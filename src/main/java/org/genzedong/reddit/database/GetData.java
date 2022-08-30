package org.genzedong.reddit.database;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.genzedong.reddit.database.objects.Comment;
import org.genzedong.reddit.database.objects.Post;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class GetData {

    public static List<Post> getPosts() throws IOException {
        try (InputStreamReader postJson = new InputStreamReader(GetData.class.getResourceAsStream("/json/posts.json"), StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(postJson);
            Type type = new TypeToken<List<Post>>(){}.getType();
            List<Post> posts = gson.fromJson(br, type);
            return posts;
        }
    }

    public static List<Comment> getComments(String postId) throws IOException {
        try (InputStreamReader CommentJson = new InputStreamReader(GetData.class.getResourceAsStream("/json/comments.json"), StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(CommentJson);
            Type type = new TypeToken<List<Comment>>() {
            }.getType();
            List<Comment> allComments = gson.fromJson(br, type);
            List<Comment> comments = new ArrayList<>();
            for (Comment comment : allComments) {
                    if (comment.post_id.equals(postId)) {
                        if (comment.parent_comment_id == null){
                            comment.subComment = false;
                        } else {
                            comment.subComment = true;
                        }
                        comments.add(comment);
                    }
            }
            return comments;
        }
    }
}