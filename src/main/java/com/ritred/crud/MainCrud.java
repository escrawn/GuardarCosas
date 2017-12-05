package com.ritred.crud;

import java.io.File;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class MainCrud {

	protected SessionFactory sessionFactory;

	/**
	 * Configura la conexion con la base de datos.
	 */
	protected void setup() {
	        try {
	            // Create the SessionFactory from hibernate.cfg.xml
	            sessionFactory = new Configuration().configure("hibernate.cfg.xml")
	                    .buildSessionFactory();
	        } catch (Throwable ex) {
	            // Make sure you log the exception, as it might be swallowed
	            System.err.println("Initial SessionFactory creation failed." + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	    }

	/**
	 * Crea un objeto en la base de datos.
	 */
	protected void create() {
	}

	/**
	 * Obtiene un objeto de la base de datos.
	 */
	protected void read() {
	}

	/**
	 * Modifica un objeto de la base de datos.
	 */
	protected void update() {
	}

	/**
	 * Borra un objeto de la base de datos.
	 */
	protected void delete() {
	}

	/**
	 * Cierra la conexion con la base de datos.
	 */
	protected void exit() {
		sessionFactory.close();
	}

}
