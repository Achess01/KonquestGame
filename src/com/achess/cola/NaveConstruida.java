package com.achess.cola;

import com.achess.constructores.Constructores;
import com.achess.planetas.Planeta;

public class NaveConstruida extends Cola{
    private Constructores constructor;

    public NaveConstruida(int turnosNecesarios, Constructores constructor) {
        super(turnosNecesarios);
        this.constructor = constructor;
    }

    @Override
    public void ejecutar(int turnoActual) {
        int diferencia = turnosNecesarios - turnoActual;
        if(diferencia == 0){
            constructor.fabricarNave();
        }
    }
}
