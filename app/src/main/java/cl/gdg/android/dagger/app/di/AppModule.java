package cl.gdg.android.dagger.app.di;

import cl.gdg.android.dagger.data.di.DataModule;
import dagger.Module;

@Module(includes = {DataModule.class, MainActivityModule.class})
interface AppModule {
}
