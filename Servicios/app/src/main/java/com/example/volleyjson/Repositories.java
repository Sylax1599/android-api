package com.example.volleyjson;

import android.os.Parcel;
import android.os.Parcelable;

public class Repositories implements Parcelable {

    private String id;
    private String html_url;
    private String description;

    public Repositories(String id, String html_url, String description) {
        this.id = id;
        this.html_url = html_url;
        this.description = description;
    }

    protected Repositories(Parcel in) {
        id = in.readString();
        description = in.readString();
        description = in.readString();
    }

    public static final Parcelable.Creator<Repositories> CREATOR = new Parcelable.Creator<Repositories>() {
        @Override
        public Repositories createFromParcel(Parcel in) {
            return new Repositories(in);
        }

        @Override
        public Repositories[] newArray(int size) {
            return new Repositories[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "id='" + id + '\'' +
                ", name='" + html_url + '\'' +
                ", area='" + description + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(html_url);
        dest.writeString(description);
    }
}
