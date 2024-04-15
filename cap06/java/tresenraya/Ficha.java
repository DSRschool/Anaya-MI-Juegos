package tresenraya;

import java.awt.Color;

public enum Ficha {
	X(Color.RED), O(Color.GREEN);
	
	private Color color;
	
	Ficha (Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}

	public Ficha siguiente() {
		switch (this) {
		case X: 
			return O;
		case O:
			return X;
		default: 
			return null; // caso imposible
		}
	}
}
