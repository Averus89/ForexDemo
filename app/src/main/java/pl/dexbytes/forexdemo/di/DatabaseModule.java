package pl.dexbytes.forexdemo.di;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.dexbytes.forexdemo.db.AppDatabase;
import pl.dexbytes.forexdemo.db.quote.QuoteDao;

@Module
public class DatabaseModule {

    @Provides
    AppDatabase provideAppDatabase(Context context){
        return Room.databaseBuilder(context, AppDatabase.class, "app-database")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    QuoteDao provideQuoteDao(AppDatabase appDatabase){
        return appDatabase.quoteDao();
    }
}
