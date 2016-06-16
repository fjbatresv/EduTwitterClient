package edu.galileo.android.twitterclient.entities;

/**
 * Created by javie on 15/06/2016.
 */
public class Image {
    private String id;
    private String imageUrl;
    private String tweetText;
    private int favoriteCount;
    private final static String baseTweetUrl = "https://twitter.com/null/status/";

    public Image(String id, String imageUrl, String tweetText, int favoriteCount) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.tweetText = tweetText;
        this.favoriteCount = favoriteCount;
    }

    public Image() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
        if(o instanceof Image){
            Image tmp = (Image) o;
            equals = this.id.equals(tmp.getId()) && this.imageUrl.equals(tmp.getImageUrl());
        }
        return equals;
    }
}
