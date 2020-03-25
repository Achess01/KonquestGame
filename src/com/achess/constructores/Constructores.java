package com.achess.constructores;
import com.achess.planetas.Planeta;

public abstract class Constructores {
    public static final int TIEMPO[] = {3, 2, 1, 1};
    public static final int PRECIO_C[] = {50, 100, 250, 300};
    public static final int PRECIO_V[] = {40, 70, 175, 200};
    protected int tiempoConstruccion;
    protected int precioCompra;
    protected int precioVenta;

    public Constructores(int indexConstructor) {
        this.tiempoConstruccion = TIEMPO[indexConstructor];
        this.precioCompra = PRECIO_C[indexConstructor];
        this.precioVenta = PRECIO_V[indexConstructor];
    }

    public void fabricarNave(Planeta planeta){

    }
}
