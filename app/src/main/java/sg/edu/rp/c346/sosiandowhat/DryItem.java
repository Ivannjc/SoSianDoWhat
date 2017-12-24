package sg.edu.rp.c346.sosiandowhat;

import java.io.Serializable;

/**
 * Created by 15017608 on 20/12/2017.
 */

public class DryItem implements Serializable {
    private int dryId;
    private String dryTitle;

    public DryItem(int dryId, String dryTitle){
        this.dryId = dryId;
        this.dryTitle = dryTitle;
    }
    public int getDryId() {
        return dryId;
    }

    public void setDryId(int dryId) {
        this.dryId = dryId;
    }

    public String getDryTitle() {
        return dryTitle;
    }

    public void setDryTitle(String dryTitle) {
        this.dryTitle = dryTitle;
    }


    @Override
    public String toString() {
        return "Item: " + dryTitle;
    }
}
