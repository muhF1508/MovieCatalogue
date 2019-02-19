package id.co.maminfaruq.moviecatalogueapi.up;


import android.content.Context;

import java.util.List;

import id.co.maminfaruq.moviecatalogueapi.model2.ResultsItem;

public interface UpContract {
    interface View{
        void showProgress();
        void hideProgress();
        void showDataSingleMovie(List<ResultsItem>resultsItems);
        void ShowFailureMessage(String msg);
    }

    interface Presenter{
        void getMovie(Context context);
    }
}
