package pl.dexbytes.forexdemo.db.quote;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface QuoteDao {
    @Query("SELECT * FROM quote")
    LiveData<List<QuoteEntity>> getAll();

    @Query("SELECT * FROM quote WHERE quote.uid LIKE (:search)")
    LiveData<List<QuoteEntity>> searchAllByNane(String search);

    @Query("SELECT * FROM quote WHERE quote.uid IN (:pairs)")
    LiveData<List<QuoteEntity>> getAllBySymbol(String[] pairs);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll(QuoteEntity... entities);

    @Delete
    Single<Integer> delete(QuoteEntity entity);
}
