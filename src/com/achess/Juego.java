package com.achess;

import java.util.Scanner;

public class Juego implements Agregar{
    Mapa mapas[] = new Mapa[0];
    Juego(){

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
    }

    private void editarMapas(){
        int i = 1;
        for(Mapa m: mapas){
            System.out.println(i +".\n"+ m);
            i++;
        }
        System.out.println();
        int index = validar("::Elija el número del mapa que desea editar\n ==>", 1, mapas.length);
        System.out.println("1. Editar planetas de jugadores");
        System.out.println("2. Editar planetas neutrales");
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
