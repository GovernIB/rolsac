package org.ibit.rol.sac.model.ws;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.TraduccionEdificio;
import org.ibit.rol.sac.model.TraduccionNormativa;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class NormativaTransferible extends ActuacionTransferible implements Serializable {

    protected static final Log log = LogFactory.getLog(NormativaTransferible.class);
	//VUDS
	
	private Long id;
    private Long numero;
    private Integer validacion;
    
    private TraduccionNormativaTransferible[] traducciones;
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Integer getValidacion() {
		return validacion;
	}

	public void setValidacion(Integer validacion) {
		this.validacion = validacion;
	}
	

	public TraduccionNormativaTransferible[] getTraducciones() {
		return traducciones;
	}

	public void setTraducciones(TraduccionNormativaTransferible[] traducciones) {
		this.traducciones = traducciones;
	}

	public void rellenar(final Normativa normativa){
    	if(normativa!=null){
    		this.setId(normativa.getId());
    		this.setNumero(normativa.getNumero());
    		this.setValidacion(normativa.getValidacion());
    		
    		 //Relleno las traducciones
    		final List<TraduccionNormativaTransferible> traducciones = new ArrayList<TraduccionNormativaTransferible>(); 
    		for (final String idioma : (Collection<String>)normativa.getLangs()){
                final TraduccionNormativa traduccion = (TraduccionNormativa)normativa.getTraduccion(idioma);
                if(traduccion!=null){
                    final TraduccionNormativaTransferible temp =  new TraduccionNormativaTransferible();
                    temp.setTitulo(traduccion.getTitulo());
                    temp.setCodigoEstandarIdioma(idioma);
                    traducciones.add(temp);
                }
            }
    		this.setTraducciones(traducciones.toArray(new TraduccionNormativaTransferible[0]));
    	}
    }
	
    public static NormativaTransferible generar(Normativa normativa){
    	NormativaTransferible normT = new NormativaTransferible();
    	if(normativa!=null){
    		normT.rellenar(normativa);
    	}
    	return normT;
    }
}
