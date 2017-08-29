package org.mannayakasha.controller;

import org.mannayakasha.controller.command.ActionCommand;
import org.mannayakasha.controller.command.factory.ActionFactory;
import org.mannayakasha.dao.pool.ConnectionPool;
import org.mannayakasha.dao.pool.exception.ConnectionPoolException;
import org.mannayakasha.resource.ConfigurationManager;
import org.mannayakasha.resource.MessageManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Dispatcher servlet of the application.
 *
 * @author Pavel
 * @version 1.0 28.08.2017.
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {

    //private static final Logger LOGGER = Logger.getLogger(DispatcherServlet.class);

    @Override
    public void init() throws ServletException {
        try {
            ConnectionPool.getInstance().init(); // Connection pool init
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;

        ActionFactory client = new ActionFactory();

        ActionCommand command = client.defineCommand(request);
        page = command.execute(request);

        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = ConfigurationManager.getProperty("path.page.error");
            request.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}

