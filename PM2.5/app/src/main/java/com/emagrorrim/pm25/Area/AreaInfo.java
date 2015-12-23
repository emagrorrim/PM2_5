package com.emagrorrim.pm25.Area;

import com.google.gson.annotations.SerializedName;

import retrofit.http.GET;

/**
 * Created by apple on 15/12/20.
 */
public class AreaInfo {

    public AreaInfo(String positionName,String quality,int pm25) {
        this.pm25 = pm25;
        this.quality = quality;
        this.positionName = positionName;
    }


    @SerializedName("position_name")
    private String positionName;

    @SerializedName("quality")
    private String quality;

    @SerializedName("pm2_5")
    private int pm25;

    public int getPm25() { return pm25; }

    public void setPm25(int pm25) { this.pm25 = pm25; }

    public String getPositionName() {
        return positionName;
    }

    public String getQuality() {
        return quality;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public void setQuality(String quality) { this.quality = quality; }

    public String toString() {
        return "Position Name: " + getPositionName() + ".\n"
                + "PM2.5: " + getPm25() + ".\n"
                + "Quality: " + getQuality() + ".\n";
    }
}
