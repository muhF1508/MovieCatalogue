package id.co.maminfaruq.moviecatalogueapi.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import id.co.maminfaruq.moviecatalogueapi.R;
import id.co.maminfaruq.moviecatalogueapi.detailUp.DetaiUpPresenter;
import id.co.maminfaruq.moviecatalogueapi.detailUp.DetailUpContract;
import id.co.maminfaruq.moviecatalogueapi.model2.ResultsItem;

public class DetailUpActivity extends AppCompatActivity implements DetailUpContract.View {

    @BindView(R.id.imgDetail)
    CircleImageView imgDetail;
    @BindView(R.id.tvJudul)
    TextView tvJudul;
    @BindView(R.id.vote_average)
    TextView voteAverage;
    @BindView(R.id.vote_count)
    TextView voteCount;
    @BindView(R.id.original_language)
    TextView originalLanguage;
    @BindView(R.id.release_date)
    TextView releaseDate;
    @BindView(R.id.overview)
    TextView overview;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private final DetaiUpPresenter detaiUpPresenter = new DetaiUpPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_up);
        ButterKnife.bind(this);

        final Bundle bundle = getIntent().getExtras();
        detaiUpPresenter.getDetail(bundle);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                detaiUpPresenter.getDetail(bundle);
            }
        });
    }

    @Override
    public void showProgress() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showDataSingleMovie(ResultsItem resultsItem) {

        String title = resultsItem.getOriginalTitle();
        Double vote_average = resultsItem.getVoteAverage();
        String isi = resultsItem.getOverview();
        int vote_Count = resultsItem.getVoteCount();
        String Language = resultsItem.getOriginalLanguage();
        String date = resultsItem.getReleaseDate();
        String gambar =  resultsItem.getPosterPath();

        tvJudul.setText(title);
        voteAverage.setText(String.valueOf(vote_average));
        overview.setText(isi);
        voteCount.setText(String.valueOf(vote_Count));
        originalLanguage.setText(Language);
        releaseDate.setText(date);

        Glide.with(this).load("https://image.tmdb.org/t/p/original" + gambar).into(imgDetail);

        setTitle(title);

    }

    @Override
    public void ShowFailureMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
