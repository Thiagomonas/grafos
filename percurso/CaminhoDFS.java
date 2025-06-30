package percurso;

import enums.Cor;

import java.util.Arrays;

public class CaminhoDFS {
    public int[] vertices;
    public Cor[] cores;
    public int[] pi;
    public int[] d;
    public int[] f;

    public CaminhoDFS(int tam) {
        vertices = new int[tam];
        cores = initCores(tam);
        pi = new int[tam];
        d = new int[tam];
        f = new int[tam];
    }

    private Cor[] initCores(int tam) {
        Cor[] cores = new Cor[tam];
        Arrays.fill(cores, Cor.BRANCO);
        return cores;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pi.length; i++) {
//            Verifica se esse vértice existe
            if (cores[i] == Cor.BRANCO && pi[i] == 0 && d[i] == 0 && f[i] == 0)
                continue;
            sb.append("cor[").append(i).append("] = ").append(cores[i]).append(", ");
            if (pi[i] == Integer.MIN_VALUE)
                sb.append("pi[").append(i).append("] = NULL, ");
            else
                sb.append("pi[").append(i).append("] = ").append(pi[i]).append(", ");
            sb.append("d[").append(i).append("] = ").append(d[i]).append(", ");
            sb.append("f[").append(i).append("] = ").append(f[i]).append("\n");
        }
        return sb.toString();
    }
}
