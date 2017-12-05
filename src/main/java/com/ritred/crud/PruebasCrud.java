package com.ritred.crud;

import com.ritred.dao.Relatos;
import com.ritred.dao.Usuario;

public class PruebasCrud {

	public static void main(String[] args) {

		RelatosCrud rc = new RelatosCrud();
		UsuarioCrud uc = new UsuarioCrud();

		uc.setup();
		rc.setup();

		Usuario enganchado = uc.readById(7);

		Relatos r = rc.getRelatoById(1);

		rc.addEnganchado(r, enganchado);
		rc.exit();
		uc.exit();
	}
}
