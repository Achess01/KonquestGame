package com.achess.planetas;

public class Organico extends Planeta {
    private final int INDEX_GUERRERO = 3;
    public Organico(String nombre, int posX, int poxY) {
        super(nombre, posX, poxY);
    }

    @Override
    protected void generarGuerreros() {

    }
}
