package id.co.imastudio.affandimovie.affandimovie.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

import id.co.imastudio.affandimovie.affandimovie.R;
import id.co.imastudio.affandimovie.affandimovie.helper.DataMovieParser;
import id.co.imastudio.affandimovie.affandimovie.model.MovieItem;


/**
 * Created by Ingat Mati on 09/07/2017.
 */

public class ItemMainAdapter extends BaseAdapter {
    Context context;
    String url_image;
    List<DataMovieParser.Result> movies;

    public ItemMainAdapter(Context context, List<DataMovieParser.Result> movies) {
        this.context = context;
        this.movies = movies;
        url_image = context.getResources().getString(R.string.base_url_image) + context.getResources().getString(R.string.size_poster_image_recomended);
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.item_poster_movie, parent, false);
        }

        ImageView ivPoster = (ImageView) convertView.findViewById(R.id.imgV_item_poster);
        Picasso.with(context).load(url_image + movies.get(position).getPosterPath()).into(ivPoster);
        return convertView;
    }
}
