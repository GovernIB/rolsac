package org.ibit.rol.sac.model;

import java.util.HashMap;
import java.util.Date;
import org.ibit.rol.sac.model.ws.NormativaTransferible;
import org.ibit.rol.sac.model.ws.TraduccionNormativaTransferible;

/**
 * Normativa remota. 
 * @author slromero
 *
 */
public class NormativaRemota extends Normativa implements Remoto{
	
	 	/** Serial Version UID.  */
	 	private static final long serialVersionUID = -7439674331843874347L;

	 	/**
	 	 * Constructor. 
	 	 */
	    public NormativaRemota() {
	        super();
	    }

	    /**
	     * Contructor para optimizar las busquedas	    
	     * @param id
	     * @param numero
	     * @param fecha
	     * @param fechaBoletin
	     * @param nombreTipo
	     * @param validacion
	     * @param traduccionTitulo
	     * @param nombreBoletin
	     * @param idioma
	     */
	    public NormativaRemota(
	            Long id, 
	            Long numero, 
	            Date fecha, 
	            Date fechaBoletin, 
	            String nombreTipo, 
	            Integer validacion,
	            String traduccionTitulo, 
	            String nombreBoletin,
	            String idioma) {

	        super();

	        setId(id == null ? 0 : id);
	        setNumero(numero == null ? -1l : numero);
	        setFecha(fecha);
	        setFechaBoletin(fechaBoletin);
	        setValidacion(validacion);
	        setNombreTipo(nombreTipo != null ? nombreTipo : "");
	        setTraduccionTitulo(traduccionTitulo != null ? traduccionTitulo : "");
	        setNombreBoletin(nombreBoletin != null ? nombreBoletin : "");
	        setIdioma(idioma);
	    }
	    
	    
	    /** Contructor para optimizar las busquedas
	     * 
	     * @param id
	     * @param numero
	     * @param fecha
	     * @param fechaBoletin
	     * @param validacion
	     * @param traduccionTitulo
	     * @param nombreBoletin
	     * @param idioma
	     */
	     
	      
	    public NormativaRemota(
	            Long id, 
	            Long numero, 
	            Date fecha, 
	            Date fechaBoletin, 
	            Integer validacion,
	            String traduccionTitulo, 
	            String nombreBoletin,
	            String idioma) {
	        
	        this(id, numero, fecha, fechaBoletin, "", validacion, traduccionTitulo, nombreBoletin, idioma);
	    }
	    
	    /** Contructor para optimizar las busquedas
	     * 
	     * @param id
	     * @param numero
	     * @param fecha
	     * @param fechaBoletin
	     * @param nombreTipo
	     * @param validacion
	     * @param traduccionTitulo
	     * @param idioma
	     */
	    public NormativaRemota(
	            Long id, 
	            Long numero, 
	            Date fecha, 
	            Date fechaBoletin, 
	            String nombreTipo, 
	            Integer validacion,
	            String traduccionTitulo, 
	            String idioma) {
	        
	        this(id, numero, fecha, fechaBoletin, nombreTipo, validacion, traduccionTitulo, "", idioma);
	    }
	    
	    /** Contructor para optimizar las busquedas
	     * 
	     * @param id
	     * @param numero
	     * @param fecha
	     * @param fechaBoletin
	     * @param validacion
	     * @param traduccionTitulo
	     * @param idioma
	     */
	    public NormativaRemota(
	            Long id, 
	            Long numero, 
	            Date fecha, 
	            Date fechaBoletin, 
	            Integer validacion,
	            String traduccionTitulo, 
	            String idioma) {
	        
	        this(id, numero, fecha, fechaBoletin, "", validacion, traduccionTitulo, "", idioma);
	    }
	    

	    /** Administracion remota. **/
	    private AdministracionRemota administracionRemota;
	    
	    /** Id externo. **/
	    private Long idExterno;

	    /**
	     * Get Administracion remota.  
	     */
	    public AdministracionRemota getAdministracionRemota() {
	    	return administracionRemota;
	    }

	    /**
	     * Set administracion remota. 
	     * @param administracionRemota. 
	     */
	    public void setAdministracionRemota(AdministracionRemota administracionRemota) {
	    	this.administracionRemota = administracionRemota;
	    }

	    /** 
	     * Get id Externo.
	     */
	    public Long getIdExterno() {
	    	return idExterno;
	    }

	    /**
	     * Set id externo
	     * @param idExterno
	     */
	    public void setIdExterno(Long idExterno) {
	    	this.idExterno = idExterno;
	    }

  
	    /**
	     * Get url remota. 
	     * @return
	     */
	    public String getUrlRemota() {
	    	return urlRemota;
	    }

	    /**
	     * Set url remota. 
	     * @param urlRemota
	     */
	    public void setUrlRemota(String urlRemota) {
	    	this.urlRemota = urlRemota;
	    }
	    
	    /** Url remota. **/
	    private String urlRemota;

	    /**
	     *  Rellenar la normativa transferible en la clase actual.
	     * @param normativaTransferible
	     */
	    public void rellenar(final NormativaTransferible normativaTransferible){
	    	this.setIdExterno(normativaTransferible.getId());
		    this.setNumero(normativaTransferible.getNumero());
			this.setValidacion(normativaTransferible.getValidacion());
	
			 //Relleno las traducciones
			final HashMap<String, Traduccion> traducciones = new HashMap<String, Traduccion>();
	       if (normativaTransferible.getTraducciones() != null) {
	           for (final TraduccionNormativaTransferible traduccion : normativaTransferible.getTraducciones()){
	               if (traduccion != null) {
	                   final TraduccionNormativa temp =  new TraduccionNormativa();
	                   temp.setTitulo(traduccion.getTitulo());
	                   traducciones.put(traduccion.getCodigoEstandarIdioma().toLowerCase(), temp);
	               }
	           }
	       }
	       this.setTraduccionMap(traducciones);
		}

	    /**
	     * Genera una noramtivaRemota a partir de la normativa transferible.
	     * @param normT
	     * @return
	     */
	    public static NormativaRemota generar(NormativaTransferible normT){
	    	NormativaRemota norm =  new NormativaRemota();
	    	if(normT!=null){
	    		norm.rellenar(normT);
	    	}
	    	return norm;
	    }
}
