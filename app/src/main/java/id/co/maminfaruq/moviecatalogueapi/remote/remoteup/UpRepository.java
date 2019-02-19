package id.co.maminfaruq.moviecatalogueapi.remote.remoteup;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.util.List;

import id.co.maminfaruq.moviecatalogueapi.database.MovieDatabase;
import id.co.maminfaruq.moviecatalogueapi.database.MovieUpLocalDataSource;
import id.co.maminfaruq.moviecatalogueapi.model2.ResultsItem;

public class UpRepository implements UpDataSource{
    private final UpRemoteDataSource remoteDataSource;
    private final MovieUpLocalDataSource movieLocalDataSource;
    private MovieDatabase movieDatabase;

    public UpRepository(UpRemoteDataSource remoteDataSource, MovieUpLocalDataSource movieLocalDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.movieLocalDataSource = movieLocalDataSource;
    }


    @Override
    public void getListMovieUp(Context context, final GetListUpCallback callback) {
        NetworkInfo info = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        movieDatabase = MovieDatabase.getMovieDatabase(context);
        if (movieDatabase.movieDao().selectUp().size() != 0){
            Toast.makeText(context, "Use database Local", Toast.LENGTH_SHORT).show();
            movieLocalDataSource.getListMovieUp(context, new GetListUpCallback() {
                @Override
                public void onSuccess(List<ResultsItem> data) {
                    callback.onSuccess(data);
                }

                @Override
                public void onFailed(String errorMessage) {
                    callback.onFailed(errorMessage);
                }
            });
        }else if (info != null && info.isConnected()){
            Toast.makeText(context, "Use database cloud", Toast.LENGTH_SHORT).show();
            remoteDataSource.getListMovieUp(context, new GetListUpCallback() {
                @Override
                public void onSuccess(List<ResultsItem> data) {
                    callback.onSuccess(data);
                    movieDatabase.movieDao().insertUp(data);
                }

                @Override
                public void onFailed(String errorMessage) {
                    callback.onFailed(errorMessage);
                }
            });
        }
    }
}
