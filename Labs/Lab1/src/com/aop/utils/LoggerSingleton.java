package com.aop.utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerSingleton {

    private static String LOG_FILE = "log.txt";

    private static LoggerSingleton ourInstance = new LoggerSingleton();

    public static LoggerSingleton getInstance() {
        return ourInstance;
    }

    private Logger logger;

    private LoggerSingleton() {
        init();
    }

    private void init() {
        logger = Logger.getGlobal();
        logger.setLevel(Level.INFO);
        File f = new File(LOG_FILE);
        FileHandler handler;

        if (!f.exists()) {
            try {
                f.createNewFile();
                handler = new FileHandler(LOG_FILE, true);
                handler.setFormatter(new SimpleFormatter());
                logger.addHandler(handler);
                logger.setUseParentHandlers(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setLogFile(String fileName) {
        LOG_FILE = fileName;
        init();
    }

    public void info(String message) {
        logger.info(message);
    }
}
