package edu.galileo.android.twitterclient.entities;

import java.util.List;

/**
 * Created by javie on 15/06/2016.
 */
public class Hash {
    private String id;
    private List<String> tags;
    private String tweetText;
    private int favoriteCount;
    private final static String baseTweetUrl = "https://twitter.com/null/status/";

    public Hash(String id, List<String> tags, String tweetText, int favoriteCount) {
        this.id = id;
        this.tags = tags;
        this.tweetText = tweetText;
        this.favoriteCount = favoriteCount;
    }

    public Hash() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getTweetText() {
        return tweetText;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public static String getBaseTweetUrl() {
        return baseTweetUrl;
    }

    public String getTweetUrl(){
        return this.baseTweetUrl + this.id;
    }

    @Override
    public boolean equals(Object o) {
        boolean equals = false;
        if(o instanceof Hash){
            Hash tmp = (Hash) o;
            equals = this.id.equals(tmp.getId());
        }
        return equals;
    }
}
