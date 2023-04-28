package com.itech.api.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.itech.api.tools.exceptions.ItechAPIException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A main response tool for all api requests.
 * @author Itech
 */
public class Response extends ItechAPIException {
    /**
     * Construct a LOGGER.
     */
    private static final Logger LOGGER = LogManager.getLogger(Response.class);
    /**
     * Request uri
     */
    private String uri;
    /**
     * Response status code.
     */
    private int code;
    /**
     * Response message.
     */
    private String message;
    /**
     * Response body from resource api.
     */
    private Object data;
    /**
     * Response headers.
     */
    private Map<String,Object> headers;
    /**
     * Is Json response.
     */
    private Boolean isJson;
    /**
     * API request exception.
     */
    private Exception exception;
    /**
     * Construct a GSON object to serialize and prettify the json response.
     */
    private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

    /**
     * Create an Response form http response.
     * @param response HttpResponse
     * @param isJson Is Json response
     */
    public Response(HttpResponse<?> response, Boolean isJson) {
        this.uri = response.uri().toString();
        this.code = response.statusCode();
        this.data = response.body();
        this.isJson = isJson;
        setHeaders(response.headers());
    }

    /**
     * Constructor for Response.
     * @param uri Request URI
     * @param code Response status code
     * @param message Response message
     * @param isJson Response Json
     */
    public Response(String uri, int code, String message, Boolean isJson) {
        this.uri = uri;
        this.code = code;
        this.message = message;
    }

    /**
     * Get Request URI.
     * @return Request URI
     */
    public String getUri() {
        return uri;
    }

    /**
     * Set Request URI.
     * @param uri Request URI
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * Get Response status code.
     * @return Response status code
     */
    public int getCode() {
        return code;
    }

    /**
     * Get response message.
     * @return Response message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the response message.
     * @param message Response message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Set the response status code.
     * @param code Response status code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Set the response data from resource API.
     * @return Response data
     */
    public Object getData() {
        return data == null ? "" : data;
    }

    /**
     * Set the response Data
     * @param data Response data
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * Get the response headers from resource API.
     * @return Response headers
     */
    public Object getHeaders() {
        return headers!=null ? headers.entrySet()
                .stream()
                .map(entry -> "\""+analyzeJson(entry.getKey())+"\": \""+analyzeJson(entry.getValue().toString())+"\"")
                .collect(Collectors.joining(",","{","}"))
                : null;
    }

    /**
     * Set response header.
     * @param httpHeaders HttpHeaders
     */
    public void setHeaders(HttpHeaders httpHeaders) {
        this.headers = null;
        if(httpHeaders.map().size() > 0){
            this.headers = new HashMap<>();
            for(Map.Entry<String, List<String>> entry : httpHeaders.map().entrySet()){
                this.headers.put(entry.getKey(), entry.getValue().toString());
            }
        }
    }

    /**
     * Get is Json response.
     * @return IsJson
     */
    public Boolean getJson() {
        return isJson;
    }

    /**
     * Set the json response.
     * @param json isJson
     */
    public void setJson(Boolean json) {
        isJson = json;
    }

    /**
     * Get exception.
     * @return Exception
     */
    public Exception getException() {
        return exception;
    }

    /**
     * Set exception.
     * @param exception Exception
     */
    public void setException(Exception exception) {
        this.exception = exception;
    }

    /**
     * Collect all data from the response as String.
     * @return String
     */
    private String collect(){
        StringBuilder string = new StringBuilder();
        string.append("{");
        if(getUri() != null && getData().toString().length() > 0) string.append("\"uri\":\"").append(getUri()).append("\"");
        if(getCode() > 0) string.append(",\"code\":").append(getCode());
        if(getMessage()!= null && getData().toString().length() > 0) string.append(",\"message\":\"").append(getMessage()).append("\"");
        if(getData()!= null && getData().toString().length() > 0) string.append(",\"data\":").append(getData());
        if(getHeaders()!= null && getData().toString().length() > 0) string.append(",\"headers\":").append(getHeaders());
        string.append("}");
        return string.toString();
    }

    /**
     * Convert response to string.
     * @return JSON representation String
     */
    private String toJson(){
        try{
            return GSON.toJson(JsonParser.parseString(this.collect()));
        }catch(Exception e){
            return this.collect();
        }
    }
    private String analyzeJson(String object){
        return object.replaceAll("\"", "'");
    }
    /**
     * toString method
     * @return String representation
     */
    @Override
    public String toString() {
        if(this.getException() != null){
            return this.getException().toString();
        }
        if(Boolean.TRUE.equals(getJson())){
            return toJson();
        }
        return this.getData().toString();
    }

    /**
     * Constructs an exception after a failed API call.
     * @param callback Asynchronous callback with exception parameter
     */
    @Override
    public Response exception(Function<Response, Response> callback) {
        if(this.getException() == null) return this;
        LOGGER.error(this.getException().toString());
        callback.apply(this);
        return this;
    }
}
