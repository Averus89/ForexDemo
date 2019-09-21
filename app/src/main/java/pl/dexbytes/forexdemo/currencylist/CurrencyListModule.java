package pl.dexbytes.forexdemo.currencylist;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.dexbytes.forexdemo.di.CurrencyListActivityScope;
import pl.dexbytes.forexdemo.net.OneForge.OneForgeInterface;
import pl.dexbytes.forexdemo.rx.SchedulersFacade;

@Module
public class CurrencyListModule {

    @Provides
    @CurrencyListActivityScope
    CurrencyRepository provideCurrencyRepository(OneForgeInterface forgeInterface){
        return new CurrencyRepository(forgeInterface);
    }

    @Provides
    @CurrencyListActivityScope
    CurrencyListViewModelFactory provideCurrencyListViewModelFactory(CurrencyRepository currencyRepository,
                                                                     SchedulersFacade schedulersFacade) {
        return new CurrencyListViewModelFactory(schedulersFacade, currencyRepository);
    }
}
