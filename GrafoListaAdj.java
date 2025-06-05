import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class GrafoListaAdj implements Grafo {
	private Map<Integer, ArrayList<Integer>> listaAdj;
	
	public GrafoListaAdj() {
		listaAdj = new HashMap<Integer, ArrayList<Integer>>();
	}

	@Override
	public void addAresta(int v1, int v2) {
		ArrayList<Integer> vertices = listaAdj.get(v1);
		if (vertices != null) {
			vertices.add(v2);
			listaAdj.put(v1, vertices);
		} else {
			listaAdj.put(v1, new ArrayList<>(v2));
		}
		vertices = listaAdj.get(v2);
		if (vertices != null) {
			vertices.add(v1);
			listaAdj.put(v2, vertices);
		} else {
			listaAdj.put(v2, new ArrayList<>(v1));
		}
	}
}
