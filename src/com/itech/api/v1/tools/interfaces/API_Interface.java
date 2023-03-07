package com.itech.api.v1.tools.interfaces;

/**
 * An API Interface for interacting API operations.
 */
public interface API_Interface extends Resolver {
    /**
     * Request a GET method.
     * @param url Request URL
     * @return <code>API_Interface</code>
     */
    API_Interface get(String url);
    /**
     * Request a POST method.
     * @param url Request URL
     * @param headers Specifies the http's headers
     * @return <code>API_Interface</code>
     */
    API_Interface get(String url, String... headers);
    /**
     * Request a POST method.
     * @param url Request URL
     * @param body Request Body
     * @return <code>API_Interface</code>
     */
    API_Interface post(String url, Object body);
    /**
     * Request a POST method.
     * @param url Request URL
     * @param body Request Body
     * @param headers Specifies the http's headers
     * @return <code>API_Interface</code>
     */
    API_Interface post(String url, Object body, String... headers);
    /**
     * Request a PUT method.
     * @param url Request URL
     * @param body Request Body
     * @return <code>API_Interface</code>
     */
    API_Interface put(String url, Object body);
    /**
     * Request a PUT method.
     * @param url Request URL
     * @param body Request Body
     * @param headers Specifies the http's headers
     * @return <code>API_Interface</code>
     */
    API_Interface put(String url, Object body, String... headers);
    /**
     * Request a DELETE request
     * @param url Request URL
     * @return <code>API_Interface</code>
     */
    API_Interface delete(String url);
    /**
     * Request a DELETE request
     * @param url Request URL
     * @param headers Specifies the http's headers
     * @return <code>API_Interface</code>
     */
    API_Interface delete(String url, String... headers);
}
