package id.co.imastudio.affandimovie.affandimovie.adapter.review;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import java.util.List;
import id.co.imastudio.affandimovie.affandimovie.holder.HolderRecycleMainPoster;
import id.co.imastudio.affandimovie.affandimovie.model.review.DataReviewParser;


public class RecycleItemReview extends RecyclerView.Adapter<HolderRecycleMainPoster> {
    private final Context context;
    private final List<DataReviewParser.Result> listItemReview;
    private final String nameAuthor;
    private final String contentReview;

    public RecycleItemReview(Context context, List<DataReviewParser.Result> listItemReview) {
        this.context = context;
        this.listItemReview = listItemReview;
        this.nameAuthor = "";
        this.contentReview = "";
    }

    @Override
    public HolderRecycleMainPoster onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(HolderRecycleMainPoster holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listItemReview.size();
    }
}

