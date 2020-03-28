package com.achess.planetas;

import com.achess.guerreros.Groot;

public class Organico extends Planeta {
    private final int INDEX_GUERRERO = 3;
    public Organico(String nombre, int posX, int poxY) {
        super(nombre, posX, poxY);
        maxG = 15;
        minG = 5;
        maxD = 160;
        minD = 80;
    }

    @Override
    protected void generarGuerreros(int cantidadGuerrerosGenerados) {
        for(int x = 0; x < cantidadGuerrerosGenerados; x++){
            agregarGuerreros(new Groot(porcentajeMuerte, INDEX_GUERRERO),INDEX_GUERRERO,true);
        }
    }
}
