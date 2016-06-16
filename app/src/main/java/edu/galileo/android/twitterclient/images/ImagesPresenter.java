package edu.galileo.android.twitterclient.images;

import edu.galileo.android.twitterclient.images.events.ImagesEvent;

/**
 * Created by javie on 15/06/2016.
 */
public interface ImagesPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getImageTweetes();
    void onEventMainThread(ImagesEvent event);
}
