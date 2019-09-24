package pl.dexbytes.forexdemo.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import pl.dexbytes.forexdemo.db.quote.QuoteDao;
import pl.dexbytes.forexdemo.db.quote.QuoteEntity;
import pl.dexbytes.forexdemo.db.quotehistory.QuoteHistoryDao;
import pl.dexbytes.forexdemo.db.quotehistory.QuoteHistoryEntity;

@Database(entities = {QuoteEntity.class, QuoteHistoryEntity.class}, version = 4, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract QuoteDao quoteDao();

    public abstract QuoteHistoryDao getQuoteHistoryDao();
}
