package es.caib.rolsac.back2.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LocaleServletFilter implements Filter {
	private FilterConfig _filterConfig = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		_filterConfig = filterConfig;
	}

	public void destroy() {
		_filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			HttpServletRequest req = (HttpServletRequest) request;
			LocaleRequestWrapper wrapper = new LocaleRequestWrapper(req);
			chain.doFilter(wrapper, response);
		} else {
			chain.doFilter(request, response);
		}

	}
}
