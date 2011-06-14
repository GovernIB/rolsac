package org.ibit.rol.sac.model;

import org.ibit.rol.sac.model.ws.NormativaTransferible;
import org.ibit.rol.sac.model.ws.TraduccionNormativaTransferible;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: mgonzalez
 * Date: 23-nov-2010
 * Time: 13:47:24
 * To change this template use File | Settings | File Templates.
 */
public class NormativaExternaRemota extends NormativaExterna implements Remoto{

     protected static final Log log = LogFactory.getLog(NormativaExternaRemota.class);

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

    public String getUrlRemota() {
        return urlRemota;
    }

    public void setUrlRemota(String urlRemota) {
        this.urlRemota = urlRemota;
    }

    private String urlRemota;

    public void rellenar(final NormativaTransferible normativaTransferible){
		this.setIdExterno(normativaTransferible.getId());
		this.setNumero(normativaTransferible.getNumero());
		this.setValidacion(normativaTransferible.getValidacion());

		 //Relleno las traducciones
		final HashMap<String, Traduccion> traducciones = new HashMap<String, Traduccion>();
        if (normativaTransferible.getTraducciones() != null) {
            for (final TraduccionNormativaTransferible traduccion : normativaTransferible.getTraducciones()){
                if (traduccion != null) {
                    final TraduccionNormativaExterna temp =  new TraduccionNormativaExterna();
                    log.info("TITULO NORMATIVA REMOTA" + traduccion.getTitulo());
                    log.info("TITULO NORMATIVA REMOTA LENGHT" + traduccion.getTitulo().length());
                    temp.setTitulo(traduccion.getTitulo());
                    traducciones.put(traduccion.getCodigoEstandarIdioma().toLowerCase(), temp);
                }
            }
        }
        this.setTraduccionMap(traducciones);
	}

    public static NormativaExternaRemota generar(NormativaTransferible normT){
    	NormativaExternaRemota norm =  new NormativaExternaRemota();
    	if(normT!=null){
    		norm.rellenar(normT);
    	}
    	return norm;
    }
}
