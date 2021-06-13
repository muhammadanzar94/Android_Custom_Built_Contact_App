/*contact class that contain Name, Number and Image of a user*/
package com.example.custombuiltcontactapp;

import android.graphics.Bitmap;
public class contact {

    private String cName;
    private String cNumber;
    private Bitmap cImage;

    public contact(String cName, String cNumber, Bitmap cImage) {
        this.cName = cName;
        this.cNumber = cNumber;
        this.cImage = cImage;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcNumber() {
        return cNumber;
    }

    public void setcNumber(String cNumber) {
        this.cNumber = cNumber;
    }

    public Bitmap getcImage() {
        return cImage;
    }

    public void setcImage(Bitmap cImage) {
        this.cImage = cImage;
    }
}
