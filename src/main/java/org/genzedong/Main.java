package org.genzedong;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.genzedong.reddit.database.FullPostData;
import org.genzedong.reddit.database.GetData;
import org.genzedong.website.Website;

import java.io.IOException;
import java.util.List;

public class Main {
    public static List<FullPostData> data;
    public static void main(String[] args) throws IOException {
        data = GetData.getData();
        Website.runWebsite(808);

    }
}
