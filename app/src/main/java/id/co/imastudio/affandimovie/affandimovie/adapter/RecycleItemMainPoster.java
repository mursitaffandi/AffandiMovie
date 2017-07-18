package id.co.imastudio.affandimovie.affandimovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.co.imastudio.affandimovie.affandimovie.R;
import id.co.imastudio.affandimovie.affandimovie.ScrollingDetailMovieActivity;
import id.co.imastudio.affandimovie.affandimovie.helper.DataMovieParser;
import id.co.imastudio.affandimovie.affandimovie.helper.HolderRecycleMainPoster;

/**
 * Created by Ingat Mati on 18/07/2017.
 */

public class RecycleItemMainPoster extends RecyclerView.Adapter<HolderRecycleMainPoster> {
    Context context;
    List<DataMovieParser.Result> listItemMovie;
    private final String url_image;

    public RecycleItemMainPoster(Context context, List<DataMovieParser.Result> listItemMovie) {
        this.context = context;
        this.listItemMovie = listItemMovie;
        url_image = context.getResources().getString(R.string.base_url_image) + context.getResources().getString(R.string.size_poster_image_recommended);
    }

    @Override
    public HolderRecycleMainPoster onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_poster_movie, null);
        HolderRecycleMainPoster holderItem = new HolderRecycleMainPoster(view);
        return holderItem;
    }

    @Override
    public void onBindViewHolder(HolderRecycleMainPoster holder, int position) {
        final String originalTitle, moviePoster, synopsis, userRating, releaseDate;

        originalTitle = listItemMovie.get(position).getOriginalTitle();
        moviePoster = url_image + listItemMovie.get(position).getPosterPath();
        synopsis = listItemMovie.get(position).getOverview();
        userRating = String.valueOf(listItemMovie.get(position).getVoteAverage());
        releaseDate = listItemMovie.get(position).getReleaseDate();

        Picasso.with(context).load(moviePoster).into(holder.imgVPoster);

        holder.imgVPoster.setOnClickListener(new View.OnClickListener() {
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
    }


    @Override
    public int getItemCount() {
        return listItemMovie.size();
    }
}
