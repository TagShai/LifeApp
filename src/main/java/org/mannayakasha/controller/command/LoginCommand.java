package org.mannayakasha.controller.command;

import org.mannayakasha.dao.exception.DaoException;
import org.mannayakasha.dao.impl.mysql.MySQLDaoFactoryImpl;
import org.mannayakasha.model.AccessType;
import org.mannayakasha.model.Account;
import org.mannayakasha.resource.ConfigurationManager;
import org.mannayakasha.resource.LoginMessageManager;
import org.mannayakasha.service.exception.ServiceException;
import org.mannayakasha.service.impl.ServiceFactoryImpl;
import org.mannayakasha.service.interfaces.IAccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Login user into the system.
 *
 * @author Pavel
 * @version 1.0 29.08.2017.
 */
public class LoginCommand extends ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String page = null;

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Account account = null;

        if(username != null && password != null) {
            try {
                serviceFactory = new ServiceFactoryImpl(new MySQLDaoFactoryImpl());
                IAccountService service = serviceFactory.getService(IAccountService.class);
                account = service.findByLoginAndPassword(username, password);
            } catch (ServiceException | DaoException e) {
                e.printStackTrace();
            }
        }

        if (account != null) {
            HttpSession httpSession = request.getSession();

/*            if (httpSession.getAttribute("locale") == null) {
                Locale locale = new Locale("en");
                request.setAttribute("locale", locale);
            }*/

            httpSession.setAttribute("user", account.getUsername());
            httpSession.setAttribute("accessType", account.getAccessType());

            if (account.getAccessType().getAccess().equals(AccessType.ADMIN.getAccess())) {

            } else if (account.getAccessType().getAccess().equals(AccessType.USER.getAccess())) {

            }
            page = ConfigurationManager.getProperty("path.page.main");
        } else {
            request.setAttribute("errorLoginPassMessage", LoginMessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }

        serviceFactory.close();
        return page;
    }
}