import grafos.GrafoMatriz;
import grafos.GrafoListaAdj;

public class Main {
	public static void main(String[] args) {
		GrafoMatriz grafoM = new GrafoMatriz();
		System.out.println(grafoM);
		grafoM.addVertice(2);
		grafoM.addVertice(1);
		grafoM.addVertice(3);
		System.out.println(grafoM);

		grafoM.addAresta(1, 2);
		grafoM.addAresta(2, 3);
		grafoM.addAresta(3, 4);
		System.out.println(grafoM);

		grafoM.removerVertice(1);
		System.out.println(grafoM);

		grafoM.addVertice(4);
		grafoM.removerAresta(3, 2);
		System.out.println(grafoM);

		GrafoListaAdj grafoL = new GrafoListaAdj();
		System.out.println(grafoL);

		grafoL.addVertice(1);
		grafoL.addVertice(2);
		grafoL.addVertice(3);
		System.out.println(grafoL);

		grafoL.addAresta(1, 2);
		grafoL.addAresta(2, 3);
		grafoL.addAresta(2, 2);
		grafoL.addAresta(3, 4);
		System.out.println(grafoL);

		grafoL.removerVertice(3);
		grafoL.removerAresta(2, 2);
		System.out.println(grafoL);

		grafoL.addVertice(4);
		grafoL.addAresta(4, 4);
		System.out.println(grafoL.representacaoFormal());
	}
}
