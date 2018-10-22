package cn.gin.passport.module.web.filter;

import cn.gin.passport.module.config.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 *
 */
@Order(PropertiesAssembledFilter.DEFAULT_ORDER)
@WebFilter(urlPatterns = "/*")
public class PropertiesAssembledFilter implements Filter {

    /**
     * Current filter default execution order. The smaller the implementation, the earlier execute
     */
    public static final int DEFAULT_ORDER = 90;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        final ServletContext context = request.getServletContext();

        if (context.getAttribute(ApplicationProperties.SELF_KEY) == null) {
            context.setAttribute(ApplicationProperties.SELF_KEY, this.applicationProperties);
        }

        chain.doFilter(request, response);
    }
}