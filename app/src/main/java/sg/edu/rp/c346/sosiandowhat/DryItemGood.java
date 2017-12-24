package sg.edu.rp.c346.sosiandowhat;

import java.io.Serializable;

/**
 * Created by 15017608 on 24/12/2017.
 */

public class DryItemGood implements Serializable {
    private int dryIdGood;
    private String dryTitleGood;

    public DryItemGood(int dryIdGood, String dryTitleGood){
        this.dryIdGood = dryIdGood;
        this.dryTitleGood = dryTitleGood;
    }
    public int getDryIdGood() {
        return dryIdGood;
    }

    public void setDryIdGood(int dryIdGood) {
        this.dryIdGood = dryIdGood;
    }

    public String getDryTitleGood() {
        return dryTitleGood;
    }

    public void setDryTitleGood(String dryTitleGood) {
        this.dryTitleGood = dryTitleGood;
    }


    @Override
    public String toString() {
        return "Item: " + dryTitleGood;
    }
}
