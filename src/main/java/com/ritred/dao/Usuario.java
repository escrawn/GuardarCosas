package com.ritred.dao;

import org.hibernate.validator.constraints.Email;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuario")
public class Usuario {

    private int pkUsuario;

    @NotNull(message = "Campo obligatorio")
    @Size(min=4, max=30,message = "Nombre no válido")
    private String nombre;

    @NotNull(message = "Campo obligatorio")
    @Size(min=4, max=30,message = "Nombre de usuario no válido")
    private String username;

    @NotNull(message = "Campo obligatorio")
    @Size(min=4, max=30,message = "Contraseña no válida")
    private String contrasena;

    @NotNull(message = "Campo obligatorio")
    @Email(message = "Email no válido")
    private String email;

    private String descripcion;

    private String ubicacion;

    private String foto;

    private String tipoUsuario;

    private List<Relatos> relatos;

    private List<Usuario> seguidores;

    private List<Usuario> usuariosSeguido;

    private List<Relatos> relatosConEnganchados;

    public Usuario(String username, String contrasena, ArrayList roles) {

    }
    public Usuario(){

    }

    @ManyToMany(mappedBy = "enganchados")
    public List<Relatos> getRelatosConEnganchados() {
        return relatosConEnganchados;
    }

    public void setRelatosConEnganchados(List<Relatos> relatosConEnganchados) {
        this.relatosConEnganchados = relatosConEnganchados;
    }

    @ManyToMany(mappedBy = "seguidores")
    public List<Usuario> getUsuariosSeguido() {
        return usuariosSeguido;
    }

    public void setUsuariosSeguido(List<Usuario> usuariosSeguido) {
        this.usuariosSeguido = usuariosSeguido;
    }

    @JoinTable(name = "usuario_has_seguidores", joinColumns = {
            @JoinColumn(name = "usuario_pkUsuario", referencedColumnName = "pkUsuario", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "usuario_pkSeguidor", referencedColumnName = "pkUsuario", nullable = false)})
    @ManyToMany(cascade = {CascadeType.ALL})
    public List<Usuario> getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(List<Usuario> seguidores) {
        this.seguidores = seguidores;
    }

    @Id
    @Column(name = "pkUsuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getPkUsuario() {
        return pkUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void setPkUsuario(int pkUsuario) {
        this.pkUsuario = pkUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Relatos> getRelatos() {
        return relatos;
    }

    public void setRelatos(List<Relatos> relatos) {
        this.relatos = relatos;
    }

}
