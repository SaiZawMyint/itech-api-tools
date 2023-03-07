package com.itech.api.utils;

import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * A UTIL tool for Itech API.
 * @author Itech
 */
public class Config {
    /**
     * Default log file.
     */
    private static final String LOG_FILE = "ItechAPILog.properties";
    /**
     * Default log properties.
     */
    private static final String LOB_PROPERTIES ="log4j.rootLogger=DEBUG, Appender1,Appender2\n" +
            "log4j.appender.Appender1=org.apache.log4j.ConsoleAppender\n" +
            "log4j.appender.Appender1.layout=org.apache.log4j.PatternLayout\n" +
            "log4j.appender.Appender1.layout.ConversionPattern=%-7p %d [%t] %c %x - %m%n\n" +
            "log4j.appender.Appender2=org.apache.log4j.FileAppender\n" +
            "log4j.appender.Appender2.File=${user.home}/itechapi/log/applog.log\n" +
            "log4j.appender.Appender2.layout=org.apache.log4j.PatternLayout\n" +
            "log4j.appender.Appender2.layout.ConversionPattern=%-7p %d [%t] %c %x - %m%n";

    /**
     * Configuration for the logging system.
     */
    public static void configureLOG(){
        File file = new File(LOG_FILE);
        if(!file.exists()) {
            try {
                FileOutputStream outputStream = new FileOutputStream(LOG_FILE);
                byte[] strToBytes = LOB_PROPERTIES.getBytes();
                outputStream.write(strToBytes);
                outputStream.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        PropertyConfigurator.configure(LOG_FILE);
    }
}
