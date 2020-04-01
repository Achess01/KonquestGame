package com.achess.planetas;

import com.achess.guerreros.Mole;

public class Tierra extends Planeta{
    private final int INDEX_GUERRERO = 0;

    public Tierra(String nombre, int posX, int poxY) {
        super(nombre, posX, poxY);
        maxG = 25;
        minG = 15;
        maxD = 100;
        minD = 50;
        tipo = "Tierra";
    }
    @Override
    public void guerrerosIniciales(int cantidad){
        for (int i = 0; i < cantidad ; i++) {
            agregarGuerreros(new Mole(porcentajeMuerte, INDEX_GUERRERO), INDEX_GUERRERO, true);
        }
    }

    @Override
    protected void generarGuerreros(int cantidadGuerrerosGenerados) {
        for(int x = 0; x < cantidadGuerrerosGenerados; x++){
            agregarGuerreros(new Mole(porcentajeMuerte, INDEX_GUERRERO),INDEX_GUERRERO,true);
        }
    }

}
