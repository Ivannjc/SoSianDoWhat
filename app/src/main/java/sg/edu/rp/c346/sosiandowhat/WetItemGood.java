package sg.edu.rp.c346.sosiandowhat;

import java.io.Serializable;

/**
 * Created by 15017608 on 24/12/2017.
 */

public class WetItemGood implements Serializable {
    private int wetIdGood;
    private String wetTitleGood;

    public WetItemGood(int wetIdGood, String wetTitleGood){
        this.wetIdGood = wetIdGood;
        this.wetTitleGood = wetTitleGood;
    }
    public int getWetIdGood() {
        return wetIdGood;
    }

    public void setWetIdGood(int wetIdGood) {
        this.wetIdGood = wetIdGood;
    }

    public String getWetTitleGood() {
        return wetTitleGood;
    }

    public void setWetTitleGood(String wetTitleGood) {
        this.wetTitleGood = wetTitleGood;
    }


    @Override
    public String toString() {
        return "Item: " + wetTitleGood;
    }
}
