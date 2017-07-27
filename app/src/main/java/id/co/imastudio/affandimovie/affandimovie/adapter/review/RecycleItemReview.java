package id.co.imastudio.affandimovie.affandimovie.adapter.review;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import id.co.imastudio.affandimovie.affandimovie.R;
import id.co.imastudio.affandimovie.affandimovie.holder.HolderRecycleMainPoster;
import id.co.imastudio.affandimovie.affandimovie.holder.review.HolderRecycleItemReview;
import id.co.imastudio.affandimovie.affandimovie.holder.trailer.HolderRecycleItemTrailer;
import id.co.imastudio.affandimovie.affandimovie.model.review.DataReviewParser;


public class RecycleItemReview extends RecyclerView.Adapter<HolderRecycleItemReview> {
    private final Context context;
    private final List<DataReviewParser.Result> listItemReview;

    public RecycleItemReview(Context context, List<DataReviewParser.Result> listItemReview) {
        this.context = context;
        this.listItemReview = listItemReview;
    }

    @Override
    public HolderRecycleItemReview onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_review, null);
        return new HolderRecycleItemReview(view);
    }

    @Override
    public void onBindViewHolder(HolderRecycleItemReview holder, int position) {
        holder.tvReviewAuthor.setText(listItemReview.get(position).getAuthor());
        holder.tvReviewContent.setText(listItemReview.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return listItemReview.size();
    }
}

