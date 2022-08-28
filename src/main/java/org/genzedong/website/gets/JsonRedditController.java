package org.genzedong.website.gets;

import io.javalin.http.Context;
import org.genzedong.Main;
import org.genzedong.reddit.database.objects.Comment;
import org.genzedong.reddit.database.objects.FullPostData;
import org.genzedong.reddit.database.objects.Post;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonRedditController {
    public static void getPostId(Context ctx) {
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        for(Post post : Main.postData){
            if(post.id.contains(ctx.pathParam("postId"))){
                obj.put("id", post.id);
                obj.put("author", post.author);
                obj.put("title", post.title);
                obj.put("body", post.body);
                obj.put("createdTime", post.created_utc);
                obj.put("permalink", post.permalink);
                obj.put("score", post.score);
                obj.put("ups", post.ups);
                obj.put("downs", post.downs);
                obj.put("numComments", post.numComments);
                obj.put("url", post.url);
                obj.put("comments", arr);

                ctx.json(obj);
                return;
            }
        }
        ctx.result("No Post :(");
    }
}
