package com.itech.api.v1.tools;

import com.itech.api.tools.Response;
import com.itech.api.tools.exceptions.ItechAPIException;
import com.itech.api.tools.request.DELETE;
import com.itech.api.tools.request.GET;
import com.itech.api.tools.request.POST;
import com.itech.api.tools.request.PUT;
import com.itech.api.utils.Config;
import com.itech.api.v1.tools.interfaces.API_Interface;
import org.apache.log4j.Logger;

import java.util.function.Function;

/**
 * A tool to request an external API and supports asynchronously requests.
 * Easy to handle responses, exceptions.
 * Current version support 4 requests.
 * <ul>
 *     <li>GET</li>
 *     <li>POST</li>
 *     <li>PUT</li>
 *     <li>DELETE</li>
 * </ul>
 * @author ITech
 */
public class API extends ItechAPIException implements API_Interface {
    private static final Logger LOGGER = Logger.getLogger(API.class);
    private Response response;
    private String[] headers;

    /**
     * API constructor
     */
    public API() {
        Config.configureLOG();
        LOGGER.info("New API generated!");
    }

    /**
     * Request a GET method.
     * @param url Request URL
     * @return <code>API_Interface</code>
     */
    @Override
    public API_Interface get(String url) {
        GET g = GET.createNewGET(url);
        this.response = g.execute(this.getHeaders());
        this.response.setException(g.getE());
        return this;
    }

    /**
     * Request a POST method.
     * @param url Request URL
     * @param headers Specifies the http's headers
     * @return <code>API_Interface</code>
     */
    @Override
    public API_Interface get(String url, String... headers) {
        GET g = GET.createNewGET(url);
        this.setHeaders(headers);
        this.response = g.execute(this.getHeaders());
        this.response.setException(g.getE());
        return this;
    }

    /**
     * Request a POST method.
     * @param url Request URL
     * @param body Request Body
     * @return <code>API_Interface</code>
     */
    @Override
    public API_Interface post(String url, Object body) {
        POST p = POST.createNewPost(url, body);
        this.response = p.execute(this.getHeaders());
        this.response.setException(p.getE());
        return this;
    }

    /**
     * Request a POST method.
     * @param url Request URL
     * @param body Request Body
     * @param headers Specifies the http's headers
     * @return <code>API_Interface</code>
     */
    @Override
    public API_Interface post(String url, Object body, String... headers) {
        POST p = POST.createNewPost(url, body);
        this.setHeaders(headers);
        this.response = p.execute(this.getHeaders());
        this.response.setException(p.getE());
        return this;
    }

    /**
     * Request a PUT method.
     * @param url Request URL
     * @param body Request Body
     * @return <code>API_Interface</code>
     */
    @Override
    public API_Interface put(String url, Object body) {
        PUT p = PUT.createNewPUT(url, body);
        this.response = p.execute(this.getHeaders());
        this.response.setException(p.getE());
        return this;
    }

    /**
     * Request a PUT method.
     * @param url Request URL
     * @param body Request Body
     * @param headers Specifies the http's headers
     * @return <code>API_Interface</code>
     */
    @Override
    public API_Interface put(String url, Object body, String... headers) {
        PUT p = PUT.createNewPUT(url, body);
        this.setHeaders(headers);
        this.response = p.execute(this.getHeaders());
        this.response.setException(p.getE());
        return this;
    }

    /**
     * Request a DELETE request
     * @param url Request URL
     * @return <code>API_Interface</code>
     */
    @Override
    public API_Interface delete(String url) {
        DELETE d = DELETE.createNewDELETE(url);
        this.response = d.execute(this.getHeaders());
        this.response.setException(d.getE());
        return this;
    }

    /**
     * Request a DELETE request
     * @param url Request URL
     * @param headers Specifies the http's headers
     * @return <code>API_Interface</code>
     */
    @Override
    public API_Interface delete(String url, String... headers) {
        DELETE d = DELETE.createNewDELETE(url);
        this.setHeaders(headers);
        this.response = d.execute(this.getHeaders());
        this.response.setException(d.getE());
        return this;
    }

    /**
     * To handle the API response after request is successful.
     * @param callback Asynchronous callback with response parameter
     * @return <code>Response</code>
     */
    @Override
    public Response then(Function<Response, Response> callback) {
        callback.apply(this.response);
        return this.response;
    }

    /**
     * To handle the exception after request fails.
     * @param callback Asynchronous callback with exception parameter
     */
    @Override
    public Response exception(Function<Response, Response> callback) {
        if (this.response.getException() != null) {
            callback.apply(this.response);
        }
        return this.response;
    }

    /**
     * Get the API headers
     * @return HttpHeaders string representation
     */
    public String[] getHeaders() {
        return headers;
    }

    /**
     * Set the API headers before request is executed.
     * @param headers API headers
     */
    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

}
