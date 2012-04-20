package es.caib.rolsac.api.v1;

import java.io.Serializable;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.HechoVitalProcedimiento;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EdificioDelegate;

public class ProcedimientoCompletoDTO extends ProcedimientoDTO implements Serializable {

	private static final long serialVersionUID = 8799656445784716638L;

    private List<DocumentoDTO> documentos;
    private List<TramiteDTO> tramites;
    private Set<MateriaDTO> materias;
    private Set<NormativaDTO> normativas;
    private UnidadAdministrativaDTO organoCompetente;
    private UnidadAdministrativaDTO unidadAdministrativa;
    private IniciacionDTO iniciacion;
    private Set<HechoVitalProcedimientoDTO> hechosVitalesProcedimientos;
    private Set<EdificioDTO> edificios;
    private FamiliaDTO familia;

    public ProcedimientoCompletoDTO(ProcedimientoLocal pl, String idioma) {
    	super(pl, idioma);

    	this.tramites = new LinkedList<TramiteDTO>();
    	//this.tramites = pl.getTramites();
    	for( Tramite t : pl.getTramites() )
    	{
    		this.tramites.add( new TramiteDTO( t, idioma ) );
    	}
    	Collections.sort( this.tramites );

    	this.documentos = new LinkedList<DocumentoDTO>();
    	for( Documento documento : pl.getDocumentos() )
    	{
    		if (documento != null) {
    			//por algún motivo, en ocasiones el documento es nulo
    			this.documentos.add( new DocumentoDTO( documento, idioma ) );
    		}
    		
    	}
    	Collections.sort( this.documentos );

    	this.unidadAdministrativa = new UnidadAdministrativaDTO( pl.getUnidadAdministrativa(), idioma, true );
    	if (pl.getOrganResolutori() != null) {
    		this.organoCompetente = new UnidadAdministrativaDTO( pl.getOrganResolutori(), idioma );
    	}

    	this.materias = new TreeSet<MateriaDTO>();
    	for (Object obj: pl.getMaterias()){
    		this.materias.add( new MateriaDTO( (Materia)obj, idioma));
    	}

    	//this.normativas = pl.getNormativas();
    	this.normativas = new TreeSet<NormativaDTO>();
    	for( Normativa normativa : pl.getNormativas() )
    	{
    		this.normativas.add( new NormativaDTO( normativa, idioma ) );
    	}

    	if (pl.getIniciacion() != null) {
    		this.iniciacion = new IniciacionDTO( pl.getIniciacion(), idioma );
    	}

    	//this.hechosVitalesProcedimientos = pl.getHechosVitalesProcedimientos();
    	this.hechosVitalesProcedimientos = new TreeSet<HechoVitalProcedimientoDTO>();
    	for( HechoVitalProcedimiento hv : pl.getHechosVitalesProcedimientos() )
    	{
    		this.hechosVitalesProcedimientos.add( new HechoVitalProcedimientoDTO( hv ) );
    	}

    	if (pl.getFamilia()!= null) {
    		this.familia = new FamiliaDTO( pl.getFamilia(), idioma );
    	}

    	try {
    		EdificioDelegate edificioDelegate = DelegateUtil.getEdificioDelegate();
			//edificios = edificioDelegate.listarEdificiosUnidad( pl.getOrganResolutori().getId() );
			edificios = new TreeSet<EdificioDTO>();
			Set<Edificio> edificiosNoSer = edificioDelegate.listarEdificiosUnidad( pl.getOrganResolutori().getId() );
			for( Edificio e : edificiosNoSer )
			{
				edificios.add( new EdificioDTO( e, idioma, false ) );
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
    
    public List<TramiteDTO> getTramites() {
        return tramites;
    }

    public List<DocumentoDTO> getDocumentos() {
        return documentos;
    }

    public Set<MateriaDTO> getMaterias() {
        return materias;
    }

	public Set<NormativaDTO> getNormativas() {
		return normativas;
	}

	public IniciacionDTO getIniciacion() {
		return iniciacion;
	}

	public Set<HechoVitalProcedimientoDTO> getHechosVitalesProcedimientos() {
		return hechosVitalesProcedimientos;
	}

	public Set<EdificioDTO> getEdificios() {
		return edificios;
	}

	public FamiliaDTO getFamilia() {
		return familia;
	}

	public UnidadAdministrativaDTO getOrganoCompetente() {
		return organoCompetente;
	}

	public UnidadAdministrativaDTO getUnidadAdministrativa() {
		return unidadAdministrativa;
	}

	public String toString(){
		StringBuffer salida = new StringBuffer("---ProcedimientoCompletoDTO---\n");
		salida.append("  -documentos size: ");
		salida.append(documentos.size());
		salida.append("\n  -tramites size: ");
		salida.append(tramites.size());
		salida.append("\n  -materias size: ");
		salida.append(materias.size());
		salida.append("\n  -normativas size: ");
		salida.append(normativas.size());
		salida.append("\n  -edificios size: ");
		salida.append(edificios.size());

		return salida.toString();
	}
}
