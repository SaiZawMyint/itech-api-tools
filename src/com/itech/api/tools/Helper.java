package com.itech.api.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class Helper {
    private static final Logger LOGGER = Logger.getLogger(Helper.class);
    public static final String CONTENT_TYPE = "application/json";
    public static final Gson GSON = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
    private String uri;
    private Boolean isJson;
    private Object body;
    private HttpRequest httpRequest;
    private String[] headers;
    public Exception e;
    public Helper(String uri){
        this.uri = uri;
    }

    public Helper(String uri, Object body){
        this.uri = uri;
        this.body = body;
    }
    public HttpRequest createGETRequest(String... headers){
        try {
            this.headers = headers;
            return HttpRequest.newBuilder()
                    .uri(new URI(this.uri))
                    .headers(headers)
                    .build();
        } catch (URISyntaxException e) {
            LOGGER.error("URI error occurs : "+e.getMessage());
            return null;
        }
    }
    public HttpRequest createPostRequest(String... headers){
        try {
            this.headers = headers;
            return HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(getBody()))
                    .uri(new URI(this.uri))
                    .headers(headers)
                    .build();
        } catch (URISyntaxException e) {
            LOGGER.error("URI error occurs : "+e.getMessage());
            this.e = e;
            return null;
        }
    }
    public Response createResponse(HttpRequest request) {
        if(headers!= null && headers.length > 0){
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
                return new Response(getUri(), 404,"Fails to connect : "+getUri(), getJson());
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

    public abstract Response execute(String... headers);
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Boolean getJson() {
        return isJson;
    }

    public void setJson(Boolean json) {
        isJson = json;
    }

    public String getBody() {
       if(this.body instanceof String)  return GSON.toJson(JsonParser.parseString(this.body.toString()));
       return GSON.toJson(this.body);
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public Exception getE() {
        return e;
    }

    public void setE(Exception e) {
        this.e = e;
    }
}
