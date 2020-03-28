package com.achess.planetas;

import com.achess.guerreros.Nemo;

public class Agua extends Planeta {
    private final int INDEX_GUERRERO = 1;

    public Agua(String nombre, int posX, int poxY) {
        super(nombre, posX, poxY);
        maxG = 23;
        minG = 12;
        maxD = 120;
        minD = 60;
    }

    @Override
    protected void generarGuerreros(int cantidadGuerrerosGenerados) {
        for(int x = 0; x < cantidadGuerrerosGenerados; x++){
            agregarGuerreros(new Nemo(porcentajeMuerte, INDEX_GUERRERO),INDEX_GUERRERO,true);
        }
    }
}
