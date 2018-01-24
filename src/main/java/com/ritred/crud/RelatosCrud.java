package com.ritred.crud;

import java.util.Collections;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.ritred.dao.Capitulos;
import com.ritred.dao.Relatos;
import com.ritred.dao.Usuario;

@Component
public class RelatosCrud extends MainCrud {

    public RelatosCrud() {

    }


    protected void createRelatos(Relatos relato) {

        this.setup();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(relato);

        session.getTransaction().commit();
        session.close();

        this.exit();
    }

    protected Relatos getRelatoById(int pk) {

        this.setup();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Relatos r = session.get(Relatos.class, pk);

        session.getTransaction().commit();
        session.close();
        this.exit();
        return r;

    }

    protected void addEnganchado(Relatos r, Usuario enganchado) {
        this.setup();
        Relatos rsAnt = getRelatoById(r.getPkRelatos());
        rsAnt.getEnganchados().add(enganchado);

        updateRelato(rsAnt, rsAnt);
        this.exit();
    }

    protected void updateRelato(Relatos antiguo, Relatos nuevo) {
        this.setup();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        antiguo = nuevo;

        session.update(antiguo);
        session.getTransaction().commit();
        session.close();
        this.exit();
    }

    protected void addCapitulo(Relatos relato, Capitulos capitulo) {
        this.setup();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Relatos relatoBD = getRelatoById(relato.getPkRelatos());
        Relatos aux = relatoBD;


        List<Capitulos> capitulos = relatoBD.getCapitulos();
        capitulos.add(capitulo);

        session.getTransaction().commit();
        session.close();

        updateRelato(relato, aux);
        this.exit();
    }

    protected List<Relatos> getRelatos() {
        setup();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query q = session.createQuery("from Relatos");
        List<Relatos> relatos = q.list();

        session.getTransaction().commit();
        session.close();

        this.exit();
        return relatos;
    }

    public List<Relatos> getNovedadesRelatos() {

        //List<Relatos> novedades = getRelatos();

        setup();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query q = session.createQuery("from Relatos");
        List<Relatos> novedades = q.list();
        Collections.sort(novedades);

        this.exit();
        return novedades;
    }

    public List<Relatos> getRelatosUsuario(int id){

        setup();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query q = session.createQuery("from Relatos where Usuario_pkUsuario = :id");
        q.setInteger("id",id);

        List<Relatos> relatos = q.list();
        session.getTransaction().commit();
        session.close();
        exit();

        return relatos;
    }




}
