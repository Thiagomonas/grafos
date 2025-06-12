import grafos.GrafoMatriz;
import grafos.GrafoListaAdj;

public class Main {
	public static void main(String[] args) {
		GrafoMatriz grafoM = new GrafoMatriz();
		for (int i = 0; i < 14; i++) {
			grafoM.addVertice(i + 1);
		}
		grafoM.addAresta(1, 2);
		grafoM.addAresta(1, 3);
		grafoM.addAresta(2, 4);
		grafoM.addAresta(3, 4);
		grafoM.addAresta(4, 5);
		grafoM.addAresta(4, 6);
		grafoM.addAresta(5, 6);
		grafoM.addAresta(5, 7);
		grafoM.addAresta(5, 8);
		grafoM.addAresta(6, 7);
		grafoM.addAresta(6, 9);
		grafoM.addAresta(7, 10);
		grafoM.addAresta(7, 11);
		grafoM.addAresta(8, 12);
		grafoM.addAresta(9, 10);
		grafoM.addAresta(11, 12);
		grafoM.addAresta(11, 13);
		grafoM.addAresta(11, 14);
		grafoM.addAresta(13, 14);

//		grafoM.addAresta(1, 2);
//		grafoM.addAresta(1, 3);
//		grafoM.addAresta(1, 4);
//		grafoM.addAresta(1, 8);
//		grafoM.addAresta(2, 3);
//		grafoM.addAresta(2, 5);
//		grafoM.addAresta(2, 7);
//		grafoM.addAresta(3, 5);
//		grafoM.addAresta(3, 6);
//		grafoM.addAresta(4, 6);
//		grafoM.addAresta(5, 6);
//		grafoM.addAresta(5, 7);
//		grafoM.addAresta(6, 8);
//		grafoM.addAresta(7, 8);
//		grafoM.addAresta(7, 10);
//		grafoM.addAresta(8, 9);
//		grafoM.addAresta(9, 10);

		System.out.println(grafoM);
		System.out.println(grafoM.representacaoFormal());

		int[] a = grafoM.getCicloEuleriano(1);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + ", ");
		}
		System.out.println("\n");

		GrafoListaAdj grafoL = new GrafoListaAdj();
		for (int i = 0; i < 14; i++) {
			grafoL.addVertice(i + 1);
		}
		grafoL.addAresta(1, 2);
		grafoL.addAresta(1, 3);
		grafoL.addAresta(2, 4);
		grafoL.addAresta(3, 4);
		grafoL.addAresta(4, 5);
		grafoL.addAresta(4, 6);
		grafoL.addAresta(5, 6);
		grafoL.addAresta(5, 7);
		grafoL.addAresta(5, 8);
		grafoL.addAresta(6, 7);
		grafoL.addAresta(6, 9);
		grafoL.addAresta(7, 10);
		grafoL.addAresta(7, 11);
		grafoL.addAresta(8, 12);
		grafoL.addAresta(9, 10);
		grafoL.addAresta(11, 12);
		grafoL.addAresta(11, 13);
		grafoL.addAresta(11, 14);
		grafoL.addAresta(13, 14);
		System.out.println(grafoL);
		System.out.println(grafoL.representacaoFormal());

		int[] b = grafoL.getCicloEuleriano(13);
		for (int i = 0; i < a.length; i++) {
			System.out.print(b[i] + ", ");
		}
		System.out.println("\n");
	}
}
