package org.mannayakasha.util.validator;

import org.mannayakasha.dao.exception.DaoException;
import org.mannayakasha.dao.impl.mysql.MySQLDaoFactoryImpl;
import org.mannayakasha.model.Account;
import org.mannayakasha.resource.LoginRegistrationMessageManager;
import org.mannayakasha.service.IServiceFactory;
import org.mannayakasha.service.exception.ServiceException;
import org.mannayakasha.service.impl.ServiceFactoryImpl;
import org.mannayakasha.service.interfaces.IAccountService;

import javax.servlet.http.HttpServletRequest;

/**
 * Validator for {@link org.mannayakasha.model.Account} class.
 *
 * @author Pavel
 * @version 1.0 31.08.2017.
 */
public class AccountValidator implements IValidator<Account> {

    //private static final Logger LOGGER = Logger.getLogger(AccountValidator.class);

    //private final String EMAIL_REGEX = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";

    @Override
    public Account validate(HttpServletRequest request) {

        Account account = new Account();

        boolean error = false;

        String parameter = request.getParameter("id");
        if (parameter != null) {
            account.setId(Integer.parseInt(parameter));
        } else {
            // TODO: 31.08.2017
        }

        parameter = request.getParameter("username");
        if (parameter != null && !parameter.isEmpty()) {
            account.setUsername(parameter);
            if (account.getUsername().length() < 8 || account.getUsername().length() > 16) {
                request.setAttribute("sizeUserFormUsername", LoginRegistrationMessageManager.getProperty("message.size.userForm.username"));
                error = true;
            }
        } else {
            // TODO: 31.08.2017
        }

        parameter = request.getParameter("password");
        if (parameter != null && !parameter.isEmpty()) {
            account.setPassword(parameter);
            if (account.getPassword().length() < 8) {
                request.setAttribute("sizeUserFormPassword", LoginRegistrationMessageManager.getProperty("message.size.userForm.password"));
                error = true;
            }
        } else {
            // TODO: 31.08.2017
        }

        parameter = request.getParameter("confirmPassword");
        if (parameter != null && !parameter.isEmpty()) {
            account.setConfirmPassword(parameter);
            if (!account.getConfirmPassword().equals(account.getPassword())) {
                request.setAttribute("differentUserFormPassword", LoginRegistrationMessageManager.getProperty("message.different.userForm.password"));
                error = true;
                //return null;    // TODO: 31.08.2017 It's not good to return null. Application crash.
            }
        } else {
            // TODO: 31.08.2017
        }

        try {
            IServiceFactory serviceFactory = new ServiceFactoryImpl(new MySQLDaoFactoryImpl());
            IAccountService accountService = serviceFactory.getService(IAccountService.class);
            if (accountService.findByLogin(account.getUsername()) != null) {
                request.setAttribute("duplicateUserFormUsername", LoginRegistrationMessageManager.getProperty("message.duplicate.userForm.username"));
                error = true;
            }
            serviceFactory.close();
        } catch (ServiceException | DaoException e) {
            e.printStackTrace();
        }

/*        if (!Pattern.matches(EMAIL_REGEX, user.getEmail())) {

        }*/

        if (error) {
            account = null;
        }

        return account;
    }
}
