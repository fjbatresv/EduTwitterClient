package edu.galileo.android.twitterclient.hashtags.DI;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Session;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.twitterclient.api.CustomTwitterApiClient;
import edu.galileo.android.twitterclient.entities.Hash;
import edu.galileo.android.twitterclient.hashtags.HashesInteractor;
import edu.galileo.android.twitterclient.hashtags.HashesInteractorImplementation;
import edu.galileo.android.twitterclient.hashtags.HashesPresenter;
import edu.galileo.android.twitterclient.hashtags.HashesPresenterImplementation;
import edu.galileo.android.twitterclient.hashtags.HashesRepository;
import edu.galileo.android.twitterclient.hashtags.HashesRepositoryImplementation;
import edu.galileo.android.twitterclient.hashtags.ui.HashesView;
import edu.galileo.android.twitterclient.hashtags.ui.adapters.HashAdapter;
import edu.galileo.android.twitterclient.hashtags.ui.adapters.OnItemClickListener;
import edu.galileo.android.twitterclient.libs.base.EventBus;

/**
 * Created by javie on 15/06/2016.
 */
@Module
public class HashesModule {
    private HashesView view;
    private OnItemClickListener listener;

    public HashesModule(HashesView view, OnItemClickListener listener) {
        this.view = view;
        this.listener = listener;
    }

    @Provides
    @Singleton
    HashAdapter providesAdapter(List<Hash> imgs, OnItemClickListener clickListener){
        return  new HashAdapter(imgs, clickListener);
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener(){
        return this.listener;
    }

    @Provides
    @Singleton
    List<Hash> providesItemList(){
        return new ArrayList<Hash>();
    }

    @Provides
    @Singleton
    HashesPresenter providesImagesPresenter(EventBus bus, HashesView view, HashesInteractor interactor){
        return new HashesPresenterImplementation(bus, view, interactor);
    }

    @Provides
    @Singleton
    HashesView provideImagesView(){
        return this.view;
    }
    @Provides
    @Singleton
    HashesInteractor provideImagesInteractor(HashesRepository repo){
        return new HashesInteractorImplementation(repo);
    }
    @Provides
    @Singleton
    HashesRepository providesImagesRepository(EventBus bus, CustomTwitterApiClient apiClient){
        return new HashesRepositoryImplementation(bus, apiClient);
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
