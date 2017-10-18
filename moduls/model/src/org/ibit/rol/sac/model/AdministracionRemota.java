package org.ibit.rol.sac.model;

import java.util.Set;

/**
 * Bean que contiene los datos basicos necesarios para recibir los datos de una
 * Administración Remota(PORMAD)
 * 
 * @author arodrigo
 *
 */
public class AdministracionRemota implements ValueObject {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nombre;
    private String endpoint;
    private Integer profundidad;
    private String codigoEstandarUA;
    private EspacioTerritorial espacioTerrit;
    private Archivo logop;
    private Archivo logog;
    private String responsable;
    private Long version;

    //Identifica de manera unica a una AdministracionRemota
    private String idRemoto;

    private Set<UnidadAdministrativaRemota> unidadesRemotas;
    private Set<FichaRemota> fichasRemotas;
    private Set<ProcedimientoRemoto> procedimientosRemotos;
    private Set<ServicioRemoto> serviciosRemotos;
    private Set<EdificioRemoto> edificiosRemotos;
    private Set<TramiteRemoto> tramitesRemotos;
    private Set<NormativaRemota> normativasRemotas;
    private Set<DocumentoRemoto> documentosRemotos;

    public EspacioTerritorial getEspacioTerrit() {
        return espacioTerrit;
    }

    public void setEspacioTerrit(EspacioTerritorial espacioTerrit) {
        this.espacioTerrit = espacioTerrit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

	public Integer getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(Integer profundidad) {
		this.profundidad = profundidad;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

    public Set<FichaRemota> getFichasRemotas() {
        return fichasRemotas;
    }

    public void setFichasRemotas(Set<FichaRemota> fichasRemotas) {
        this.fichasRemotas = fichasRemotas;
    }
    
    public void addFichaRemota(FichaRemota fichaRemota){
    	fichasRemotas.add(fichaRemota);
    }
    
    public void removeFichaRemota(FichaRemota fichaRemota){
    	fichasRemotas.remove(fichaRemota);
    }

    public Set<ProcedimientoRemoto> getProcedimientosRemotos() {
        return procedimientosRemotos;
    }

    public void setProcedimientosRemotos(Set<ProcedimientoRemoto> procedimientosRemotos) {
        this.procedimientosRemotos = procedimientosRemotos;
    }
    
    public void addProcedimientoRemoto(ProcedimientoRemoto procedimientoRemoto){
    	procedimientosRemotos.add(procedimientoRemoto);
    }
    
    public void removeProcedimientoRemoto(ProcedimientoRemoto procedimientoRemoto){
    	procedimientosRemotos.remove(procedimientoRemoto);
    }
    
    public void addServicioRemoto(ServicioRemoto servicioRemoto){
    	serviciosRemotos.add(servicioRemoto);
    }
    
    public void removeServicioRemoto(ServicioRemoto servicioRemoto){
    	serviciosRemotos.remove(servicioRemoto);
    }
    
    public void addEdificioRemoto(EdificioRemoto edificioRemoto){
    	edificiosRemotos.add(edificioRemoto);
    }
    
    public void removeEdificioRemoto(EdificioRemoto edificioRemoto){
    	edificiosRemotos.remove(edificioRemoto);
    }
    
    public void addTramiteRemoto(TramiteRemoto tramiteRemoto){
    	tramitesRemotos.add(tramiteRemoto);
    }
    
    public void removeTramiteRemoto(TramiteRemoto tramiteRemoto){
    	tramitesRemotos.remove(tramiteRemoto);
    }
    
    public Set<UnidadAdministrativaRemota> getUnidadesRemotas() {
		return unidadesRemotas;
	}

	public void setUnidadesRemotas(Set<UnidadAdministrativaRemota> unidadesRemotas) {
		this.unidadesRemotas = unidadesRemotas;
	}
	
	public void addUnidadAdministrativaRemota(UnidadAdministrativaRemota administrativaRemota){
		unidadesRemotas.add(administrativaRemota);
	}

	public void removeUnidadAdministrativaRemota(UnidadAdministrativaRemota administrativaRemota){
		unidadesRemotas.remove(administrativaRemota);
	}

    public void addNormativaRemota(NormativaRemota normativaRemota){
    	normativasRemotas.add(normativaRemota);
    }

    public void removeNormativaRemota(NormativaRemota normativaRemota){
    	normativasRemotas.remove(normativaRemota);
    }

     public void addDocumentoRemoto(DocumentoRemoto documentoRemoto){
    	documentosRemotos.add(documentoRemoto);
    }

    public void removeDocumentoRemoto(DocumentoRemoto documentoRemoto){
    	documentosRemotos.remove(documentoRemoto);
    }

	public String getCodigoEstandarUA() {
		return codigoEstandarUA;
	}

	public void setCodigoEstandarUA(String codigoEstandarUA) {
		this.codigoEstandarUA = codigoEstandarUA;
	}
	
	/**
	 * Recoge el Id unico de una AdministrcionRemota
	 * @return Id unico de una AdministrcionRemota
	 */
	public String getIdRemoto() {
		return idRemoto;
	}
	
	public void setIdRemoto(String idRemoto) {
		this.idRemoto = idRemoto;
	}

	public Archivo getLogop() {
		return logop;
	}

	public void setLogop(Archivo logop) {
		this.logop = logop;
	}

	public Archivo getLogog() {
		return logog;
	}

	public void setLogog(Archivo logog) {
		this.logog = logog;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public Set<EdificioRemoto> getEdificiosRemotos() {
		return edificiosRemotos;
	}

	public void setEdificiosRemotos(Set<EdificioRemoto> edificiosRemotos) {
		this.edificiosRemotos = edificiosRemotos;
	}
	
	public Set<TramiteRemoto> getTramitesRemotos() {
		return tramitesRemotos;
	}

	public void setTramitesRemotos(Set<TramiteRemoto> tramitesRemotos) {
		this.tramitesRemotos = tramitesRemotos;
	}


	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}


    public Set<NormativaRemota> getNormativasRemotas() {
        return normativasRemotas;
    }

    public void setNormativasRemotas(Set<NormativaRemota> normativasRemotas) {
        this.normativasRemotas = normativasRemotas;
    }

    public Set<DocumentoRemoto> getDocumentosRemotos() {
        return documentosRemotos;
    }

    public void setDocumentosRemotos(Set<DocumentoRemoto> documentosRemotos) {
        this.documentosRemotos = documentosRemotos;
    }

	/**
	 * @return the serviciosRemotos
	 */
	public Set<ServicioRemoto> getServiciosRemotos() {
		return serviciosRemotos;
	}

	/**
	 * @param serviciosRemotos the serviciosRemotos to set
	 */
	public void setServiciosRemotos(Set<ServicioRemoto> serviciosRemotos) {
		this.serviciosRemotos = serviciosRemotos;
	}
}
