package grafos;

import java.util.HashMap;

public class GrafoListaAdjValorado extends GrafoValorado {
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
        if (!listaAdj.containsKey(v1) || !listaAdj.containsKey(v2) || !listaAdj.get(v1).containsKey(v2))
            return Integer.MAX_VALUE;
        return listaAdj.get(v1).get(v2);
    }

    @Override
    public int[] getVertices() {
        int[] vertices = new int[getNumVertices()];
        int i = 0;
        for (int v: listaAdj.keySet()) {
            vertices[i] = v;
            i++;
        }
        return vertices;
    }

    @Override
    public int[] getVerticesAdjacentes(int v) {
        int[] verticesAdj = new int[listaAdj.get(v).size()];
        int i = 0;
        for (int vAdj : listaAdj.get(v).keySet()) {
            verticesAdj[i] = vAdj;
            i++;
        }
        return verticesAdj;
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
