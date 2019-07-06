package com.agarwal.arpit.quizapp.response;

import com.google.gson.annotations.SerializedName;

public class LogoItem {

    @SerializedName("imgUrl")
    private String imageUrl;

    @SerializedName("name")
    private String imageName;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
