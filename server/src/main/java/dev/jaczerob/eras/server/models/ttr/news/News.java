package dev.jaczerob.eras.server.models.ttr.news;

import dev.jaczerob.eras.server.models.ttr.ToontownObject;

public class News extends ToontownObject {
    private String title;
    private int postId;
    private String author;
    private String body;
    private String date;
    private String image;

    public String getTitle() {
        return title;
    }

    public int getPostId() {
        return postId;
    }

    public String getAuthor() {
        return author;
    }

    public String getBody() {
        return body;
    }

    public String getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setPostId(final int postId) {
        this.postId = postId;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public void setBody(final String body) {
        this.body = body;
    }

    public void setDate(final String date) {
        this.date = date;
    }

    public void setImage(final String image) {
        this.image = image;
    }
}
