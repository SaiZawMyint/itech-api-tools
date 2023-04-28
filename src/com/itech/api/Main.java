package com.itech.api;

import com.itech.api.v1.tools.API;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
//        Config.configureLOG();
        LOGGER.info("Thanks for using ItechAPI tool.\n");
        API api = new API();
        api.get("https://medium-backend-api.onrender.com/api/posts")
                .then(r->{
                    System.out.println(r);
                    return r;
                })
                .exception(e -> {
                    e.getException().printStackTrace();
                    return e;
                });
    }
}