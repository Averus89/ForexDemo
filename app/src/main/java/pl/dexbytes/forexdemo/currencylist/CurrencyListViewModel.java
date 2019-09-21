package pl.dexbytes.forexdemo.currencylist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.Completable;
import pl.dexbytes.forexdemo.db.quote.QuoteEntity;

public class CurrencyListViewModel extends ViewModel {
    private final CurrencyRepository mCurrencyRepository;

    public CurrencyListViewModel(CurrencyRepository currencyRepository) {
        mCurrencyRepository = currencyRepository;
    }

    LiveData<List<QuoteEntity>> getAllQuotes(){
        return mCurrencyRepository.getQuotes();
    }

    public Completable insert(QuoteEntity entity){
        return mCurrencyRepository.insert(entity);
    }
}
