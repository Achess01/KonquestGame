package com.achess.naves;
import com.achess.Jugador;
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
    protected Planeta planetaOrigen;


    Naves(int indexNave, Planeta planetaOrigen){
        this.id = ++cantidadNaves;
        this.capacidad = CAPACIDAD[indexNave];
        this.costo = PRECIO[indexNave];
        this.velocidad = VELOCIDAD[indexNave];
        this.nombre = NOMBRE[indexNave];
        this.planetaOrigen = planetaOrigen;
    }

    public Planeta getPlanetaOrigen() {
        return planetaOrigen;
    }

    public void setPlanetaOrigen(Planeta planetaOrigen) {
        this.planetaOrigen = planetaOrigen;
    }

    /**
     * Verifica que la nave tenga la capacidad para cargar a los guerreros
     * @param cantidadGuerreros
     * @param guerreros
     * @return
     */
    public boolean cargarGuerreros(int cantidadGuerreros, Guerreros guerreros[]){
        int asientosNecesarios = cantidadGuerreros*guerreros[0].getEspacioEnNave();
        if(asientosNecesarios <= this.capacidad && cantidadGuerreros > 0){
            this.guerreros = guerreros;
            return true;
        }
        else{
            System.out.println("La nave solo tiene " + this.capacidad + " asientos");
            return false;
        }
    }

    public Guerreros[] batalla(Planeta planetaDestino){
        if(planetaDestino.getPropietario().equals(planetaOrigen.getPropietario())){

        }
        return guerreros;
    }

}
