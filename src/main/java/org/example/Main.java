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

        // Generar obstáculos
        generarObstaculos(mapa);
        // Generar salida
        generarSalida(mapa);
        // Generar cofre
        generarCofre(mapa);
        return mapa;
    }

    public static void imprimirMapa(String[][] mapa) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(mapa[i][j]);
            }
            System.out.println();
        }
    }


    public static void llenarMapa(String[][] mapa) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (mapa[i][j] == null) {
                    mapa[i][j] = ".";
                }
            }
        }
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
        Scanner scanner = new Scanner(System.in);
        String opcion = "";

        System.out.println("Bienvenido a Escapa del Cuadrado!");
        System.out.println("Ingresa una opción para comenzar a jugar o salir del programa.");

        while (!opcion.equals("2")) {
            System.out.println("1. Iniciar la partida");
            System.out.println("2. Salir del programa");

            opcion = scanner.nextLine();

            if (opcion.equals("1")) {
                // Inicializar el mapa, crear personaje y enemigos
                String[][] mapa = inicializarMapa();
                int[] personaje = crearPersonaje();
                int[][] enemigos = generarEnemigos(mapa, 3);

                // Colocar el jugador y los enemigos en el mapa
                colocarJugadorEnMapa(mapa, personaje);
                colocarEnemigosEnMapa(mapa, enemigos);

                boolean jugando = true;
                while (jugando) {
                    imprimirMapa(mapa); // Mostrar el mapa en cada turno
                    personaje = moverPersonaje(mapa, personaje); // Mover al personaje

                    if (personaje[0] == 9 && personaje[1] == 9) {
                        System.out.println("¡Has llegado a la salida! ¡Ganaste!");
                        jugando = false;
                    }
                }
            } else if (opcion.equals("2")) {
                System.out.println("Saliendo del programa...");
                break;
            } else {
                System.out.println("Opción no válida, por favor ingresa 1 o 2.");
            }
        }

        scanner.close();
    }

    public static int[] crearPersonaje() {
        return new int[]{0, 0, 100, 15}; // [X, Y, Vida, Ataque]
    }

    public static int[] crearEnemigo(int x, int y) {
        return new int[]{x, y, 45, 10}; // [X, Y, Vida, Ataque]
    }

    public static int[] moverPersonaje(String[][] mapa, int[] personaje) {
        Scanner scanner = new Scanner(System.in);
        boolean movimientoValido = false;
        int jugadorX = personaje[0];
        int jugadorY = personaje[1];

        while (!movimientoValido) {
            System.out.println("Ingresa un movimiento (w = arriba, s = abajo, a = izquierda, d = derecha): ");
            String movimiento = scanner.nextLine();

            int nuevaX = jugadorX;
            int nuevaY = jugadorY;

            switch (movimiento) {
                case "w":
                    nuevaX--;
                    break;
                case "s":
                    nuevaX++;
                    break;
                case "a":
                    nuevaY--;
                    break;
                case "d":
                    nuevaY++;
                    break;
                default:
                    System.out.println("Movimiento inválido. Inténtalo de nuevo.");
                    continue;
            }

            // Verificar si la nueva posición es válida
            if (nuevaX >= 0 && nuevaX < mapa.length && nuevaY >= 0 && nuevaY < mapa[0].length && mapa[nuevaX][nuevaY].equals(".")) {
                mapa[jugadorX][jugadorY] = "."; // Vaciar la posición anterior
                personaje[0] = nuevaX; // Actualizar la nueva posición
                personaje[1] = nuevaY;
                mapa[nuevaX][nuevaY] = "J";
                movimientoValido = true;
            } else {
                System.out.println("Movimiento no permitido. Intenta una dirección válida.");
            }
        }
        return personaje;
    }

    public static void colocarJugadorEnMapa(String[][] mapa, int[] personaje) {
        boolean posicionValida = false;

        while (!posicionValida) {
            int x = (int) (Math.random() * (mapa.length - 2)) + 1; // Para evitar los bordes
            int y = (int) (Math.random() * (mapa[0].length - 2)) + 1;

            // Verificar si la posición está vacía
            if (mapa[x][y].equals(".")) {
                personaje[0] = x;
                personaje[1] = y;
                mapa[x][y] = "J";
                posicionValida = true;
            }
        }
    }

    public static void colocarEnemigosEnMapa(String[][] mapa, int[][] enemigos) {
        for (int[] enemigo : enemigos) {
            mapa[enemigo[0]][enemigo[1]] = "E";
        }
    }

    public static int[][] generarEnemigos(String[][] mapa, int cantidad) {
        int[][] enemigos = new int[cantidad][4]; // [X, Y, Vida, Ataque] para cada enemigo

        for (int i = 0; i < cantidad; i++) {
            boolean posicionValida = false;

            while (!posicionValida) {
                int x = (int) (Math.random() * (mapa.length - 2)) + 1;
                int y = (int) (Math.random() * (mapa[0].length - 2)) + 1;

                if (mapa[x][y].equals(".")) {
                    enemigos[i] = crearEnemigo(x, y);
                    mapa[x][y] = "E";
                    posicionValida = true;
                }
            }
        }

        return enemigos;
    }


}

