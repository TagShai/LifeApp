package org.mannayakasha.controller.command;

import org.mannayakasha.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Logout user from the system.
 *
 * @author Pavel
 * @version 1.0 29.08.2017.
 */
public class LogoutCommand extends ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.index"); // уничтожение сессии
        request.getSession().invalidate();
        return page;
    }
}