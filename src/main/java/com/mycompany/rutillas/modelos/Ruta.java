/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rutillas.modelos;

/**
 *
 * @author Nayra
 */
public class Ruta {
    private int id;
    private String nombre;
    private float distancia;
    private int desnivel;
    private String fecha;
    private String localizacion;
    private int dificultad;
    private int usuario;
    
    public Ruta(){}
    
    public Ruta(int id, String nombre, float distancia, int desnivel, String fecha, String localizacion, int dificultad, int usuario){
        this.id = id;
        this.nombre = nombre;
        this.distancia = distancia;
        this.desnivel = desnivel;
        this.fecha = fecha;
        this.localizacion = localizacion;
        this.dificultad = dificultad;
        this.usuario = usuario;
        
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the distancia
     */
    public float getDistancia() {
        return distancia;
    }

    /**
     * @param distancia the distancia to set
     */
    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

    /**
     * @return the desnivel
     */
    public int getDesnivel() {
        return desnivel;
    }

    /**
     * @param desnivel the desnivel to set
     */
    public void setDesnivel(int desnivel) {
        this.desnivel = desnivel;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the Localizacion
     */
    public String getLocalizacion() {
        return localizacion;
    }

    /**
     * @param localizacion the Localizacion to set
     */
    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    /**
     * @return the dificultad
     */
    public int getDificultad() {
        return dificultad;
    }

    /**
     * @param dificultad the dificultad to set
     */
    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    /**
     * @return the usuario
     */
    public int getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ruta: ").append(nombre);
        sb.append(", ").append(localizacion);
        return sb.toString();
    }
    
    
}
