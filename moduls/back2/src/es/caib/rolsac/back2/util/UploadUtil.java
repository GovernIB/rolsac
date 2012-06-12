package es.caib.rolsac.back2.util;

import java.io.File;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.ibit.rol.sac.model.Archivo;

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
	
	/**
	 * Convierte el objeto FileItem al objeto Archivo.
	 * @param archivo Objeto Archivo.
	 * @param fileItem Objeto FileItem.
	 * @return Objeto Archivo.
	 */
	public static Archivo obtenerArchivo(Archivo archivo, FileItem fileItem) {
		if (archivo == null)
			archivo = new Archivo();
		
		
		String nombre = fileItem.getName();
		
		//Retiramos posible ruta incluida en el nombre
		int sep = nombre.lastIndexOf('\\');
		if (sep < 0)
			sep = nombre.lastIndexOf('/');
				
		nombre = nombre.substring(sep + 1);
		
		archivo.setMime(fileItem.getContentType());
		archivo.setPeso(fileItem.getSize());
		archivo.setNombre(nombre);        			
		archivo.setDatos(fileItem.get());
		return archivo;
	}	

}
