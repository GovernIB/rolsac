package es.caib.rolsac.api.v2.rolsac.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.AgrupacionHechoVital;
import org.ibit.rol.sac.model.AgrupacionMateria;
import org.ibit.rol.sac.model.Boletin;
import org.ibit.rol.sac.model.CatalegDocuments;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.Enlace;
import org.ibit.rol.sac.model.EspacioTerritorial;
import org.ibit.rol.sac.model.ExcepcioDocumentacio;
import org.ibit.rol.sac.model.Familia;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.Formulario;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.IconoFamilia;
import org.ibit.rol.sac.model.IconoMateria;
import org.ibit.rol.sac.model.Iniciacion;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.MateriaAgrupacionM;
import org.ibit.rol.sac.model.NormativaExterna;
import org.ibit.rol.sac.model.NormativaLocal;
import org.ibit.rol.sac.model.PerfilCiudadano;
import org.ibit.rol.sac.model.Personal;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.PublicoObjetivo;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.Taxa;
import org.ibit.rol.sac.model.Tipo;
import org.ibit.rol.sac.model.TipoAfectacion;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.UnidadMateria;
import org.ibit.rol.sac.model.Usuario;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.butlleti.ButlletiCriteria;
import es.caib.rolsac.api.v2.butlleti.ButlletiDTO;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsCriteria;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsDTO;
import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentDTO;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.edifici.EdificiCriteria;
import es.caib.rolsac.api.v2.edifici.EdificiDTO;
import es.caib.rolsac.api.v2.enllac.EnllacCriteria;
import es.caib.rolsac.api.v2.enllac.EnllacDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioCriteria;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioDTO;
import es.caib.rolsac.api.v2.familia.FamiliaCriteria;
import es.caib.rolsac.api.v2.familia.FamiliaDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUADTO;
import es.caib.rolsac.api.v2.formulari.FormulariCriteria;
import es.caib.rolsac.api.v2.formulari.FormulariDTO;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;
import es.caib.rolsac.api.v2.iniciacio.IniciacioCriteria;
import es.caib.rolsac.api.v2.iniciacio.IniciacioDTO;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioCriteria;
import es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioDTO;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.perfil.PerfilCriteria;
import es.caib.rolsac.api.v2.perfil.PerfilDTO;
import es.caib.rolsac.api.v2.personal.PersonalCriteria;
import es.caib.rolsac.api.v2.personal.PersonalDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentUtils;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.taxa.TaxaCriteria;
import es.caib.rolsac.api.v2.taxa.TaxaDTO;
import es.caib.rolsac.api.v2.tipus.TipusCriteria;
import es.caib.rolsac.api.v2.tipus.TipusDTO;
import es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioCriteria;
import es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioDTO;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaCriteria;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaDTO;
import es.caib.rolsac.api.v2.usuari.UsuariCriteria;
import es.caib.rolsac.api.v2.usuari.UsuariDTO;

