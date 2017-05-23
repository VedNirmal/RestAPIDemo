/**
 * 
 */
package com.deere.customerapi.interceptor;

import java.nio.charset.Charset;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.deere.customerapi.service.CustomerService;
import com.deere.customerapi.service.exception.UnauthorizedAccessException;

/**
 * @author nirmal.ved
 *
 */
@Component
public class CustomerAPIInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	CustomerService customerService;
	
	 
	@Override
	public void afterCompletion(HttpServletRequest request,
		HttpServletResponse response, Object object, Exception exp){
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object object, ModelAndView mav){
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws UnauthorizedAccessException{
		boolean isValid = validateUser(request);
		if(isValid){
			return true;
		} else {
			throw new UnauthorizedAccessException("User is not authorized to perform this operation");
		}
	}
	
	  private boolean validateUser(HttpServletRequest request) throws UnauthorizedAccessException {
	    	final String authorization = request.getHeader("Authorization");
	    		System.out.println(authorization);
	    		if (authorization != null && authorization.startsWith("Basic")) {
	    	        String base64Credentials = authorization.substring("Basic".length()).trim();
	    	        String credentials = new String(Base64.getDecoder().decode(base64Credentials),
	    	                Charset.forName("UTF-8"));
	    	        final String[] values = credentials.split(":",2);
	    	        Integer id = Integer.valueOf(values[0]);
	    	        String pass = values[1];
	    	        customerService.findCustomer(id, pass);
	    	        return true;
	    		}
	    		return false;
		}
}
