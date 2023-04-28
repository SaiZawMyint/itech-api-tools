module com.itech.api {
    requires java.net.http;
    requires org.apache.logging.log4j;
    requires com.google.gson;
    requires org.apache.log4j;

    exports com.itech.api.tools;
    exports com.itech.api.tools.request;
    exports com.itech.api.tools.exceptions;
    exports com.itech.api.utils;
    exports com.itech.api.v1.tools;
    exports com.itech.api.v1.tools.interfaces;
}
