package nyc.c4q.wesniemarcelin.resourcedapp.google_map;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.wesniemarcelin.resourcedapp.R;
import nyc.c4q.wesniemarcelin.resourcedapp.backend.UPKService;
import nyc.c4q.wesniemarcelin.resourcedapp.mapRecyclerView.UPKMapAdapter;
import nyc.c4q.wesniemarcelin.resourcedapp.model.Rows;
import nyc.c4q.wesniemarcelin.resourcedapp.model.UPKResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity_Hakeem extends AppCompatActivity implements OnMapReadyCallback {
    private ArrayList<ArrayList<String>> retrofitData;
    private static final double ONEMILEINDEGREES = 1 / 69d;
    static View view;
    RecyclerView uPKReceyclerView;
    List<Rows> mUPKList;


//    public static View getView() {
//        return view;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hakeem);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        uPKReceyclerView = (RecyclerView) findViewById(R.id.map_recycler_view);
        connectToServer();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
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


    public void connectToServer(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://gsx2json.com/").addConverterFactory(GsonConverterFactory.create()).build();
        UPKService service = retrofit.create(UPKService.class);
        Call<UPKResponse> call = service.getData("1ITPdXilVjBOLG_rxaSxeWbK-esHrY8AX3pGvixAzDXo", "3", "");
        call.enqueue(new Callback<UPKResponse>() {
            @Override
            public void onResponse(Call<UPKResponse> call, Response<UPKResponse> response) {
                /*
                HAKEEM: added an arraylist data field that will get populated here
                 */
                Log.d("Success", "in there");
                Log.d("YOOO", "POJO" + response.body());
                UPKResponse upkResponse = response.body();
                mUPKList = upkResponse.getRows();
                uPKReceyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                UPKMapAdapter adapter = new UPKMapAdapter(mUPKList);
                uPKReceyclerView.setAdapter(adapter);
                Log.d("Adapter", "adapter attached");

//                data = response.body().getRows();
//                System.out.println(data);
//                System.out.println("UPK DATA STREAM");
//                childCareAdapter = new ChildCareAdapter(response.body());
//                childCareRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//                childCareRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<UPKResponse> call, Throwable t) {
                Log.d("failure", "Failed to connect");
            }
        });
    }
}
