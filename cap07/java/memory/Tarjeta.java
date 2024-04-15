package memory;

public class Tarjeta {

	private Simbolo simbolo;
	private Estado estado;
	
	public Tarjeta(Simbolo simbolo) {
		this.simbolo = simbolo;
		estado = Estado.OCULTA;
	}

	public Estado getEstado() {
		return estado;
	}

	public Simbolo getSimbolo() {
		return simbolo;
	}
	
	public void emparejar() {
		estado = Estado.EMPAREJADA;
	}
	
	public void mostrar() {
		estado = Estado.VISIBLE;
	}
	
	public void ocultar() {
		estado = Estado.OCULTA;
	}
	
	@Override
	public String toString() {
		return simbolo + " " + estado;
	}
}
