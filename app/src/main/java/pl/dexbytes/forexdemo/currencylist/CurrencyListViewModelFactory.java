package pl.dexbytes.forexdemo.currencylist;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import pl.dexbytes.forexdemo.rx.SchedulersFacade;

public class CurrencyListViewModelFactory implements ViewModelProvider.Factory {
    private final SchedulersFacade mSchedulersFacade;
    private final CurrencyRepository mCurrencyRepository;

    public CurrencyListViewModelFactory(SchedulersFacade schedulersFacade, CurrencyRepository currencyRepository) {
        mSchedulersFacade = schedulersFacade;
        mCurrencyRepository = currencyRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CurrencyListViewModel.class)){
            return (T) new CurrencyListViewModel(mSchedulersFacade, mCurrencyRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
