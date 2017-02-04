package nyc.c4q.wesniemarcelin.resourcedapp.backend;

import nyc.c4q.wesniemarcelin.resourcedapp.model.UPKResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UPKService {
    @GET("api/views/sd93-evwm/rows.json")
    Call<UPKResponse> getData();
}