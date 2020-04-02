package com.achess;

import com.achess.constructores.Constructores;
import com.achess.guerreros.Guerreros;
import com.achess.naves.Naves;

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
    private boolean esUnPlaneta(String planeta) {
        int x;
        int y;
        if (planeta.length() == 2 && planeta.charAt(0) >= 'A' && planeta.charAt(0) <= 'Z'
                && planeta.charAt(1) >= '1' && planeta.charAt(1) <= '9') {
           // x = (int) planeta.charAt(0) - 65; //ASCII
            //y = (int) planeta.charAt(0) - 48;//ASCII
            return true;
        } else {
            return false;
        }
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

}




