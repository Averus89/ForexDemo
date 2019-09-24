package pl.dexbytes.forexdemo.currencylist.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import pl.dexbytes.forexdemo.currencylist.currencylist.CurrencyListFragment;

@Module
public abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract CurrencyListFragment bindCurrencyListFragment();
}
