package buscaminas;

import java.util.List;

public class Celda {
	private int x;
	private int y;
	private boolean esMina;
	private int contador;
	private boolean marcada;
	private Tablero tablero;
	private Casilla casilla;
	private List<Celda> contorno;
	
	public Celda(Tablero tablero, int x, int y) {
		this.tablero = tablero;
		this.x = x;
		this.y = y;
	}
	
	public void ponerMina() {
		esMina = true;
		incrementarVecinas();
	}
	
	public boolean esMina() {
		return esMina;
	}
	
	public void incrementarContador() {
		contador++;
	}
	
	public int getContador() {
		return contador;
	}
	
	public void invertirMarca() {
		marcada = !marcada;
	}
	
	public boolean estaMarcada() {
		return marcada;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Casilla getCasilla() {
		return casilla;
	}
	
	public void setCasilla(Casilla casilla) {
		this.casilla = casilla;
	}

	public void setContorno(List<Celda> contorno) {
		this.contorno = contorno;
	}
	
	public List<Celda> getContorno() {
		return contorno;
	}

	public void explotar() {
		tablero.explotar();
	}
	
	@Override
	public String toString() {
		return esMina ? "X" : contador + "";
	}
	
	private void incrementarVecinas() {
		for (Celda vecina : contorno) {
			if (!vecina.esMina()) {
				vecina.incrementarContador();
			}
		}
	}
}
