package id.co.imastudio.affandimovie.affandimovie.adapter.favorite;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import id.co.imastudio.affandimovie.affandimovie.R;
import id.co.imastudio.affandimovie.affandimovie.holder.HolderRecycleMainPoster;

/**
 * Created by Ingat Mati on 29/07/2017.
 */

public class ItemMovieAdapter  extends RecyclerView.Adapter<HolderRecycleMainPoster> {
    private Cursor mCursor;
    private Context mContext;

    public ItemMovieAdapter(Cursor mCursor, Context mContext) {
        this.mCursor = mCursor;
        this.mContext = mContext;
    }

    @Override
    public HolderRecycleMainPoster onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(HolderRecycleMainPoster holder, int position) {
        // Move the mCursor to the position of the item to be displayed
        if (!mCursor.moveToPosition(position))
            return; // bail if returned null

        // Update the view holder with the information needed to display
        String name = mCursor.getString(mCursor.getColumnIndex(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME));
        int partySize = mCursor.getInt(mCursor.getColumnIndex(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE));
        // COMPLETED (6) Retrieve the id from the cursor and
        long id = mCursor.getLong(mCursor.getColumnIndex(WaitlistContract.WaitlistEntry._ID));

        // Display the guest name
        holder.nameTextView.setText(name);
        // Display the party count
        holder.partySizeTextView.setText(String.valueOf(partySize));
        // COMPLETED (7) Set the tag of the itemview in the holder to the id
        holder.itemView.setTag(id);

        Picasso
                .with(mContext)
                .load(
                        url_image
                                + sizeImage
                                + listItemMovie.get(position).getPosterPath()
                )
                .placeholder(R.drawable.image_sampel)
                .into(holder.imgVPoster);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
