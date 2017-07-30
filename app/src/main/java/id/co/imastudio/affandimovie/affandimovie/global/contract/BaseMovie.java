package id.co.imastudio.affandimovie.affandimovie.global.contract;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import static id.co.imastudio.affandimovie.affandimovie.global.ConfigUri.BASE_CONTENT_URI;
import static id.co.imastudio.affandimovie.affandimovie.global.ConfigUri.CONTENT_AUTHORITY;


public class BaseMovie {

    public static final class MovieListEntry implements BaseColumns {

        public static final String TABLE_MOVIE_NAME = "tb_movie";
        public static final String COLUMN_MOVIE_ID = "movie_id";
        public static final String COLUMN_MOVIE_RATE = "movie_rate";
        public static final String COLUMN_MOVIE_TITLE = "movie_title";
        public static final String COLUMN_MOVIE_POSTER = "movie_poster_path";
        public static final String COLUMN_MOVIE_SYNOPSIS = "movie_overview";
        public static final String COLUMN_MOVIE_RELEASE = "movie_release_date";
        public static final String COLUMN_MOVIE_BACKDROP = "movie_backdrop_path";
        public static final String COLUMN_MOVIE_TIMESTAMP = "movie_timestamp";

        // create content uri
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon()
                .appendPath(TABLE_MOVIE_NAME).build();

        // create cursor of base type directory for multiple entries
        public static final String CONTENT_DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_MOVIE_NAME;
        // create cursor of base type item for single entry
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE +"/" + CONTENT_AUTHORITY + "/" + TABLE_MOVIE_NAME;
        // for building URIs on insertion
        public static Uri buildFlavorsUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }
    
}
