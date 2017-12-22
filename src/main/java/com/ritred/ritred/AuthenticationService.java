package com.ritred.ritred;

import com.ritred.crud.UsuarioCrud;
import com.ritred.dao.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UsuarioCrud uc;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = uc.readByUsername(username);

        GrantedAuthority authority = new SimpleGrantedAuthority(usuario.getTipoUsuario());

        ArrayList roles = new ArrayList();
        roles.add(usuario.getTipoUsuario());

        Usuario usuarioDetail = new Usuario(usuario.getUsername(), usuario.getContrasena(), roles);
        usuarioDetail.setUsername(usuario.getUsername());
        usuarioDetail.setContrasena(usuario.getContrasena());
        usuarioDetail.setTipoUsuario(usuario.getTipoUsuario());

        UserDetails userDetails = (UserDetails) new Usuario(usuario.getUsername(),usuario.getContrasena(),roles);



        return userDetails;

    }
}
