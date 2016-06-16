package edu.galileo.android.twitterclient.hashtags.ui;

import java.util.List;

import edu.galileo.android.twitterclient.entities.Hash;
import edu.galileo.android.twitterclient.entities.Image;

/**
 * Created by javie on 15/06/2016.
 */
public interface HashesView {
    void showList();
    void hideList();
    void showProgressBar();
    void hideProgressBar();

    void onError(String error);
    void setContent(List<Hash> imgs);
}
