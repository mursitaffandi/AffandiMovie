package id.co.imastudio.affandimovie.affandimovie;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
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
import id.co.imastudio.affandimovie.affandimovie.global.PreferenceSettingOrder;
import id.co.imastudio.affandimovie.affandimovie.model.DataMovieParser;
import id.co.imastudio.affandimovie.affandimovie.model.MessageEvent;
import id.co.imastudio.affandimovie.affandimovie.setting.SettingsActivity;
import id.co.imastudio.affandimovie.affandimovie.util.CustomRecyclerviewItemClick;


public class MainPosterActivity extends AppCompatActivity {
    private DataMovieParser dataMovieParser;
    private String urlRequest;
    @BindView(R.id.rcView_main_poster)
    RecyclerView rcViewMain;
    private final String TAG_MOVIE_PARCEL = "parcel_movie";

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

        if (savedInstanceState == null || dataMovieParser == null) {
            requestDataPosterMovie();
        }
        rcViewMain.addOnItemTouchListener(new CustomRecyclerviewItemClick(getApplicationContext(), new CustomRecyclerviewItemClick.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent toDetail = new Intent(view.getContext(), DetailMovieActivity.class);
                toDetail.putExtra("parcel", dataMovieParser.results.get(position));
                view.getContext().startActivity(toDetail);
            }
        }));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (dataMovieParser != null)
            outState.putParcelable(TAG_MOVIE_PARCEL, dataMovieParser);
}
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null && dataMovieParser != null) {
            // Restore value of members from saved state
            dataMovieParser = savedInstanceState.getParcelable(TAG_MOVIE_PARCEL);
            RecycleItemMainPoster adaterItemPoster = new RecycleItemMainPoster(getApplicationContext(), dataMovieParser.results);
            rcViewMain.setAdapter(adaterItemPoster);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setUrlRequestBaseOnSetting() {
        urlRequest =
                (PreferenceSettingOrder.STATE_ORDER == 0)
                        ? getResources().getString(R.string.url_json_popular_movie)
                        + getResources().getString(R.string.MY_TMDB_API_KEY)
                        : getResources().getString(R.string.url_json_toprated_movie)
                        + getResources().getString(R.string.MY_TMDB_API_KEY);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.menu_item_setting:
                goToSetting();
                return true;
            case R.id.menu_item_refresh:
                requestDataPosterMovie();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void requestDataPosterMovie() {
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

    private boolean isOnline() {
        boolean mobileAndWifi = false;
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if(cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            mobileAndWifi = true;
        }
        return netInfo != null && netInfo.isConnected() && mobileAndWifi;//OrConnecting();

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
}
