package grafos;

import enums.Cor;
import percurso.CaminhoDFS;

public abstract class Grafo {
	abstract int addVertice(int v);
	abstract int removerVertice(int v);
	abstract int addAresta(int v1, int v2);
	abstract int removerAresta(int v1, int v2);
	abstract int getNumVertices();
	abstract int getNumArestas();
	abstract int[] getVertices();
	abstract int[] getVerticesAdjacentes(int v);
	abstract int[] getVerticesIncidentes(int v);
	abstract Grafo getBackup();
	abstract String representacaoFormal();

	private boolean contem(int[] vertices, int vertice) {
		for (int v : vertices) {
			if (v == vertice)
				return true;
		}
		return false;
	}

	public int grau(int v) {
//		Calcula o grau de um vértice a partir do número de arestas incidentes
		int grau = getVerticesIncidentes(v).length;
		if (contem(getVerticesAdjacentes(v), v))
			grau++;
		return grau;
	}

	public boolean ehCompleto(){
//		Verifica se todos os vértices possuem n arestas para n vértices
		int[] vertices = getVertices();
        for (int v: vertices) {
            if (getVerticesAdjacentes(v).length != getNumVertices())
                return false;
        }
		return true;
	}

	public int kRegular() {
//		Verifica se todos os vértices possuem o mesmo grau e retorna tal grau
		int[] vertices = getVertices();
		int k = getVerticesAdjacentes(vertices[0]).length;
		for (int v: vertices) {
			if (getVerticesAdjacentes(v).length != k)
				return -1;
		}
		return k;
	}

	public double densidade() {
//		Calcula a densidade do grafo
		return (double) (2 * getNumArestas()) / getNumVertices();
	}

	public boolean haCicloEuleriano(){
//		Verifica se todos o vértices possuem um grau par
		int[] vertices = getVertices();
		for (int v: vertices) {
			if (grau(v) % 2 != 0)
				return false;
		}
		return true;
	}

	public boolean haPercursoEuleriano() {
//		Verifica se há 0 ou 2 vértice de grau ímpar, retornando verdadeiro também caso haja um percurso euleriano aberto
		int[] vertices = getVertices();
		int numGrauImpar = 0;
		for (int v: vertices) {
			if (grau(v) % 2 != 0)
				numGrauImpar++;
		}
		return numGrauImpar == 0 || numGrauImpar == 2;
	}

	private void buscaEmProfundidade(int verticeInicial, int[] verticesVisitados, int numVerticesVisitados) {
		int[] vAdjacentes = getVerticesAdjacentes(verticeInicial);
		for (int v: vAdjacentes) {
			if (!contem(verticesVisitados, v)) {
				verticesVisitados[numVerticesVisitados] = v;
				buscaEmProfundidade(v, verticesVisitados, numVerticesVisitados + 1);
			}
		}
	}

	public int getNumComponentesConexos() {
		int componentesConexos = 0;
		int numVerticesVisitados = 0;
		int[] verticesVisitados = new int[getNumVertices()];
		for (int v: getVertices()) {
			if (!contem(verticesVisitados, v)) {
				verticesVisitados[numVerticesVisitados] = v;
				buscaEmProfundidade(v, verticesVisitados, numVerticesVisitados + 1);
				componentesConexos++;
			}
		}
		return componentesConexos;
	}

	private boolean ehPonte(int v1, int v2) {
//      Verifica se a aresta é uma ponte caso o número de componentes conexos aumente com sua ausência
		int componentesConexosAntes = getNumComponentesConexos();
		removerAresta(v1, v2);
		int componentesConexosDepois = getNumComponentesConexos();
		addAresta(v1, v2);
		return componentesConexosDepois > componentesConexosAntes;
	}

