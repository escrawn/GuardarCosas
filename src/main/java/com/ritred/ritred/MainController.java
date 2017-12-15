package com.ritred.ritred;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

	@GetMapping(value = "/user/{id}/")
	public String usuario(@PathVariable(value = "id") Long id) {
		return "";
	}

	@GetMapping(value = "/user/{id}/relato/{idRelato}")
	public String relatoDeUsuario(@PathVariable(value = "id") Long idUsuario,
			@PathVariable(value = "idRelato") Long idRelato) {
		return "";
	}

}
