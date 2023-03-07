package com.itech.api.tools.exceptions;

import com.itech.api.tools.Response;

import java.util.function.Function;

/**
 * An exception tool to handle after a failed API call.
 * @author Itech
 */
public abstract class ItechAPIException {
    /**
     * Constructs an exception after a failed API call.
     * @param callback Asynchronous callback with exception parameter
     * @return <code>Response</code> - exception response
     */
    public abstract Response exception(Function<Response, Response> callback);
}
