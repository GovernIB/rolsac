/* Generated by Together */

package org.ibit.rol.sac.model;

import java.util.*;

/**
 * (PORMAD)
 */
public class UnidadAdministrativa extends Traducible implements Indexable, Validable {
	
	private static final long serialVersionUID = 1L;
	
	// Constructores

	public UnidadAdministrativa() {
		super();
	}
	
	public UnidadAdministrativa(Long id) {
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

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
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

    public Archivo getFotop() {
        return fotop;
    }

    public void setFotop(Archivo fotop) {
        this.fotop = fotop;
    }

    public Archivo getFotog() {
        return fotog;
    }

    public void setFotog(Archivo fotog) {
        this.fotog = fotog;
    }
    
    public Archivo getLogoh() {
        return logoh;
    }

    public void setLogoh(Archivo logoh) {
        this.logoh = logoh;
    }
    
    public Archivo getLogov() {
        return logov;
    }

    public void setLogov(Archivo logov) {
        this.logov = logov;
    }
    
    public Archivo getLogos() {
        return logos;
    }

    public void setLogos(Archivo logos) {
        this.logos = logos;
    }

    public Archivo getLogot() {
        return logot;
    }

    public void setLogot(Archivo logot) {
        this.logot = logot;
    }


    public UnidadAdministrativa getPadre() {
        return padre;
    }

    public void setPadre(UnidadAdministrativa padre) {
        this.padre = padre;
    }

    public List<UnidadAdministrativa> getHijos() {
        return hijos;
    }

    public void setHijos(List<UnidadAdministrativa> hijos) {
        this.hijos = hijos;
    }

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    public Set getEdificios() {
        return edificios;
    }

    public void setEdificios(Set edificios) {
        this.edificios = edificios;
    }

    public Set<Personal> getPersonal() {
        return personal;
    }

    public void setPersonal(Set<Personal> personal) {
        this.personal = personal;
    }

    public Set<Normativa> getNormativas() {
        return normativas;
    }

    public void setNormativas(Set<Normativa> normativas) {
        this.normativas = normativas;
    }

    public Set<ProcedimientoLocal> getProcedimientos() {
        return procedimientos;
    }

    public void setProcedimientos(Set<ProcedimientoLocal> procedimientos) {
        this.procedimientos = procedimientos;
    }

    public Set<UnidadMateria> getUnidadesMaterias() {
        return unidadesMaterias;
    }

    public void setUnidadesMaterias(Set<UnidadMateria> unidadesMaterias) {
        this.unidadesMaterias = unidadesMaterias;
    }

    public Set getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set usuarios) {
        this.usuarios = usuarios;
    }

    public List<FichaUA> getFichasUA() {
        return fichasUA;
    }

    public void setFichasUA(List<FichaUA> fichasUA) {
        this.fichasUA = fichasUA;
    }

    public void addHijo(UnidadAdministrativa hijo) {
        hijo.setPadre(this);
        hijo.setOrden(hijos.size());
        hijos.add(hijo);
    }

    public void removeHijo(UnidadAdministrativa hijo) {
        int ind = hijos.indexOf(hijo);
        if (ind > -1) {
            hijo.setPadre(null);
            hijos.remove(ind);
            int reordenar = 0;
            for (int i = 0; i < hijos.size(); i++) {
                UnidadAdministrativa ua = (UnidadAdministrativa) hijos.get(i);
                if (ua != null)
                	ua.setOrden(reordenar++);
                else
                	hijos.remove(i);
            }
        }
    }


    public void darDeBajaUA(UnidadAdministrativa hijo) {
        int ind = hijos.indexOf(hijo);
        if (ind > -1) {
	    	hijo.setValidacion(Validacion.BAJA);
            hijos.remove(ind);
            int reordenar = 0;
            for (int i = 0; i < hijos.size(); i++) {
                UnidadAdministrativa ua = (UnidadAdministrativa) hijos.get(i);
                if (ua != null)
                	ua.setOrden(reordenar++);
                else
                	hijos.remove(i);
            }
        }
    }      
    
    public void darDeBajaHijosUA(UnidadAdministrativa ua) {
            
    		for (int i = 0; i < hijos.size(); i++) {
                UnidadAdministrativa uaHijo = (UnidadAdministrativa) hijos.get(i);
                uaHijo.setValidacion(Validacion.BAJA);
		    	uaHijo.darDeBajaHijosUA(uaHijo);
            }
        }

    
    public boolean isUAdadaDeBaja(UnidadAdministrativa ua) {
        
    	Integer uADadadeBaja = ua.getValidacion();

		if (uADadadeBaja.equals(Validacion.BAJA)) return true;	
		else return false;
    }    
    
