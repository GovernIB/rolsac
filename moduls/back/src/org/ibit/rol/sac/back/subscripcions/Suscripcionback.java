package org.ibit.rol.sac.back.subscripcions;

import java.util.Hashtable;

import org.ibit.rol.sac.model.EnvioSuscripcion;
import org.ibit.rol.sac.model.Suscriptor;

/**
 * Clase básica de los sites. 
 * Contiene variables estáticas que se utilizan por toda la aplicación
 * @author vroca
 *
 */

public class Suscripcionback {

	static public String RESOURCE_HOST = "www.caib.es";

	static public String SUSCRIPCION_PORTAL = "SUSPOR";

	
	static public Hashtable CANALES = new Hashtable();
	static {
		CANALES.put(EnvioSuscripcion.CANAL_MENSAJE, "Missatge");
		CANALES.put(EnvioSuscripcion.CANAL_SMS, "SMS");
	}
	
	static public Hashtable ESTADOS_ENVIO = new Hashtable();
	static {
		ESTADOS_ENVIO.put(EnvioSuscripcion.EJECUTADO, "Enviat");
		ESTADOS_ENVIO.put(EnvioSuscripcion.GENERACION, "Generant-se");
		ESTADOS_ENVIO.put(EnvioSuscripcion.PENDIENTE, "Pendent d'enviament");
	}
	
	static public Hashtable TIPO_ENVIO = new Hashtable();
	static {
		TIPO_ENVIO.put(EnvioSuscripcion.TIPO_ALERTAS, "Alertes");
		TIPO_ENVIO.put(EnvioSuscripcion.TIPO_BOLETIN, "Butlletí");
		TIPO_ENVIO.put(EnvioSuscripcion.TIPO_ESTUDIOS, "Estudis i informes");
	}

	static public Hashtable ESTADOS_SUSCRIPCION = new Hashtable();
	static {
		ESTADOS_SUSCRIPCION.put(Suscriptor.TIPO_ACTIVO, "Actiu");
		ESTADOS_SUSCRIPCION.put(Suscriptor.TIPO_PENDIENTE_ACTIVAR, "Pendent d'activar");
		ESTADOS_SUSCRIPCION.put(Suscriptor.TIPO_BAJA, "Baixa");
	}

	static public Hashtable TIPO_ENTIDAD = new Hashtable();
	static {
		TIPO_ENTIDAD.put(Suscriptor.TIPO_PARTICULAR, "Particular");
		TIPO_ENTIDAD.put(Suscriptor.TIPO_ENTIDAD_PUBLICA, "Entitat Pública");
		TIPO_ENTIDAD.put(Suscriptor.TIPO_ENTIDAD_PRIVADA, "Entitat Privada");
	}
	
}
