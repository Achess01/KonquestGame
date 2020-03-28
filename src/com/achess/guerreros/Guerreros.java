package com.achess.guerreros;

public abstract class Guerreros {
    public static final float FACTOR[] = {1.5f, 1.6f, 1.75f, 1.85f, 1.95f};
    public static final int ESPACIO[] = {1, 1, 2, 3, 4};
    protected static int cantidadGuerreros = 0;
    protected int id;
    protected float factorDeMuerte;
    protected int espacioEnNave;
    protected float valorAtaque;
    protected float porcentajePlaneta;

    public Guerreros(float porcentajePlaneta, int indexGuerrero){
        this.id = ++cantidadGuerreros;
        this.porcentajePlaneta = porcentajePlaneta;
        this.factorDeMuerte = FACTOR[indexGuerrero];
        this.valorAtaque = factorDeMuerte*porcentajePlaneta;
        this.espacioEnNave = ESPACIO[indexGuerrero];
    }

    public int getEspacioEnNave() {
        return espacioEnNave;
    }

    public float getValorAtaque() {
        return valorAtaque;
    }
}
