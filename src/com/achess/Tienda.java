package com.achess;

import com.achess.constructores.*;
import com.achess.naves.Naves;
import com.achess.planetas.Planeta;

import java.util.Scanner;

public class Tienda{

    public static void info(){
        System.out.println("::Comprar");
        System.out.println("::Constructores");
        System.out.println("1. Obrero - precio: " + Constructores.PRECIO_C[0] + " galactus");
        System.out.println("2. Maestro de obra - precio: " + Constructores.PRECIO_C[1] + " galactus");
        System.out.println("3. Arquitecto - precio: " + Constructores.PRECIO_C[2] + " galactus");
        System.out.println("4. Ingeniero - precio: " + Constructores.PRECIO_C[3] + " galactus");
    }

    public static void venderNave(Planeta planeta, String tipo){
        int indexNave = -1;
        switch (tipo){
            case "NABOO N-1": indexNave = 0; break;
            case "X-WING": indexNave = 1; break;
            case "MILLENIAL FALCON": indexNave = 2; break;
            case "STAR DESTROYER": indexNave = 3; break;
            default: indexNave = -1;
        }
        if(indexNave > 0) {
            Naves naves[][] = planeta.getNavesDisponibles();
            if(naves[indexNave].length > 0){
                planeta.agregarNaves(naves[indexNave][0], indexNave, false);
                planeta.getPropietario().setDinero(planeta.getPropietario().getDinero() + Naves.PRECIO[indexNave]);
            }
            else {
                System.out.println("::Nave no existente en su inventario");
            }
        }
        else{
            System.out.println("Tipo de nave no existente");
        }

    }

    public static void venderConstrutor(Planeta planeta, String tipo){
        int response = -1;
        switch (tipo){
            case "OBRERO": response = 1; break;
            case "MAESTRO DE OBRA": response = 2; break;
            case "ARQUITECTO": response = 3; break;
            case "INGENIERO": response = 4; break;
        }
        if(response > 0) {
            Constructores constructores[][] = planeta.getConstructores();
            if(constructores[response].length > 0){
                planeta.agregarConstructores(constructores[response][0], response, false);
                planeta.getPropietario().setDinero(planeta.getPropietario().getDinero() + Constructores.PRECIO_V[response]);
            }
            else {
                System.out.println("::Constructor no existente en su inventario");
            }
        }
        else{
            System.out.println("Tipo de constructor no existente");
        }

    }

    public static void venderConstructor(Planeta planeta){

    }

    public static void comprar(Planeta planeta, int cantidad, String tipo){
        int response = 0;
        switch (tipo){
            case "OBRERO": response = 1; break;
            case "MAESTRO DE OBRA": response = 2; break;
            case "ARQUITECTO": response = 3; break;
            case "INGENIERO": response = 4; break;
        }
            int index = response - 1;
            if(response >0 && response < 5) {
                int precioT = Constructores.PRECIO_C[response - 1] * cantidad;
                int diferencia = planeta.getPropietario().getDinero() - precioT;
                if (diferencia < 0) {
                    System.out.println("Dinero insuficiente");
                    System.out.println("Le hacen falta " + diferencia + " galactus");
                    response = 0;
                }
                else{
                    planeta.getPropietario().setDinero(diferencia);
                }

                switch (response) {
                    case 1:
                        for (int i = 0; i < cantidad; i++) {
                            planeta.agregarConstructores(new Obrero(index, planeta), index, true);
                        }
                        System.out.println("Compra exitosa");
                        break;
                    case 2:
                        for (int i = 0; i < cantidad; i++) {
                            planeta.agregarConstructores(new MaestroDeObra(index, planeta), index, true);
                        }
                        System.out.println("Compra exitosa");
                        break;
                    case 3:
                        for (int i = 0; i < cantidad; i++) {
                            planeta.agregarConstructores(new Arquitecto(index, planeta), index, true);
                        }
                        System.out.println("Compra exitosa");
                        break;
                    case 4:
                        for (int i = 0; i < cantidad; i++) {
                            planeta.agregarConstructores(new Ingeniero(index, planeta), index, true);
                        }
                        System.out.println("Compra exitosa");
                        break;
                    default:
                        System.out.println("::Compra NO efectuada");
                }
                System.out.println("::Dinero actual: " + planeta.getPropietario().getDinero());
            }
            else{
                System.out.println("::Tipo de nave incorrecta");
            }

    }
}
