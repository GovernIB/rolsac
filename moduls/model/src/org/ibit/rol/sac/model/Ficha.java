package org.ibit.rol.sac.model;

import java.util.Set;
import java.util.List;
import java.util.Date;
import java.util.Iterator;

/**
 * Bean que representa a una ficha. Modificado para (PORMAD)
 */

public class Ficha extends Classificable implements Indexable, Validable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Date fechaPublicacion;
	private Date fechaCaducidad;
	private Date fechaActualizacion;
	private String urlVideo;   
	private String urlForo;      
	private String foro_tema;
	private Archivo icono;
	private Archivo imagen;
	private Archivo baner;
	private Integer validacion;
	private List<Documento> documentos;
	private List<Enlace> enlaces;
	private Set<FichaUA> fichasua;
	private Set<HechoVital> hechosVitales;
	private String info;
	private String responsable;
	 

	//Constructores
	

	public Ficha() {
		super();
	}
	
	public Ficha(Long id) {
		super();
		this.id = id;
	}
	
	// get & set
	

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
    
    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }
    
    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }   
    
    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }    
    
    public String getUrlForo() {
        return urlForo;
    }

    public void setUrlForo(String urlForo) {
        this.urlForo = urlForo;
    }    

	public String getForo_tema() {
		return foro_tema;
	}

	public void setForo_tema(String foro_tema) {
		this.foro_tema = foro_tema;
	}
	
    public Archivo getIcono() {
        return icono;
    }

    public void setIcono(Archivo icono) {
        this.icono = icono;
    }

    public Archivo getImagen() {
        return imagen;
    }

    public void setImagen(Archivo imagen) {
        this.imagen = imagen;
    }

    public Archivo getBaner() {
        return baner;
    }

    public void setBaner(Archivo baner) {
        this.baner = baner;
    }

    public Integer getValidacion() {
        return validacion;
    }

    public void setValidacion(Integer validacion) {
        this.validacion = validacion;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public List<Enlace> getEnlaces() {
        return enlaces;
    }

    public void setEnlaces(List<Enlace> enlaces) {
        this.enlaces = enlaces;
    }
    
    public Set<FichaUA> getFichasua() {
        return fichasua;
    }

    public void setFichasua(Set<FichaUA> fichasua) {
        this.fichasua = fichasua;
    }

    public void addDocumento(Documento documento) {
        documento.setFicha(this);
        documento.setOrden(documentos.size());
        documentos.add(documento);
    }

    public void removeDocumento(Documento documento) {
        int ind = documentos.indexOf(documento);
        documentos.remove(ind);
        for (int i = ind; i < documentos.size(); i++) {
            Documento d = (Documento) documentos.get(i);
            d.setOrden(i);
        }
    }

    public void addEnlace(Enlace enlace) {
        enlace.setFicha(this);
        enlace.setOrden(enlaces.size());
        enlaces.add(enlace);
    }

    public void removeEnlace(Enlace enlace) {
        int ind = enlaces.indexOf(enlace);
        enlaces.remove(ind);
        for (int i = ind; i < enlaces.size(); i++) {
        	Enlace e = (Enlace) enlaces.get(i);
            e.setOrden(i);
        }
    }
    
    public void addFichaUA(FichaUA ficha) {
        ficha.setFicha(this);
        fichasua.add(ficha);
    }

    public void removeFichaUA(FichaUA ficha) {
        fichasua.remove(ficha);
    }

     public Set<HechoVital> getHechosVitales() {
        return hechosVitales;
    }

    public void setHechosVitales(Set<HechoVital> hechosVitales) {
        this.hechosVitales = hechosVitales;
    }
    
    public void addHechovital(HechoVital hechoVital){
    	hechosVitales.add(hechoVital);
    }
    
    public void removeHechovital(HechoVital hechoVital){
    	hechosVitales.remove(hechoVital);
    }
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    public String getResponsable() {
		return responsable;
	}
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	@Override
	public String toString() {
		return "Ficha [id=" + id + ", fechaPublicacion=" + fechaPublicacion
				+ ", fechaCaducidad=" + fechaCaducidad
				+ ", fechaActualizacion=" + fechaActualizacion 
				+ ", urlVideo="	+ urlVideo 
				+ ", urlForo=" + urlForo 
				+ ", foro_tema=" + foro_tema 
				//+ ", icono=" + icono 
				//+ ", imagen=" + imagen
				//+ ", baner=" + baner 
				+ ", validacion=" + validacion
				//+ ", documentos=" + documentos + ", enlaces=" + enlaces
				//+ ", fichasua=" + fichasua 
				//+ ", hechosVitales=" + hechosVitales
				+ ", info=" + info 
				+ ", responsable=" + responsable
				//+ ", materias(" + getMaterias().size() + ")=" + getMaterias() 
				+ "]";
	}

	

 
    /*
    public IndexObject indexObject() {
        final IndexObject io = new IndexObject();
        // io.setId(getId());
        // io.setClassName(Ficha.class.getName());
        for (Iterator iterator = getTraducciones().values().iterator(); iterator.hasNext();) {
            TraduccionFicha tr = (TraduccionFicha) iterator.next();
            if (tr != null) {
                io.addTextLine(tr.getTitulo());
                io.addTextLine(tr.getDescAbr());
                io.addTextLine(tr.getDescripcion());
            }
        }
        return io;
    }
    */
 
	public Boolean comprovarVisibilitat() {
	
		Date dataActual = new Date();
		Boolean visible;
		if (this.getValidacion().equals(Validacion.PUBLICA) && 
				((this.getFechaCaducidad() != null && this.getFechaCaducidad().before(dataActual)) || this.getFechaCaducidad() == null)
				&& ((this.getFechaPublicacion() != null && this.getFechaPublicacion().after(dataActual)) || this.getFechaPublicacion() == null)){
			visible = Boolean.TRUE;
		} else {
			visible = Boolean.FALSE;
		}
		return visible;
	}
   
}