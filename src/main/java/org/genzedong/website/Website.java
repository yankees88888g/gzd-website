package org.genzedong.website;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.staticfiles.Location;
import org.genzedong.reddit.database.objects.FullPostData;
import org.genzedong.reddit.database.GetData;
import org.genzedong.website.gets.Index;
import org.genzedong.website.gets.JsonRedditCommentController;
import org.genzedong.website.gets.JsonRedditController;
import org.genzedong.website.gets.RedditController;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

public class Website {
    public static void runWebsite(int port) throws IOException {
        Javalin app = Javalin.create(config -> {
            config.addStaticFiles(staticFiles -> {
                staticFiles.hostedPath = "/css";
                staticFiles.directory = "/css";
                staticFiles.location = Location.CLASSPATH;
            });
            config.addStaticFiles(staticFiles -> {
                staticFiles.hostedPath = "/js";
                staticFiles.directory = "/js";
                staticFiles.location = Location.CLASSPATH;
            });
        }).start(port);
        String jsonString = "{\"test1\":\"value1\",\"test2\":{\"id\":0,\"name\":\"testName\"}}";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonString);
        app.error(404, ctx -> ctx.json(jsonNode));
        app.get("/reddit/{postId}", RedditController::getPost);
        app.get("/json/reddit/{postId}", JsonRedditController::getPostId);
        app.get("/json/reddit/comments/{postId}", JsonRedditCommentController::getComments);
        app.get("/", new Index());
    }

    private static void inputStream(Context ctx, String file) {
    }

}
//added Javascript rendering and added 2 more json data points I missed.