package com.sasha.grodno.website.controllers;

import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppExceptionHandler.class);

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleConflict(DataIntegrityViolationException ex, Model model) {
        LOGGER.error("Exception occurred.", ex);
        model.addAttribute("message", getMessage(ex));
        return "error";
    }

    private String getMessage(DataIntegrityViolationException ex) {
        return ((PSQLException) ex.getCause().getCause()).getServerErrorMessage().getDetail();
    }
}
