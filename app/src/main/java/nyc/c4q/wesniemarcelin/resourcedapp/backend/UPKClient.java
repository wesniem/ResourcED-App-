package nyc.c4q.wesniemarcelin.resourcedapp.backend;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.wesniemarcelin.resourcedapp.R;
import nyc.c4q.wesniemarcelin.resourcedapp.google_map.MapsActivity_Hakeem;
import nyc.c4q.wesniemarcelin.resourcedapp.mapRecyclerView.UPKMapAdapter;
import nyc.c4q.wesniemarcelin.resourcedapp.model.Rows;
import nyc.c4q.wesniemarcelin.resourcedapp.model.UPKResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by josevila on 1/29/17.
 */

public class UPKClient {





    private static final String TAG = "Connection result";
    private static String BASE_URL = "http://gsx2json.com/";

    public ArrayList<Rows> getData() {
        return data;
    }

    public void setData(ArrayList<Rows> data) {
        this.data = data;
    }

    static ArrayList<Rows> data;

    /*
    HAKEEM: I added the  constructor  so that inside we can call Jose's connectToServer method
    this will be useful for getting the data we need in the main activity
     */
    public UPKClient() {
        connectToServer(BASE_URL);
    }

    //    private RecyclerView childCareRecyclerView;
//    private View mRoot;
//    private ChildCareAdapter adapter;
    static List<Rows> mUPKList;
     RecyclerView uPKReceyclerView;
    static View view;


    public static void connectToServer(String baseUrl) {
//        uPKReceyclerView = (RecyclerView) view.findViewById(R.id.map_recycler_view);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
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
//                uPKReceyclerView.setLayoutManager(new LinearLayoutManager(MapsActivity_Hakeem.getView().getContext()));
//                UPKMapAdapter adapter = new UPKMapAdapter(mUPKList);
//                uPKReceyclerView.setAdapter(adapter);
                Log.d("Adapter", "adapter attached");

                data = response.body().getRows();
                System.out.println(data);
                System.out.println("UPK DATA STREAM");
//                childCareAdapter = new ChildCareAdapter(response.body());
//                childCareRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//                childCareRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<UPKResponse> call, Throwable t) {
                Log.d(TAG, "Failed to connect");
            }
        });
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }
}
