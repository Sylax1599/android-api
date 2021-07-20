package com.example.volleyjson;

import android.os.Parcel;
import android.os.Parcelable;

public class ApiInfo implements Parcelable{
    private String id;
    private String name;
    private String url;

    public ApiInfo(String id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    protected ApiInfo(Parcel in) {
        id = in.readString();
        name = in.readString();
        url = in.readString();
    }

    public static final Creator<ApiInfo> CREATOR = new Creator<ApiInfo>() {
        @Override
        public ApiInfo createFromParcel(Parcel in) {
            return new ApiInfo(in);
        }

        @Override
        public ApiInfo[] newArray(int size) {
            return new ApiInfo[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(url);
    }
}
