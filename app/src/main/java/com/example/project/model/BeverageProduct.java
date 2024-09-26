package com.example.project.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BeverageProduct extends Product {
    private String size;
    private String features;
    private String consumption;
    private String warning;

    public BeverageProduct(String name, String imageUrl, int price, String category, String size, String features, String consumption, String warning) {
        super(name, imageUrl, price, category);
        this.size = size;
        this.features = features;
        this.consumption = consumption;
        this.warning = warning;
    }

    // Getters
    public String getSize() {
        return size;
    }

    public String getFeatures() {
        return features;
    }

    public String getConsumption() {
        return consumption;
    }

    public String getWarning() {
        return warning;
    }

    // Parcelable methods
    protected BeverageProduct(Parcel in) {
        super(in);
        size = in.readString();
        features = in.readString();
        consumption = in.readString();
        warning = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(size);
        dest.writeString(features);
        dest.writeString(consumption);
        dest.writeString(warning);
    }

    public static final Creator<BeverageProduct> CREATOR = new Creator<BeverageProduct>() {
        @Override
        public BeverageProduct createFromParcel(Parcel in) {
            return new BeverageProduct(in);
        }

        @Override
        public BeverageProduct[] newArray(int size) {
            return new BeverageProduct[size];
        }
    };
}
