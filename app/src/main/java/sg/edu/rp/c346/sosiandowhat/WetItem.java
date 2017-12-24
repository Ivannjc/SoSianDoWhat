package sg.edu.rp.c346.sosiandowhat;

import java.io.Serializable;

/**
 * Created by 15017608 on 24/12/2017.
 */

public class WetItem implements Serializable {
    private int wetId;
    private String wetTitle;

    public WetItem(int wetId, String wetTitle){
        this.wetId = wetId;
        this.wetTitle = wetTitle;
    }
    public int getWetId() {
        return wetId;
    }

    public void setWetId(int wetId) {
        this.wetId = wetId;
    }

    public String getWetTitle() {
        return wetTitle;
    }

    public void setWetTitle(String wetTitle) {
        this.wetTitle = wetTitle;
    }


    @Override
    public String toString() {
        return "Item: " + wetTitle;
    }
}

