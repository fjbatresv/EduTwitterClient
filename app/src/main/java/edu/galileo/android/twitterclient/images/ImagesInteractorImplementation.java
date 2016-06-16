package edu.galileo.android.twitterclient.images;

/**
 * Created by javie on 15/06/2016.
 */
public class ImagesInteractorImplementation implements ImagesInteractor {
    private ImagesRepository repo;

    public ImagesInteractorImplementation(ImagesRepository repo) {
        this.repo = repo;
    }

    @Override
    public void execute() {
        repo.getImages();
    }
}
