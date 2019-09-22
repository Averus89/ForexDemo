package pl.dexbytes.forexdemo.currencylist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import pl.dexbytes.forexdemo.db.quote.QuoteEntity;

public class CurrencyListViewModel extends ViewModel {
    private final CurrencyRepository mCurrencyRepository;

    @Inject
    public CurrencyListViewModel(CurrencyRepository currencyRepository) {
        mCurrencyRepository = currencyRepository;
    }

    LiveData<List<QuoteEntity>> getQuotes(){
        return mCurrencyRepository.getQuotes();
    }

    public void insert(QuoteEntity entity){
        mCurrencyRepository.save(entity);
    }
}
