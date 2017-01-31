package nyc.c4q.wesniemarcelin.resourcedapp.google_map;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import nyc.c4q.wesniemarcelin.resourcedapp.R;

public class MapsActivity_Hakeem extends AppCompatActivity implements OnMapReadyCallback {

    private MapFragment mMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hakeem);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        setContentView(R.layout.activity_main_hakeem);
//        mMapFragment = MapFragment.newInstance();
//        FragmentTransaction fragmentTransaction =
//                getFragmentManager().beginTransaction();
//        fragmentTransaction.add(R.id.map_frame_layout, mMapFragment);
//        fragmentTransaction.commit();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("tt", "onMapReady: hi");
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(-33.852, 151))
                .title("Sydney "));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-33.852, 151)));
    }

    void mapMarker (GoogleMap googleMap, ArrayList<ArrayList<String>> data){
        for (int i = 0; i < data.size(); i++) {
            String place = data.get(i).get(8);
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(Double.valueOf(place.substring(7,25)),Double.valueOf(place.substring(26,43))))
                    .title(data.get(i).get(9)));
        }
    }
}
