package id.co.imastudio.affandimovie.affandimovie.global.contract;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import static id.co.imastudio.affandimovie.affandimovie.global.ConfigUri.BASE_CONTENT_URI;
import static id.co.imastudio.affandimovie.affandimovie.global.ConfigUri.CONTENT_AUTHORITY;


public class BaseTrailer {
    public static final class TrailerListEntry implements BaseColumns {
        public static final String TABLE_TRAILER_NAME = "tb_trailer";
        public static final String COLUMN_TRAILER_ID_TB = "id_tb_trailer";
        public static final String COLUMN_TRAILER_PARENT_ID = "trailer_parent_id";
        public static final String COLUMN_TRAILER_KEY = "trailer_key";
        public static final String COLUMN_TRAILER_NAME = "trailer_name";
        public static final String COLUMN_TRAILER_TIMESTAMP = "trailer_timestamp";
        // create content uri
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(TABLE_TRAILER_NAME).build();

        // create cursor of base type directory for multiple entries
        public static final String CONTENT_DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_TRAILER_NAME;
        // create cursor of base type item for single entry
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE +"/" + CONTENT_AUTHORITY + "/" + TABLE_TRAILER_NAME;
        // for building URIs on insertion
        public static Uri buildFlavorsUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

}
