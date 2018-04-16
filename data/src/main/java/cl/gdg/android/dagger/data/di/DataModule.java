package cl.gdg.android.dagger.data.di;

import cl.gdg.android.dagger.data.GeocodeRepository;
import cl.gdg.android.dagger.data.RetrofitGeocodeRepository;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import retrofit2.Retrofit;

@Module(includes = DataModule.Bindings.class)
public class DataModule {

    @JSON
    @Provides
    @Reusable
    GeocodeRepository provideJSONRepository(@JSON Retrofit retrofit) {
        return new RetrofitGeocodeRepository("json", retrofit);
    }

    @XML
    @Provides
    @Reusable
    GeocodeRepository provideXMLRepository(@XML Retrofit retrofit) {
        return new RetrofitGeocodeRepository("xml", retrofit);
    }

    @Module
    interface Bindings {

        @Binds
        GeocodeRepository bindRepository(@JSON GeocodeRepository impl);

    }

}
