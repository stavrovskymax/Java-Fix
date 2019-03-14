package ru.ivmiit.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class JdbcDataSourceUtil {
    private final Properties properties = new Properties();
    private static final JdbcDataSourceUtil data = new JdbcDataSourceUtil();

    private JdbcDataSourceUtil() {
        try {
            properties.load(new FileInputStream(this.getClass().getClassLoader().getResource("db.properties").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JdbcDataSourceUtil getData() {
        return data;
    }

    public String getProperty(String value) {
        return properties.getProperty(value);
    }
}