    public void addFichaUA(FichaUA ficha) {
        ficha.setUnidadAdministrativa(this);
        fichasUA.add(ficha);
        todasfichas.add(ficha);
    }

    public void removeFichaUA(FichaUA ficha) {
        int ind = fichasUA.indexOf(ficha);
        if (ind > -1) {
            ficha.setUnidadAdministrativa(null);
            fichasUA.remove(ind);
            for (int i = ind; i < fichasUA.size(); i++) {
                FichaUA f = (FichaUA) fichasUA.get(i);
                if (f!=null)
                	f.setOrden(i);
            }
        }
        todasfichas.remove(ficha);
    }

    public Map getMapSeccionFichasUA() {
    	
        Map result = new TreeMap();
        Iterator todas= todasfichas.iterator();
        
        while(todas.hasNext()) {
        	
        	FichaUA fichaUA = (FichaUA)todas.next();
        	String newIdseccion = fichaUA.getSeccion().getId().longValue() + 
        							"#" + 
        							((TraduccionSeccion)fichaUA.getSeccion().getTraduccion("ca")).getNombre();
            List fichasSeccion = (List) result.get(newIdseccion);
            if (fichasSeccion == null) {
                fichasSeccion = new ArrayList();
                result.put(newIdseccion, fichasSeccion);
            }
            fichasSeccion.add(fichaUA);  

        }
        return result;

    }

    public void addPersonal(Personal persona) {
        persona.setUnidadAdministrativa(this);
        personal.add(persona);
    }

    public void removePersonal(Personal persona) {
        personal.remove(persona);
    }


    public void addUnidadMateria(UnidadMateria unidadMateria) {
        unidadMateria.setUnidad(this);
        unidadesMaterias.add(unidadMateria);
    }

    public void removeUnidadMateria(UnidadMateria unidadMateria) {
        unidadesMaterias.remove(unidadMateria);
    }

    public void addProcedimientoLocal(ProcedimientoLocal procedimientoLocal){
        procedimientos.add(procedimientoLocal);
    }

    public void removeProcedimientoLocal(ProcedimientoLocal procedimientoLocal) {
        procedimientos.remove(procedimientoLocal);
    }
    
    public void removeNormativa(Normativa normativa) {
        normativas.remove(normativa);
    }    
    

    /* Gesti�n del organigrama */

    public boolean isRaiz() {
        return padre == null;
    }
    public boolean isDescendiente(UnidadAdministrativa ua) {
        return !isRaiz() && (getPadre().equals(ua) || getPadre().isDescendiente(ua));
    }

    public boolean isPredecesor(UnidadAdministrativa ua) {
        return ua.isDescendiente(this);
    }

    public Set getPredecesores() {
        if (isRaiz()) {
            return new HashSet();
        } else {
            Set predecesores = getPadre().getPredecesores();
            predecesores.add(getPadre());
            return predecesores;
        }
    }

	public EspacioTerritorial getEspacioTerrit() {
		return espacioTerrit;
	}

	public void setEspacioTerrit(EspacioTerritorial espacioTerrit) {
		this.espacioTerrit = espacioTerrit;
    }

    public Date getFechaUltimaActualizacion() {
       return fechaUltimaActualizacion;
    }

