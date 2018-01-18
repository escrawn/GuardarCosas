package com.ritred.ritred;

import com.ritred.crud.RelatosCrud;
import com.ritred.crud.UsuarioCrud;
import com.ritred.dao.Relatos;
import com.ritred.dao.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
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

        System.err.println("Llamado post registro");
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
    public ModelAndView postLogin(@ModelAttribute("usuario") Usuario usuario, BindingResult bindingResult, HttpSession httpSession) {

        System.out.println("Llamado post login");
        boolean correcto = false;

        if (bindingResult.hasErrors()) {
            return new ModelAndView("login");
        }

        ModelAndView mav = new ModelAndView("UsuarioDetalles");
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

        //Si t0do va bien redireccionamos a registro
        if (correcto) {
            httpSession.setAttribute("TipoUsuario", us.getTipoUsuario());
            httpSession.setAttribute("Id", us.getPkUsuario());
            httpSession.setAttribute("Username", us.getUsername());
            System.out.println("Funciona!!");
            return mav;
        }

        return new ModelAndView("login");
    }

    @GetMapping(value = "usuario/{usernameURL}")
    public ModelAndView detallesUsuario(HttpSession httpSession, @PathVariable(value = "usernameURL") String usernameURL) {
        ModelAndView mav = new ModelAndView("UsuarioDetalles");

        boolean mostrar = false;
        String tu = (String) httpSession.getAttribute("TipoUsuario");
        String us = (String) httpSession.getAttribute("Username");
        int id = (int) httpSession.getAttribute("Id");

        UsuarioCrud uc = new UsuarioCrud();

        Usuario usUrl = null;

        try {
            usUrl = uc.readByUsername(usernameURL);
        } catch (Exception e) {
            usUrl.setPkUsuario(-1);
        }


        System.out.println(tu + "-----------------------------------");
        System.out.println(us + "+++++++++++++++++++++++++++++++++++");

        if (usUrl.getPkUsuario() == id) {
            mostrar = true;
        } else if (usUrl.getPkUsuario() == -1) {
            mostrar = false;
        }
        mav.getModelMap().addAttribute("tUsuario", tu);
        mav.getModelMap().addAttribute("user", us);
        mav.getModelMap().addAttribute("mostrar", mostrar);

        return mav;
    }

    @GetMapping(value = "/test")
    public ModelAndView pruebasHTML() {
        ModelAndView mav = new ModelAndView("test");


        RelatosCrud rc = new RelatosCrud();
        List<Relatos> relatos = rc.getRelatosUsuario(7);

        UsuarioCrud uc = new UsuarioCrud();
        List<Relatos> relatosEng = uc.getEnganchadosUsuario(7);


        mav.addObject("relatosU", relatos);
        mav.addObject("relatosEng",relatosEng);

        return mav;
    }

}
