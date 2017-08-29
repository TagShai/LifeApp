package org.mannayakasha.dao.pool;

import java.util.ResourceBundle;

/**
 * Class, that provide a configuration to connection pool from resource bundle.
 *
 * @author Pavel
 * @version 1.0 28.08.2017.
 */
public class PoolConfiguration {
    private final static ResourceBundle bundle = ResourceBundle.getBundle("connectionPoolConfiguration.poolConfig");

    private String driver;
    private String url;
    private String user;
    private String password;
    private int minSize;
    private int maxSize;
    private int timeout;

    public PoolConfiguration() {
        init();
    }

    private void init() {
        driver = bundle.getString("pool.driver");
        url = bundle.getString("pool.url");
        user = bundle.getString("pool.user");
        password = bundle.getString("pool.password");
        minSize = Integer.parseInt(bundle.getString("pool.min.size"));
        maxSize = Integer.parseInt(bundle.getString("pool.max.size"));
        timeout = Integer.parseInt(bundle.getString("pool.timeout"));
    }

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public int getMinSize() {
        return minSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public int getTimeout() {
        return timeout;
    }
}
