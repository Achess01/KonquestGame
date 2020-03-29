package com.achess;

import com.achess.cola.Cola;
import com.achess.constructores.Constructores;
import com.achess.guerreros.Guerreros;
import com.achess.naves.Naves;
import com.achess.planetas.Planeta;

import java.util.Scanner;


public interface Agregar {
    /**
     * Agrega o borra elementos de un arreglo dependiendo del valor de 'validar'
     * @param obj
     * @param objetos
     * @param validar
     * @return el arreglo con un espacio más o uno menos.
     */
    default Guerreros[] agregarElementos(Guerreros obj, Guerreros[] objetos, boolean validar){
        if(validar) {
            Guerreros newObjetos[] = new Guerreros[objetos.length + 1];
            newObjetos[0] = obj;
            for (int x = 1; x < newObjetos.length; x++) {
                newObjetos[x] = objetos[x - 1];
            }
            return newObjetos;
        }
        else{
            Guerreros newObjetos[] = new Guerreros[objetos.length - 1];
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
    /**
     * Agrega o borra elementos de un arreglo dependiendo del valor de 'validar'
     * @param obj
     * @param objetos
     * @param validar
     * @return el arreglo con un espacio más o uno menos.
     */
    default Naves[] agregarElementos(Naves obj, Naves[] objetos, boolean validar){
        if(validar) {
            Naves newObjetos[] = new Naves[objetos.length + 1];
            newObjetos[0] = obj;
            for (int x = 1; x < newObjetos.length; x++) {
                newObjetos[x] = objetos[x - 1];
            }
            return newObjetos;
        }
        else{
            Naves newObjetos[] = new Naves[objetos.length - 1];
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
    /**
     * Agrega o borra elementos de un arreglo dependiendo del valor de 'validar'
     * @param obj
     * @param objetos
     * @param validar
     * @return el arreglo con un espacio más o uno menos.
     */
    default Planeta[] agregarElementos(Planeta obj, Planeta[] objetos, boolean validar){
        if(validar) {
            Planeta newObjetos[] = new Planeta[objetos.length + 1];
            newObjetos[0] = obj;
            for (int x = 1; x < newObjetos.length; x++) {
                newObjetos[x] = objetos[x - 1];
            }
            return newObjetos;
        }
        else{
            Planeta newObjetos[] = new Planeta[objetos.length - 1];
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
    /**
     * Agrega o borra elementos de un arreglo dependiendo del valor de 'validar'
     * @param obj
     * @param objetos
     * @param validar
     * @return el arreglo con un espacio más o uno menos.
     */
    default Constructores[] agregarElementos(Constructores obj, Constructores[] objetos, boolean validar){
        if(validar) {
            Constructores newObjetos[] = new Constructores[objetos.length + 1];
            newObjetos[0] = obj;
            for (int x = 1; x < newObjetos.length; x++) {
                newObjetos[x] = objetos[x - 1];
            }
            return newObjetos;
        }
        else{
            Constructores newObjetos[] = new Constructores[objetos.length - 1];
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
    /**
     * Agrega o borra elementos de un arreglo dependiendo del valor de 'validar'
     * @param obj
     * @param objetos
     * @param validar
     * @return el arreglo con un espacio más o uno menos.
     */
    default Cola[] agregarElementos(Cola obj, Cola[] objetos, boolean validar) {
        if (validar) {
            Cola newObjetos[] = new Cola[objetos.length + 1];
            newObjetos[0] = obj;
            for (int x = 1; x < newObjetos.length; x++) {
                newObjetos[x] = objetos[x - 1];
            }
            return newObjetos;
        } else {
            Cola newObjetos[] = new Cola[objetos.length - 1];
            for (int x = 0; x < newObjetos.length; x++) {
                if (obj.equals(objetos[x])) {
                    for (int y = x; y < newObjetos.length; y++) {
                        objetos[x] = objetos[x + 1];
                    }
                }
                newObjetos[x] = objetos[x];
            }
            return newObjetos;
        }
    }

    default int numerosAleatorios(int min, int max){
        int aleatorio = (int)Math.floor(Math.random()*(max - min +1) + min);
        return aleatorio;
    }

    default int pedirDato(String mensaje){
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
}
