package edu.galileo.android.twitterclient.libs.DI;

import android.support.v4.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.twitterclient.libs.GlideImageLoader;
import edu.galileo.android.twitterclient.libs.GreenRobotEventBus;
import edu.galileo.android.twitterclient.libs.base.EventBus;
import edu.galileo.android.twitterclient.libs.base.ImageLoader;


/**
 * Created by javie on 15/06/2016.
 */
@Module
public class LibsModule {

    private Fragment fragment;

    public LibsModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Singleton
    @Provides
    public EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus){
        return new GreenRobotEventBus(eventBus);
    }

    @Singleton
    @Provides
    public org.greenrobot.eventbus.EventBus providesLibraryEventBus(){
        return org.greenrobot.eventbus.EventBus.getDefault();
    }

    @Singleton
    @Provides
    public ImageLoader providesImageLoader(RequestManager manager){
        return new GlideImageLoader(manager);
    }

    @Singleton
    @Provides
    public RequestManager providesRequestManager(Fragment fragment){
        return Glide.with(fragment);
    }

    @Singleton
    @Provides
    public Fragment providesFragment(){
        return this.fragment;
    }
}
