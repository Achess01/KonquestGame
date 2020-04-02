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
    public void ejecutar() {
        turnosNecesarios--;
        if(turnosNecesarios == 0){
            constructor.fabricarNave();
            constructor.getPlaneta().agregarConstructoresOcupados(constructor, constructor.getIndexConstructor(), false);
            constructor.getPlaneta().agregarConstructores(constructor, constructor.getIndexConstructor(), true);
        }
    }
}
