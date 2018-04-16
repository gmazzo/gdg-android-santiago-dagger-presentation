package cl.gdg.android.dagger.data.di;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

@Module(includes = OkHttpModule.class)
public class RetrofitModule {

    @Provides
    Retrofit.Builder provideClientBuilder(OkHttpClient client) {
        return new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .baseUrl("https://maps.googleapis.com/maps/api/");
    }

    @JSON
    @Provides
    @Reusable
    Retrofit provideJsonRetrofit(Retrofit.Builder builder) {
        return builder
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @XML
    @Provides
    @Reusable
    Retrofit provideXMLRetrofit(Retrofit.Builder builder) {
        return builder
                .addConverterFactory(SimpleXmlConverterFactory.createNonStrict())
                .build();
    }

}
