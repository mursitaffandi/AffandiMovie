package id.co.imastudio.affandimovie.affandimovie.model.dbItem;

import android.os.Parcel;
import android.os.Parcelable;

import id.co.imastudio.affandimovie.affandimovie.model.DataMovieParser;

public class Movie implements Parcelable {
    private int movie_id;
    private String movie_title;
    private String movie_rate;
    private String movie_poster_path;
    private String movie_overview;
    private String movie_release_date;
    private String movie_backdrop_path;

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public String getMovie_rate() {
        return movie_rate;
    }

    public void setMovie_rate(String movie_rate) {
        this.movie_rate = movie_rate;
    }

    public String getMovie_poster_path() {
        return movie_poster_path;
    }

    public void setMovie_poster_path(String movie_poster_path) {
        this.movie_poster_path = movie_poster_path;
    }

    public String getMovie_overview() {
        return movie_overview;
    }

    public void setMovie_overview(String movie_overview) {
        this.movie_overview = movie_overview;
    }

    public String getMovie_release_date() {
        return movie_release_date;
    }

    public void setMovie_release_date(String movie_release_date) {
        this.movie_release_date = movie_release_date;
    }

    public String getMovie_backdrop_path() {
        return movie_backdrop_path;
    }

    public void setMovie_backdrop_path(String movie_backdrop_path) {
        this.movie_backdrop_path = movie_backdrop_path;
    }

    public static Creator<Movie> getCREATOR() {
        return CREATOR;
    }

    public Movie(int movie_id, String movie_title, String movie_rate, String movie_poster_path, String movie_overview, String movie_release_date, String movie_backdrop_path) {
        this.movie_id = movie_id;
        this.movie_title = movie_title;
        this.movie_rate = movie_rate;
        this.movie_poster_path = movie_poster_path;
        this.movie_overview = movie_overview;
        this.movie_release_date = movie_release_date;
        this.movie_backdrop_path = movie_backdrop_path;
    }
    public Movie(DataMovieParser.Result dataMovie){
        this.movie_id = dataMovie.getId();
        this.movie_title = dataMovie.getTitle();
        this.movie_rate = dataMovie.getVoteAverage().toString();
        this.movie_poster_path =dataMovie.getPosterPath();
        this.movie_overview =dataMovie.getOverview();
        this.movie_release_date =dataMovie.getReleaseDate();
        this.movie_backdrop_path =dataMovie.getBackdropPath();
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

    private Movie(Parcel in) {
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
