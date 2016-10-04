package es.caib.rolsac.api.v2.general;

//IMPORT INCORRECTO: import org.jboss.axis.AxisProperties;
import org.apache.axis.AxisProperties;

public class CertificadoUtil
{
	private static Boolean autentificado = false;
	
	public static void autentificar(String password, String keyStore)
	{
		if (autentificado == false) {
			AxisSSLSocketFactory.setKeystorePassword(password);
			AxisSSLSocketFactory.setResourcePathToKeystore(keyStore);
			AxisProperties.setProperty("axis.socketSecureFactory", "es.caib.rolsac.api.v2.general.AxisSSLSocketFactory");
			autentificado = true;
		}
	}
	
}
