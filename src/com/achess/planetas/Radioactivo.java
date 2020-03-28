package com.achess.planetas;

import com.achess.guerreros.FisionGuy;

public class Radioactivo extends Planeta {
    private final int INDEX_GUERRERO = 4;
    public Radioactivo(String nombre, int posX, int poxY) {
        super(nombre, posX, poxY);
        maxG = 9;
        minG = 3;
        maxD = 180;
        minD = 90;
    }

    @Override
    protected void generarGuerreros(int cantidadGuerrerosGenerados) {
        for(int x = 0; x < cantidadGuerrerosGenerados; x++){
            agregarGuerreros(new FisionGuy(porcentajeMuerte, INDEX_GUERRERO),INDEX_GUERRERO,true);
        }
    }
}
