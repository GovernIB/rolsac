package test.integracion.model.common;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import junit.framework.TestCase;

public abstract class ModelTestCase extends TestCase {


	protected String leerJbossServiceXMLComoString() throws IOException {
		File file=obtenerFile();
		String contents=FileUtils.readFileToString(file);
		return contents;
	
	}

	private File obtenerFile() {
		String homeDir=obtenerHomeDir();
		return new File(homeDir+"/"+"jboss-service.xml");
	}

	private String obtenerHomeDir() {
		return System.getProperty("home.dir"); 
	}

}