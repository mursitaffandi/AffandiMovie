package id.co.imastudio.affandimovie.affandimovie.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import id.co.imastudio.affandimovie.affandimovie.helper.DataMovieParser;


/**
 * Created by Ingat Mati on 09/07/2017.
 */

public class Item_main_adapter extends ArrayAdapter<DataMovieParser.Creator> {
    private static final String LOG_TAG = Item_main_adapter.class.getSimpleName();

    public Item_main_adapter(Activity context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //MoviePosterModel androidFlavor = getItem(position);

       /* if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_flavor, parent, false);
        }

        ImageView iconView = (ImageView) convertView.findViewById(R.id.list_item_icon);
        iconView.setImageResource(androidFlavor.image);

        TextView versionNameView = (TextView) convertView.findViewById(R.id.list_item_version_name);
        versionNameView.setText(androidFlavor.versionName);

        TextView versionNumberView = (TextView) convertView.findViewById(R.id.list_item_versionnumber_textview);
        versionNumberView.setText(androidFlavor.versionNumber);*/
        return convertView;
    }
}