/**
 * SessionBean para consultas de ROLSAC.
 *
 * @ejb.bean
 *  name="sac/api/RolsacQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
@SuppressWarnings("deprecation")
public class RolsacQueryServiceEJB extends HibernateEJB {

    private static final long serialVersionUID = 6406577402253277346L;

    private static Log log = LogFactory.getLog(RolsacQueryServiceEJB.class);

    private static final String HQL_TRADUCCIONES_ALIAS = "trad";
    private static final String HQL_PROCEDIMIENTO_CLASS = "ProcedimientoLocal";
    private static final String HQL_PROCEDIMIENTO_ALIAS = "p";
    private static final String HQL_MATERIA_CLASS = "Materia";
    private static final String HQL_MATERIA_ALIAS = "m";
    private static final String HQL_CATALOGO_DOCUMENTOS_CLASS = "CatalegDocuments";
    private static final String HQL_CATALOGO_DOCUMENTOS_ALIAS = "cd";
    private static final String HQL_EXCEPCION_DOCUMENTACION_CLASS = "ExcepcioDocumentacio";
    private static final String HQL_EXCEPCION_DOCUMENTACION_ALIAS = "ex";
    private static final String HQL_TRAMIT_CLASS = "Tramite";
    private static final String HQL_TRAMIT_ALIAS = "t";
    private static final String HQL_UA_CLASS = "UnidadAdministrativa";
    private static final String HQL_UA_ALIAS = "ua";
    private static final String HQL_FITXA_CLASS = "Ficha";
    private static final String HQL_FITXA_ALIAS = "fi";
    private static final String HQL_NORMATIVA_LOCAL_CLASS = "NormativaLocal";
    private static final String HQL_NORMATIVA_EXTERNA_CLASS = "NormativaExterna";
    private static final String HQL_NORMATIVA_ALIAS = "n";
    private static final String HQL_PERSONAL_CLASS = "Personal";
    private static final String HQL_PERSONAL_ALIAS = "per";
    private static final String HQL_DOC_TRAMITE_CLASS = "DocumentTramit";
    private static final String HQL_DOC_TRAMITE_ALIAS = "dt";
    private static final String HQL_USUARI_CLASS = "Usuario";
    private static final String HQL_USUARI_ALIAS = "usu";
    private static final String HQL_TAXA_CLASS = "Taxa";
    private static final String HQL_TAXA_ALIAS = "ta"; 
    private static final String HQL_AGRUPACIO_FET_VITAL_CLASS = "AgrupacionHechoVital";
    private static final String HQL_AGRUPACIO_FET_VITAL_ALIAS = "afv"; 
    private static final String HQL_AGRUPACIO_MATERIA_CLASS = "AgrupacionMateria";
    private static final String HQL_AGRUPACIO_MATERIA_ALIAS = "am";
    private static final String HQL_BUTLLETI_CLASS = "Boletin";
    private static final String HQL_BUTLLETI_ALIAS = "b";
    private static final String HQL_DOCUMENT_CLASS = "Documento";
    private static final String HQL_DOCUMENT_ALIAS = "doc";
    private static final String HQL_EDIFICI_CLASS = "Edificio";
    private static final String HQL_EDIFICI_ALIAS = "ed";
    private static final String HQL_FET_VITAL_CLASS = "HechoVital";
    private static final String HQL_FET_VITAL_ALIAS = "fv";
    private static final String HQL_ENLLAC_CLASS = "Enlace";
    private static final String HQL_ENLLAC_ALIAS = "en";
    private static final String HQL_ESPAI_TERRITORIAL_CLASS = "EspacioTerritorial";
    private static final String HQL_ESPAI_TERRITORIAL_ALIAS = "et";
    private static final String HQL_FAMILIA_CLASS = "Familia";
    private static final String HQL_FAMILIA_ALIAS = "fa";
    private static final String HQL_PUBLIC_OBJECTIU_CLASS = "PublicoObjetivo";
    private static final String HQL_PUBLIC_OBJECTIU_ALIAS = "po";
    private static final String HQL_FITXA_UA_CLASS = "FichaUA";
    private static final String HQL_FITXA_UA_ALIAS = "fua";
    private static final String HQL_FORMULARI_CLASS = "Formulario";
    private static final String HQL_FORMULARI_ALIAS = "form";
    private static final String HQL_ICONA_FAMILIA_CLASS = "IconoFamilia";
    private static final String HQL_ICONA_FAMILIA_ALIAS = "iFam";
    private static final String HQL_ICONA_MATERIA_CLASS = "IconoMateria";
    private static final String HQL_ICONA_MATERIA_ALIAS = "iFMat";
    private static final String HQL_MATERIA_AGRUPACIO_CLASS = "MateriaAgrupacionM";
    private static final String HQL_MATERIA_AGRUPACIO_ALIAS = "ma";
    private static final String HQL_PERFIL_CLASS = "PerfilCiudadano";
    private static final String HQL_PERFIL_ALIAS = "pc";
    private static final String HQL_SECCIO_CLASS = "Seccion";
    private static final String HQL_SECCIO_ALIAS = "s";
    private static final String HQL_TIPUS_CLASS = "Tipo";
    private static final String HQL_TIPUS_ALIAS = "ti";
    private static final String HQL_TIPUS_AFECTACIO_CLASS = "TipoAfectacion";
    private static final String HQL_TIPUS_AFECTACIO_ALIAS = "tia";
    private static final String HQL_UNITAT_MATERIA_CLASS = "UnidadMateria";
    private static final String HQL_UNITAT_MATERIA_ALIAS = "um";
    private static final String HQL_INICIACIO_CLASS = "Iniciacion";
    private static final String HQL_INICIACIO_ALIAS = "inici";

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    @Override
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
    
    /**
     * Obtiene excepción de documentación.
     * @param excepcioDocumentacioCriteria
     * @return ExcepcioDocumentacioDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ExcepcioDocumentacioDTO obtenirExcepcioDocumentacio(ExcepcioDocumentacioCriteria excepcioDocumentacioCriteria) {
    	
        List<CriteriaObject> criteris;
        ExcepcioDocumentacioDTO excepcioDocumentacioDTO = null;
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    ExcepcioDocumentacioCriteria.class,
                    HQL_EXCEPCION_DOCUMENTACION_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, excepcioDocumentacioCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_EXCEPCION_DOCUMENTACION_CLASS, HQL_EXCEPCION_DOCUMENTACION_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_EXCEPCION_DOCUMENTACION_ALIAS, 
                    entities, 
                    excepcioDocumentacioCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            ExcepcioDocumentacio excepcioDocumentacio = (ExcepcioDocumentacio) query.uniqueResult();
            if (excepcioDocumentacio != null) {
                excepcioDocumentacioDTO = (ExcepcioDocumentacioDTO) BasicUtils.entityToDTO(ExcepcioDocumentacioDTO.class, excepcioDocumentacio, excepcioDocumentacioCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return excepcioDocumentacioDTO;    	

    }    
    
    /**
     * Obtiene excepciones de documentación.
     * @param excepcioDocumentacioCriteria
     * @return List<ExcepcioDocumentacioDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<ExcepcioDocumentacioDTO> llistarExcepcionsDocumentacio(ExcepcioDocumentacioCriteria excepcioDocumentacioCriteria) {
        List<CriteriaObject> criteris;
        List<ExcepcioDocumentacioDTO> excepcioDocumentacioDTOList = new ArrayList<ExcepcioDocumentacioDTO>();
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    ExcepcioDocumentacioCriteria.class,
                    HQL_EXCEPCION_DOCUMENTACION_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, excepcioDocumentacioCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_EXCEPCION_DOCUMENTACION_CLASS, HQL_EXCEPCION_DOCUMENTACION_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_EXCEPCION_DOCUMENTACION_ALIAS, 
                    entities, 
                    excepcioDocumentacioCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<ExcepcioDocumentacio> excepcioDocumentacioResult = (List<ExcepcioDocumentacio>) query.list();
            for (ExcepcioDocumentacio excepcioDocumentacio : excepcioDocumentacioResult) {
                excepcioDocumentacioDTOList.add((ExcepcioDocumentacioDTO) BasicUtils.entityToDTO(
                        ExcepcioDocumentacioDTO.class, 
                        excepcioDocumentacio, 
                        excepcioDocumentacioCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }
        return excepcioDocumentacioDTOList;
    }    
    
    /**
     * Obtiene un catalogo de documentos.
     * @param catalegDocumentsCriteria
     * @return CatalegDocumentsDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public CatalegDocumentsDTO obtenirCatalegDocuments(CatalegDocumentsCriteria catalegDocumentsCriteria) {
    	
        List<CriteriaObject> criteris;
        CatalegDocumentsDTO catalegDocumentsDTO = null;
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    CatalegDocumentsCriteria.class,
                    HQL_CATALOGO_DOCUMENTOS_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, catalegDocumentsCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_CATALOGO_DOCUMENTOS_CLASS, HQL_CATALOGO_DOCUMENTOS_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_CATALOGO_DOCUMENTOS_ALIAS, 
                    entities, 
                    catalegDocumentsCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            CatalegDocuments catalegDocuments = (CatalegDocuments) query.uniqueResult();
            if (catalegDocuments != null) {
                catalegDocumentsDTO = (CatalegDocumentsDTO) BasicUtils.entityToDTO(CatalegDocumentsDTO.class, catalegDocuments, catalegDocumentsCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return catalegDocumentsDTO;    	

    }    
    
    /**
     * Obtiene catálogos de documentos.
     * @param catalegDocumentsCriteria
     * @return List<CatalegDocumentsDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<CatalegDocumentsDTO> llistarCatalegsDocuments(CatalegDocumentsCriteria catalegDocumentsCriteria) {
        List<CriteriaObject> criteris;
        List<CatalegDocumentsDTO> catalegDocumentsDTOList = new ArrayList<CatalegDocumentsDTO>();
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    CatalegDocumentsCriteria.class,
                    HQL_CATALOGO_DOCUMENTOS_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, catalegDocumentsCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_CATALOGO_DOCUMENTOS_CLASS, HQL_CATALOGO_DOCUMENTOS_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_CATALOGO_DOCUMENTOS_ALIAS, 
                    entities, 
                    catalegDocumentsCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<CatalegDocuments> catalegDocumentsResult = (List<CatalegDocuments>) query.list();
            for (CatalegDocuments catalegDocuments: catalegDocumentsResult) {
                catalegDocumentsDTOList.add((CatalegDocumentsDTO) BasicUtils.entityToDTO(
                        CatalegDocumentsDTO.class, 
                        catalegDocuments, 
                        catalegDocumentsCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }
        return catalegDocumentsDTOList;
    }
    
    
    /**
     * Obtiene un procedimiento.
     * @param procedimentCriteria
     * @return ProcedimentDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ProcedimentDTO obtenirProcediment(ProcedimentCriteria procedimentCriteria) {
    	
        List<CriteriaObject> criteris = new ArrayList<CriteriaObject>();
        ProcedimentDTO procedimentDTO = null;
        Session session = null;
        
        try {
        	
            ProcedimentUtils.parseActiu(criteris, procedimentCriteria, HQL_PROCEDIMIENTO_ALIAS);
            criteris.addAll(BasicUtils.parseCriterias(
                    ProcedimentCriteria.class,
                    HQL_PROCEDIMIENTO_ALIAS,
                    HQL_TRADUCCIONES_ALIAS,
                    procedimentCriteria));

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_PROCEDIMIENTO_CLASS,HQL_PROCEDIMIENTO_ALIAS));
            QueryBuilder qb = new QueryBuilder(
                    HQL_PROCEDIMIENTO_ALIAS, 
                    entities, 
                    procedimentCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            ProcedimientoLocal procedimentLocal = (ProcedimientoLocal)query.uniqueResult();
            
            if (procedimentLocal != null) {
        		procedimentDTO = (ProcedimentDTO)BasicUtils.entityToDTO(
        				ProcedimentDTO.class,
        				procedimentLocal,
        				procedimentCriteria.getIdioma()
        			);
            }
            
        } catch (HibernateException e) {
        	
            log.error(e);
            throw new EJBException(e);
            
        } catch (CriteriaObjectParseException e) {
        	
            log.error(e);
            throw new EJBException(e);
            
        } catch (QueryBuilderException e) {
        	
            log.error(e);
            throw new EJBException(e);
            
        } finally {
        	
            close(session);
            
        }

        return procedimentDTO;
        
    }

    /**
     * Obtiene procedimientos.
     * @param procedimentCriteria
     * @return List<ProcedimentDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<ProcedimentDTO> llistarProcediments(ProcedimentCriteria procedimentCriteria) {
    	
        List<ProcedimentDTO> procedimentDTOList = new ArrayList<ProcedimentDTO>();
        List<CriteriaObject> criteris = new ArrayList<CriteriaObject>();
        Session session = null;
        
        try {
        	
            ProcedimentUtils.parseActiu(criteris, procedimentCriteria, HQL_PROCEDIMIENTO_ALIAS);            
            criteris.addAll(BasicUtils.parseCriterias(
                    ProcedimentCriteria.class,
                    HQL_PROCEDIMIENTO_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, 
                    procedimentCriteria));
            
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_PROCEDIMIENTO_CLASS, HQL_PROCEDIMIENTO_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_PROCEDIMIENTO_ALIAS, 
                    entities, 
                    procedimentCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<ProcedimientoLocal> procedimentsResult = (List<ProcedimientoLocal>)query.list();
            
            for (ProcedimientoLocal procediment: procedimentsResult) {
            	procedimentDTOList.add(
						(ProcedimentDTO)BasicUtils.entityToDTO(
							ProcedimentDTO.class, 
							procediment, 
							procedimentCriteria.getIdioma()
						)
					);
           }
            
        } catch (HibernateException e) {
        	
            log.error(e);
            throw new EJBException(e);
            
        } catch (CriteriaObjectParseException e) {
        	
            log.error(e);
            throw new EJBException(e);
            
        } catch (QueryBuilderException e) {
        	
            log.error(e);
            throw new EJBException(e);
            
        } finally {
        	
            close(session);
            
        }

        return procedimentDTOList;
        
    }
    
    /**
     * Cuenta procedimientos.
     * @param procedimentCriteria
     * @return int
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public int getNumProcediments(ProcedimentCriteria procedimentCriteria) {
    	
        List<CriteriaObject> criteris = new ArrayList<CriteriaObject>();
        Session session = null;
        
        try {
        	
            ProcedimentUtils.parseActiu(criteris, procedimentCriteria, HQL_PROCEDIMIENTO_ALIAS);            
            criteris.addAll(BasicUtils.parseCriterias(
                    ProcedimentCriteria.class,
                    HQL_PROCEDIMIENTO_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, 
                    procedimentCriteria));
            
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_PROCEDIMIENTO_CLASS, HQL_PROCEDIMIENTO_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_PROCEDIMIENTO_ALIAS, 
                    entities, 
                    procedimentCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS, 
                    true);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            return getNumberResults(query);
            
        } catch (HibernateException e) {
        	
            log.error(e);
            throw new EJBException(e);
            
        } catch (CriteriaObjectParseException e) {
        	
            log.error(e);
            throw new EJBException(e);
            
        } catch (QueryBuilderException e) {
        	
            log.error(e);
            throw new EJBException(e);
            
        } finally {
        	
            close(session);
            
        }

    }

    /**
     * Obtiene una materia.
     * @param materiaCriteria
     * @return MateriaDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public MateriaDTO obtenirMateria(MateriaCriteria materiaCriteria) {
        List<CriteriaObject> criteris;
        MateriaDTO materiaDTO = null;
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    MateriaCriteria.class,
                    HQL_MATERIA_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, materiaCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_MATERIA_CLASS,HQL_MATERIA_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_MATERIA_ALIAS, 
                    entities, 
                    materiaCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            Materia materia = (Materia) query.uniqueResult();
            if (materia != null) {
                materiaDTO = (MateriaDTO) BasicUtils.entityToDTO(MateriaDTO.class, materia, materiaCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return materiaDTO;
    }
    
    /**
     * Obtiene materias.
     * @param materiaCriteria
     * @return List<MateriaDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<MateriaDTO> llistarMateries(MateriaCriteria materiaCriteria) {
        List<CriteriaObject> criteris;
        List<MateriaDTO> materiaDTOList = new ArrayList<MateriaDTO>();
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    MateriaCriteria.class,
                    HQL_MATERIA_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, materiaCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_MATERIA_CLASS, HQL_MATERIA_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_MATERIA_ALIAS, 
                    entities, 
                    materiaCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<Materia> materiaResult = (List<Materia>) query.list();
            for (Materia materia: materiaResult) {
                materiaDTOList.add((MateriaDTO) BasicUtils.entityToDTO(
                        MateriaDTO.class, 
                        materia, 
                        materiaCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }
        return materiaDTOList;
    }
    
    /**
     * Obtiene un tramite.
     * @param tramiteCriteria
     * @return TramiteDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public TramitDTO obtenirTramit(TramitCriteria tramitCriteria) {
        List<CriteriaObject> criteris;
        TramitDTO tramitDTO = null;
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    TramitCriteria.class,
                    HQL_TRAMIT_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, tramitCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_TRAMIT_CLASS, HQL_TRAMIT_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_TRAMIT_ALIAS, 
                    entities, 
                    tramitCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            Tramite tramit = (Tramite) query.uniqueResult();
            if (tramit != null) {
                tramitDTO = (TramitDTO) BasicUtils.entityToDTO(
                        TramitDTO.class, 
                        tramit,
                        tramitCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return tramitDTO;
    }
    
    /**
     * Obtiene tramites.
     * @param tramiteCriteria
     * @return List<TramiteDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<TramitDTO> llistarTramit(TramitCriteria tramitCriteria) {
        List<TramitDTO> tramitDTOList = new ArrayList<TramitDTO>();
        List<CriteriaObject> criteris;
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    TramitCriteria.class,
                    HQL_TRAMIT_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, tramitCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_TRAMIT_CLASS, HQL_TRAMIT_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_TRAMIT_ALIAS, 
                    entities, 
                    tramitCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<Tramite> tramitsResult = (List<Tramite>) query.list();
            for (Tramite tramit: tramitsResult) {
                tramitDTOList.add((TramitDTO) BasicUtils.entityToDTO(
                        TramitDTO.class, 
                        tramit, 
                        tramitCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return tramitDTOList;
    }
    
    /**
     * Obtiene una unidad administrativa.
     * @param uaCriteria
     * @return UnitatAdministrativaDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(UnitatAdministrativaCriteria uaCriteria) {
        List<CriteriaObject> criteris;
        UnitatAdministrativaDTO uaDTO = null;
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    UnitatAdministrativaCriteria.class,
                    HQL_UA_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, uaCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_UA_ALIAS, 
                    entities, 
                    uaCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            UnidadAdministrativa ua = (UnidadAdministrativa) query.uniqueResult();
            if (ua != null) {
                uaDTO = (UnitatAdministrativaDTO) BasicUtils.entityToDTO(
                        UnitatAdministrativaDTO.class, 
                        ua,
                        uaCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return uaDTO;
    }

    /**
     * Obtiene unidades administrativas.
     * @param uaCriteria
     * @return List<UnitatAdministrativaDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(UnitatAdministrativaCriteria uaCriteria) {
        List<UnitatAdministrativaDTO> uaDTOList = new ArrayList<UnitatAdministrativaDTO>();
        List<CriteriaObject> criteris;
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    UnitatAdministrativaCriteria.class,
                    HQL_UA_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, uaCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_UA_ALIAS, 
                    entities, 
                    uaCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<UnidadAdministrativa> uaResult = (List<UnidadAdministrativa>) query.list();
            for (UnidadAdministrativa ua: uaResult) {
                uaDTOList.add((UnitatAdministrativaDTO) BasicUtils.entityToDTO(
                        UnitatAdministrativaDTO.class, 
                        ua, 
                        uaCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return uaDTOList;
    }    
    
    /**
     * Obtiene un hecho vital.
     * @param fetVitalCriteria
     * @return FetVitalDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public FetVitalDTO obtenirFetVital(FetVitalCriteria fetVitalCriteria) {
        List<CriteriaObject> criteris;
        FetVitalDTO fetVitalDTO = null;
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    FetVitalCriteria.class,
                    HQL_FET_VITAL_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, fetVitalCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FET_VITAL_CLASS, HQL_FET_VITAL_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_FET_VITAL_ALIAS, 
                    entities, 
                    fetVitalCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            HechoVital fetVital = (HechoVital) query.uniqueResult();
            if (fetVital != null) {
                fetVitalDTO = (FetVitalDTO) BasicUtils.entityToDTO(
                        FetVitalDTO.class, 
                        fetVital,
                        fetVitalCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return fetVitalDTO;
    }
    
    /**
     * Obtiene hechos vitales.
     * @param fetVitalCriteria
     * @return FetVitalDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<FetVitalDTO> llistarFetsVitals(FetVitalCriteria fetVitalCriteria) {
        List<CriteriaObject> criteris;
        List<FetVitalDTO> fetVitalDTOList = new ArrayList<FetVitalDTO>();
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    FetVitalCriteria.class,
                    HQL_FET_VITAL_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, fetVitalCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FET_VITAL_CLASS, HQL_FET_VITAL_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_FET_VITAL_ALIAS, 
                    entities, 
                    fetVitalCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<HechoVital> fvResult = (List<HechoVital>) query.list();
            for (HechoVital fv: fvResult) {
                fetVitalDTOList.add((FetVitalDTO) BasicUtils.entityToDTO(
                        FetVitalDTO.class, fv, fetVitalCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return fetVitalDTOList;
    }

    /**
     * Obtiene una ficha.
     * @param fitxaCriteria
     * @return FitxaDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public FitxaDTO obtenirFitxa(FitxaCriteria fitxaCriteria) {
    	
        List<CriteriaObject> criteris;
        FitxaDTO fitxaDTO = null;
        Session session = null;
        
        // Comprobamos si solicitan registros visibles.
        boolean soloRegistrosVisibles = ( fitxaCriteria.getActiu() == null ) // Si el campo no se especifica, mostramos sólo visibles por defecto.
        		|| ( fitxaCriteria.getActiu() != null && fitxaCriteria.getActiu().booleanValue() ); 
        // Ponemos campo a null para que no se procese como Criteria para la consulta HQL (i.e. para que no lo parsee BasicUtils.parseCriterias()).
        fitxaCriteria.setActiu(null);

        try {
        	
            criteris = BasicUtils.parseCriterias(
                    FitxaCriteria.class,
                    HQL_FITXA_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, fitxaCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FITXA_CLASS, HQL_FITXA_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_FITXA_ALIAS, 
                    entities, 
                    fitxaCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            Ficha fitxa = (Ficha)query.uniqueResult();
            
            if (fitxa != null) {
            	
                if ( (soloRegistrosVisibles && fitxa.getIsVisible())	// Si nos solicitan recursos visibles, sólo lo añadimos a la lista de resultados si cumple con ello.
    					|| !soloRegistrosVisibles ) {					// Si no los solicitan sólo visibles, los añadimos sin comprobar nada más.
            		
                	fitxaDTO = (FitxaDTO)BasicUtils.entityToDTO(FitxaDTO.class, fitxa, fitxaCriteria.getIdioma());
            		
            	}

            }
                        
        } catch (HibernateException e) {
        	
            log.error(e);
            throw new EJBException(e);
            
        } catch (CriteriaObjectParseException e) {
        	
            log.error(e);
            throw new EJBException(e);
            
        } catch (QueryBuilderException e) {
        	
            log.error(e);
            throw new EJBException(e);
            
        } finally {
        	
            close(session);
            
        }

        return fitxaDTO;
        
    }
    
    /**
     * Obtiene fichas.
     * @param fitxaCriteria
     * @return List<FitxaDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<FitxaDTO> llistarFitxes(FitxaCriteria fitxaCriteria) {
    	
        List<FitxaDTO> fitxaDTOList = new ArrayList<FitxaDTO>();
        List<CriteriaObject> criteris;
        Session session = null;
        
        // Comprobamos si solicitan registros visibles.
        boolean soloRegistrosVisibles = ( fitxaCriteria.getActiu() == null ) // Si el campo no se especifica, mostramos sólo visibles por defecto.
        		|| ( fitxaCriteria.getActiu() != null && fitxaCriteria.getActiu().booleanValue() );  

        // Ponemos campo a null para que no se procese como Criteria para la consulta HQL (i.e. para que no lo parsee BasicUtils.parseCriterias()).
        fitxaCriteria.setActiu(null);

        try {
        	
            criteris = BasicUtils.parseCriterias(
                    FitxaCriteria.class,
                    HQL_FITXA_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, fitxaCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FITXA_CLASS, HQL_FITXA_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_FITXA_ALIAS, 
                    entities, 
                    fitxaCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<Ficha> fitxaResult = (List<Ficha>) query.list();
            
            for (Ficha ficha: fitxaResult) {
            	
            	if ( (soloRegistrosVisibles && ficha.getIsVisible())	// Si nos solicitan recursos visibles, sólo lo añadimos a la lista de resultados si cumple con ello.
						|| !soloRegistrosVisibles ) {					// Si no los solicitan sólo visibles, los añadimos sin comprobar nada más.
            		
            		fitxaDTOList.add(
            			(FitxaDTO)BasicUtils.entityToDTO(
                            FitxaDTO.class, 
                            ficha, 
                            fitxaCriteria.getIdioma()
                        )
                    );
            		
            	}
            	            	                
            }
            
        } catch (HibernateException e) {
        	
            log.error(e);
            throw new EJBException(e);
            
        } catch (CriteriaObjectParseException e) {
        	
            log.error(e);
            throw new EJBException(e);
            
        } catch (QueryBuilderException e) {
        	
            log.error(e);
            throw new EJBException(e);
            
        } finally {
        	
            close(session);
            
        }

        return fitxaDTOList;
        
    }
    
    /**
     * Obtiene un documento tramite.
     * @param documentTramitCriteria
     * @return DocumentTramitDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public DocumentTramitDTO obtenirDocumentTramit(DocumentTramitCriteria documentTramitCriteria) {        
        DocumentTramitDTO docTramitDTO = null;
        List<CriteriaObject> criteris;
        Session session = null;

        try {            
            criteris = BasicUtils.parseCriterias(
                    DocumentTramitCriteria.class,
                    HQL_DOC_TRAMITE_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, documentTramitCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_DOC_TRAMITE_CLASS, HQL_DOC_TRAMITE_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_DOC_TRAMITE_ALIAS, 
                    entities, 
                    documentTramitCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            DocumentTramit docTramit = (DocumentTramit) query.uniqueResult();
            if (docTramit != null) {
                docTramitDTO = (DocumentTramitDTO) BasicUtils.entityToDTO(
                        DocumentTramitDTO.class,
                        docTramit, 
                        documentTramitCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }
    
        return docTramitDTO;
    }
    
    /**
     * Obtiene documentos tramite.
     * @param documentTramitCriteria
     * @return List<DocumentTramitDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<DocumentTramitDTO> llistarDocumentTramit(DocumentTramitCriteria documentTramitCriteria) {        
        List<DocumentTramitDTO> documentsTramitDTOList = new ArrayList<DocumentTramitDTO>();
        List<CriteriaObject> criteris;
        Session session = null;

        try {            
            criteris = BasicUtils.parseCriterias(
                    DocumentTramitCriteria.class,
                    HQL_DOC_TRAMITE_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, documentTramitCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_DOC_TRAMITE_CLASS, HQL_DOC_TRAMITE_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_DOC_TRAMITE_ALIAS, 
                    entities, 
                    documentTramitCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<DocumentTramit> documentTramitsResult = (List<DocumentTramit>) query.list();
            for (DocumentTramit documentTramit : documentTramitsResult) {
                documentsTramitDTOList.add((DocumentTramitDTO) BasicUtils.entityToDTO(
                        DocumentTramitDTO.class,
                        documentTramit, 
                        documentTramitCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }
    
        return documentsTramitDTOList;
    }
    
    /**
     * Obtiene una normativa.
     * @param normativaCriteria
     * @return NormativaDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public NormativaDTO obtenirNormativa(NormativaCriteria normativaCriteria) {
        NormativaDTO normativaDTO = null;
        List<CriteriaObject> criteris;
        Session session = null;
        
        boolean incluirExternas = (normativaCriteria.getIncluirExternas() == null)? false : normativaCriteria.getIncluirExternas();
        normativaCriteria.setIncluirExternas(null); // Para evitar que se parsee como los demas criterias

        try {
            criteris = BasicUtils.parseCriterias(NormativaCriteria.class, HQL_NORMATIVA_ALIAS, HQL_TRADUCCIONES_ALIAS, normativaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_NORMATIVA_LOCAL_CLASS, HQL_NORMATIVA_ALIAS));
            QueryBuilder qb = new QueryBuilder("n", entities, normativaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            session = getSession();
            Query query = qb.createQuery(session);
            NormativaLocal normativaLocal = (NormativaLocal) query.uniqueResult();
            if (normativaLocal != null) {
                normativaDTO = (NormativaDTO) BasicUtils.entityToDTO(
                        NormativaDTO.class, 
                        normativaLocal,
                        normativaCriteria.getIdioma());
            } else if (incluirExternas) {
                criteris = BasicUtils.parseCriterias(NormativaCriteria.class, HQL_NORMATIVA_ALIAS, HQL_TRADUCCIONES_ALIAS, normativaCriteria);
                entities = new ArrayList<FromClause>();
                entities.add(new FromClause(HQL_NORMATIVA_EXTERNA_CLASS, HQL_NORMATIVA_ALIAS));
                qb = new QueryBuilder(HQL_NORMATIVA_ALIAS, entities, normativaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
                qb.extendCriteriaObjects(criteris);
                query = qb.createQuery(session);
                NormativaExterna normativaExterna = (NormativaExterna) query.uniqueResult();
                
                if (normativaExterna != null) {
                    normativaDTO = (NormativaDTO) BasicUtils.entityToDTO(
                            NormativaDTO.class, 
                            normativaExterna,
                            normativaCriteria.getIdioma());
                }
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return normativaDTO;
    }
    
    /**
     * Obtiene normativas.
     * @param normativaCriteria
     * @return List<NormativaDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<NormativaDTO> llistarNormatives(NormativaCriteria normativaCriteria) {
        List<NormativaDTO> normativaDTOList = new ArrayList<NormativaDTO>();
        List<CriteriaObject> criteris;
        Session session = null;
        
        boolean incluirExternas = (normativaCriteria.getIncluirExternas() == null)? false : normativaCriteria.getIncluirExternas();
        normativaCriteria.setIncluirExternas(null); // Para evitar que se parsee como los demas criterias

        try {
            criteris = BasicUtils.parseCriterias(NormativaCriteria.class, HQL_NORMATIVA_ALIAS, HQL_TRADUCCIONES_ALIAS, normativaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_NORMATIVA_LOCAL_CLASS, HQL_NORMATIVA_ALIAS));
            QueryBuilder qb = new QueryBuilder("n", entities, normativaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            session = getSession();
            Query query = qb.createQuery(session);
            List<NormativaLocal> normativasLocalesResult = (List<NormativaLocal>) query.list();
            
            List<NormativaExterna> normativasExternasResult = null;
            if (incluirExternas) {
                criteris = BasicUtils.parseCriterias(NormativaCriteria.class, HQL_NORMATIVA_ALIAS, HQL_TRADUCCIONES_ALIAS, normativaCriteria);
                entities = new ArrayList<FromClause>();
                entities.add(new FromClause(HQL_NORMATIVA_EXTERNA_CLASS, HQL_NORMATIVA_ALIAS));
                qb = new QueryBuilder(HQL_NORMATIVA_ALIAS, entities, normativaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
                qb.extendCriteriaObjects(criteris);
                query = qb.createQuery(session);
                normativasExternasResult = (List<NormativaExterna>) query.list();
            }
            NormativaDTO dto;
            for (NormativaLocal normativa : normativasLocalesResult) {
                dto = (NormativaDTO) BasicUtils.entityToDTO(NormativaDTO.class, normativa, normativaCriteria.getIdioma());
                dto.setLocal(true);
                normativaDTOList.add(dto);
            }
            if (incluirExternas) {
                for (NormativaExterna normativa : normativasExternasResult) {
                    dto = (NormativaDTO) BasicUtils.entityToDTO(NormativaDTO.class, normativa, normativaCriteria.getIdioma());
                    dto.setLocal(false);
                    normativaDTOList.add(dto);
                }
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return normativaDTOList;
    }

    /**
     * Obtiene un miembro de personal.
     * @param personalCriteria
     * @return PersonalDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public PersonalDTO obtenirPersonal(PersonalCriteria personalCriteria) {
        List<CriteriaObject> criteris;
        PersonalDTO personalDTO = null;
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(PersonalCriteria.class, HQL_PERSONAL_ALIAS, personalCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_PERSONAL_CLASS, HQL_PERSONAL_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_PERSONAL_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            Personal personal = (Personal) query.uniqueResult();
            if (personal != null) {
                personalDTO = (PersonalDTO) BasicUtils.entityToDTO(
                        PersonalDTO.class, 
                        personal,
                        personalCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return personalDTO;
    }

    /**
     * Obtiene lista de personal.
     * @param personalCriteria
     * @return List<PersonalDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<PersonalDTO> llistarPersonal(PersonalCriteria personalCriteria) {
        List<CriteriaObject> criteris;
        List<PersonalDTO> personalDTOList = new ArrayList<PersonalDTO>();
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(PersonalCriteria.class, HQL_PERSONAL_ALIAS, personalCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_PERSONAL_CLASS, HQL_PERSONAL_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_PERSONAL_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<Personal> personalResult = (List<Personal>) query.list();
            for (Personal personal: personalResult) {
                personalDTOList.add((PersonalDTO) BasicUtils.entityToDTO(
                        PersonalDTO.class, 
                        personal, 
                        personalCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return personalDTOList;
    }
    
    /**
     * Obtiene un usuario.
     * @param usuariCriteria
     * @return UsuariDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public UsuariDTO obtenirUsuari(UsuariCriteria usuariCriteria) {
        List<CriteriaObject> criteris;
        UsuariDTO usuariDTO = null;
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(UsuariCriteria.class, HQL_USUARI_ALIAS, usuariCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_USUARI_CLASS, HQL_USUARI_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_USUARI_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            Usuario usuari = (Usuario) query.uniqueResult();
            if (usuari != null) {
                usuariDTO = (UsuariDTO) BasicUtils.entityToDTO(
                        UsuariDTO.class, 
                        usuari,
                        usuariCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return usuariDTO;
    }
    
    /**
     * Obtiene usuarios.
     * @param usuariCriteria
     * @return List<UsuariDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<UsuariDTO> llistarUsuaris(UsuariCriteria usuariCriteria) {
        List<CriteriaObject> criteris;
        List<UsuariDTO> usuariDTOList = new ArrayList<UsuariDTO>();
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(UsuariCriteria.class, HQL_USUARI_ALIAS, usuariCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_USUARI_CLASS, HQL_USUARI_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_USUARI_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<Usuario> usuariResult = (List<Usuario>) query.list();
            for (Usuario usuari : usuariResult) {
                usuariDTOList.add((UsuariDTO) BasicUtils.entityToDTO(
                        UsuariDTO.class, 
                        usuari,
                        usuariCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return usuariDTOList;
    }
    
    /**
     * Obtiene una tasa.
     * @param taxaCriteria
     * @return TaxaDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public TaxaDTO obtenirTaxa(TaxaCriteria taxaCriteria) {
        TaxaDTO taxaDTO = null;
        List<CriteriaObject> criteris;
        Session session = null;

            try {            
                criteris = BasicUtils.parseCriterias(
                        TaxaCriteria.class,
                        HQL_TAXA_ALIAS,
                        HQL_TRADUCCIONES_ALIAS, taxaCriteria);

                List<FromClause> entities = new ArrayList<FromClause>();
                entities.add(new FromClause(HQL_TAXA_CLASS, HQL_TAXA_ALIAS));
                
                QueryBuilder qb = new QueryBuilder(
                        HQL_TAXA_ALIAS, 
                        entities, 
                        taxaCriteria.getIdioma(),
                        HQL_TRADUCCIONES_ALIAS);
                qb.extendCriteriaObjects(criteris);

                session = getSession();
                Query query = qb.createQuery(session);
                Taxa taxa = (Taxa) query.uniqueResult();
                if (taxa != null) {
                    taxaDTO = (TaxaDTO) BasicUtils.entityToDTO(TaxaDTO.class,  taxa, taxaCriteria.getIdioma());
                }
            } catch (HibernateException e) {
                log.error(e);
                throw new EJBException(e);
            } catch (CriteriaObjectParseException e) {
                log.error(e);
                throw new EJBException(e);
            } catch (QueryBuilderException e) {
                log.error(e);
                throw new EJBException(e);
            } finally {
                close(session);
            }
        
            return taxaDTO;
    }
    
    /**
     * Obtiene tasas.
     * @param taxaCriteria
     * @return List<TaxaDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<TaxaDTO> llistarTaxes(TaxaCriteria taxaCriteria) {
        List<TaxaDTO> taxesDTOList = new ArrayList<TaxaDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

            try {            
                criteris = BasicUtils.parseCriterias(
                        TaxaCriteria.class,
                        HQL_TAXA_ALIAS,
                        HQL_TRADUCCIONES_ALIAS, taxaCriteria);

                List<FromClause> entities = new ArrayList<FromClause>();
                entities.add(new FromClause(HQL_TAXA_CLASS, HQL_TAXA_ALIAS));
                
                QueryBuilder qb = new QueryBuilder(
                        HQL_TAXA_ALIAS, 
                        entities, 
                        taxaCriteria.getIdioma(),
                        HQL_TRADUCCIONES_ALIAS);
                qb.extendCriteriaObjects(criteris);

                sessio = getSession();
                Query query = qb.createQuery(sessio);
                List<Taxa> taxesResult = (List<Taxa>) query.list();
                for (Taxa taxa : taxesResult) {
                    taxesDTOList.add((TaxaDTO) BasicUtils.entityToDTO(TaxaDTO.class,  taxa, taxaCriteria.getIdioma()));
                }
            } catch (HibernateException e) {
                log.error(e);
                throw new EJBException(e);
            } catch (CriteriaObjectParseException e) {
                log.error(e);
                throw new EJBException(e);
            } catch (QueryBuilderException e) {
                log.error(e);
                throw new EJBException(e);
            } finally {
                close(sessio);
            }
        
            return taxesDTOList;
    }
    
    /**
     * Obtiene una agrupacion hecho vital.
     * @param afvCriteria
     * @return AgrupacioFetVitalDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public AgrupacioFetVitalDTO obtenirAgrupacioFetVital(AgrupacioFetVitalCriteria afvCriteria) {
        List<CriteriaObject> criteris = new ArrayList<CriteriaObject>();
        AgrupacioFetVitalDTO afvDTO = null;
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    AgrupacioFetVitalCriteria.class,
                    HQL_AGRUPACIO_FET_VITAL_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, afvCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_AGRUPACIO_FET_VITAL_CLASS, HQL_AGRUPACIO_FET_VITAL_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_AGRUPACIO_FET_VITAL_ALIAS, 
                    entities, 
                    afvCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            AgrupacionHechoVital afv = (AgrupacionHechoVital) query.uniqueResult();
            if (afv != null) {
                afvDTO = (AgrupacioFetVitalDTO) BasicUtils.entityToDTO(
                        AgrupacioFetVitalDTO.class, 
                        afv,
                        afvCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return afvDTO;
    }
    
    /**
     * Obtiene agrupaciones hecho vital.
     * @param afvCriteria
     * @return List<AgrupacioFetVitalDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<AgrupacioFetVitalDTO> llistarAgrupacionsFetsVitals(AgrupacioFetVitalCriteria afvCriteria) {
        List<CriteriaObject> criteris = new ArrayList<CriteriaObject>();
        List<AgrupacioFetVitalDTO> afvDTOList = new ArrayList<AgrupacioFetVitalDTO>();
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    AgrupacioFetVitalCriteria.class,
                    HQL_AGRUPACIO_FET_VITAL_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, afvCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_AGRUPACIO_FET_VITAL_CLASS, HQL_AGRUPACIO_FET_VITAL_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_AGRUPACIO_FET_VITAL_ALIAS, 
                    entities, 
                    afvCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<AgrupacionHechoVital> afvResult = (List<AgrupacionHechoVital>) query.list();
            for (AgrupacionHechoVital afv: afvResult) {
                afvDTOList.add((AgrupacioFetVitalDTO) BasicUtils.entityToDTO(
                        AgrupacioFetVitalDTO.class, 
                        afv,
                        afvCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return afvDTOList;
    }
    
    /**
     * Obtiene una agrupacion materia.
     * @param amCriteria
     * @return AgrupacioMateriaDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public AgrupacioMateriaDTO obtenirAgrupacioMateria(AgrupacioMateriaCriteria amCriteria) {
        List<CriteriaObject> criteris = new ArrayList<CriteriaObject>();
        AgrupacioMateriaDTO afvDTO = null;
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    AgrupacioMateriaCriteria.class,
                    HQL_AGRUPACIO_MATERIA_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, amCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_AGRUPACIO_MATERIA_CLASS, HQL_AGRUPACIO_MATERIA_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_AGRUPACIO_MATERIA_ALIAS, 
                    entities, 
                    amCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            AgrupacionMateria am = (AgrupacionMateria) query.uniqueResult();
            if (am != null) {
                afvDTO = (AgrupacioMateriaDTO) BasicUtils.entityToDTO(
                        AgrupacioMateriaDTO.class, 
                        am,
                        amCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return afvDTO;
    }
    
    /**
     * Obtiene agrupaciones materia.
     * @param amCriteria
     * @return List<AgrupacioMateriaDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<AgrupacioMateriaDTO> llistarAgrupacionsMateries(AgrupacioMateriaCriteria amCriteria) {
        List<CriteriaObject> criteris = new ArrayList<CriteriaObject>();
        List<AgrupacioMateriaDTO> amDTOList = new ArrayList<AgrupacioMateriaDTO>();
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    AgrupacioMateriaCriteria.class,
                    HQL_AGRUPACIO_MATERIA_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, amCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_AGRUPACIO_MATERIA_CLASS, HQL_AGRUPACIO_MATERIA_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_AGRUPACIO_MATERIA_ALIAS, 
                    entities, 
                    amCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<AgrupacionMateria> amResult = (List<AgrupacionMateria>) query.list();
            for (AgrupacionMateria am: amResult) {
                amDTOList.add((AgrupacioMateriaDTO) BasicUtils.entityToDTO(
                        AgrupacioMateriaDTO.class, 
                        am,
                        amCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return amDTOList;
    }
    
    /**
     * Obtiene un boletin.
     * @param butlletiCriteria
     * @return ButlletiDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ButlletiDTO obtenirButlleti(ButlletiCriteria butlletiCriteria) {
        List<CriteriaObject> criteris;
        ButlletiDTO butlletiDTO = null;
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(ButlletiCriteria.class, HQL_BUTLLETI_ALIAS, butlletiCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_BUTLLETI_CLASS, HQL_BUTLLETI_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_BUTLLETI_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            Boletin butlleti = (Boletin) query.uniqueResult();
            if (butlleti != null) {
                butlletiDTO = (ButlletiDTO) BasicUtils.entityToDTO(
                        ButlletiDTO.class, 
                        butlleti,
                        butlletiCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return butlletiDTO;
    }
    
    /**
     * Obtiene lista de boletines.
     * @param butlletiCriteria
     * @return List<ButlletiDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<ButlletiDTO> llistarButlletins(ButlletiCriteria butlletiCriteria) {
        List<CriteriaObject> criteris;
        List<ButlletiDTO> butlletiDTOList = new ArrayList<ButlletiDTO>();
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(ButlletiCriteria.class, HQL_BUTLLETI_ALIAS, butlletiCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_BUTLLETI_CLASS, HQL_BUTLLETI_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_BUTLLETI_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<Boletin> butlletiResult = (List<Boletin>) query.list();
            for (Boletin boletin: butlletiResult) {
                butlletiDTOList.add((ButlletiDTO) BasicUtils.entityToDTO(
                        ButlletiDTO.class, 
                        boletin,
                        butlletiCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return butlletiDTOList;
    }

    /**
     * Obtiene un documento.
     * @param documentCriteria
     * @return DocumentDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public DocumentDTO obtenirDocument(DocumentCriteria docCriteria) {
        List<CriteriaObject> criteris;
        DocumentDTO docDTO = null;
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    DocumentCriteria.class,
                    HQL_DOCUMENT_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, docCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_DOCUMENT_CLASS, HQL_DOCUMENT_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_DOCUMENT_ALIAS, 
                    entities, 
                    docCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            Documento doc = (Documento) query.uniqueResult();
            if (doc != null) {
                docDTO = (DocumentDTO) BasicUtils.entityToDTO(DocumentDTO.class, doc, docCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return docDTO;
    }
    
    /**
     * Obtiene lista de documentos.
     * @param documentCriteria
     * @return List<DocumentDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<DocumentDTO> llistarDocuments(DocumentCriteria docCriteria) {
        List<CriteriaObject> criteris;
        List<DocumentDTO> docDTOList = new ArrayList<DocumentDTO>();
        Session sessio = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    DocumentCriteria.class,
                    HQL_DOCUMENT_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, docCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_DOCUMENT_CLASS, HQL_DOCUMENT_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_DOCUMENT_ALIAS, 
                    entities, 
                    docCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            sessio = getSession();
            Query query = qb.createQuery(sessio);
            List<Documento> docResult = (List<Documento>) query.list();
            for (Documento doc: docResult) {
                docDTOList.add((DocumentDTO) BasicUtils.entityToDTO(DocumentDTO.class, doc, docCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(sessio);
        }

        return docDTOList;
    }

    /**
     * Obtiene un edificio.
     * @param edificiCriteria
     * @return EdificiDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public EdificiDTO obtenirEdifici(EdificiCriteria edificiCriteria) {
        List<CriteriaObject> criteris;
        EdificiDTO edificiDTO = null;
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    EdificiCriteria.class,
                    HQL_EDIFICI_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, edificiCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_EDIFICI_CLASS, HQL_EDIFICI_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_EDIFICI_ALIAS, 
                    entities, 
                    edificiCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            Edificio edifici = (Edificio) query.uniqueResult();
            if (edifici != null) {
                edificiDTO = (EdificiDTO) BasicUtils.entityToDTO(EdificiDTO.class, edifici, edificiCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return edificiDTO;
    }
    
    /**
     * Obtiene edificios.
     * @param edificiCriteria
     * @return List<EdificiDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<EdificiDTO> llistarEdificis(EdificiCriteria edificiCriteria) {
        List<CriteriaObject> criteris;
        List<EdificiDTO> edificisDTOList = new ArrayList<EdificiDTO>();
        Session sessio = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    EdificiCriteria.class,
                    HQL_EDIFICI_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, edificiCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_EDIFICI_CLASS, HQL_EDIFICI_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_EDIFICI_ALIAS, 
                    entities, 
                    edificiCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            sessio = getSession();
            Query query = qb.createQuery(sessio);
            List<Edificio> edificiResult = (List<Edificio>) query.list();
            for (Edificio ed: edificiResult) {
                edificisDTOList.add((EdificiDTO) BasicUtils.entityToDTO(
                        EdificiDTO.class, 
                        ed,
                        edificiCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(sessio);
        }

        return edificisDTOList;
    }
    
    /**
     * Obtiene un enlace.
     * @param enllacCriteria
     * @return EnllacDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public EnllacDTO obtenirEnllac(EnllacCriteria enllacCriteria) {
        List<CriteriaObject> criteris;
        EnllacDTO enllacDTO = null;
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    EnllacCriteria.class,
                    HQL_ENLLAC_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, enllacCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_ENLLAC_CLASS, HQL_ENLLAC_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_ENLLAC_ALIAS, 
                    entities, 
                    enllacCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            Enlace enllac = (Enlace) query.uniqueResult();
            if (enllac != null) {
                enllacDTO = (EnllacDTO) BasicUtils.entityToDTO(EnllacDTO.class, enllac, enllacCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return enllacDTO;
    }
    
    /**
     * Obtiene lista de enlaces.
     * @param enllacCriteria
     * @return List<EnllacDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<EnllacDTO> llistarEnllacos(EnllacCriteria enllacCriteria) {
        List<CriteriaObject> criteris;
        List<EnllacDTO> enllacDTOList = new ArrayList<EnllacDTO>();
        Session sessio = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    EnllacCriteria.class,
                    HQL_ENLLAC_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, enllacCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_ENLLAC_CLASS, HQL_ENLLAC_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_ENLLAC_ALIAS, 
                    entities, 
                    enllacCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            sessio = getSession();
            Query query = qb.createQuery(sessio);
            List<Enlace> enllacResult = (List<Enlace>) query.list();
            for (Enlace e: enllacResult) {
                enllacDTOList.add((EnllacDTO) BasicUtils.entityToDTO(EnllacDTO.class, e, enllacCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(sessio);
        }

        return enllacDTOList;
    }
    
    /**
     * Obtiene un espacio territorial.
     * @param etCriteria
     * @return EspaiTerritorialDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public EspaiTerritorialDTO obtenirEspaiTerritorial(EspaiTerritorialCriteria etCriteria) {
        List<CriteriaObject> criteris;
        EspaiTerritorialDTO etDTO = null;
        Session sessio = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    EspaiTerritorialCriteria.class,
                    HQL_ESPAI_TERRITORIAL_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, etCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_ESPAI_TERRITORIAL_CLASS, HQL_ESPAI_TERRITORIAL_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_ESPAI_TERRITORIAL_ALIAS, 
                    entities, 
                    etCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            sessio = getSession();
            Query query = qb.createQuery(sessio);
            EspacioTerritorial et = (EspacioTerritorial) query.uniqueResult();
            if (et != null) {
                etDTO = (EspaiTerritorialDTO) BasicUtils.entityToDTO(
                        EspaiTerritorialDTO.class, et, etCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(sessio);
        }

        return etDTO;
    }
    
    /**
     * Obtiene espacios territoriales.
     * @param etCriteria
     * @return List<EspaiTerritorialDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<EspaiTerritorialDTO> llistarEspaisTerritorials(EspaiTerritorialCriteria etCriteria) {
        List<CriteriaObject> criteris;
        List<EspaiTerritorialDTO> etDTOList = new ArrayList<EspaiTerritorialDTO>();
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    EspaiTerritorialCriteria.class,
                    HQL_ESPAI_TERRITORIAL_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, etCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_ESPAI_TERRITORIAL_CLASS, HQL_ESPAI_TERRITORIAL_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_ESPAI_TERRITORIAL_ALIAS, 
                    entities, 
                    etCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<EspacioTerritorial> etResult = (List<EspacioTerritorial>) query.list();
            for (EspacioTerritorial e: etResult) {
                etDTOList.add((EspaiTerritorialDTO) BasicUtils.entityToDTO(
                        EspaiTerritorialDTO.class, e, etCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return etDTOList;
    }
    
    /**
     * Obtiene una familia.
     * @param familiaCriteria
     * @return FamiliaDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public FamiliaDTO obtenirFamilia(FamiliaCriteria familiaCriteria) {
        List<CriteriaObject> criteris;
        FamiliaDTO familiaDTO = null;
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    FamiliaCriteria.class,
                    HQL_FAMILIA_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, familiaCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FAMILIA_CLASS, HQL_FAMILIA_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_FAMILIA_ALIAS, 
                    entities, 
                    familiaCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            Familia familia = (Familia) query.uniqueResult();
            if (familia != null) {
                familiaDTO = (FamiliaDTO) BasicUtils.entityToDTO(FamiliaDTO.class, familia, familiaCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return familiaDTO;
    }
    
    /**
     * Obtiene familias.
     * @param familiaCriteria
     * @return List<FamiliaDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<FamiliaDTO> llistarFamilies(FamiliaCriteria familiaCriteria) {
        List<CriteriaObject> criteris;
        List<FamiliaDTO> familiaDTOList = new ArrayList<FamiliaDTO>();
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    FamiliaCriteria.class,
                    HQL_FAMILIA_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, familiaCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FAMILIA_CLASS, HQL_FAMILIA_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_FAMILIA_ALIAS, 
                    entities, 
                    familiaCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<Familia> familiaResult = (List<Familia>) query.list();
            for (Familia f: familiaResult) {
                familiaDTOList.add((FamiliaDTO) BasicUtils.entityToDTO(FamiliaDTO.class, f, familiaCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return familiaDTOList;
    }
    
    /**
     * Obtiene publico objetivo.
     * @param publicObjectiuCriteria
     * @return PublicObjectiuDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public PublicObjectiuDTO obtenirPublicObjectiu(PublicObjectiuCriteria publicObjectiuCriteria) {
        List<CriteriaObject> criteris;
        PublicObjectiuDTO publicObjectiuDTO = null;
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    PublicObjectiuCriteria.class,
                    HQL_PUBLIC_OBJECTIU_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, publicObjectiuCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_PUBLIC_OBJECTIU_CLASS, HQL_PUBLIC_OBJECTIU_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_PUBLIC_OBJECTIU_ALIAS, 
                    entities, 
                    publicObjectiuCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            PublicoObjetivo publicoObjetivo = (PublicoObjetivo) query.uniqueResult();
            if (publicoObjetivo != null) {
                publicObjectiuDTO = (PublicObjectiuDTO) BasicUtils.entityToDTO(PublicObjectiuDTO.class, publicoObjetivo, publicObjectiuCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return publicObjectiuDTO;
    }

    /**
     * Obtiene publicos objetivo.
     * @param publicObjectiuCriteria
     * @return List<PublicObjectiuDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<PublicObjectiuDTO> llistarPublicsObjectius(PublicObjectiuCriteria poCriteria) {
        List<CriteriaObject> criteris;
        List<PublicObjectiuDTO> poDTOList = new ArrayList<PublicObjectiuDTO>();
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    PublicObjectiuCriteria.class,
                    HQL_PUBLIC_OBJECTIU_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, poCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_PUBLIC_OBJECTIU_CLASS, HQL_PUBLIC_OBJECTIU_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_PUBLIC_OBJECTIU_ALIAS, 
                    entities, 
                    poCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<PublicoObjetivo> poResult = (List<PublicoObjetivo>) query.list();
            for (PublicoObjetivo po: poResult) {
                poDTOList.add((PublicObjectiuDTO) BasicUtils.entityToDTO(
                        PublicObjectiuDTO.class, po, poCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return poDTOList;
    }
    
    /**
     * Obtiene ficha UA.
     * @param fuaCriteria
     * @return FitxaUADTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public FitxaUADTO obtenirFitxaUA(FitxaUACriteria fuaCriteria) {
        List<CriteriaObject> criteris;
        FitxaUADTO fuaDTO = null;
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(FitxaUACriteria.class, HQL_FITXA_UA_ALIAS, fuaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FITXA_UA_CLASS, HQL_FITXA_UA_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_FITXA_UA_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            FichaUA fua = (FichaUA) query.uniqueResult();
            if (fua != null) {
                fuaDTO = (FitxaUADTO) BasicUtils.entityToDTO(FitxaUADTO.class, fua, fuaCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return fuaDTO;
    }
    
    /**
     * Obtiene lista de fichas UA.
     * @param fuaCriteria
     * @return List<FitxaUADTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<FitxaUADTO> llistarFitxesUA(FitxaUACriteria fuaCriteria) {
        List<CriteriaObject> criteris;
        List<FitxaUADTO> fuaDTOList = new ArrayList<FitxaUADTO>();
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(FitxaUACriteria.class, HQL_FITXA_UA_ALIAS, fuaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FITXA_UA_CLASS, HQL_FITXA_UA_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_FITXA_UA_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<FichaUA> fuaResult = (List<FichaUA>) query.list();
            for (FichaUA fua: fuaResult) {
                fuaDTOList.add((FitxaUADTO) BasicUtils.entityToDTO(FitxaUADTO.class, fua, fuaCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return fuaDTOList;
    }
    
    /**
     * Obtiene un formulario.
     * @param formCriteria
     * @return formulariDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public FormulariDTO obtenirFormulari(FormulariCriteria formCriteria) {
        List<CriteriaObject> criteris;
        FormulariDTO formDTO = null;
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(FormulariCriteria.class, HQL_FORMULARI_ALIAS, formCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FORMULARI_CLASS, HQL_FORMULARI_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_FORMULARI_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            Formulario form = (Formulario) query.uniqueResult();
            if (form != null) {
                formDTO = (FormulariDTO) BasicUtils.entityToDTO(FormulariDTO.class, form, formCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return formDTO;
    }
    
    /**
     * Obtiene formularios.
     * @param formCriteria
     * @return List<formulariDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<FormulariDTO> llistarFormularis(FormulariCriteria formCriteria) {
        List<CriteriaObject> criteris;
        List<FormulariDTO> formDTOList = new ArrayList<FormulariDTO>();
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(FormulariCriteria.class, HQL_FORMULARI_ALIAS, formCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FORMULARI_CLASS, HQL_FORMULARI_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_FORMULARI_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<Formulario> formResult = (List<Formulario>) query.list();
            for (Formulario form: formResult) {
                formDTOList.add((FormulariDTO) BasicUtils.entityToDTO(FormulariDTO.class, form, formCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return formDTOList;
    }
    
    /**
     * Obtiene un icono familia.
     * @param ifCriteria
     * @return IconaFamiliaDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public IconaFamiliaDTO obtenirIconaFamilia(IconaFamiliaCriteria ifCriteria) {
        List<CriteriaObject> criteris;
        IconaFamiliaDTO ifDTO = null;
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(IconaFamiliaCriteria.class, HQL_ICONA_FAMILIA_ALIAS, ifCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_ICONA_FAMILIA_CLASS, HQL_ICONA_FAMILIA_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_ICONA_FAMILIA_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            IconoFamilia icoFam = (IconoFamilia) query.uniqueResult();
            if (icoFam != null) {
                ifDTO = (IconaFamiliaDTO) BasicUtils.entityToDTO(IconaFamiliaDTO.class, icoFam, ifCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return ifDTO;
    }

    /**
     * Obtiene lista de iconos familia.
     * @param ifCriteria
     * @return List<IconaFamiliaDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<IconaFamiliaDTO> llistarIconesFamilies(IconaFamiliaCriteria ifCriteria) {
        List<CriteriaObject> criteris;
        List<IconaFamiliaDTO> ifDTOList = new ArrayList<IconaFamiliaDTO>();
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(IconaFamiliaCriteria.class, HQL_ICONA_FAMILIA_ALIAS, ifCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_ICONA_FAMILIA_CLASS, HQL_ICONA_FAMILIA_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_ICONA_FAMILIA_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<IconoFamilia> ifResult = (List<IconoFamilia>) query.list();
            for (IconoFamilia iFam: ifResult) {
                ifDTOList.add((IconaFamiliaDTO) BasicUtils.entityToDTO(IconaFamiliaDTO.class, iFam, ifCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return ifDTOList;
    }
    
    /**
     * Obtiene un icono materia.
     * @param imCriteria
     * @return IconaMateriaDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public IconaMateriaDTO obtenirIconaMateria(IconaMateriaCriteria imCriteria) {
        List<CriteriaObject> criteris;
        IconaMateriaDTO imDTO = null;
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(IconaMateriaCriteria.class, HQL_ICONA_MATERIA_ALIAS, imCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_ICONA_MATERIA_CLASS, HQL_ICONA_MATERIA_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_ICONA_MATERIA_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            IconoMateria icoMat = (IconoMateria) query.uniqueResult();
            if (icoMat != null) {
                imDTO = (IconaMateriaDTO) BasicUtils.entityToDTO(IconaMateriaDTO.class, icoMat, imCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return imDTO;
    }

    /**
     * Obtiene iconos materia.
     * @param imCriteria
     * @return List<IconaMateriaDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<IconaMateriaDTO> llistarIconesMateries(IconaMateriaCriteria imCriteria) {
        List<CriteriaObject> criteris;
        List<IconaMateriaDTO> imDTOList = new ArrayList<IconaMateriaDTO>();
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(IconaMateriaCriteria.class, HQL_ICONA_MATERIA_ALIAS, imCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_ICONA_MATERIA_CLASS, HQL_ICONA_MATERIA_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_ICONA_MATERIA_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<IconoMateria> imResult = (List<IconoMateria>) query.list();
            for (IconoMateria iMat: imResult) {
                imDTOList.add((IconaMateriaDTO) BasicUtils.entityToDTO(IconaMateriaDTO.class, iMat, imCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return imDTOList;
    }
    
    /**
     * Obtiene una materia agrupacion.
     * @param maCriteria
     * @return MateriaAgrupacioDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public MateriaAgrupacioDTO obtenirMateriaAgrupacio(MateriaAgrupacioCriteria maCriteria) {
        List<CriteriaObject> criteris;
        MateriaAgrupacioDTO imDTO = null;
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(MateriaAgrupacioCriteria.class, HQL_MATERIA_AGRUPACIO_ALIAS, maCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_MATERIA_AGRUPACIO_CLASS, HQL_MATERIA_AGRUPACIO_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_MATERIA_AGRUPACIO_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            MateriaAgrupacionM ma = (MateriaAgrupacionM) query.uniqueResult();
            if (ma != null) {
                imDTO = (MateriaAgrupacioDTO) BasicUtils.entityToDTO(MateriaAgrupacioDTO.class, ma, maCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return imDTO;
    }
    
    /**
     * Obtiene lista de materias agrupacion.
     * @param maCriteria
     * @return List<MateriaAgrupacioDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<MateriaAgrupacioDTO> llistarMateriesAgrupacions(MateriaAgrupacioCriteria maCriteria) {
        List<CriteriaObject> criteris;
        List<MateriaAgrupacioDTO> imDTOList = new ArrayList<MateriaAgrupacioDTO>();
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(MateriaAgrupacioCriteria.class, HQL_MATERIA_AGRUPACIO_ALIAS, maCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_MATERIA_AGRUPACIO_CLASS, HQL_MATERIA_AGRUPACIO_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_MATERIA_AGRUPACIO_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<MateriaAgrupacionM> maResult = (List<MateriaAgrupacionM>) query.list();
            for (MateriaAgrupacionM ma: maResult) {
                imDTOList.add((MateriaAgrupacioDTO) BasicUtils.entityToDTO(MateriaAgrupacioDTO.class, ma, maCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return imDTOList;
    }
    
    /**
     * Obtiene un perfil.
     * @param perfilCriteria
     * @return PerfilDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public PerfilDTO obtenirPerfil(PerfilCriteria perfilCriteria) {
        List<CriteriaObject> criteris;
        PerfilDTO perfilDTO = null;
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    PerfilCriteria.class,
                    HQL_PERFIL_ALIAS,
                    HQL_TRADUCCIONES_ALIAS,
                    perfilCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_PERFIL_CLASS,HQL_PERFIL_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_PERFIL_ALIAS, 
                    entities, 
                    perfilCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            PerfilCiudadano perfil = (PerfilCiudadano) query.uniqueResult();
            if (perfil != null) {
                perfilDTO = (PerfilDTO) BasicUtils.entityToDTO(PerfilDTO.class, perfil, perfilCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return perfilDTO;
    }
    
    /**
     * Obtiene perfiles.
     * @param perfilCriteria
     * @return List<PerfilDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<PerfilDTO> llistarPerfils(PerfilCriteria perfilCriteria) {
        List<CriteriaObject> criteris;
        List<PerfilDTO> perfilDTOList = new ArrayList<PerfilDTO>();
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    PerfilCriteria.class,
                    HQL_PERFIL_ALIAS,
                    HQL_TRADUCCIONES_ALIAS,
                    perfilCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_PERFIL_CLASS,HQL_PERFIL_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_PERFIL_ALIAS, 
                    entities, 
                    perfilCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<PerfilCiudadano> perfilResult = (List<PerfilCiudadano>) query.list();
            for (PerfilCiudadano p: perfilResult) {
                perfilDTOList.add((PerfilDTO) BasicUtils.entityToDTO(PerfilDTO.class, p, perfilCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return perfilDTOList;
    }

    /**
     * Obtiene una seccion.
     * @param seccioCriteria
     * @return SeccioDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public SeccioDTO obtenirSeccio(SeccioCriteria seccioCriteria) {
        List<CriteriaObject> criteris;
        SeccioDTO seccioDTO = null;
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    SeccioCriteria.class,
                    HQL_SECCIO_ALIAS,
                    HQL_TRADUCCIONES_ALIAS,
                    seccioCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_SECCIO_CLASS, HQL_SECCIO_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_SECCIO_ALIAS, 
                    entities, 
                    seccioCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            Seccion seccio = (Seccion) query.uniqueResult();
            if (seccio != null) {
                seccioDTO = (SeccioDTO) BasicUtils.entityToDTO(SeccioDTO.class, seccio, seccioCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return seccioDTO;
    }
    
    /**
     * Obtiene secciones.
     * @param seccioCriteria
     * @return List<SeccioDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<SeccioDTO> llistarSeccions(SeccioCriteria seccioCriteria) {
        List<CriteriaObject> criteris;
        List<SeccioDTO> seccioDTOList = new ArrayList<SeccioDTO>();
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    SeccioCriteria.class,
                    HQL_SECCIO_ALIAS,
                    HQL_TRADUCCIONES_ALIAS,
                    seccioCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_SECCIO_CLASS, HQL_SECCIO_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_SECCIO_ALIAS, 
                    entities, 
                    seccioCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<Seccion> seccioResult = (List<Seccion>) query.list();
            for (Seccion s: seccioResult) {
                seccioDTOList.add((SeccioDTO) BasicUtils.entityToDTO(SeccioDTO.class, s, seccioCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return seccioDTOList;
    }
    
    /**
     * Obtiene una unidad materia.
     * @param umCriteria
     * @return UnitatMateriaDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public UnitatMateriaDTO obtenirUnitatMateria(UnitatMateriaCriteria umCriteria) {
        List<CriteriaObject> criteris;
        UnitatMateriaDTO umDTO = null;
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    UnitatMateriaCriteria.class,
                    HQL_UNITAT_MATERIA_ALIAS,
                    HQL_TRADUCCIONES_ALIAS,
                    umCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_UNITAT_MATERIA_CLASS, HQL_UNITAT_MATERIA_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_UNITAT_MATERIA_ALIAS, 
                    entities, 
                    umCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            UnidadMateria um = (UnidadMateria) query.uniqueResult();
            if (um != null) {
                umDTO = (UnitatMateriaDTO) BasicUtils.entityToDTO(UnitatMateriaDTO.class, um, umCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return umDTO;
    }
    
    /**
     * Obtiene unidades materia.
     * @param umCriteria
     * @return List<UnitatMateriaDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<UnitatMateriaDTO> llistarUnitatsMateries(UnitatMateriaCriteria umCriteria) {
        List<CriteriaObject> criteris;
        List<UnitatMateriaDTO> umDTOList = new ArrayList<UnitatMateriaDTO>();
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    UnitatMateriaCriteria.class,
                    HQL_UNITAT_MATERIA_ALIAS,
                    HQL_TRADUCCIONES_ALIAS,
                    umCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_UNITAT_MATERIA_CLASS, HQL_UNITAT_MATERIA_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_UNITAT_MATERIA_ALIAS, 
                    entities, 
                    umCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<UnidadMateria> umResult = (List<UnidadMateria>) query.list();
            for (UnidadMateria um: umResult) {
                umDTOList.add((UnitatMateriaDTO) BasicUtils.entityToDTO(UnitatMateriaDTO.class, um, umCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return umDTOList;
    }

    /**
     * Obtiene un tipo.
     * @param tipusCriteria
     * @return TipusDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public TipusDTO obtenirTipus(TipusCriteria tipusCriteria) {
        List<CriteriaObject> criteris;
        TipusDTO tipusDTO = null;
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    TipusCriteria.class,
                    HQL_TIPUS_ALIAS,
                    HQL_TRADUCCIONES_ALIAS,
                    tipusCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_TIPUS_CLASS, HQL_TIPUS_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_TIPUS_ALIAS, 
                    entities, 
                    tipusCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            Tipo tipus = (Tipo) query.uniqueResult();
            if (tipus != null) {
                tipusDTO = (TipusDTO) BasicUtils.entityToDTO(TipusDTO.class, tipus, tipusCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return tipusDTO;
    }

    /**
     * Obtiene tipos.
     * @param tipusCriteria
     * @return List<TipusDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<TipusDTO> llistarTipus(TipusCriteria tipusCriteria) {
        List<CriteriaObject> criteris;
        List<TipusDTO> tipusDTOList = new ArrayList<TipusDTO>();
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    TipusCriteria.class,
                    HQL_TIPUS_ALIAS,
                    HQL_TRADUCCIONES_ALIAS,
                    tipusCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_TIPUS_CLASS, HQL_TIPUS_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_TIPUS_ALIAS, 
                    entities, 
                    tipusCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<Tipo> tipusResult = (List<Tipo>) query.list();
            for (Tipo t: tipusResult) {
                tipusDTOList.add((TipusDTO) BasicUtils.entityToDTO(TipusDTO.class, t, tipusCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return tipusDTOList;
    }

    /**
     * Obtiene un tipo afectacion.
     * @param tipusAfectacioCriteria
     * @return TipusAfectacioDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public TipusAfectacioDTO obtenirTipusAfectacio(TipusAfectacioCriteria tipusAfectacioCriteria) {
        List<CriteriaObject> criteris;
        TipusAfectacioDTO tipusAfectacioDTO = null;
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    TipusAfectacioCriteria.class,
                    HQL_TIPUS_AFECTACIO_ALIAS,
                    HQL_TRADUCCIONES_ALIAS,
                    tipusAfectacioCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_TIPUS_AFECTACIO_CLASS, HQL_TIPUS_AFECTACIO_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_TIPUS_AFECTACIO_ALIAS, 
                    entities, 
                    tipusAfectacioCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            TipoAfectacion tipusAfectacio = (TipoAfectacion) query.uniqueResult();
            if (tipusAfectacio != null) {
                tipusAfectacioDTO = (TipusAfectacioDTO) BasicUtils.entityToDTO(TipusAfectacioDTO.class, tipusAfectacio, tipusAfectacioCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return tipusAfectacioDTO;
    }

    /**
     * Obtiene tipos afectacion.
     * @param tipusAfectacioCriteria
     * @return List<TipusAfectacioDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<TipusAfectacioDTO> llistarTipusAfectacio(TipusAfectacioCriteria tipusAfectacioCriteria) {
        List<CriteriaObject> criteris;
        List<TipusAfectacioDTO> tipusAfectacioDTOList = new ArrayList<TipusAfectacioDTO>();
        Session session = null;

        try {
            criteris = BasicUtils.parseCriterias(
                    TipusAfectacioCriteria.class,
                    HQL_TIPUS_AFECTACIO_ALIAS,
                    HQL_TRADUCCIONES_ALIAS,
                    tipusAfectacioCriteria);

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_TIPUS_AFECTACIO_CLASS, HQL_TIPUS_AFECTACIO_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_TIPUS_AFECTACIO_ALIAS, 
                    entities, 
                    tipusAfectacioCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<TipoAfectacion> tipoAfectacionResult = (List<TipoAfectacion>) query.list();
            for (TipoAfectacion t: tipoAfectacionResult) {
                tipusAfectacioDTOList.add((TipusAfectacioDTO) BasicUtils.entityToDTO(TipusAfectacioDTO.class, t, tipusAfectacioCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            throw new EJBException(e);
        } catch (QueryBuilderException e) {
            log.error(e);
            throw new EJBException(e);
        } finally {
            close(session);
        }

        return tipusAfectacioDTOList;
    }
    
    /**
     * Obtiene lista de tipos de iniciación.
     * @param iniciacioCriteria
     * @return List<IniciacioDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<IniciacioDTO> llistarTipusIniciacions(IniciacioCriteria iniciacioCriteria) {
    	
        List<CriteriaObject> criteris;
        List<IniciacioDTO> iniciacioDTOList = new ArrayList<IniciacioDTO>();
        Session session = null;

        try {
        	
            criteris = BasicUtils.parseCriterias(
	        		IniciacioCriteria.class, 
	        		HQL_INICIACIO_ALIAS, 
	        		HQL_TRADUCCIONES_ALIAS, 
	        		iniciacioCriteria
            );
            
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_INICIACIO_CLASS, HQL_INICIACIO_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
            		HQL_INICIACIO_ALIAS, 
                    entities, 
                    iniciacioCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS
            );
            
            
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<Iniciacion> iniciacioResult = (List<Iniciacion>)query.list();
            
            for (Iniciacion iniciacion: iniciacioResult) {
                iniciacioDTOList.add((IniciacioDTO)BasicUtils.entityToDTO(
                		IniciacioDTO.class, 
                		iniciacion,
                        iniciacioCriteria.getIdioma()));
            }
            
        } catch (HibernateException e) {
        	
            log.error(e);
            throw new EJBException(e);
            
        } catch (CriteriaObjectParseException e) {
        	
            log.error(e);
            throw new EJBException(e);
            
        } catch (QueryBuilderException e) {
        	
            log.error(e);
            throw new EJBException(e);
            
        } finally {
        	
            close(session);
            
        }

        return iniciacioDTOList;
        
    }
    
    /**
     * Obtiene un tipo de iniciación.
     * @param iniciacioCriteria
     * @return IniciacioDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public IniciacioDTO obtenirTipusIniciacio(IniciacioCriteria iniciacioCriteria) {
    	
        List<CriteriaObject> criteris;
        IniciacioDTO iniciacioDTO = null;
        Session session = null;

        try {
        	
            criteris = BasicUtils.parseCriterias(
	        		IniciacioCriteria.class, 
	        		HQL_INICIACIO_ALIAS, 
	        		HQL_TRADUCCIONES_ALIAS, 
	        		iniciacioCriteria
            );
            
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_INICIACIO_CLASS, HQL_INICIACIO_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
            		HQL_INICIACIO_ALIAS, 
                    entities, 
                    iniciacioCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS
            );
            
            
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            Iniciacion iniciacion = (Iniciacion)query.uniqueResult();
            
            if (iniciacion != null) {
            	
				iniciacioDTO = (IniciacioDTO)BasicUtils.entityToDTO(
						IniciacioDTO.class, 
						iniciacion,
						iniciacioCriteria.getIdioma()
				);
				
            }
            
        } catch (HibernateException e) {
        	
            log.error(e);
            throw new EJBException(e);
            
        } catch (CriteriaObjectParseException e) {
        	
            log.error(e);
            throw new EJBException(e);
            
        } catch (QueryBuilderException e) {
        	
            log.error(e);
            throw new EJBException(e);
            
        } finally {
        	
            close(session);
            
        }

        return iniciacioDTO;
        
    }
    
}
