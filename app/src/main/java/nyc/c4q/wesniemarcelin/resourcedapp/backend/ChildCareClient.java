package nyc.c4q.wesniemarcelin.resourcedapp.backend;

import android.util.Log;

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
    private static String BASE_URL = "http://jsjrobotics.nyc/";
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
