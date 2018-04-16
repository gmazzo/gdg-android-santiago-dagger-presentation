package cl.gdg.android.dagger.app.di;

import cl.gdg.android.dagger.app.AddressFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
interface AddressFragmentModule {

    @ContributesAndroidInjector
    AddressFragment provideAddressFragment();

}
