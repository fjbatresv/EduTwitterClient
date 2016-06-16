package edu.galileo.android.twitterclient.hashtags.DI;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.twitterclient.hashtags.HashesPresenter;
import edu.galileo.android.twitterclient.hashtags.ui.HashtagsFragment;
import edu.galileo.android.twitterclient.images.ImagesPresenter;
import edu.galileo.android.twitterclient.images.ui.ImagesFragment;
import edu.galileo.android.twitterclient.libs.DI.LibsModule;

/**
 * Created by javie on 15/06/2016.
 */
@Singleton
@Component(modules = {HashesModule.class, LibsModule.class})
public interface HashesComponent {
    //Opcion 1:
    void inject(HashtagsFragment fragment);
    //Opcion 2:
    HashesPresenter getPresenter();
}
