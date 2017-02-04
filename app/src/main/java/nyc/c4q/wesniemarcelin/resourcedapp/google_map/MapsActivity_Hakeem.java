package nyc.c4q.wesniemarcelin.resourcedapp.google_map;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import nyc.c4q.wesniemarcelin.resourcedapp.R;

public class MapsActivity_Hakeem extends AppCompatActivity implements OnMapReadyCallback {
    private ArrayList<ArrayList<String>> retrofitData;
    private static final double ONEMILEINDEGREES = 1 / 69d;
    private GoogleMap mapDisplayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hakeem);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapDisplayed = googleMap;
    }



    void mapMarker(GoogleMap googleMap, ArrayList<ArrayList<String>> dataToMap) {
        for (int i = 0; i < dataToMap.size(); i++) {
            /*
             * 8 is the string that stores the lat long point
             */
            String place = dataToMap.get(i).get(8);
            googleMap.addMarker(new MarkerOptions()
                    .position(getLatLngFromRadius(place))
                    .title(dataToMap.get(i).get(9)));
        }
    }

    ArrayList<ArrayList<String>> distanceSearch(LatLng center, double radius) {
        ArrayList<ArrayList<String>> dataInRange = new ArrayList<>();
        for (int i = 0; i < retrofitData.size(); i++) {
            String place = retrofitData.get(i).get(8);
            if (distanceFromPoint(center, getLatLngFromRadius(place)) < (radius * ONEMILEINDEGREES)) {
                dataInRange.add(retrofitData.get(i));
            }
        }
        return dataInRange;
    }

    private LatLng getLatLngFromRadius(String place) {
        return new LatLng(Double.valueOf(place.substring(7, 25)), Double.valueOf(place.substring(26, 43)));
    }

    private double distanceFromPoint(LatLng a, LatLng b) {
        return Math.sqrt(Math.pow(a.longitude - b.longitude, 2) + Math.pow(a.latitude - b.latitude, 2));
    }
}
