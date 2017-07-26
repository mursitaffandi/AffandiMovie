package id.co.imastudio.affandimovie.affandimovie.helper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ingat Mati on 25/07/2017.
 */

public class DatabaseManager extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "db_movie";
    //    Table
    private static final String TABLE_MOVIE_NAME = "tb_movie";
    private static final String TABLE_TRAILER_NAME = "tb_trailer";
    private static final String TABLE_REVIEW_NAME = "tb_review";

    //    Field table movie
    private static final String COLUMN_MOVIE_ID_TB = "id_tb_movie";
    private static final String COLUMN_MOVIE_ID = "movie_id";
    private static final String COLUMN_MOVIE_RATE = "movie_title";
    private static final String COLUMN_MOVIE_TITLE = "movie_title";
    private static final String COLUMN_MOVIE_POSTER = "movie_poster_path";
    private static final String COLUMN_MOVIE_SYNOPSIS = "movie_overview";
    private static final String COLUMN_MOVIE_RELEASE = "movie_release_date";
    private static final String COLUMN_MOVIE_BACKDROP = "movie_backdrop_path";

    //    Field table trailer
    private static final String COLUMN_TRAILER_ID_TB = "id_tb_trailer";
    private static final String COLUMN_TRAILER_PARENT_ID = "trailer_parent_id";
    private static final String COLUMN_TRAILER_KEY = "trailer_key";
    private static final String COLUMN_TRAILER_NAME = "trailer_name";

    //   Field table review
    private static final String COLUMN_REVIEW_ID_TB = "id_tb_review";
    private static final String COLUMN_REVIEW_PARENT_ID = "review_parent_id";
    private static final String COLUMN_REVIEW_ID = "review_id";
    private static final String COLUMN_REVIEW_CONTENT = "review_content";
    private static final String COLUMN_REVIEW_URL = "review_url";

    //Query create table movie
    String CREATE_TABLE_MOVIE = "CREATE TABLE " + TABLE_MOVIE_NAME + "("
            + COLUMN_MOVIE_ID_TB + " INTEGER PRIMARY KEY,"
            + COLUMN_MOVIE_ID + " INTEGER,"
            + COLUMN_MOVIE_RATE + " CHARACTER,"
            + COLUMN_MOVIE_TITLE + "VARCHAR,"
            + COLUMN_MOVIE_POSTER + " VARCHAR,"
            + COLUMN_MOVIE_SYNOPSIS + " TEXT,"
            + COLUMN_MOVIE_RELEASE + " VARCHAR,"
            + COLUMN_MOVIE_BACKDROP + " VARCHAR"
            + ")";

    //Query create table trailer
    String CREATE_TABLE_TRAILER = "CREATE TABLE " + TABLE_TRAILER_NAME + "("
            + COLUMN_TRAILER_ID_TB + " INTEGER PRIMARY KEY,"
            + COLUMN_TRAILER_PARENT_ID + " INTEGER,"
            + COLUMN_TRAILER_KEY + " CHARACTER,"
            + COLUMN_TRAILER_NAME + " VARCHAR"
            + ")";

    //Query create table review
    String CREATE_TABLE_REVIEW = "CREATE TABLE " + TABLE_REVIEW_NAME + "("
            + COLUMN_REVIEW_ID_TB + " INTEGER PRIMARY KEY,"
            + COLUMN_REVIEW_PARENT_ID + " INTEGER,"
            + COLUMN_REVIEW_ID + " VARCHAR,"
            + COLUMN_REVIEW_CONTENT + " TEXT,"
            + COLUMN_REVIEW_URL + " VARCHAR"
            + ")";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_MOVIE);
        db.execSQL(CREATE_TABLE_TRAILER);
        db.execSQL(CREATE_TABLE_REVIEW);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRAILER_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REVIEW_NAME);
        // create new tables
        onCreate(db);
    }
}
