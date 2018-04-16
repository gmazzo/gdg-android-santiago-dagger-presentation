package cl.gdg.android.dagger.data.di;

import cl.gdg.android.dagger.data.BuildConfig;
import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class OkHttpModule {

    @Provides
    OkHttpClient.Builder provideClientBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            builder.addInterceptor(interceptor);
        }
        return builder;
    }

    @Provides
    @Reusable
    OkHttpClient provideClient(OkHttpClient.Builder builder) {
        return builder.build();
    }

}
