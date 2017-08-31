package org.mannayakasha.util.validator.factory;

import org.mannayakasha.model.Account;
import org.mannayakasha.model.Entity;
import org.mannayakasha.util.validator.AccountValidator;
import org.mannayakasha.util.validator.IValidator;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory to create concrete type of validator.
 *
 * @author Pavel
 * @version 1.0 31.08.2017.
 */
public class ValidatorFactory {
    private static Map<Class<? extends Entity>, Class<? extends IValidator<?>>> validators = new HashMap<>();

    static {
        validators.put(Account.class, AccountValidator.class);
    }

    @SuppressWarnings("unchecked")
    public static <Type extends Entity> IValidator<Type> createValidator(Class<Type> entity) {
        try {
            return (IValidator<Type>)validators.get(entity).newInstance();
        } catch(InstantiationException | IllegalAccessException e) {
            return null;
        }
    }
}
