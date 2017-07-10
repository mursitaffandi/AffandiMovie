package id.co.imastudio.affandimovie.affandimovie.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import id.co.imastudio.affandimovie.affandimovie.R;
import id.co.imastudio.affandimovie.affandimovie.helper.DataMovieParser;
import id.co.imastudio.affandimovie.affandimovie.helper.Result;
import id.co.imastudio.affandimovie.affandimovie.model.MovieItem;


/**
 * Created by Ingat Mati on 09/07/2017.
 */

public class ItemMainAdapter extends ArrayAdapter<MovieItem> {
    Context context;
    ArrayList<MovieItem> movies;
    String url_image;
        public ItemMainAdapter(Context context, ArrayList<MovieItem> movies) {
            super(context, 0, movies);
            url_image = context.getResources().getString(R.string.base_url_image)+context.getResources().getString(R.string.size_poster_image_recomended);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            MovieItem movieItem = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_poster_movie, parent, false);
            }

            ImageView ivPoster = (ImageView)convertView.findViewById(R.id.imgV_item_poster);
            Picasso.with(context).load(url_image+movieItem.getUrlImage()).into(ivPoster);
            // Return the completed view to render on screen
            return convertView;
        }
    }
}
