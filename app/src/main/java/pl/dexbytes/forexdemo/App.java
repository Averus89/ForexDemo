package pl.dexbytes.forexdemo;

import android.app.Application;
import android.content.Intent;
import android.os.Build;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import pl.dexbytes.forexdemo.di.component.DaggerAppComponent;
import pl.dexbytes.forexdemo.service.RefreshQuote;
import timber.log.Timber;

public class App extends Application implements HasAndroidInjector {
    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);

        Intent serviceIntent = new Intent(this, RefreshQuote.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent);
        } else {
            startService(serviceIntent);
        }
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }
}
