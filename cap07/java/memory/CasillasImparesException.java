package memory;

import static memory.Mensajes.EXC_CASILLAS_IMPARES;

public class CasillasImparesException extends DimensionesException {
	
	private static final long serialVersionUID = 481071844090885888L;

	@Override
	public String getMessage() {
		return EXC_CASILLAS_IMPARES;
	}
}
