package com.lizeth.codigo.catalogo;

import android.graphics.Bitmap;

/**
 * Created by FAMILIA on 30/01/2016.
 */
public class Aplicacion {

    private String Nombre;
    private String UrlImagen;
    private String Descripcion;
    private String Precio;
    private String titulo;
    private String Autor;
    private String Categoria;
    public Bitmap Imagen;


    public  Aplicacion(){
      /*  this.Descripcion=descripcion;
        this.UrlImagen=imagen;
        this.Nombre=nombre;
        this.Precio=precio;
        this.titulo=titulo;
        this.Autor=Autor;
        this.Categoria=Categoria;*/
        super();
    }

    public String getNombre() {
        return Nombre;
    }
    public String getUrlImagen() {
        return UrlImagen;
    }
    public String getDescripcion() {
        return Descripcion;
    }
    public String getPrecio() {
        return Precio;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getAutor() {
        return Autor;
    }
    public String getCategoria() {
        return Categoria;
    }
    public Bitmap getImagen() {
        // TODO Auto-generated method stub
        return Imagen;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }
    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    public void setUrlImagen(String UrlImagen ) {
        this.UrlImagen = UrlImagen;
    }
    public void setPrecio(String Precio) {
        this.Precio = Precio;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setAutor(String Autor) {
        this.Autor = Autor;
    }
    public void setCategoria(String nombre) {
        this.Categoria = Categoria;
    }

}
