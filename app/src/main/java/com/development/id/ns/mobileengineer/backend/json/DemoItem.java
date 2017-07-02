
/**
 * Created by Drago on 6/28/2017.
 */

package com.development.id.ns.mobileengineer.backend.json;

import com.google.gson.annotations.SerializedName;

public class DemoItem {
    @SerializedName("image")
    private String imageUrl;
    private String description;
    private String title;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
