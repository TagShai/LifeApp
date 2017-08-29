package org.mannayakasha.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Set request and response encoding to UTF-8.
 *
 * @author Pavel
 * @version 1.0 28.08.2017.
 */

@WebFilter(filterName = "EncodingFilter", urlPatterns = "/*")
public class EncodingFilter implements Filter {
    //private static final Logger LOGGER = Logger.getLogger(EncodingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //LOGGER.info("Encodings filter started");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8"); // TODO: 04.08.2017
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        //LOGGER.info("Encodings filter destroyed");
    }
}
