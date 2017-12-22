package com.ritred.ritred;

import com.ritred.crud.RelatosCrud;
import com.ritred.crud.UsuarioCrud;
import com.ritred.dao.Relatos;
import com.ritred.dao.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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

    @GetMapping(value = "/registro")
    public ModelAndView registro() {
        ModelAndView mav = new ModelAndView("registro");
        Usuario usuario = new Usuario();
        mav.addObject("usuario", usuario);

        return mav;
    }

    @PostMapping(value = "/registro")
    public ModelAndView registroPost(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("registro");
        }

        ModelAndView mav = new ModelAndView("login");

        usuario.setTipoUsuario("USUARIO");
        UsuarioCrud uc = new UsuarioCrud();
        uc.createUsuario(usuario);

        String mens = "Te has registrado correctamente";
        mav.addObject("mensaje", mens);

        return mav;
    }

    @GetMapping(value = "/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        Usuario usLog = new Usuario();
        mav.addObject("usLog",usLog);

        return mav;
    }


    @GetMapping(value = "/admin")
    public ModelAndView admin(Authentication auth) {
        ModelAndView mav = new ModelAndView("admin");

        for(GrantedAuthority authority : auth.getAuthorities()){
            System.out.println(authority.getAuthority()+",");
        }


        return mav;

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
