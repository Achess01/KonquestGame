package com.achess;

import com.achess.planetas.*;

import java.util.Scanner;

public class Mapa implements Agregar{
    private static int cantidadPlanetas;
    private static int nombrePlaneta;
    public static int cantidadMapas = 0;
    private int id;
    private Planeta campoJuego[][] =new Planeta[0][0];
    private int cantidadFilas;
    private int cantidadColumnas;
    private int cantidadNeutrales;
    public Jugador neutro = new Jugador();
    private Jugador jugadores[] = new Jugador[2];

    public Mapa(int cantidadFilas, int cantidadColumnas,int cantidadNeutrales, String j1, String j2) {
        setCantidadFilasYColumnas(cantidadFilas, cantidadColumnas);
        setCantidadNeutrales(cantidadNeutrales);
        nombrePlaneta = 65;
        cantidadPlanetas = 0;
        this.id = ++cantidadMapas;
        j1.trim().toUpperCase();
        j2.trim().toUpperCase();
        if(j1.equals(j2)){
            j2 = "02".concat(j2);
        }
        jugadores[0] = new Jugador(this, j1);
        jugadores[0].setColor(Juego.COLORES[1]);
        jugadores[1] = new Jugador(this, j2);
        jugadores[1].setColor(Juego.COLORES[2]);
        neutro.setColor(Juego.COLORES[3]);
        campoJuego = new Planeta[this.cantidadFilas][this.cantidadColumnas];
    }

    public void turnos(){
        for (int i = 0; i < cantidadFilas; i++) {
            for (int j = 0; j <cantidadColumnas ; j++) {
                Planeta p = campoJuego[i][j];
                if(p != null){
                    p.turnos();
                }
            }

        }
    }
    public void planetasJugadores(){
        Scanner sc = new Scanner(System.in);
        System.out.println("::Valores para los planetas de los jugadores");
        System.out.println("::Jugador: " + jugadores[0].getNombre());
        planetaCustom(jugadores[0]);
        System.out.println("::Jugador: " + jugadores[1].getNombre());
        planetaCustom(jugadores[1]);

    }

    public void planetasNeutrales(){
        int cantidad = validar("::Cantidad de planetas neutros que desea editar\n ==>", 0, cantidadNeutrales);
        for (int i = 0; i < cantidad ; i++) {
            planetaCustom(neutro);
        }
        int restantes = cantidadNeutrales - cantidad;
        for (int i = 0; i < restantes; i++) {
            planetaDefecto();
        }
    }
    public void planetaCustom(Jugador propietario){
        Scanner sc = new Scanner(System.in);
        System.out.print("::Nombre(10 caracteres max)\n==> ");
        String nombrePlaneta = sc.nextLine();
        nombrePlaneta = nombrePlaneta.replaceAll(" ", "");
        if(nombrePlaneta.length() > 10) {
            nombrePlaneta = nombrePlaneta.substring(0, 10);
        }
        Planeta planetaGenerado = generarPlanetas(nombrePlaneta);
        planetaGenerado.setPropietario(propietario);
        int cantidadDinero = validar("::Dinero inicial(Entre 100 y 1000)\n ==> ", 100, 1000);
        planetaGenerado.setCantidadDineroInicial(cantidadDinero);
        int cantidadConstructores = validar("::Cantidad constructores(Entre 1 y 10 )\n ==>", 1, 10);
        planetaGenerado.constructoresIniciales(cantidadConstructores);
        int cantidadNaves = validar("::Cantidad naves(Entre 1 y 5)\n ==>", 1, 5);
        planetaGenerado.navesIniciales(cantidadNaves);
        int cantidadGuerreros = validar("::Cantidad guerreros(Entre 10 y 15) ==>", 10, 15);
        planetaGenerado.guerrerosIniciales(cantidadGuerreros);
        propietario.agregarPlanetas(planetaGenerado, true);
        int x = planetaGenerado.getPosX();
        int y = planetaGenerado.getPosY();
        campoJuego[x][y] = planetaGenerado;
    }

    public void planetaDefecto(){
        Planeta planetaGenerado = generarPlanetas("");
        planetaGenerado.setPropietario(neutro);
        int cantidadDinero = numerosAleatorios(100, 500);
        planetaGenerado.setCantidadDineroInicial(cantidadDinero);
        planetaGenerado.constructoresIniciales(1);
        int cantidadNaves = numerosAleatorios(1, 3);
        planetaGenerado.navesIniciales(cantidadNaves);
        planetaGenerado.generarGuerrerosTurnos();
        int x = planetaGenerado.getPosX();
        int y = planetaGenerado.getPosY();
        neutro.agregarPlanetas(planetaGenerado, true);
        campoJuego[x][y] = planetaGenerado;
    }


    public Planeta generarPlanetas(String nombre){
        int probabilidadAparicion = numerosAleatorios(1, 100);
        int x, y;
        do{
            y = numerosAleatorios(0, this.cantidadColumnas - 1);
            x = numerosAleatorios(0, this.cantidadFilas -1);
            if(campoJuego[x][y] == null)
                break;
        }while(true);

        if(nombre.trim().isEmpty()){
            char n = (char)nombrePlaneta++;
            if(nombrePlaneta > 90){
                nombrePlaneta = 65;
            }
            String no = Integer.toString(cantidadPlanetas++);
            nombre = no.concat(Character.toString(n));
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

    /**
     * Valida los datos de filas y columnas
     * @param cantidadFilas
     * @param cantidadColumnas
     */
    public void setCantidadFilasYColumnas(int cantidadFilas, int cantidadColumnas) {
        while(true) {
            if (Math.abs(cantidadFilas - cantidadColumnas) > 20 ||
                    cantidadColumnas * cantidadFilas > 100 ||
                    cantidadColumnas * cantidadColumnas < 10) {
                System.out.println("Error:\n" +
                        "La diferencia entre filas y columnas no debe de ser mayor a 20\n"+
                        "La cantidad de casillas totales no debe de ser mayor a 100\n" +
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

    /**
     * Valida la cantidad de planetas neutrales
     * @param cantidadNeutrales
     */
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

    public Planeta[][] getCampoJuego() {
        return campoJuego;
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }

    public void dibujar(){
        int letras = 65;
        System.out.print("   ");
        for (int i = 1; i <=cantidadColumnas ; i++) {
            char letra = (char)letras;
            String l = Character.toString(letra);
            System.out.print("   "+l+"      |");
            letras++;

        }
        System.out.println("");
        int k = 0;
        for (int i = 0; i <cantidadFilas ; i++) {
            k = 0;
            while (k < 5){
                for (int j = 0; j < cantidadColumnas; j++) {
                    if(j ==0){
                        if(k == 2){
                            System.out.print((i+1)+" |");
                        }
                        else {
                            System.out.print("  |");
                        }
                    }
                    Planeta planeta = campoJuego[i][j];
                    if (planeta != null) {
                        switch (k) {
                            case 0:
                                planeta.dibujar1();
                                break;
                            case 1:
                                planeta.dibujar2();
                                break;
                            case 2:
                                planeta.dibujar3();
                                break;
                            case 3:
                                planeta.dibujar4();
                                break;
                            case 4:
                                planeta.dibujar5();
                                break;
                        }
                    } else {
                        System.out.print("##########|");
                    }
                }
                System.out.println("");
                k++;
            }
        }
    }
    @Override
    public String toString() {
        return "Mapa{" +
                "cantidadFilas=" + cantidadFilas +
                "\ncantidadColumnas=" + cantidadColumnas +
                "\ncantidadNeutrales=" + cantidadNeutrales +
                '}';
    }
}
