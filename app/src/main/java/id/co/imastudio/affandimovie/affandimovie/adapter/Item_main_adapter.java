package id.co.imastudio.affandimovie.affandimovie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by Ingat Mati on 09/07/2017.
 */

public class Item_main_adapter extends BaseAdapter {
    private Context context;

    @Override
    public int getCount() {
        return 0;
    }

    public Item_main_adapter(Context context) {
        this.context = context;
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
        ImageView imagePoster = new ImageView(context);
        return null;
    }
}
