package cl.gdg.android.dagger.app;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import cl.gdg.android.dagger.app.di.DaggerInjector;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.reactivex.plugins.RxJavaPlugins;

public class DaggerApp extends DaggerApplication {
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerInjector.create();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        RxJavaPlugins.setErrorHandler($ -> {
            $.printStackTrace();

            handler.post(() ->
                    Toast.makeText(this, $.getLocalizedMessage(), Toast.LENGTH_SHORT)
                            .show());
        });
    }
}
