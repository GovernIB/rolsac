package org.ibit.rol.sac.model.ws;

public class ActuacionTransferible {

	public ActuacionTransferible() {
		super();
	}

	protected String establecerIdEnUrl(String id, String url) {
		return url.replaceAll("%id%", id);
	}
	

	protected String obtenerUrl(String propName) {
		String url = System.getProperty(propName);
		return url;
	}

}