package id.co.maminfaruq.moviecatalogueapi.now;

import android.content.Context;

import java.util.List;

import id.co.maminfaruq.moviecatalogueapi.model.ResultsItemNow;

public interface NowContract {
    interface View{
        void showProgress();
        void HideProgress();
        void showListMovie(List<ResultsItemNow>resultsItemNowList);
        void showFailureMessage(String msg);
    }

    interface Presenter{
        void getMovie(Context context);
    }
}
