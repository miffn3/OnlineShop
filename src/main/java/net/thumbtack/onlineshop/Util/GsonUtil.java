package net.thumbtack.onlineshop.Util;

import com.google.gson.Gson;

public class GsonUtil {
    private static Gson gson;

    public static Gson getInstance() {
        if(gson == null) {
            gson = new Gson();
        }
        return gson;
    }
}
