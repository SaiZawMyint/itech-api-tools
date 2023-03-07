package com.itech.api.v1.tools.interfaces;

import com.itech.api.tools.Response;

import java.util.function.Function;

/**
 * A tool to handle the API responses.
 */
public interface Resolver {
    /**
     * To handle the API response after request is successful.
     * @param callback Asynchronous callback with response parameter
     * @return <code>Response</code>
     */
    Response then(Function<Response, Response> callback);
}
