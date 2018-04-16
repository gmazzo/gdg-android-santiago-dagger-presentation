package cl.gdg.android.dagger.app.di;

import javax.inject.Singleton;

import cl.gdg.android.dagger.app.DaggerApp;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class})
interface Injector extends AndroidInjector<DaggerApp> {
}
