package com.achess.cola;

import com.achess.naves.Naves;
import com.achess.planetas.Planeta;

public class FlotaEnviada extends Cola{
    private Naves nave;
    private Planeta destino;
    public FlotaEnviada(int turnosNecesarios, Planeta destino, Naves nave) {
        super(turnosNecesarios);
        this.nave = nave;
        this.destino = destino;
    }

    @Override
    public void ejecutar() {
        turnosNecesarios--;
        if(turnosNecesarios == 0){
            nave.batalla(destino);

        }
    }

    @Override
    public String toString() {
        return "FlotaEnviada{" +
                "nave=" + nave +
                "\ndestino=" + destino +
                "\nTurnos restantes=" + turnosNecesarios +
                '}';
    }
}
