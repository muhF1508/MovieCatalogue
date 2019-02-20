package id.co.maminfaruq.moviecatalogueapi.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import id.co.maminfaruq.moviecatalogueapi.Injection;
import id.co.maminfaruq.moviecatalogueapi.R;
import id.co.maminfaruq.moviecatalogueapi.adapter.NowAdapter;
import id.co.maminfaruq.moviecatalogueapi.adapter.UpAdapter;
import id.co.maminfaruq.moviecatalogueapi.model2.ResultsItem;
import id.co.maminfaruq.moviecatalogueapi.up.UpContract;
import id.co.maminfaruq.moviecatalogueapi.up.UpPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingFragment extends Fragment implements UpContract.View {


    private final UpPresenter upPresenter = new UpPresenter(this, Injection.provideDataRepository());
    private final List<ResultsItem> resultsItemList = new ArrayList<>();
    @BindView(R.id.rv_up)
    RecyclerView rvUp;
    @BindView(R.id.swipe_refresh_up)
    SwipeRefreshLayout swipeRefreshUp;

    public UpcomingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        upPresenter.getMovie(getActivity());
        initAdapter();
        swipeRefreshUp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                upPresenter.getMovie(getActivity());
            }
        });
    }

    private void initAdapter() {
        rvUp.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvUp.setHasFixedSize(true);
        rvUp.setAdapter(new UpAdapter(getContext(),resultsItemList));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void showProgress() {
        swipeRefreshUp.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefreshUp.setRefreshing(false);
    }

    @Override
    public void showDataSingleMovie(List<ResultsItem> resultsItems) {
        resultsItemList.addAll(resultsItems);

    }

    @Override
    public void ShowFailureMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

}
