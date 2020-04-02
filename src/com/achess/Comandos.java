package com.achess;

import com.achess.constructores.Constructores;
import com.achess.guerreros.Guerreros;
import com.achess.naves.Naves;
import com.achess.planetas.Planeta;

import java.util.Scanner;

public interface Comandos {
    /**
     * Recibe el comando y lo interpreta.
     *
     * @param comando
     * @param mapa
     * @param turno
     */
    default void recibirComando(String comando, Mapa mapa, Jugador turno) {
        comando = comando.trim().toUpperCase();
        if (comando.length() > 1) {
            String accion[] = comando.split(",");
            for (int i = 0; i < accion.length; i++) {
                accion[i] = accion[i].trim();
            }
            int palabras = accion.length;
            switch (palabras){

            }
        } else {
            System.out.println("::Error");
        }
    }

    /**
     * Verifica si el comando se refiere a un planeta.
     *
     * @param planeta
     * @return
     */
    private boolean esUnPlaneta(String planeta, Mapa mapa) {
        if (planeta.length() < 4 && planeta.charAt(0) >= 'A' && planeta.charAt(0) <= 'Z'
                && esUnNumero(planeta.substring(1))) {
            int c[] = obtenerCoordenadasPlanetas(planeta);
            if(existePlaneta(mapa, c)){
                 return true;
            }
        }
        return false;
    }

    private boolean esUnaNave(String nave){
        for (String n : Naves.NOMBRE){
            if(n.equalsIgnoreCase(nave)){
                return true;
            }
        }
        return false;
    }

    private boolean esUnGuerrero(String guerrero){
        for (String g : Guerreros.NOMBRE){
            if(g.equalsIgnoreCase(guerrero)){
                return true;
            }
        }
        return false;
    }

    private boolean esUnConstructor(String constructor){
        for (String c : Constructores.NOMBRE){
            if(c.equalsIgnoreCase(constructor)){
                return true;
            }
        }
        return false;
    }

    private boolean esUnNumero(String numero){
        try {
            int n = Integer.parseInt(numero);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }
    private void comandosDosPalabras(String comando[], Jugador turno, Mapa mapa){
        Planeta planeta1, planeta2;
        int p1[] = new int[2];
        int p2[];
        Constructores constructor;

        if(esUnPlaneta(comando[0]) && esUnPlaneta(comando[1])) {
            p2 = new int[2];
            p1 = obtenerCoordenadasPlanetas(comando[0]);
            p2 = obtenerCoordenadasPlanetas(comando[1]);
            Planeta campo[][] = mapa.getCampoJuego();

            if (existePlaneta(mapa, p1) && existePlaneta(mapa, p2)) {

                if (campo[p1[0]][p1[1]].getPropietario().equals(turno)){
                    float d = campo[p1[0]][p1[1]].getPropietario().medirDistancias(campo[p1[0]][p1[1]], campo[p2[0]][p2[1]]);
                    System.out.println("La distancia en los planetas es: " + d + " años luz");
                }
                else {
                    System.out.println("::Error");
                    System.out.println("::Verifique los planetas");
                }
            }
            else {
                System.out.println("::Error");
                System.out.println("::Verifique las coordenadas");
            }
        }
        else if(esUnPlaneta(comando[0]) && esUnConstructor(comando[1])){

        }
    }

    private  boolean existePlaneta(Mapa mapa, int p1[]){
        Planeta campo[][] = mapa.getCampoJuego();
        if(p1[0] < mapa.getCantidadFilas() && p1[1] < mapa.getCantidadColumnas() &&
                campo[p1[0]][p1[1]] != null){
            return true;
        }
        return false;
    }
    private int[] obtenerCoordenadasPlanetas(String coordenadas){
        int c[] = new int[2];
        c[0] = Integer.parseInt(coordenadas.substring(1)) - 1;
        c[1] = (int)coordenadas.charAt(0) - 65;
        return c;
    }
}




