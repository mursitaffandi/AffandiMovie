package id.co.imastudio.affandimovie.affandimovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScrollingDetailMovieActivity extends AppCompatActivity {
    private Intent fromListMovie;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tvSynopsis) TextView tvsynopsis;
    @BindView(R.id.tvRate) TextView tvuserRating;
    @BindView(R.id.tvReleaseDate) TextView tvreleaseDate;
    @BindView(R.id.ivPosterDetail) ImageView ivmoviePoster;
    @BindView(R.id.toolbar_layout) CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        fromListMovie = getIntent();

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e){
            e.printStackTrace();
        }

        String originalTitle = fromListMovie.getStringExtra("title");
        String urlmoviePoster = fromListMovie.getStringExtra("poster_path");
        String synopsis = fromListMovie.getStringExtra("overview");
        String userRating = fromListMovie.getStringExtra("vote_average");
        String releaseDate = fromListMovie.getStringExtra("release_date");

        collapsingToolbarLayout.setTitle(originalTitle);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBarPlus1);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBarPlus1);
        // collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        Picasso.with(this)
                .load(urlmoviePoster)
                .into(ivmoviePoster);
        tvsynopsis.setText(synopsis);
        tvuserRating.setText(userRating);
        tvreleaseDate.setText(releaseDate);
    }
}
