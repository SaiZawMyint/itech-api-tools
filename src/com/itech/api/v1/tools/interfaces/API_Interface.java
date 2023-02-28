package com.itech.api.v1.tools.interfaces;

public interface API_Interface extends Resolver {
    API_Interface get(String url);
    API_Interface get(String url, String... headers);
    API_Interface post(String url, Object body);
    API_Interface post(String url, Object body, String... headers);
}
