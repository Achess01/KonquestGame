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

    public Mapa(int cantidadFilas, int cantidadColumnas, String j1, String j2) {
        this.id = ++cantidadMapas;
        this.cantidadFilas = cantidadFilas;
        this.cantidadColumnas = cantidadColumnas;
        j1.trim().toUpperCase();
        j2.trim().toUpperCase();
        if(j1.equals(j2)){
            j2 = j2.concat("02");
        }
        jugadores[0] = new Jugador(this, j1);
        jugadores[0] = new Jugador(this, j1);
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
