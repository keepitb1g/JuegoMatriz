package org.example;

import java.lang.Math;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        menu();
    }

    public static String[][] inicializarMapa() {
        String[][] mapa = new String[10][10];
        // Inicializar bordes de la matriz con '#'
        for (int i = 0; i < 10; i++) {
            mapa[0][i] = "#";
            mapa[i][0] = "#";
            mapa[9][i] = "#";
            mapa[i][9] = "#";
        }
        // Rellenar espacios vacíos
        llenarMapa(mapa);
        // Generar jugador
        generarJugador(mapa);
        // Generar obstáculos
        generarObstaculos(mapa);
        // Generar enemigos
        generarEnemigos(mapa);
        // Generar salida
        generarSalida(mapa);
        // Generar cofre
        generarCofre(mapa);
        return mapa;
    }

    public static void imprimirMapa() {
        String[][] mapa = inicializarMapa();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(mapa[i][j]);
            }
            System.out.println();
        }
    }

    public static String[][] llenarMapa(String[][] mapa) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (mapa[i][j] == null) {
                    mapa[i][j] = ".";
                }
            }
        }
        return mapa;
    }

    public static String[][] generarObstaculos(String[][] mapa) {
        int obstaculosGenerados = 0;
        while (obstaculosGenerados < 10) {
            int numeroPosicionX = (int) (Math.random() * 9 + 1);
            int numeroPosicionY = (int) (Math.random() * 9 + 1);
            if (mapa[numeroPosicionX][numeroPosicionY].equals(".")) {
                mapa[numeroPosicionX][numeroPosicionY] = "#";
                obstaculosGenerados++;
            }
        }
        return mapa;
    }

    public static String[][] generarJugador(String[][] mapa) {
        boolean jugadorColocado = false;
        while (!jugadorColocado) {
            int numeroPosicionX = (int) (Math.random() * 9 + 1);
            int numeroPosicionY = (int) (Math.random() * 9 + 1);
            if (mapa[numeroPosicionX][numeroPosicionY].equals(".")) {
                mapa[numeroPosicionX][numeroPosicionY] = "P";
                jugadorColocado = true;
            }
        }
        return mapa;
    }

    public static String[][] generarEnemigos(String[][] mapa) {
        int enemigosGenerados = 0;
        while (enemigosGenerados < 3) {
            int numeroPosicionX = (int) (Math.random() * 9 + 1);
            int numeroPosicionY = (int) (Math.random() * 9 + 1);
            if (mapa[numeroPosicionX][numeroPosicionY].equals(".")) {
                mapa[numeroPosicionX][numeroPosicionY] = "E";
                enemigosGenerados++;
            }
        }
        return mapa;
    }

    public static String[][] generarSalida(String[][] mapa) {
        boolean salidaColocada = false;
        while (!salidaColocada) {
            int numeroPosicionX = (int) (Math.random() * 9 + 1);
            int numeroPosicionY = (int) (Math.random() * 9 + 1);
            if (mapa[numeroPosicionX][numeroPosicionY].equals(".")) {
                mapa[numeroPosicionX][numeroPosicionY] = "X";
                salidaColocada = true;
            }
        }
        return mapa;
    }

    public static String[][] generarCofre(String[][] mapa) {
        boolean cofreColocado = false;
        while (!cofreColocado) {
            int numeroPosicionX = (int) (Math.random() * 9 + 1);
            int numeroPosicionY = (int) (Math.random() * 9 + 1);
            if (mapa[numeroPosicionX][numeroPosicionY].equals(".")) {
                mapa[numeroPosicionX][numeroPosicionY] = "C";
                cofreColocado = true;
            }
        }
        return mapa;
    }
    public static void menu() {
        Scanner scanner = new Scanner(System.in); // Crear Scanner una vez
        String opcion = "";

        System.out.println("Bienvenido a Escapa del Cuadrado!");
        System.out.println("Ingresa una opción para comenzar a jugar o salir del programa.");

        while (!opcion.equals("2")) {
            System.out.println("1. Iniciar la partida");
            System.out.println("2. Salir del programa");

            opcion = scanner.nextLine();

            if (opcion.equals("1")) {
                imprimirMapa();
            } else if (opcion.equals("2")) {
                System.out.println("Saliendo del programa...");
                break;
            } else {
                System.out.println("Opción no válida, por favor ingresa 1 o 2.");
            }
        }

        scanner.close(); // Cerrar Scanner después de usarlo
    }

}

