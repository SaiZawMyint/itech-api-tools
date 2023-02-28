package com.itech.test;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class TestCallbacks {
    public static String callback(String name, Function<String, String> callback){
        return callback.apply(name);
    }

    interface API {
        API get(String url);
        API then(Function callback);

        void exception(Function callback);
    }
    public static void main(String[] args) {
//        callback("callback",(str)->{
//            System.out.println(str);
//            return str;
//        });
        API api = new API() {
            private String url;
            private Exception e;
            @Override
            public API get(String url) {
                this.url = url;
                return this;
            }

            @Override
            public API then(Function callback) {
                callback.apply(this.url);
                return this;
            }

            @Override
            public void exception(Function callback) {
                callback.apply(this.e);
            }
        };
        api.get("http://www.baidu.com").then(str->{
            System.out.println(str);
            return 0;
        }).exception(e->{
            System.out.println(e);
            return 0;
        });
    }
}
