/**
 * RolsacQueryServiceEJBRemote.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost.sacws_api.services.RolsacWS;

public interface RolsacQueryServiceEJBRemote extends java.rmi.Remote {
    public es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO[] llistarUnitatsAdministratives(es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO obtenirEspaiTerritorial(es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.procediment.ProcedimentDTO obtenirProcediment(es.caib.rolsac.api.v2.procediment.ProcedimentCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.procediment.ProcedimentDTO[] llistarProcediments(es.caib.rolsac.api.v2.procediment.ProcedimentCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.materia.MateriaDTO obtenirMateria(es.caib.rolsac.api.v2.materia.MateriaCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.materia.MateriaDTO[] llistarMateries(es.caib.rolsac.api.v2.materia.MateriaCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.tramit.TramitDTO obtenirTramit(es.caib.rolsac.api.v2.tramit.TramitCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.tramit.TramitDTO[] llistarTramit(es.caib.rolsac.api.v2.tramit.TramitCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO obtenirUnitatAdministrativa(es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.fetVital.FetVitalDTO obtenirFetVital(es.caib.rolsac.api.v2.fetVital.FetVitalCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.fetVital.FetVitalDTO[] llistarFetsVitals(es.caib.rolsac.api.v2.fetVital.FetVitalCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.fitxa.FitxaDTO obtenirFitxa(es.caib.rolsac.api.v2.fitxa.FitxaCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.fitxa.FitxaDTO[] llistarFitxes(es.caib.rolsac.api.v2.fitxa.FitxaCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO obtenirDocumentTramit(es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO[] llistarDocumentTramit(es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.normativa.NormativaDTO obtenirNormativa(es.caib.rolsac.api.v2.normativa.NormativaCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.normativa.NormativaDTO[] llistarNormatives(es.caib.rolsac.api.v2.normativa.NormativaCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.personal.PersonalDTO obtenirPersonal(es.caib.rolsac.api.v2.personal.PersonalCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.personal.PersonalDTO[] llistarPersonal(es.caib.rolsac.api.v2.personal.PersonalCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.usuari.UsuariDTO obtenirUsuari(es.caib.rolsac.api.v2.usuari.UsuariCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.usuari.UsuariDTO[] llistarUsuaris(es.caib.rolsac.api.v2.usuari.UsuariCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.taxa.TaxaDTO obtenirTaxa(es.caib.rolsac.api.v2.taxa.TaxaCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.taxa.TaxaDTO[] llistarTaxes(es.caib.rolsac.api.v2.taxa.TaxaCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO obtenirAgrupacioFetVital(es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO[] llistarAgrupacionsFetsVitals(es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO obtenirAgrupacioMateria(es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO[] llistarAgrupacionsMateries(es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.butlleti.ButlletiDTO obtenirButlleti(es.caib.rolsac.api.v2.butlleti.ButlletiCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.butlleti.ButlletiDTO[] llistarButlletins(es.caib.rolsac.api.v2.butlleti.ButlletiCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.document.DocumentDTO obtenirDocument(es.caib.rolsac.api.v2.document.DocumentCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.document.DocumentDTO[] llistarDocuments(es.caib.rolsac.api.v2.document.DocumentCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.edifici.EdificiDTO obtenirEdifici(es.caib.rolsac.api.v2.edifici.EdificiCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.edifici.EdificiDTO[] llistarEdificis(es.caib.rolsac.api.v2.edifici.EdificiCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.enllac.EnllacDTO obtenirEnllac(es.caib.rolsac.api.v2.enllac.EnllacCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.enllac.EnllacDTO[] llistarEnllacos(es.caib.rolsac.api.v2.enllac.EnllacCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO[] llistarEspaisTerritorials(es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.familia.FamiliaDTO obtenirFamilia(es.caib.rolsac.api.v2.familia.FamiliaCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.familia.FamiliaDTO[] llistarFamilies(es.caib.rolsac.api.v2.familia.FamiliaCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO obtenirPublicObjectiu(es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO[] llistarPublicsObjectius(es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.fitxaUA.FitxaUADTO obtenirFitxaUA(es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.fitxaUA.FitxaUADTO[] llistarFitxesUA(es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.formulari.FormulariDTO obtenirFormulari(es.caib.rolsac.api.v2.formulari.FormulariCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.formulari.FormulariDTO[] llistarFormularis(es.caib.rolsac.api.v2.formulari.FormulariCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO obtenirIconaFamilia(es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO[] llistarIconesFamilies(es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO obtenirIconaMateria(es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO[] llistarIconesMateries(es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioDTO obtenirMateriaAgrupacio(es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioDTO[] llistarMateriesAgrupacions(es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.perfil.PerfilDTO obtenirPerfil(es.caib.rolsac.api.v2.perfil.PerfilCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.perfil.PerfilDTO[] llistarPerfils(es.caib.rolsac.api.v2.perfil.PerfilCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.seccio.SeccioDTO obtenirSeccio(es.caib.rolsac.api.v2.seccio.SeccioCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.seccio.SeccioDTO[] llistarSeccions(es.caib.rolsac.api.v2.seccio.SeccioCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaDTO obtenirUnitatMateria(es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaDTO[] llistarUnitatsMateries(es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.tipus.TipusDTO obtenirTipus(es.caib.rolsac.api.v2.tipus.TipusCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.tipus.TipusDTO[] llistarTipus(es.caib.rolsac.api.v2.tipus.TipusCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioDTO obtenirTipusAfectacio(es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioCriteria in0) throws java.rmi.RemoteException;
    public es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioDTO[] llistarTipusAfectacio(es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioCriteria in0) throws java.rmi.RemoteException;
}
