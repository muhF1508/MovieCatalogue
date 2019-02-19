package id.co.maminfaruq.moviecatalogueapi.remote.remoteup;

import android.content.Context;

import id.co.maminfaruq.moviecatalogueapi.model2.ResponseUp;
import id.co.maminfaruq.moviecatalogueapi.network.ApiClient;
import id.co.maminfaruq.moviecatalogueapi.network.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpRemoteDataSource implements UpDataSource{

    private final ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);


    @Override
    public void getListMovieUp(Context context, final GetListUpCallback callback) {
        Call<ResponseUp>call = apiInterface.getUpMovie();
        call.enqueue(new Callback<ResponseUp>() {
            @Override
            public void onResponse(Call<ResponseUp> call, Response<ResponseUp> response) {
                if (response.body() != null){
                    callback.onSuccess(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<ResponseUp> call, Throwable t) {
                callback.onFailed(t.toString());
            }
        });
    }
}
