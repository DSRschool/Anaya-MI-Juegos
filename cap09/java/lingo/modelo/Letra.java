package lingo.modelo;

import static lingo.modelo.EstadoLetra.DESCONOCIDA;

import java.util.Objects;

public class Letra {
	
	private char letra;
	private EstadoLetra estado;
	
	public Letra(char letra) {
		this.letra = letra;
		estado = DESCONOCIDA;
	}

	public String getLetra() {
		return letra + "";
	}

	public EstadoLetra getEstado() {
		return estado;
	}

	public void setEstado(EstadoLetra estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(letra);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Letra other = (Letra) obj;
		return letra == other.letra;
	}
	
	@Override
	public String toString() {
		switch (estado) {
		case OK: 
			return "[" + letra + "]";
		case ENCONTRADA:
			return "(" + letra + ")";
		default:
			return " " + letra + " ";
		}
	}
}
