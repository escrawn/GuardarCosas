package com.ritred.ritred;

import com.ritred.crud.RelatosCrud;
import com.ritred.crud.UsuarioCrud;
import com.ritred.dao.Relatos;
import com.ritred.dao.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    public ModelAndView registroPost(@ModelAttribute("usuario") Usuario usuario, BindingResult bindingResult) {

        System.out.println("Llamado post registro");
        if (bindingResult.hasErrors()) {
            return new ModelAndView("registro");
        }

        ModelAndView mav = new ModelAndView("registro");

        usuario.setTipoUsuario("USUARIO");
        UsuarioCrud uc = new UsuarioCrud();
        uc.createUsuario(usuario);

        String mens = "Te has registrado correctamente";
        mav.addObject("mensaje", mens);

        return mav;
    }

    @GetMapping(value = "/login")
    public ModelAndView login() {
        Usuario us = new Usuario();
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("usuario", us);

        System.out.println("Llamado get login");
        return mav;
    }

    @PostMapping(value = "/login")
    public ModelAndView postLogin(@ModelAttribute("usuario") Usuario usuario, BindingResult bindingResult) {

        System.out.println("Llamado post login");
        boolean correcto = false;

        if (bindingResult.hasErrors()) {
            return new ModelAndView("login");
        }

        ModelAndView mav = new ModelAndView("registro");
        UsuarioCrud uc = new UsuarioCrud();

        Usuario us = null;

        //Comprobamos que el username no este vacío
        if (usuario.getUsername().isEmpty()) {
            System.out.println("Username vacio");
            return new ModelAndView("login");
        }

        //Obtenemos el usuario con ese username y comprobamos las contraseñas
        us = uc.readByUsername(usuario.getUsername());
        if (us.getContrasena().equals(usuario.getContrasena())) {
            correcto = true;
        }

        //Si no existe ningun usuario devolvemos al login
        if (us == null || !correcto) {
            System.out.println("No funciona :(");
            return new ModelAndView("login");
        }

        //Si todo va bien redireccionamos a registro
        if (correcto == true) {
            System.out.println("Funciona!!");
            return mav;
        }

        return new ModelAndView("login");
    }

    @GetMapping(value = "/admin")
    public ModelAndView admin(Authentication auth) {
        ModelAndView mav = new ModelAndView("admin");

        for (GrantedAuthority authority : auth.getAuthorities()) {
            System.out.println(authority.getAuthority() + ",");
        }


        return mav;

    }


}
