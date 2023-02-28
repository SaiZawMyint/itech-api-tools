package com.itech.api.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.itech.api.utils.Constants;

import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Response {
    private String uri;
    private int code;
    private String message;
    private Object data;
    private Map<String,Object> headers;
    private Boolean isJson;
    private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
    public Response(HttpResponse<?> response, Boolean isJson) {
        this.uri = response.uri().toString();
        this.code = response.statusCode();
        this.data = response.body();
        this.isJson = isJson;
        setHeaders(response.headers());
    }

    public Response(String uri, int code, String message, Boolean isJson) {
        this.uri = uri;
        this.code = code;
        this.message = message;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data == null ? "" : data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getHeaders() {
        return headers!=null ? headers.entrySet()
                .stream()
                .map(entry -> entry.getKey()+": \""+entry.getValue()+"\"")
                .collect(Collectors.joining(",","{","}"))
                : null;
    }

    public void setHeaders(HttpHeaders httpHeaders) {
        this.headers = null;
        if(httpHeaders.map().size() > 0){
            this.headers = new HashMap<>();
            for(Map.Entry<String, List<String>> entry : httpHeaders.map().entrySet()){
                this.headers.put(entry.getKey(), entry.getValue().toString());
            }
        }
    }

    public Boolean getJson() {
        return isJson;
    }

    public void setJson(Boolean json) {
        isJson = json;
    }

    private String collect(){
        StringBuilder string = new StringBuilder();
        string.append("{");
        if(getUri() != null) string.append("\"uri\":\"").append(getUri()).append("\"");
        if(getCode() > 0) string.append(",\"code\":").append(getCode());
        if(getMessage()!= null) string.append(",\"message\":\"").append(getMessage()).append("\"");
        if(getData()!= null) string.append(",\"data\":").append(getData());
        if(getHeaders()!= null) string.append(",\"headers\":").append(getHeaders());
        string.append("}");
        return string.toString();
    }

    private String toJson(){
        try{
            return GSON.toJson(JsonParser.parseString(this.collect()));
        }catch(Exception e){
            return this.collect();
        }
    }

    @Override
    public String toString() {
        if(Boolean.TRUE.equals(getJson())){
            return toJson();
        }
        return this.getData().toString();
    }
}
