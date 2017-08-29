package org.mannayakasha.controller.command.factory;

import org.mannayakasha.controller.command.ActionCommand;
import org.mannayakasha.controller.command.EmptyCommand;
import org.mannayakasha.resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Factory for all commands
 *
 * @author Pavel
 * @version 1.0 29.08.2017.
 */
public class ActionFactory {
    //private static final Logger LOGGER = Logger.getLogger(ActionFactory.class);

    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();

/*        if (current == null) {
            System.out.println("DEBUG: current = null");
        }*/

        String action = request.getParameter("command");

        if (action == null || action.isEmpty()) {
            return current;
        }

        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}
