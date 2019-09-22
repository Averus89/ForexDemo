package pl.dexbytes.forexdemo.di.module;

import androidx.lifecycle.ViewModel;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import pl.dexbytes.forexdemo.currencylist.CurrencyListViewModel;
import pl.dexbytes.forexdemo.di.util.ViewModelKey;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyListViewModel.class)
    abstract ViewModel bindListViewModel(CurrencyListViewModel currencyListViewModel);
}
