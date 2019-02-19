package id.co.maminfaruq.moviecatalogueapi.remote;

import android.content.Context;

import java.util.List;

import id.co.maminfaruq.moviecatalogueapi.model.ResultsItemNow;

public interface MovieDataSource {

    void getListMovie(Context context, GetListMovieCallback callback);

    interface GetListMovieCallback{
        void onSucces(List<ResultsItemNow> data);
        void onFailed(String errorMessage);
    }
}
