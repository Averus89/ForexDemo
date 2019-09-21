package pl.dexbytes.forexdemo.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import pl.dexbytes.forexdemo.db.quote.QuoteDao;
import pl.dexbytes.forexdemo.db.quote.QuoteEntity;

@Database(entities = {QuoteEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract QuoteDao quoteDao();
}
