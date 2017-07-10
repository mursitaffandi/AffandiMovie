package id.co.imastudio.affandimovie.affandimovie.model;

/**
 * Created by Ingat Mati on 10/07/2017.
 */

public class MovieItem {
    public String urlImage;
    public String originalTitle;
    public String moviePoster;
    public String synopsis;
    public String userRating;
    public String releaseDate;

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public MovieItem(String urlImage, String originalTitle, String moviePoster, String synopsis, String userRating, String releaseDate) {
        this.urlImage = urlImage;
        this.originalTitle = originalTitle;
        this.moviePoster = moviePoster;
        this.synopsis = synopsis;
        this.userRating = userRating;
        this.releaseDate = releaseDate;
    }

}
