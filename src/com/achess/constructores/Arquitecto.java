package com.achess.constructores;

import com.achess.naves.MillenialFalcon;
import com.achess.planetas.Planeta;

public class Arquitecto extends Constructores {
    private final int INDEX_NAVE = 2;

    public Arquitecto(int indexConstructor, Planeta planeta) {
        super(indexConstructor, planeta);

    }

    @Override
    public void fabricarNave() {
        planeta.agregarNaves(new MillenialFalcon(INDEX_NAVE, planeta), INDEX_NAVE, true);
    }
}
