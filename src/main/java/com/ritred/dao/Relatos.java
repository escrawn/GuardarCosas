package com.ritred.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "relatos")
public class Relatos implements Comparable<Relatos>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int pkRelatos;
	@Column
	private String titulo;
	@Column
	private int nPaginas;
	@Column
	private float puntuacion;
	@Column
	private String archivo;
	@Column
	private Date fechaCreacion;
	@Column
	private Date ultimaEdicion;

	@ManyToOne
	@JoinColumn(name = "Usuario_pkUsuario")
	private Usuario usuario;

	@JoinTable(name = "relatos_has_enganchados", joinColumns = {
			@JoinColumn(name = "Relatos_pkRelatos", referencedColumnName = "pkRelatos", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "usuario_pkEnganchado", referencedColumnName = "pkUsuario", nullable = false) })
	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private List<Usuario> enganchados;

	@OneToMany(mappedBy = "relato", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Capitulos> capitulos;

	public Relatos() {

	}

	public List<Capitulos> getCapitulos() {
		return capitulos;
	}

	public void setCapitulos(List<Capitulos> capitulos) {
		this.capitulos = capitulos;
	}

	public int getPkRelatos() {
		return pkRelatos;
	}

	public void setPkRelatos(int pkRelatos) {
		this.pkRelatos = pkRelatos;
	}

	public int getnPaginas() {
		return nPaginas;
	}

	public void setnPaginas(int nPaginas) {
		this.nPaginas = nPaginas;
	}

	public List<Usuario> getEnganchados() {
		return enganchados;
	}

	public void setEnganchados(List<Usuario> enganchados) {
		this.enganchados = enganchados;
	}

	public int getPkRelato() {
		return pkRelatos;
	}

	public void setPkRelato(int pkRelato) {
		this.pkRelatos = pkRelato;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getNumPag() {
		return nPaginas;
	}

	public void setNumPag(int numPag) {
		this.nPaginas = numPag;
	}

	public float getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(float puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getUltimaEdicion() {
		return ultimaEdicion;
	}

	public void setUltimaEdicion(Date ultimaEdicion) {
		this.ultimaEdicion = ultimaEdicion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int compareTo(Relatos r) {
		return this.getFechaCreacion().compareTo(r.getFechaCreacion());
	}

}
