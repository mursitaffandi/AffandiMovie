package id.co.imastudio.affandimovie.affandimovie.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import id.co.imastudio.affandimovie.affandimovie.global.contract.BaseMovie;

import static id.co.imastudio.affandimovie.affandimovie.global.contract.BaseMovie.MovieListEntry.COLUMN_MOVIE_BACKDROP;
import static id.co.imastudio.affandimovie.affandimovie.global.contract.BaseMovie.MovieListEntry.COLUMN_MOVIE_ID;
import static id.co.imastudio.affandimovie.affandimovie.global.contract.BaseMovie.MovieListEntry.COLUMN_MOVIE_POSTER;
import static id.co.imastudio.affandimovie.affandimovie.global.contract.BaseMovie.MovieListEntry.COLUMN_MOVIE_RATE;
import static id.co.imastudio.affandimovie.affandimovie.global.contract.BaseMovie.MovieListEntry.COLUMN_MOVIE_RELEASE;
import static id.co.imastudio.affandimovie.affandimovie.global.contract.BaseMovie.MovieListEntry.COLUMN_MOVIE_SYNOPSIS;
import static id.co.imastudio.affandimovie.affandimovie.global.contract.BaseMovie.MovieListEntry.COLUMN_MOVIE_TIMESTAMP;
import static id.co.imastudio.affandimovie.affandimovie.global.contract.BaseMovie.MovieListEntry.COLUMN_MOVIE_TITLE;
import static id.co.imastudio.affandimovie.affandimovie.global.contract.BaseMovie.MovieListEntry.TABLE_MOVIE_NAME;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "db_movie.db";
    //Query create table movie
    private final String CREATE_TABLE_MOVIE = "CREATE TABLE " + TABLE_MOVIE_NAME + "("
            + BaseMovie.MovieListEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_MOVIE_ID + " INTEGER NOT NULL,"
            + COLUMN_MOVIE_RATE + " CHARACTER NOT NULL,"
            + COLUMN_MOVIE_TITLE + " VARCHAR NOT NULL,"
            + COLUMN_MOVIE_POSTER + " VARCHAR NOT NULL,"
            + COLUMN_MOVIE_SYNOPSIS + " TEXT NOT NULL,"
            + COLUMN_MOVIE_RELEASE + " VARCHAR NOT NULL,"
            + COLUMN_MOVIE_BACKDROP + " VARCHAR NOT NULL,"
            + COLUMN_MOVIE_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
            + ")";

    /*//Query create table trailer
    private final String CREATE_TABLE_TRAILER = "CREATE TABLE " + TABLE_TRAILER_NAME + "("
            + COLUMN_TRAILER_ID_TB + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_TRAILER_PARENT_ID + " INTEGER NOT NULL,"
            + COLUMN_TRAILER_KEY + " CHARACTER NOT NULL,"
            + COLUMN_TRAILER_NAME + " VARCHAR NOT NULL,"
            + COLUMN_TRAILER_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
            + ")";

    //Query create table review
    private final String CREATE_TABLE_REVIEW = "CREATE TABLE " + TABLE_REVIEW_NAME + "("
            + COLUMN_REVIEW_ID_TB + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_REVIEW_PARENT_ID + " INTEGER NOT NULL,"
            + COLUMN_REVIEW_ID + " VARCHAR NOT NULL,"
            + COLUMN_REVIEW_CONTENT + " TEXT NOT NULL,"
            + COLUMN_REVIEW_URL + " VARCHAR NOT NULL,"
            + COLUMN_REVIEW_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
            + ")";*/

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_MOVIE);
        /*db.execSQL(CREATE_TABLE_TRAILER);
        db.execSQL(CREATE_TABLE_REVIEW);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE_NAME);
        /*db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRAILER_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REVIEW_NAME);*/
        // create new tables
        onCreate(db);
    }


}
