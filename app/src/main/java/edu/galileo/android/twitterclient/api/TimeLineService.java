package edu.galileo.android.twitterclient.api;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;


import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by javie on 14/06/2016.
 */
public interface TimeLineService {
    @GET("/1.1/statuses/home_timeline.json")
    void homeTimeLine(
            @Query("count") Integer count,
            @Query("trim_user") boolean trim_user,
            @Query("exclude_replies") boolean exclude_replies,
            @Query("contributor_details") boolean contributor_details,
            @Query("include_entities") boolean include_entities,
            Callback<List<Tweet>> callback);
}
