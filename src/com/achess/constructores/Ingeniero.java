package com.achess.constructores;

import com.achess.naves.StarDestroyer;
import com.achess.planetas.Planeta;

public class Ingeniero extends Constructores {
    private final int INDEX_NAVE = 3;

    public Ingeniero(int indexConstructor, Planeta planeta) {
        super(indexConstructor, planeta);
    }

    @Override
    public void fabricarNave() {
        planeta.agregarNaves(new StarDestroyer(INDEX_NAVE, planeta), INDEX_NAVE, true);
    }
}
