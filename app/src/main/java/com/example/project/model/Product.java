package com.example.project.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {

    private String name;
    private String imageUrl;
    private int price;
    private String category; // เพิ่มฟิลด์ category

    // Constructor
    public Product(String name, String imageUrl, int price, String category) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.category = category; // กำหนด category
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category; // Getter สำหรับ category
    }

    public void setCategory(String category) {
        this.category = category; // Setter สำหรับ category
    }

    // Parcelable methods
    protected Product(Parcel in) {
        name = in.readString();
        imageUrl = in.readString();
        price = in.readInt();
        category = in.readString(); // อ่านค่า category จาก Parcel
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(imageUrl);
        dest.writeInt(price);
        dest.writeString(category); // เขียนค่า category ลง Parcel
    }
}
