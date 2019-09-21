package pl.dexbytes.forexdemo.currencylist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CurrencyListViewModelFactory implements ViewModelProvider.Factory {
    private final CurrencyRepository mCurrencyRepository;

    public CurrencyListViewModelFactory(CurrencyRepository currencyRepository) {
        mCurrencyRepository = currencyRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CurrencyListViewModel.class)){
            return (T) new CurrencyListViewModel(mCurrencyRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
