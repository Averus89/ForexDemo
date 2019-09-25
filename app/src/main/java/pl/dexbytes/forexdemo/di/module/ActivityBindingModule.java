package pl.dexbytes.forexdemo.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import pl.dexbytes.forexdemo.currencylist.main.MainActivity;
import pl.dexbytes.forexdemo.currencylist.main.MainFragmentBindingModule;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {MainFragmentBindingModule.class})
    abstract MainActivity bindsMainActivity();
}
