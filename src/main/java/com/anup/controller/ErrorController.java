package com.anup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {

	private static final String ERROR_MAPPING = "/error";

	@RequestMapping(value = ERROR_MAPPING)
	public String error() {
		return "redirect:/login.jsf";
	}

	@Override
	public String getErrorPath() {
		return ERROR_MAPPING;
	}

}
