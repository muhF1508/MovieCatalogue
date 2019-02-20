package id.co.maminfaruq.moviecatalogueapi.network;

import id.co.maminfaruq.moviecatalogueapi.model.ResponseNow;
import id.co.maminfaruq.moviecatalogueapi.model2.ResponseUp;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/3/movie/now_playing?api_key={API KEY}&language=en-US")
    Call<ResponseNow>getNowMovie();

    @GET("/3/movie/upcoming?api_key={API KEY}&language=en-US")
    Call<ResponseUp>getUpMovie();
}
