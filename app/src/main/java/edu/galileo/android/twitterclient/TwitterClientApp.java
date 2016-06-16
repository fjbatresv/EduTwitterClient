package edu.galileo.android.twitterclient;

import android.app.Application;

import android.os.Build;
import android.support.v4.app.Fragment;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import edu.galileo.android.twitterclient.hashtags.DI.DaggerHashesComponent;
import edu.galileo.android.twitterclient.hashtags.DI.HashesComponent;
import edu.galileo.android.twitterclient.hashtags.DI.HashesModule;
import edu.galileo.android.twitterclient.hashtags.ui.HashesView;
import edu.galileo.android.twitterclient.images.DI.DaggerImagesComponent;
import edu.galileo.android.twitterclient.images.DI.ImagesComponent;
import edu.galileo.android.twitterclient.images.DI.ImagesModule;
import edu.galileo.android.twitterclient.images.adapters.OnItemClickListener;
import edu.galileo.android.twitterclient.images.ui.ImagesView;
import edu.galileo.android.twitterclient.libs.DI.LibsModule;
import io.fabric.sdk.android.Fabric;

/**
 * Created by javie on 14/06/2016.
 */
public class TwitterClientApp extends Application {
    @Override
    public void onCreate() {
        initFabric();
        super.onCreate();
    }

    private void initFabric() {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(
                BuildConfig.TWITTER_KEY, BuildConfig.TWITTER_SECRET
        );
        Fabric.with(this, new Twitter(authConfig));
    }

    public ImagesComponent getImagesComponent(Fragment fragment, ImagesView view, OnItemClickListener listener){
        return DaggerImagesComponent
                .builder()
                .libsModule(new LibsModule(fragment))
                .imagesModule(new ImagesModule(view, listener))
                .build();
    }

    public HashesComponent getHashComponent(Fragment fragment, HashesView view, edu.galileo.android.twitterclient.hashtags.ui.adapters.OnItemClickListener listener){
        return DaggerHashesComponent
                .builder()
                .libsModule(new LibsModule(fragment))
                .hashesModule(new HashesModule(view, listener))
                .build();
    }


}
