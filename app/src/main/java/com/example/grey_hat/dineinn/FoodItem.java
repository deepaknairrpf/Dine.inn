package com.example.grey_hat.dineinn;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by grey-hat on 1/4/17.
 */

public class FoodItem implements Parcelable{
    private String name;
    private String ImgUrl;
    private long price;
    private Boolean availability;

    public FoodItem() {}

    protected FoodItem(Parcel in) {
        name = in.readString();
        ImgUrl = in.readString();
        price = in.readLong();

    }

    public static final Creator<FoodItem> CREATOR = new Creator<FoodItem>() {
        @Override
        public FoodItem createFromParcel(Parcel in) {
            return new FoodItem(in);
        }

        @Override
        public FoodItem[] newArray(int size) {
            return new FoodItem[size];
        }
    };

    public long getPrice() {
        return price;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public String getName() {
        return name;
    }

    public Boolean getAvailability() {
        return availability;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(ImgUrl);
        dest.writeLong(price);

    }
}
