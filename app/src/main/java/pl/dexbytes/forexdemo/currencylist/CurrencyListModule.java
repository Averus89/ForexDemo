package pl.dexbytes.forexdemo.currencylist;

import dagger.Module;
import dagger.Provides;
import pl.dexbytes.forexdemo.db.quote.QuoteDao;
import pl.dexbytes.forexdemo.di.CurrencyListActivityScope;

@Module
public class CurrencyListModule {

    @Provides
    @CurrencyListActivityScope
    CurrencyRepository provideCurrencyRepository(QuoteDao quoteDao){
        return new CurrencyRepository(quoteDao);
    }

    @Provides
    @CurrencyListActivityScope
    CurrencyListViewModelFactory provideCurrencyListViewModelFactory(CurrencyRepository currencyRepository) {
        return new CurrencyListViewModelFactory(currencyRepository);
    }
}
