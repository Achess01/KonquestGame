package com.achess.cola;

import com.achess.planetas.Planeta;

public abstract class Cola {
    protected static int cantidadColas = 0;
    protected int id;
    protected int turnosNecesarios;

    public Cola(int turnosNecesarios){
        this.turnosNecesarios = turnosNecesarios;
        this.id = ++cantidadColas;
    }

    public abstract void ejecutar();

    public int getTurnosNecesarios() {
        return turnosNecesarios;
    }
}
