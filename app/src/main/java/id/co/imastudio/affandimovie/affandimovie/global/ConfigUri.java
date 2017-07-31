package id.co.imastudio.affandimovie.affandimovie.global;

import android.net.Uri;

import id.co.imastudio.affandimovie.affandimovie.R;


public class ConfigUri extends MyApplication {

    public static final String BASE_URL_THUMNAIL_YT =  getContext().getResources().getString(R.string.base_url_thumnail_yt);
    public static final String BASE_URL_VIDEO_YT =  getContext().getResources().getString(R.string.base_url_video_yt);
    public static final String SIZE_IMAGE_YT_MIDDLE =  getContext().getResources().getString(R.string.size_image_yt_middle);
    public static final String URL_JSON_POPULAR_MOVIE =  getContext().getResources().getString(R.string.url_json_popular_movie);
    public static final String URL_JSON_TOPRATED_MOVIE =  getContext().getResources().getString(R.string.url_json_toprated_movie);
    public static final String MY_TMDB_API_KEY =  getContext().getResources().getString(R.string.MY_TMDB_API_KEY);
    public static final String BASE_URL_IMAGE =  getContext().getResources().getString(R.string.base_url_image);
    public static final String BASE_URL_DETAIL =  getContext().getResources().getString(R.string.base_url_detail);
    public static final String TAIL_URL_TRAILER =  getContext().getResources().getString(R.string.tail_url_trailer);
    public static final String TAIL_URL_REVIEW =  getContext().getResources().getString(R.string.tail_url_review);

    public static final String SIZE_POSTER_IMAGE_W342 =  getContext().getResources().getString(R.string.size_poster_image_w342);

    public static final String SIZE_POSTER_IMAGE_RECOMMENDED =  getContext().getResources().getString(R.string.size_poster_image_recommended);


//    Content Provider Constant
    public static final String CONTENT_AUTHORITY = "id.co.imastudio.affandimovie.affandimovie";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

}
