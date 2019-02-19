package id.co.maminfaruq.moviecatalogueapi.DetailNow;

import android.os.Bundle;

import java.util.List;

import id.co.maminfaruq.moviecatalogueapi.model.ResponseNow;
import id.co.maminfaruq.moviecatalogueapi.model.ResultsItemNow;
import id.co.maminfaruq.moviecatalogueapi.network.ApiClient;
import id.co.maminfaruq.moviecatalogueapi.network.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenterNow implements DetailContractNow.Presenter {

    private final DetailContractNow.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public DetailPresenterNow(DetailContractNow.View view) {
        this.view = view;
    }

    @Override
    public void getDetail(Bundle bundle) {
        if (bundle != null){
            int id = bundle.getInt("id");
        }

        view.showProgress();
        Call<ResponseNow>call = apiInterface.getNowMovie();
        call.enqueue(new Callback<ResponseNow>() {
            @Override
            public void onResponse(Call<ResponseNow> call, Response<ResponseNow> response) {
                view.hideProgress();

                if (response.body() != null){
                    List<ResultsItemNow>resultsItemNowList = response.body().getResults();

                    ResultsItemNow resultsItemNow = resultsItemNowList.get(0);
                    view.showDataSingleMovie(resultsItemNow);
                }
            }

            @Override
            public void onFailure(Call<ResponseNow> call, Throwable t) {
                view.hideProgress();
                view.ShowFailureMessage(t.getMessage());
            }
        });

    }
}
