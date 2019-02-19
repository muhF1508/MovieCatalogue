package id.co.maminfaruq.moviecatalogueapi.database;

import android.content.Context;

import id.co.maminfaruq.moviecatalogueapi.remote.MovieDataSource;

public class MovieLocalDataSource implements MovieDataSource {
    @Override
    public void getListMovie(Context context, GetListMovieCallback callback) {
        MovieDatabase movieDatabase = MovieDatabase.getMovieDatabase(context);
        if (movieDatabase.movieDao().select() != null){
            callback.onSucces(movieDatabase.movieDao().select());
        }else {
            callback.onFailed("No database");
        }
    }
}
