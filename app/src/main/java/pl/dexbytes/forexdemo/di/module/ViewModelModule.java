package pl.dexbytes.forexdemo.di.module;

import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import pl.dexbytes.forexdemo.currencylist.currencylist.CurrencyListViewModel;
import pl.dexbytes.forexdemo.currencylist.history.CurrencyHistoryViewModel;
import pl.dexbytes.forexdemo.currencylist.main.SharedViewModel;
import pl.dexbytes.forexdemo.di.util.ViewModelKey;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyListViewModel.class)
    abstract ViewModel bindListViewModel(CurrencyListViewModel currencyListViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyHistoryViewModel.class)
    abstract ViewModel bindHistoryViewModel(CurrencyHistoryViewModel currencyHistoryViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SharedViewModel.class)
    abstract ViewModel bindSharedViewModel(SharedViewModel sharedViewModel);
}
