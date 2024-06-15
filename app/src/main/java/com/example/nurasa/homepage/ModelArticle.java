package com.example.nurasa.homepage;

public class ModelArticle {
    private String imgThumb; // URL gambar dari Firebase Storage
    private String tvPlaceName;
    private String tvVote;

    public ModelArticle() {
        // Default constructor required for calls to DataSnapshot.getValue(ModelArticle.class)
    }

    public ModelArticle(String imgThumb, String tvPlaceName, String tvVote) {
        this.imgThumb = imgThumb;
        this.tvPlaceName = tvPlaceName;
        this.tvVote = tvVote;
    }

    public String getImgThumb() {
        return imgThumb;
    }

    public void setImgThumb(String imgThumb) {
        this.imgThumb = imgThumb;
    }

    public String getTvPlaceName() {
        return tvPlaceName;
    }

    public void setTvPlaceName(String tvPlaceName) {
        this.tvPlaceName = tvPlaceName;
    }

    public String getTvVote() {
        return tvVote;
    }

    public void setTvVote(String tvVote) {
        this.tvVote = tvVote;
    }
}
