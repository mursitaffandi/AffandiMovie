package id.co.imastudio.affandimovie.affandimovie.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class DataTrailerParser implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    public List<Result> results = null;
    public final static Parcelable.Creator<DataTrailerParser> CREATOR = new Creator<DataTrailerParser>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DataTrailerParser createFromParcel(Parcel in) {
            DataTrailerParser instance = new DataTrailerParser();
            instance.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            in.readList(instance.results, (Result.class.getClassLoader()));
            return instance;
        }

        public DataTrailerParser[] newArray(int size) {
            return (new DataTrailerParser[size]);
        }

    };

    /**
     * No args constructor for use in serialization
     */
    private DataTrailerParser() {
    }

    /**
     * @param id
     * @param results
     */
    public DataTrailerParser(Integer id, List<Result> results) {
        super();
        this.id = id;
        this.results = results;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeList(results);
    }

    public int describeContents() {
        return 0;
    }

    public static class Result implements Parcelable {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("iso_639_1")
        @Expose
        private String iso6391;
        @SerializedName("iso_3166_1")
        @Expose
        private String iso31661;
        @SerializedName("key")
        @Expose
        private String key;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("site")
        @Expose
        private String site;
        @SerializedName("size")
        @Expose
        private Integer size;
        @SerializedName("type")
        @Expose
        private String type;
        public final static Parcelable.Creator<Result> CREATOR = new Creator<Result>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Result createFromParcel(Parcel in) {
                Result instance = new Result();
                instance.id = ((String) in.readValue((String.class.getClassLoader())));
                instance.iso6391 = ((String) in.readValue((String.class.getClassLoader())));
                instance.iso31661 = ((String) in.readValue((String.class.getClassLoader())));
                instance.key = ((String) in.readValue((String.class.getClassLoader())));
                instance.name = ((String) in.readValue((String.class.getClassLoader())));
                instance.site = ((String) in.readValue((String.class.getClassLoader())));
                instance.size = ((Integer) in.readValue((Integer.class.getClassLoader())));
                instance.type = ((String) in.readValue((String.class.getClassLoader())));
                return instance;
            }

            public Result[] newArray(int size) {
                return (new Result[size]);
            }

        };

        /**
         * No args constructor for use in serialization
         */
        public Result() {
        }

        /**
         * @param site
         * @param iso6391
         * @param id
         * @param iso31661
         * @param name
         * @param type
         * @param key
         * @param size
         */
        public Result(String id, String iso6391, String iso31661, String key, String name, String site, Integer size, String type) {
            super();
            this.id = id;
            this.iso6391 = iso6391;
            this.iso31661 = iso31661;
            this.key = key;
            this.name = name;
            this.site = site;
            this.size = size;
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIso6391() {
            return iso6391;
        }

        public void setIso6391(String iso6391) {
            this.iso6391 = iso6391;
        }

        public String getIso31661() {
            return iso31661;
        }

        public void setIso31661(String iso31661) {
            this.iso31661 = iso31661;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(iso6391);
            dest.writeValue(iso31661);
            dest.writeValue(key);
            dest.writeValue(name);
            dest.writeValue(site);
            dest.writeValue(size);
            dest.writeValue(type);
        }

        public int describeContents() {
            return 0;
        }

    }
}


