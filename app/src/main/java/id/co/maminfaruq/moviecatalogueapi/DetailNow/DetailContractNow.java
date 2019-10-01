package id.co.maminfaruq.moviecatalogueapi.DetailNow;

import android.os.Bundle;

import id.co.maminfaruq.moviecatalogueapi.model.ResultsItemNow;

public interface DetailContractNow {
    interface View{
        void showProgress();
        void hideProgress();
        void showDataSingleMovie(ResultsItemNow resultsItemNow);
        void ShowFailureMessage(String msg);
    }
    
//     Hey dddnewdibw euwu wnkcxaweniubace jewnd

    interface Presenter{
        void getDetail(Bundle bundle);
    }
}
