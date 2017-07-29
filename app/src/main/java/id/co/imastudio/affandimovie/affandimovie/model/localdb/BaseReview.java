package id.co.imastudio.affandimovie.affandimovie.model.localdb;

import android.provider.BaseColumns;

public class BaseReview {
    public static final class ReviewListEntry implements BaseColumns {
        public static final String TABLE_REVIEW_NAME = "tb_review";
        public static final String COLUMN_REVIEW_ID_TB = "id_tb_review";
        public static final String COLUMN_REVIEW_PARENT_ID = "review_parent_id";
        public static final String COLUMN_REVIEW_ID = "review_id";
        public static final String COLUMN_REVIEW_CONTENT = "review_content";
        public static final String COLUMN_REVIEW_URL = "review_url";
        public static final String COLUMN_REVIEW_TIMESTAMP = "review_timestamp";
    }
}
