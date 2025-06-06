package grafos;

public interface Grafo {
	public void addAresta(int v1, int v2);
	public void removerAresta(int v1, int v2);
	int getNumVertices();
	int getNumArestas();
	public int grau(int v);
}
