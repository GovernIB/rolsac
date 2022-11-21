package org.ibit.rol.sac.persistence.util;


import java.util.*;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class RemotoUtils {

     protected static final Log log = LogFactory.getLog(RemotoUtils.class);  
     
    /**
     * Recojo, si existe, la {@link FichaUA} relacionada con una {@link UnidadAdministrativa} y una 
     * {@link Ficha}.Si no existe devuelvo null.
     * 
     * @param session
     * @param idUnidad
     * @param idFicha
     * @return {@link FichaUA}
     * @throws HibernateException
     */
	public static FichaUA recogerFichaUA(final Session session, final Long idUnidad, final Long idFicha, final Long idSeccion) throws HibernateException{
		final Query query = session.createQuery("select fichaua From FichaUA as fichaua where fichaua.seccion.id=:idSeccion and fichaua.ficha.id=:idFicha and fichaua.unidadAdministrativa.id=:idUnidad");
		query.setLong("idSeccion", idSeccion);
		query.setLong("idFicha", idFicha);
		query.setLong("idUnidad", idUnidad);
		//@SuppressWarnings("unchecked")
		List<FichaUA> lista = query.list();
		if(lista==null || lista.isEmpty()) {
			return null;
		}
		return (FichaUA)query.list().iterator().next();
    }
    
    /**
     * Recojo, si existe, el {@link HechoVitalProcedimiento} relacionada con un {@link Procedimiento} y un 
     * {@link HechoVital}.Si no existe devuelvo null.
     * 
     * @param session
     * @param idProc
     * @param idHecho
     * @return {@link HechoVitalProcedimiento}
     * @throws HibernateException
     */
    public static HechoVitalProcedimiento recogerHVProcedimiento(final Session session, final Long idProc, final Long idHecho) throws HibernateException{
		final Query query = session.createQuery("select hvp From HechoVitalProcedimiento as hvp where hvp.procedimiento.id=:idProc and hvp.hechoVital.id=:idHecho");
		query.setLong("idProc", idProc);
		query.setLong("idHecho", idHecho);
		return (HechoVitalProcedimiento)query.uniqueResult();
    }
    
    public static Seccion recogerSeccionCE(final Session session, String codEst) throws HibernateException{
    	final Query query = session.createQuery("select secc From Seccion as secc where secc.codigoEstandard=:codEst");
		query.setString("codEst", codEst);
		return (Seccion)query.uniqueResult();
    }
    
    public static Tratamiento recogerTratamientoCE(final Session session, String codEst) throws HibernateException{
    	final Query query = session.createQuery("select trat From Tratamiento as trat where trat.codigoEstandar=:codEst");
		query.setString("codEst", codEst);
		return (Tratamiento)query.uniqueResult();
    }
    
    public static UnidadAdministrativaRemota recogerUnidad(final Session session, Long idExtUA, Long idAdmin) throws HibernateException{
    	final Query query = session.createQuery("select unidad From UnidadAdministrativaRemota as unidad where unidad.administracionRemota.id=:idAdmin and unidad.idExterno=:idUnidad");
		query.setLong("idAdmin", idAdmin);
		query.setLong("idUnidad", idExtUA);
		return (UnidadAdministrativaRemota)query.uniqueResult();
    }
    
    public static FichaRemota recogerFicha(final Session session, Long idFicha, Long idAdmin) throws HibernateException{
    	final Query query = session.createQuery("select ficha From FichaRemota as ficha where ficha.administracionRemota.id=:idAdmin and ficha.idExterno=:idFicha");
		query.setLong("idAdmin", idAdmin);
		query.setLong("idFicha", idFicha);
		return (FichaRemota)query.uniqueResult();
    }
    
    public static EdificioRemoto recogerEdificio(final Session session, Long idEdificio, Long idAdmin) throws HibernateException{
    	final Query query = session.createQuery("select edificio From EdificioRemoto as edificio where edificio.administracionRemota.id=:idAdmin and edificio.idExterno=:idEdificio");
		query.setLong("idAdmin", idAdmin);
		query.setLong("idEdificio", idEdificio);
		
		EdificioRemoto edificio = (EdificioRemoto)query.uniqueResult();
			
		if (edificio != null) {
			Hibernate.initialize(edificio.getUnidadesAdministrativas());
		}
		return edificio;
    }
    
    public static TramiteRemoto recogerTramite(final Session session, Long idTramite, Long idAdmin) throws HibernateException{
    	final Query query = session.createQuery("select tramite From TramiteRemoto as tramite where tramite.administracionRemota.id=:idAdmin and tramite.idExterno=:idTramite");
		query.setLong("idAdmin", idAdmin);
		query.setLong("idTramite", idTramite);
		
		TramiteRemoto tramite = (TramiteRemoto)query.uniqueResult();
			
		if (tramite != null) {
			Hibernate.initialize(tramite.getProcedimiento());
			Hibernate.initialize(tramite.getTaxes());
			Hibernate.initialize(tramite.getDocsInformatius());
			Hibernate.initialize(tramite.getFormularios());
		}
		return tramite;
    }
    
    public static ProcedimientoRemoto recogerProcedimiento(final Session session, Long idProc, Long idAdmin) throws HibernateException{
    	final Query query = session.createQuery("select proc From ProcedimientoRemoto as proc where proc.administracionRemota.id=:idAdmin and proc.idExterno=:idProc");
		query.setLong("idAdmin", idAdmin);
		query.setLong("idProc", idProc);

        ProcedimientoRemoto procRemoto = (ProcedimientoRemoto) query.uniqueResult();
        if(procRemoto != null){
            Hibernate.initialize(procRemoto.getDocumentos());
        }
		return (ProcedimientoRemoto)query.uniqueResult();
    }

    public static NormativaRemota recogerNormativaRemota(final Session session, Long idNorm, Long idAdmin) throws HibernateException{
    	final Query query = session.createQuery("select norm From NormativaRemota as norm where norm.administracionRemota.id=:idAdmin and norm.idExterno=:idNorm");
		query.setLong("idAdmin", idAdmin);
		query.setLong("idNorm", idNorm);

        NormativaRemota normRemota = (NormativaRemota) query.uniqueResult();
        if(normRemota != null){
            Hibernate.initialize(normRemota.getProcedimientos());
        }
		return (NormativaRemota)query.uniqueResult();
    }
    
    public static AdministracionRemota recogerAdministracionRemota(final Session session, String idRemoto) throws HibernateException{
    	final Query query =  session.createQuery("select admin from AdministracionRemota as admin where admin.idRemoto=:idRemoto");
		query.setString("idRemoto", idRemoto);
		return (AdministracionRemota)query.uniqueResult();
    }
    
    /**
     * A partir de un Array de Strings con el codigo estandar de las materias devuelvo
     * un set con las materias que contiene el codigo estandar
     * 
     * @param session
     * @param ceMaterias
     * @return Set<Materia>
     * @throws HibernateException
     */
    @SuppressWarnings("unchecked")
	public static Set<Materia> recogerMateriasCE(final Session session, String[] ceMaterias) throws HibernateException{
    	Set<Materia> materias = null;
    	if(ceMaterias!=null && ceMaterias.length>0){
	    	final Query query = session.createQuery("select mat From Materia as mat where mat.codigoEstandar in (:mats)");
			query.setParameterList("mats",ceMaterias,Hibernate.STRING);
			List<Materia> materiasL = query.list();
			if (materiasL!=null) {
				materias= new HashSet<Materia>(materiasL);
			}
    	}
		return (Set<Materia>) materias;
    }
    
    /**
     * A partir de un Array de Strings con el codigo estandar de los HechosVitales devuelvo
     * un set con los hechosVitales que conteiene el codigo estandar
     * 
     * @param session
     * @param ceHechos
     * @return Set<HechoVital>
     * @throws HibernateException
     */
    @SuppressWarnings("unchecked")
	public static Set<HechoVital> recogerHechosCE(final Session session, String[] ceHechos) throws HibernateException{
    	Set<HechoVital> hechos = null;
    	if(ceHechos!=null && ceHechos.length>0){
    		final Query query = session.createQuery("select hecho From HechoVital as hecho where hecho.codigoEstandar in (:hechos)");
    		query.setParameterList("hechos",ceHechos,Hibernate.STRING);
    		List<HechoVital> hechosL = query.list();
			if (hechosL!=null) {
				hechos = new HashSet<HechoVital>(hechosL);
			}
    	}
		return (Set<HechoVital>) hechos;
    }
    
    /**
     * A partir de un String con el codigo estandar de la iniciación obtengo el objeto Iniciacion

     * 
     * @param session
     * @param ceIniciacion
     * @return Iniciacion
     * @throws HibernateException
     */
    public static Iniciacion recogerIniciacionCE(final Session session, String ceIniciacion) throws HibernateException{
    	final Query query =  session.createQuery("select ini from Iniciacion as ini where ini.codigoEstandar=:ceIniciacion");
		query.setString("ceIniciacion", ceIniciacion);
		return (Iniciacion)query.uniqueResult();
    }

    /** Documento Remoto a partir de idExterno y adm remota
     * */
     public static DocumentoRemoto recogerDocumento(final Session session, Long idDoc, Long idAdmin) throws HibernateException{
    	final Query query = session.createQuery("select doc From DocumentoRemoto as doc where doc.administracionRemota.id=:idAdmin and doc.idExterno=:idDoc");
		query.setLong("idAdmin", idAdmin);
		query.setLong("idDoc", idDoc);

        DocumentoRemoto docRemoto = (DocumentoRemoto) query.uniqueResult();

        if(docRemoto != null){
            Hibernate.initialize(docRemoto.getArchivo());
            for (Iterator iterator = docRemoto.getLangs().iterator(); iterator.hasNext();) {
                String lang = (String) iterator.next();
                TraduccionDocumento traduccion = (TraduccionDocumento) docRemoto.getTraduccion(lang);
                if(traduccion != null){
                    Hibernate.initialize(traduccion.getArchivo());
                }
            }
        }
		return (DocumentoRemoto)query.uniqueResult();
    }

}
