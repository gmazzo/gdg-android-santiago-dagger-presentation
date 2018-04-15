package cl.gdg.android.dagger.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String query = "https://maps.googleapis.com/maps/api/geocode/json?address=Andres+Bello+2687,+Santiago,+Chile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
