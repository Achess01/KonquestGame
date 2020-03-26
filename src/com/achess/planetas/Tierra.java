package com.achess.planetas;

public class Tierra extends Planeta{
    private final int INDEX_GUERRERO = 0;

    public Tierra(String nombre, int posX, int poxY) {
        super(nombre, posX, poxY);
    }

    @Override
    protected void generarGuerreros() {

    }
}
