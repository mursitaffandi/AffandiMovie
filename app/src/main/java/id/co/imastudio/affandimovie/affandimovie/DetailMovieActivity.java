package id.co.imastudio.affandimovie.affandimovie;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.co.imastudio.affandimovie.affandimovie.adapter.RecycleItemMainPoster;
import id.co.imastudio.affandimovie.affandimovie.adapter.review.RecycleItemReview;
import id.co.imastudio.affandimovie.affandimovie.adapter.trailer.RecycleItemTrailer;
import id.co.imastudio.affandimovie.affandimovie.model.DataMovieParser;
import id.co.imastudio.affandimovie.affandimovie.model.review.DataReviewParser;
import id.co.imastudio.affandimovie.affandimovie.model.trailer.DataTrailerParser;

public class DetailMovieActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvSynopsis)
    TextView tvsynopsis;
    @BindView(R.id.tvRate)
    TextView tvuserRating;
    @BindView(R.id.tvReleaseDate)
    TextView tvreleaseDate;
    @BindView(R.id.ivPosterDetail)
    ImageView ivmoviePoster;
    @BindView(R.id.iv_detail_header_parallax)
    ImageView ivheaderBackdrop;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    RecyclerView rvTrailer;
    @BindView(R.id.rcV_detail_review)
    RecyclerView rvReview;

    private DataMovieParser.Result dataMoviewParcel;
    private DataTrailerParser dataTrailerParcel;
    private DataReviewParser dataReviewParcel;

    private String urlRequestTrailer;
    private String urlRequestReview;

    LinearLayoutManager trailerLayoutManager;
    GsonBuilder gsonBuilder;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        rvTrailer = (RecyclerView)findViewById(R.id.rcV_detail_trailer);
        dataMoviewParcel = getIntent().getParcelableExtra("parcel");
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        urlRequestTrailer = this.getResources().getString(R.string.base_url_detail)
                + String.valueOf(dataMoviewParcel.getId())
                + this.getResources().getString(R.string.tail_url_trailer)
                + this.getResources().getString(R.string.MY_TMDB_API_KEY);

        urlRequestReview = this.getResources().getString(R.string.base_url_detail)
                + String.valueOf(dataMoviewParcel.getId())
                + this.getResources().getString(R.string.tail_url_review)
                + this.getResources().getString(R.string.MY_TMDB_API_KEY);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
            Picasso.with(this)
                    .load(this.getResources().getString(R.string.base_url_image) + this.getResources().getString(R.string.size_poster_image_w342) + dataMoviewParcel.getBackdropPath())
                    .placeholder(R.drawable.image_sampel)
                    .into(ivheaderBackdrop);
        }
        collapsingToolbarLayout.setTitle(dataMoviewParcel.getOriginalTitle());
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBarPlus1);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBarPlus1);

        Picasso.with(this)
                .load(this.getResources().getString(R.string.base_url_image) + this.getResources().getString(R.string.size_poster_image_w342) + dataMoviewParcel.getPosterPath())
                .placeholder(R.drawable.image_sampel)
                .into(ivmoviePoster);
        tvsynopsis.setText(dataMoviewParcel.getOverview());
        tvuserRating.setText(String.valueOf(dataMoviewParcel.getVoteAverage()));
        tvreleaseDate.setText(dataMoviewParcel.getReleaseDate());

        trailerLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rvTrailer.setLayoutManager(trailerLayoutManager);

        requestDataReview();
        requestDataTrailer();
    }

    @OnClick(R.id.btnSaveFavorite)
    void onClick(Button btnSaveFavorite) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.menu_item_detail_share:
                //goToSetting();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void requestDataTrailer() {
        if (isOnline()) {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest strRequest = new StringRequest(Request.Method.GET, urlRequestTrailer, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    dataTrailerParcel = gson.fromJson(response, DataTrailerParser.class);
                    RecycleItemTrailer adaterItemTrailer = new RecycleItemTrailer(getApplicationContext(), dataTrailerParcel.results);
                    rvTrailer.setAdapter(adaterItemTrailer);
                    adaterItemTrailer.notifyDataSetChanged();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Fail send request to server", Toast.LENGTH_LONG).show();
                }
            });
            requestQueue.add(strRequest);
        }
    }

    private void requestDataReview() {
        if (isOnline()) {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest strRequest = new StringRequest(Request.Method.GET, urlRequestReview, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    dataReviewParcel = gson.fromJson(response, DataReviewParser.class);
                    RecycleItemReview adaterItemReview = new RecycleItemReview(getApplicationContext(), dataReviewParcel.results);
                    rvTrailer.setAdapter(adaterItemReview);
                    adaterItemReview.notifyDataSetChanged();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Fail send request to server", Toast.LENGTH_LONG).show();
                }
            });
            requestQueue.add(strRequest);
        }
    }

    private boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
