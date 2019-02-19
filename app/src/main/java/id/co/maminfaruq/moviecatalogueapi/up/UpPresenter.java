package id.co.maminfaruq.moviecatalogueapi.up;

import android.content.Context;

import java.util.List;

import id.co.maminfaruq.moviecatalogueapi.model2.ResultsItem;
import id.co.maminfaruq.moviecatalogueapi.remote.remoteup.UpDataSource;
import id.co.maminfaruq.moviecatalogueapi.remote.remoteup.UpRepository;

public class UpPresenter implements UpContract.Presenter {

    private final UpContract.View view;
    private final UpRepository repository;


    public UpPresenter(UpContract.View view, UpRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void getMovie(Context context) {
        view.showProgress();
        repository.getListMovieUp(context, new UpDataSource.GetListUpCallback() {
            @Override
            public void onSuccess(List<ResultsItem> data) {
                view.hideProgress();
                view.showDataSingleMovie(data);
            }

            @Override
            public void onFailed(String errorMessage) {
                view.hideProgress();
                view.ShowFailureMessage(errorMessage);
            }
        });
    }
}
