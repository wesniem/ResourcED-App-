package nyc.c4q.wesniemarcelin.resourcedapp.backend;

import nyc.c4q.wesniemarcelin.resourcedapp.model.UPKResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UPKService {
    @GET("api")
    Call<UPKResponse> getData(@Query("id") String id, @Query("sheet") String sheet, @Query("q") String q);
}