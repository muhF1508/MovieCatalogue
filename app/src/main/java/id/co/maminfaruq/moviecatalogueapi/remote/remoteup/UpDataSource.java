package id.co.maminfaruq.moviecatalogueapi.remote.remoteup;

import android.content.Context;

import java.util.List;

import id.co.maminfaruq.moviecatalogueapi.model2.ResultsItem;

public interface UpDataSource {

    void getListMovieUp(Context context, GetListUpCallback callback);

    interface GetListUpCallback{
        void onSuccess(List<ResultsItem>data);
        void onFailed(String errorMessage);
    }
}
