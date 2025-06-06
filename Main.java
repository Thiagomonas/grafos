import grafos.GrafoListaAdj;
import grafos.GrafoMatriz;

public class Main {
	public static void main(String[] args) {
		GrafoMatriz grafoM = new GrafoMatriz(5);
		grafoM.addAresta(1, 1);
		grafoM.addAresta(2, 3);
		grafoM.addAresta(3, 4);
		grafoM.addAresta(4, 1);
		System.out.println(grafoM);

		System.out.println(grafoM.grau(1));
		System.out.println(grafoM.getNumArestas() + "\n");

		GrafoListaAdj grafoL = new GrafoListaAdj();
		grafoL.addAresta(1, 2);
		grafoL.addAresta(2, 3);
		grafoL.addAresta(4, 3);
		grafoL.addAresta(1, 1);
		System.out.println(grafoL);

		System.out.println(grafoL.grau(1));
		System.out.println(grafoL.getNumArestas() + "\n");
	}
}
