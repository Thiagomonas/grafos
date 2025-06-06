package grafos;

public class GrafoMatriz implements Grafo {
	private int[][] matriz;
	private int numVertices;
	
	public GrafoMatriz(int numVertices) {
		this.numVertices = numVertices;
		matriz = new int[numVertices][numVertices];
	}

	@Override
	public void addAresta(int v1, int v2) {
		if (v1 < 1 || v2 < 1 || v1 > numVertices || v2 > numVertices) {
			throw new IndexOutOfBoundsException();
		}
		matriz[v1 - 1][v2 - 1] = 1;
		matriz[v2 - 1][v1 - 1] = 1;
	}

	@Override
	public void removerAresta(int v1, int v2) {
		if (v1 < 1 || v2 < 1 || v1 > numVertices || v2 > numVertices) {
			throw new IndexOutOfBoundsException();
		}
		matriz[v1 - 1][v2 - 1] = 0;
		matriz[v2 - 1][v1 - 1] = 0;
	}

	@Override
	public int getNumVertices() {
		return numVertices;
	}

	@Override
	public int getNumArestas() {
		int numArestas = 0;
		for (int i = 0; i < numVertices; i++) {
			for (int j = i; j < numVertices; j++) {
				if (matriz[i][j] == 1) {
					numArestas++;
				}
			}
		}
		return numArestas;
	}

	@Override
	public int grau(int v) {
		if (v < 1 || v > numVertices) {
			throw new IndexOutOfBoundsException();
		}
		int grau = 0;
		for (int i = 0; i < numVertices; i++) {
			if (matriz[i][v - 1] == 1) {
				grau++;
			}
		}
		return grau;
	}
	
	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("   ");
		for (int i = 0; i < numVertices; i++) {
			res.append(i + 1).append("  ");
		}
		res.append("\n");
		for (int i = 0; i < numVertices; i++) {
			res.append(i + 1).append("  ");
			for (int j = 0; j < numVertices; j++) {
				res.append(matriz[i][j]).append("  ");
			}
			res.append("\n");
		}
		return res.toString();
	}
}
