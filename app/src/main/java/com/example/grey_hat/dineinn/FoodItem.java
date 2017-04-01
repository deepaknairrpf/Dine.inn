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
    private long counter;


    public long getPrice() {
        return price;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public String getName() {
        return name;
    }

    public long getCounter() {
        return counter;
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
        dest.writeLong(counter);
    }
}
