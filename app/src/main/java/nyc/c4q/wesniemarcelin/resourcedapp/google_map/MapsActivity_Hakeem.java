package nyc.c4q.wesniemarcelin.resourcedapp.google_map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import nyc.c4q.wesniemarcelin.resourcedapp.R;
import nyc.c4q.wesniemarcelin.resourcedapp.backend.places_gmaps.PlaceResponse;
import nyc.c4q.wesniemarcelin.resourcedapp.backend.places_gmaps.PlaceService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity_Hakeem extends AppCompatActivity implements OnMapReadyCallback {
    private ArrayList<ArrayList<String>> retrofitData;
    private ArrayList<ArrayList<String>> dataToImplement;
    private EditText radius;
    private static final double ONEMILEINDEGREES = 1 / 69d;
    private GoogleMap mapDisplayed;
    private EditText address;
    int PLACE_PICKER_REQUEST = 1;
    private GoogleApiClient mGoogleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hakeem);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        radius = (EditText) findViewById(R.id.radius);
        address = (EditText) findViewById(R.id.search_bar_gmap);
        // ATTENTION: This "addApi(AppIndex.API)"was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapDisplayed = googleMap;
        mapMarker(mapDisplayed, dataToImplement);
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

    public void mapSearch(View v) {
        dataToImplement = distanceSearch( new LatLng(0,0), Double.valueOf(radius.getText().toString()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
    private static void connectToServer(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        PlaceService service = retrofit.create(PlaceService.class);
        Call<PlaceResponse> call = service.getPlace();
        call.enqueue(new Callback<PlaceResponse>() {
            @Override
            public void onResponse(Call<PlaceResponse> call, Response<PlaceResponse> response) {
               // data = response.body();
            }
            @Override
            public void onFailure(Call<PlaceResponse> call, Throwable t) {

            }
        });
    }

}
