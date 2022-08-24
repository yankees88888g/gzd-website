package org.genzedong.reddit.database;

public class Comment {

    public String id;
    public String author;
    public String body;
    public String parentCommentId;//parent_comment_id
    public String postId; //post_id
    public long createdTime;//created_utc
    public int score;
    public int ups;
    public int downs;

    public Comment (String id, String author, String body, String parentCommentId, String postId, long createdTime, int score, int ups, int downs){
        this.id = id;
        this.author = author;
        this.body = body;
        this.parentCommentId = parentCommentId;
        this.postId = postId;
        this.createdTime = createdTime;
        this.score = score;
        this.ups = ups;
        this.downs = downs;
    }
}