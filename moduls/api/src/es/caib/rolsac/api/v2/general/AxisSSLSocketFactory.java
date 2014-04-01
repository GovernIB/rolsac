package es.caib.rolsac.api.v2.general;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Hashtable;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import org.apache.axis.components.net.JSSESocketFactory;
import org.apache.axis.components.net.SecureSocketFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AxisSSLSocketFactory extends JSSESocketFactory implements SecureSocketFactory
{
	private static Log logger = LogFactory.getLog(AxisSSLSocketFactory.class);
	private static String MY_KEYSTORE_PASSWORD;
	// local keystore file (contains the self-signed certificate from the server)
	private static String RESOURCE_PATH_TO_KEYSTORE;
	
	
	public static String getKeystorePassword()
	{
		return MY_KEYSTORE_PASSWORD;
	}
	
	public static void setKeystorePassword(String keystorePassword)
	{
		MY_KEYSTORE_PASSWORD = keystorePassword;
	}
	
	public static String getResourcePathToKeystore()
	{
		return RESOURCE_PATH_TO_KEYSTORE;
	}
	
	public static void setResourcePathToKeystore(String resourcePathToKeystore)
	{
		RESOURCE_PATH_TO_KEYSTORE = resourcePathToKeystore;
	}
	
	public AxisSSLSocketFactory(Hashtable attributes)
	{
		super(attributes);
	}
	
	/**
	 * Read the keystore, init the SSL socket factory
	 * This overrides the parent class to provide our SocketFactory
	 * implementation.
	 * 
	 * @throws IOException
	 */
	protected void initFactory() throws IOException
	{
		try {
			SSLContext context = getContext();
			sslFactory = context.getSocketFactory();
		} catch (Exception e) {
			if (e instanceof IOException) {
				throw (IOException) e;
			}
			throw new IOException(e.getMessage());
		}
	}
	
	/**
	 * Gets a custom SSL Context. This is the main working of this class. The
	 * following are the steps that make up our custom configuration:
	 * 
	 * 1. Open our keystore file using the password provided
	 * 2. Create a KeyManagerFactory and TrustManagerFactory using this file
	 * 3. Initialise a SSLContext using these factories
	 */
	protected SSLContext getContext()
	{
		char[] keystorepass = MY_KEYSTORE_PASSWORD.toCharArray();
		if (StringUtils.isBlank(new String(keystorepass)))
			logger.error("Could not read password for configured keystore!");
		
		InputStream keystoreFile = Thread.currentThread().getContextClassLoader().getResourceAsStream(RESOURCE_PATH_TO_KEYSTORE);
		if (keystoreFile == null)
			logger.error("Could not read the configured keystore file at " + RESOURCE_PATH_TO_KEYSTORE);
		
		try {
			// create required keystores and their corresponding manager objects
			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			keyStore.load(keystoreFile, keystorepass);
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(keyStore, keystorepass);
			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(keyStore);
			// congifure a local SSLContext to use created keystores
			SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), new SecureRandom());
			return sslContext;
		} catch (Exception e) {
			logger.error("Error creating context for SSLSocket!");
		}
		return null;
	}
	
}