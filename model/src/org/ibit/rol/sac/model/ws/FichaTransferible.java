package org.ibit.rol.sac.model.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.TraduccionFicha;

/**
 * Created by IntelliJ IDEA.
 * User: mgonzalez
 * Date: 13-jun-2007
 * Time: 15:02:24
 * Clase que representa la información a transferir de una Ficha(PORMAD)
 */
public class FichaTransferible implements Serializable {

	
	//Ficha
    private Long id;
    private Date fechaCaducidad;
    private Date fechaActualizacion;
    private Date fechaPublicacion;
    private ArchivoTransferible icono;
    private ArchivoTransferible imagen;
    private ArchivoTransferible baner;
    private Integer validacion;
    private String info;
    private String[] codigoEstandarMaterias;
    private String[] codigoEstandarHV;
    private TraduccionFichaTransferible[] traducciones;
    private String urlRemota;
    private FichaUATransferible[] fichasUA;
    private String responsable;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public ArchivoTransferible getIcono() {
        return icono;
    }

    public void setIcono(ArchivoTransferible icono) {
        this.icono = icono;
    }

    public ArchivoTransferible getImagen() {
        return imagen;
    }

    public void setImagen(ArchivoTransferible imagen) {
        this.imagen = imagen;
    }

    public ArchivoTransferible getBaner() {
        return baner;
    }

    public void setBaner(ArchivoTransferible baner) {
        this.baner = baner;
    }

    public Integer getValidacion() {
        return validacion;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setValidacion(Integer validacion) {
        this.validacion = validacion;
    }
    //Ficha


    public String[] getCodigoEstandarMaterias() {
        return codigoEstandarMaterias;
    }

    public void setCodigoEstandarMaterias(String[] codigoEstandarMaterias) {
        this.codigoEstandarMaterias = codigoEstandarMaterias;
    }

    public String[] getCodigoEstandarHV() {
        return codigoEstandarHV;
    }

    public void setCodigoEstandarHV(String[] codigoEstandarHV) {
        this.codigoEstandarHV = codigoEstandarHV;
    }

    public TraduccionFichaTransferible[] getTraducciones() {
        return traducciones;
    }

    public void setTraducciones(TraduccionFichaTransferible[] traducciones) {
        this.traducciones = traducciones;
    }

    public String getUrlRemota() {
        return urlRemota;
    }

    public void setUrlRemota(String urlRemota) {
        this.urlRemota = urlRemota;
    }

	public FichaUATransferible[] getFichasUA() {
		return fichasUA;
	}

	public void setFichasUA(FichaUATransferible[] fichasUA) {
		this.fichasUA = fichasUA;
	}

	
	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}


	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	@SuppressWarnings("unchecked")
	public void rellenar(final Ficha ficha){
		this.setId(ficha.getId());

        this.setFechaCaducidad(ficha.getFechaCaducidad());
        this.setFechaActualizacion(ficha.getFechaActualizacion());
        this.setFechaPublicacion(ficha.getFechaPublicacion());
		this.setValidacion(ficha.getValidacion());
        this.setInfo(ficha.getInfo());
        this.setResponsable(ficha.getResponsable());
		//Trasformo los ArchivoTransferible contenidos en Archivo
		this.setIcono(ArchivoTransferible.generar(ficha.getIcono()));
		this.setImagen(ArchivoTransferible.generar(ficha.getImagen()));
		this.setBaner(ArchivoTransferible.generar(ficha.getBaner()));

		//Relleno los campos con sus Codigos Estandar
		if(ficha.getMaterias()!=null){
			final List<String> materias = new ArrayList<String>();
			for(final Materia materia : (Collection<Materia>)ficha.getMaterias()){
				materias.add(materia.getCodigoEstandar());
			}
			this.setCodigoEstandarMaterias(materias.toArray(new String[0]));
		}
		
        if(ficha.getHechosVitales()!=null){
	        final List<String> hechos = new ArrayList<String>();
			for(final HechoVital hecho : (Collection<HechoVital>)ficha.getHechosVitales()){
				hechos.add(hecho.getCodigoEstandar());
			}
			this.setCodigoEstandarHV(hechos.toArray(new String[0]));
        }
        
        String url = ResourceBundle.getBundle("url").getString("urlFicha");
		this.setUrlRemota(url.replaceAll("%id%", ficha.getId().toString()));
		
        //Relleno las traducciones
        final List<TraduccionFichaTransferible> traducciones = new ArrayList<TraduccionFichaTransferible>(); 
		for (final String idioma : (Collection<String>)ficha.getLangs()){
            final TraduccionFicha traduccion = (TraduccionFicha)ficha.getTraduccion(idioma);
            if(traduccion!=null){
	            final TraduccionFichaTransferible temp =  new TraduccionFichaTransferible();
	            temp.setTitulo(traduccion.getTitulo());
				temp.setDescripcion(traduccion.getDescripcion());
				temp.setDescAbr(traduccion.getDescAbr());
				temp.setUrl(traduccion.getUrl());
	
				temp.setCodigoEstandarIdioma(idioma);
				traducciones.add(temp);
            }
		}
		this.setTraducciones(traducciones.toArray(new TraduccionFichaTransferible[0]));
    }
	
	public static FichaTransferible generar(Ficha ficha){
		FichaTransferible fichaTransferible =  new FichaTransferible();
		if(ficha!=null){
			fichaTransferible.rellenar(ficha);
		}
		return fichaTransferible;
	}
}
