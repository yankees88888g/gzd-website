package org.genzedong.website.gets;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.genzedong.website.util.GetHtml;
import org.jetbrains.annotations.NotNull;

public class Index implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        ctx.html(GetHtml.getHtml("index.html"));
    }
}
