package nyc.c4q.wesniemarcelin.resourcedapp.google_map

import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.OnMapReadyCallback
import androidx.recyclerview.widget.RecyclerView
import nyc.c4q.wesniemarcelin.resourcedapp.model.Rows
import android.os.Bundle
import android.util.Log
import android.view.View
import nyc.c4q.wesniemarcelin.resourcedapp.R
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.LatLng
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import nyc.c4q.wesniemarcelin.resourcedapp.backend.UPKService
import nyc.c4q.wesniemarcelin.resourcedapp.model.UPKResponse
import androidx.recyclerview.widget.LinearLayoutManager
import nyc.c4q.wesniemarcelin.resourcedapp.mapRecyclerView.UPKMapAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList
import kotlin.math.pow
import kotlin.math.sqrt

class MapsActivity_Hakeem : AppCompatActivity(), OnMapReadyCallback {
    private val retrofitData: ArrayList<ArrayList<String>>? = null
    var uPKReceyclerView: RecyclerView? = null
    var mUPKList: List<Rows>? = null

    //    public static View getView() {
    //        return view;
    //    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_hakeem)
        val mapFragment = fragmentManager
            .findFragmentById(R.id.map) as MapFragment
        mapFragment.getMapAsync(this)
        uPKReceyclerView = findViewById<View>(R.id.map_recycler_view) as RecyclerView
        connectToServer()
    }

    override fun onMapReady(googleMap: GoogleMap) {}
    fun mapMarker(googleMap: GoogleMap, dataToMap: List<Rows>) {
        for (i in dataToMap.indices) {
            /*
             * 8 is the string that stores the lat long point
             */
            //String place = dataToMap.get(i).get(8);
            val place = dataToMap[i].address
            googleMap.addMarker(
                MarkerOptions()
                    .position(getLatLngFromRadius(place))
                    .title(dataToMap[i].locname)
            )
        }
    }

    fun distanceSearch(center: LatLng, radius: Double): ArrayList<ArrayList<String>> {
        val dataInRange = ArrayList<ArrayList<String>>()
        for (i in retrofitData!!.indices) {
            val place = retrofitData[i][8]
            if (distanceFromPoint(center, getLatLngFromRadius(place)) < radius * ONEMILEINDEGREES) {
                dataInRange.add(retrofitData[i])
            }
        }
        return dataInRange
    }

    private fun getLatLngFromRadius(place: String?): LatLng {
        return LatLng(
            java.lang.Double.valueOf(place!!.substring(7, 25)), java.lang.Double.valueOf(
                place.substring(26, 43)
            )
        )
    }

    private fun distanceFromPoint(a: LatLng, b: LatLng): Double {
        return sqrt(
            (a.longitude - b.longitude).pow(2.0) + (a.latitude - b.latitude).pow(2.0)
        )
    }

    private fun connectToServer() {
        val retrofit = Retrofit.Builder().baseUrl("http://gsx2json.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(UPKService::class.java)
        val call = service.getData("1ITPdXilVjBOLG_rxaSxeWbK-esHrY8AX3pGvixAzDXo", "3", "")
        call.enqueue(object : Callback<UPKResponse> {
            override fun onResponse(call: Call<UPKResponse>, response: Response<UPKResponse>) {
                /*
                HAKEEM: added an arraylist data field that will get populated here
                 */
                Log.d("Success", "in there")
                Log.d("YOOO", "POJO" + response.body())
                val upkResponse = response.body()
                mUPKList = upkResponse!!.rows
                uPKReceyclerView!!.layoutManager = LinearLayoutManager(applicationContext)
                val adapter = UPKMapAdapter(mUPKList!!)
                uPKReceyclerView!!.adapter = adapter
                Log.d("Adapter", "adapter attached")

//                data = response.body().getRows();
//                System.out.println(data);
//                System.out.println("UPK DATA STREAM");
//                childCareAdapter = new ChildCareAdapter(response.body());
//                childCareRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//                childCareRecyclerView.setAdapter(adapter);
            }

            override fun onFailure(call: Call<UPKResponse>, t: Throwable) {
                Log.d("failure", "Failed to connect")
            }
        })
    }

    companion object {
        private const val ONEMILEINDEGREES = 1 / 69.0
        var view: View? = null
    }
}