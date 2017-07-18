package id.co.imastudio.affandimovie.affandimovie.helper;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.imastudio.affandimovie.affandimovie.R;

/**
 * Created by Ingat Mati on 18/07/2017.
 */

public class HolderRecycleMainPoster extends RecyclerView.ViewHolder {

   public @BindView(R.id.imgV_item_poster) ImageView imgVPoster;
//public ImageView imgVPoster;
    public HolderRecycleMainPoster(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
//        imgVPoster = (ImageView) itemView.findViewById(R.id.imgV_item_poster);
    }
}
