package edu.galileo.android.twitterclient.images;

import org.greenrobot.eventbus.Subscribe;

import edu.galileo.android.twitterclient.images.events.ImagesEvent;
import edu.galileo.android.twitterclient.images.ui.ImagesView;
import edu.galileo.android.twitterclient.libs.base.EventBus;

/**
 * Created by javie on 15/06/2016.
 */
public class ImagesPresenterImplementation implements ImagesPresenter {
    private EventBus bus;
    private ImagesView view;
    private  ImagesInteractor interactor;

    public ImagesPresenterImplementation(EventBus bus, ImagesView view, ImagesInteractor interactor) {
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
    public void getImageTweetes() {
        if(view != null){
            view.hideImages();
            view.showProgressBar();
        }
        interactor.execute();
    }

    @Subscribe
    @Override
    public void onEventMainThread(ImagesEvent event) {
        if(view != null){
            view.showImages();
            view.hideProgressBar();
            if(event.getError() != null){
                view.onError(event.getError());
            }else{
                view.setContent(event.getImgs());
            }
        }
    }
}
