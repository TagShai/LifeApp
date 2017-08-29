package org.mannayakasha.controller.command;

import org.mannayakasha.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Command wasn't specified.
 *
 * @author Pavel
 * @version 1.0 29.08.2017.
 */
public class EmptyCommand extends ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {

/*        Locale locale = new Locale("en");
        request.setAttribute("locale", locale);*/

        String page = ConfigurationManager.getProperty("path.page.login"); // path.page.error
        //request.getSession().invalidate();
        return page;
    }
}