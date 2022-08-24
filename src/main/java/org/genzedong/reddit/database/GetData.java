package org.genzedong.reddit.database;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class GetData {

    public static List<FullPostData> getData() throws IOException {
        try (InputStreamReader postJson = new InputStreamReader(GetData.class.getResourceAsStream("/json/posts.json"), StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(postJson);
            Type type = new TypeToken<List<Post>>(){}.getType();
            List<Post> posts = gson.fromJson(br, type);
            List<FullPostData> fullPostDataList = new ArrayList<>();
            for (Post post : posts) {
                fullPostDataList.add(new FullPostData(post, getComments(post.id), post.id));
            }
            return fullPostDataList;
        }
    }

    private static List<Comment> getComments(String postId) throws IOException {
        try (InputStreamReader CommentJson = new InputStreamReader(GetData.class.getResourceAsStream("/json/comments.json"), StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(CommentJson);
            Type type = new TypeToken<List<Comment>>() {
            }.getType();
            List<Comment> allComments = gson.fromJson(br, type);
            List<Comment> comments = new ArrayList<>();
            for (Comment comment : allComments) {
                if (comment.postId == postId) {
                    comments.add(comment);
                }
            }

            return comments;
        }
    }
}
