package com.achess;

import com.achess.cola.*;
import com.achess.naves.Naves;
import com.achess.planetas.*;

public class Jugador implements Agregar{
    private int mapa;
    private String nombre;
    private Planeta planetas[];
    private Cola cola[];

    public Jugador(int mapa, String nombre) {
        this.mapa = mapa;
        this.nombre = nombre;
    }

    /**
     * Calcula la distancia entre planetas utilizando el teorema de pitágoras
     * @param planetaOrigen
     * @param planetaDestino
     * @return
     */
    public float medirDistancias(Planeta planetaOrigen, Planeta planetaDestino){
        float x = Math.abs(planetaDestino.getPosX() - planetaOrigen.getPosX());
        float y = Math.abs(planetaDestino.getPosY() - planetaOrigen.getPosY());
        float c = (float) (Math.pow(x, 2) + Math.pow(y, 2));
        c = (float) Math.sqrt(c);
        return c;
    }

    public void verPlanetas(Planeta planeta){
        System.out.println(planeta);
    }

    public void verFlotas(Planeta planeta){
        Naves naves[][] = planeta.getNavesOcupadas();
        for(Naves tipos[]: naves){
            for(Naves nave: tipos){
                System.out.println(nave);
            }
        }
    }
    public void agregarPlanetas(Planeta planeta, boolean validar) {
        planetas = agregarElementos(planeta, planetas, validar);
    }

    public void agregarCola(Cola cola, boolean validar) {
        this.cola = agregarElementos(cola, this.cola, validar);
    }

    public int getMapa() {
        return mapa;
    }

    public String getNombre() {
        return nombre;
    }
    //Mandar a la flota es trabajo del planeta
}
