package com.bsjjim.test.config.advice;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice(annotations = Controller.class)
@Controller
@Profile("fo-api")
public class FOHtmlResponseAdvice implements ErrorController{

	private static final String ERROR_PATH = "/error1";
	
	public static final int		HTTP_STATUS_CODE_404	= 404;
	public static final String	DEFAULT_RETURN_PAGE_404	= "error";

	public static final int		HTTP_STATUS_CODE_500	= 500;
	public static final String	DEFAULT_RETURN_PAGE_500	= "error";

	
	@Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

	@RequestMapping(ERROR_PATH)
	public String handleError(HttpServletRequest request, Model model) {
		
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));
		String statusCode = String.valueOf(status);
		if ( status.equals(HttpStatus.NOT_FOUND.value()) ) {
			return DEFAULT_RETURN_PAGE_404;
		}
		
		return DEFAULT_RETURN_PAGE_500;
	}
}