	public int[] getCicloEuleriano(int verticeInicial) {
//		Usa o algoritmo de Fleury para encontrar um ciclo Euleriano no grafo e o retorna
		if (!haCicloEuleriano())
			return null;
		Grafo copia = getBackup();
		int[] cicloEuleriano = new int[getNumArestas() + 1];
		cicloEuleriano[0] = verticeInicial;
		int verticeAtual = verticeInicial;
		int numArestasPercorridas = 0;
		int numArestasTotal = getNumArestas();
		while (numArestasPercorridas < numArestasTotal) {
			int[] verticesAdjacentes = copia.getVerticesAdjacentes(verticeAtual);
			for (int i = 0; i < verticesAdjacentes.length; i++) {
				if (!copia.ehPonte(verticeAtual, verticesAdjacentes[i]) || i == verticesAdjacentes.length - 1) {
					cicloEuleriano[++numArestasPercorridas] = verticesAdjacentes[i];
					copia.removerAresta(verticeAtual, verticesAdjacentes[i]);
					verticeAtual = verticesAdjacentes[i];
					break;
				}
			}
		}
		return cicloEuleriano;
	}

	private int getVerticeMax() {
		int[] vertices = getVertices();
		int max = 0;
		for (int v: vertices) {
			if (v > max)
				max = v;
		}
		return max;
	}

	public CaminhoDFS DFS_Cormen() {
//		Usando o algoritmo do DFS segundo o livo do Cormen
//		Utiliza a classe CaminhoDFS para guardar as variáveis auxiliares
		int[] vertices = getVertices();
		int verticeMax = getVerticeMax() + 1;
		CaminhoDFS caminhoDFS = new CaminhoDFS(verticeMax);
		int tempo = 0;
		for (int v: vertices) {
			if (caminhoDFS.cores[v] == Cor.BRANCO) {
				DFS(v, Integer.MIN_VALUE, caminhoDFS, tempo);
			}
		}
		return caminhoDFS;
	}

	private int DFS(int v, int vIncidente, CaminhoDFS caminhoDFS, int tempo) {
		caminhoDFS.cores[v] = Cor.CINZA;
		caminhoDFS.pi[v] = vIncidente;
		caminhoDFS.d[v] = tempo + 1;
		for (Integer vAdj: getVerticesAdjacentes(v)) {
			if (caminhoDFS.cores[vAdj] == Cor.BRANCO) {
				tempo = DFS(vAdj, v, caminhoDFS, tempo + 1);
			}
		}
		caminhoDFS.cores[v] = Cor.PRETO;
		caminhoDFS.f[v] = tempo + 1;
		return tempo;
	}

	public int[] DFS_Ciclo(int verticeInicial) {
//		Adaptação do algoritmo DFS do Cormen para encontrar um ciclo no grafo
		int verticeMax = getVerticeMax() + 1;
		CaminhoDFS caminhoDFS = new CaminhoDFS(verticeMax);
		DFSc(verticeInicial, Integer.MIN_VALUE, caminhoDFS, verticeInicial);
		int[] caminhoIncompleto = caminhoDFS.getCaminho(verticeInicial);
		int[] caminho = new int[caminhoIncompleto.length + 1];
		for (int i = 0; i < caminhoIncompleto.length; i++) {
			caminho[i] = caminhoIncompleto[i];
		}
		caminho[caminhoIncompleto.length] = verticeInicial;
		return caminho;
	}

	private void DFSc(int v, int vIncidente, CaminhoDFS caminhoDFS, int vInicial) {
		caminhoDFS.cores[v] = Cor.CINZA;
		caminhoDFS.pi[v] = vIncidente;
		int[] vAdjacentes = getVerticesAdjacentes(v);
		// Encontrou um ciclo
		if (contem(vAdjacentes, vInicial)) {
			return;
		}
		for (Integer vAdj: vAdjacentes) {
			if (caminhoDFS.cores[vAdj] == Cor.BRANCO) {
				DFSc(vAdj, v, caminhoDFS, vInicial);
			}
		}
	}
}
