package cl.gdg.android.dagger.data;

import java.util.Collections;
import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface GeocodeAPI {

    @GET("geocode/${format}")
    Single<Results> geocode(@Path("format") String format, @Query("address") String address);

    class Results {
        List<ResultItem> results = Collections.emptyList();
        String status;
    }

    class ResultItem {
        String formatted_address;
        Geometry geometry;
        String place_id;
    }

    class Geometry {
        Location location;
    }

    class Location {
        double lat;
        double lng;
    }

}
