package grafos;

import java.util.ArrayList;
import java.util.HashMap;

public class GrafoListaAdj implements Grafo {
	private HashMap<Integer, ArrayList<Integer>> listaAdj;
    private int numArestas;

	public GrafoListaAdj() {
		listaAdj = new HashMap<>();
        numArestas = 0;
	}

    @Override
    public int addVertice(int v) {
//        Adiciona um vértice não existente no grafo
//        Retorna 0 ao adicionar o vértice
//        Retorna 1 caso o vértice já existe
        if (listaAdj.containsKey(v))
            return 1;
        listaAdj.put(v, new ArrayList<>());
        return 0;
    }

    @Override
    public int removerVertice(int v) {
//        Remove um vértice já existente no grafo
//        Retorna 0 ao remover o vértice
//        Retorna 1 caso o vértice não existe
        ArrayList<Integer> res = listaAdj.remove(v);
        if (res == null)
            return 1;
        for (ArrayList<Integer> vAdj : listaAdj.values()) {
            vAdj.remove((Object) v);
        }
        return 0;
    }

	@Override
	public int addAresta(int v1, int v2) {
//        Adicionar uma aresta não existente ao grafo
//        Retorna 0 se a aresta foi adicionada
//        Retorna 1 caso um dos vértices não existe
		ArrayList<Integer> v1Adj = listaAdj.get(v1);
        ArrayList<Integer> v2Adj = listaAdj.get(v2);
		if (v1Adj == null || v2Adj == null)
			return 1;
        if (v1Adj.contains(v2) || v2Adj.contains(v1))
            return 1;
        v1Adj.add(v2);
        if (v1 != v2)
            v2Adj.add(v1);
        numArestas++;
        return 0;
	}

	@Override
	public int removerAresta(int v1, int v2) {
//        Remove uma aresta já existente no grafo
//        Retorna 0 ao remover a aresta
//        Retorna 1 caso a aresta não exista
		ArrayList<Integer> v1Adj = listaAdj.get(v1);
        ArrayList<Integer> v2Adj = listaAdj.get(v2);
		if (v1Adj == null || v2Adj == null)
            return 1;
        v1Adj.remove((Object) v2);
        v2Adj.remove((Object) v1);
        listaAdj.put(v1, v1Adj);
        listaAdj.put(v2, v2Adj);
        numArestas--;
        return 0;
	}

	@Override
	public int getNumVertices() {
		return listaAdj.size();
	}

	@Override
	public int getNumArestas() {
		return this.numArestas;
	}

	@Override
	public int grau(int v) {
//        Conta o número de arestas que partem de v (mesmo número que as incidentes)
//        Retorna o grau caso o vértice exista
//        Retorna -1 caso o vértice não exista
        ArrayList<Integer> vAdj = listaAdj.get(v);
        if (vAdj == null)
            return -1;
		return vAdj.size();
	}

    @Override
    public boolean ehCompleto() {
//        Verifica se para cada vértice no grafo possui uma aresta com cada outra aresta do grafo
        for (ArrayList<Integer> vAdj : listaAdj.values()) {
            if (vAdj.size() < listaAdj.size())
                return false;
        }
        return true;
    }

    @Override
    public int kRegular() {
//		Calcula o grau de todos os vértices e verifica sua k-regularidade
//		Retorna a k-regularidade se todos os vértices possuem o mesmo grau
//		Retorna -1 caso não haja k-regularidade
        int k = -1;
        for (Integer v : listaAdj.keySet()) {
            if (k == -1)
                k = grau(v);
            if (k != grau(v))
                return -1;
        }
        return k;
    }

    @Override
    public double densidade() {
//        Calcula a densidade do grafo de acorda com a fórmula (2 * A) / V
        return (double) (2 * getNumArestas()) / getNumVertices();
    }

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		for (HashMap.Entry<Integer, ArrayList<Integer>> par : listaAdj.entrySet()) {
            res.append(par.getKey());
            ArrayList<Integer> vertices = par.getValue();
            if (vertices.isEmpty()) {
                res.append('\n');
                continue;
            }
			res.append(" -> ");
			for (int i = 0; i < vertices.size() - 1; i++) {
				res.append(vertices.get(i)).append(", ");
			}
			res.append(vertices.getLast()).append("\n");
		}
		return res.toString();
	}

    @Override
    public String representacaoFormal() {
        if (listaAdj.isEmpty())
            return "Grafo nulo";
        StringBuilder res = new StringBuilder();
        res.append("V = {");
        listaAdj.keySet().forEach(v -> { res.append(v); res.append(", "); });
        res.delete(res.length() - 2, res.length());
        res.append("}\n");

        res.append("A = {");
        ArrayList<Integer> verticesVisitados = new ArrayList<>();
        for (HashMap.Entry<Integer, ArrayList<Integer>> par : listaAdj.entrySet()) {
            for (int vAdj : par.getValue()) {
                if (!verticesVisitados.contains(vAdj))
                    res.append("(").append(par.getKey()).append(", ").append(vAdj).append("), ");
            }
            verticesVisitados.add(par.getKey());
        }
        res.delete(res.length() - 2, res.length());
        res.append("}\n");
        return res.toString();
    }
}
