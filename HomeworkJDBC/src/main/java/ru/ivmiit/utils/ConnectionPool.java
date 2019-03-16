package ru.ivmiit.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConnectionPool {
    private final Properties properties = new Properties();
    private static final ConnectionPool connectionPool = new ConnectionPool();

    private ConnectionPool() {
        try {
            properties.load(new FileInputStream(this.getClass().getClassLoader().getResource("db.properties").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionPool getConnectionPool() {
        return connectionPool;
    }

    public String getProperty(String value) {
        return properties.getProperty(value);
    }
}
