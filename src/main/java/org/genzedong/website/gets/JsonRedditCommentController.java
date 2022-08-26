package org.genzedong.website.gets;

import io.javalin.http.Context;
import org.genzedong.reddit.database.GetData;
import org.genzedong.reddit.database.objects.Comment;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.List;

public class JsonRedditCommentController {

    public static void getComments(Context ctx) throws IOException {
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        List<Comment> comments = GetData.getComments(ctx.pathParam("postId"));
        for (Comment c : comments) {
            System.out.println(c.author);
            obj.put("id", c.id);
            obj.put("author", c.author);
            obj.put("body", c.body);
            obj.put("createdTime", c.created_utc);
            obj.put("score", c.score);
            obj.put("parentCommentId", c.parent_comment_id);
            obj.put("ups", c.ups);
            obj.put("downs", c.downs);

            arr.add(obj);
            obj = new JSONObject();
        }
        ctx.json(arr);
    }
}
