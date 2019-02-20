package id.co.maminfaruq.moviecatalogueapi.view;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import id.co.maminfaruq.moviecatalogueapi.Injection;
import id.co.maminfaruq.moviecatalogueapi.R;
import id.co.maminfaruq.moviecatalogueapi.adapter.NowAdapter;
import id.co.maminfaruq.moviecatalogueapi.model.ResultsItemNow;
import id.co.maminfaruq.moviecatalogueapi.now.NowContract;
import id.co.maminfaruq.moviecatalogueapi.now.NowPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayingFragment extends Fragment implements NowContract.View{


    SwipeRefreshLayout swipeRefreshLayoutNow;
    RecyclerView recyclerView;

    private final NowPresenter nowPresenter = new NowPresenter(this,Injection.provideRepository());

    private final List<ResultsItemNow>nowList = new ArrayList<>();

    public NowPlayingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
//        swipeRefreshLayout = Objects.requireNonNull(getView()).findViewById(R.id.swipe_refresh_now);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//

        swipeRefreshLayoutNow = Objects.requireNonNull(getActivity()).findViewById(R.id.swipe_refresh_now);
        recyclerView = getActivity().findViewById(R.id.rv_now);
        nowPresenter.getMovie(getActivity());
        initAdapter();
        swipeRefreshLayoutNow.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                nowPresenter.getMovie(getActivity());
            }
        });

    }

    private void initAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new NowAdapter(getContext(),nowList));
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void showProgress() {
        swipeRefreshLayoutNow.setRefreshing(true);
    }

    @Override
    public void HideProgress() {
        swipeRefreshLayoutNow.setRefreshing(false);
    }

    @Override
    public void showListMovie(List<ResultsItemNow> resultsItemNowList) {
        nowList.addAll(resultsItemNowList);
    }

    @Override
    public void showFailureMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

}
