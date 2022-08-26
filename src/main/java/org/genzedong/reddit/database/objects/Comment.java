package org.genzedong.reddit.database.objects;

public class Comment {

    public String id;
    public String author;
    public String body;
    public String parent_comment_id;
    public String post_id;
    public long created_utc;
    public int score;
    public int ups;
    public int downs;

    public Comment (String id, String author, String body, String parentCommentId, String postId, long createdTime, int score, int ups, int downs){
        this.id = id;
        this.author = author;
        this.body = body;
        this.parent_comment_id = parentCommentId;
        this.post_id = postId;
        this.created_utc = createdTime;
        this.score = score;
        this.ups = ups;
        this.downs = downs;
    }
}