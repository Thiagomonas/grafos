package grafos;

public interface Grafo {
	int addVertice(int v);
	int removerVertice(int v);
	int addAresta(int v1, int v2);
	int removerAresta(int v1, int v2);
	int getNumVertices();
	int getNumArestas();
	int grau(int v);
	boolean ehCompleto();
	int kRegular();
	double densidade();
	String toString();
	String representacaoFormal();
}
