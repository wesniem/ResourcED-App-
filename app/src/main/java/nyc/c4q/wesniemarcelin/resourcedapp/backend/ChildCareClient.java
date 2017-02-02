package nyc.c4q.wesniemarcelin.resourcedapp.backend;

import android.util.Log;

import java.util.ArrayList;

import nyc.c4q.wesniemarcelin.resourcedapp.model.ChildCareResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by josevila on 1/29/17.
 */

public class ChildCareClient {

    private static final String TAG = "Connection result";
    private static String BASE_URL = "https://data.cityofnewyork.us/";

    public ArrayList<ArrayList<String>> getData() {
        return data;
    }

    public void setData(ArrayList<ArrayList<String>> data) {
        this.data = data;
    }

    ArrayList<ArrayList<String>> data;


    /*
    HAKEEM: I added the  constructor  so that inside we can call Jose's connectToServer method
    this will be useful for getting the data we need in the main activity
     */
    public ChildCareClient() {
        connectToServer(BASE_URL);
    }
//    private RecyclerView childCareRecyclerView;
//    private View mRoot;
//    private ChildCareAdapter adapter;

    private void connectToServer(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        ChildCareService service = retrofit.create(ChildCareService.class);
        Call<ChildCareResponse> call = service.getData();
        call.enqueue(new Callback<ChildCareResponse>() {
            @Override
            public void onResponse(Call<ChildCareResponse> call, Response<ChildCareResponse> response) {

                /*
                HAKEEM: added an arraylist data field that will get populated here
                 */
                data = response.body().getData();

//                childCareAdapter = new ChildCareAdapter(response.body());
//                childCareRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//                childCareRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ChildCareResponse> call, Throwable t) {
                Log.d(TAG,"Failed to connect");
            }
        });
    }

    public static String getBaseUrl(){
        return BASE_URL;
    }
}
