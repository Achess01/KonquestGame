package com.achess.constructores;

import com.achess.naves.XWing;
import com.achess.planetas.Planeta;

public class MaestroDeObra extends Constructores {
    private final int INDEX_NAVE = 1;

    public MaestroDeObra(int indexConstructor, Planeta planeta) {
        super(indexConstructor, planeta);
    }

    @Override
    public void fabricarNave() {
        planeta.agregarNaves(new XWing(INDEX_NAVE, planeta), INDEX_NAVE, true);
    }
}
