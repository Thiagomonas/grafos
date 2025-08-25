package percurso;

import java.util.ArrayList;
import java.util.Arrays;

public class PercursoMinimo {
    public int[] distancias;
    public int[] pi;
    public int[][] matrizDist;

    public PercursoMinimo(int verticeInicial, int[] vertices) {
        initDistancias(verticeInicial, vertices);
        initPi(vertices);
    }

    public PercursoMinimo(int[] vertices) {
        initMatrizDist(vertices);
        initPi(vertices);
    }

    private void initDistancias(int verticeInicial, int[] vertices) {
        int tam = 0, max = 0;
        for (int v : vertices) {
            if (v > max) {
                tam = v;
                max = v;
            }
        }
        this.distancias = new int[tam + 1];
        Arrays.fill(distancias, Integer.MAX_VALUE);
        distancias[verticeInicial] = 0;
    }
    private void initPi(int[] vertices) {
        int tam = 0, max = 0;
        for (int v : vertices) {
            if (v > max) {
                tam = v;
                max = v;
            }
        }
        this.pi = new int[tam + 1];
        Arrays.fill(pi, Integer.MAX_VALUE);
    }

    private void initMatrizDist(int[] vertices) {
        int tam = 0, max = 0;
        for (int v : vertices) {
            if (v > max) {
                tam = v;
                max = v;
            }
        }
        this.matrizDist = new int[tam + 1][tam + 1];
        for (int i = 0; i < tam; i++) {
            Arrays.fill(matrizDist[i], Integer.MAX_VALUE);
        }
    }
}
