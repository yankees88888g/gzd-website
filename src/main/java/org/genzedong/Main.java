package org.genzedong;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.genzedong.reddit.database.GetData;
import org.genzedong.website.Website;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Website.runWebsite(808);
        //GetData.getData();
    }
}
