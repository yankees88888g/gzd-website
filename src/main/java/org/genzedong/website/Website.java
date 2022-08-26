package org.genzedong.website;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.genzedong.reddit.database.objects.FullPostData;
import org.genzedong.reddit.database.GetData;
import org.genzedong.website.gets.JsonRedditCommentController;
import org.genzedong.website.gets.JsonRedditController;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

public class Website {
    public static void runWebsite(int port) throws IOException {
        Javalin app = Javalin.create().start(port);
        String jsonString = "{\"test1\":\"value1\",\"test2\":{\"id\":0,\"name\":\"testName\"}}";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonString);
        app.error(404, ctx -> ctx.json(jsonNode));
        app.get("/json/reddit/{postId}", JsonRedditController::getPostId);
        app.get("/json/reddit/comments/{postId}", JsonRedditCommentController::getComments);


        /*app.get("/reddit", new Reddit());
app.routes(() -> {
            path("/json/reddit", () -> {
                get(UserController::getAllUsers);
                post(UserController::createUser);
                path(":id", () -> {
                    get(UserController::getUser);
                    patch(UserController::updateUser);
                    delete(UserController::deleteUser);
                });
                ws("events", userController::webSocketEvents);
            });
        });

        List<FullPostData> postData = GetData.getData();
        for(int i = 0; i < postData.size(); i++) {
            app.get("/json/reddit/" + postData.get(i).id, new ViewPost(postData.get(i)));
        }
    }

    private static class Reddit implements Handler {
        @Override
        public void handle(@NotNull Context ctx) throws Exception {
            List<FullPostData> postData = GetData.getPosts();
            StringBuilder stringBuilder = new StringBuilder();
            for (FullPostData post : postData) {
                String comment;
                try {
                    comment = post.comments.get(0).body;
                } catch (Exception e) {
                    comment = "null";
                }
                //stringBuilder.append("Post author: " + post.post.author + " Number of comments: " + post.post.numComments + "A comment: " + comment);
            }
            ctx.result(stringBuilder.toString());
        }
    }

    private static class ViewPost implements Handler {
        FullPostData fullPostData;
        public ViewPost(FullPostData fullPostData) {
            this.fullPostData = fullPostData;
        }

        @Override
        public void handle(@NotNull Context ctx) throws Exception {
            ctx.result(fullPostData.id);
        }*/
    }
}
