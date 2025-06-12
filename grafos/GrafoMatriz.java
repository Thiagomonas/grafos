package grafos;

import java.util.ArrayList;

public class GrafoMatriz implements Grafo {
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

	@Override
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

	@Override
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

	@Override
	public double densidade() {
//        Calcula a densidade do grafo de acorda com a fórmula (2 * A) / V
		return (double) (2 * getNumArestas()) / getNumVertices();
	}

	@Override
	public boolean haCicloEuleriano() {
//		Verifica se todos os vertices possue grau par, caso sim há um ciclo euleriano
		for (Integer v : vertices) {
			if (grau(v) % 2 != 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean haPercursoEuleriano() {
//		Verifica se há um percurso aberto ou fechado euleriano, permitindo 0 ou 2 vértices de grau impar
		int contagemGrauImpar = 0;
		for (Integer v: vertices) {
			if (grau(v) % 2 != 0) {
				contagemGrauImpar++;
			}
		}
		return contagemGrauImpar == 0 || contagemGrauImpar == 2;
	}

	public void buscaEmProfundidade(int vertice, ArrayList<Integer> verticesVisitados) {
//		Percorre o grafo sem repetir os vértices já visitados
		verticesVisitados.add(vertice);
		for (int j = 0; j < vertices.size(); j++) {
			int i = vertices.indexOf(vertice);
			if (matriz.get(i).get(j) == 1 && !verticesVisitados.contains(vertices.get(j))) {
				buscaEmProfundidade(vertices.get(j), verticesVisitados);
			}
		}
	}

	protected int getNumComponentesConexos() {
//		Conta o número de componentes conexos presentes no gráfico
		ArrayList<Integer> verticesVisitados = new ArrayList<>();
		int componentesConexos = 0;
		for (Integer v : vertices) {
			if (!verticesVisitados.contains(v)) {
				buscaEmProfundidade(v, verticesVisitados);
				componentesConexos++;
			}
		}
		return componentesConexos;
	}

	protected boolean ehPonte(int v1, int v2) {
//		Dado uma aresta que conecte v1 a v2, verifica se essa aresta é uma ponte
//		caso sua ausência aumente o número de componentes conexos
		int componentesConexosAntes = getNumComponentesConexos();
		removerAresta(v1, v2);
		int componentesConexosDepois = getNumComponentesConexos();
		addAresta(v1, v2);
        return componentesConexosDepois > componentesConexosAntes;
    }

	protected ArrayList<Integer> getVerticesAdjacentes(int v) {
//		Varre os vértices adjacentes e retorna uma lista os contendo
		ArrayList<Integer> verticesAdjacentes = new ArrayList<>();
		int i = vertices.indexOf(v);
		for (int j = 0; j < vertices.size(); j++) {
			if (matriz.get(i).get(j) == 1) {
				verticesAdjacentes.add(vertices.get(j));
			}
		}
		return verticesAdjacentes;
	}

	@Override
	public int[] getCicloEuleriano(int verticeInicial) {
//		Utiliza o algoritmo de Fleury para retornar um ciclo euleriano no grafo
		if (!haCicloEuleriano() || !vertices.contains(verticeInicial))
			return null;
		ArrayList<ArrayList<Integer>> matrizBackup = new ArrayList<>(matriz);
		int[] cicloEuleriano = new int[getNumArestas() + 1];
		cicloEuleriano[0] = verticeInicial;
		int verticeAtual = verticeInicial;
		int numArestas = 0;
		int numArestasTotal = getNumArestas();
		while (numArestas < numArestasTotal) {
			ArrayList<Integer> verticesAdj = getVerticesAdjacentes(verticeAtual);
			int i = 0;
			for (Integer v : verticesAdj) {
				if (!ehPonte(verticeAtual, v) || verticesAdj.size() - i == 1) {
					cicloEuleriano[++numArestas] = v;
					removerAresta(verticeAtual, v);
					verticeAtual = v;
					break;
				}
				i++;
			}
		}
		matriz = matrizBackup;
		return cicloEuleriano;
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
