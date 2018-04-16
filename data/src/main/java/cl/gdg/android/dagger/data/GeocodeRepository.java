package cl.gdg.android.dagger.data;

import cl.gdg.android.dagger.data.model.Address;
import io.reactivex.Observable;

public interface GeocodeRepository {

    Observable<Address> lookup(String address);

}
