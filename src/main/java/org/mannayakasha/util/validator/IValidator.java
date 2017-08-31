package org.mannayakasha.util.validator;

import org.mannayakasha.model.Entity;

import javax.servlet.http.HttpServletRequest;

/**
 * Base service for all validators.
 *
 * @author Pavel
 * @version 1.0 31.08.2017.
 */
public interface IValidator<T extends Entity> {
    T validate(HttpServletRequest request); // TODO: 31.08.2017 Think about some other methods or about another signature
}
