package org.genzedong.website;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.staticfiles.Location;import io.javalin.ssl.plugin.SSLPlugin;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.genzedong.reddit.database.objects.FullPostData;
import org.genzedong.reddit.database.GetData;
import org.genzedong.website.gets.Index;
import org.genzedong.website.gets.JsonRedditCommentController;
import org.genzedong.website.gets.JsonRedditController;
import org.genzedong.website.gets.RedditController;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Website {
    public static void runWebsite(int port) throws IOException {
        Javalin app = Javalin.create(config -> {
            /*config.server(() -> {
                Server server = new Server();
                ServerConnector sslConnector = new ServerConnector(server, getSslContextFactory());
                sslConnector.setPort(4443);
                ServerConnector connector = new ServerConnector(server);
                connector.setPort(8080);
                server.setConnectors(new Connector[]{sslConnector, connector});
                return server;
        config.enforceSsl = false;
            });
            config.plugins.register(new SSLPlugin(ssl-> {
                ssl.sslPort = 443;
               ssl.pemFromPath(  "genzedong.org/cert.pem", Paths.get(System.getProperty("user.dir")).getParent() + "genzedong.org/key.pem");
            }));*/
            config.staticFiles.add(staticFiles -> {
                staticFiles.hostedPath = "/css";
                staticFiles.directory = "/css";
                staticFiles.location = Location.CLASSPATH;
            });
            config.staticFiles.add(staticFiles -> {
                staticFiles.hostedPath = "/js";
                staticFiles.directory = "/js";
                staticFiles.location = Location.CLASSPATH;
            });
            config.staticFiles.add(staticFiles -> {
                staticFiles.hostedPath = "/png";
                staticFiles.directory = "/png";
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
        app.get("/discord", ctx -> ctx.redirect("https://discord.gg/mwdP9wfD2F"));
    }


    private static SslContextFactory.Server getSslContextFactory() {
        SslContextFactory.Server sslContextFactory = new SslContextFactory.Server();
        sslContextFactory.setKeyStorePath(Website.class.getResource("/keystore.jks").toExternalForm());
        sslContextFactory.setKeyStorePassword("password");
        return sslContextFactory;
    }
    private static void inputStream(Context ctx, String file) {
    }

}
//added Javascript rendering and added 2 more json data points I missed.