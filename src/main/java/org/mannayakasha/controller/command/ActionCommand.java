package org.mannayakasha.controller.command;

import org.mannayakasha.service.IServiceFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Base class for all commands.
 *
 * @author Pavel
 * @version 1.0 29.08.2017.
 */
public abstract class ActionCommand {

    protected IServiceFactory serviceFactory;

    public abstract String execute(HttpServletRequest request);
}
