package id.co.imastudio.affandimovie.affandimovie.global.contract;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import static id.co.imastudio.affandimovie.affandimovie.global.ConfigUri.BASE_CONTENT_URI;
import static id.co.imastudio.affandimovie.affandimovie.global.ConfigUri.CONTENT_AUTHORITY;

public class BaseReview {
    public static final class ReviewListEntry implements BaseColumns {
        public static final String TABLE_REVIEW_NAME = "tb_review";
        public static final String COLUMN_REVIEW_ID_TB = "id_tb_review";
        public static final String COLUMN_REVIEW_PARENT_ID = "review_parent_id";
        public static final String COLUMN_REVIEW_ID = "review_id";
        public static final String COLUMN_REVIEW_CONTENT = "review_content";
        public static final String COLUMN_REVIEW_URL = "review_url";
        public static final String COLUMN_REVIEW_TIMESTAMP = "review_timestamp";
        // create content uri
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(TABLE_REVIEW_NAME).build();

        // create cursor of base type directory for multiple entries
        public static final String CONTENT_DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_REVIEW_NAME;
        // create cursor of base type item for single entry
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE +"/" + CONTENT_AUTHORITY + "/" + TABLE_REVIEW_NAME;
        // for building URIs on insertion
        public static Uri buildFlavorsUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }
}
