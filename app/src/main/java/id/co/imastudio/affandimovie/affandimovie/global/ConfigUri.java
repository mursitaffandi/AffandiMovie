package id.co.imastudio.affandimovie.affandimovie.global;

import android.net.Uri;

import id.co.imastudio.affandimovie.affandimovie.R;


/**
 * Created by Ingat Mati on 29/07/2017.
 */

public class ConfigUri extends MyApplication {
    public static final String CONTENT_DESCRIPTION_ITEM_POSTER =  getContext().getResources().getString(R.string.content_description_item_poster);
    public static final String CONTENT_DESCRIPTION_ICON_RATE =  getContext().getResources().getString(R.string.content_description_icon_rate);
    public static final String CONTENT_DESCRIPTION_POSTER_DETAIL =  getContext().getResources().getString(R.string.content_description_poster_detail);
    public static final String TITLE_ACTIVITY_SETTINGS =  getContext().getResources().getString(R.string.title_activity_settings);
    public static final String TITLE_PREFERENCE_SHORT =  getContext().getResources().getString(R.string.title_preference_short);
    public static final String ITEM_SETTING =  getContext().getResources().getString(R.string.item_setting);
    public static final String ITEM_SHARE =  getContext().getResources().getString(R.string.item_share);
    public static final String ITEM_REFRESH =  getContext().getResources().getString(R.string.item_refresh);
    public static final String CONFIRM_PREFERENCE =  getContext().getResources().getString(R.string.confirm_preference);
    public static final String TEXT_DETAIL_DESCRIPTION =  getContext().getResources().getString(R.string.text_detail_description);
    public static final String TEXT_DETAIL_RELEASEDATE =  getContext().getResources().getString(R.string.text_detail_releasedate);
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
    public static final String SIZE_POSTER_IMAGE_W92 =  getContext().getResources().getString(R.string.size_poster_image_w92);
    public static final String SIZE_POSTER_IMAGE_W154 =  getContext().getResources().getString(R.string.size_poster_image_w154);
    public static final String SIZE_POSTER_IMAGE_W185 =  getContext().getResources().getString(R.string.size_poster_image_w185);
    public static final String SIZE_POSTER_IMAGE_W342 =  getContext().getResources().getString(R.string.size_poster_image_w342);
    public static final String SIZE_POSTER_IMAGE_W500 =  getContext().getResources().getString(R.string.size_poster_image_w500);
    public static final String SIZE_POSTER_IMAGE_W780 =  getContext().getResources().getString(R.string.size_poster_image_w780);
    public static final String SIZE_POSTER_IMAGE_ORIGINAL =  getContext().getResources().getString(R.string.size_poster_image_original);
    public static final String SIZE_POSTER_IMAGE_RECOMMENDED =  getContext().getResources().getString(R.string.size_poster_image_recommended);


//    Content Provider Constant
    public static final String CONTENT_AUTHORITY = "id.co.imastudio.affandimovie.affandimovie";//getContext().getResources().getString(R.string.authority);
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_MOVIE = "tb_movie";

}
