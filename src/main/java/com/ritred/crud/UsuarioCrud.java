package com.ritred.crud;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ritred.dao.Relatos;
import com.ritred.dao.Usuario;

public class UsuarioCrud extends MainCrud {

    public UsuarioCrud() {

    }

    public void createUsuario(Usuario us) {
        setup();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(us);

        session.getTransaction().commit();
        session.close();
        exit();
    }

    protected Usuario readById(int pkUsuario) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Usuario us = null;
        us = session.get(Usuario.class, pkUsuario);

        session.getTransaction().commit();
        session.close();

        return us;
    }

    public Usuario readByUsername(String username, String password) {
        setup();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query q = session.createQuery("from usuario as c where c.username =:username and c.contrasena =:contrasena");
        q.setString("username", username);
        q.setString("contrasena", password);

        List result = q.list();
        Usuario us = (Usuario) result.get(0);

        session.getTransaction().commit();
        session.close();
        exit();

        return us;
    }

    protected void addSeguidor(Usuario usuario, Usuario seguidor) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Usuario us = null;
        Usuario antiguo = null;

        antiguo = session.get(Usuario.class, usuario.getPkUsuario());
        us = antiguo;

        us.getSeguidores().add(seguidor);

        session.getTransaction().commit();
        session.close();
        updateUsuario(us, antiguo);

    }

    protected void updateUsuario(Usuario antiguo, Usuario nuevo) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        antiguo = nuevo;

        session.update(antiguo);
        session.getTransaction().commit();
        session.close();

    }

    protected List<Relatos> getRelatosUsuario(Usuario usuario) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Usuario usBD = this.readById(usuario.getPkUsuario());
        List<Relatos> relatos = usBD.getRelatos();

        session.getTransaction().commit();
        session.close();

        return relatos;

    }

    protected void delete(Usuario us) {

    }

}
