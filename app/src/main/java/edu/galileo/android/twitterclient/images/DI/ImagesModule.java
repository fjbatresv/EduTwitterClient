package edu.galileo.android.twitterclient.images.DI;

import android.widget.ImageView;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Session;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.twitterclient.api.CustomTwitterApiClient;
import edu.galileo.android.twitterclient.entities.Image;
import edu.galileo.android.twitterclient.images.ImagesInteractor;
import edu.galileo.android.twitterclient.images.ImagesInteractorImplementation;
import edu.galileo.android.twitterclient.images.ImagesPresenter;
import edu.galileo.android.twitterclient.images.ImagesPresenterImplementation;
import edu.galileo.android.twitterclient.images.ImagesRepository;
import edu.galileo.android.twitterclient.images.ImagesRepositoryImplementation;
import edu.galileo.android.twitterclient.images.adapters.ImagesAdapter;
import edu.galileo.android.twitterclient.images.adapters.OnItemClickListener;
import edu.galileo.android.twitterclient.images.ui.ImagesView;
import edu.galileo.android.twitterclient.libs.base.EventBus;
import edu.galileo.android.twitterclient.libs.base.ImageLoader;

/**
 * Created by javie on 15/06/2016.
 */
@Module
public class ImagesModule {
    private ImagesView view;
    private OnItemClickListener listener;

    public ImagesModule(ImagesView view, OnItemClickListener listener) {
        this.view = view;
        this.listener = listener;
    }

    @Provides
    @Singleton
    ImagesAdapter providesAdapter(List<Image> imgs, ImageLoader imageLoader, OnItemClickListener clickListener){
        return  new ImagesAdapter(imgs, imageLoader, clickListener);
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener(){
        return this.listener;
    }

    @Provides
    @Singleton
    List<Image> providesItemList(){
        return new ArrayList<Image>();
    }

    @Provides
    @Singleton
    ImagesPresenter providesImagesPresenter(EventBus bus, ImagesView view, ImagesInteractor interactor){
        return new ImagesPresenterImplementation(bus, view, interactor);
    }

    @Provides
    @Singleton
    ImagesView provideImagesView(){
        return this.view;
    }
    @Provides
    @Singleton
    ImagesInteractor provideImagesInteractor(ImagesRepository repo){
        return new ImagesInteractorImplementation(repo);
    }
    @Provides
    @Singleton
    ImagesRepository providesImagesRepository(EventBus bus, CustomTwitterApiClient apiClient){
        return new ImagesRepositoryImplementation(bus, apiClient);
    }

    @Provides
    @Singleton
    CustomTwitterApiClient providesApiClient(Session session){
        return new CustomTwitterApiClient(session);
    }

    @Provides
    @Singleton
    Session providesTwitterSession(){
        return Twitter.getSessionManager().getActiveSession();
    }
}
