package org.ibit.rol.sac.model;

import java.util.HashMap;
import org.ibit.rol.sac.model.ws.NormativaTransferible;
import org.ibit.rol.sac.model.ws.TraduccionNormativaTransferible;

public class NormativaRemota extends Normativa implements Remoto{
    
	private static final long serialVersionUID = 1L;

	private AdministracionRemota administracionRemota;
	private Long idExterno;

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
	
    public static NormativaRemota generar(NormativaTransferible normT){
    	NormativaRemota norm =  new NormativaRemota();
    	if(normT!=null){
    		norm.rellenar(normT);
    	}
    	return norm;
    }
	


	
}
