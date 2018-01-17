package com.ritred.crud;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ritred.dao.Relatos;
import com.ritred.dao.Usuario;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
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

    public Usuario readById(int pkUsuario) {

        setup();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Usuario us = null;
        us = session.get(Usuario.class, pkUsuario);

        session.getTransaction().commit();
        session.close();
        exit();

        return us;
    }

    public Usuario readByUsername(String username) {
        setup();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query q = session.createQuery("from Usuario as c where c.username = :username");
        q.setString("username", username);


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

    public List<Relatos> getRelatosUsuario(int pkUsuario) {

        setup();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Usuario us = session.get(Usuario.class, pkUsuario);
        List<Relatos> relatos = us.getRelatos();

        session.getTransaction().commit();
        session.close();

        exit();
        return relatos;

    }

    public List<Relatos> getEnganchadosUsuario(int pk) {

        setup();

        Session session = sessionFactory.openSession();
        session.beginTransaction();


        Usuario us = session.get(Usuario.class, pk);

        List<Relatos> relatosConEnganchados = us.getRelatosConEnganchados();


        session.getTransaction().commit();
        session.close();

        exit();
        return relatosConEnganchados;
    }
}
