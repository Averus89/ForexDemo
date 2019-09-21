package pl.dexbytes.forexdemo.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import pl.dexbytes.forexdemo.currencylist.CurrencyListActivity;
import pl.dexbytes.forexdemo.currencylist.CurrencyListModule;

@Module
public abstract class AppBinder {

    @CurrencyListActivityScope
    @ContributesAndroidInjector(modules = {CurrencyListModule.class, NetworkModule.class})
    public abstract CurrencyListActivity currencyListActivity();
}
