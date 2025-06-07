package grafos;

import java.util.ArrayList;

public class GrafoMatriz implements Grafo {
	private ArrayList<ArrayList<Integer>> matriz;
	private ArrayList<Integer> vertices;
	private int numArestas;
	
	public GrafoMatriz() {
		matriz = new ArrayList<>();
		vertices = new ArrayList<>();
		numArestas = 0;
	}

	@Override
	public int addVertice(int v) {
//		Adiciona um vértice ao grafo caso ainda não exista
//		Retorna 0 caso adicione
//		Retorna 1 caso o vértice já exista
		if (vertices.contains(v))
			return 1;
		vertices.add(v);
		for (ArrayList<Integer> linha : matriz) {
            linha.add(0);
        }
		ArrayList<Integer> linha = new ArrayList<>();
		for (int i = 0; i < vertices.size(); i++) {
			linha.add(0);
		}
		matriz.add(linha);
		return 0;
	}

	@Override
	public int removerVertice(int v) {
//		Remove o vertice do grafo caso exista
//		Retorna 0 caso remova
//		Retorna 1 caso o vértice não exista
		int i = vertices.indexOf(v);
		if (i == -1)
			return 1;
		vertices.remove(i);
		for (ArrayList<Integer> linha : matriz) {
			linha.remove(i);
		}
		matriz.remove(i);
		return 0;
	}

	@Override
	public int addAresta(int v1, int v2) {
//		Adiciona uma aresta conectando dois vértices já existentes ao grafo
//		Retorna 0 caso adicione a aresta
//		Retorna 1 caso um dos vértices não exista
		if (!vertices.contains(v1) || !vertices.contains(v2)) {
			return 1;
		}
		int i = vertices.indexOf(v1);
		int j = vertices.indexOf(v2);
		matriz.get(i).add(j, 1);
		matriz.get(j).add(i, 1);
		numArestas++;
		return 0;
	}

	@Override
	public int removerAresta(int v1, int v2) {
//		Remove uma aresta existente do grafo
//		Retorna 0 caso remova
//		Retorna 1 caso a aresta ou um dos vértices não exista
		if (!vertices.contains(v1) || !vertices.contains(v2)) {
			return 1;
		}
		int i = vertices.indexOf(v1);
		int j = vertices.indexOf(v2);
		if (i == -1 || j == -1) {
			return 1;
		}
		matriz.get(i).remove(j);
		matriz.get(j).remove(i);
		numArestas--;
		return 0;
	}

	@Override
	public int getNumVertices() {
//		Conta o número de vértices existente no grafo
		return vertices.size();
	}

	@Override
	public int getNumArestas() {
//		Conta o número de arestas presente no grafo
		return numArestas;
	}

	@Override
	public int grau(int v) {
//		Conta o número de arestas incidentes no vertice v
//		Retorna o grau caso o vertice exista
//		Retorna -1 caso o vértice não exista
		int j = vertices.indexOf(v);
		if (j == -1) {
			return -1;
		}
		int grau = 0;
		for (ArrayList<Integer> linha : matriz) {
			if (linha.get(j) == 1)
				grau++;
		}
		return grau;
	}

	public boolean ehCompleto() {
//		Verifica se cada vertice presente possui aresta com cada outro vertice
		for (ArrayList<Integer> linha : matriz) {
			for (Integer v : linha) {
				if (v == 0) {
					return false;
				}
			}
		}
		return true;
	}

	public int kRegular() {
//		Calcula o grau de todos os vértices e verifica sua k-regularidade
//		Retorna a k-regularidade se todos os vértices possuem o mesmo grau
//		Retorna -1 caso não haja k-regularidade
		int k = grau(vertices.getFirst());
		for (Integer v : vertices) {
			if (k != grau(v))
				return -1;
		}
		return k;
	}

	public double densidade() {
//        Calcula a densidade do grafo de acorda com a fórmula (2 * A) / V
		return (double) (2 * getNumArestas()) / getNumVertices();
	}
	
	@Override
	public String toString() {
//		Cria uma representação matricial do grafo
		if (this.getNumVertices() == 0) {
			return "Grafo Nulo\n";
		}
		StringBuilder res = new StringBuilder();
		res.append("   ");
		for (Integer v : vertices) {
			res.append(v).append("  ");
		}
		res.append("\n");
		for (int i = 0; i < this.getNumVertices(); i++) {
			res.append(vertices.get(i)).append("  ");
			for (int j = 0; j < this.getNumVertices(); j++) {
				res.append(matriz.get(i).get(j)).append("  ");
			}
			res.append("\n");
		}
		return res.toString();
	}

	@Override
	public String representacaoFormal() {
//		Cria uma representação com um conjunto de vértices e um conjunto de arestas
		StringBuilder res = new StringBuilder();
		res.append("V = {");
		for (Integer v : vertices) {
			res.append(v).append(", ");
		}
		res.delete(res.length() - 2, res.length());
		res.append("}\n").append("A = {");
		for (int i = 0; i < this.getNumVertices(); i++) {
			for (int j = i; j < this.getNumVertices(); j++) {
				if (matriz.get(i).get(j) == 1) {
					res.append("(").append(vertices.get(i)).append(", ").append(vertices.get(j)).append(")");
					res.append(", ");
				}
			}
		}
		res.delete(res.length() - 2, res.length());
		res.append("}\n");
		return res.toString();
	}
}
