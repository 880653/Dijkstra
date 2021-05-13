package maps;

public class Errepidea {
	
	public final int nora;
	public final float luzera;
	
	public Errepidea(int besteTokia, float distantzia) {
		nora = besteTokia;
		luzera = distantzia;
	}
	
	public int getNora() {
		return nora;
	}

	public float getLuzera() {
		return luzera;
	}
	

	@Override
	public String toString() {
		return "(" + nora + " • " + luzera + ")";
	}

}
