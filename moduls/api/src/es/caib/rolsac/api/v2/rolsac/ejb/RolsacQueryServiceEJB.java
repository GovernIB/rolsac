package es.caib.rolsac.api.v2.rolsac.ejb;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.AgrupacionHechoVital;
import org.ibit.rol.sac.model.AgrupacionMateria;
import org.ibit.rol.sac.model.Boletin;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.Enlace;
import org.ibit.rol.sac.model.EspacioTerritorial;
import org.ibit.rol.sac.model.Familia;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.Formulario;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.IconoFamilia;
import org.ibit.rol.sac.model.IconoMateria;
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
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;
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
import es.caib.rolsac.api.v2.query.HibernateUtils;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.taxa.TaxaCriteria;
import es.caib.rolsac.api.v2.taxa.TaxaDTO;
import es.caib.rolsac.api.v2.tipus.TipusCriteria;
import es.caib.rolsac.api.v2.tipus.TipusDTO;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaCriteria;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaDTO;
import es.caib.rolsac.api.v2.usuari.UsuariCriteria;
import es.caib.rolsac.api.v2.usuari.UsuariDTO;

@SuppressWarnings("deprecation")
public class RolsacQueryServiceEJB {

    private static Log log = LogFactory.getLog(RolsacQueryServiceEJB.class);

    private static final String HQL_TRADUCCIONES_ALIAS = "trad";
    private static final String HQL_PROCEDIMIENTO_CLASS = "ProcedimientoLocal";
    private static final String HQL_PROCEDIMIENTO_ALIAS = "p";
    private static final String HQL_MATERIA_CLASS = "Materia";
    private static final String HQL_MATERIA_ALIAS = "m";
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
    private static final String HQL_UNITAT_MATERIA_CLASS = "UnidadMateria";
    private static final String HQL_UNITAT_MATERIA_ALIAS = "um";

