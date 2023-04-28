package com.itech.api.tools.request;

import com.itech.api.tools.Helper;
import com.itech.api.tools.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.http.HttpRequest;

/**
 * API tool to request POST requests.
 * @author Itech
 */
public class POST extends Helper {
    /**
     * Construct a LOGGER
     */
    private static final Logger LOGGER = LogManager.getLogger(POST.class);

    /**
     * POST request constructor
     * @param uri request URI
     * @param body request body
     */
    public POST(String uri,Object body) {
        super(uri,body);
    }

    /**
     * Create a new instance POST request.
     * @param uri Request URI
     * @param body Request body
     * @return <code>POST</code>
     */
    public static POST createNewPost(String uri, Object body) {
        return new POST(uri,body);
    }

    /**
     * Execute the POST request.
     * @param headers Specifies the http's headers
     * @return <code>Response</code>
     */
    @Override
    public Response execute(String... headers) {
        LOGGER.info("POST URL : " + this.getUri());
        if(headers == null || headers.length == 0){
            headers = new String[]{"Content-Type", CONTENT_TYPE};
        }
        HttpRequest request = this.createPOSTRequest(headers);
        Response response = this.createResponse(request);
        LOGGER.info("Return : status ["+response.getCode()+"] -> body ["+ (response.getData().toString().length() > 0) +"]\n"
                + response);
        return response;
    }

}
