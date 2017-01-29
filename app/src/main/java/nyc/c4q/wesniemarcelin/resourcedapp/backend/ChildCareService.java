package nyc.c4q.wesniemarcelin.resourcedapp.backend;

import nyc.c4q.wesniemarcelin.resourcedapp.model.ChildCareResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ChildCareService {
    @GET("cgi-bin/1_11_2017_exam.pl")
    Call<ChildCareResponse> getChildCareData();
}