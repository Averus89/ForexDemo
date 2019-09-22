package pl.dexbytes.forexdemo.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import pl.dexbytes.forexdemo.R;
import pl.dexbytes.forexdemo.currencylist.CurrencyListActivity;
import pl.dexbytes.forexdemo.db.quote.QuoteDao;
import pl.dexbytes.forexdemo.mappers.QuoteMapper;
import pl.dexbytes.forexdemo.net.OneForge.OneForgeInterface;
import pl.dexbytes.forexdemo.rx.SchedulersFacade;
import pl.dexbytes.forexdemo.vars.StaticVariables;
import timber.log.Timber;

public class RefreshQuote extends Service {
    public static final int NOTIFICATION_ID = 1000;
    Disposable mSubscription;

    @Inject
    SchedulersFacade mSchedulersFacade;

    @Inject
    OneForgeInterface mOneForgeInterface;

    @Inject
    QuoteDao mQuoteDao;

    public RefreshQuote() {
    }

    @Override
    public void onCreate() {
        AndroidInjection.inject(this);
        super.onCreate();
        startForeground(NOTIFICATION_ID, createNotification());
        scheduleWork();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void scheduleWork(){
        mSubscription = Observable.interval(20, TimeUnit.SECONDS)
                .subscribeOn(mSchedulersFacade.io())
                .observeOn(mSchedulersFacade.io())
                .subscribe(aLong -> mOneForgeInterface.getQuotes(StaticVariables.Example.EXAMPLE_PAIRS)
                        .subscribeOn(mSchedulersFacade.io())
                        .observeOn(mSchedulersFacade.io())
                        .subscribe(
                                list -> mQuoteDao.saveAll(QuoteMapper.INSTANCE.quoteDtoToQuoteEntities(list)),
                                Timber::e
                        ));
    }

    private PendingIntent getActivityPendingIntent(){
        Intent intent = new Intent(this, CurrencyListActivity.class);
        return PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private Notification createNotification(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (manager != null) {
                manager.createNotificationChannel(getNotificationChannel());
            }
        }
        return new NotificationCompat.Builder(
                this, getString(R.string.notification_channel_id))
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Processing quotes in the background")
                .setAutoCancel(false)
                .setOngoing(true)
                .setContentIntent(getActivityPendingIntent())
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setCategory(NotificationCompat.CATEGORY_SERVICE)
                .build();
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private NotificationChannel getNotificationChannel(){
        NotificationChannel channel = new NotificationChannel(
                getString(R.string.notification_channel_id),
                getString(R.string.notification_channel),
                NotificationManager.IMPORTANCE_NONE
        );
        channel.setLightColor(Color.BLUE);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        return channel;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mSubscription != null && !mSubscription.isDisposed()) {
            mSubscription.dispose();
        }
    }
}
