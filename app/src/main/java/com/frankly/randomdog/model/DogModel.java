package com.frankly.randomdog.model;

public class DogModel {

    private String imageURL;
    private String key;

    public DogModel(String imageURL) {
        this.imageURL = imageURL;
        this.key = imageURL.substring(imageURL.lastIndexOf("/") + 1);
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getKey() {
        return key;
    }

}
