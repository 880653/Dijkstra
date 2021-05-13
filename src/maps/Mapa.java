package maps;
import java.util.List;

public interface Mapa {
	
	// Grafoaren erpin kopurua itzultzen du
	public int numVertices();
	
	// Grafoaren arku kopurua itzultzen du
	public int numEdges();
	
	// Arku berria gehitzen dio grafoari
	public void addEdge(int v, int w, float distantzia);
	
	// Erpinaren auzokideak
	public List<Errepidea> adjacentsOf (int v);
	
	// Erpinaren auzokide-kopurua
	public int degree(int v);
	
	// Erpina auzokidea denen kopurua
	public int inDegree(int v);
}
