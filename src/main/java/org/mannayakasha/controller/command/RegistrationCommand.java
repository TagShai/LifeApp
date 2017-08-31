package org.mannayakasha.controller.command;

import org.mannayakasha.dao.exception.DaoException;
import org.mannayakasha.dao.impl.mysql.MySQLDaoFactoryImpl;
import org.mannayakasha.model.Account;
import org.mannayakasha.resource.ConfigurationManager;
import org.mannayakasha.resource.LoginRegistrationMessageManager;
import org.mannayakasha.service.exception.ServiceException;
import org.mannayakasha.service.impl.ServiceFactoryImpl;
import org.mannayakasha.service.interfaces.IAccountService;
import org.mannayakasha.util.validator.IValidator;
import org.mannayakasha.util.validator.factory.ValidatorFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Register some user in the system.
 *
 * @author Pavel
 * @version 1.0 31.08.2017.
 */
public class RegistrationCommand extends ActionCommand {

    //private static final Logger LOGGER = Logger.getLogger(RegistrationCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        IValidator<Account> validator = ValidatorFactory.createValidator(Account.class);
        Account account = validator.validate(request);

        if (account != null) {
            try {
                serviceFactory = new ServiceFactoryImpl(new MySQLDaoFactoryImpl());
                IAccountService userService = serviceFactory.getService(IAccountService.class);
                userService.save(account);
                serviceFactory.close();
            } catch (ServiceException | DaoException e) {
                e.printStackTrace();
            }
            request.setAttribute("successRegistration", LoginRegistrationMessageManager.getProperty("message.success.registration"));
            page = ConfigurationManager.getProperty("path.page.login");
        } else {
            //page = ConfigurationManager.getProperty("path.page.registration");
            page = ConfigurationManager.getProperty("path.page.login");
        }

        return page;
    }
}
