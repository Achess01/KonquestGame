package com.achess.planetas;

import com.achess.guerreros.Magma;

public class Fuego extends Planeta {
    private final int INDEX_GUERRERO = 2;
    public Fuego(String nombre, int posX, int poxY) {
        super(nombre, posX, poxY);
        maxG = 20;
        minG = 10;
        maxD = 140;
        minD = 70;
    }

    @Override
    protected void generarGuerreros(int cantidadGuerrerosGenerados) {
        for(int x = 0; x < cantidadGuerrerosGenerados; x++){
            agregarGuerreros(new Magma(porcentajeMuerte, INDEX_GUERRERO),INDEX_GUERRERO,true);
        }
    }
}
