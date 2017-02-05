package nyc.c4q.wesniemarcelin.resourcedapp.backend.places_gmaps;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by hakeemsackes-bramble on 2/4/17.
 */

public interface PlaceService {
    @GET("maps/api/place/textsearch/")
    Call<PlaceResponse> getPlace();
}
