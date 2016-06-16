package edu.galileo.android.twitterclient.hashtags;

import org.greenrobot.eventbus.Subscribe;

import edu.galileo.android.twitterclient.hashtags.events.HashEvent;
import edu.galileo.android.twitterclient.hashtags.ui.HashesView;
import edu.galileo.android.twitterclient.images.ImagesInteractor;
import edu.galileo.android.twitterclient.images.ImagesPresenter;
import edu.galileo.android.twitterclient.images.events.ImagesEvent;
import edu.galileo.android.twitterclient.images.ui.ImagesView;
import edu.galileo.android.twitterclient.libs.base.EventBus;

/**
 * Created by javie on 15/06/2016.
 */
public class HashesPresenterImplementation implements HashesPresenter {
    private EventBus bus;
    private HashesView view;
    private  HashesInteractor interactor;

    public HashesPresenterImplementation(EventBus bus, HashesView view, HashesInteractor interactor) {
        this.bus = bus;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onResume() {
        bus.register(this);
    }

    @Override
    public void onPause() {
        bus.unRegister(this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void getHashtagTweetes() {
        if(view != null){
            view.hideList();
            view.showProgressBar();
        }
        interactor.execute();
    }

    @Subscribe
    @Override
    public void onEventMainThread(HashEvent event) {
        if(view != null){
            view.showList();
            view.hideProgressBar();
            if(event.getError() != null){
                view.onError(event.getError());
            }else{
                view.setContent(event.getImgs());
            }
        }
    }
}
