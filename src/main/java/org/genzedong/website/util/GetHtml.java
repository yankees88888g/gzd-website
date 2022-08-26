package org.genzedong.website.util;

import java.io.*;

public class GetHtml {
    public static String getHtml(String file) throws IOException {
        InputStream is = GetHtml.class.getClassLoader().getResourceAsStream("html/" + file);
        byte[] htmlFile = is.readAllBytes();
        return new String(htmlFile);
    }
}
