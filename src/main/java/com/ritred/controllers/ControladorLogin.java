package com.ritred.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControladorLogin {

	@RequestMapping("/login")
	public String prueba() {
		return "login";
	}
}
