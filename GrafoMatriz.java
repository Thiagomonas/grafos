
public class GrafoMatriz implements Grafo {
	private int[][] matriz;
	private int numVertices;
	
	public GrafoMatriz(int numVertices) {
		this.numVertices = numVertices;
		matriz = new int[numVertices][numVertices];
	}

	@Override
	public void addAresta(int v1, int v2) {
		matriz[v1][v2] = 1;
		matriz[v2][v1] = 1;
	}
	
	public String toString() {
		for (int i = 0; i < numVertices; i++) {
			for (int j = 0; j < numVertices; j++) {
				System.out.println(numVertices);
			}
		}
	}
}
