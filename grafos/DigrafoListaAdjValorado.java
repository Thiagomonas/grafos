package grafos;

public class DigrafoListaAdjValorado extends GrafoListaAdjValorado {

    @Override
    public int addAresta(int v1, int v2, int valor) {
        if (!listaAdj.containsKey(v1) || !listaAdj.containsKey(v2))
            return 1;
        if (listaAdj.get(v1).containsKey(v2))
            return 1;
        listaAdj.get(v1).put(v2, valor);
        numArestas++;
        return 0;
    }

    @Override
    public int removerAresta(int v1, int v2) {
        if (!listaAdj.containsKey(v1) || !listaAdj.containsKey(v2))
            return 1;
        listaAdj.get(v1).remove(v2);
        numArestas--;
        return 0;
    }

    @Override
    public int mudarValorAresta(int v1, int v2, int valor) {
        if (!listaAdj.containsKey(v1) || !listaAdj.containsKey(v2))
            return 1;
        listaAdj.get(v1).put(v2, valor);
        return 0;
    }
}
