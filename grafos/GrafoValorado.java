package grafos;

import percurso.PercursoMinimo;

import java.util.Arrays;

public abstract class GrafoValorado {
    public abstract int addVertice(int v);
    public abstract int removerVertice(int v);
    public abstract int addAresta(int v1, int v2, int valor);
    public abstract int removerAresta(int v1, int v2);
    public abstract int mudarValorAresta(int v1, int v2, int valor);
    public abstract int getNumVertices();
    public abstract int getNumArestas();
    public abstract int getValorAresta(int v1, int v2);
    public abstract int[] getVertices();
    public abstract int[] getVerticesAdjacentes(int v);
    public abstract String representacaoFormal();

    private int getVerticeAdjCustoMin(int v, int[] verticesMarcados) {
        int verticeMin = Integer.MAX_VALUE;
        int valorMin = Integer.MAX_VALUE;
        int[] vertices = getVertices();
        for (int vertice: vertices) {
            if (Arrays.stream(verticesMarcados).anyMatch(vert -> vert == vertice))
                continue;
            for (int vAdj : getVerticesAdjacentes(vertice)) {
                if (valorMin > getValorAresta(vertice, vAdj)) {
                    valorMin = vAdj;
                    verticeMin = vertice;
                }
            }
        }
        return verticeMin;
    }

    public int[] getCicloHamiltoniano(int verticeInicial) {
//        Retorna um ciclo hamiltoniano, possivelmente de custo mínimo, usando o algoritmo de Bellmore e Nemhauser
        int[] vertices = getVertices();
        if (Arrays.stream(vertices).noneMatch(v -> v == verticeInicial))
            return null;

        int[] cicloHamiltoniano = new int[getNumVertices() + 1];
        cicloHamiltoniano[0] = verticeInicial;
        int verticeAtual = verticeInicial;
        int numVerticesVisitados = 1;
        while (numVerticesVisitados < getNumVertices()) {
            int v = getVerticeAdjCustoMin(verticeAtual, cicloHamiltoniano);
            if (v == Integer.MAX_VALUE) {
                // Não há outro vértice para ir
                break;
            }
            verticeAtual = v;
            cicloHamiltoniano[numVerticesVisitados++] = v;
        }
        if (Arrays.stream(getVerticesAdjacentes(verticeAtual)).noneMatch(v -> v == verticeInicial) || numVerticesVisitados < getNumVertices())
            // Não há ciclo hamiltoniano
            return null;
        cicloHamiltoniano[numVerticesVisitados] = verticeInicial;
        return cicloHamiltoniano;
    }


    private int relaxar(int v1, int v2, PercursoMinimo percurso) {
        int novo_dist = percurso.distancias[v1] + getValorAresta(v1, v2);
        if (novo_dist < percurso.distancias[v2]) {
            percurso.distancias[v2] = novo_dist;
            percurso.pi[v2] = v1;
            return 0;
        }
        return 1;
    }

    public PercursoMinimo bellmanFord(int verticeInicial) {
        PercursoMinimo percursoMinimo = new PercursoMinimo(verticeInicial, getVertices());
        for (int i = 0; i < getNumVertices() - 1; i++) {
            for (int v: getVertices()) {
                for (int vAdj : getVerticesAdjacentes(v)) {
                    relaxar(v, vAdj, percursoMinimo);
                }
            }
        }
        for (int v: getVertices()) {
            for (int vAdj : getVerticesAdjacentes(v)) {
                if (relaxar(v, vAdj, percursoMinimo) == 0)
                    // Ciclo negativo encontrado
                    return null;
            }
        }
        return percursoMinimo;
    }

    public PercursoMinimo floydWarshall() {
        PercursoMinimo percursoMinimo = new PercursoMinimo(getVertices());
        int numVertices = getNumVertices();
        int[] vertices = getVertices();
        for (int v: vertices) {
            for (int w: vertices) {
                if (v == w) {
                    percursoMinimo.matrizDist[v][w] = 0;
                }
                else {
                    percursoMinimo.matrizDist[v][w] = getValorAresta(v, w);
                    percursoMinimo.pi[w] = v;
                }
            }
        }
        for (int k : vertices) {
            for (int v: vertices) {
                for (int w: vertices) {
                    if (percursoMinimo.matrizDist[v][k] == Integer.MAX_VALUE || percursoMinimo.matrizDist[k][w] == Integer.MAX_VALUE || percursoMinimo.matrizDist[v][w] == Integer.MAX_VALUE)
                        continue;
                    if (percursoMinimo.matrizDist[v][k] + percursoMinimo.matrizDist[k][w] < percursoMinimo.matrizDist[v][w]) {
                        percursoMinimo.matrizDist[v][w] = percursoMinimo.matrizDist[v][k] + percursoMinimo.matrizDist[k][w];
                        percursoMinimo.pi[w] = k;
                    }
                }
            }
        }
        return percursoMinimo;
    }
}
