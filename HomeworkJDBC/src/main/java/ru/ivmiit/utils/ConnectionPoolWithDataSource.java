package ru.ivmiit.utils;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConnectionPoolWithDataSource {
    private static ConnectionPoolWithDataSource connectionPool;
    private static Properties properties = new Properties();
    private static DriverManagerDataSource dataSource = new DriverManagerDataSource();

    static {
        connectionPool = new ConnectionPoolWithDataSource();
        try {
            properties.load(new FileInputStream(ConnectionPoolWithDataSource.class.getClassLoader().getResource("db.properties").getFile()));
            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");
            String driverClassName = properties.getProperty("db.driverClassName");

            dataSource.setUrl(dbUrl);
            dataSource.setUsername(dbUsername);
            dataSource.setPassword(dbPassword);
            dataSource.setDriverClassName(driverClassName);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }


    public static ConnectionPoolWithDataSource getConnectionPool() {
        return connectionPool;
    }

    public DriverManagerDataSource getDataSource() {
        return dataSource;
    }


}
