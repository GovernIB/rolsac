package org.ibit.rol.sac.model.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.ibit.rol.sac.model.FichaUA;

/**
 * Clase que representa la información a transferir de una FichaUA(PORMAD)
 */
public class FichaUATransferible implements Serializable {

    private Long idUnidadAdministrativa;
    private Long idFicha;
    private String codigoEstandarSeccion;
    private Integer orden;
    
    public FichaUATransferible() {
	}
    
	public FichaUATransferible(Long idUnidadAdministrativa, Long idFicha,
			String codigoEstandarSeccion) {
		this.idUnidadAdministrativa = idUnidadAdministrativa;
		this.idFicha = idFicha;
		this.codigoEstandarSeccion = codigoEstandarSeccion;
	}
	
	public String getCodigoEstandarSeccion() {
		return codigoEstandarSeccion;
	}
	public void setCodigoEstandarSeccion(String codigoEstandarSeccion) {
		this.codigoEstandarSeccion = codigoEstandarSeccion;
	}
	public Long getIdFicha() {
		return idFicha;
	}
	public void setIdFicha(Long idFicha) {
		this.idFicha = idFicha;
	}
	public Long getIdUnidadAdministrativa() {
		return idUnidadAdministrativa;
	}
	public void setIdUnidadAdministrativa(Long idUnidadAdministrativa) {
		this.idUnidadAdministrativa = idUnidadAdministrativa;
	}
	public Integer getOrden() {
		return orden;
	}
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
    
	public static FichaUATransferible[] generar(Collection<FichaUA> fichasUA){
		FichaUATransferible[] transferibles = null;
		                    
		if(fichasUA!=null){
	        final List<FichaUATransferible> fichasUAT = new ArrayList<FichaUATransferible>();
			for(final FichaUA fichaUA : fichasUA){
				FichaUATransferible fichaUAT =  new FichaUATransferible();
				fichaUAT.setIdFicha(fichaUA.getFicha().getId());
				fichaUAT.setIdUnidadAdministrativa(fichaUA.getUnidadAdministrativa().getId());
				fichaUAT.setCodigoEstandarSeccion(fichaUA.getSeccion().getCodigoEstandard());
	
				fichasUAT.add(fichaUAT);
			}
			transferibles = fichasUAT.toArray(new FichaUATransferible[0]);
        }
		
		return transferibles;
	}
    
	public String toString(){
		StringBuffer salida = new StringBuffer("---FichaUA---\n");
		salida.append(" -Ficha id: ");
		salida.append(getIdFicha());
		salida.append("\n -Unidad id: ");
		salida.append(getIdUnidadAdministrativa());
		salida.append("\n -Seccion CE: ");
		salida.append(getCodigoEstandarSeccion());
		salida.append("\n");
		return salida.toString();
		
		
	}
}
