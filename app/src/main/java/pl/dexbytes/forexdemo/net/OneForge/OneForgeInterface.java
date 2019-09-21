package pl.dexbytes.forexdemo.net.OneForge;

import com.google.gson.JsonElement;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import pl.dexbytes.forexdemo.vars.StaticVariables;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OneForgeInterface {
    @GET(StaticVariables.Urls.QUOTES)
    Observable<List<Quote>> getQuotes(@Query("pairs") String pairs);

    @GET(StaticVariables.Urls.QUOTES)
    Single<List<Quote>> getQuotesSingle(@Query("pairs") String pairs);

    @GET(StaticVariables.Urls.SYMBOLS)
    Observable<List<String>> getPairs();

    @GET(StaticVariables.Urls.CONVERT)
    Observable<Conversion> getConversion(
        @Query("from") String from,
        @Query("to") String to,
        @Query("quantity") int quantity
    );

    @GET(StaticVariables.Urls.MARKET_STATUS)
    Observable<JsonElement> getMarketStatus();

    @GET(StaticVariables.Urls.QUOTA)
    Observable<Quota> getQuota();
}
