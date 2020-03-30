package com.achess;

import com.achess.planetas.*;

public class Mapa implements Agregar{
    private static int nombrePlaneta;
    public static int cantidadMapas = 0;
    private int id;
    private Planeta campoJuego[][];
    private int cantidadFilas;
    private int cantidadColumnas;
    private int cantidadNeutrales;
    private Jugador jugadores[] = new Jugador[2];

    public Mapa(int cantidadFilas, int cantidadColumnas,int cantidadNeutrales, String j1, String j2) {
        setCantidadFilasYColumnas(cantidadFilas, cantidadColumnas);
        setCantidadNeutrales(cantidadNeutrales);
        nombrePlaneta = 65;
        this.id = ++cantidadMapas;
        j1.trim().toUpperCase();
        j2.trim().toUpperCase();
        if(j1.equals(j2)){
            j2 = "02".concat(j2);
        }
        jugadores[0] = new Jugador(this, j1);
        jugadores[1] = new Jugador(this, j2);
        campoJuego = new Planeta[this.cantidadFilas][this.cantidadColumnas];
    }

    public Planeta generarPlanetas(String nombre){
        int probabilidadAparicion = numerosAleatorios(1, 100);
        int x, y;
        do{
            x = numerosAleatorios(0, cantidadColumnas - 1);
            y = numerosAleatorios(0, cantidadFilas -1);
            if(campoJuego[x][y] == null)
                break;
        }while(true);

        if(nombre.trim().isEmpty()){
            char n = (char)nombrePlaneta++;
            nombre = Character.toString(n);
        }
        if(probabilidadAparicion <= 45){
            return new Tierra(nombre, x, y);
        }
        else if(probabilidadAparicion <= 70){
            return new Agua(nombre, x, y);
        }
        else if(probabilidadAparicion <= 85){
            return new Fuego(nombre, x, y);
        }
        else if(probabilidadAparicion <= 95){
            return new Organico(nombre, x, y);
        }
        else {
            return new Radioactivo(nombre, x, y);
        }
    }

    public int getCantidadFilas() {
        return cantidadFilas;
    }

    public void setCantidadFilasYColumnas(int cantidadFilas, int cantidadColumnas) {
        while(true) {
            if (Math.abs(cantidadFilas - cantidadColumnas) > 20 ||
                    cantidadColumnas * cantidadFilas > 100 ||
                    cantidadColumnas * cantidadColumnas < 10) {
                System.out.println("Error:\n" +
                        "La diferencia entre filas y columnas no debe de ser mayor a 20\n"+
                        "La cantidad de casillas totales no debe de ser mayor a 100" +
                        "La cantidad de casilla totales no debe de ser menor a 10");
                cantidadFilas = pedirDato("Cantidad de filas\n ==> ");
                cantidadColumnas = pedirDato("Cantidad de columnas\n ==> ");
            }
            else{
                this.cantidadFilas = Math.abs(cantidadFilas);
                this.cantidadColumnas = Math.abs(cantidadColumnas);
                break;
            }
        }
    }

    public int getCantidadColumnas() {
        return cantidadColumnas;
    }

    public int getCantidadNeutrales() {
        return cantidadNeutrales;
    }

    public void setCantidadNeutrales(int cantidadNeutrales) {
        while(true){
            if(cantidadNeutrales > (this.cantidadFilas*this.cantidadColumnas*0.60) || cantidadNeutrales < 1){
                System.out.println("Error:\n" +
                        "Planetas neutrales no pueden ser mayores al 60% de las casillas" +
                        "\nAl menos debe de haber un planeta neutral");
                cantidadNeutrales = pedirDato("Cantidad neutrales\n ==> ");
            }
            else{
                this.cantidadNeutrales = cantidadNeutrales;
                break;
            }
        }
    }
}
