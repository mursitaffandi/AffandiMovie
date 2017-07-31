package id.co.imastudio.affandimovie.affandimovie.adapter.favorite;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import id.co.imastudio.affandimovie.affandimovie.R;
import id.co.imastudio.affandimovie.affandimovie.global.ConfigUri;
import id.co.imastudio.affandimovie.affandimovie.holder.HolderRecycleMainPoster;
import id.co.imastudio.affandimovie.affandimovie.global.contract.BaseMovie;
import id.co.imastudio.affandimovie.affandimovie.model.dbItem.Movie;

public class ItemMovieAdapter extends RecyclerView.Adapter<HolderRecycleMainPoster> {
    private final Context mContext;
    private Cursor mCursor;
    public final List<Movie> slistMovie = new ArrayList<>();

    public ItemMovieAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public HolderRecycleMainPoster onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_poster_movie, null);
        return new HolderRecycleMainPoster(view);
    }

    @Override
    public void onBindViewHolder(HolderRecycleMainPoster holder, int position) {
        // Move the mCursor to the position of the item to be displayed
        if (!mCursor.moveToPosition(position))
            return; // bail if returned null
        // Update the view holder with the information needed to display
        String name = mCursor.getString(mCursor.getColumnIndex(BaseMovie.MovieListEntry.COLUMN_MOVIE_POSTER));
        // COMPLETED (6) Retrieve the id from the cursor and
        long id = mCursor.getLong(mCursor.getColumnIndex(BaseMovie.MovieListEntry.COLUMN_MOVIE_ID));

        int movie_id =
                mCursor
                        .getInt(mCursor.getColumnIndex(BaseMovie.MovieListEntry.COLUMN_MOVIE_ID));
        String movie_title =
                mCursor
                        .getString(mCursor.getColumnIndex(BaseMovie.MovieListEntry.COLUMN_MOVIE_TITLE));
        String movie_rate =
                mCursor
                        .getString(mCursor.getColumnIndex(BaseMovie.MovieListEntry.COLUMN_MOVIE_RATE));
        String movie_poster_path =
                mCursor
                        .getString(mCursor.getColumnIndex(BaseMovie.MovieListEntry.COLUMN_MOVIE_POSTER));
        String movie_overview =
                mCursor
                        .getString(mCursor.getColumnIndex(BaseMovie.MovieListEntry.COLUMN_MOVIE_SYNOPSIS));
        String movie_release_date =
                mCursor
                        .getString(mCursor.getColumnIndex(BaseMovie.MovieListEntry.COLUMN_MOVIE_RELEASE));
        String movie_backdrop_path =
                mCursor
                        .getString(mCursor.getColumnIndex(BaseMovie.MovieListEntry.COLUMN_MOVIE_BACKDROP));

        Picasso
                .with(mContext)
                .load(
                        ConfigUri.BASE_URL_IMAGE
                                + ConfigUri.SIZE_POSTER_IMAGE_RECOMMENDED
                                + name
                )
                .placeholder(R.drawable.placeholder)
                .into(holder.imgVPoster);
        // COMPLETED (7) Set the tag of the itemview in the holder to the id
        slistMovie.add(new Movie(movie_id, movie_title, movie_rate, movie_poster_path, movie_overview, movie_release_date, movie_backdrop_path));
        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        if (mCursor == null) {
            return 0;
        }
        return mCursor.getCount();
    }

    public List<Movie> getListMovie() {
        return slistMovie;
    }

    public Cursor swapCursor(Cursor c) {
        // check if this cursor is the same as the previous cursor (mCursor)
        if (mCursor == c) {
            return null; // bc nothing has changed
        }
        Cursor temp = mCursor;
        this.mCursor = c; // new cursor value assigned

        //check if this is a valid cursor, then update the cursor
        if (c != null) {
            this.notifyDataSetChanged();
        }
        return temp;
    }
}
