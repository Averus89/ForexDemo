package pl.dexbytes.forexdemo.currencylist.history;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import pl.dexbytes.forexdemo.db.quotehistory.QuoteHistoryStat;
import pl.dexbytes.forexdemo.util.StringUtils;
import timber.log.Timber;

public class CurrencyHistoryViewModel extends ViewModel {
    private static final String ARG_PAIR = "arg_pair";
    private final CurrencyHistoryRepository mHistoryRepository;
    private MutableLiveData<String> mSelectedPair = new MutableLiveData<>();

    @Inject
    CurrencyHistoryViewModel(CurrencyHistoryRepository historyRepository) {
        mHistoryRepository = historyRepository;
    }

    LiveData<List<QuoteHistoryStat>> getStatsForPair(MutableLiveData<String> selectedPair) {
        mSelectedPair = selectedPair;
        return Transformations
                .switchMap(mSelectedPair, mHistoryRepository::getStatsForPair);
    }

    MutableLiveData<String> getSelectedPair(){
        return mSelectedPair;
    }

    public void setSelectedPair(String pair) {
        mSelectedPair.setValue(pair);
    }

    void saveToBundle(Bundle outState) {
        Timber.d("saveToBundle");
        if(StringUtils.isNotEmpty(mSelectedPair.getValue())) {
            outState.putString(ARG_PAIR, mSelectedPair.getValue());
        }
    }

    void restoreFromBundle(Bundle savedInstanceState) {
        Timber.d("restoreFromBundle");
        if(StringUtils.isNotEmpty(mSelectedPair.getValue())
                && savedInstanceState != null
                && savedInstanceState.containsKey(ARG_PAIR)) {
            setSelectedPair(savedInstanceState.getString(ARG_PAIR, ""));
        }
    }
}
