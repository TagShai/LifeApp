package org.mannayakasha.resource;

import java.util.ResourceBundle;

/**
 * Message manager for i18n.login.index.
 *
 * @author Pavel
 * @version 1.0 29.08.2017.
 */
public class LoginMessageManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n.login.index");

    private LoginMessageManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
