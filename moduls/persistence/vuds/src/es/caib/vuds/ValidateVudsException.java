package es.caib.vuds;

public class ValidateVudsException extends VudsException {

	String[] campsSenseValidar;


	public ValidateVudsException(String[] campsSenseValidar) {
		super();
		this.campsSenseValidar = campsSenseValidar;
	}

	public String[] getCampsSenseValidar() {
		return campsSenseValidar;
	}

	public void setCampsSenseValidar(String[] campsSenseValidar) {
		this.campsSenseValidar = campsSenseValidar;
	}
	
}
