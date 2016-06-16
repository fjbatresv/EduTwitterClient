package edu.galileo.android.twitterclient.hashtags.events;

import java.util.List;

import edu.galileo.android.twitterclient.entities.Hash;
import edu.galileo.android.twitterclient.entities.Image;

/**
 * Created by javie on 15/06/2016.
 */
public class HashEvent {
    private String error;
    private List<Hash> imgs;

    public HashEvent() {
    }

    public HashEvent(String error, List<Hash> imgs) {
        this.error = error;
        this.imgs = imgs;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Hash> getImgs() {
        return imgs;
    }

    public void setImgs(List<Hash> imgs) {
        this.imgs = imgs;
    }
}
