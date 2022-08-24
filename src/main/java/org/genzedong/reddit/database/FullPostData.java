package org.genzedong.reddit.database;

import java.util.List;

public class FullPostData {
    public Post post;
    public List<Comment> comments;
    public String id;

    public FullPostData(Post post, List<Comment> comments, String id){
        this.post = post;
        this.comments = comments;
        this.id = id;
    }
}
