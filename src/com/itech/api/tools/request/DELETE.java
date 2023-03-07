package com.itech.api.tools.request;

import com.itech.api.tools.Helper;
import com.itech.api.tools.Response;
import org.apache.log4j.Logger;

import java.net.http.HttpRequest;

/**
 * API tool to request a DELETE request.
 * @author Itech
 */
public class DELETE extends Helper {
    /**
     * Construct a LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(DELETE.class);
    /**
     * DELETE request constructor.
     * @param url DELETE request url
     */
    public DELETE(String url) {
        super(url);
    }

    /**
     * Create new instance DELETE request
     * @param url Request URL
     * @return <code>DELETE</code>
     */
    public static DELETE createNewDELETE(String url){
        return new DELETE(url);
    }

    /**
     * Execute DELETE request.
     * @param headers Specifies the http's headers
     * @return <code>Response</code>
     */
    @Override
    public Response execute(String... headers) {
        LOGGER.info("DELETE URL : " + this.getUri());
        if(headers == null || headers.length == 0){
            headers = new String[]{"Content-Type", CONTENT_TYPE};
        }
        HttpRequest request = this.createDELETERequest(headers);
        Response response = this.createResponse(request);
        LOGGER.info("Return : status ["+response.getCode()+"] -> body ["+
                (response.getData().toString().length() > 0) +"]\n"
                +response.toString());
        return response;
    }
}
