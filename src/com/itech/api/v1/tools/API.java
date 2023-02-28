package com.itech.api.v1.tools;

import com.itech.api.tools.Response;
import com.itech.api.tools.request.GET;
import com.itech.api.tools.request.POST;
import com.itech.api.v1.tools.interfaces.API_Interface;
import com.itech.api.v1.tools.interfaces.Functions;
import com.itech.api.v1.tools.interfaces.Resolver;

import java.util.function.Function;

public class API implements API_Interface {
    private Response response;
    private Exception e;
    @Override
    public API_Interface get(String url) {
        GET g = GET.createNewGET(url);
        this.response = g.execute();
        this.e = g.getE();
        return this;
    }

    @Override
    public API_Interface get(String url, String... headers) {
        GET g = GET.createNewGET(url);
        this.response = g.execute(headers);
        this.e = g.getE();
        return this;
    }

    @Override
    public API_Interface post(String url, Object body) {
        POST p = POST.createNewPost(url,body);
        this.response = p.execute();
        this.e = p.getE();
        return this;
    }

    @Override
    public API_Interface post(String url, Object body, String... headers) {
        POST p = POST.createNewPost(url,body);
        this.response = p.execute(headers);
        this.e = p.getE();
        return this;
    }

    @Override
    public Resolver then(Function callback) {
        callback.apply(this.response);
        return this;
    }

    @Override
    public Exception exception(Function<Exception, Object> callback) {
        if(this.e == null) {return null;}
        return (Exception) callback.apply(this.e);
    }
}
