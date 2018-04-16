package cl.gdg.android.dagger.data;

import javax.inject.Inject;

import cl.gdg.android.dagger.data.model.Address;
import io.reactivex.Observable;
import retrofit2.Retrofit;

public class RetrofitGeocodeRepository implements GeocodeRepository {
    private final String format;
    private final GeocodeAPI api;

    @Inject
    public RetrofitGeocodeRepository(String format, Retrofit retrofit) {
        this.format = format;
        this.api = retrofit.create(GeocodeAPI.class);
    }

    @Override
    public Observable<Address> lookup(String address) {
        return api.geocode(format, address)
                .filter($ -> "OK".equals($.status))
                .flatMapObservable($ -> Observable.fromIterable($.results))
                .map(this::convertToAddress);
    }

    private Address convertToAddress(GeocodeAPI.ResultItem item) {
        return new Address(
                item.place_id,
                item.formatted_address,
                item.geometry.location.lat,
                item.geometry.location.lng);
    }

}
