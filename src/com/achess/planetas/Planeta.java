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
    protected float porcentajeMuerte;
    protected int cantidadDinero;
    protected Constructores constructores[][] = new Constructores[4][0];
    protected Naves navesDisponibles[][] = new Naves[4][0];
    protected Naves navesOcupadas[][] = new Naves[4][0];
    protected Guerreros guerreros[][] = new Guerreros[5][0];
    protected Guerreros enemigos[];
    protected Jugador propietario;
    protected int maxG;
    protected int minG;
    protected int maxD;
    protected int minD;

    public Planeta(String nombre, int posX, int posY){
        this.nombre = nombre;
        this.posX = posX;
        this.posY = posY;
        agregarNaves(new NabooN1(0, this), 0, true);
        this.porcentajeMuerte = numerosAleatorios(100, 9999)/1000;
        generarGuerrerosTurnos();
    }

    public void batalla(Planeta enemigo){
        int iterador = 0;
        int bE, bG;
        int indexGuerrero = 0;
        while (true){
            if(!(guerreros[indexGuerrero].length > 0)){
                indexGuerrero++;
                if(indexGuerrero > 4){
                    //Pierde
                    break;
                }
                continue;
            }
            Guerreros g = guerreros[indexGuerrero][0];
            Guerreros e = enemigos[0];
            float ataqueE = e.getValorAtaque();
            float ataqueG = g.getValorAtaque();
            if(ataqueE > ataqueG){
                agregarGuerreros(g, g.getIndexGuerrero(), false);
                e.setCantidadBatallas(e.getCantidadBatallas()+1);
            }
            else if(ataqueE < ataqueG){
                quitarEnemigos(e);
                g.setCantidadBatallas(g.getCantidadBatallas()+1);
            }


            bE = e.getCantidadBatallas();
            bG = g.getCantidadBatallas();

        }
    }
    public void generarGuerrerosTurnos(){
        int cantidadGuerrerosGenerados = numerosAleatorios(minG, maxG);
        generarGuerreros(cantidadGuerrerosGenerados);
    }
    protected abstract void generarGuerreros(int cantidad);

    /**
     * Valida la nave pedida y los guerreros solicitados. Si los datos
     * solicitados son correctros mueve la nave al arreglo navesOcupadas. Y agrega la tarea a la cola.
     *
     * @param cantidad
     * @param indexGuerrero
     * @param indexNave
     */
    protected void enviarGuerreros(int cantidad, int indexGuerrero, int indexNave){
            Naves naveElegida;
            Guerreros guerrerosElegidos[];
            if(navesDisponibles[indexNave].length > 0){ //Verifica si el planeta tiene esa nave.
                if(guerreros[indexGuerrero].length >= cantidad){//Verifica si el planeta tiene la cantidad de guerreros que se piden
                    guerrerosElegidos = new Guerreros[cantidad];

                    for(int x = 0; x < cantidad; x++){
                        guerrerosElegidos[x] = guerreros[indexGuerrero][x];
                    }
                    naveElegida = navesDisponibles[indexGuerrero][0];
                    if(naveElegida.cargarGuerreros(cantidad,guerrerosElegidos)){
                        for(int x = 0; x < cantidad; x++){
                            agregarGuerreros(guerreros[indexGuerrero][0],indexGuerrero,false);
                        }
                        agregarNavesOcupadas(naveElegida,indexNave, true);
                        agregarNaves(naveElegida,indexNave, false);
                    }
                    else{
                        System.out.println("Acción no completada");
                    }
                }
                else{
                    System.out.println("Cantidad de guerreros insuficiente");
                    System.out.println("Cantidad de guerreros disponibles: " + guerreros[indexGuerrero].length);
                }
            }
            else{
                System.out.println("Nave no disponible");
            }
    }

    /**
     * Agrega una nave a la matriz de navesDisponibles.
     * @param nave
     * @param indexNave
     */
    public void agregarNaves(Naves nave, int indexNave, boolean validar){
        navesDisponibles[indexNave] = agregarElementos(nave,navesDisponibles[indexNave], validar);
    }

    public void agregarNavesOcupadas(Naves nave, int indexNave, boolean validar){
        navesOcupadas[indexNave] = agregarElementos(nave,navesOcupadas[indexNave], validar);
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

    public void quitarEnemigos(Guerreros guerrero){
        enemigos = agregarElementos(guerrero, enemigos, false);
    }

    public void setEnemigos(Guerreros[] enemigos) {
        this.enemigos = enemigos;
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

    public float getPorcentajeMuerte() {
        return porcentajeMuerte;
    }

    public int getCantidadDinero() {
        return cantidadDinero;
    }

    public Jugador getPropietario() {
        return propietario;
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
