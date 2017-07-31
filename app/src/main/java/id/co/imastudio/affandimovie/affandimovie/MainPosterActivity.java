package id.co.imastudio.affandimovie.affandimovie;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.imastudio.affandimovie.affandimovie.adapter.RecycleItemMainPoster;
import id.co.imastudio.affandimovie.affandimovie.adapter.favorite.ItemMovieAdapter;
import id.co.imastudio.affandimovie.affandimovie.global.ConfigUri;
import id.co.imastudio.affandimovie.affandimovie.global.PreferenceSettingOrder;
import id.co.imastudio.affandimovie.affandimovie.global.contract.BaseMovie;
import id.co.imastudio.affandimovie.affandimovie.model.DataMovieParser;
import id.co.imastudio.affandimovie.affandimovie.model.dbItem.Movie;
import id.co.imastudio.affandimovie.affandimovie.util.MessageEvent;
import id.co.imastudio.affandimovie.affandimovie.setting.SettingsActivity;
import id.co.imastudio.affandimovie.affandimovie.util.CustomRecyclerviewItemClick;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

public class MainPosterActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private DataMovieParser dataMovieParser;
    private String urlRequest;
    @BindView(R.id.rcView_main_poster)
    private
    RecyclerView rcViewMain;
    private final String TAG_MOVIE_PARCEL = "parcel";

    private static final int CURSOR_LOADER_ID = 0;
    private ItemMovieAdapter mItemMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_poster);
        ButterKnife.bind(this);

        GridLayoutManager manager = new GridLayoutManager(this, 2);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            manager.setSpanCount(2);
        else
            manager.setSpanCount(3);
        rcViewMain.setLayoutManager(manager);

        if (savedInstanceState == null) {
            requestDataPosterMovie();
        }

        rcViewMain.addOnItemTouchListener(new CustomRecyclerviewItemClick(getApplicationContext(), new CustomRecyclerviewItemClick.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Movie sendtoDetail;
                Intent toDetail = new Intent(view.getContext(), DetailMovieActivity.class);
                if (PreferenceSettingOrder.STATE_ORDER != 2) {
                    sendtoDetail = new Movie(dataMovieParser.results.get(position));
                }
                else sendtoDetail = mItemMovieAdapter.slistMovie.get(position);

                toDetail.putExtra("parcel", sendtoDetail);
                view.getContext().startActivity(toDetail);
            }
        }));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (PreferenceSettingOrder.STATE_ORDER != 2) {
            if (dataMovieParser != null) {
                outState.putParcelable(TAG_MOVIE_PARCEL, dataMovieParser);
            }
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey(TAG_MOVIE_PARCEL) && PreferenceSettingOrder.STATE_ORDER != 2) {
            dataMovieParser = savedInstanceState.getParcelable(TAG_MOVIE_PARCEL);
            RecycleItemMainPoster adaterItemPoster = new RecycleItemMainPoster(getApplicationContext(), dataMovieParser.results);
            rcViewMain.setAdapter(adaterItemPoster);
        } else {
            requestDataPosterMovie();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (PreferenceSettingOrder.STATE_ORDER == 2){
            getSupportLoaderManager().restartLoader(CURSOR_LOADER_ID, null, this);
        }
    }

    private void setUrlRequestBaseOnSetting() {
        switch (PreferenceSettingOrder.STATE_ORDER) {
            case 0:
                urlRequest = ConfigUri.URL_JSON_POPULAR_MOVIE + ConfigUri.MY_TMDB_API_KEY;
                break;
            case 1:
                urlRequest = ConfigUri.URL_JSON_TOPRATED_MOVIE + ConfigUri.MY_TMDB_API_KEY;
                break;
            case 2:
                urlRequest = "";
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_setting:
                goToSetting();
                return true;
            case R.id.menu_item_refresh:
                if (PreferenceSettingOrder.STATE_ORDER == 2)  getSupportLoaderManager().restartLoader(CURSOR_LOADER_ID, null, this);
                else requestDataPosterMovie();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void requestDataPosterMovie() {
        if (PreferenceSettingOrder.STATE_ORDER == 2) {
            mItemMovieAdapter = new ItemMovieAdapter(this);
            getSupportLoaderManager().initLoader(CURSOR_LOADER_ID, null, this);
            rcViewMain.setAdapter(mItemMovieAdapter);
        } else {
            setUrlRequestBaseOnSetting();
            if (isOnline()) {
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                StringRequest strRequest = new StringRequest(Request.Method.GET, urlRequest, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        dataMovieParser = gson.fromJson(response, DataMovieParser.class);
                        RecycleItemMainPoster adaterItemPoster = new RecycleItemMainPoster(getApplicationContext(), dataMovieParser.results);
                        adaterItemPoster.notifyDataSetChanged();
                        rcViewMain.setAdapter(adaterItemPoster);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Fail send request to server", Toast.LENGTH_LONG).show();
                    }
                });
                requestQueue.add(strRequest);
            } else {
                Toast.makeText(getApplicationContext(), "You are offline", Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private void goToSetting() {
        Intent goSetting = new Intent(this, SettingsActivity.class);
        startActivity(goSetting);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event.is_apply_preference) {
            requestDataPosterMovie();
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<Cursor>(this) {
            Cursor mTaskData = null;

            @Override
            public Cursor loadInBackground() {
                return null;
            }

            @Override
            protected void onStartLoading() {
                if (mTaskData != null) {
                    deliverResult(mTaskData);
                    Log.d("M_onStartLoading", "start load data.");

                } else {
                    forceLoad();
                    Log.d("M_onStartLoading", "Forece load data.");
                }
            }

            @Override
            protected Cursor onLoadInBackground() {
                try {
                    return getContentResolver()
                            .query(
                                    BaseMovie.MovieListEntry.CONTENT_URI,
                                    null,
                                    null,
                                    null,
                                    BaseMovie.MovieListEntry.COLUMN_MOVIE_TITLE
                            );
                } catch (Exception e) {
                    Log.e("M_onLoadInBackground", "Failed to asynchronously load data.");
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            public void deliverResult(Cursor data) {
                mTaskData = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mItemMovieAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mItemMovieAdapter.swapCursor(null);
    }
}
