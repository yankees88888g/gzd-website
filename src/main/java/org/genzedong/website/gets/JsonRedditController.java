package org.genzedong.website.gets;

import io.javalin.http.Context;
import org.genzedong.Main;
import org.genzedong.reddit.database.Comment;
import org.genzedong.reddit.database.FullPostData;
import org.genzedong.reddit.database.Post;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonRedditController {
    public static void getPostId(Context ctx) {
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        for(FullPostData fullPostData : Main.data){
            if(fullPostData.id.contains(ctx.pathParam("postId"))){
                for(int i = 0 ; i< fullPostData.comments.size() ; i++)
                {
                    Comment c = fullPostData.comments.get(i);

                    obj.put("id", c.id);
                    obj.put("author", c.author);
                    obj.put("body", c.body);
                    obj.put("createdTime", c.createdTime);
                    obj.put("score", c.score);
                    obj.put("parentCommentId", c.parentCommentId);
                    obj.put("ups", c.ups);
                    obj.put("downs", c.downs);

                    arr.add(obj);
                    obj = new JSONObject();
                }
                Post post = fullPostData.post;
                obj.put("id", post.id);
                obj.put("author", post.author);
                obj.put("body", post.body);
                obj.put("createdTime", post.createdTime);
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
