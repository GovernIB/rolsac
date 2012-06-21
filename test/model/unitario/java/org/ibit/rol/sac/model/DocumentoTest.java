package org.ibit.rol.sac.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class DocumentoTest {

	@Test
	public void compruebaStringDocumentoSinArchivo() {
		Documento doc = new Documento();
		String str = doc.toString();
		assertFalse(str.contains("archivo=..."));
		assertFalse(str.contains("Archivo"));
		assertTrue(str.contains("archivo=null"));
	}
	
	@Test
	public void compruebaStringDocumentoConArchivoEnCatala() {
		Documento doc = new Documento();
		TraduccionDocumento tradoc =new TraduccionDocumento();
		Archivo archivo = new Archivo();
		String nom="archivo de prueba.txt";
		Long peso=2222L; 
		archivo.setNombre(nom);
		archivo.setPeso(peso);
		tradoc.setArchivo(archivo);
		doc.setTraduccion("ca", tradoc);
		
		String str = doc.toString();
		assertFalse(str.contains("archivo=..."));
		assertFalse(str.contains("archivo=null"));
		assertTrue(str.contains(nom));
		assertTrue(str.contains(peso.toString()));
		
	}
		
	
	@Test
	public void compruebaStringDocumentoConArchivoEnCastellaSolamente() {
		Documento doc = new Documento();
		TraduccionDocumento tradoc =new TraduccionDocumento();
		Archivo archivo = new Archivo();
		String nom="archivo de prueba.txt";
		Long peso=2222L; 
		archivo.setNombre(nom);
		archivo.setPeso(peso);
		tradoc.setArchivo(archivo);
		doc.setTraduccion("es", tradoc);
		
		String str = doc.toString();
		assertFalse(str.contains("archivo=..."));
		assertTrue(str.contains("archivo=null"));
		
	}
}
