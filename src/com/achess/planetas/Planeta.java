package com.achess.planetas;

import com.achess.Agregar;
import com.achess.Juego;
import com.achess.Jugador;
import com.achess.cola.Cola;
import com.achess.cola.FlotaEnviada;
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
    protected int cantidadDineroInicial = 0;
    protected Constructores constructores[][] = new Constructores[4][0];
    protected Constructores constructoresOcupados[][] = new Constructores[4][0];
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
                System.out.println("::El planeta " + this.nombre + " de " +propietario.getNombre() +" ha sido defendido");
                break;
            }
            if(!(guerreros[indexGuerrero].length > 0)){
                indexGuerrero++;
                if(indexGuerrero > 4){
                    System.out.println("::El planeta " + this.nombre + " de " +propietario.getNombre() +" ha caído");

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

    public void turnos(){
        generarGuerrerosTurnos();
        generarDineroTurnos();
    }
    public void generarGuerrerosTurnos(){
        int cantidadGuerrerosGenerados = numerosAleatorios(minG, maxG);
        generarGuerreros(cantidadGuerrerosGenerados);
    }

    private void generarDineroTurnos(){
        int cantidadDineroGenerado = numerosAleatorios(minD, maxD);
            propietario.setDinero(propietario.getDinero() + cantidadDineroGenerado);
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
    public void enviarGuerreros(int cantidad, int indexGuerrero, int indexNave, Planeta destino){
            Naves naveElegida;
            Guerreros guerrerosElegidos[];
            if(navesDisponibles[indexNave].length > 0){ //Verifica si el planeta tiene esa nave.
                if(guerreros[indexGuerrero].length >= cantidad){//Verifica si el planeta tiene la cantidad de guerreros que se piden
                    guerrerosElegidos = new Guerreros[cantidad];

                    for(int x = 0; x < cantidad; x++){
                        guerrerosElegidos[x] = guerreros[indexGuerrero][x];
                    }
                    naveElegida = navesDisponibles[indexNave][0];
                    if(naveElegida.cargarGuerreros(cantidad,guerrerosElegidos)){
                        for(int x = 0; x < cantidad; x++){
                            agregarGuerreros(guerreros[indexGuerrero][0],indexGuerrero,false);
                        }
                        agregarNavesOcupadas(naveElegida,indexNave, true);
                        agregarNaves(naveElegida,indexNave, false);
                        float d = this.propietario.medirDistancias(this, destino);
                        int turnosNecesarios = (int)Math.ceil(d/naveElegida.getVelocidad());
                        Cola cola = new FlotaEnviada(turnosNecesarios, destino,naveElegida);
                        this.propietario.agregarCola(cola, true);
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

    /**
     * Agrega naves a las naves ocupadas. Se hace en caso de que una nave haya sido enviada a un planeta.
     * @param nave
     * @param indexNave
     * @param validar
     */
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

    public void agregarConstructoresOcupados(Constructores constructor, int indexConstructor, boolean validar){
        constructoresOcupados[indexConstructor] = agregarElementos(constructor, constructoresOcupados[indexConstructor], validar);
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
        this.cantidadDineroInicial = cantidadDinero;//
        this.propietario.setDinero(propietario.getDinero() + cantidadDinero);
    //
    }

    public void setPropietario(Jugador propietario) {
        this.propietario = propietario;
    }

    public Naves[][] getNavesOcupadas() {
        return navesOcupadas;
    }

    public Naves getNavesDisponibles(int indexNave) {
        if(navesDisponibles[indexNave].length > 0){
            return navesDisponibles[indexNave][0];
        }
        return null;
    }

    public Constructores getConstructores(int indexConstructor) {
        if(constructores[indexConstructor].length > 0){
            return constructores[indexConstructor][0];
        }
        return null;
    }

    public Constructores construirNave(int indexConstructor){
        Constructores c = constructores[indexConstructor][0];
        agregarConstructores(c, indexConstructor, false);
        agregarConstructoresOcupados(c, indexConstructor, true);
        return c;
    }


    public void dibujar1(){
        System.out.print(this.propietario.getColor()+"          "+Juego.COLORES[0] +"|");
    }
    public void dibujar2(){
        String espacios = "";
        for (int i = 0; i < 10 - this.nombre.length() ; i++) {
            espacios +=  " ";
        }
        System.out.print(this.propietario.getColor()+nombre+espacios+Juego.COLORES[0]+"|");
    }
    public void dibujar3(){
        System.out.print(this.propietario.getColor()+"Dueño:    "+Juego.COLORES[0]+"|");
    }
    public void dibujar4(){
        String p = this.propietario.getNombre();
        String espacios = "";
        if(p.length() >= 10){
            p = p.substring(10);
        }
        else{
            for (int i = 0; i < 10 - p.length() ; i++) {
                espacios +=  " ";
            }
        }

        System.out.print(this.propietario.getColor()+p+espacios+Juego.COLORES[0] + "|");

    }

    public void dibujar5(){
        System.out.print("----------|");
    }

    @Override
    public String toString() {
        int cantidadGuerreros = 0;
        for (Guerreros[] g: guerreros){
            cantidadGuerreros += g.length;
        }
        return "Planeta{" +
                "tipo='" + tipo + '\'' +
                "\nnombre='" + nombre + '\'' +
                "\nporcentajeMuerte=" + porcentajeMuerte +
                "\nguerreros=" + cantidadGuerreros +
                "\npropietario=" + propietario.getNombre() +
                '}';
    }
}
