package com.achess.constructores;

import com.achess.naves.NabooN1;
import com.achess.planetas.Planeta;

public class Obrero extends Constructores{
    private final int INDEX_NAVE = 0;

    public Obrero(int indexConstructor, Planeta planeta) {
        super(indexConstructor, planeta);
    }

    @Override
    public void fabricarNave() {
        planeta.agregarNaves(new NabooN1(INDEX_NAVE,planeta),INDEX_NAVE, true);
    }
}
