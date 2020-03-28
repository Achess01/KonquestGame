package com.achess.constructores;
import com.achess.planetas.Planeta;

public abstract class Constructores {
    public static final int TIEMPO[] = {3, 2, 1, 1};
    public static final int PRECIO_C[] = {50, 100, 250, 300};
    public static final int PRECIO_V[] = {40, 70, 175, 200};
    protected static int cantidadConstructores = 0;
    protected int id;
    protected int tiempoConstruccion;
    protected int precioCompra;
    protected int precioVenta;
    protected Planeta planeta;

    public Constructores(int indexConstructor, Planeta planeta) {
        this.id = ++cantidadConstructores;
        this.tiempoConstruccion = TIEMPO[indexConstructor];
        this.precioCompra = PRECIO_C[indexConstructor];
        this.precioVenta = PRECIO_V[indexConstructor];
        this.planeta = planeta;
    }

    public void fabricarNave(Planeta planeta){

    }

    public Planeta getPlaneta() {
        return planeta;
    }

    public void setPlaneta(Planeta planeta) {
        this.planeta = planeta;
    }
}
