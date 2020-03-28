package com.achess;

import com.achess.planetas.*;

public class Mapa implements Agregar{
    public static int cantidadMapas = 0;
    private int id;
    private Planeta campoJuego[][];
    private int cantidadFilas;
    private int cantidadColumnas;
    private int cantidadNeutrales;
    private Jugador jugadores[] = new Jugador[2];

    public Mapa(int cantidadFilas, int cantidadColumnas,int cantidadNeutrales, String j1, String j2) {
        this.id = ++cantidadMapas;
        this.cantidadFilas = Math.abs(cantidadFilas);
        this.cantidadColumnas = Math.abs(cantidadColumnas);
        j1.trim().toUpperCase();
        j2.trim().toUpperCase();
        if(j1.equals(j2)){
            j2 = "02".concat(j2);
        }
        jugadores[0] = new Jugador(this, j1);
        jugadores[1] = new Jugador(this, j2);
        campoJuego = new Planeta[cantidadFilas][cantidadColumnas];
    }

    public void generarPlanetas(){

    }

    public int getCantidadFilas() {
        return cantidadFilas;
    }

    public void setCantidadFilas(int cantidadFilas) {
        this.cantidadFilas = cantidadFilas;
    }

    public int getCantidadColumnas() {
        return cantidadColumnas;
    }

    public void setCantidadColumnas(int cantidadColumnas) {
        this.cantidadColumnas = cantidadColumnas;
    }

    public int getCantidadNeutrales() {
        return cantidadNeutrales;
    }

    public void setCantidadNeutrales(int cantidadNeutrales) {
        this.cantidadNeutrales = cantidadNeutrales;
    }
}
