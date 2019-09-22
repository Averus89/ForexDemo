package pl.dexbytes.forexdemo.currencylist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import pl.dexbytes.forexdemo.db.quote.QuoteEntity;
import pl.dexbytes.forexdemo.util.StringUtils;

public class CurrencyListViewModel extends ViewModel {
    private final CurrencyRepository mCurrencyRepository;
    private MutableLiveData<String> mFilterTextAll = new MutableLiveData<>();

    @Inject
    CurrencyListViewModel(CurrencyRepository currencyRepository) {
        mCurrencyRepository = currencyRepository;
        mFilterTextAll.setValue("%");
    }

    void setFilterText(String value){
        if(StringUtils.isEmpty(value) || StringUtils.isEmpty(value.replace("%", ""))) {
            value = "%";
        } else {
            value = value.replaceAll("%", "");
            value = String.format("%%%s%%", value);
        }
        mFilterTextAll.setValue(value);
    }

    LiveData<List<QuoteEntity>> getQuotes(){
        return Transformations
                .switchMap(mFilterTextAll, input -> {

                    return mCurrencyRepository.getQuotesByName(input);
                });
    }

    public void insert(QuoteEntity entity){
        mCurrencyRepository.save(entity);
    }
}
