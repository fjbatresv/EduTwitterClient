package edu.galileo.android.twitterclient.images.events;

import java.util.List;

import edu.galileo.android.twitterclient.entities.Image;

/**
 * Created by javie on 15/06/2016.
 */
public class ImagesEvent {
    private String error;
    private List<Image> imgs;

    public ImagesEvent() {
    }

    public ImagesEvent(String error, List<Image> imgs) {
        this.error = error;
        this.imgs = imgs;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Image> getImgs() {
        return imgs;
    }

    public void setImgs(List<Image> imgs) {
        this.imgs = imgs;
    }
}
