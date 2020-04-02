package com.achess;

import com.achess.cola.*;
import com.achess.naves.Naves;
import com.achess.planetas.*;

public class Jugador implements Agregar{
    private Mapa mapa;
    private int dinero = 0;
    private String nombre;
    private Planeta planetas[];
    private Cola cola[];
    private String color;

    public Jugador(Mapa mapa, String nombre) {
        this.mapa = mapa;
        this.nombre = nombre;
    }

    public Jugador(){
        this.nombre = "NEUTRO";
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

    public Mapa getMapa() {
        return mapa;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean existePlaneta(Planeta buscado){
        for(Planeta p :planetas){
            if(p.equals(buscado))
                return true;
        }
        return false;
    }
}
