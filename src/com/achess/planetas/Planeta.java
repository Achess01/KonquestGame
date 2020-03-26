package com.achess.planetas;

import com.achess.Agregar;
import com.achess.Jugador;
import com.achess.constructores.*;
import com.achess.guerreros.*;
import com.achess.naves.*;

import java.lang.reflect.Constructor;

public abstract class Planeta implements Agregar {
    protected String nombre;
    protected int posX;
    protected int posY;
    protected int porcentajeMuerte;
    protected int cantidadDinero;
    protected int cantidadGuerreros[] = new int[5];
    protected Constructores constructores[][] = new Constructores[4][];
    protected Naves navesDisponibles[][] = new Naves[4][];
    protected Naves navesOcupadas[][] = new Naves[4][];
    protected Guerreros guerreros[][] = new Guerreros[5][];
    protected Guerreros enemigos[];
    protected Jugador propietario;

    public Planeta(String nombre, int posX, int posY) {
        this.nombre = nombre;
        this.posX = posX;
        this.posY = posY;
        agregarNaves(new NabooN1(0), 0, true);
    }

    protected abstract void generarGuerreros();
    protected void enviarGuerreros(int cantidad, int indexGuerrero, int indexNave){

    }

    /**
     * Agrega una nave a la matriz de navesDisponibles.
     * @param nave
     * @param indexNave
     */
    public void agregarNaves(Naves nave, int indexNave, boolean validar){
        navesDisponibles[indexNave] = agregarElementos(nave,navesDisponibles[indexNave], validar);
    }

    /**
     * Agrega una constructor a la matriz de constructores
     * @param constructor
     * @param indexConstructor
     */
    public void agregarConstructores(Constructores constructor, int indexConstructor, boolean validar){
        constructores[indexConstructor] = agregarElementos(constructor, constructores[indexConstructor], validar);
    }

    /**
     * Agrega un guerrero a la matriz de guerreros.
     * @param guerrero
     * @param indexGuerrero
     */
    public void agregarGuerreros(Guerreros guerrero, int indexGuerrero, boolean validar){
        guerreros[indexGuerrero] = agregarElementos(guerrero, guerreros[indexGuerrero], validar);
    }

    public String getNombre() {
        return nombre;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getPorcentajeMuerte() {
        return porcentajeMuerte;
    }

    public int getCantidadDinero() {
        return cantidadDinero;
    }

    public Jugador getPropietario() {
        return propietario;
    }

    public int[] getCantidadGuerreros() {
        return cantidadGuerreros;
    }

    public Constructores[][] getConstructores() {
        return constructores;
    }

    public Naves[][] getNavesDisponibles() {
        return navesDisponibles;
    }

    public Naves[][] getNavesOcupadas() {
        return navesOcupadas;
    }

    public Guerreros[][] getGuerreros() {
        return guerreros;
    }
}
