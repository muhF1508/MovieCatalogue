package id.co.maminfaruq.moviecatalogueapi.detailUp;

import android.os.Bundle;

import java.util.List;

import id.co.maminfaruq.moviecatalogueapi.model.ResultsItemNow;
import id.co.maminfaruq.moviecatalogueapi.model2.ResponseUp;
import id.co.maminfaruq.moviecatalogueapi.model2.ResultsItem;
import id.co.maminfaruq.moviecatalogueapi.network.ApiClient;
import id.co.maminfaruq.moviecatalogueapi.network.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetaiUpPresenter implements DetailUpContract.Presenter {

    private final DetailUpContract.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    private int id;

    public DetaiUpPresenter(DetailUpContract.View view) {
        this.view = view;
    }

    @Override
    public void getDetail(Bundle bundle) {

        if (bundle != null){
            id = bundle.getInt("id");
        }

        view.showProgress();
        Call<ResponseUp>call = apiInterface.getUpMovie();
        call.enqueue(new Callback<ResponseUp>() {
            @Override
            public void onResponse(Call<ResponseUp> call, Response<ResponseUp> response) {
                view.hideProgress();

                if (response.body() != null){
                    List<ResultsItem> resultsItemList = response.body().getResults();

                    ResultsItem resultsItem = resultsItemList.get(0);
                    view.showDataSingleMovie(resultsItem);
                }
            }

            @Override
            public void onFailure(Call<ResponseUp> call, Throwable t) {

            }
        });

    }
}
