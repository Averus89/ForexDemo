package pl.dexbytes.forexdemo.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import pl.dexbytes.forexdemo.currencylist.CurrencyListActivity;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {MainFragmentBindingModule.class})
    abstract CurrencyListActivity bindCurrencyListActivity();
}
