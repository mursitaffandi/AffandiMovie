package id.co.imastudio.affandimovie.affandimovie.model.localdb;

import android.provider.BaseColumns;


public class BaseTrailer {
    public static final class TrailerListEntry implements BaseColumns {
        public static final String TABLE_TRAILER_NAME = "tb_trailer";
        public static final String COLUMN_TRAILER_ID_TB = "id_tb_trailer";
        public static final String COLUMN_TRAILER_PARENT_ID = "trailer_parent_id";
        public static final String COLUMN_TRAILER_KEY = "trailer_key";
        public static final String COLUMN_TRAILER_NAME = "trailer_name";
        public static final String COLUMN_TRAILER_TIMESTAMP = "trailer_timestamp";
    }
}
