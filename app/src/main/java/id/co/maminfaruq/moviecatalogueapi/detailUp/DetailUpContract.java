package id.co.maminfaruq.moviecatalogueapi.detailUp;

import android.os.Bundle;

import id.co.maminfaruq.moviecatalogueapi.model.ResultsItemNow;
import id.co.maminfaruq.moviecatalogueapi.model2.ResultsItem;

public interface DetailUpContract {

    interface View{
        void showProgress();
        void hideProgress();
        void showDataSingleMovie(ResultsItem resultsItem);
        void ShowFailureMessage(String msg);
    }

    interface Presenter{
        void getDetail(Bundle bundle);
    }
}
