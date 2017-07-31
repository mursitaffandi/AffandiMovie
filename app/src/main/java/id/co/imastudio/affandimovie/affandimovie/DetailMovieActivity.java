package id.co.imastudio.affandimovie.affandimovie;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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
import id.co.imastudio.affandimovie.affandimovie.adapter.RecycleItemReview;
import id.co.imastudio.affandimovie.affandimovie.adapter.RecycleItemTrailer;
import id.co.imastudio.affandimovie.affandimovie.global.ConfigUri;
import id.co.imastudio.affandimovie.affandimovie.global.contract.BaseMovie;
import id.co.imastudio.affandimovie.affandimovie.model.DataReviewParser;
import id.co.imastudio.affandimovie.affandimovie.model.DataTrailerParser;
import id.co.imastudio.affandimovie.affandimovie.model.dbItem.Movie;
import id.co.imastudio.affandimovie.affandimovie.util.CustomRecyclerviewItemClick;

import static id.co.imastudio.affandimovie.affandimovie.global.contract.BaseMovie.MovieListEntry.COLUMN_MOVIE_ID;
import static id.co.imastudio.affandimovie.affandimovie.global.contract.BaseMovie.MovieListEntry.CONTENT_URI;

public class DetailMovieActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    private
    Toolbar toolbar;
    @BindView(R.id.tvSynopsis)
    private
    TextView tvsynopsis;
    @BindView(R.id.tvRate)
    private
    TextView tvuserRating;
    @BindView(R.id.tvReleaseDate)
    private
    TextView tvreleaseDate;
    @BindView(R.id.ivPosterDetail)
    private
    ImageView ivmoviePoster;
    @BindView(R.id.iv_detail_header_parallax)
    private
    ImageView ivheaderBackdrop;
    @BindView(R.id.toolbar_layout)
    private
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.btnSaveFavorite)
    private
    ImageView btnFavorite;

    @BindView(R.id.rcV_detail_trailer)
    private
    RecyclerView rvTrailer;
    @BindView(R.id.rcV_detail_review)
    private
    RecyclerView rvReview;

    @BindView(R.id.tvdetailnoReviewView)
    private
    TextView tvNotFoundReview;

    private DataTrailerParser dataTrailerParcel;
    private DataReviewParser dataReviewParcel;
    private Movie dataMovieDB;

    private String urlRequestTrailer;
    private String urlRequestReview;
    private String sharedTrailerUrl = null;
    private String sharedTrailerTitle;
    private static boolean sIsFavorite = false;
    private static boolean sAvailableDB = false;

    private LinearLayoutManager verticalLayoutManager;
    private GsonBuilder gsonBuilder;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        dataMovieDB = getIntent().getParcelableExtra("parcel");
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
        sAvailableDB = checkAvalableMovie(String.valueOf(dataMovieDB.getMovie_id()));
        if (savedInstanceState == null) {
            if (sAvailableDB) {
                btnFavorite.setImageResource(R.drawable.fav_added);
                sIsFavorite = true;
            } else {
                btnFavorite.setImageResource(R.drawable.fav_unavailabled);
                sIsFavorite = false;
            }
        }

        urlRequestTrailer = ConfigUri.BASE_URL_DETAIL
                + String.valueOf(dataMovieDB.getMovie_id())
                + ConfigUri.TAIL_URL_TRAILER
                + ConfigUri.MY_TMDB_API_KEY;

        urlRequestReview = ConfigUri.BASE_URL_DETAIL
                + String.valueOf(dataMovieDB.getMovie_id())
                + ConfigUri.TAIL_URL_REVIEW
                + ConfigUri.MY_TMDB_API_KEY;

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
            Picasso.with(this)
                    .load(
                            ConfigUri.BASE_URL_IMAGE
                                    + ConfigUri.SIZE_POSTER_IMAGE_W342
                                    + dataMovieDB.getMovie_backdrop_path())
                    .placeholder(R.drawable.placeholder)
                    .into(ivheaderBackdrop);
        }
        sharedTrailerTitle = dataMovieDB.getMovie_title();

        collapsingToolbarLayout.setTitle(sharedTrailerTitle);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBarPlus1);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBarPlus1);

        Picasso.with(this)
                .load(
                        ConfigUri.BASE_URL_IMAGE
                                + ConfigUri.SIZE_POSTER_IMAGE_W342
                                + dataMovieDB.getMovie_poster_path())
                .placeholder(R.drawable.placeholder)
                .into(ivmoviePoster);

        tvsynopsis.setText(dataMovieDB.getMovie_overview());
        tvuserRating.setText(dataMovieDB.getMovie_rate());
        tvreleaseDate.setText(dataMovieDB.getMovie_release_date());

        verticalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvTrailer.setLayoutManager(verticalLayoutManager);
        rvReview.setLayoutManager(new LinearLayoutManager(this));
        requestDataTrailer();
        requestDataReview();

        rvTrailer.addOnItemTouchListener(new CustomRecyclerviewItemClick(this, new CustomRecyclerviewItemClick.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(
                        new Intent(
                                Intent.ACTION_VIEW
                                , Uri.parse(
                                ConfigUri.BASE_URL_VIDEO_YT
                                        + dataTrailerParcel.results.get(position).getKey()
                        )));
            }
        }));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sIsFavorite && !sAvailableDB) onClickAddTask();
                if (!sIsFavorite && sAvailableDB) onClickRemoveTask();
                finish();
            }
        });
    }

    @OnClick(R.id.btnSaveFavorite)
    void onClick(ImageView btnSaveFavorite) {
        if (sIsFavorite) {
            btnSaveFavorite.setImageResource(R.drawable.fav_unavailabled);
            sIsFavorite = false;
        } else {
            btnSaveFavorite.setImageResource(R.drawable.fav_added);
            sIsFavorite = true;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("favorite", sIsFavorite);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("favorite")) {
            sIsFavorite = savedInstanceState.getBoolean("favorite");
            if (sIsFavorite) btnFavorite.setImageResource(R.drawable.fav_added);
            else btnFavorite.setImageResource(R.drawable.fav_unavailabled);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_detail_share:
                String textShare = "";
                if (urlRequestTrailer != null)
                    textShare = sharedTrailerTitle + "/n" + sharedTrailerUrl;

                ShareCompat.IntentBuilder
                        .from(this)
                        .setType("text/plain")
                        .setChooserTitle("share to:")
                        .setText("Watch" + "/n" + textShare)
                        .startChooser();

                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Watch " + sharedTrailerTitle);
                startActivity(sharingIntent);
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
                    sharedTrailerUrl =
                            ConfigUri.BASE_URL_VIDEO_YT + dataTrailerParcel.results.get(0).getKey();
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
                    if (dataReviewParcel.getTotalResults() > 0) {
                        RecycleItemReview adaterItemReview = new RecycleItemReview(getApplicationContext(), dataReviewParcel.results);
                        rvReview.setAdapter(adaterItemReview);
                        adaterItemReview.notifyDataSetChanged();
                    } else tvNotFoundReview.setVisibility(View.VISIBLE);

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

    @Override
    protected void onDestroy() {
        if (sAvailableDB) Log.d("onDestroy", "true sAvailableDB ->"+dataMovieDB.getMovie_title());
        if (!sAvailableDB) Log.d("onDestroy", "false sAvailableDB ->"+dataMovieDB.getMovie_title());
        if (sIsFavorite) Log.d("onDestroy", "true sIsFavorite ->"+dataMovieDB.getMovie_title());
        if (!sIsFavorite) Log.d("onDestroy", "false sIsFavorite ->"+dataMovieDB.getMovie_title());
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (sIsFavorite && !sAvailableDB) onClickAddTask();
        if (!sIsFavorite && sAvailableDB) onClickRemoveTask();
        super.onBackPressed();
    }

    private boolean checkAvalableMovie(String movie_id) {
        String[] mProjection = new String[]{COLUMN_MOVIE_ID};
        String mSelection = COLUMN_MOVIE_ID + "= ?";
        String[] mSelectionArgs = new String[]{movie_id};

        Cursor cursor = getContentResolver().query(CONTENT_URI,
                mProjection,
                mSelection,
                mSelectionArgs,
                null);
        boolean avalableResult = cursor.getCount() > 0;
        cursor.close();
        return avalableResult;
    }

    private void onClickRemoveTask() {
        Uri uri = CONTENT_URI;
        uri = uri.buildUpon().appendPath(String.valueOf(dataMovieDB.getMovie_id())).build();
        getContentResolver().delete(uri, null, null);
        if (uri != null) {
            Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
        }
    }

    private void onClickAddTask() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BaseMovie.MovieListEntry.COLUMN_MOVIE_ID, dataMovieDB.getMovie_id());
        contentValues.put(BaseMovie.MovieListEntry.COLUMN_MOVIE_RATE, dataMovieDB.getMovie_rate());
        contentValues.put(BaseMovie.MovieListEntry.COLUMN_MOVIE_TITLE, dataMovieDB.getMovie_title());
        contentValues.put(BaseMovie.MovieListEntry.COLUMN_MOVIE_POSTER, dataMovieDB.getMovie_poster_path());
        contentValues.put(BaseMovie.MovieListEntry.COLUMN_MOVIE_SYNOPSIS, dataMovieDB.getMovie_overview());
        contentValues.put(BaseMovie.MovieListEntry.COLUMN_MOVIE_RELEASE, dataMovieDB.getMovie_release_date());
        contentValues.put(BaseMovie.MovieListEntry.COLUMN_MOVIE_BACKDROP, dataMovieDB.getMovie_backdrop_path());

        Uri uri = getContentResolver().insert(CONTENT_URI, contentValues);

        if (uri != null) {
            Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
        } else {
            Log.d("onClickAddTask", "insert success");
        }
    }
}
