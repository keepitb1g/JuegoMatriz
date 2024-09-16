package org.example;

import org.example.Main;
import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    public void testInicializarMapa() {
        String[][] mapa = Main.inicializarMapa();
        assert mapa.length == 10;
        assert mapa[0].length == 10;
    }

    @Test
    public void testImprimirMapa() {
        String[][] mapa = Main.inicializarMapa();
        Main.imprimirMapa(mapa);
    }

    @Test
    public void testRastrearSalida() {
        String[][] mapa = Main.inicializarMapa();
        int[] salida = Main.rastrearSalida(mapa);
        assert salida.length == 2;
    }
    @Test
    public void testCrearPersonaje() {
        int[] personaje = Main.crearPersonaje();
        assert personaje.length == 4;
        assert personaje[0] == 0;
        assert personaje[1] == 0;
        assert personaje[2] == 100;
        assert personaje[3] == 15;
    }

    @Test
    public void testCrearEnemigo() {
        int[] enemigo = Main.crearEnemigo(1, 1);
        assert enemigo.length == 4;
        assert enemigo[0] == 1;
        assert enemigo[1] == 1;
        assert enemigo[2] == 45;
        assert enemigo[3] == 10;
    }

    @Test
    public void testGenerarCofre() {
        String[][] mapa = Main.inicializarMapa();
        String[][] nuevoMapa = Main.generarCofre(mapa);
        assert nuevoMapa.length == 10;
        assert nuevoMapa[0].length == 10;
    }
}

