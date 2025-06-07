package grafos;

import java.util.ArrayList;

public class DigrafoMatriz extends GrafoMatriz{
    ArrayList<ArrayList<Integer>> matriz;
    ArrayList<Integer> vertices;
    int numArestas;

    @Override
    public int addAresta(int v1, int v2) {
//		Adiciona uma aresta de v1 a v2 que já existem no grafo
//		Retorna 0 caso adicione a aresta
//		Retorna 1 caso um dos vértices não exista
        if (!vertices.contains(v1) || !vertices.contains(v2)) {
            return 1;
        }
        int i = vertices.indexOf(v1);
        int j = vertices.indexOf(v2);
        matriz.get(i).add(j, 1);
        numArestas++;
        return 0;
    }

    @Override
    public int removerAresta(int v1, int v2) {
//		Remove a aresta existente v1 a v2 do grafo
//		Retorna 0 caso remova
//		Retorna 1 caso a aresta ou um dos vértices não exista
        if (!vertices.contains(v1) || !vertices.contains(v2)) {
            return 1;
        }
        int i = vertices.indexOf(v1);
        int j = vertices.indexOf(v2);
        if (i != -1 && j != -1) {
            return 1;
        }
        matriz.get(i).remove(j);
        numArestas--;
        return 0;
    }
}
