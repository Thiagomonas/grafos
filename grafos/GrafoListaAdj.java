package grafos;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class GrafoListaAdj implements Grafo {
	private Map<Integer, ArrayList<Integer>> listaAdj;
	
	public GrafoListaAdj() {
		listaAdj = new HashMap<>();
	}

	@Override
	public void addAresta(int v1, int v2) {
		ArrayList<Integer> vertices = listaAdj.get(v1);
		if (vertices != null) {
			if (vertices.contains(v2))
				return;
			vertices.add(v2);
			listaAdj.put(v1, vertices);
		} else {
			ArrayList<Integer> v2Array = new ArrayList<>();
			v2Array.add(v2);
			listaAdj.put(v1, v2Array);
		}
		vertices = listaAdj.get(v2);
		if (vertices != null) {
			if (vertices.contains(v1))
				return;
			vertices.add(v1);
			listaAdj.put(v2, vertices);
		} else {
			ArrayList<Integer> v1Array = new ArrayList<>();
			v1Array.add(v1);
			listaAdj.put(v2, v1Array);
		}
	}

	@Override
	public int grau(int v) {
		int grau = 0;
		for (Map.Entry<Integer, ArrayList<Integer>> par : listaAdj.entrySet()) {
			if (par.getValue().contains(v)) {
				grau++;
			}
		}
		return grau;
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		for (Map.Entry<Integer, ArrayList<Integer>> par : listaAdj.entrySet()) {
			res.append(par.getKey()).append(" -> ");
			ArrayList<Integer> vertices = par.getValue();
			for (int i = 0; i < vertices.size() - 1; i++) {
				res.append(vertices.get(i)).append(", ");
			}
			res.append(vertices.getLast()).append("\n");
		}
		return res.toString();
	}
}
