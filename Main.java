import grafos.*;
import percurso.CaminhoBFS;
import percurso.CaminhoDFS;
import percurso.PercursoMinimo;

public class Main {
	public static void main(String[] args) {
		System.out.println("Teste Grafo Matriz:");
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

		System.out.println(grafoM);
		System.out.println(grafoM.representacaoFormal());

		int[] a = grafoM.getCicloEuleriano(1);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + ", ");
		}
		System.out.println("\n");

		System.out.println("Teste Grafo Lista de Adjacência:");
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

		System.out.println("Teste Ciclo Eulerino:");
		int[] b = grafoL.getCicloEuleriano(13);
		for (int i = 0; i < a.length; i++) {
			System.out.print(b[i] + ", ");
		}
		System.out.println("\n");

		System.out.println("Teste Grafo Valorado:");
		GrafoListaAdjValorado grafoLV = new GrafoListaAdjValorado();
		grafoLV.addVertice(1);
		grafoLV.addVertice(2);
		grafoLV.addVertice(3);
		grafoLV.addVertice(4);

		grafoLV.addAresta(1, 2, 3);
		grafoLV.addAresta(1, 3, 2);
		grafoLV.addAresta(1, 4, 7);
		grafoLV.addAresta(2, 3, 5);
		grafoLV.addAresta(2, 4, 9);
		grafoLV.addAresta(3, 4, 6);
		System.out.println(grafoLV);

		System.out.println("Teste Ciclo Hamiltoniano:");
		int[] c = grafoLV.getCicloHamiltoniano(1);
		for (int i = 0; i < c.length; i++) {
			System.out.print(c[i] + ", ");
		}
		System.out.println("\n");

		System.out.println("teste caminho DFS:");
		CaminhoDFS caminhoDFS = grafoL.DFS_Cormen();
		System.out.println(caminhoDFS);

		System.out.println("Teste ciclo por DFS:");
		int[] ciclo = grafoL.DFS_Ciclo(5);
		for (int i = 0; i < ciclo.length - 1; i++) {
			System.out.print(ciclo[i] + " -> ");
		}
		System.out.println(ciclo[ciclo.length - 1] + "\n");

		System.out.println("Teste caminho BFS:");
		CaminhoBFS caminhoBFS = grafoL.BFS_Cormen(2);
		System.out.println(caminhoBFS);

		System.out.println("Teste distância entre dois vértice com BFS:");
		int v1 = 2, v2 = 7;
		int dist = grafoL.BFS_dist(v1, v2);
		System.out.println("Distância entre " + v1 + " e " + v2 + " = " + dist + "\n");

		System.out.println("Digrafo Valorado:");
		GrafoValorado grafoV = new DigrafoListaAdjValorado();
		grafoV.addVertice(1);
		grafoV.addVertice(2);
		grafoV.addVertice(3);
		grafoV.addVertice(4);

		grafoV.addAresta(1, 2, 3);
		grafoV.addAresta(1, 3, 2);
		grafoV.addAresta(1, 4, 7);
		grafoV.addAresta(2, 3, 5);
		grafoV.addAresta(2, 4, 9);
		grafoV.addAresta(3, 4, 6);
		System.out.println(grafoV);

		System.out.println("Distâncias com algoritmo Bellman-Ford:");
		PercursoMinimo percursoMinimo = grafoV.bellmanFord(1);
		for (int i = 1; i < percursoMinimo.distancias.length; i++) {
			System.out.print("d[" + i + "] = " + percursoMinimo.distancias[i] + " ");
			System.out.println("pi[" + i + "] = " + percursoMinimo.pi[i] + " ");
		}

		System.out.println("\nAlgoritmo Floyd-Marshall:");
		percursoMinimo = grafoV.floydWarshall();
		for (int i = 1; i < percursoMinimo.matrizDist.length; i++) {
			System.out.print("[ ");
			for (int j = 1; j < percursoMinimo.matrizDist[i].length; j++) {
				System.out.print(percursoMinimo.matrizDist[i][j] + " ");
			}
			System.out.print("] ");
			System.out.println("pi[" + i + "] = " + percursoMinimo.pi[i] + " ");
		}
	}
}
