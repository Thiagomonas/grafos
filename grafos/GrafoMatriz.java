package grafos;

import java.util.ArrayList;


public class GrafoMatriz extends Grafo {
	protected ArrayList<ArrayList<Integer>> matriz;
	protected ArrayList<Integer> vertices;
	protected int numArestas;
	
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
		matriz.get(i).set(j, 1);
		matriz.get(j).set(i, 1);
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
		matriz.get(i).set(j, 0);
		matriz.get(j).set(i, 0);
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
	public int[] getVertices() {
		return vertices.stream().mapToInt(v -> v).toArray();
	}

	@Override
	public int[] getVerticesAdjacentes(int v) {
		ArrayList<Integer> verticesAdjacentes = new ArrayList<>();
		int i = vertices.indexOf(v);
		for (int j = 0; j < matriz.size(); j++) {
			if (matriz.get(i).get(j) == 1)
				verticesAdjacentes.add(vertices.get(j));
		}
		return verticesAdjacentes.stream().mapToInt(vAdj -> vAdj).toArray();
	}

	@Override
	public int[] getVerticesIncidentes(int v) {
		ArrayList<Integer> verticesIncidentes = new ArrayList<>();
		int j = vertices.indexOf(v);
		for (int i = 0; i < matriz.size(); i++) {
			if (matriz.get(i).get(j) == 1)
				verticesIncidentes.add(vertices.get(i));
		}
		return verticesIncidentes.stream().mapToInt(vAdj -> vAdj).toArray();
	}

	@Override
	public Grafo getBackup() {
		Grafo backup = new GrafoMatriz();
		for (Integer v : vertices) {
			backup.addVertice(v);
		}
		for (int i = 0; i < vertices.size(); i++) {
			for (int j = 0; j < vertices.size(); j++) {
				if (matriz.get(i).get(j) == 1)
					backup.addAresta(vertices.get(i), vertices.get(j));
			}
		}
		return backup;
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
