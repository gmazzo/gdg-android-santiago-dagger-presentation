package cl.gdg.android.dagger.app.di;

import cl.gdg.android.dagger.app.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(includes = AddressFragmentModule.class)
interface MainActivityModule {

    @ContributesAndroidInjector
    MainActivity provideMainActivity();

}
