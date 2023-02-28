package com.itech.api.tools.request;

import com.itech.api.tools.Helper;
import com.itech.api.tools.Response;
import org.apache.log4j.Logger;

import java.net.http.HttpRequest;

public class GET extends Helper {

    private static final Logger LOGGER = Logger.getLogger(GET.class);

    public GET(String url) {
        super(url);
    }
    public static GET createNewGET(String url){
        return new GET(url);
    }
    @Override
    public Response execute(String... headers) {
        LOGGER.info("GET URL : " + this.getUri());
        if(headers == null || headers.length == 0){
            headers = new String[]{"Content-Type", CONTENT_TYPE};
        }
        HttpRequest request = this.createGETRequest(headers);
        Response response = this.createResponse(request);
        LOGGER.info("Return : status ["+response.getCode()+"] -> body ["+
                (response.getData().toString().length() > 0) +"]\n"
                +response.toString());
        return response;
    }

}
