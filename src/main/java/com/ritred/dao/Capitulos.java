package com.ritred.dao;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "capitulos")
public class Capitulos {

	private int pkCapitulo;
	private int nPaginas;
	private float puntuacion;
	private String listaCap;
	private Date ultimaEdicion;
	private String titulo;

	private Relatos relato;

	public Capitulos() {

	}

	@ManyToOne
	@JoinColumn(name = "Relatos_pkRelatos")
	public Relatos getRelato() {
		return relato;
	}

	public void setRelato(Relatos relato) {
		this.relato = relato;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getPkCapitulo() {
		return pkCapitulo;
	}

	public void setPkCapitulo(int pkCapitulo) {
		this.pkCapitulo = pkCapitulo;
	}

	public int getnPaginas() {
		return nPaginas;
	}

	public void setnPaginas(int nPaginas) {
		this.nPaginas = nPaginas;
	}

	public float getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(float puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getListaCap() {
		return listaCap;
	}

	public void setListaCap(String listaCap) {
		this.listaCap = listaCap;
	}

	public Date getUltimaEdicion() {
		return ultimaEdicion;
	}

	public void setUltimaEdicion(Date ultimaEdicion) {
		this.ultimaEdicion = ultimaEdicion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
