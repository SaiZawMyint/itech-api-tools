package com.itech.api.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * A main helper tool to create an external API request.
 * @author Itech
 */
public abstract class Helper {
    /**
     * Construct a LOGGER.
     */
    private static final Logger LOGGER = LogManager.getLogger(Helper.class);
    /**
     * Default content type for http request header.
     */
    public static final String CONTENT_TYPE = "application/json";
    /**
     * Construct a GSON object to serialize and prettify the json response.
     */
    public static final Gson GSON = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
    /**
     * Request uri
     */
    private String uri;
    /**
     * Is http request with json response.
     */
    private Boolean isJson;
    /**
     * Request body
     */
    private Object body;
    /**
     * Request headers
     */
    private String[] headers;
    /**
     * API call exception
     */
    public Exception e;

    /**
     * Construct a helper object with specified uri
     * @param uri Request uri
     */
    public Helper(String uri){
        this.uri = uri;
    }

    /**
     * Construct a helper object with specified uri and body
     * @param uri Request uri
     * @param body Request body
     */
    public Helper(String uri, Object body){
        this.uri = uri;
        this.body = body;
    }

    /**
     * Create http request for GET Method to use in executing the API.
     * @param headers Specifies the http's headers
     * @return <code>HttpRequest</code>
     */
    public HttpRequest createGETRequest(String... headers){
        try {
            this.headers = headers;
            return this.createHttpRequestBuilder(headers)
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            LOGGER.error("URI error occurs : "+e.getMessage());
            this.e = e;
            return null;
        }
    }
    /**
     * Create http request for POST Method to use in executing the API.
     * @param headers Specifies the http's headers
     * @return <code>HttpRequest</code>
     */
    public HttpRequest createPOSTRequest(String... headers){
        try {
            this.headers = headers;
            return this.createHttpRequestBuilder(headers)
                    .POST(HttpRequest.BodyPublishers.ofString(getBody()))
                    .build();
        } catch (URISyntaxException e) {
            LOGGER.error("URI error occurs : "+e.getMessage());
            this.e = e;
            return null;
        }
    }
    /**
     * Create http request for PUT Method to use in executing the API.
     * @param headers Specifies the http's headers
     * @return <code>HttpRequest</code>
     */
    public HttpRequest createPUTRequest(String... headers){
        try {
            this.headers = headers;
            return this.createHttpRequestBuilder(headers)
                    .PUT(HttpRequest.BodyPublishers.ofString(this.getBody()))
                    .build();
        } catch (URISyntaxException e) {
            LOGGER.error("URI error occurs : "+e.getMessage());
            this.e = e;
            return null;
        }
    }
    /**
     * Create http request for DELETE Method to use in executing the API.
     * @param headers Specifies the http's headers
     * @return <code>HttpRequest</code>
     */
    public HttpRequest createDELETERequest(String... headers){
        try {
            this.headers = headers;
            return this.createHttpRequestBuilder(headers)
                    .DELETE()
                    .build();
        } catch (URISyntaxException e) {
            LOGGER.error("URI error occurs : "+e.getMessage());
            this.e = e;
            return null;
        }
    }

    /**
     * Create a response for API requests.
     * @param request HttpRequest
     * @return <code>Response</code>
     */
    public Response createResponse(HttpRequest request) {
        if(request == null) {
            LOGGER.error("Cannot create a request! Please check uri or headers!");
            return new Response(getUri(), 400, "Cannot create a request! Please check uri or headers!", getJson());
        }
        if(request.headers()!= null && request.headers().map().size() > 0) {
            if(request.headers().map().containsKey("Content-Type")){
                this.isJson = request.headers().map().get("Content-Type").get(0).equals(CONTENT_TYPE);
            }
        }
        try{
            return new Response(HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString()), getJson());
        }catch(SocketException e){
            this.e = new SocketException("Fails to connect : "+getUri());
            if(e instanceof ConnectException){
                LOGGER.error("Fails to connect : "+getUri());
                return new Response(getUri(), 503,"Fails to connect : "+getUri(), getJson());
            }
            LOGGER.error("Socket exception occurred! Message : "+e.getMessage());
            return new Response(getUri(), 500, "Socket exception occurred! Message : "+e.getMessage(), getJson());
        }
        catch (IOException e) {
            this.e = new IOException("IO error occurs : "+e.getMessage());
            LOGGER.error("IO error occurs : "+e.getMessage());
            return new Response(getUri(), 405,"Bad Request! Message : "+e.getMessage(), getJson());
        } catch (InterruptedException e) {
            this.e = new InterruptedException("Internal server error : "+e.getMessage());
            LOGGER.error("Interruption error occurs : "+e.getMessage());
            return new Response(getUri(), 500,"Internal server error : "+e.getMessage(), getJson());
        }
    }

    /**
     * An executor tool to execute API requests.
     * @param headers Specifies the http's headers
     * @return <code>Response</code>
     */
    public abstract Response execute(String... headers);

    /**
     * GET url for API requests
     * @return url for API requests
     */
    public String getUri() {
        return uri;
    }

    /**
     * Set the request uri to be used.
     * @param uri Request uri
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * Get is the request is json.
     * @return Is Json
     */
    public Boolean getJson() {
        return isJson;
    }

    /**
     * Set the request type to json.
     * @param json Is json
     */
    public void setJson(Boolean json) {
        isJson = json;
    }

    /**
     * Get response body
     * @return String response body
     */
    public String getBody() {
       if(this.body instanceof String)  return GSON.toJson(JsonParser.parseString(this.body.toString()));
       return GSON.toJson(this.body);
    }

    /**
     * Set response body
     * @param body Response body
     */
    public void setBody(Object body) {
        this.body = body;
    }

    /**
     * Get exception.
     * @return <code>Exception</code>
     */
    public Exception getE() {
        return e;
    }

    /**
     * Set exception.
     * @param e Exception
     */
    public void setE(Exception e) {
        this.e = e;
    }

    /**
     * Create a request builder to build the http request.
     * @param headers Specifies the http's headers
     * @return <code>HttpRequest.Builder</code>
     * @throws URISyntaxException the uri exception
     */
    private HttpRequest.Builder createHttpRequestBuilder(String... headers) throws URISyntaxException {
        return HttpRequest.newBuilder().headers(headers).uri(new URI(this.uri));
    }
}
