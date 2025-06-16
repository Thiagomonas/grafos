package grafos;

import java.util.Arrays;
import java.util.HashMap;

public class GrafoListaAdjValorado implements GrafoValorado {
    protected HashMap<Integer, HashMap<Integer, Integer>> listaAdj;
    protected int numArestas;

    public GrafoListaAdjValorado() {
        this.listaAdj = new HashMap<>();
        this.numArestas = 0;
    }

    @Override
    public int addVertice(int v) {
        if (listaAdj.containsKey(v))
            return 1;
        listaAdj.put(v, new HashMap<>());
        return 0;
    }

    @Override
    public int removerVertice(int v) {
        if (!listaAdj.containsKey(v))
            return 1;
        listaAdj.remove(v);
        for (HashMap<Integer, Integer> vAdj : listaAdj.values()) {
            vAdj.remove(v);
        }
        return 0;
    }

    @Override
    public int addAresta(int v1, int v2, int valor) {
        if (!listaAdj.containsKey(v1) || !listaAdj.containsKey(v2))
            return 1;
        if (!listaAdj.get(v1).containsKey(v2))
            listaAdj.get(v1).put(v2, valor);
        if (!listaAdj.get(v2).containsKey(v1))
            listaAdj.get(v2).put(v1, valor);
        numArestas++;
        return 0;
    }

    @Override
    public int removerAresta(int v1, int v2) {
        if (!listaAdj.containsKey(v1) || !listaAdj.containsKey(v2))
            return 1;
        listaAdj.get(v1).remove(v2);
        listaAdj.get(v2).remove(v1);
        numArestas--;
        return 0;
    }

    @Override
    public int mudarValorAresta(int v1, int v2, int valor) {
        if (!listaAdj.containsKey(v1) || !listaAdj.containsKey(v2))
            return 1;
        listaAdj.get(v1).put(v2, valor);
        listaAdj.get(v2).put(v1, valor);
        return 0;
    }

    @Override
    public int getNumVertices() {
        return listaAdj.size();
    }

    @Override
    public int getNumArestas() {
        return numArestas;
    }

    @Override
    public int getValorAresta(int v1, int v2) {
        return listaAdj.get(v1).get(v2);
    }

    protected int getVerticeAdjCustoMin(int v, int[] verticesMarcados) {
        int verticeMin = Integer.MAX_VALUE;
        int valorMin = Integer.MAX_VALUE;
        for (HashMap.Entry<Integer, Integer> par : listaAdj.get(v).entrySet()) {
            if (valorMin > par.getValue() && Arrays.stream(verticesMarcados).allMatch((vertice) -> vertice != par.getKey())) {
                valorMin = par.getValue();
                verticeMin = par.getKey();
            }
        }
        return verticeMin;
    }

    @Override
    public int[] getCicloHamiltoniano(int verticeInicial) {
//        Retorna um ciclo hamiltoniano, possivelmente de custo mínimo, usando o algoritmo de Bellmore e Nemhauser
        if (!listaAdj.containsKey(verticeInicial))
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
        if (!listaAdj.get(verticeAtual).containsKey(verticeInicial) || numVerticesVisitados < getNumVertices())
            // Não há ciclo hamiltoniano
            return null;
        cicloHamiltoniano[numVerticesVisitados] = verticeInicial;
        return cicloHamiltoniano;
    }

    @Override
    public String toString() {
        if (listaAdj.isEmpty())
            return "Grafo Nulo";
        StringBuilder res = new StringBuilder();
        for (HashMap.Entry<Integer, HashMap<Integer, Integer>> par : listaAdj.entrySet()) {
            res.append(par.getKey()).append(" ");
            for (HashMap.Entry<Integer, Integer> par2 : par.getValue().entrySet()) {
                res.append("-(").append(par2.getValue()).append(")-> ").append(par2.getKey()).append(", ");
            }
            res.delete(res.length() - 2, res.length());
            res.append("\n");
        }
        return res.toString();
    }

    @Override
    public String representacaoFormal() {
        return "";
    }
}
