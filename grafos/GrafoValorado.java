package grafos;

public interface GrafoValorado {
    int addVertice(int v);
    int removerVertice(int v);
    int addAresta(int v1, int v2, int valor);
    int removerAresta(int v1, int v2);
    int mudarValorAresta(int v1, int v2, int valor);
    int getNumVertices();
    int getNumArestas();
    int getValorAresta(int v1, int v2);
    int[] getCicloHamiltoniano(int verticeInicial);
    String toString();
    String representacaoFormal();
}
