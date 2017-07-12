package id.co.imastudio.affandimovie.affandimovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.co.imastudio.affandimovie.affandimovie.R;
import id.co.imastudio.affandimovie.affandimovie.ScrollingDetailMovieActivity;
import id.co.imastudio.affandimovie.affandimovie.helper.DataMovieParser;



public class ItemMainAdapter extends BaseAdapter {
    private final Context context;
    private final String url_image;
    private final List<DataMovieParser.Result> movies;

    public ItemMainAdapter(Context context, List<DataMovieParser.Result> movies) {
        this.context = context;
        this.movies = movies;
        url_image = context.getResources().getString(R.string.base_url_image) + context.getResources().getString(R.string.size_poster_image_recommended);
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
        final String originalTitle, moviePoster, synopsis, userRating, releaseDate;

        originalTitle = movies.get(position).getOriginalTitle();
        moviePoster = url_image + movies.get(position).getPosterPath();
        synopsis = movies.get(position).getOverview();
        userRating = String.valueOf(movies.get(position).getVoteAverage());
        releaseDate = movies.get(position).getReleaseDate();

        ImageView ivPoster = (ImageView) convertView.findViewById(R.id.imgV_item_poster);
        Picasso.with(context).load(moviePoster).into(ivPoster);

        ivPoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toDetail = new Intent(context, ScrollingDetailMovieActivity.class);
                toDetail.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                toDetail.putExtra("title", originalTitle);
                toDetail.putExtra("poster_path",moviePoster);
                toDetail.putExtra("overview", synopsis);
                toDetail.putExtra("vote_average", userRating);
                toDetail.putExtra("release_date", releaseDate);
                context.startActivity(toDetail);

            }
        });
        return convertView;
    }
}
