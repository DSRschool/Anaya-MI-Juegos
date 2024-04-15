package memory;

import static memory.Mensajes.EXC_SIMBOLOS_INSUFICIENTES;

public class SimbolosInsuficientesException extends DimensionesException {

	private static final long serialVersionUID = -5697386614085603580L;

	@Override
	public String getMessage() {
		return EXC_SIMBOLOS_INSUFICIENTES;
	}
}
