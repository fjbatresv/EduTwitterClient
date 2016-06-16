package edu.galileo.android.twitterclient.images.ui;

import java.util.List;

import edu.galileo.android.twitterclient.entities.Image;

/**
 * Created by javie on 15/06/2016.
 */
public interface ImagesView {
    void showImages();
    void hideImages();
    void showProgressBar();
    void hideProgressBar();

    void onError(String error);
    void setContent(List<Image> imgs);
}
