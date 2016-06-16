package edu.galileo.android.twitterclient.hashtags;

import edu.galileo.android.twitterclient.images.ImagesInteractor;
import edu.galileo.android.twitterclient.images.ImagesRepository;

/**
 * Created by javie on 15/06/2016.
 */
public class HashesInteractorImplementation implements HashesInteractor {
    private HashesRepository repo;

    public HashesInteractorImplementation(HashesRepository repo) {
        this.repo = repo;
    }

    @Override
    public void execute() {
        repo.getHashes();
    }
}
