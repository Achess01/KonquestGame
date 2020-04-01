package com.achess;

import com.achess.constructores.*;
import com.achess.planetas.Planeta;

import java.util.Scanner;

public class Tienda{
    public static void menu(Planeta planeta){
        int response;
        do{
            System.out.println("::Tienda");
            System.out.println("1. Comprar");
            System.out.println("2. Vender");
            System.out.println("0 .Salir");
            response = pedirDato("==> ");
            switch (response){
                case 1:
                    comprar(planeta);
                    break;
                case 2:
                    break;
            }
        }while(response != 0);
    }

    public static void comprar(Planeta planeta){
        int response;
        int cantidad;
        do {
            System.out.println("::Comprar");
            System.out.println("::Constructores");
            System.out.println("1. Obrero - precio: " + Constructores.PRECIO_C[0] + " galactus");
            System.out.println("2. Maestro de obra - precio: " + Constructores.PRECIO_C[1] + " galactus");
            System.out.println("3. Arquitecto - precio: " + Constructores.PRECIO_C[2] + " galactus");
            System.out.println("4. Ingeniero - precio: " + Constructores.PRECIO_C[3] + " galactus");
            System.out.println("0. Salir");
            response = pedirDato("==> ");
            int index = response - 1;
            if(response >0 && response < 5) {
                System.out.println("::Cantidad");
                cantidad = pedirDato("==> ");
                cantidad = Math.abs(cantidad);
                int precioT = Constructores.PRECIO_C[response - 1] * cantidad;
                int diferencia = precioT - planeta.getPropietario().getDinero();
                if (diferencia > 0) {
                    System.out.println("Dinero insuficiente");
                    System.out.println("Le hacen falta " + diferencia + " galactus");
                    response = 0;
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
                }
            }
        }while(response != 0);
    }
    private static int pedirDato(String mensaje){
        int numero;
        while (true) {
            try {
                System.out.print(mensaje);
                Scanner sc = new Scanner(System.in);
                numero = Integer.parseInt(sc.nextLine());
                break;
            } catch (Exception ex) {
                System.out.println("Valor no válido");
            }
        }
        return numero;
    }

    private static int validar(String mensaje, int min, int max){
        int dato;
        while(true){
            dato = pedirDato(mensaje);
            if(dato > min && dato < max){
                break;
            }
        }
        return dato;
    }
}