    public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) {
       this.fechaUltimaActualizacion = fechaUltimaActualizacion;
    }

    public String getCodigoEstandar() {
        return codigoEstandar;
    }

    public void setCodigoEstandar(String codigoEstandar) {
        this.codigoEstandar = codigoEstandar;
    }


    
    private Long id;
    private String businessKey;
    private String claveHita;
    private String dominio;
    private long orden;
    private Integer validacion;
    private String responsable;
    private String telefono;
    private String fax;
    private String email;
    private Integer sexoResponsable;
    private Integer numfoto1;
    private Integer numfoto2;
    private Integer numfoto3;
    private Integer numfoto4;
    private Archivo fotop;
    private Archivo fotog;
    private Archivo logoh;
    private Archivo logov;
    private Archivo logos;
    private Archivo logot;
    private String codigoEstandar;



    private UnidadAdministrativa padre;
    private List<UnidadAdministrativa> hijos;
    private Tratamiento tratamiento;
    private Set edificios;
    private Set<Personal> personal;
    private Set<Normativa> normativas;
    private Set<ProcedimientoLocal> procedimientos;
    private Set<Tramite> tramites;
    private Set<UnidadMateria> unidadesMaterias;
    private Set usuarios = new HashSet();
    private List<FichaUA> fichasUA;
    private EspacioTerritorial espacioTerrit;
    private Date fechaUltimaActualizacion;

    public final static int HOMBRE = 1;
    public final static int MUJER = 2;

    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UnidadAdministrativa)) return false;

        final UnidadAdministrativa unidadAdministrativa = (UnidadAdministrativa) o;

        if (id != null ? !id.equals(unidadAdministrativa.id) : unidadAdministrativa.id != null) return false;

        return true;
    }

    public int hashCode() {
        return (id != null ? id.hashCode() : 0);
    }
    
    
    private Set<FichaUA> todasfichas;


	@Override
	public String toString() {
		String nombre =getTraduccion()!=null? ((TraduccionUA)getTraduccion()).getNombre() : "";
		return "UnidadAdministrativa [id=" + id + ", nombre=" + nombre + "]";
	}

	public Set<FichaUA> getTodasfichas() {
		return todasfichas;
	}

	public void setTodasfichas(Set<FichaUA> todasfichas) {
		this.todasfichas = todasfichas;
	}

	public Integer getNumfoto1() {
		return numfoto1;
	}

	public void setNumfoto1(Integer numfoto1) {
		this.numfoto1 = numfoto1;
	}

	public Integer getNumfoto2() {
		return numfoto2;
	}

	public void setNumfoto2(Integer numfoto2) {
		this.numfoto2 = numfoto2;
	}

	public Integer getNumfoto3() {
		return numfoto3;
	}

	public void setNumfoto3(Integer numfoto3) {
		this.numfoto3 = numfoto3;
	}

	public Integer getNumfoto4() {
		return numfoto4;
	}

	public void setNumfoto4(Integer numfoto4) {
		this.numfoto4 = numfoto4;
	}
    
	public Set<Tramite> getTramites() {
		return tramites;
	}

	public void setTramites(Set<Tramite> tramites) {
		this.tramites = tramites;
	}
 
     /**
     * M�todo que devuelve una Lista con los Ids de los Procedimientos relacionados con la UA. 
     * @author Indra
     * @return Devuelve un Lista con Ids de los Procedimientos relacionados
     */
	public List<Long> getIdsProcedimientos(){
		List<Long> idsList = new ArrayList<Long>();
		Iterator<ProcedimientoLocal> iterProce = this.procedimientos.iterator();
	  	while(iterProce.hasNext()){
	  		ProcedimientoLocal proceLocal= (ProcedimientoLocal)iterProce.next();
	  		idsList.add(proceLocal.getId());
	  	}
	  	return idsList;
	}
	
    /**
     * M�todo que devuelve una Lista con los Ids de las Normativas relacionados con la UA. 
     * @author Indra
     * @return Devuelve un Lista con Ids de los Normativas relacionados
     */
	public List<Long> getIdsNormativas(){
		List<Long> idsList = new ArrayList<Long>();
		Iterator<Normativa> iterNorma = this.normativas.iterator();
	  	while(iterNorma.hasNext()){
	  		Normativa normaLocal= (Normativa)iterNorma.next();
	  		idsList.add(normaLocal.getId());
	  	}
	  	return idsList;
	}
 
 
/*
	@Override
	public String toString() {
		return "UnidadAdministrativa [businessKey=" + businessKey
				+ ", claveHita=" + claveHita + ", codigoEstandar="
				+ codigoEstandar + ", dominio=" + dominio + ", edificios="
				+ edificios + ", email=" + email + ", espacioTerrit="
				+ espacioTerrit + ", fax=" + fax
				+ ", fechaUltimaActualizacion=" + fechaUltimaActualizacion
				+ ", fichasUA=" + fichasUA + ", fotog=" + fotog + ", fotop="
				+ fotop + ", hijos=" + hijos + ", id=" + id + ", logoh="
				+ logoh + ", logos=" + logos + ", logot=" + logot + ", logov="
				+ logov + ", normativas=" + normativas + ", numfoto1="
				+ numfoto1 + ", numfoto2=" + numfoto2 + ", numfoto3="
				+ numfoto3 + ", numfoto4=" + numfoto4 + ", orden=" + orden
				+ ", padre=" + padre + ", personal=" + personal
				+ ", procedimientos=" + procedimientos + ", responsable="
				+ responsable + ", sexoResponsable=" + sexoResponsable
				+ ", telefono=" + telefono + ", todasfichas=" + todasfichas
				+ ", tratamiento=" + tratamiento + ", unidadesMaterias="
				+ unidadesMaterias + ", usuarios=" + usuarios + ", validacion="
				+ validacion + "]";
	}
	*/
}
