package id.co.maminfaruq.moviecatalogueapi.now;

import android.content.Context;

import java.util.List;

import id.co.maminfaruq.moviecatalogueapi.model.ResultsItemNow;
import id.co.maminfaruq.moviecatalogueapi.remote.MovieDataSource;
import id.co.maminfaruq.moviecatalogueapi.remote.MovieRepository;

public class NowPresenter implements NowContract.Presenter {
    //TODO membuat variable dan constructor untuk menerima context dari mainActivity
    //agar dapat mengakses method yang ada di mainActivity
    private final NowContract.View view;
    private final MovieRepository repository;

    public NowPresenter(NowContract.View view, MovieRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void getMovie(Context context) {
        view.showProgress();
        repository.getListMovie(context, new MovieDataSource.GetListMovieCallback() {
            @Override
            public void onSuccess(List<ResultsItemNow> data) {
                view.HideProgress();
                view.showListMovie(data);
            }

            @Override
            public void onFailed(String errorMessage) {
                view.HideProgress();
                view.showFailureMessage(errorMessage);
            }
        });
    }
}
