package id.co.imastudio.affandimovie.affandimovie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;
import id.co.imastudio.affandimovie.affandimovie.R;
import id.co.imastudio.affandimovie.affandimovie.holder.HolderRecycleItemTrailer;
import id.co.imastudio.affandimovie.affandimovie.model.DataTrailerParser;

public class RecycleItemTrailer extends RecyclerView.Adapter<HolderRecycleItemTrailer> {
    private final Context context;
    private final List<DataTrailerParser.Result> listItemTrailer;

    public RecycleItemTrailer(Context context, List<DataTrailerParser.Result> listItemTrailer) {
        this.context = context;
        this.listItemTrailer = listItemTrailer;
    }


    @Override
    public HolderRecycleItemTrailer onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_trailer, null);
        return new HolderRecycleItemTrailer(view);
    }

    @Override
    public void onBindViewHolder(HolderRecycleItemTrailer holder, int position) {
        Picasso
                .with(context)
                .load(
                        context.getResources().getString(R.string.base_url_thumnail_yt)
                                + listItemTrailer.get(position).getKey()
                                + context.getResources().getString(R.string.size_image_yt_middle)
                )
                .placeholder(R.drawable.image_sampel)
                .into(holder.ivThumnailTrailer);
    }

    @Override
    public int getItemCount() {
        return listItemTrailer.size();
    }
}
