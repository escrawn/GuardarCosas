package com.ritred.ritred;

import com.ritred.crud.RelatosCrud;
import com.ritred.dao.Relatos;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    @GetMapping(value = "/")
    public ModelAndView greeting() {
        ModelAndView mav = new ModelAndView("index");
        RelatosCrud rc = new RelatosCrud();
        List<Relatos> novedades = rc.getNovedadesRelatos();

        mav.addObject("relatos", novedades);
        return mav;
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
