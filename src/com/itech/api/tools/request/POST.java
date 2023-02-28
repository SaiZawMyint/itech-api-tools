package com.itech.api.tools.request;

import com.itech.api.tools.Helper;
import com.itech.api.tools.Response;
import org.apache.log4j.Logger;

import java.net.http.HttpRequest;

public class POST extends Helper {
    private static final Logger LOGGER = Logger.getLogger(POST.class);
    public POST(String uri,Object body) {
        super(uri,body);
    }
    public static POST createNewPost(String uri, Object body) {
        return new POST(uri,body);
    }
    @Override
    public Response execute(String... headers) {
        LOGGER.info("POST URL : " + this.getUri());
        if(headers == null || headers.length == 0){
            headers = new String[]{"Content-Type", CONTENT_TYPE};
        }
        HttpRequest request = this.createPostRequest(headers);
        Response response = this.createResponse(request);
        LOGGER.info("Return : status ["+response.getCode()+"] -> body ["+ (response.getData().toString().length() > 0) +"]\n"
                +response.toString());
        return response;
    }

}
