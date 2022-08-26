package org.genzedong;

import org.genzedong.reddit.database.objects.Comment;
import org.genzedong.reddit.database.GetData;
import org.genzedong.reddit.database.objects.Post;
import org.genzedong.website.Website;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static List<Post> postData;
    public static HashMap<String, List<Comment>> commentData;
    public static void main(String[] args) throws IOException {
        postData = GetData.getPosts();
        Website.runWebsite(1300);

    }
}
