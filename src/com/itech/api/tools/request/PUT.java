package com.itech.api.tools.request;

import com.itech.api.tools.Helper;
import com.itech.api.tools.Response;
import org.apache.log4j.Logger;

import java.net.http.HttpRequest;

/**
 * API tool to request PUT requests.
 * @author Itech
 */
public class PUT extends Helper {
    /**
     * Construct a LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(PUT.class);

    /**
     * PUT request constructor.
     * @param uri request URI
     * @param body request body
     */
    public PUT(String uri,Object body) {
        super(uri,body);
    }

    /**
     * Create a new instance PUT request.
     * @param uri Request URI
     * @param body Request body
     * @return <code>PUT</code>
     */
    public static PUT createNewPUT(String uri, Object body) {
        return new PUT(uri,body);
    }

    /**
     * Execute the PUT request.
     * @param headers Specifies the http's headers
     * @return <code>Response</code>
     */
    @Override
    public Response execute(String... headers) {
        LOGGER.info("PUT URL : " + this.getUri());
        if(headers == null || headers.length == 0){
            headers = new String[]{"Content-Type", CONTENT_TYPE};
        }
        HttpRequest request = this.createPUTRequest(headers);
        Response response = this.createResponse(request);
        LOGGER.info("Return : status ["+response.getCode()+"] -> body ["+ (response.getData().toString().length() > 0) +"]\n"
                +response.toString());
        return response;
    }
}
