package org.ibit.rol.sac.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ibit.rol.sac.model.ws.EdificioTransferible;
import org.ibit.rol.sac.model.ws.TraduccionEdificioTransferible;
import org.ibit.rol.sac.model.ws.TraduccionUATransferible;
import org.ibit.rol.sac.model.ws.UnidadAdministrativaTransferible;

/**
 * Created by IntelliJ IDEA.
 * User: mgonzalez
 * Date: 01-jun-2007
 * Time: 13:15:43
 * (PORMAD)
 */
public class UnidadAdministrativaRemota extends UnidadAdministrativa implements Remoto {
	
	private static final long serialVersionUID = 1L;
	
	private Long idExterno;
	private AdministracionRemota administracionRemota;
	private String urlRemota;

    public Long getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(Long idExterno) {
        this.idExterno = idExterno;
    }

    public AdministracionRemota getAdministracionRemota() {
        return administracionRemota;
    }

    public void setAdministracionRemota(AdministracionRemota administracionRemota) {
        this.administracionRemota = administracionRemota;
    }

    public String getUrlRemota() {
        return urlRemota;
    }

    public void setUrlRemota(String urlRemota) {
        this.urlRemota = urlRemota;
    }
    
    public void rellenar(UnidadAdministrativaTransferible uaTransferible){
    	//Relleno los campos
		this.setIdExterno(uaTransferible.getId());
		this.setClaveHita(uaTransferible.getClaveHita());
		this.setDominio(uaTransferible.getDominio());
		this.setOrden(uaTransferible.getOrden());
		this.setValidacion(uaTransferible.getValidacion());
		this.setResponsable(uaTransferible.getResponsable());
		this.setTelefono(uaTransferible.getTelefono());
		this.setFax(uaTransferible.getFax());
		this.setEmail(uaTransferible.getEmail());
		this.setSexoResponsable(uaTransferible.getSexoResponsable());
		this.setUrlRemota(uaTransferible.getUrlRemota());
		

		//EdificioTransferible[] edificioTransferible =uaTransferible.getEdificiosTransferibles();
		
        //Relleno las traducciones
		final Set<Edificio> listEdificios = new HashSet<Edificio>();
		if (uaTransferible.getEdificiosTransferibles() != null && uaTransferible.getEdificiosTransferibles().length>0) {
            for (EdificioTransferible edificioTransferible : uaTransferible.getEdificiosTransferibles()){
                if (edificioTransferible != null) {
                    final Edificio temp =  new Edificio();
                    
                    temp.setId(edificioTransferible.getId());
                    temp.setCodigoPostal(edificioTransferible.getCodigoPostal());
                    temp.setDireccion(edificioTransferible.getDireccion());
                    temp.setEmail(edificioTransferible.getEmail());
                    temp.setFax(edificioTransferible.getFax());
                    temp.setPoblacion(edificioTransferible.getPoblacion());
                    temp.setTelefono(edificioTransferible.getTelefono());
                    temp.setLatitud(edificioTransferible.getLatitud());
                    temp.setLongitud(edificioTransferible.getLongitud());

                    //Relleno las traducciones
            		/*final List<TraduccionEdificio> traduccionesEdificio = new ArrayList<TraduccionEdificio>(); 
            		for (final String idioma : (Collection<String>)temp.getLangs()){
                        final TraduccionEdificioTransferible traduccionEdificioTransferible = (TraduccionEdificioTransferible)edificioTransferible.getTraducciones(idioma);
                        if(traduccionEdificioTransferible!=null){
                            final TraduccionEdificio temp2 =  new TraduccionEdificio();

                            temp2.setDescripcion(traduccionEdificioTransferible.getDescripcion());
                            traduccionesEdificio.add(temp2);
                        }
                    }
            		traduccionesEdificio.setTraducciones(traduccionesEdificio.toArray(new TraduccionEdificio[0]));
                    */
                    listEdificios.add(temp);
                }
            }
        }
        this.setEdificios(listEdificios);
		
		
		//Trasformo los ArchivoTransferible contenidos en Archivo
		//this.setFotop(Archivo.generar(uaTransferible.getFotop()));
		//this.setFotog(Archivo.generar(uaTransferible.getFotog()));
		//this.setLogoh(Archivo.generar(uaTransferible.getLogoh()));
		//this.setLogov(Archivo.generar(uaTransferible.getLogov()));
		//this.setLogos(Archivo.generar(uaTransferible.getLogos()));
		//this.setLogot(Archivo.generar(uaTransferible.getLogot()));
		
		
        //Relleno las traducciones
		final Map<String, Traduccion> traducciones = new HashMap<String, Traduccion>();
		if (uaTransferible.getTraducciones() != null) {
            for (final TraduccionUATransferible traduccion : uaTransferible.getTraducciones()){
                if (traduccion != null) {
                    final TraduccionUA temp =  new TraduccionUA();
                    temp.setNombre(traduccion.getNombre());
                    temp.setAbreviatura(traduccion.getAbreviatura());
                    temp.setPresentacion(traduccion.getPresentacion());
                    traducciones.put(traduccion.getCodigoEstandarIdioma().toLowerCase(), temp);
                }
            }
        }
        this.setTraduccionMap(traducciones);
    }
    
    public static UnidadAdministrativaRemota generar(UnidadAdministrativaTransferible uaT){
    	UnidadAdministrativaRemota unidad =  new UnidadAdministrativaRemota();
    	if(uaT!=null){
    		unidad.rellenar(uaT);
    	}
    	return unidad;
    }
}
