package com.achess.naves;
import com.achess.guerreros.Guerreros;
import com.achess.planetas.Planeta;

public abstract class Naves {
    public static final int CAPACIDAD[] ={25, 42, 58, 80};
    public static final int PRECIO[] = {40, 50, 70, 100};
    public static final float VELOCIDAD[] = {1f, 1.25f, 1.50f, 1.75f};
    public static final String NOMBRE[] = {"Naboo N-1", "X-Wing", "Millenial Falcon", "Star Destroyer"};
    protected static int cantidadNaves = 0;
    protected String nombre;
    protected int id;
    protected Guerreros guerreros[];
    protected int capacidad;
    protected int costo;
    protected float velocidad;

    Naves(int indexNave){
        this.id = ++cantidadNaves;
        this.capacidad = CAPACIDAD[indexNave];
        this.costo = PRECIO[indexNave];
        this.velocidad = VELOCIDAD[indexNave];
        this.nombre = NOMBRE[indexNave];
    }
    public void cargarGuerreros(int cantidadGuerreros, Guerreros guerreros[]){
        int asientosNecesarios = cantidadGuerreros*guerreros[0].getEspacioEnNave();
        if(asientosNecesarios <= this.capacidad && cantidadGuerreros > 0){
            this.guerreros = guerreros;
        }
        else{
            System.out.println("La nave solo tiene " + this.capacidad + " asientos");
        }
    }

    public Guerreros[] batalla(){
        return guerreros;
    }

}
