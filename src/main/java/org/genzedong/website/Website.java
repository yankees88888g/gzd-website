package org.genzedong.website;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;

public class Website {
    public static void runWebsite(int port) throws JsonProcessingException {
        Javalin app = Javalin.create().start(port);
        String jsonString = "{\"test1\":\"value1\",\"test2\":{\"id\":0,\"name\":\"testName\"}}";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonString);
        app.error(404, ctx -> ctx.json(jsonNode));
    }
}
