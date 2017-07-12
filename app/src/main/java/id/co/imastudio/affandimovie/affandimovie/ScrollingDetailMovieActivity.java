package id.co.imastudio.affandimovie.affandimovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ScrollingDetailMovieActivity extends AppCompatActivity {
    private String originalTitle, urlmoviePoster, synopsis, userRating, releaseDate;
    Intent fromListMovie;

    private TextView tvsynopsis,
            tvuserRating,
            tvreleaseDate;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView ivmoviePoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fromListMovie = getIntent();

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        originalTitle = fromListMovie.getStringExtra("title");
        urlmoviePoster = fromListMovie.getStringExtra("poster_path");
        synopsis = fromListMovie.getStringExtra("overview");
        userRating = fromListMovie.getStringExtra("vote_average");
        releaseDate = fromListMovie.getStringExtra("release_date");

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        tvsynopsis = (TextView) findViewById(R.id.tvSynopsis);
        tvuserRating = (TextView) findViewById(R.id.tvRate);
        tvreleaseDate = (TextView) findViewById(R.id.tvReleaseDate);
        ivmoviePoster = (ImageView) findViewById(R.id.ivPosterDetail);

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
