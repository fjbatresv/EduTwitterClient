package edu.galileo.android.twitterclient.images.DI;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.twitterclient.images.ImagesPresenter;
import edu.galileo.android.twitterclient.images.ui.ImagesFragment;
import edu.galileo.android.twitterclient.libs.DI.LibsModule;

/**
 * Created by javie on 15/06/2016.
 */
@Singleton
@Component(modules = {ImagesModule.class, LibsModule.class})
public interface ImagesComponent {
    //Opcion 1:
    void inject(ImagesFragment fragment);
    //Opcion 2:
    ImagesPresenter getPresenter();
}
