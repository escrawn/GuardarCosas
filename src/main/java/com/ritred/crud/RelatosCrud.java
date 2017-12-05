package com.ritred.crud;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.ritred.dao.Relatos;
import com.ritred.dao.Usuario;

@Component
public class RelatosCrud extends MainCrud {

	public RelatosCrud() {

	}

	protected void createRelatos(Relatos relato) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(relato);

		session.getTransaction().commit();
		session.close();
	}

	protected Relatos getRelatoById(int pk) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Relatos r = session.get(Relatos.class, pk);

		session.getTransaction().commit();
		session.close();

		return r;

	}

	protected void addEnganchado(Relatos r, Usuario enganchado) {

		Relatos rsAnt = getRelatoById(r.getPkRelato());
		rsAnt.getEnganchados().add(enganchado);

		updateRelato(rsAnt, rsAnt);

	}

	protected void updateRelato(Relatos antiguo, Relatos nuevo) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		antiguo = nuevo;

		session.update(antiguo);
		session.getTransaction().commit();
		session.close();

	}
}
