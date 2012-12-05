package org.ibit.rol.sac.persistence.ejb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

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
public abstract class TramiteFacadeEJB extends HibernateEJB implements
		TramiteDelegateI {

	
	DestinatarioDelegate destDelegate;  

    public DestinatarioDelegate getDestDelegate() {
		return destDelegate;
	}

	public void setDestDelegate(DestinatarioDelegate destDelegate) {
		this.destDelegate = destDelegate;
	}

    /**
     * Obtiene refer�ncia al ejb de control de Acceso.
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
     * Autoriza la creaci�n de un tr�mite
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public boolean autorizaCrearTramite(Long idProcedimiento) throws SecurityException  {
    	return (getAccesoManager().tieneAccesoProcedimiento(idProcedimiento)); 
    }
    
        
    /**
     * Autoriza la modificaci�n tr�mite
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public boolean autorizaModificarTramite(Long idTramite) throws SecurityException {
         return (getAccesoManager().tieneAccesoTramite(idTramite));
    }   
    
    
    
	/**
	 * Crea o actualiza un tramite.
	 * @throws ValidateVudsException 
	 * @throws ActualizacionVudsException 
	 * @throws ActualizadorException 
	 * 
	 * @ejb.interface-method
	 * @ejb.permission 
	 *                 role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public Long grabarTramite(Tramite tramite, Long idOC) {
		
		
        Session session = getSession();

        try {
    		Long tramiteId = getTramiteSaver().grabarTramite(tramite,idOC,session);
			return tramiteId;
    		
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

	
	
    /**
     * Obtiene un tramite.
	 * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
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

	/*    *//**
	 * A�ade un nuevo formulario al tramite ejb.interface-method
	 * ejb.permission
	 * role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	/*
	 * 
	 * public void anyadirFormulario(Long tramite_id, Long formulario_id) {
	 * Session session = getSession(); try { if
	 * (!getAccesoManager().tieneAccesoTramite(tramite_id)) { throw new
	 * SecurityException("No tiene acceso al tr�mite"); } Tramite tramite =
	 * (Tramite) session.load(Tramite.class, tramite_id); Document formulario =
	 * (Document) session.load(Document.class, formulario_id);
	 * tramite.addFormulario(formulario); session.flush(); } catch
	 * (HibernateException e) { throw new EJBException(e); } finally {
	 * close(session); } }
	 */
    /**
	 * elimina un formulario del tramite
	 * 
	 * @deprecated. Usar el metode borrarDocument()
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
	 * Borrar un tramite
	 * 
     * @ejb.interface-method
	 * @ejb.permission 
	 *                 role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
	public void borrarTramite(Long id) {
        Session session = getSession();
        try {
			if (!getAccesoManager().tieneAccesoTramite(id)) {
                    throw new SecurityException("No tiene acceso al tr�mite");
            }
			Tramite tramite = (Tramite) session.load(Tramite.class, id);
			session.delete(tramite);
            session.flush();
            Actualizador.borrar(tramite,true);            
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
	public Long grabarDocument(DocumentTramit doc, Long tid) {
        Session session = getSession();
        try {
			if (!getAccesoManager().tieneAccesoTramite(tid)) {
				throw new SecurityException("No tiene acceso al tramite.");
			}

			Tramite tramite = null;
			boolean actualizar=false;
			
			if (doc.getId() == null) {
				tramite = cargaTramite(session, tid);
				tramite.addDocument(doc);
				session.save(doc);
			} else {
				session.update(doc);
				actualizar=true;
            }
            session.flush();
			if(actualizar){tramite = cargaTramite(session, tid);}
			if (null != tramite.getProcedimiento()){
				log.debug("Grabar Documento: Lanzo el actualizador");
				Actualizador.actualizar(tramite,true);
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
	 * @ejb.interface-method
	 * @ejb.permission 
	 *                 role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public Long grabarTaxa(Taxa taxa, Long tid) {
		Session session = getSession();
		try {
			if (!getAccesoManager().tieneAccesoTramite(tid)) {
				throw new SecurityException("No tiene acceso al tramite.");
			}

			Tramite tramite = null;
			boolean actualizar=false;
			
			if (taxa.getId() == null) {
				tramite = cargaTramite(session, tid);
				tramite.addTaxa(taxa);
				session.save(taxa);
			} else {
				session.update(taxa);
				actualizar=true;
			}
			session.flush();
			if(actualizar){tramite = cargaTramite(session, tid);}
			if (null != tramite.getProcedimiento()){
				log.debug("Grabar Taxa: Lanzo el actualizador ");
				Actualizador.actualizar(tramite,true);
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
	 * @ejb.interface-method
	 * @ejb.permission 
	 *                 role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 */
	public Taxa obtenirTaxa(Long taxId) {
		Session session = getSession();
		try {
			Taxa taxa = (Taxa) session.load(Taxa.class, taxId);
			/*
			 * for (Iterator iterator = taxa.getLangs().iterator();
			 * iterator.hasNext();) { String lang = (String) iterator.next();
			 * TraduccionTaxa traduccion = (TraduccionTaxa)
			 * taxa.getTraduccion(lang); }
			 */
			cargaTramite(session, taxa.getTramit().getId());
			// Hibernate.initialize(taxa.getTramit());

			/*
			 * Tramite tramite=doc.getTramit(); if(null!=tramite) {
			 * Hibernate.initialize(tramite.getFormularios());
			 * Hibernate.initialize(tramite.getDocsInformatius());
			 * Hibernate.initialize(tramite.getDocsPresentar());
			 * Hibernate.initialize(tramite.getRequisits()); }
			 */
			return taxa;
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
	public void borrarTaxa(Long id) {
		Session session = getSession();
		try {

			Taxa taxa = (Taxa) session.load(Taxa.class, id);
			Tramite tramite = cargaTramite(session, taxa.getTramit().getId());

			long tid = taxa.getTramit().getId();

			if (!getAccesoManager().tieneAccesoTramite(tid)) {
				throw new SecurityException("No tiene acceso al documento");
			}

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

			if (null != tramite.getProcedimiento()){
				log.debug("Borro Taxa: Lanzo el actualizador");
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
	public DocumentTramit obtenirDocument(Long docId) {
		Session session = getSession();
		try {
			DocumentTramit doc = (DocumentTramit) session.load(
					DocumentTramit.class, docId);
			Hibernate.initialize(doc.getArchivo());
			for (Iterator iterator = doc.getLangs().iterator(); iterator
					.hasNext();) {
				String lang = (String) iterator.next();
				TraduccionDocumento traduccion = (TraduccionDocumento) doc
						.getTraduccion(lang);
				if (traduccion != null) {
					Hibernate.initialize(traduccion.getArchivo());
				}
			}
			if (null == doc.getTramit()) throw new EJBException("doc.getTramit() es null");
			Hibernate.initialize(doc.getTramit());
			cargaTramite(session, doc.getTramit().getId());

			/*
			 * Tramite tramite=doc.getTramit(); if(null!=tramite) {
			 * Hibernate.initialize(tramite.getFormularios());
			 * Hibernate.initialize(tramite.getDocsInformatius());
			 * Hibernate.initialize(tramite.getDocsPresentar());
			 * Hibernate.initialize(tramite.getRequisits()); }
			 */
			return doc;
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
	public void borrarDocument(Long id) {
		Session session = getSession();
		try {

			DocumentTramit document = (DocumentTramit) session.load(
					DocumentTramit.class, id);
			Tramite tramite = cargaTramite(session, document.getTramit().getId());

			document.getTramit().removeDocument(document);

			long tid = document.getTramit().getId();

			if (!getAccesoManager().tieneAccesoTramite(tid)) {
				throw new SecurityException("No tiene acceso al documento");
			}

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
			List<DocumentTramit> docs = obtenirDocumentsSegonsTipus(session,
					tid, tipus);
			actualitzarDocumentsPerOrdreNatural(session, docs);

			if (null != tramite.getProcedimiento()){
				log.debug("Borrar Documento: Lanzo el actualizador");
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
	public void actualizarOrdenDocs(Map<String, String[]> map, long tid)
			throws DelegateException {
		Session session = getSession();
		if (!getAccesoManager().tieneAccesoTramite(tid)) {
			throw new SecurityException("No tiene acceso al documento");
		}
		try {
			// 1. ordenar segun orden relativo dado por el usuario
			Long id;
			int valor_orden = 0;
			List<DocumentTramit> doc_orden = new ArrayList<DocumentTramit>();

			Iterator it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry e = (Map.Entry) it.next();

				String paramName = e.getKey().toString();
				if (paramName.startsWith("orden_doc")) {
					id = Long.valueOf(paramName.substring(9)).longValue();
					String[] parametros = (String[]) e.getValue();
					valor_orden = Integer.parseInt(parametros[0]);

					DocumentTramit documento = (DocumentTramit) session.load(
							DocumentTramit.class, id);
					documento.setOrden((long)valor_orden);
					doc_orden.add(documento);
				}
			}
			session.flush();

			// 2. ordenar segun orden natural (1..N)
			Collections.sort(doc_orden, new DocOrdenComparator());

			actualitzarDocumentsPerOrdreNatural(session, doc_orden);

			// 3. eliminar documents del tr�mit del cache perqu� es recarreguin les llistes de formularis i docsinformatius amb el nou ordre.
			getSessionFactory().evictCollection("org.ibit.rol.sac.model.Tramite.docsInformatius", tid);
			getSessionFactory().evictCollection("org.ibit.rol.sac.model.Tramite.formularios", tid);
	     getSessionFactory().evictCollection("org.ibit.rol.sac.model.Tramite.docsRequerits", tid);
			// 4. Actualizamos WS
			Tramite tramite =cargaTramite(session,tid);
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
	
	// ====================== seccio privada =============================

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

	private void actualitzarDocumentsPerOrdreNatural(Session session,
			List<DocumentTramit> docs) {
		try {
			Long contador = 1L;
			Iterator<DocumentTramit> itdoc = docs.iterator();
			while (itdoc.hasNext()) {
				DocumentTramit dt = itdoc.next();
				dt.setOrden(contador++);
				session.update(dt);
			}
			session.flush();

		} catch (HibernateException he) {
			throw new EJBException(he);
		}
	}
	
	private void actualitzarTaxesPerOrdreNatural(Session session, List<Taxa> tasas) {
		try {
			Long contador = 1L;
			Iterator<Taxa> it = tasas.iterator();
			while (it.hasNext()) {
				Taxa t = it.next();
				t.setOrden(contador++);
				session.update(t);
			}
			session.flush();

		} catch (HibernateException he) {
			throw new EJBException(he);
		}
	}
	

	// es fa publica pq t� que se accedida desde procedimentFacadeEJB
	public Tramite cargaTramite(Session session, Long tid)
			throws HibernateException {
		Tramite tramite = (Tramite) session.load(Tramite.class, tid);

		Hibernate.initialize(tramite.getFormularios());
		for (DocumentTramit formulario : tramite.getFormularios()) {
			for (final String idioma : (Collection<String>)tramite.getLangs()){
	            final TraduccionDocumento traduccion = (TraduccionDocumento)formulario.getTraduccion(idioma);
	            if(traduccion!=null){					
	            	Hibernate.initialize(traduccion.getArchivo());
	            }
	        }
		}
		Hibernate.initialize(tramite.getDocsInformatius());
		for (DocumentTramit docInformatiu : tramite.getDocsInformatius()) {
			for (final String idioma : (Collection<String>)tramite.getLangs()){
	            final TraduccionDocumento traduccion = (TraduccionDocumento)docInformatiu.getTraduccion(idioma);
	            if(traduccion!=null){					
	            	Hibernate.initialize(traduccion.getArchivo());
	            }
	        }
		}
    Hibernate.initialize(tramite.getDocsRequerits());
		Hibernate.initialize(tramite.getTaxes());
		return tramite;
	}

	/*
	private void inicialitzarDocumentsTramit(Session session, Tramite tramite)
			throws HibernateException {
		long tid = tramite.getId();
		tramite.setFormularios(listToSet(obtenirDocumentsSegonsTipus(session,
				tid, DocumentTramit.FORMULARI)));
		tramite.setDocsInformatius(listToSet(obtenirDocumentsSegonsTipus(
				session, tid, DocumentTramit.DOCINFORMATIU)));
	}*/

	private List<DocumentTramit> obtenirDocumentsSegonsTipus(Session session,
			long tid, int tipus) throws HibernateException {
		String query = "from DocumentTramit where tipus=:tipus and coditra=:id";
		Query q = session.createQuery(query);
		q.setInteger("tipus", tipus);
		q.setLong("id", tid);

		/*
		 * debug List<DocumentTramit> list = q.list();
		 * if(1==tipus)for(DocumentTramit dt:list) { log.debug(dt); }
		 */

		return q.list();
	}

	/*
	private Set<DocumentTramit> listToSet(List<DocumentTramit> list) {
		Set<DocumentTramit> set = new TreeSet<DocumentTramit>(
				new DocOrdenComparator());
		for (DocumentTramit dt : list) {
			boolean b = set.add(dt);
		}// if(!b) log.debug(dt); }
		return set;
	}*/
	
    protected boolean publico(Tramite tram) {
		return tram.esPublico();
	}

    

	public boolean tieneAccesoTramite( Tramite tramite) {
		if(null==tramite.getId()) return true;
		
		return getAccesoManager().tieneAccesoTramite(tramite.getId());
	}

    
    
    TramiteSaver tramiteSaver;

	public TramiteSaver getTramiteSaver() {
		if(null==tramiteSaver) 
			tramiteSaver=new TramiteSaver(this); 
		return tramiteSaver;
	}

	public void setTramiteSaver(TramiteSaver tramiteSaver) {
		this.tramiteSaver = tramiteSaver;
	}
 


}
