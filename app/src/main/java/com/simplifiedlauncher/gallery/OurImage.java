package com.simplifiedlauncher.gallery;

import android.graphics.Bitmap;

import java.util.UUID;

/**
 * Created by dtrop on 20/01/2017.
 */

public class OurImage {
    private UUID id;
    private Bitmap sourceImage;
    public OurImage(Bitmap sourceImage){
        id=UUID.randomUUID();
        this.sourceImage=sourceImage;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Bitmap getSourceImage() {
        return sourceImage;
    }

    public void setSourceImage(Bitmap sourceImage) {
        this.sourceImage = sourceImage;
    }
}
