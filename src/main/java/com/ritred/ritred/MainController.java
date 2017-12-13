package com.ritred.ritred;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping(value = "/")
	public String greeting() {

		return "/index";
	}

	@GetMapping(value = "/login")
	public String login() {
		return "/login";
	}

	@GetMapping(value = "/admin")
	public String admin() {
		return "/admin";
	}

	@GetMapping(value = "/registro")
	public String registro() {
		return "/registro";
	}
}
