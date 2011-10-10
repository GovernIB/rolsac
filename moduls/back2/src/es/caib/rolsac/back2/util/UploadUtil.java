package es.caib.rolsac.back2.util;

import java.io.File;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadUtil {

	//TODO: obtener de un fichero de propiedades externo
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 10; //10Mb
	private static final int SIZE_THRESHOLD = 1024 * 4; //4 Kb
	private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
	
	/**
	 * Crea un objeto ServleFileUpload para obtener un formulario multipart con upload de ficheros.
	 * @return Objeto ServletFileUpload inicializado.
	 */
	public static ServletFileUpload obtenerServletFileUpload() {
		
		DiskFileItemFactory factory = new DiskFileItemFactory();    		
		factory.setSizeThreshold(SIZE_THRESHOLD);    		
		factory.setRepository(new File(TEMP_DIR));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(MAX_FILE_SIZE);
		
		return upload;
	}	

}
