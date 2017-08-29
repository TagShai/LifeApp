package org.mannayakasha.resource;

import java.util.ResourceBundle;

/**
 * Message manager for commands.
 *
 * @author Pavel
 * @version 1.0 29.08.2017.
 */
public class ConfigurationManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("commands.commands");

    private ConfigurationManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }

}
