package id.co.maminfaruq.moviecatalogueapi.database;

import android.content.Context;

import id.co.maminfaruq.moviecatalogueapi.remote.remoteup.UpDataSource;

public class MovieUpLocalDataSource implements UpDataSource {
    @Override
    public void getListMovieUp(Context context, GetListUpCallback callback) {
        MovieDatabase movieDatabase = MovieDatabase.getMovieDatabase(context);
        if (movieDatabase.movieDao().selectUp() != null){
            callback.onSuccess(movieDatabase.movieDao().selectUp());
        }else {
            callback.onFailed("No database");
        }
    }
}
