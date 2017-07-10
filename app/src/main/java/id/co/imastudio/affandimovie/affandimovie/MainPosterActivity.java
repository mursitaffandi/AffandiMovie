package id.co.imastudio.affandimovie.affandimovie;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import id.co.imastudio.affandimovie.affandimovie.adapter.ItemMainAdapter;
import id.co.imastudio.affandimovie.affandimovie.constant.PreferenceSettingOrder;
import id.co.imastudio.affandimovie.affandimovie.helper.DataMovieParser;
import id.co.imastudio.affandimovie.affandimovie.model.MovieItem;
import id.co.imastudio.affandimovie.affandimovie.setting.SettingsActivity;

public class MainPosterActivity extends AppCompatActivity {
    DataMovieParser dataMovieParser;
    int statusOrder = PreferenceSettingOrder.STATE_ORDER;
    String urlRequest;
    GridView gvPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_poster);
        gvPoster = (GridView) findViewById(R.id.gridView_fragment_main_poster);
        requestDataPosterMovie();
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
            case R.id.menu_item_resfresh:
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

                    Log.d("respons", response);
                    Log.d("url", urlRequest);
                    Log.d("Order", String.valueOf(PreferenceSettingOrder.STATE_ORDER));

                /*mainAdapterItem = new AdapterItem(getApplicationContext(), itemObject.listplanet);
                rclListItem.setAdapter(mainAdapterItem);*/
                    ItemMainAdapter adapterPoster = new ItemMainAdapter(getApplicationContext(), dataMovieParser.results);

                    gvPoster.setAdapter(adapterPoster);
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

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private void goToSetting() {
        Intent goSetting = new Intent(this, SettingsActivity.class);
        startActivity(goSetting);
    }
}
