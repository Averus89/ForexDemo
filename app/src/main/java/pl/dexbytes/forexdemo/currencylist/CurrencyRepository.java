package pl.dexbytes.forexdemo.currencylist;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import pl.dexbytes.forexdemo.db.quote.QuoteDao;
import pl.dexbytes.forexdemo.db.quote.QuoteEntity;

public class CurrencyRepository {
    private QuoteDao mQuoteDao;

    @Inject
    public CurrencyRepository(QuoteDao quoteDao) {
        mQuoteDao = quoteDao;
    }

    public LiveData<List<QuoteEntity>> getQuotes(){
        return mQuoteDao.findAll();
    }

    public void saveAll(List<QuoteEntity> entities){
        mQuoteDao.saveAll(entities);
    }

    public void save(QuoteEntity entity) {
        mQuoteDao.save(entity);
    }

    public interface RepositorySelectedListener{
        void onQuoteSelected(QuoteEntity quote);
    }
}
