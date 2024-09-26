package com.example.project.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SmartphoneProduct extends Product {
    private String screenSize;
    private String processor;
    private String ram;
    private String storage;
    private String camera;
    private String battery;
    private String operatingSystem;

    public SmartphoneProduct(String name, String imageUrl, int price, String category, String screenSize, String processor, String ram, String storage, String camera, String battery, String operatingSystem) {
        super(name, imageUrl, price, category);
        this.screenSize = screenSize;
        this.processor = processor;
        this.ram = ram;
        this.storage = storage;
        this.camera = camera;
        this.battery = battery;
        this.operatingSystem = operatingSystem;
    }

    // Getters
    public String getScreenSize() {
        return screenSize;
    }

    public String getProcessor() {
        return processor;
    }

    public String getRam() {
        return ram;
    }

    public String getStorage() {
        return storage;
    }

    public String getCamera() {
        return camera;
    }

    public String getBattery() {
        return battery;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    // Parcelable methods
    protected SmartphoneProduct(Parcel in) {
        super(in);
        screenSize = in.readString();
        processor = in.readString();
        ram = in.readString();
        storage = in.readString();
        camera = in.readString();
        battery = in.readString();
        operatingSystem = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(screenSize);
        dest.writeString(processor);
        dest.writeString(ram);
        dest.writeString(storage);
        dest.writeString(camera);
        dest.writeString(battery);
        dest.writeString(operatingSystem);
    }

    public static final Creator<SmartphoneProduct> CREATOR = new Creator<SmartphoneProduct>() {
        @Override
        public SmartphoneProduct createFromParcel(Parcel in) {
            return new SmartphoneProduct(in);
        }

        @Override
        public SmartphoneProduct[] newArray(int size) {
            return new SmartphoneProduct[size];
        }
    };
}
