package org.ibit.rol.sac.model.ws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.UnidadMateria;
import org.ibit.rol.sac.model.Validacion;

/**
 * Created by IntelliJ IDEA.
 * User: mgonzalez
 * Date: 13-jun-2007
 * Time: 15:21:25
 * Clase que representa la información traducida a transferir de una UA(PORMAD)
 */
public class UnidadAdministrativaTransferible extends ActuacionTransferible implements Serializable {

	public static final String URL_UA = "es.caib.rolsac.model.ws.urlUnidad";

	
    private UnidadMateriaTransferible[] unidadesMaterias;
    private String codigoEstandarTratamiento;
    private Long idPadre;
    private TraduccionUATransferible[] traducciones;
    private Long[] idHijos;

    private Long id;
    private String claveHita;
    private String dominio;
    private long orden;
    private Integer validacion;
    private String responsable;
    private String telefono;
    private String fax;
    private String email;
    private int nivel;

    private Integer sexoResponsable;
    private ArchivoTransferible fotop;
    private ArchivoTransferible fotog;
    private ArchivoTransferible logoh;
    private ArchivoTransferible logov;
    private ArchivoTransferible logos;
    private ArchivoTransferible logot;
    private String[] codigoEstandarMaterias;
    private EdificioTransferible[] edificiosTransferibles;
    
    private String urlRemota;

    public String getCodigoEstandarTratamiento() {
        return codigoEstandarTratamiento;
    }

    public void setCodigoEstandarTratamiento(String codigoEstandarTratamiento) {
        this.codigoEstandarTratamiento = codigoEstandarTratamiento;
    }
    
    public String[] getCodigoEstandarMaterias() {
        return codigoEstandarMaterias;
    }

    public void setCodigoEstandarMaterias(String[] codigoEstandarMaterias) {
        this.codigoEstandarMaterias = codigoEstandarMaterias;
    }

