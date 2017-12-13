package com.ritred.crud;

import com.ritred.dao.Capitulos;
import com.ritred.dao.Relatos;

public class PruebasCrud {

	public static void main(String[] args) {

		CapitulosCrud cc = new CapitulosCrud();
		RelatosCrud rc = new RelatosCrud();

		cc.setup();
		rc.setup();

		Relatos r = rc.getRelatoById(1);

		Capitulos capitulo = new Capitulos();
		capitulo.setListaCap("rutaALista");
		capitulo.setTitulo("TituloCapitulo");

		capitulo.setRelato(r);

		rc.addCapitulo(r, capitulo);

		rc.exit();
		cc.exit();
	}
}
