package id.co.imastudio.affandimovie.affandimovie.model;

/**
 * Created by Ingat Mati on 09/07/2017.
 */

public class MoviePosterModel {
    String versionName;
    String versionNumber;
    int image; // drawable reference id

    public MoviePosterModel(String vName, String vNumber, int image)
    {
        this.versionName = vName;
        this.versionNumber = vNumber;
        this.image = image;
    }
}
