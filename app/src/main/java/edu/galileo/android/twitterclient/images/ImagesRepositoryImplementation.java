package edu.galileo.android.twitterclient.images;

import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.galileo.android.twitterclient.api.CustomTwitterApiClient;
import edu.galileo.android.twitterclient.entities.Hash;
import edu.galileo.android.twitterclient.entities.Image;
import edu.galileo.android.twitterclient.hashtags.HashesRepository;
import edu.galileo.android.twitterclient.hashtags.events.HashEvent;
import edu.galileo.android.twitterclient.images.events.ImagesEvent;
import edu.galileo.android.twitterclient.libs.base.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by javie on 15/06/2016.
 */
public class ImagesRepositoryImplementation implements ImagesRepository {
    private EventBus bus;
    private CustomTwitterApiClient apiClient;
    public final static int tweetCount = 100;

    public ImagesRepositoryImplementation(EventBus bus, CustomTwitterApiClient apiClient) {
        this.bus = bus;
        this.apiClient = apiClient;
    }

    @Override
    public void getImages() {
        Callback<List<Tweet>> callback = new Callback<List<Tweet>>() {
            @Override
            public void success(List<Tweet> tweets, Response response) {
                List<Image> imgs = new ArrayList<Image>();
                for(Tweet tweet : tweets){
                    if(containsImages(tweet)){
                        Image img = new Image();
                        img.setId(tweet.idStr);
                        img.setFavoriteCount(tweet.favoriteCount);
                        String tweetTxt = tweet.text;
                        int index = tweetTxt.indexOf("http");
                        if(index > 0){
                            tweetTxt = tweetTxt.substring(0, index);
                        }
                        img.setTweetText(tweetTxt);
                        img.setImageUrl(tweet.entities.media.get(0).mediaUrl);
                        imgs.add(img);
                    }
                }
                Collections.sort(imgs, new Comparator<Image>() {
                    @Override
                    public int compare(Image image, Image t1) {
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

    private boolean containsImages(Tweet tweet){
        return tweet.entities != null && tweet.entities.media != null
                && !tweet.entities.media.isEmpty();
    }

    private void post(List<Image> imgs, String error){
        bus.post(new ImagesEvent(error, imgs));
    }
}
