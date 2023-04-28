package com.itech.api.tools.request;

import com.itech.api.tools.Helper;
import com.itech.api.tools.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.http.HttpRequest;

/**
 * API tool to request a GET request.
 * @author Itech
 */
public class GET extends Helper {
    /**
     * Construct a LOGGER
     */
    private static final Logger LOGGER = LogManager.getLogger(GET.class);


    /**
     * GET request constructor.
     * @param url GET request url
     */
    public GET(String url) {


        super(url);
    }

    /**
     * Create new instance GET request.
     * @param url Request URL
     * @return <code>GET</code>
     */
    public static GET createNewGET(String url){
        return new GET(url);
    }

    /**
     * Execute GET request.
     * @param headers Specifies the http's headers
     * @return <code>Response</code>
     */
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
