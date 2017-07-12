package id.co.imastudio.affandimovie.affandimovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    String originalTitle, urlmoviePoster, synopsis, userRating, releaseDate;
    Intent fromListMovie;

    TextView tvoriginalTitle,
            tvsynopsis,
            tvuserRating,
            tvreleaseDate;

    ImageView ivmoviePoster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fromListMovie = getIntent();

        originalTitle = fromListMovie.getStringExtra("title");
        urlmoviePoster = fromListMovie.getStringExtra("poster_path");
        synopsis = fromListMovie.getStringExtra("overview");
        userRating = fromListMovie.getStringExtra("vote_average");
        releaseDate = fromListMovie.getStringExtra("release_date");

        tvoriginalTitle = (TextView) findViewById(R.id.tvDetailTitleMovie);
        tvsynopsis = (TextView) findViewById(R.id.tvSvnopsis);
        tvuserRating = (TextView) findViewById(R.id.tvRate);
        tvreleaseDate = (TextView) findViewById(R.id.tvReleaseDate);
        ivmoviePoster = (ImageView) findViewById(R.id.ivPosterDetail);

        Picasso.with(this)
                .load(urlmoviePoster)
                .into(ivmoviePoster);
        tvoriginalTitle.setText(originalTitle);
        tvsynopsis.setText(synopsis);
        tvuserRating.setText(userRating);
        tvreleaseDate.setText(releaseDate);
    }

}
