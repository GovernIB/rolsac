package org.ibit.rol.sac.model;

import java.util.HashMap;

import org.ibit.rol.sac.model.ws.ArchivoTransferible;
import org.ibit.rol.sac.model.ws.FichaTransferible;
import org.ibit.rol.sac.model.ws.TraduccionFichaTransferible;

/**
 * Created by IntelliJ IDEA.
 * User: mgonzalez
 * Date: 04-jun-2007
 * Time: 13:29:51
 * Bean que contiene los atributos proprios de una FichaRemota(PORMAD)
 */
public class FichaRemota extends Ficha implements Remoto{
    
	private static final long serialVersionUID = 1L;

	private AdministracionRemota administracionRemota;
	private Long idExterno;
	private String urlRemota;
	private String responsable;
	
    public AdministracionRemota getAdministracionRemota() {
        return administracionRemota;
    }

    public void setAdministracionRemota(AdministracionRemota administracionRemota) {
        this.administracionRemota = administracionRemota;
    }


    public Long getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(Long idExterno) {
        this.idExterno = idExterno;
    }


    public String getUrlRemota() {
        return urlRemota;
    }

    public void setUrlRemota(String urlRemota) {
        this.urlRemota = urlRemota;
    }
    
    public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public void rellenar(final FichaTransferible fichaTransferible){
		this.setIdExterno(fichaTransferible.getId());
		this.setFechaCaducidad(fichaTransferible.getFechaCaducidad());
		this.setFechaActualizacion(fichaTransferible.getFechaActualizacion());
        this.setFechaPublicacion(fichaTransferible.getFechaPublicacion());
		this.setValidacion(fichaTransferible.getValidacion());
		this.setResponsable(fichaTransferible.getResponsable());
		
		//Trasformo los ArchivoTransferible contenidos en Archivo
		this.setIcono(Archivo.generar(fichaTransferible.getIcono()));
		this.setImagen(Archivo.generar(fichaTransferible.getImagen()));
		this.setBaner(Archivo.generar(fichaTransferible.getBaner()));
		this.setUrlRemota(fichaTransferible.getUrlRemota());
		this.setInfo(fichaTransferible.getInfo());
        //Relleno las traducciones
		final HashMap<String, Traduccion> traducciones = new HashMap<String, Traduccion>();
        if (fichaTransferible.getTraducciones() != null) {
            for (final TraduccionFichaTransferible traduccion : fichaTransferible.getTraducciones()){
                if (traduccion != null) {
                    final TraduccionFicha temp =  new TraduccionFicha();
                    temp.setTitulo(traduccion.getTitulo());
                    temp.setDescripcion(traduccion.getDescripcion());
                    temp.setDescAbr(traduccion.getDescAbr());
                    temp.setUrl(traduccion.getUrl());
                    traducciones.put(traduccion.getCodigoEstandarIdioma().toLowerCase(), temp);
                }
            }
        }
        this.setTraduccionMap(traducciones);
	}
}
