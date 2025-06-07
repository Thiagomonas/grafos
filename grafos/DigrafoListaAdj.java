package grafos;

import java.util.ArrayList;
import java.util.HashMap;

public class DigrafoListaAdj extends GrafoListaAdj {
    private HashMap<Integer, ArrayList<Integer>> listaAdj;
    private int numArestas;

    @Override
    public int addAresta(int v1, int v2) {
//        Adicionar uma aresta de v1 a v2 não existente ao grafo
//        Retorna 0 se a aresta foi adicionada
//        Retorna 1 caso um dos vértices não existe
        ArrayList<Integer> v1Adj = listaAdj.get(v1);
        if (v1Adj == null || listaAdj.get(v2) == null)
            return 1;
        if (v1Adj.contains(v2))
            return 1;
        v1Adj.add(v2);
        numArestas++;
        return 0;
    }

    @Override
    public int removerAresta(int v1, int v2) {
//        Remove uma aresta de v1 a v2 já existente no grafo
//        Retorna 0 ao remover a aresta
//        Retorna 1 caso a aresta não exista
        ArrayList<Integer> v1Adj = listaAdj.get(v1);
        if (v1Adj == null || listaAdj.get(v2) == null)
            return 1;
        v1Adj.remove((Object) v2);
        listaAdj.put(v1, v1Adj);
        numArestas--;
        return 0;
    }

    @Override
    public int grau(int v) {
//        Conta o número de arestas incidentes no vertice v
//        Retorna o grau caso o vértice exista
//        Retorna -1 caso o vértice não exista
        int grau = 0;
        for (ArrayList<Integer> vAdj : listaAdj.values()) {
            if (vAdj.contains(v))
                grau++;
        }
        return grau;
    }
}
