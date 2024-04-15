package memory;

import java.awt.Color;

public enum Simbolo {
	A(Color.RED), B(Color.GREEN), C(Color.BLUE), D(Color.CYAN), 
	E(Color.ORANGE), F(Color.MAGENTA), G(Color.GRAY), H(Color.PINK);
	
	private Color color;
	
	Simbolo(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
}
