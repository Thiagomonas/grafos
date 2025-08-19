package percurso;

import java.util.Arrays;

public class PercursoMinimo {
    public int[] distancias;
    public int[] pi;

    public PercursoMinimo(int verticeInicial, int[] vertices) {
        initVetores(verticeInicial, vertices);
    }

    private void initVetores(int verticeInicial, int[] vertices) {
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
        this.pi = new int[tam + 1];
        Arrays.fill(pi, Integer.MAX_VALUE);
    }
}
