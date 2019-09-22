package pl.dexbytes.forexdemo.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import pl.dexbytes.forexdemo.service.RefreshQuote;

@Module
public abstract class ServiceBindingModule {

    @ContributesAndroidInjector
    abstract RefreshQuote bindRefreshQuoteService();
}
