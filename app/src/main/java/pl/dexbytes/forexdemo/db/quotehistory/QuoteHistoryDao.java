package pl.dexbytes.forexdemo.db.quotehistory;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuoteHistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(QuoteHistoryEntity entity);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<QuoteHistoryEntity> entityList);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(QuoteHistoryEntity... entities);

    @Query("SELECT " +
            "MIN(price) AS min, " +
            "AVG(price) AS avg, MAX(price) AS max, " +
            "CAST(strftime('%d', timestamp) AS int) AS day, " +
            "CAST(strftime('%m', timestamp) AS int) AS month, " +
            "CAST(strftime('%Y', timestamp) AS int) AS year " +
            "FROM quote_history WHERE symbol = :pair GROUP BY day ORDER BY date(timestamp) DESC")
    LiveData<List<QuoteHistoryStat>> selectStatsForPair(String pair);
}
