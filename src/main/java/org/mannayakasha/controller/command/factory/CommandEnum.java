package org.mannayakasha.controller.command.factory;

import org.mannayakasha.controller.command.ActionCommand;
import org.mannayakasha.controller.command.LanguageCommand;
import org.mannayakasha.controller.command.LoginCommand;
import org.mannayakasha.controller.command.LogoutCommand;

/**
 * Enum, that contains all commands.
 *
 * @author Pavel
 * @version 1.0 29.08.2017.
 */
public enum CommandEnum {
    LOGIN {{
        this.command = new LoginCommand();
    }}, LOGOUT {{
        this.command = new LogoutCommand();
    }}, RU {{
        this.command = new LanguageCommand();
    }}, EN {{
        this.command = new LanguageCommand();
    }};

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}