package com.itech.api;

import com.itech.api.v1.tools.API;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        LOGGER.info("Thanks for using ItechAPI tool.\n");
        API api = new API();
        api.setHeaders(new String[]{"Content-Type", "application/json", "X-Shopify-Access-Token","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"});
        api.get("https://test-store-4023.myshopify.com/admin/api/2021-07/shop.json")
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