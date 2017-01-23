package com.simplifiedlauncher.gallery;

/**
 * Created by dtrop on 23/01/2017.
 */

public class itemOfImages {
    OurImage firstImage;
    OurImage secondImage;
    OurImage thirdImage;

    public itemOfImages(OurImage firstImage, OurImage secondImage, OurImage thirdImage) {
        this.firstImage = firstImage;
        this.secondImage = secondImage;
        this.thirdImage = thirdImage;
    }

    public OurImage getFirstImage() {
        return firstImage;
    }

    public void setFirstImage(OurImage firstImage) {
        this.firstImage = firstImage;
    }

    public OurImage getSecondImage() {
        return secondImage;
    }

    public void setSecondImage(OurImage secondImage) {
        this.secondImage = secondImage;
    }

    public OurImage getThirdImage() {
        return thirdImage;
    }

    public void setThirdImage(OurImage thirdImage) {
        this.thirdImage = thirdImage;
    }

    @Override
    public String toString() {
        return "itemOfImages{" +
                "firstImage=" + firstImage.getSourceImage() +
                ", secondImage=" + secondImage.getSourceImage() +
                ", thirdImage=" + thirdImage.getSourceImage() +
                '}';
    }
}

