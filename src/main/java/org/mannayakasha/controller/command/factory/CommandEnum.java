package org.mannayakasha.controller.command.factory;

import org.mannayakasha.controller.command.*;

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
    }}, REGISTRATION {{
        this.command = new RegistrationCommand();
    }};

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}