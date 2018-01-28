package com.klyashtorny.graduation.web;

import com.klyashtorny.graduation.util.exception.ErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import com.klyashtorny.graduation.util.ValidationUtil;
import com.klyashtorny.graduation.util.exception.ApplicationException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);


    @ExceptionHandler(ApplicationException.class)
    public ModelAndView applicationErrorHandler(HttpServletRequest req, ApplicationException appEx) throws Exception {
        return getView(req, appEx, appEx.getType());
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        return getView(req, e, ErrorType.APP_ERROR);
    }

    public ModelAndView getView(HttpServletRequest req, Exception e, ErrorType type) throws Exception {
        Throwable rootCause = ValidationUtil.getRootCause(e);
        log.error("Exception at request " + req.getRequestURL(), rootCause);
        ModelAndView mav = new ModelAndView("exception/exception");
        mav.addObject("typeMessage", type);
        mav.addObject("exception", rootCause);
        mav.addObject("exception", rootCause);
        return mav;
    }
}
