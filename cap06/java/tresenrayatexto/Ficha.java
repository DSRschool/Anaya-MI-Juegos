package tresenrayatexto;

public enum Ficha {
	X, O;

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
