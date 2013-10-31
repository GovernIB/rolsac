package org.ibit.rol.sac.persistence.ejb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.lang.StringUtils;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Formulario;
import org.ibit.rol.sac.model.Taxa;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DestinatarioDelegate;
import org.ibit.rol.sac.persistence.delegate.TramiteDelegateI;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.remote.vuds.ActualizacionVudsException;
import org.ibit.rol.sac.persistence.remote.vuds.ValidateVudsException;
import org.ibit.rol.sac.persistence.saver.TramiteSaver;
import org.ibit.rol.sac.persistence.ws.Actualizador;

/**
 * SessionBean para mantener y consultar Tramites.
 *
 * @ejb.bean name="sac/persistence/TramiteFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.TramiteFacade"
 *           type="Stateless" view-type="remote" transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class TramiteFacadeEJB extends HibernateEJB implements TramiteDelegateI {


	/**
	 * 
	 */
	private static final long serialVersionUID = 15377941969351981L;
	DestinatarioDelegate destDelegate;  

	public DestinatarioDelegate getDestDelegate() {
		return destDelegate;
	}


	public void setDestDelegate(DestinatarioDelegate destDelegate) {
		this.destDelegate = destDelegate;
	}


	/**
	 * Obtiene referéncia al ejb de control de Acceso.
	 * @ejb.ejb-ref ejb-name="sac/persistence/AccesoManager"
	 */
	protected abstract AccesoManagerLocal getAccesoManager();


	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
	}


	/**
	 * Autoriza la creación de un trámite
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public boolean autorizaCrearTramite(Long idProcedimiento) throws SecurityException  {
		return (getAccesoManager().tieneAccesoProcedimiento(idProcedimiento)); 
	}


	/**
	 * Autoriza la modificación trámite
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public boolean autorizaModificarTramite(Long idTramite) throws SecurityException {
		return (getAccesoManager().tieneAccesoTramite(idTramite));
	}   


	/**
	 * Crea o actualiza un trámite.
	 * @param tramite	Indica el trámite a guardar
	 * 
	 * @param idUA	Identificador de la unidad administrativa
	 * 
	 * @throws ValidateVudsException
	 *  
	 * @throws ActualizacionVudsException
	 *  
	 * @throws ActualizadorException 
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission
	 *  
	 * role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 * 
	 * @return	Devuelve el identificador del trámite guardado.
	 */
	public Long grabarTramite(Tramite tramite, Long idUA) {


		Session session = getSession();

		try {

			Long tramiteId = getTramiteSaver().grabarTramite(tramite, idUA, session);

			return tramiteId;

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Obtiene un trámite.
	 * 
	 * @param	id	Indica el identificador del trámite a obtener.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * 
	 * @return Devuelve <code>Tramite</code>
	 */
	public Tramite obtenerTramite(Long id) {

		Session session = getSession();

		try {

			return cargaTramite(session, id);

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * @deprecated Usar el metode borrarDocument()
	 * Elimina un formulario del trámite
	 * @ejb.interface-method
	 * @ejb.permission 
	 *                 role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public void eliminarFormulario(Long tramite_id, Long formulario_id) {
		Session session = getSession();
		try {
			if (!getAccesoManager().tieneAccesoTramite(tramite_id)) {
				throw new SecurityException("No tiene acceso al tr�mite");
			}
			Tramite tramite = (Tramite) session.load(Tramite.class, tramite_id);
			Formulario formulario = (Formulario) session.load(Formulario.class,
					formulario_id);
			tramite.removeFormulario(formulario);
			session.flush();
		} catch (HibernateException e) {
			throw new EJBException(e);
		} finally {
			close(session);
		}
	}


	/**
	 * Borra un trámite
	 * 
	 * @param id	Identificador del trámite a borrar.	
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public void borrarTramite(Long id) {

		Session session = getSession();

		try {

			if ( !getAccesoManager().tieneAccesoTramite(id) )
				throw new SecurityException("No tiene acceso al tr�mite");

			Tramite tramite = (Tramite) session.load(Tramite.class, id);
			session.delete(tramite);
			session.flush();

			Actualizador.borrar(tramite, true);

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Guarda un documento
	 * 
	 * @param	doc	Indica un documento asociado a un determinado trámite.
	 * 
	 * @param idTramite	Identificador de un trámite.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 * 
	 * @return Devuelve el identificador del documento guardado.
	 */
	public Long grabarDocument(DocumentTramit doc, Long idTramite) {

		Session session = getSession();

		try {

			if ( !getAccesoManager().tieneAccesoTramite(idTramite) )
				throw new SecurityException("No tiene acceso al tramite.");

			Tramite tramite = null;
			boolean actualizar = false;

			if ( doc.getId() == null ) {

				tramite = cargaTramite(session, idTramite);
				tramite.addDocument(doc);
				session.save(doc);

			} else {

				session.update(doc);
				actualizar = true;
			}

			session.flush();
			if (actualizar)
				tramite = cargaTramite(session, idTramite);

			if ( tramite.getProcedimiento() != null ) {

				log.debug("Grabar Documento: Lanzo el actualizador");
				Actualizador.actualizar(tramite, true);

			}

			/*
			 * TODO como funciona esto de la indexacion?
			 * 
			 * if (ficha_id != null) { if (ficha_id != null) ficha = ficha =
			 * (Ficha) session.load(Ficha.class, ficha_id); FichaDelegate ficdel
			 * = null!=fichDel? fichDel: DelegateUtil.getFichaDelegate(); try {
			 * ficdel.indexBorraFicha(ficha.getId());
			 * ficdel.indexInsertaFicha(ficha,null); } catch (DelegateException
			 * e) { log.error("Error indexando ficha", e); } } if
			 * (procedimiento_id != null) { if (procedimiento_id != null)
			 * procedimiento = procedimiento = (ProcedimientoLocal)
			 * session.load(ProcedimientoLocal.class, procedimiento_id);
			 * ProcedimientoDelegate pldel = null!=procDel? procDel :
			 * DelegateUtil.getProcedimientoDelegate(); try {
			 * pldel.indexBorraProcedimiento(procedimiento);
			 * pldel.indexInsertaProcedimiento(procedimiento,null); } catch
			 * (DelegateException e) {
			 * log.error("Error indexando procedimiento", e); } }
			 */

			return doc.getId();

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Guarda la tasa asociada a un trámite
	 * 
	 * @param	taxa	Indica la tasa a guardar.
	 * 
	 * @param	idTramite	Identificador de un trámite.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 * 
	 * @return Devuelve el identificador de la tasa guardada.
	 */
	public Long grabarTaxa(Taxa taxa, Long idTramite) {

		Session session = getSession();

		try {

			if ( !getAccesoManager().tieneAccesoTramite(idTramite) )
				throw new SecurityException("No tiene acceso al tramite.");

			Tramite tramite = null;
			boolean actualizar = false;

			if ( taxa.getId() == null ) {

				tramite = cargaTramite(session, idTramite);
				tramite.addTaxa(taxa);
				session.save(taxa);

			} else {

				session.update(taxa);
				actualizar = true;

			}

			session.flush();
			if (actualizar) {
				tramite = cargaTramite(session, idTramite);
			}

			if ( tramite.getProcedimiento() != null ) {

				log.debug("Grabar Taxa: Lanzo el actualizador ");
				Actualizador.actualizar(tramite, true);

			}

			/*
			 * TODO como funciona esto de la indexacion?
			 * 
			 * if (ficha_id != null) { if (ficha_id != null) ficha = ficha =
			 * (Ficha) session.load(Ficha.class, ficha_id); FichaDelegate ficdel
			 * = null!=fichDel? fichDel: DelegateUtil.getFichaDelegate(); try {
			 * ficdel.indexBorraFicha(ficha.getId());
			 * ficdel.indexInsertaFicha(ficha,null); } catch (DelegateException
			 * e) { log.error("Error indexando ficha", e); } } if
			 * (procedimiento_id != null) { if (procedimiento_id != null)
			 * procedimiento = procedimiento = (ProcedimientoLocal)
			 * session.load(ProcedimientoLocal.class, procedimiento_id);
			 * ProcedimientoDelegate pldel = null!=procDel? procDel :
			 * DelegateUtil.getProcedimientoDelegate(); try {
			 * pldel.indexBorraProcedimiento(procedimiento);
			 * pldel.indexInsertaProcedimiento(procedimiento,null); } catch
			 * (DelegateException e) {
			 * log.error("Error indexando procedimiento", e); } }
			 */

			return taxa.getId();

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Obtiene una tasa
	 * 
	 * @param	idTasa	Identificador de la tasa solicitada.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 * 
	 * @return	Devuelve <code>Taxa</code> solicitada.
	 */
	public Taxa obtenirTaxa(Long idTasa) {

		Session session = getSession();

		try {

			Taxa taxa = (Taxa) session.load(Taxa.class, idTasa);
			cargaTramite( session, taxa.getTramit().getId() );

			return taxa;

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Borra una tasa
	 * 
	 * @param id	Identificador de una tasa.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public void borrarTaxa(Long id) {

		Session session = getSession();

		try {

			Taxa taxa = (Taxa) session.load(Taxa.class, id);
			Tramite tramite = cargaTramite( session, taxa.getTramit().getId() );

			long idTramite = taxa.getTramit().getId();

			if ( !getAccesoManager().tieneAccesoTramite(idTramite) )
				throw new SecurityException("No tiene acceso al documento");

			taxa.getTramit().removeTaxa(taxa);

			session.delete(taxa);
			session.flush();

			/*
			 * TODO indexacion if (procedimiento != null) {
			 * ProcedimientoDelegate pldel =
			 * DelegateUtil.getProcedimientoDelegate(); try {
			 * pldel.indexBorraProcedimiento(procedimiento);
			 * pldel.indexInsertaProcedimiento(procedimiento,null); } catch
			 * (DelegateException e) {
			 * log.error("Error indexando procedimiento", e); } }
			 */

			if ( tramite.getProcedimiento() != null ) {

				log.debug("Borro Taxa: Lanzo el actualizador");
				Actualizador.actualizar(tramite, true);

			}

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Obtener documento
	 * 
	 * @param idDocumento	Identificador del documento solicitado.
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 * 
	 * @return Devuelve <code>DocumentTramit</code> solicitado.
	 */
	public DocumentTramit obtenirDocument(Long idDocumento) {

		Session session = getSession();

		try {

			DocumentTramit doc = (DocumentTramit) session.load(DocumentTramit.class, idDocumento);
			Hibernate.initialize( doc.getArchivo() );

			Iterator iterator = doc.getLangs().iterator();
			while ( iterator.hasNext() ) {

				String lang = (String) iterator.next();
				TraduccionDocumento traduccion = (TraduccionDocumento) doc.getTraduccion(lang);

				if ( traduccion != null )
					Hibernate.initialize( traduccion.getArchivo() );

			}

			if ( doc.getTramit() == null ) 
				throw new EJBException("doc.getTramit() es null");

			Tramite tramite = doc.getTramit();
			Hibernate.initialize( tramite );
			cargaTramite( session, tramite.getId() );

			return doc;

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Borra documento
	 * 
	 * @param id	Identificador de un documento
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public void borrarDocument(Long id) {

		Session session = getSession();

		try {

			DocumentTramit document = (DocumentTramit) session.load(DocumentTramit.class, id);
			Tramite tramite = cargaTramite( session, document.getTramit().getId() );

			document.getTramit().removeDocument(document);

			long idTramite = document.getTramit().getId();

			if ( !getAccesoManager().tieneAccesoTramite(idTramite) )
				throw new SecurityException("No tiene acceso al documento");

			session.delete(document);
			session.flush();

			/*
			 * TODO indexacion if (procedimiento != null) {
			 * ProcedimientoDelegate pldel =
			 * DelegateUtil.getProcedimientoDelegate(); try {
			 * pldel.indexBorraProcedimiento(procedimiento);
			 * pldel.indexInsertaProcedimiento(procedimiento,null); } catch
			 * (DelegateException e) {
			 * log.error("Error indexando procedimiento", e); } }
			 */
			// reordenacio de documents
			// TODO posar reordenacio aqui trenca la cohesio. S'hauria de posar
			// fora del metode i cridar
			// la reordenacio desde dins la action 'borrar'

			int tipus = document.getTipus();
			List<DocumentTramit> docs = obtenirDocumentsSegonsTipus(session, idTramite, tipus);
			actualitzarDocumentsPerOrdreNatural(session, docs);

			if ( tramite.getProcedimiento() != null ) {

				log.debug("Borrar Documento: Lanzo el actualizador");
				Actualizador.actualizar(tramite, true);

			}

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Borra documento
	 * 
	 * @param	tramite	Indica el trámite al que pertenecen los documentos
	 * 
	 * @param documentos	Listado de documentos a borrar
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public void borrarDocumentos(Tramite tramite, List<DocumentTramit> documentos) {

		Session session = getSession();

		try {

			if ( !getAccesoManager().tieneAccesoTramite( tramite.getId() ) )
				throw new SecurityException("No tiene acceso al documento");
			
			List<Long> idTramites = new Vector<Long>();
			
			for ( DocumentTramit document : documentos ) {
				document.getTramit().removeDocument(document);
				idTramites.add( document.getId() );

			}

			String consulta = "from DocumentTramit as dt where dt.id in (:tramites)";
			consulta = StringUtils.replaceOnce(consulta, ":tramites", StringUtils.join(idTramites.iterator(), ",") );

			session.delete(consulta);


			for ( int tipus = 0 ; tipus < 4 ; tipus++ ) {

				List<DocumentTramit> docs = obtenirDocumentsSegonsTipus(session, tramite.getId(), tipus);
				actualitzarDocumentsPerOrdreNatural(session, docs);

			}

			if ( tramite.getProcedimiento() != null ) {

				log.debug("Borrar Documento: Lanzo el actualizador");
				Actualizador.actualizar(tramite, true);

			}

			session.flush();
			getSessionFactory().evictCollection("org.ibit.rol.sac.model.Tramite.docsInformatius", tramite.getId());
			getSessionFactory().evictCollection("org.ibit.rol.sac.model.Tramite.formularios", tramite.getId());
			getSessionFactory().evictCollection("org.ibit.rol.sac.model.Tramite.docsRequerits", tramite.getId());
			
		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Actualiza el orden de los documentos
	 * 
	 * @param idTramite	Identificador de un trámite
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public void actualizarOrdenDocs(Map<String, String[]> map, long idTramite) throws DelegateException {

		//TODO: Refactorizar
		Session session = getSession();
		
		if ( !getAccesoManager().tieneAccesoTramite(idTramite) )
			throw new SecurityException("No tiene acceso al documento");

		try {

			// 1. ordenar segun orden relativo dado por el usuario
			Long id;
			int orden = 0;
			List<DocumentTramit> ordenacion = new ArrayList<DocumentTramit>();

			Iterator it = map.entrySet().iterator();
			while ( it.hasNext() ) {

				Map.Entry e = (Map.Entry) it.next();

				String paramName = e.getKey().toString();
				if (paramName.startsWith("orden_doc")) {
					id = Long.valueOf(paramName.substring(9)).longValue();
					String[] parametros = (String[]) e.getValue();
					orden = Integer.parseInt(parametros[0]);

					DocumentTramit documento = (DocumentTramit) session.load(DocumentTramit.class, id);
					documento.setOrden((long)orden);
					ordenacion.add(documento);

				}

			}
			
			session.flush();

			// 2. ordenar segun orden natural (1..N)
			Collections.sort(ordenacion, new DocOrdenComparator());

			actualitzarDocumentsPerOrdreNatural(session, ordenacion);

			// 3. eliminar documents del tr�mit del cache perqu� es recarreguin les llistes de formularis i docsinformatius amb el nou ordre.
			getSessionFactory().evictCollection("org.ibit.rol.sac.model.Tramite.docsInformatius", idTramite);
			getSessionFactory().evictCollection("org.ibit.rol.sac.model.Tramite.formularios", idTramite);
			getSessionFactory().evictCollection("org.ibit.rol.sac.model.Tramite.docsRequerits", idTramite);

			// 4. Actualizamos WS
			Tramite tramite =cargaTramite(session,idTramite);
			if(tramite.getProcedimiento()!=null){
				log.debug("Cambio Orden Document: Lanzo el actualizador");
				Actualizador.actualizar(tramite,true);
			}

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}
	

	/**
	 * @ejb.interface-method
	 * @ejb.permission 
	 *                 role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public void actualizarOrdenTasas(Map<String, String[]> map, long tid)
			throws DelegateException {

		//TODO: Refactorizar
		Session session = getSession();
		if (!getAccesoManager().tieneAccesoTramite(tid)) {
			throw new SecurityException("No tiene acceso a la tasa");
		}
		try {
			// 1. ordenar segun orden relativo dado por el usuario
			Long id;
			int valor_orden = 0;
			List<Taxa> taxa_orden = new ArrayList<Taxa>();

			Iterator it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry e = (Map.Entry) it.next();

				String paramName = e.getKey().toString();
				if (paramName.startsWith("orden_taxa")) {
					id = Long.valueOf(paramName.substring(10)).longValue();
					String[] parametros = (String[]) e.getValue();
					valor_orden = Integer.parseInt(parametros[0]);

					Taxa tasa = (Taxa) session.load(
							Taxa.class, id);
					tasa.setOrden((long)valor_orden);
					taxa_orden.add(tasa);
				}
			}
			session.flush();

			// 2. ordenar segun orden natural (1..N)
			Collections.sort(taxa_orden, new TasaOrdenComparator());

			actualitzarTaxesPerOrdreNatural(session, taxa_orden);

			// 3. eliminar taxes del cache perqu� es recarreguin les llistes de taxes amb el nou ordre.
			getSessionFactory().evictCollection("org.ibit.rol.sac.model.Tramite.taxes", tid);
			// 4. Actualizamos WS
			Tramite tramite=cargaTramite(session,tid);
			if(tramite.getProcedimiento()!=null){
				log.debug("Cambio Orden tasas: Lanzo el actualizador");
				Actualizador.actualizar(tramite,true);
			}
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}

	}

	// ====================== sección privada =============================

	class DocOrdenComparator implements Comparator<DocumentTramit> {

		public int compare(DocumentTramit o1, DocumentTramit o2) {

			Long x1 = o1.getOrden();
			Long x2 = o2.getOrden();

			return x1.compareTo(x2);

		}

	}

	class TasaOrdenComparator implements Comparator<Taxa> {
		public int compare(Taxa o1, Taxa o2) {
			Long x1 = o1.getOrden();
			Long x2 = o2.getOrden();
			return x1.compareTo(x2);
		}
	}	


	/**
	 * Actualiza los documentos por orden natural
	 * 
	 * @param	session	Indica la sesión de hibernate.
	 * 
	 * @param	docs	Indica un listado de documentos.
	 * */
	private void actualitzarDocumentsPerOrdreNatural(Session session, List<DocumentTramit> docs) {

		try {

			Long contador = 1L;
			Iterator<DocumentTramit> itdoc = docs.iterator();

			while ( itdoc.hasNext() ) {

				DocumentTramit dt = itdoc.next();
				dt.setOrden(contador++);
				session.update(dt);

			}

			session.flush();

		} catch (HibernateException he) {

			throw new EJBException(he);

		}

	}


	/**
	 * Actualiza las tasas por orden natural
	 * 
	 * @param session Indica la sesión de Hibernate.
	 * 
	 * @param	tasas	Indica un listado de tasas.
	 * */
	private void actualitzarTaxesPerOrdreNatural(Session session, List<Taxa> tasas) {

		try {

			Long contador = 1L;
			Iterator<Taxa> it = tasas.iterator();

			while ( it.hasNext() ) {

				Taxa t = it.next();
				t.setOrden(contador++);
				session.update(t);

			}

			session.flush();

		} catch (HibernateException he) {

			throw new EJBException(he);

		}

	}


	// Se hace pública para que se pueda acceder desde procedimentFacadeEJB
	public Tramite cargaTramite(Session session, Long idTramite) throws HibernateException {

		Tramite tramite = (Tramite) session.load(Tramite.class, idTramite);
		Hibernate.initialize(tramite.getFormularios());

		for ( DocumentTramit formulario : tramite.getFormularios() ) {

			for ( final String idioma : (Collection<String>) tramite.getLangs() ) {

				final TraduccionDocumento traduccion = (TraduccionDocumento) formulario.getTraduccion(idioma);

				if ( traduccion != null )
					Hibernate.initialize(traduccion.getArchivo());

			}

		}

		Hibernate.initialize(tramite.getDocsInformatius());

		for ( DocumentTramit docInformatiu : tramite.getDocsInformatius() ) {

			for ( final String idioma : (Collection<String>) tramite.getLangs() ) {

				final TraduccionDocumento traduccion = (TraduccionDocumento) docInformatiu.getTraduccion(idioma);

				if ( traduccion != null )
					Hibernate.initialize(traduccion.getArchivo());

			}

		}

		Hibernate.initialize(tramite.getDocsRequerits());
		Hibernate.initialize(tramite.getTaxes());

		return tramite;

	}


	/** 
	 * Obtiene documentos filtrado por tipo
	 * 
	 * @param	session Indica la sesión de hibernate.
	 * 
	 * @param tid	Indentificador de un trámite.
	 * 
	 * @param tipus	Filtro que indica el tipo de documento.
	 * 
	 * @return	Devuelve <code>List<DocumentTramit></code> filtrados por tipo.
	 * 
	 * */
	private List<DocumentTramit> obtenirDocumentsSegonsTipus(Session session, long tid, int tipus) throws HibernateException {

		Query query = session.createQuery("from DocumentTramit where tipus = :tipus and coditra = :id");
		query.setInteger("tipus", tipus);
		query.setLong("id", tid);

		return query.list();

	}


	protected boolean publico(Tramite tram) {
		return tram.esPublico();
	}


	/**
	 * Valida si tiene acceso al trámite
	 * 
	 * @param	tramite	Indica un trámite
	 * 
	 * @return Devuelve <code>true</code> si el usuario tiene acceso al trámite.
	 * */
	public boolean tieneAccesoTramite( Tramite tramite) {

		if ( null == tramite.getId() ) 
			return true;

		return getAccesoManager().tieneAccesoTramite( tramite.getId() );

	}



	TramiteSaver tramiteSaver;

	public TramiteSaver getTramiteSaver() {

		if ( null == tramiteSaver ) 
			tramiteSaver = new TramiteSaver(this);

		return tramiteSaver;

	}

	public void setTramiteSaver(TramiteSaver tramiteSaver) {
		this.tramiteSaver = tramiteSaver;
	}



}
