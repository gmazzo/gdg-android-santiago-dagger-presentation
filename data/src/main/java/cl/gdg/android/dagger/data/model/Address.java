package cl.gdg.android.dagger.data.model;

public class Address {
    public final String placeId;
    public final String address;
    public final double latitude;
    public final double longitude;

    public Address(String placeId, String address, double latitude, double longitude) {
        this.placeId = placeId;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
