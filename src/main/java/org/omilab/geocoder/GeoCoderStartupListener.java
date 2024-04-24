package org.omilab.geocoder;

import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class GeoCoderStartupListener implements javax.servlet.ServletContextListener {

    Logger log = LogManager.getLogger(this.getClass());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("Geocoder web service ready, Geocoder initialisation,  ...");
        Thread initThread = new Thread("Geocoder Initialisation") {
        };
        initThread.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("Geocoder web service destroyed ...");
    }
}
