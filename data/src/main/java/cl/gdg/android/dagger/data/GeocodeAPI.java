package cl.gdg.android.dagger.data;

import com.google.gson.annotations.SerializedName;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.Collections;
import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface GeocodeAPI {

    @GET("geocode/{format}")
    Single<Results> geocode(@Path("format") String format, @Query("address") String address);

    class Results {

        @SerializedName("results")
        @ElementList(inline = true, entry = "result", type = ResultItem.class)
        List<ResultItem> results = Collections.emptyList();

        @SerializedName("status")
        @Element(name = "status")
        String status;

    }

    class ResultItem {

        @SerializedName("formatted_address")
        @Element(name = "formatted_address")
        String address;

        @SerializedName("geometry")
        @Element(name = "geometry")
        Geometry geometry;

        @SerializedName("place_id")
        @Element(name = "place_id")
        String placeId;

    }

    class Geometry {

        @SerializedName("location")
        @Element(name = "location")
        Location location;

    }

    class Location {

        @SerializedName("lat")
        @Element(name = "lat")
        double lat;

        @SerializedName("lng")
        @Element(name = "lng")
        double lng;

    }

}
