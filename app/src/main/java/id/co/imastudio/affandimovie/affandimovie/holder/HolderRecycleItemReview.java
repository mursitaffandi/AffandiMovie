package id.co.imastudio.affandimovie.affandimovie.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.imastudio.affandimovie.affandimovie.R;


public class HolderRecycleItemReview extends RecyclerView.ViewHolder {

    public
    @BindView(R.id.tv_item_review_author)
    TextView tvReviewAuthor;

    public
    @BindView(R.id.tv_item_review_content)
    TextView tvReviewContent;
    public HolderRecycleItemReview(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}