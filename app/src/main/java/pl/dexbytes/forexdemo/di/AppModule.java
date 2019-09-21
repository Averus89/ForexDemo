package pl.dexbytes.forexdemo.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import pl.dexbytes.forexdemo.App;

@Module
public class AppModule {
    @Provides
    Context provideContext(App application){
        return application.getApplicationContext();
    }
}
