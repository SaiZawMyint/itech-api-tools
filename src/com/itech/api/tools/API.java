package com.itech.api.tools;


import com.itech.api.tools.request.GET;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.function.Function;

public class API {
    Logger logger = Logger.getLogger(API.class.getName());

    public API(){
        PropertyConfigurator.configure("log4j.properties");
        logger.info("New API registered!");
    }
    public Response get(String uri){
        return GET.createNewGET(uri).execute();
    }

    public Response get(String uri, String... headers){
        return GET.createNewGET(uri).execute(headers);
    }

    public Response get(String uri, Function<Response, Response> callback){
        return callback.apply(GET.createNewGET(uri).execute());
    }
    public Response get(String uri,String[] headers, Function<Response, Response> callback){
        return callback.apply(GET.createNewGET(uri).execute(headers));
    }
}
