package pl.dexbytes.forexdemo.currencylist;

import java.util.List;

import io.reactivex.Observable;
import pl.dexbytes.forexdemo.net.OneForge.OneForgeInterface;
import pl.dexbytes.forexdemo.net.OneForge.Quote;
import pl.dexbytes.forexdemo.vars.StaticVariables;

public class CurrencyRepository {

    private OneForgeInterface mOneForgeInterface;

    public CurrencyRepository(OneForgeInterface oneForgeInterface) {
        mOneForgeInterface = oneForgeInterface;
    }

    public Observable<List<Quote>> getQuotes(){
        return mOneForgeInterface.getQuotes(StaticVariables.Example.EXAMPLE_PAIRS);
    }

    public interface RepositorySelectedListener{
        void onQuoteSelected(Quote quote);
    }
}
