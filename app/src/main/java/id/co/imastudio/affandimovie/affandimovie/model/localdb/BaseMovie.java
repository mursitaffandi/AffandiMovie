package id.co.imastudio.affandimovie.affandimovie.model.localdb;

import android.provider.BaseColumns;


public class BaseMovie {
    public static final class MovieListEntry implements BaseColumns {
        public static final String TABLE_MOVIE_NAME = "tb_movie";
        public static final String COLUMN_MOVIE_ID_TB = "id_tb_movie";
        public static final String COLUMN_MOVIE_ID = "movie_id";
        public static final String COLUMN_MOVIE_RATE = "movie_title";
        public static final String COLUMN_MOVIE_TITLE = "movie_title";
        public static final String COLUMN_MOVIE_POSTER = "movie_poster_path";
        public static final String COLUMN_MOVIE_SYNOPSIS = "movie_overview";
        public static final String COLUMN_MOVIE_RELEASE = "movie_release_date";
        public static final String COLUMN_MOVIE_BACKDROP = "movie_backdrop_path";
        public static final String COLUMN_MOVIE_TIMESTAMP = "movie_timestamp";

    }
}
