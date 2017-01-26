package com.simplifiedlauncher.gallery;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;

import java.util.UUID;

/**
 * Created by dtrop on 20/01/2017.
 */

public class OurImage {
    private UUID id;
    private Bitmap sourceImage;
    private String path;
    final int THUMBSIZE = 128;



    public OurImage(Bitmap sourceImage,String path){
        id=UUID.randomUUID();
        this.sourceImage= ThumbnailUtils.extractThumbnail(sourceImage ,THUMBSIZE, THUMBSIZE);
        this.path=path;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
