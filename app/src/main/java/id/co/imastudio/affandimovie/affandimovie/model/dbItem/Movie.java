package id.co.imastudio.affandimovie.affandimovie.model.dbItem;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ingat Mati on 30/07/2017.
 */

public class Movie implements Parcelable {
    public int movie_id;
    public String movie_title;
    public String movie_rate;
    public String movie_poster_path;
    public String movie_overview;
    public String movie_release_date;
    public String movie_backdrop_path;

    public Movie(int movie_id, String movie_title, String movie_rate, String movie_poster_path, String movie_overview, String movie_release_date, String movie_backdrop_path) {
        this.movie_id = movie_id;
        this.movie_title = movie_title;
        this.movie_rate = movie_rate;
        this.movie_poster_path = movie_poster_path;
        this.movie_overview = movie_overview;
        this.movie_release_date = movie_release_date;
        this.movie_backdrop_path = movie_backdrop_path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.movie_id);
        dest.writeString(this.movie_title);
        dest.writeString(this.movie_rate);
        dest.writeString(this.movie_poster_path);
        dest.writeString(this.movie_overview);
        dest.writeString(this.movie_release_date);
        dest.writeString(this.movie_backdrop_path);
    }

    protected Movie(Parcel in) {
        this.movie_id = in.readInt();
        this.movie_title = in.readString();
        this.movie_rate = in.readString();
        this.movie_poster_path = in.readString();
        this.movie_overview = in.readString();
        this.movie_release_date = in.readString();
        this.movie_backdrop_path = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
