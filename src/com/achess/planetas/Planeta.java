package com.achess.planetas;

import com.achess.Agregar;
import com.achess.Jugador;
import com.achess.constructores.*;
import com.achess.guerreros.*;
import com.achess.naves.*;

import java.lang.reflect.Constructor;
import java.util.Arrays;

public abstract class Planeta implements Agregar {
    protected String tipo;
    protected String nombre;
    protected int posX;
    protected int posY;
    protected float porcentajeMuerte;
    protected int cantidadDineroInicial;
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
        this.porcentajeMuerte = numerosAleatorios(100, 9999)/1000;
    }

    public void constructoresIniciales(int cantidad){
        for (int i = 0; i < cantidad; i++) {
            agregarConstructores(new Obrero(0, this), 0, true);
        }
    }

    public void navesIniciales(int cantidad){
        for (int i = 0; i < cantidad ; i++) {
            agregarNaves(new NabooN1(0, this), 0, true);
        }
    }

    public abstract void guerrerosIniciales(int cantidad);

    public void batalla(Planeta enemigo){
        int bE, bG;
        int indexGuerrero = 0;
        System.out.println(this + " VS. \n" + enemigo);
        while (true){
            if(!(enemigos.length > 0)){
                System.out.println("::El planeta " + this.nombre + "de" +propietario.getNombre() +"ha sido defendido");
                break;
            }
            if(!(guerreros[indexGuerrero].length > 0)){
                indexGuerrero++;
                if(indexGuerrero > 4){
                    System.out.println("::El planeta " + this.nombre + "de" +propietario.getNombre() +"ha caído");

                    if(propietario.getNombre().equals("NEUTRO")){
                        enemigo.getPropietario().setDinero(enemigo.getPropietario().getDinero() + cantidadDineroInicial);
                    }
                    enemigo.propietario.agregarPlanetas(this, true);
                    this.propietario.agregarPlanetas(this,false);
                    this.propietario = enemigo.propietario;
                    this.propietario.agregarPlanetas(this, true);
                    for(Guerreros gue: enemigos){
                        agregarGuerreros(gue, gue.getIndexGuerrero(), true);
                    }
                    enemigos = new Guerreros[0];
                    break;
                }
                continue;
            }
            Guerreros g = guerreros[indexGuerrero][0];
            Guerreros e = enemigos[0];
            float ataqueE = e.getValorAtaque();
            float ataqueG = g.getValorAtaque();
            if(ataqueE > ataqueG){
                //Gana enemigo, elimina guerrero
                agregarGuerreros(g, g.getIndexGuerrero(), false);
                e.setCantidadBatallas(e.getCantidadBatallas()+1);
            }
            else if(ataqueE < ataqueG){
                //Gana guerrero, elimina enemigo
                quitarEnemigos(e);
                g.setCantidadBatallas(g.getCantidadBatallas()+1);
            }
            else{
                g.setCantidadBatallas(g.getCantidadBatallas()+1);
                e.setCantidadBatallas(e.getCantidadBatallas()+1);
            }
            bE = e.getCantidadBatallas();
            bG = g.getCantidadBatallas();

            if(bE == 2){
                quitarEnemigos(e);
            }
            if(bG == 2){
                agregarGuerreros(g, g.getIndexGuerrero(), false);
            }
        }
    }
    //Turnos
    public void generarGuerrerosTurnos(){
        int cantidadGuerrerosGenerados = numerosAleatorios(minG, maxG);
        generarGuerreros(cantidadGuerrerosGenerados);
    }

    private void generarDineroTurnos(){
        int cantidadDineroGenerado = numerosAleatorios(minD, maxD);
        if(!propietario.getNombre().equals("NEUTRO")){
            propietario.setDinero(propietario.getDinero() + cantidadDineroGenerado);
        }
    }

    //
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
        return cantidadDineroInicial;
    }

    public Jugador getPropietario() {
        return propietario;
    }

    public Guerreros[][] getGuerreros() {
        return guerreros;
    }

    public void setCantidadDineroInicial(int cantidadDinero) {
        this.cantidadDineroInicial = cantidadDinero;
        if(!propietario.getNombre().equals("NEUTRO")){
            propietario.setDinero(propietario.getDinero() + cantidadDinero);
        }
    }

    public void setPropietario(Jugador propietario) {
        this.propietario = propietario;
    }

    public Naves[][] getNavesOcupadas() {
        return navesOcupadas;
    }

    @Override
    public String toString() {
        int cantidadGuerreros = 0;
        for (Guerreros[] g: guerreros){
            cantidadGuerreros += g.length;
        }
        return "Planeta{" +
                "tipo='" + tipo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", porcentajeMuerte=" + porcentajeMuerte +
                ", guerreros=" + cantidadGuerreros +
                ", propietario=" + propietario.getNombre() +
                '}';
    }
}
