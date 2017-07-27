package id.co.imastudio.affandimovie.affandimovie.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.imastudio.affandimovie.affandimovie.R;


public class HolderRecycleMainPoster extends RecyclerView.ViewHolder {

    public
    @BindView(R.id.imgV_item_poster)
    ImageView imgVPoster;

    public HolderRecycleMainPoster(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
