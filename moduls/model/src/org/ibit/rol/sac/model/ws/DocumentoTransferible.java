package org.ibit.rol.sac.model.ws;

import org.ibit.rol.sac.model.Iniciacion;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.Documento;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: mgonzalez
 * Date: 28-oct-2010
 * Time: 10:17:02
 * To change this template use File | Settings | File Templates.
 */
public class DocumentoTransferible extends ActuacionTransferible implements Serializable{

    private Long id;

	private int tipus;

	private Long orden;

	private String titulo;
    private String descripcion;
    private ArchivoTransferible archivo;
	
	private TraduccionDocumentoTransferible[] traducciones;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public int getTipus() {
		return tipus;
	}

	public void setTipus(int tipus) {
		this.tipus = tipus;
	}

	public Long getOrden() {
		return orden;
	}

	public void setOrden(Long orden) {
		this.orden = orden;
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public ArchivoTransferible getArchivo() {
		return archivo;
	}
	public void setArchivo(ArchivoTransferible archivo) {
		this.archivo = archivo;
	}

	public TraduccionDocumentoTransferible[] getTraducciones() {
		return traducciones;
	}
	public void setTraducciones(TraduccionDocumentoTransferible[] traducciones) {
		this.traducciones = traducciones;
	}



    public void rellenar(final Documento documento){

    	if(documento!=null){
        	this.setId(documento.getId());
        	this.setOrden(documento.getOrden());

    		 //Relleno las traducciones
    		final List<TraduccionDocumentoTransferible> traducciones = new ArrayList<TraduccionDocumentoTransferible>();
    		for (final String idioma : documento.getLangs()){
                final TraduccionDocumento traduccion = (TraduccionDocumento)documento.getTraduccion(idioma);
                if(traduccion!=null){
                    final TraduccionDocumentoTransferible temp =  new TraduccionDocumentoTransferible();
                    temp.setTitulo(traduccion.getTitulo());
                    temp.setDescripcion(traduccion.getDescripcion());
                    temp.setCodigoEstandarIdioma(idioma);
                    temp.setArchivoTransferible(ArchivoTransferible.generar(traduccion.getArchivo()));
                    traducciones.add(temp);
                }
            }
    		this.setTraducciones(traducciones.toArray(new TraduccionDocumentoTransferible[0]));

    	}

    }

	public static IniciacionTransferible generar( Iniciacion iniciacion )
    {
		IniciacionTransferible iniT = new IniciacionTransferible();
    	if( iniciacion != null )
    	{
    		iniT.rellenar( iniciacion );
    	}
    	return iniT;
    }

	public static DocumentoTransferible generar(Documento documento){
    	DocumentoTransferible documentoTransferible = new DocumentoTransferible();
    	if(documento!=null){
    		documentoTransferible.rellenar(documento);
    	}
    	return documentoTransferible;
    }
}
