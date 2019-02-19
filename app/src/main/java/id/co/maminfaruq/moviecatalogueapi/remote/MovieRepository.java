package id.co.maminfaruq.moviecatalogueapi.remote;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.util.List;

import id.co.maminfaruq.moviecatalogueapi.database.MovieDatabase;
import id.co.maminfaruq.moviecatalogueapi.database.MovieLocalDataSource;
import id.co.maminfaruq.moviecatalogueapi.model.ResultsItemNow;

public class MovieRepository implements MovieDataSource {

    private final MovieRemoteDataSource remoteDataSource;
    private final MovieLocalDataSource movieLocalDataSource;
    private MovieDatabase movieDatabase;

    public MovieRepository(MovieRemoteDataSource remoteDataSource, MovieLocalDataSource movieLocalDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.movieLocalDataSource = movieLocalDataSource;
    }


    @Override
    public void getListMovie(Context context, final GetListMovieCallback callback) {
        NetworkInfo info = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        movieDatabase = MovieDatabase.getMovieDatabase(context);
        if (movieDatabase.movieDao().select().size() != 0){
            Toast.makeText(context, "Use database Local", Toast.LENGTH_SHORT).show();
            movieLocalDataSource.getListMovie(context, new GetListMovieCallback() {
                @Override
                public void onSucces(List<ResultsItemNow> data) {
                    callback.onSucces(data);
                }

                @Override
                public void onFailed(String errorMessage) {
                    callback.onFailed(errorMessage);
                }
            });

        }else if (info != null && info.isConnected()){
            Toast.makeText(context, "Use database cloud", Toast.LENGTH_SHORT).show();
            remoteDataSource.getListMovie(context, new GetListMovieCallback() {
                @Override
                public void onSucces(List<ResultsItemNow> data) {
                    callback.onSucces(data);
                    movieDatabase.movieDao().insert(data);
                }

                @Override
                public void onFailed(String errorMessage) {
                    callback.onFailed(errorMessage);
                }
            });
        }else {
            callback.onSucces(null);
            callback.onFailed("");
        }
    }
}
