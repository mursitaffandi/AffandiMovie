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
import id.co.imastudio.affandimovie.affandimovie.DetailMovieActivity;
import id.co.imastudio.affandimovie.affandimovie.model.DataMovieParser;
import id.co.imastudio.affandimovie.affandimovie.holder.HolderRecycleMainPoster;

/**
 * Created by Ingat Mati on 18/07/2017.
 */

public class RecycleItemMainPoster extends RecyclerView.Adapter<HolderRecycleMainPoster> {
    Context context;
    List<DataMovieParser.Result> listItemMovie;
    private String url_image, sizeImage;

    public RecycleItemMainPoster(Context context, List<DataMovieParser.Result> listItemMovie) {
        this.context = context;
        this.listItemMovie = listItemMovie;
        url_image = context.getResources().getString(R.string.base_url_image);
        sizeImage = context.getResources().getString(R.string.size_poster_image_recommended);
    }

    @Override
    public HolderRecycleMainPoster onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_poster_movie, null);
        HolderRecycleMainPoster holderItem = new HolderRecycleMainPoster(view);

        return holderItem;
    }

    @Override
    public void onBindViewHolder(HolderRecycleMainPoster holder, int position) {
        final DataMovieParser.Result movieModel;
        movieModel = listItemMovie.get(position);
        Picasso.with(context).load(url_image + sizeImage + movieModel.getPosterPath()).placeholder(R.drawable.image_sampel).into(holder.imgVPoster);
       /* holder.imgVPoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toDetail = new Intent(v.getContext(), DetailMovieActivity.class);
                toDetail.putExtra("parcel", movieModel);
                v.getContext().startActivity(toDetail);
            }
        });*/


    }


    @Override
    public int getItemCount() {
        return listItemMovie.size();
    }


}
