package pl.dexbytes.forexdemo.di.module;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.dexbytes.forexdemo.db.AppDatabase;
import pl.dexbytes.forexdemo.db.quote.QuoteDao;
import pl.dexbytes.forexdemo.db.quotehistory.QuoteHistoryDao;

@Module(includes = {ViewModelModule.class})
public class DatabaseModule {

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(Context context){
        return Room.databaseBuilder(context, AppDatabase.class, "app-database")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    QuoteDao provideQuoteDao(AppDatabase appDatabase){
        return appDatabase.quoteDao();
    }

    @Provides
    @Singleton
    QuoteHistoryDao provideQuoteHistoryDao(AppDatabase appDatabase) {
        return appDatabase.getQuoteHistoryDao();
    }
}
