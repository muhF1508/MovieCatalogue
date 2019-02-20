package id.co.maminfaruq.moviecatalogueapi.remote;

import android.content.Context;

import id.co.maminfaruq.moviecatalogueapi.model.ResponseNow;
import id.co.maminfaruq.moviecatalogueapi.network.ApiClient;
import id.co.maminfaruq.moviecatalogueapi.network.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRemoteDataSource implements MovieDataSource {

    private final ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    @Override
    public void getListMovie(Context context, final GetListMovieCallback callback) {
        Call<ResponseNow>call = apiInterface.getNowMovie();
        call.enqueue(new Callback<ResponseNow>() {
            @Override
            public void onResponse(Call<ResponseNow> call, Response<ResponseNow> response) {
                if (response.body() != null){
                    callback.onSuccess(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<ResponseNow> call, Throwable t) {
                callback.onFailed(t.toString());
            }
        });
    }
}
