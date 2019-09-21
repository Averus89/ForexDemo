package pl.dexbytes.forexdemo.currencylist;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.Completable;
import pl.dexbytes.forexdemo.db.quote.QuoteDao;
import pl.dexbytes.forexdemo.db.quote.QuoteEntity;

public class CurrencyRepository {
    private QuoteDao mQuoteDao;

    public CurrencyRepository(QuoteDao quoteDao) {
        mQuoteDao = quoteDao;
    }

    public LiveData<List<QuoteEntity>> getQuotes(){
        return mQuoteDao.getAll();
    }

    public Completable insert(QuoteEntity... entities){
        return mQuoteDao.insertAll(entities);
    }

    public interface RepositorySelectedListener{
        void onQuoteSelected(QuoteEntity quote);
    }
}
