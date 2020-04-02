package com.achess;

import com.achess.cola.Cola;
import com.achess.cola.FlotaEnviada;
import com.achess.cola.NaveConstruida;
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
                case 1: comandosUnaPalabra(accion, turno, mapa); break;
                case 2: comandosDosPalabras(accion, turno, mapa); break;
                case 3: comandosTresPalabras(accion, turno, mapa); break;
                case 4: comandosCuatroPalabras(accion, turno, mapa); break;
                case 5: comandosCincoPalabras(accion, turno, mapa); break;
            }
        } else {
            System.out.println("::Error");
        }
    }

    private void comandosUnaPalabra(String comando[], Jugador turno, Mapa mapa){
        Planeta planeta = esPropietario(turno, comando[0], mapa);
            if(planeta != null){
                System.out.println(planeta);
            }
            else if(comando[0].equalsIgnoreCase("Flota")){
               Cola cola[] = turno.getCola();
               for(Cola c : cola){
                   if(c instanceof FlotaEnviada){
                       System.out.println(c);
                   }
               }
            }
    }
    private void comandosDosPalabras(String comando[], Jugador turno, Mapa mapa){
        Planeta planeta1, planeta2;
        int p1[] = new int[2];
        int p2[];

        if(esUnPlaneta(comando[0], mapa) != null && esUnPlaneta(comando[1], mapa) != null) {
            p2 = new int[2];
            p1 = obtenerCoordenadasPlanetas(comando[0]);
            p2 = obtenerCoordenadasPlanetas(comando[1]);
            Planeta campo[][] = mapa.getCampoJuego();
            if (esPropietario(turno, comando[0], mapa) != null){
                    float d = campo[p1[0]][p1[1]].getPropietario().medirDistancias(campo[p1[0]][p1[1]], campo[p2[0]][p2[1]]);
                    System.out.println("La distancia en los planetas es: " + d + " años luz");
            }

        }
        else if(esUnConstructor(comando[1]) >= 0 && esPropietario(turno,comando[0],mapa) != null){
            int c[] = obtenerCoordenadasPlanetas(comando[0]);
            int indexConstructor = esUnConstructor(comando[1]);
            Planeta planeta = esPropietario(turno,comando[0],mapa);
            if(planeta.getConstructores(indexConstructor) != null
                    && turno.getDinero() > Naves.PRECIO[indexConstructor]){
                Constructores ctor =  planeta.construirNave(indexConstructor);
               turno.setDinero(turno.getDinero() - Naves.PRECIO[indexConstructor]);
                Cola cola = new NaveConstruida(Constructores.TIEMPO[indexConstructor], ctor);
                turno.agregarCola(cola, true);
                System.out.println("::Tarea en proceso");
            }
            else {
                System.out.println("::Error");
                System.out.println("No cuenta con lo necesario para construir la nave que pide");

            }
        }
    }
    private void comandosTresPalabras(String comando[], Jugador turno, Mapa mapa){
        Planeta planeta = esPropietario(turno, comando[0], mapa);
        int indexNave = esUnaNave(comando[2]);
        int indexConstructor = esUnConstructor(comando[2]);
            if(planeta != null && comando[1].equalsIgnoreCase("Vender") && indexNave >= 0){
                if(planeta.getNavesDisponibles(indexNave) != null){
                    Naves n = planeta.getNavesDisponibles(indexNave);
                    planeta.agregarNaves(n, indexNave, false);
                    turno.setDinero(turno.getDinero() + Naves.PRECIO[indexNave]);
                    System.out.println("::Nave vendida. Dinero actual: " + turno.getDinero());
                }
            }
            else if(planeta != null && comando[1].equalsIgnoreCase("Vender") && indexConstructor >= 0){
                if(planeta.getConstructores(indexConstructor) != null){
                    Constructores ctor = planeta.getConstructores(indexConstructor);
                    planeta.agregarConstructores(ctor, indexConstructor, false);
                    turno.setDinero(turno.getDinero() + Constructores.PRECIO_V[indexConstructor]);
                    System.out.println("::Constructor vendido. Dinero actual: " + turno.getDinero());
                }
            }
            else {
                System.out.println("::Opción no valida");
            }
    }

    private void comandosCuatroPalabras(String comando[], Jugador turno, Mapa mapa){
        Planeta planeta = esPropietario(turno, comando[0], mapa);
        int cantidad = esUnNumero(comando[2]);
        int indexConstructor = esUnConstructor(comando[3]);
        if(planeta != null && cantidad > 0 && indexConstructor >= 0 && comando[1].equalsIgnoreCase("Comprar")){
            int total = cantidad*Constructores.PRECIO_V[indexConstructor];
            int diferencia = turno.getDinero() - total;
            if(diferencia > 0){
                turno.setDinero(diferencia);
                System.out.println("::Compra de: " + cantidad + "constructores: " + Constructores.NOMBRE[indexConstructor]);
                System.out.println("::Dinero actual: " + turno.getDinero());
            }
            else{
                System.out.println("::Dinero insuficiente");
            }
        }
        else{
            System.out.println("::Opción inválida");
        }
    }

    private void comandosCincoPalabras(String comando[], Jugador turno, Mapa mapa){
        Planeta p1 = esPropietario(turno, comando[0], mapa);
        int cantidad = esUnNumero(comando[1]);
        int indexGuerrero = esUnGuerrero(comando[2]);
        int indexNave = esUnaNave(comando[3]);
        Planeta p2 = esUnPlaneta(comando[4], mapa);
        if(p1 != null && cantidad > 0 && indexGuerrero >= 0 && indexNave >= 0 && p2 != null){
            p1.enviarGuerreros(cantidad, indexGuerrero, indexNave, p2);
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


    /**
     * Verifica si el comando se refiere a un planeta.
     *
     * @param planeta
     * @return
     */
    private Planeta esUnPlaneta(String planeta, Mapa mapa) {
        if (planeta.length() < 4 && planeta.charAt(0) >= 'A' && planeta.charAt(0) <= 'Z'
                && esUnNumero(planeta.substring(1)) > 0) {
            int c[] = obtenerCoordenadasPlanetas(planeta);
            if(existePlaneta(mapa, c)){
                Planeta campo[][]= mapa.getCampoJuego();
                Planeta p = campo[c[0]][c[1]];
                return p;
            }
        }
        return null;
    }

    private int esUnaNave(String nave){
        int x = 0;
        for (String n : Naves.NOMBRE){
            if(n.equalsIgnoreCase(nave)){
                return x;
            }
            x++;
        }
        return -1;
    }

    private int esUnGuerrero(String guerrero){
        int x = 0;
        for (String g : Guerreros.NOMBRE){
            if(g.equalsIgnoreCase(guerrero)){
                return x;
            }
            x++;
        }
        return -1;
    }

    private int esUnConstructor(String constructor){
        int x = 0;
        for (String c : Constructores.NOMBRE){
            if(c.equalsIgnoreCase(constructor)){
                return x;
            }
            x++;
        }
        return -1;
    }

    private int esUnNumero(String numero){
        try {
            int n = Integer.parseInt(numero);
            return Math.abs(n);
        }
        catch (Exception ex){
            return -1;
        }
    }

    private Planeta esPropietario(Jugador turno, String comando, Mapa mapa){
        Planeta p = esUnPlaneta(comando,mapa);
        if(p != null) {
            if (p.getPropietario().equals(turno)) {
                return p;
            }
        }
        return null;
    }
}




