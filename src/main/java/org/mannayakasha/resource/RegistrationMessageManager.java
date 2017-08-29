package org.mannayakasha.resource;

import java.util.ResourceBundle;

/**
 * Message manager for i18n.registration.index.
 *
 * @author Pavel
 * @version 1.0 29.08.2017.
 */
public class RegistrationMessageManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n.registration.index");

    private RegistrationMessageManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
