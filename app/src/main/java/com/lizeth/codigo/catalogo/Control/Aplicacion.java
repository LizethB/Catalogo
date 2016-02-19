package com.lizeth.codigo.catalogo.Control;

import android.graphics.Bitmap;

/**
 * Created by FAMILIA on 30/01/2016.
 */
public class Aplicacion {

    private String Name;
    private String Download;
    private String Summary;
    private Double Price;
    private String Title;
    private String Artist;
    private String Category;
    private String urlImag53;
    private String urlImag75;
    private String urlImag100;
    private String Currency;
    private String Type;
    private String Rigths;
    private Long IdApp;
    private String ReleaseDate;
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

    public String getName() {
        return Name;
    }
    public String getDownload() {
        return Download;
    }
    public String getSummary() {
        return Summary;
    }
    public Double getPrice() {
        return Price;
    }
    public String getTitle() {
        return Title;
    }
    public String getArtist() {
        return Artist;
    }
    public String getCategory() {
        return Category;
    }
    public String geturlImag53() {
        return urlImag53;
    }
    public String geturlImag75() {
        return urlImag75;
    }
    public String geturlImag100() {
        return urlImag100;
    }
    public String getCurrency() {
        return Currency;
    }
    public String getType() {
        return Type;
    }
    public String getRights() {
        return Rigths;
    }
    public Long getIdApp() {
        return IdApp;
    }
    public String getReleaseDate() {
        return ReleaseDate;
    }

    public Bitmap getImagen() {
        // TODO Auto-generated method stub
        return Imagen;
    }



    public void setName(String name) {
        this.Name = name;
    }
    public void setSummary(String summary) {
        this.Summary = summary;
    }
    public void setPrice(Double price ) {
        this.Price = price;
    }
    public void setCurrency(String currency) {
        this.Currency = currency;
    }
    public void setType(String type) {
        this.Type = type;
    }
    public void setRights(String rigths) {
        this.Rigths = rigths;
    }
    public void setTitle(String title) {
        this.Title = title;
    }
    public void setDownload(String download) {
        this.Download = download;
    }
    public void setIdApp(Long IdApp) {
        this.IdApp = IdApp;
    }
    public void setArtist(String artist) {
        this.Artist = artist;
    }
    public void setCategory(String category) {
        this.Category = category;
    }
    public void setReleaseDate(String releaseDate) {
        this.ReleaseDate = releaseDate;
    }
    public void setUrlImag53(String urlimag53) {this.urlImag53 = urlimag53;}
    public void setUrlImag75(String urlimag75) {
        this.urlImag75 = urlimag75;
    }
    public void setUrlImag100(String urlimag100) {
        this.urlImag100 = urlimag100;
    }

}
