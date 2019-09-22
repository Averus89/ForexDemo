package pl.dexbytes.forexdemo.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import pl.dexbytes.forexdemo.App;
import pl.dexbytes.forexdemo.di.module.ActivityBindingModule;
import pl.dexbytes.forexdemo.di.module.ContextModule;
import pl.dexbytes.forexdemo.di.module.DatabaseModule;
import pl.dexbytes.forexdemo.di.module.NetworkModule;
import pl.dexbytes.forexdemo.di.module.ServiceBindingModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ContextModule.class,
        NetworkModule.class,
        DatabaseModule.class,
        ActivityBindingModule.class,
        ServiceBindingModule.class
})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance Builder application(Application application);
        AppComponent build();
    }

    void inject(App app);
}
