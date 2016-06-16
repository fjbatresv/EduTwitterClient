package edu.galileo.android.twitterclient.hashtags;

import edu.galileo.android.twitterclient.hashtags.events.HashEvent;
import edu.galileo.android.twitterclient.images.events.ImagesEvent;

/**
 * Created by javie on 15/06/2016.
 */
public interface HashesPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getHashtagTweetes();
    void onEventMainThread(HashEvent event);
}