    public Long getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Long idPadre) {
        this.idPadre = idPadre;
    }

    public TraduccionUATransferible[] getTraducciones() {
        return traducciones;
    }

    public void setTraducciones(TraduccionUATransferible[] traducciones) {
        this.traducciones = traducciones;
    }


    //UnidadAdministrativa
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClaveHita() {
        return claveHita;
    }

    public void setClaveHita(String claveHita) {
        this.claveHita = claveHita;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public long getOrden() {
        return orden;
    }

    public void setOrden(long orden) {
        this.orden = orden;
    }

    public Integer getValidacion() {
        return validacion;
    }

    public void setValidacion(Integer validacion) {
        this.validacion = validacion;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSexoResponsable() {
        return sexoResponsable;
    }

    public void setSexoResponsable(Integer sexoResponsable) {
        this.sexoResponsable = sexoResponsable;
    }

    public ArchivoTransferible getFotop() {
        return fotop;
    }

    public void setFotop(ArchivoTransferible fotop) {
        this.fotop = fotop;
    }

    public ArchivoTransferible getFotog() {
        return fotog;
    }

    public void setFotog(ArchivoTransferible fotog) {
        this.fotog = fotog;
    }

    public ArchivoTransferible getLogoh() {
        return logoh;
    }

    public void setLogoh(ArchivoTransferible logoh) {
        this.logoh = logoh;
    }

    public ArchivoTransferible getLogov() {
        return logov;
    }

    public void setLogov(ArchivoTransferible logov) {
        this.logov = logov;
    }

    public ArchivoTransferible getLogos() {
        return logos;
    }

    public void setLogos(ArchivoTransferible logos) {
        this.logos = logos;
    }

    public ArchivoTransferible getLogot() {
        return logot;
    }

    public void setLogot(ArchivoTransferible logot) {
        this.logot = logot;
    }
    
    public Long[] getIdHijos() {
		return idHijos;
	}

	public void setIdHijos(Long[] idHijos) {
		this.idHijos = idHijos;
	}


    public UnidadMateriaTransferible[] getUnidadesMaterias() {
        return unidadesMaterias;
    }

    public void setUnidadesMaterias(UnidadMateriaTransferible[] unidadesMaterias) {
        this.unidadesMaterias = unidadesMaterias;
    }

    public String getUrlRemota() {
        return urlRemota;
    }

    public void setUrlRemota(String urlRemota) {
        this.urlRemota = urlRemota;
    }
    
    public EdificioTransferible[] getEdificiosTransferibles() {
    	return edificiosTransferibles;
    }
    
    public void setEdificiosTransferibles(
    		EdificioTransferible[] edificiosTransferibles) {
    	this.edificiosTransferibles = edificiosTransferibles;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }


    //Funciones Extra
    
	@SuppressWarnings("unchecked")
	public void rellenar(UnidadAdministrativa unidad){
    	//Relleno los campos
		this.setId(unidad.getId());
		this.setClaveHita(unidad.getClaveHita());
		this.setDominio(unidad.getDominio());
		this.setOrden(unidad.getOrden());
		this.setValidacion(unidad.getValidacion());
		this.setResponsable(unidad.getResponsable());
		this.setTelefono(unidad.getTelefono());
		this.setFax(unidad.getFax());
		this.setEmail(unidad.getEmail());
		this.setSexoResponsable(unidad.getSexoResponsable());

		//Trasformo los ArchivoTransferible contenidos en Archivo
		//this.setFotop(ArchivoTransferible.generar(unidad.getFotop()));
		//this.setFotog(ArchivoTransferible.generar(unidad.getFotog()));
		//this.setLogoh(ArchivoTransferible.generar(unidad.getLogoh()));
		//this.setLogov(ArchivoTransferible.generar(unidad.getLogov()));
		//this.setLogos(ArchivoTransferible.generar(unidad.getLogos()));
		//this.setLogot(ArchivoTransferible.generar(unidad.getLogot()));
		
		
		this.setUrlRemota(establecerIdEnUrl(unidad.getId().toString(), obtenerUrl(URL_UA)));


        //Relleno el id del padre
        if(unidad.getPadre()!=null){
            this.setIdPadre(unidad.getPadre().getId());
        }
        int niv =1;
        UnidadAdministrativa padre = unidad.getPadre();
        while (padre!= null){
            niv++;
            padre= padre.getPadre();
        }
        this.setNivel(niv);

        //Relleno el id de los hijos
		final List<UnidadAdministrativa> hijos = unidad.getHijos();
		List<Long> idHijos = null;
		if(hijos!=null && !hijos.isEmpty()){
			idHijos = new ArrayList<Long>();
			for(final UnidadAdministrativa hijo : hijos){
				if(hijo.getValidacion().equals(Validacion.PUBLICA)){
					idHijos.add(hijo.getId());
				}
			}
		}
		this.setIdHijos(idHijos !=null ? idHijos.toArray(new Long[0]) : null);

        //recojo los codigos estandar
        if (unidad.getTratamiento() != null) {
            this.setCodigoEstandarTratamiento(unidad.getTratamiento().getCodigoEstandar());
        }


        //Relleno los campos con sus Codigos Estandar
		if(unidad.getUnidadesMaterias()!=null && !unidad.getUnidadesMaterias().isEmpty()){
			final List<String> materias = new ArrayList<String>();
			for(final UnidadMateria unimat : (Collection<UnidadMateria>)unidad.getUnidadesMaterias()){
				materias.add(unimat.getMateria().getCodigoEstandar());
			}
			this.setCodigoEstandarMaterias(materias.toArray(new String[0]));
		}
		

		//Relleno las traducciones
		final List<TraduccionUATransferible> traducciones = new ArrayList<TraduccionUATransferible>();
        for (final String idioma : (Collection<String>)unidad.getLangs()){
            final TraduccionUA traRemoto = (TraduccionUA)unidad.getTraduccion(idioma);
            if(traRemoto != null){
                final TraduccionUATransferible temp =  new TraduccionUATransferible();
			    temp.setNombre(traRemoto.getNombre());
			    temp.setAbreviatura(traRemoto.getAbreviatura());
			    temp.setPresentacion(traRemoto.getPresentacion());
			    temp.setCodigoEstandarIdioma(idioma);
			    traducciones.add(temp);
            }
        }
        this.setTraducciones(traducciones.toArray(new TraduccionUATransferible[0] ));
    }

    public static UnidadAdministrativaTransferible generar(UnidadAdministrativa unidad){
    	UnidadAdministrativaTransferible unidadT =  new UnidadAdministrativaTransferible();
    	if(unidad!=null){
    		unidadT.rellenar(unidad);
    	}
    	return unidadT;
    }
}
