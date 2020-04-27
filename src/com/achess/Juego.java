package com.achess;

import com.achess.planetas.Planeta;

import java.util.Scanner;

public class Juego implements Agregar, Comandos{
    public static final String COLORES[] = {"\u001B[0m", "\u001B[42m", "\u001B[44m", "\u001B[45m"};
    private Mapa mapas[] = new Mapa[0];
    private Mapa mapaEnJuego;
    int response;
    Juego(){
        do {
            System.out.println("::KONQUEST");
            System.out.println("::Bienvenido");
            System.out.println("1. Jugar");
            System.out.println("2. Editar Mapas");
            System.out.println("3. Crear mapas");
            System.out.println("0. Salir");
            response = pedirDato("==> ");
            switch (response) {
                case 1:
                    juego();
                    break;
                case 2:
                    editarMapas();
                case 3:
                    crearMapas();
                    break;
            }
        }while(response != 0);
    }

    private void juego() {
        if(mapas.length > 0){
            int i = 1;
            for (Mapa m : mapas) {
                System.out.println(i + ".\n" + m);
                i++;
            }
            boolean finalizar = false;
            int response = validar("::Elija el mapa\n ==> ", 1, mapas.length);
            int turnos = 0;
            Scanner sc = new Scanner(System.in);
            sc.nextLine();
            mapaEnJuego = mapas[response - 1];
            Jugador jugadores[] = mapaEnJuego.getJugadores();
            mapaEnJuego.dibujar();
            do{
                System.out.println("::Turno jugador: "+ jugadores[turnos].getNombre());
                String comando = sc.nextLine();
                if(comando.equalsIgnoreCase("Fin")){
                    mapaEnJuego.dibujar();
                    turnos++;
                    if(turnos == 2){
                        mapaEnJuego.turnos();
                        turnos = 0;
                    }
                    jugadores[turnos].turnos();
                }
                else{
                    recibirComando(comando, mapaEnJuego, jugadores[turnos]);
                }

                if(jugadores[0].getCantidadPlanetas() == 0){
                    System.out.println("::El ganador es: " + jugadores[1].getNombre());
                    finalizar = true;
                }
                else if(jugadores[1].getCantidadPlanetas() == 0) {
                    System.out.println("::El ganador es: " + jugadores[0].getNombre());
                    finalizar = true;
                }

            }while (!finalizar);
        }
        else{
            System.out.println("::Cree un mapa para poder jugar");
        }
    }


    private void crearMapas(){
        Scanner sc = new Scanner(System.in);
        int cantidadFilas = pedirDato("::Cantidad de filas\n ==>");
        int cantidadColumnas = pedirDato("::Cantidad columnas\n ==>");
        int cantidadNeutrales = pedirDato("::Cantidad planetas neutrales\n ==>");
        String nombre, nombre2;
        nombre = pedirNombre("::Nombre jugador 1(Max 10 caracteres)\n ==>");
        nombre2 = pedirNombre("::Nombre jugador 2(Max 10 caracteres)\n ==>");
        Mapa m = new Mapa(cantidadFilas, cantidadColumnas, cantidadNeutrales, nombre, nombre2);
        m.planetasJugadores();
        m.planetasNeutrales();
        mapas = agregarElementos(m, mapas,true);
    }

    private void editarMapas(){
        if(mapas.length > 0) {


            int i = 1;
            for (Mapa m : mapas) {
                System.out.println(i + ".\n" + m);
                i++;
            }
            System.out.println();
            int response;
            do {
                int index = validar("::Elija el número del mapa que desea editar\n ==>", 1, mapas.length);
                System.out.println("1. Editar planetas de jugadores");
                System.out.println("2. Editar planetas neutrales");
                System.out.println("0. Salir");
                response = pedirDato("==> ");
                switch (response) {
                    case 1:
                        mapas[index - 1].planetasJugadores();
                        break;
                    case 2:
                        mapas[index - 1].planetasNeutrales();
                        break;

                }
            } while (response != 0);
        }
        else{
            System.out.println("::Aun no hay mapas para editar, cree uno");
        }
    }
    private String pedirNombre(String mensaje){
        Scanner sc = new Scanner(System.in);
        String nombre;
        while (true) {
            System.out.print(mensaje);
            nombre = sc.nextLine();
            nombre = nombre.replaceAll(" ", "");
            if(nombre.length() > 0 && nombre.length() <10){
                break;
            }
            System.out.printf("El nombre no tiene el tamaño adecuado");
        }
        return nombre;
    }


    private Mapa[] agregarElementos(Mapa obj, Mapa[] objetos, boolean validar){
        if(validar) {
            Mapa newObjetos[] = new Mapa[objetos.length + 1];
            newObjetos[0] = obj;
            for (int x = 1; x < newObjetos.length; x++) {
                newObjetos[x] = objetos[x - 1];
            }
            return newObjetos;
        }
        else{
            Mapa newObjetos[] = new Mapa[objetos.length - 1];
            for (int x = 0; x < newObjetos.length; x++) {
                if(obj.equals(objetos[x])){
                    for(int y = x; y < newObjetos.length; y++){
                        objetos[x] = objetos[x+1];
                    }
                }
                newObjetos[x] = objetos[x];
            }
            return newObjetos;
        }
    }
}
