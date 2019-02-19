package id.co.maminfaruq.moviecatalogueapi.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import id.co.maminfaruq.moviecatalogueapi.DetailNow.DetailContractNow;
import id.co.maminfaruq.moviecatalogueapi.DetailNow.DetailPresenterNow;
import id.co.maminfaruq.moviecatalogueapi.R;
import id.co.maminfaruq.moviecatalogueapi.model.ResultsItemNow;
import id.co.maminfaruq.moviecatalogueapi.now.NowPresenter;

public class DetailActivityNow extends AppCompatActivity implements DetailContractNow.View {


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

    private final DetailPresenterNow detailPresenterNow = new DetailPresenterNow(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_now);
        ButterKnife.bind(this);

        final Bundle bundle = getIntent().getExtras();
        detailPresenterNow.getDetail(bundle);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                detailPresenterNow.getDetail(bundle);
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
    public void showDataSingleMovie(ResultsItemNow resultsItemNow) {
        String title = resultsItemNow.getOriginalTitle();
        Double vote_average = resultsItemNow.getVoteAverage();
        String isi = resultsItemNow.getOverview();
        int vote_Count = resultsItemNow.getVoteCount();
        String Language = resultsItemNow.getOriginalLanguage();
        String date = resultsItemNow.getReleaseDate();
        String gambar =  resultsItemNow.getPosterPath();

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
