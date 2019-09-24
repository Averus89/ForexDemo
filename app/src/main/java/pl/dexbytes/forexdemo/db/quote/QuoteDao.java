package pl.dexbytes.forexdemo.db.quote;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QuoteDao {
    @Query("SELECT * FROM quote")
    List<QuoteEntity> findAllSync();

    @Query("SELECT * FROM quote")
    LiveData<List<QuoteEntity>> findAll();

    @Query("SELECT * FROM quote WHERE uid LIKE (:search)")
    LiveData<List<QuoteEntity>> findByName(String search);

    @Query("SELECT * FROM quote WHERE uid IN (:pairs)")
    LiveData<List<QuoteEntity>> findByPairs(String[] pairs);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(QuoteEntity entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<QuoteEntity> entities);

    @Update
    void update(QuoteEntity entity);

    @Delete
    int delete(QuoteEntity entity);
}
