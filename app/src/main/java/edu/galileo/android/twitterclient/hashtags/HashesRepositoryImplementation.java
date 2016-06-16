package edu.galileo.android.twitterclient.hashtags;

import com.twitter.sdk.android.core.models.HashtagEntity;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.galileo.android.twitterclient.api.CustomTwitterApiClient;
import edu.galileo.android.twitterclient.entities.Hash;
import edu.galileo.android.twitterclient.entities.Image;
import edu.galileo.android.twitterclient.hashtags.events.HashEvent;
import edu.galileo.android.twitterclient.images.ImagesRepository;
import edu.galileo.android.twitterclient.images.events.ImagesEvent;
import edu.galileo.android.twitterclient.libs.base.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by javie on 15/06/2016.
 */
public class HashesRepositoryImplementation implements HashesRepository {
    private EventBus bus;
    private CustomTwitterApiClient apiClient;
    public final static int tweetCount = 100;

    public HashesRepositoryImplementation(EventBus bus, CustomTwitterApiClient apiClient) {
        this.bus = bus;
        this.apiClient = apiClient;
    }

    @Override
    public void getHashes() {
        Callback<List<Tweet>> callback = new Callback<List<Tweet>>() {
            @Override
            public void success(List<Tweet> tweets, Response response) {
                List<Hash> imgs = new ArrayList<Hash>();
                for(Tweet tweet : tweets){
                    if(containsHashes(tweet)){
                        Hash img = new Hash();
                        img.setId(tweet.idStr);
                        img.setFavoriteCount(tweet.favoriteCount);
                        img.setTweetText(tweet.text);
                        List<String> hashTags = new ArrayList<String>();
                        for (HashtagEntity hashtagEntity : tweet.entities.hashtags){
                            hashTags.add(hashtagEntity.text);
                        }
                        img.setTags(hashTags);
                        imgs.add(img);
                    }
                }
                Collections.sort(imgs, new Comparator<Hash>() {
                    @Override
                    public int compare(Hash image, Hash t1) {
                        return t1.getFavoriteCount() - image.getFavoriteCount();
                    }
                });
                post(imgs, null);
            }

            @Override
            public void failure(RetrofitError error) {
                post(null, error.getLocalizedMessage());
            }
        };
        apiClient.getTimeLineService().homeTimeLine(tweetCount, true, true, true, true, callback);
    }

    private boolean containsHashes(Tweet tweet){
        return tweet.entities != null && tweet.entities.hashtags != null
                && !tweet.entities.hashtags.isEmpty();
    }

    private void post(List<Hash> imgs, String error){
        bus.post(new HashEvent(error, imgs));
    }
}
