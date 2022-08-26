package org.genzedong.reddit.database.objects;

import java.net.MalformedURLException;
import java.net.URL;

public class Post {
    public String id;
    public String author;
    public String title;
    public String url;
    public String permalink;
    public String body;
    public long created_utc;//created_utc
    public int score;
    public int ups;
    public int downs;
    public int numComments;

    public Post (String id, String author, String title, String url, String permaLink, String selfText, long createdTime, int score, int ups, int downs, int numComments) throws MalformedURLException {
        this.id = id;
        this.author = author;
        this.title = title;
        this.url = url;//new URL(url);
        this.permalink = permaLink;//new URL("https://www.reddit.com/" + permaLink);
        this.body = selfText;
        this.created_utc = createdTime;
        this.score = score;
        this.ups = ups;
        this.downs = downs;
        this.numComments = numComments;
    }
}
