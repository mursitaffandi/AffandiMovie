package id.co.imastudio.affandimovie.affandimovie.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.Arrays;

import id.co.imastudio.affandimovie.affandimovie.R;
import id.co.imastudio.affandimovie.affandimovie.adapter.Item_main_adapter;
import id.co.imastudio.affandimovie.affandimovie.model.MoviePosterModel;
public class MainPosterFragment extends Fragment {
    private Item_main_adapter flavorAdapter;

   /* MoviePosterModel[] androidFlavors = {
            new MoviePosterModel("Cupcake", "1.5", R.drawable.cupcake),
            new MoviePosterModel("Donut", "1.6", R.drawable.donut)
    };*/
    public MainPosterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_poster, container, false);

        //flavorAdapter = new Item_main_adapter(getActivity(), Arrays.asList(androidFlavors));

        // Get a reference to the GridView, and attach this adapter to it.
        GridView gridViewPosterMain = (GridView) rootView.findViewById(R.id.gridView_fragment_main_poster);
        gridViewPosterMain.setAdapter(flavorAdapter);

        return rootView;
    }
}