    public ProcedimentDTO obtenirProcediment(ProcedimentCriteria procedimentCriteria) {
        List<CriteriaObject> criteris = new ArrayList<CriteriaObject>();
        ProcedimentDTO procedimentDTO = null;
        Session sessio = null;

        try {
            ProcedimentUtils.parseActiu(criteris, procedimentCriteria, HQL_PROCEDIMIENTO_ALIAS);
            criteris.addAll(BasicUtils.parseCriterias(
                    ProcedimentCriteria.class,
                    HQL_PROCEDIMIENTO_ALIAS,
                    HQL_TRADUCCIONES_ALIAS, procedimentCriteria));

            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_PROCEDIMIENTO_CLASS,HQL_PROCEDIMIENTO_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
                    HQL_PROCEDIMIENTO_ALIAS, 
                    entities, 
                    procedimentCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            ProcedimientoLocal procedimentLocal = (ProcedimientoLocal) query.uniqueResult();
            sessio.close();

            if (procedimentLocal != null) {
                procedimentDTO = (ProcedimentDTO) BasicUtils.entityToDTO(
                        ProcedimentDTO.class, 
                        procedimentLocal,
                        procedimentCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return procedimentDTO;
    }

    public List<ProcedimentDTO> llistarProcediments(ProcedimentCriteria procedimentCriteria) {
        List<ProcedimentDTO> procedimentDTOList = new ArrayList<ProcedimentDTO>();
        List<CriteriaObject> criteris = new ArrayList<CriteriaObject>();
        Session sessio = null;

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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);

            @SuppressWarnings("unchecked")
            List<ProcedimientoLocal> procedimentsResult = (List<ProcedimientoLocal>) query.list();
            sessio.close();

            for (ProcedimientoLocal procediment : procedimentsResult) {
                procedimentDTOList.add((ProcedimentDTO) BasicUtils.entityToDTO(
                        ProcedimentDTO.class, 
                        procediment, 
                        procedimentCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return procedimentDTOList;
    }

    public MateriaDTO obtenirMateria(MateriaCriteria materiaCriteria) {
        List<CriteriaObject> criteris;
        MateriaDTO materiaDTO = null;
        Session sessio = null;

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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            Materia materia = (Materia) query.uniqueResult();
            sessio.close();

            if (materia != null) {
                materiaDTO = (MateriaDTO) BasicUtils.entityToDTO(MateriaDTO.class, materia, materiaCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return materiaDTO;
    }
    
    public List<MateriaDTO> llistarMateries(MateriaCriteria materiaCriteria) {
        List<CriteriaObject> criteris;
        List<MateriaDTO> materiaDTOList = new ArrayList<MateriaDTO>();
        Session sessio = null;

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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<Materia> materiaResult = (List<Materia>) query.list();
            sessio.close();

            for (Materia materia: materiaResult) {
                materiaDTOList.add((MateriaDTO) BasicUtils.entityToDTO(
                        MateriaDTO.class, 
                        materia, 
                        materiaCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }
        return materiaDTOList;
    }
    
    public TramitDTO obtenirTramit(TramitCriteria tramitCriteria) {
        List<CriteriaObject> criteris;
        TramitDTO tramitDTO = null;
        Session sessio = null;

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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            Tramite tramit = (Tramite) query.uniqueResult();
            sessio.close();

            if (tramit != null) {
                tramitDTO = (TramitDTO) BasicUtils.entityToDTO(
                        TramitDTO.class, 
                        tramit,
                        tramitCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return tramitDTO;
    }
    
    public List<TramitDTO> llistarTramit(TramitCriteria tramitCriteria) {
        List<TramitDTO> tramitDTOList = new ArrayList<TramitDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<Tramite> tramitsResult = (List<Tramite>) query.list();
            sessio.close();

            for (Tramite tramit: tramitsResult) {
                tramitDTOList.add((TramitDTO) BasicUtils.entityToDTO(
                        TramitDTO.class, 
                        tramit, 
                        tramitCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return tramitDTOList;
    }
    
    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(UnitatAdministrativaCriteria uaCriteria) {
        List<CriteriaObject> criteris;
        UnitatAdministrativaDTO uaDTO = null;
        Session sessio = null;

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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            UnidadAdministrativa ua = (UnidadAdministrativa) query.uniqueResult();
            sessio.close();

            if (ua != null) {
                uaDTO = (UnitatAdministrativaDTO) BasicUtils.entityToDTO(
                        UnitatAdministrativaDTO.class, 
                        ua,
                        uaCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return uaDTO;
    }

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(UnitatAdministrativaCriteria uaCriteria) {
        List<UnitatAdministrativaDTO> uaDTOList = new ArrayList<UnitatAdministrativaDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<UnidadAdministrativa> uaResult = (List<UnidadAdministrativa>) query.list();
            sessio.close();

            for (UnidadAdministrativa ua: uaResult) {
                uaDTOList.add((UnitatAdministrativaDTO) BasicUtils.entityToDTO(
                        UnitatAdministrativaDTO.class, 
                        ua, 
                        uaCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return uaDTOList;
    }    
    
    public FetVitalDTO obtenirFetVital(FetVitalCriteria fetVitalCriteria) {
        List<CriteriaObject> criteris;
        FetVitalDTO fetVitalDTO = null;
        Session sessio = null;

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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            HechoVital fetVital = (HechoVital) query.uniqueResult();
            sessio.close();

            if (fetVital != null) {
                fetVitalDTO = (FetVitalDTO) BasicUtils.entityToDTO(
                        FetVitalDTO.class, 
                        fetVital,
                        fetVitalCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return fetVitalDTO;
    }
    
    public List<FetVitalDTO> llistarFetsVitals(FetVitalCriteria fetVitalCriteria) {
        List<CriteriaObject> criteris;
        List<FetVitalDTO> fetVitalDTOList = new ArrayList<FetVitalDTO>();
        Session sessio = null;

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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<HechoVital> fvResult = (List<HechoVital>) query.list();
            sessio.close();

            for (HechoVital fv: fvResult) {
                fetVitalDTOList.add((FetVitalDTO) BasicUtils.entityToDTO(
                        FetVitalDTO.class, fv, fetVitalCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return fetVitalDTOList;
    }

    public FitxaDTO obtenirFitxa(FitxaCriteria fitxaCriteria) {
        List<CriteriaObject> criteris;
        FitxaDTO fitxaDTO = null;
        Session sessio = null;

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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            Ficha fitxa = (Ficha) query.uniqueResult();
            sessio.close();

            if (fitxa != null) {
                fitxaDTO = (FitxaDTO) BasicUtils.entityToDTO(FitxaDTO.class, fitxa, fitxaCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return fitxaDTO;
    }
    
    public List<FitxaDTO> llistarFitxes(FitxaCriteria fitxaCriteria) {
        List<FitxaDTO> fitxaDTOList = new ArrayList<FitxaDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<Ficha> fitxaResult = (List<Ficha>) query.list();
            sessio.close();

            for (Ficha ficha: fitxaResult) {
                fitxaDTOList.add((FitxaDTO) BasicUtils.entityToDTO(
                        FitxaDTO.class, 
                        ficha, 
                        fitxaCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return fitxaDTOList;
    }
    
    public DocumentTramitDTO obtenirDocumentTramit(DocumentTramitCriteria documentTramitCriteria) {        
        DocumentTramitDTO docTramitDTO = null;
        List<CriteriaObject> criteris;
        Session sessio = null;

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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            DocumentTramit docTramit = (DocumentTramit) query.uniqueResult();
            sessio.close();

            if (docTramit != null) {
                docTramitDTO = (DocumentTramitDTO) BasicUtils.entityToDTO(
                        DocumentTramitDTO.class,
                        docTramit, 
                        documentTramitCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }
    
        return docTramitDTO;
    }
    
    public List<DocumentTramitDTO> llistarDocumentTramit(DocumentTramitCriteria documentTramitCriteria) {        
        List<DocumentTramitDTO> documentsTramitDTOList = new ArrayList<DocumentTramitDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<DocumentTramit> documentTramitsResult = (List<DocumentTramit>) query.list();
            sessio.close();

            for (DocumentTramit documentTramit : documentTramitsResult) {
                documentsTramitDTOList.add((DocumentTramitDTO) BasicUtils.entityToDTO(
                        DocumentTramitDTO.class,
                        documentTramit, 
                        documentTramitCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }
    
        return documentsTramitDTOList;
    }
    
    public NormativaDTO obtenirNormativa(NormativaCriteria normativaCriteria) {
        NormativaDTO normativaDTO = null;
        List<CriteriaObject> criteris;
        Session sessio = null;
        
        boolean incluirExternas = (normativaCriteria.getIncluirExternas() == null)? false : normativaCriteria.getIncluirExternas();
        normativaCriteria.setIncluirExternas(null); // Para evitar que se parsee como los demas criterias

        try {
            criteris = BasicUtils.parseCriterias(NormativaCriteria.class, HQL_NORMATIVA_ALIAS, HQL_TRADUCCIONES_ALIAS, normativaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_NORMATIVA_LOCAL_CLASS, HQL_NORMATIVA_ALIAS));
            QueryBuilder qb = new QueryBuilder("n", entities, normativaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
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
                query = qb.createQuery(sessio);
                NormativaExterna normativaExterna = (NormativaExterna) query.uniqueResult();
                
                if (normativaExterna != null) {
                    normativaDTO = (NormativaDTO) BasicUtils.entityToDTO(
                            NormativaDTO.class, 
                            normativaExterna,
                            normativaCriteria.getIdioma());
                }
            }
            
            sessio.close();
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace(); // TODO: delete me.
                }
            }
        }

        return normativaDTO;
    }
    
    @SuppressWarnings("unchecked")
    public List<NormativaDTO> llistarNormatives(NormativaCriteria normativaCriteria) {
        List<NormativaDTO> normativaDTOList = new ArrayList<NormativaDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;
        
        boolean incluirExternas = (normativaCriteria.getIncluirExternas() == null)? false : normativaCriteria.getIncluirExternas();
        normativaCriteria.setIncluirExternas(null); // Para evitar que se parsee como los demas criterias

        try {
            criteris = BasicUtils.parseCriterias(NormativaCriteria.class, HQL_NORMATIVA_ALIAS, HQL_TRADUCCIONES_ALIAS, normativaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_NORMATIVA_LOCAL_CLASS, HQL_NORMATIVA_ALIAS));
            QueryBuilder qb = new QueryBuilder("n", entities, normativaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            List<NormativaLocal> normativasLocalesResult = (List<NormativaLocal>) query.list();
            
            List<NormativaExterna> normativasExternasResult = null;
            if (incluirExternas) {
                criteris = BasicUtils.parseCriterias(NormativaCriteria.class, HQL_NORMATIVA_ALIAS, HQL_TRADUCCIONES_ALIAS, normativaCriteria);
                entities = new ArrayList<FromClause>();
                entities.add(new FromClause(HQL_NORMATIVA_EXTERNA_CLASS, HQL_NORMATIVA_ALIAS));
                qb = new QueryBuilder(HQL_NORMATIVA_ALIAS, entities, normativaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
                qb.extendCriteriaObjects(criteris);
                query = qb.createQuery(sessio);
                normativasExternasResult = (List<NormativaExterna>) query.list();
            }

            sessio.close();

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
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace(); // TODO: delete me.
                }
            }
        }

        return normativaDTOList;
    }

    public PersonalDTO obtenirPersonal(PersonalCriteria personalCriteria) {
        List<CriteriaObject> criteris;
        PersonalDTO personalDTO = null;
        Session sessio = null;

        try {
            criteris = BasicUtils.parseCriterias(PersonalCriteria.class, HQL_PERSONAL_ALIAS, personalCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_PERSONAL_CLASS, HQL_PERSONAL_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_PERSONAL_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            Personal personal = (Personal) query.uniqueResult();
            sessio.close();

            if (personal != null) {
                personalDTO = (PersonalDTO) BasicUtils.entityToDTO(
                        PersonalDTO.class, 
                        personal,
                        personalCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return personalDTO;
    }

    public List<PersonalDTO> llistarPersonal(PersonalCriteria personalCriteria) {
        List<CriteriaObject> criteris;
        List<PersonalDTO> personalDTOList = new ArrayList<PersonalDTO>();
        Session sessio = null;

        try {
            criteris = BasicUtils.parseCriterias(PersonalCriteria.class, HQL_PERSONAL_ALIAS, personalCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_PERSONAL_CLASS, HQL_PERSONAL_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_PERSONAL_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<Personal> personalResult = (List<Personal>) query.list();
            sessio.close();

            for (Personal personal: personalResult) {
                personalDTOList.add((PersonalDTO) BasicUtils.entityToDTO(
                        PersonalDTO.class, 
                        personal, 
                        personalCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return personalDTOList;
    }
    
    public UsuariDTO obtenirUsuari(UsuariCriteria usuariCriteria) {
        List<CriteriaObject> criteris;
        UsuariDTO usuariDTO = null;
        Session sessio = null;

        try {
            criteris = BasicUtils.parseCriterias(UsuariCriteria.class, HQL_USUARI_ALIAS, usuariCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_USUARI_CLASS, HQL_USUARI_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_USUARI_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            Usuario usuari = (Usuario) query.uniqueResult();
            sessio.close();

            if (usuari != null) {
                usuariDTO = (UsuariDTO) BasicUtils.entityToDTO(
                        UsuariDTO.class, 
                        usuari,
                        usuariCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return usuariDTO;
    }
    
    public List<UsuariDTO> llistarUsuaris(UsuariCriteria usuariCriteria) {
        List<CriteriaObject> criteris;
        List<UsuariDTO> usuariDTOList = new ArrayList<UsuariDTO>();
        Session sessio = null;

        try {
            criteris = BasicUtils.parseCriterias(UsuariCriteria.class, HQL_USUARI_ALIAS, usuariCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_USUARI_CLASS, HQL_USUARI_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_USUARI_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<Usuario> usuariResult = (List<Usuario>) query.list();
            sessio.close();

            for (Usuario usuari : usuariResult) {
                usuariDTOList.add((UsuariDTO) BasicUtils.entityToDTO(
                        UsuariDTO.class, 
                        usuari,
                        usuariCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return usuariDTOList;
    }
    
    public TaxaDTO obtenirTaxa(TaxaCriteria taxaCriteria) {
        TaxaDTO taxeaDTO = null;
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

                sessio = HibernateUtils.getSessionFactory().openSession();
                Query query = qb.createQuery(sessio);
                Taxa taxa = (Taxa) query.uniqueResult();
                sessio.close();

                if (taxa != null) {
                    taxeaDTO = (TaxaDTO) BasicUtils.entityToDTO(TaxaDTO.class,  taxa, taxaCriteria.getIdioma());
                }
            } catch (HibernateException e) {
                e.printStackTrace();
            } catch (CriteriaObjectParseException e) {
                e.printStackTrace();
            } catch (QueryBuilderException e) {
                e.printStackTrace();
            } finally {
                if (sessio != null && sessio.isOpen()) {
                    try {
                        sessio.close();
                    } catch (HibernateException e) {
                        e.printStackTrace();
                    }
                }
            }
        
            return taxeaDTO;
    }
    
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

                sessio = HibernateUtils.getSessionFactory().openSession();
                Query query = qb.createQuery(sessio);
                @SuppressWarnings("unchecked")
                List<Taxa> taxesResult = (List<Taxa>) query.list();
                sessio.close();

                for (Taxa taxa : taxesResult) {
                    taxesDTOList.add((TaxaDTO) BasicUtils.entityToDTO(TaxaDTO.class,  taxa, taxaCriteria.getIdioma()));
                }
            } catch (HibernateException e) {
                e.printStackTrace();
            } catch (CriteriaObjectParseException e) {
                e.printStackTrace();
            } catch (QueryBuilderException e) {
                e.printStackTrace();
            } finally {
                if (sessio != null && sessio.isOpen()) {
                    try {
                        sessio.close();
                    } catch (HibernateException e) {
                        e.printStackTrace();
                    }
                }
            }
        
            return taxesDTOList;
    }
    
    public AgrupacioFetVitalDTO obtenirAgrupacioFetVital(AgrupacioFetVitalCriteria afvCriteria) {
        List<CriteriaObject> criteris = new ArrayList<CriteriaObject>();
        AgrupacioFetVitalDTO afvDTO = null;
        Session sessio = null;

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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            AgrupacionHechoVital afv = (AgrupacionHechoVital) query.uniqueResult();
            sessio.close();

            if (afv != null) {
                afvDTO = (AgrupacioFetVitalDTO) BasicUtils.entityToDTO(
                        AgrupacioFetVitalDTO.class, 
                        afv,
                        afvCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return afvDTO;
    }
    
    public List<AgrupacioFetVitalDTO> llistarAgrupacionsFetsVitals(AgrupacioFetVitalCriteria afvCriteria) {
        List<CriteriaObject> criteris = new ArrayList<CriteriaObject>();
        List<AgrupacioFetVitalDTO> afvDTOList = new ArrayList<AgrupacioFetVitalDTO>();
        Session sessio = null;

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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<AgrupacionHechoVital> afvResult = (List<AgrupacionHechoVital>) query.list();
            sessio.close();
            
            for (AgrupacionHechoVital afv: afvResult) {
                afvDTOList.add((AgrupacioFetVitalDTO) BasicUtils.entityToDTO(
                        AgrupacioFetVitalDTO.class, 
                        afv,
                        afvCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return afvDTOList;
    }
    
    public AgrupacioMateriaDTO obtenirAgrupacioMateria(AgrupacioMateriaCriteria amCriteria) {
        List<CriteriaObject> criteris = new ArrayList<CriteriaObject>();
        AgrupacioMateriaDTO afvDTO = null;
        Session sessio = null;

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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            AgrupacionMateria am = (AgrupacionMateria) query.uniqueResult();
            sessio.close();

            if (am != null) {
                afvDTO = (AgrupacioMateriaDTO) BasicUtils.entityToDTO(
                        AgrupacioMateriaDTO.class, 
                        am,
                        amCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return afvDTO;
    }
    
    public List<AgrupacioMateriaDTO> llistarAgrupacionsMateries(AgrupacioMateriaCriteria amCriteria) {
        List<CriteriaObject> criteris = new ArrayList<CriteriaObject>();
        List<AgrupacioMateriaDTO> amDTOList = new ArrayList<AgrupacioMateriaDTO>();
        Session sessio = null;

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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<AgrupacionMateria> amResult = (List<AgrupacionMateria>) query.list();
            sessio.close();

            for (AgrupacionMateria am: amResult) {
                amDTOList.add((AgrupacioMateriaDTO) BasicUtils.entityToDTO(
                        AgrupacioMateriaDTO.class, 
                        am,
                        amCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return amDTOList;
    }
    
    public ButlletiDTO obtenirButlleti(ButlletiCriteria butlletiCriteria) {
        List<CriteriaObject> criteris;
        ButlletiDTO butlletiDTO = null;
        Session sessio = null;

        try {
            criteris = BasicUtils.parseCriterias(ButlletiCriteria.class, HQL_BUTLLETI_ALIAS, butlletiCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_BUTLLETI_CLASS, HQL_BUTLLETI_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_BUTLLETI_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            Boletin butlleti = (Boletin) query.uniqueResult();
            sessio.close();

            if (butlleti != null) {
                butlletiDTO = (ButlletiDTO) BasicUtils.entityToDTO(
                        ButlletiDTO.class, 
                        butlleti,
                        butlletiCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return butlletiDTO;
    }
    
    public List<ButlletiDTO> llistarButlletins(ButlletiCriteria butlletiCriteria) {
        List<CriteriaObject> criteris;
        List<ButlletiDTO> butlletiDTOList = new ArrayList<ButlletiDTO>();
        Session sessio = null;

        try {
            criteris = BasicUtils.parseCriterias(ButlletiCriteria.class, HQL_BUTLLETI_ALIAS, butlletiCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_BUTLLETI_CLASS, HQL_BUTLLETI_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_BUTLLETI_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<Boletin> butlletiResult = (List<Boletin>) query.list();
            sessio.close();

            for (Boletin boletin: butlletiResult) {
                butlletiDTOList.add((ButlletiDTO) BasicUtils.entityToDTO(
                        ButlletiDTO.class, 
                        boletin,
                        butlletiCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return butlletiDTOList;
    }

    public DocumentDTO obtenirDocument(DocumentCriteria docCriteria) {
        List<CriteriaObject> criteris;
        DocumentDTO docDTO = null;
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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            Documento doc = (Documento) query.uniqueResult();
            sessio.close();

            if (doc != null) {
                docDTO = (DocumentDTO) BasicUtils.entityToDTO(DocumentDTO.class, doc, docCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return docDTO;
    }
    
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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<Documento> docResult = (List<Documento>) query.list();
            sessio.close();

            for (Documento doc: docResult) {
                docDTOList.add((DocumentDTO) BasicUtils.entityToDTO(DocumentDTO.class, doc, docCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return docDTOList;
    }

    public EdificiDTO obtenirEdifici(EdificiCriteria edificiCriteria) {
        List<CriteriaObject> criteris;
        EdificiDTO edificiDTO = null;
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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            Edificio edifici = (Edificio) query.uniqueResult();
            sessio.close();
            
            if (edifici != null) {
                edificiDTO = (EdificiDTO) BasicUtils.entityToDTO(EdificiDTO.class, edifici, edificiCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return edificiDTO;
    }
    
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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<Edificio> edificiResult = (List<Edificio>) query.list();
            sessio.close();
            
            for (Edificio ed: edificiResult) {
                edificisDTOList.add((EdificiDTO) BasicUtils.entityToDTO(
                        EdificiDTO.class, 
                        ed,
                        edificiCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return edificisDTOList;
    }
    
    public EnllacDTO obtenirEnllac(EnllacCriteria enllacCriteria) {
        List<CriteriaObject> criteris;
        EnllacDTO enllacDTO = null;
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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            Enlace enllac = (Enlace) query.uniqueResult();
            sessio.close();
            
            if (enllac != null) {
                enllacDTO = (EnllacDTO) BasicUtils.entityToDTO(EnllacDTO.class, enllac, enllacCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return enllacDTO;
    }
    
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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<Enlace> enllacResult = (List<Enlace>) query.list();
            sessio.close();
            
            for (Enlace e: enllacResult) {
                enllacDTOList.add((EnllacDTO) BasicUtils.entityToDTO(EnllacDTO.class, e, enllacCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return enllacDTOList;
    }
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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            EspacioTerritorial et = (EspacioTerritorial) query.uniqueResult();
            sessio.close();
            
            if (et != null) {
                etDTO = (EspaiTerritorialDTO) BasicUtils.entityToDTO(
                        EspaiTerritorialDTO.class, et, etCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return etDTO;
    }
    
    public List<EspaiTerritorialDTO> llistarEspaisTerritorials(EspaiTerritorialCriteria etCriteria) {
        List<CriteriaObject> criteris;
        List<EspaiTerritorialDTO> etDTOList = new ArrayList<EspaiTerritorialDTO>();
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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<EspacioTerritorial> etResult = (List<EspacioTerritorial>) query.list();
            sessio.close();
            
            for (EspacioTerritorial e: etResult) {
                etDTOList.add((EspaiTerritorialDTO) BasicUtils.entityToDTO(
                        EspaiTerritorialDTO.class, e, etCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return etDTOList;
    }
    
    public FamiliaDTO obtenirFamilia(FamiliaCriteria familiaCriteria) {
        List<CriteriaObject> criteris;
        FamiliaDTO familiaDTO = null;
        Session sessio = null;

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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            Familia familia = (Familia) query.uniqueResult();
            sessio.close();
            
            if (familia != null) {
                familiaDTO = (FamiliaDTO) BasicUtils.entityToDTO(FamiliaDTO.class, familia, familiaCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return familiaDTO;
    }
    
    public List<FamiliaDTO> llistarFamilies(FamiliaCriteria familiaCriteria) {
        List<CriteriaObject> criteris;
        List<FamiliaDTO> familiaDTOList = new ArrayList<FamiliaDTO>();
        Session sessio = null;

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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<Familia> familiaResult = (List<Familia>) query.list();
            sessio.close();
            
            for (Familia f: familiaResult) {
                familiaDTOList.add((FamiliaDTO) BasicUtils.entityToDTO(FamiliaDTO.class, f, familiaCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return familiaDTOList;
    }
    
    public PublicObjectiuDTO obtenirPublicObjectiu(PublicObjectiuCriteria publicObjectiuCriteria) {
        List<CriteriaObject> criteris;
        PublicObjectiuDTO publicObjectiuDTO = null;
        Session sessio = null;

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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            PublicoObjetivo publicoObjetivo = (PublicoObjetivo) query.uniqueResult();
            sessio.close();
            
            if (publicoObjetivo != null) {
                publicObjectiuDTO = (PublicObjectiuDTO) BasicUtils.entityToDTO(PublicObjectiuDTO.class, publicoObjetivo, publicObjectiuCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return publicObjectiuDTO;
    }

    public List<PublicObjectiuDTO> llistarPublicsObjectius(PublicObjectiuCriteria poCriteria) {
        List<CriteriaObject> criteris;
        List<PublicObjectiuDTO> poDTOList = new ArrayList<PublicObjectiuDTO>();
        Session sessio = null;

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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<PublicoObjetivo> poResult = (List<PublicoObjetivo>) query.list();
            sessio.close();
            
            for (PublicoObjetivo po: poResult) {
                poDTOList.add((PublicObjectiuDTO) BasicUtils.entityToDTO(
                        PublicObjectiuDTO.class, po, poCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return poDTOList;
    }
    
    public FitxaUADTO obtenirFitxaUA(FitxaUACriteria fuaCriteria) {
        List<CriteriaObject> criteris;
        FitxaUADTO fuaDTO = null;
        Session sessio = null;

        try {
            criteris = BasicUtils.parseCriterias(FitxaUACriteria.class, HQL_FITXA_UA_ALIAS, fuaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FITXA_UA_CLASS, HQL_FITXA_UA_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_FITXA_UA_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            FichaUA fua = (FichaUA) query.uniqueResult();
            sessio.close();

            if (fua != null) {
                fuaDTO = (FitxaUADTO) BasicUtils.entityToDTO(FitxaUADTO.class, fua, fuaCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return fuaDTO;
    }
    
    public List<FitxaUADTO> llistarFitxesUA(FitxaUACriteria fuaCriteria) {
        List<CriteriaObject> criteris;
        List<FitxaUADTO> fuaDTOList = new ArrayList<FitxaUADTO>();
        Session sessio = null;

        try {
            criteris = BasicUtils.parseCriterias(FitxaUACriteria.class, HQL_FITXA_UA_ALIAS, fuaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FITXA_UA_CLASS, HQL_FITXA_UA_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_FITXA_UA_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<FichaUA> fuaResult = (List<FichaUA>) query.list();
            sessio.close();

            for (FichaUA fua: fuaResult) {
                fuaDTOList.add((FitxaUADTO) BasicUtils.entityToDTO(FitxaUADTO.class, fua, fuaCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return fuaDTOList;
    }
    
    public FormulariDTO obtenirFormulari(FormulariCriteria formCriteria) {
        List<CriteriaObject> criteris;
        FormulariDTO formDTO = null;
        Session sessio = null;

        try {
            criteris = BasicUtils.parseCriterias(FormulariCriteria.class, HQL_FORMULARI_ALIAS, formCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FORMULARI_CLASS, HQL_FORMULARI_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_FORMULARI_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            Formulario form = (Formulario) query.uniqueResult();
            sessio.close();

            if (form != null) {
                formDTO = (FormulariDTO) BasicUtils.entityToDTO(FormulariDTO.class, form, formCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return formDTO;
    }
    
    public List<FormulariDTO> llistarFormularis(FormulariCriteria formCriteria) {
        List<CriteriaObject> criteris;
        List<FormulariDTO> formDTOList = new ArrayList<FormulariDTO>();
        Session sessio = null;

        try {
            criteris = BasicUtils.parseCriterias(FormulariCriteria.class, HQL_FORMULARI_ALIAS, formCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FORMULARI_CLASS, HQL_FORMULARI_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_FORMULARI_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<Formulario> formResult = (List<Formulario>) query.list();
            sessio.close();

            for (Formulario form: formResult) {
                formDTOList.add((FormulariDTO) BasicUtils.entityToDTO(FormulariDTO.class, form, formCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return formDTOList;
    }
    
    public IconaFamiliaDTO obtenirIconaFamilia(IconaFamiliaCriteria ifCriteria) {
        List<CriteriaObject> criteris;
        IconaFamiliaDTO ifDTO = null;
        Session sessio = null;

        try {
            criteris = BasicUtils.parseCriterias(IconaFamiliaCriteria.class, HQL_ICONA_FAMILIA_ALIAS, ifCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_ICONA_FAMILIA_CLASS, HQL_ICONA_FAMILIA_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_ICONA_FAMILIA_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            IconoFamilia icoFam = (IconoFamilia) query.uniqueResult();
            sessio.close();

            if (icoFam != null) {
                ifDTO = (IconaFamiliaDTO) BasicUtils.entityToDTO(IconaFamiliaDTO.class, icoFam, ifCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return ifDTO;
    }

    public List<IconaFamiliaDTO> llistarIconesFamilies(IconaFamiliaCriteria ifCriteria) {
        List<CriteriaObject> criteris;
        List<IconaFamiliaDTO> ifDTOList = new ArrayList<IconaFamiliaDTO>();
        Session sessio = null;

        try {
            criteris = BasicUtils.parseCriterias(IconaFamiliaCriteria.class, HQL_ICONA_FAMILIA_ALIAS, ifCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_ICONA_FAMILIA_CLASS, HQL_ICONA_FAMILIA_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_ICONA_FAMILIA_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<IconoFamilia> ifResult = (List<IconoFamilia>) query.list();
            sessio.close();

            for (IconoFamilia iFam: ifResult) {
                ifDTOList.add((IconaFamiliaDTO) BasicUtils.entityToDTO(IconaFamiliaDTO.class, iFam, ifCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return ifDTOList;
    }
    
    public IconaMateriaDTO obtenirIconaMateria(IconaMateriaCriteria imCriteria) {
        List<CriteriaObject> criteris;
        IconaMateriaDTO imDTO = null;
        Session sessio = null;

        try {
            criteris = BasicUtils.parseCriterias(IconaMateriaCriteria.class, HQL_ICONA_MATERIA_ALIAS, imCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_ICONA_MATERIA_CLASS, HQL_ICONA_MATERIA_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_ICONA_MATERIA_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            IconoMateria icoMat = (IconoMateria) query.uniqueResult();
            sessio.close();

            if (icoMat != null) {
                imDTO = (IconaMateriaDTO) BasicUtils.entityToDTO(IconaMateriaDTO.class, icoMat, imCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return imDTO;
    }

    public List<IconaMateriaDTO> llistarIconesMateries(IconaMateriaCriteria imCriteria) {
        List<CriteriaObject> criteris;
        List<IconaMateriaDTO> imDTOList = new ArrayList<IconaMateriaDTO>();
        Session sessio = null;

        try {
            criteris = BasicUtils.parseCriterias(IconaMateriaCriteria.class, HQL_ICONA_MATERIA_ALIAS, imCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_ICONA_MATERIA_CLASS, HQL_ICONA_MATERIA_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_ICONA_MATERIA_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<IconoMateria> imResult = (List<IconoMateria>) query.list();
            sessio.close();

            for (IconoMateria iMat: imResult) {
                imDTOList.add((IconaMateriaDTO) BasicUtils.entityToDTO(IconaMateriaDTO.class, iMat, imCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return imDTOList;
    }
    
    public MateriaAgrupacioDTO obtenirMateriaAgrupacio(MateriaAgrupacioCriteria maCriteria) {
        List<CriteriaObject> criteris;
        MateriaAgrupacioDTO imDTO = null;
        Session sessio = null;

        try {
            criteris = BasicUtils.parseCriterias(MateriaAgrupacioCriteria.class, HQL_MATERIA_AGRUPACIO_ALIAS, maCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_MATERIA_AGRUPACIO_CLASS, HQL_MATERIA_AGRUPACIO_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_MATERIA_AGRUPACIO_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            MateriaAgrupacionM ma = (MateriaAgrupacionM) query.uniqueResult();
            sessio.close();

            if (ma != null) {
                imDTO = (MateriaAgrupacioDTO) BasicUtils.entityToDTO(MateriaAgrupacioDTO.class, ma, maCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return imDTO;
    }
    
    public List<MateriaAgrupacioDTO> llistarMateriesAgrupacions(MateriaAgrupacioCriteria maCriteria) {
        List<CriteriaObject> criteris;
        List<MateriaAgrupacioDTO> imDTOList = new ArrayList<MateriaAgrupacioDTO>();
        Session sessio = null;

        try {
            criteris = BasicUtils.parseCriterias(MateriaAgrupacioCriteria.class, HQL_MATERIA_AGRUPACIO_ALIAS, maCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_MATERIA_AGRUPACIO_CLASS, HQL_MATERIA_AGRUPACIO_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_MATERIA_AGRUPACIO_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<MateriaAgrupacionM> maResult = (List<MateriaAgrupacionM>) query.list();
            sessio.close();

            for (MateriaAgrupacionM ma: maResult) {
                imDTOList.add((MateriaAgrupacioDTO) BasicUtils.entityToDTO(MateriaAgrupacioDTO.class, ma, maCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return imDTOList;
    }
    
    public PerfilDTO obtenirPerfil(PerfilCriteria perfilCriteria) {
        List<CriteriaObject> criteris;
        PerfilDTO perfilDTO = null;
        Session sessio = null;

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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            PerfilCiudadano perfil = (PerfilCiudadano) query.uniqueResult();
            sessio.close();

            if (perfil != null) {
                perfilDTO = (PerfilDTO) BasicUtils.entityToDTO(PerfilDTO.class, perfil, perfilCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return perfilDTO;
    }
    
    public List<PerfilDTO> llistarPerfils(PerfilCriteria perfilCriteria) {
        List<CriteriaObject> criteris;
        List<PerfilDTO> perfilDTOList = new ArrayList<PerfilDTO>();
        Session sessio = null;

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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<PerfilCiudadano> perfilResult = (List<PerfilCiudadano>) query.list();
            sessio.close();

            for (PerfilCiudadano p: perfilResult) {
                perfilDTOList.add((PerfilDTO) BasicUtils.entityToDTO(PerfilDTO.class, p, perfilCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return perfilDTOList;
    }

    public SeccioDTO obtenirSeccio(SeccioCriteria seccioCriteria) {
        List<CriteriaObject> criteris;
        SeccioDTO seccioDTO = null;
        Session sessio = null;

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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            Seccion seccio = (Seccion) query.uniqueResult();
            sessio.close();

            if (seccio != null) {
                seccioDTO = (SeccioDTO) BasicUtils.entityToDTO(SeccioDTO.class, seccio, seccioCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return seccioDTO;
    }
    
    public List<SeccioDTO> llistarSeccions(SeccioCriteria seccioCriteria) {
        List<CriteriaObject> criteris;
        List<SeccioDTO> seccioDTOList = new ArrayList<SeccioDTO>();
        Session sessio = null;

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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<Seccion> seccioResult = (List<Seccion>) query.list();
            sessio.close();

            for (Seccion s: seccioResult) {
                seccioDTOList.add((SeccioDTO) BasicUtils.entityToDTO(SeccioDTO.class, s, seccioCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return seccioDTOList;
    }
    
    public UnitatMateriaDTO obtenirUnitatMateria(UnitatMateriaCriteria umCriteria) {
        List<CriteriaObject> criteris;
        UnitatMateriaDTO umDTO = null;
        Session sessio = null;

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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            UnidadMateria um = (UnidadMateria) query.uniqueResult();
            sessio.close();

            if (um != null) {
                umDTO = (UnitatMateriaDTO) BasicUtils.entityToDTO(UnitatMateriaDTO.class, um, umCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return umDTO;
    }
    
    public List<UnitatMateriaDTO> llistarUnitatsMateries(UnitatMateriaCriteria umCriteria) {
        List<CriteriaObject> criteris;
        List<UnitatMateriaDTO> umDTOList = new ArrayList<UnitatMateriaDTO>();
        Session sessio = null;

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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<UnidadMateria> umResult = (List<UnidadMateria>) query.list();
            sessio.close();

            for (UnidadMateria um: umResult) {
                umDTOList.add((UnitatMateriaDTO) BasicUtils.entityToDTO(UnitatMateriaDTO.class, um, umCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return umDTOList;
    }

    public TipusDTO obtenirTipus(TipusCriteria tipusCriteria) {
        List<CriteriaObject> criteris;
        TipusDTO tipusDTO = null;
        Session sessio = null;

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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            Tipo tipus = (Tipo) query.uniqueResult();
            sessio.close();

            if (tipus != null) {
                tipusDTO = (TipusDTO) BasicUtils.entityToDTO(TipusDTO.class, tipus, tipusCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return tipusDTO;
    }

    public List<TipusDTO> llistarTipus(TipusCriteria tipusCriteria) {
        List<CriteriaObject> criteris;
        List<TipusDTO> tipusDTOList = new ArrayList<TipusDTO>();
        Session sessio = null;

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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<Tipo> tipusResult = (List<Tipo>) query.list();
            sessio.close();

            for (Tipo t: tipusResult) {
                tipusDTOList.add((TipusDTO) BasicUtils.entityToDTO(TipusDTO.class, t, tipusCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return tipusDTOList;
    }
    
}
