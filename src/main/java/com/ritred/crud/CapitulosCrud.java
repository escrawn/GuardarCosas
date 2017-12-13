package com.ritred.crud;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.ritred.dao.Capitulos;

@Component
public class CapitulosCrud extends MainCrud {

	protected Capitulos getCapituloById(int pk) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Capitulos capitulo = session.get(Capitulos.class, pk);

		session.getTransaction().commit();
		session.close();
		return capitulo;
	}

	protected void createCapitulo(Capitulos capitulo) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(capitulo);

		session.getTransaction().commit();
		session.close();
	}

	protected void updateCapitulo(Capitulos antiguo, Capitulos nuevo) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		antiguo = nuevo;

		session.update(antiguo);
		session.getTransaction().commit();
		session.close();

	}

}
