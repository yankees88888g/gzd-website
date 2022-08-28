package org.genzedong.website.gets;

import io.javalin.http.Context;
import org.genzedong.website.util.GetHtml;

import java.io.IOException;

public class RedditController {
    public static void getPost(Context ctx) throws IOException {
        ctx.html(GetHtml.getHtml("post.html"));
    }
}
