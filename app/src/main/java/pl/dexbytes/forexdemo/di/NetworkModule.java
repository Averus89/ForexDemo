package pl.dexbytes.forexdemo.di;

import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import pl.dexbytes.forexdemo.net.OneForge.OneForgeInterface;
import pl.dexbytes.forexdemo.vars.StaticVariables;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module
public class NetworkModule {

    @Provides
    Gson provideGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setLenient();
        return builder.create();
    }

    @Provides
    HttpLoggingInterceptor.Logger provideLogger(){
        return s -> Timber.d(s);
    }

    @Provides
    List<Interceptor> provideInterceptors(HttpLoggingInterceptor.Logger logger){
        List<Interceptor> interceptors = new ArrayList<>();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(logger);
        loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        interceptors.add(loggingInterceptor);
        Interceptor apiInterceptor = chain -> {
            Request request = chain.request();
            HttpUrl url = request.url()
                    .newBuilder()
                    .addQueryParameter(StaticVariables.Urls.API_PARAM, StaticVariables.Keys.ONE_FORGE_API_KEY)
                    .build();
            Request newRequest = request.newBuilder()
                    .url(url)
                    .build();
            return chain.proceed(newRequest);
        };
        interceptors.add(apiInterceptor);
        return interceptors;
    }

    @Provides
    OkHttpClient provideOkHttpClient(List<Interceptor> interceptors){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);
        interceptors.forEach(builder::addInterceptor);
        return builder.build();
    }

    @Provides
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .baseUrl(StaticVariables.Urls.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    OneForgeInterface providesOneForgeInterface(Retrofit retrofit){
        return retrofit.create(OneForgeInterface.class);
    }
}
