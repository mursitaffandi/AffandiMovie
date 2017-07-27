package id.co.imastudio.affandimovie.affandimovie.holder.trailer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.imastudio.affandimovie.affandimovie.R;


public class HolderRecycleItemTrailer extends RecyclerView.ViewHolder {

    public
    @BindView(R.id.trailerImage)
    ImageView ivThumnailTrailer;
    public HolderRecycleItemTrailer(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
