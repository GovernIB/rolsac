package org.ibit.rol.sac.persistence.ejb;

import net.sf.hibernate.Session;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Hibernate;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.ibit.lucene.indra.model.Catalogo;
import org.ibit.lucene.indra.model.ModelFilterObject;
import org.ibit.lucene.indra.model.TraModelFilterObject;
import org.ibit.rol.sac.model.*;

import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.util.RemotoUtils;
import org.ibit.rol.sac.persistence.util.DateUtils;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.List;

/**
 * SessionBean para mantener y consultar Procedimientos Remotos (PORMAD)
 * 
 * @ejb.bean name="sac/persistence/ProcedimientoRemotoFacade"
 *           jndi-name="org.ibit.rol.sac.persistence.ProcedimientoRemotoFacade"
 *           type="Stateless" view-type="remote" transaction-type="Container"
 * 
 * @ejb.transaction type="Required"
 */
public abstract class ProcedimientoRemotoFacadeEJB extends HibernateEJB {

	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public void ejbCreate() throws CreateException {
		super.ejbCreate();
	}


	/**
	 * Crea o actualiza un ProcedimientoRemoto
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param	idRemoto	Identificador remoto del procedimiento
	 * 
	 * @param	procedimiento	Procedimiento remoto a guardar.
	 * 
	 * @param	codEstandarMaterias	Indica el código estándar de las materias asignadas.
	 * 
	 * @param	codEstandarHechosVitales	Indica el código estándar de los hechos vitales asignados.
	 * 
	 * @return	Devuelve el identificador del procedimiento guardado.
	 */
	@SuppressWarnings("unchecked")
	public Long grabarProcedimientoRemoto(final String idRemoto ,final ProcedimientoRemoto procedimiento,
			final String[] codEstandarMaterias, final String[] codEstandarHechosVitales) {

		final Session session = getSession();
		try {

			AdministracionRemota adminRemota = RemotoUtils.recogerAdministracionRemota(session, idRemoto);

			if ( adminRemota == null )
				throw new EJBException("No existe ninguna Administracion Remota asociada");

			procedimiento.setAdministracionRemota(adminRemota);

			return grabarProcedimientoRemoto(session, procedimiento, codEstandarMaterias, codEstandarHechosVitales);

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Crea o actualiza un ProcedimientoRemoto
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param	procedimiento	PRocedimiento remoto a guardar.
	 * 
	 * @param	ceMaterias	Indica el código estándar de las materias asignadas.
	 * 
	 * @param	ceHechos	Indica el código estándar de los hechos vitales asignadas.
	 * 
	 * @return	Devuelve el identificador del procedimiento guardado.
	 */
	@SuppressWarnings("unchecked")
	public Long grabarProcedimientoRemoto(final ProcedimientoRemoto procedimiento,
			final String[] ceMaterias, final String[] ceHechos) {

		final Session session = getSession();
		try {

			AdministracionRemota adminRemota = null;

			if ( procedimiento.getAdministracionRemota() != null ) {

				adminRemota = (AdministracionRemota) session.load(AdministracionRemota.class, 
						procedimiento.getAdministracionRemota().getId());

			}

			if ( adminRemota == null )
				throw new EJBException("No existe ninguna Administracion Remota asociada");

			procedimiento.setAdministracionRemota(adminRemota);

			return grabarProcedimientoRemoto(session, procedimiento, ceMaterias, ceHechos);

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Crea o actualiza un ProcedimientoRemoto
	 * 
	 * @param session	Indica la sesión de hibernate.
	 * 
	 * @param procedimiento	Indica el procedimiento remoto a guardar.
	 * 
	 * @param codEstandarMaterias	Indica el código estándar de las materias asignadas.
	 * 
	 * @param codEstandarHechosVitales	Indica el código estándar de los hechos vitales asignados.
	 * 
	 * @return Devuelve el identificador del procedimiento remoto guardado.
	 * 
	 * @throws HibernateException
	 */
	@SuppressWarnings("unchecked")
	private Long grabarProcedimientoRemoto(final Session session, final ProcedimientoRemoto procedimiento,
			final String[] codEstandarMaterias, final String[] codEstandarHechosVitales) throws HibernateException {

		final Set<Materia> materias = RemotoUtils.recogerMateriasCE(session, codEstandarMaterias);
		if ( materias != null ) {

			procedimiento.setMaterias(materias);

		} else {

			procedimiento.setMaterias(Collections.EMPTY_SET);

		}

		AdministracionRemota adminRemota = procedimiento.getAdministracionRemota();

		if ( procedimiento.getId() != null ) {

			session.update(procedimiento);

		} else {

			session.save(procedimiento);
			adminRemota.addProcedimientoRemoto(procedimiento);
			procedimiento.setAdministracionRemota(adminRemota);

		}

		final Set<HechoVital> hechos = RemotoUtils.recogerHechosCE(session, codEstandarHechosVitales);
		if ( hechos != null && !hechos.isEmpty() ) {

			if ( procedimiento.getHechosVitalesProcedimientos() == null ) {

				procedimiento.setHechosVitalesProcedimientos( new HashSet<HechoVitalProcedimiento>() );

			} else {

				Set<HechoVitalProcedimiento> s = procedimiento.getHechosVitalesProcedimientos();
				for ( final HechoVitalProcedimiento heProc : s ) {

					boolean relacionado = false;
					for ( final HechoVital hecho : hechos ) {

						if ( relacionado = hecho.getId().equals( heProc.getHechoVital().getId() ) ) {

							hechos.remove(hecho);
							break;

						}

					}

					if ( !relacionado ) {

						heProc.getHechoVital().removeHechoVitalProcedimiento(heProc);
						procedimiento.removeHechoVitalProcedimiento(heProc);
						session.delete(heProc);

					}

				}

			}

			for ( final HechoVital hecho : hechos ) {

				final HechoVitalProcedimiento hvProc = new HechoVitalProcedimiento();
				hvProc.setHechoVital(hecho);
				hvProc.setProcedimiento(procedimiento);
				procedimiento.addHechoVitalProcedimiento(hvProc);
				hecho.addHechoVitalProcedimiento(hvProc);
				session.save(hvProc);

			}

		} else {

			procedimiento.setHechosVitalesProcedimientos(Collections.EMPTY_SET);

		}

		session.flush();

		return procedimiento.getId();

	}


	private ModelFilterObject obtenerFilterObject(ProcedimientoRemoto procedimiento) throws DelegateException {

		ModelFilterObject filter = new ModelFilterObject();
		TraModelFilterObject trafilter;
		String idioma;
		String txids;
		String txtexto;

		filter.setMicrosite_id(null);
		filter.setSeccion_id(null);

		// Obtenemos las materias y hechos vitales
		Materia mat;
		HechoVitalProcedimiento hvital;
		Hashtable listaMaterias = new Hashtable();
		Hashtable listaHechosVitales = new Hashtable();
		UnidadAdministrativa ua = procedimiento.getUnidadAdministrativa();
		List listaPadres = DelegateUtil.getUADelegate().listarPadresUnidadAdministrativa( ua.getId() );

		if ( procedimiento.getMaterias() != null ) {

			Iterator itmat = procedimiento.getMaterias().iterator();
			while ( itmat.hasNext() ) {

				mat = (Materia) itmat.next();
				if ( !listaMaterias.containsKey(mat.getId() ) )
					listaMaterias.put( mat.getId(), mat );
			}

		}

		if ( procedimiento.getHechosVitalesProcedimientos() != null ) {

			Iterator iteradorHechosVitales = procedimiento.getHechosVitalesProcedimientos().iterator();
			while ( iteradorHechosVitales.hasNext() ) {

				hvital = (HechoVitalProcedimiento) iteradorHechosVitales.next();
				if ( !listaHechosVitales.containsKey( hvital.getHechoVital().getId() ) )
					listaHechosVitales.put( hvital.getHechoVital().getId(), hvital.getHechoVital() );

			}

		}

		Iterator langs = procedimiento.getLangs().iterator();
		while ( langs.hasNext() ) {

			idioma = (String) langs.next();
			txids = Catalogo.KEY_SEPARADOR;
			txtexto = " ";
			trafilter = new TraModelFilterObject();
			trafilter.setMaintitle(null); 

			// Obtenemos la UA con sus padres excepto el raiz
			if ( ua != null ) {

				txids = Catalogo.KEY_SEPARADOR;
				txtexto = " ";
				UnidadAdministrativa uaPadre = null;
				for ( int x = 1 ; x < listaPadres.size() ; x++ ) {

					uaPadre = (UnidadAdministrativa) listaPadres.get(x);
					txids += uaPadre.getId() + Catalogo.KEY_SEPARADOR;
					if ( uaPadre.getTraduccion(idioma) != null )
						txtexto += ( (TraduccionUA) uaPadre.getTraduccion(idioma) ).getNombre() + " ";

				}

				filter.setUo_id( ( txids.length() == 1 ) ? null: txids );
				trafilter.setUo_text( ( txtexto.length() == 1 ) ? null : txtexto );

			}

			// Obtenemos su Familia
			Familia familia = procedimiento.getFamilia();
			if ( familia != null ) {

				filter.setFamilia_id( familia.getId() );
				if ( familia.getTraduccion(idioma) != null )	
					trafilter.setFamilia_text( ( (TraduccionFamilia) familia.getTraduccion(idioma) ).getNombre() );

			}

			// Obtenemos las materias y hechos vitales
			txids = Catalogo.KEY_SEPARADOR;
			txtexto = " ";

			Enumeration i = listaMaterias.keys();

			while ( i.hasMoreElements() ) {

				Materia materia = (Materia) listaMaterias.get( i.nextElement() );
				txids += materia.getId() + Catalogo.KEY_SEPARADOR; //anadir los ids (los de los hechos vitales no)
				if ( materia.getTraduccion(idioma) != null ) {

					txtexto += ( (TraduccionMateria) materia.getTraduccion(idioma) ).getNombre() + " ";
					txtexto += ( (TraduccionMateria) materia.getTraduccion(idioma) ).getDescripcion() + " ";
					txtexto += ( (TraduccionMateria) materia.getTraduccion(idioma) ).getPalabrasclave() + " ";

				}

			}

			i = listaHechosVitales.keys();
			HechoVital hechovital = null;

			while ( i.hasMoreElements() ) {

				hechovital = (HechoVital) listaHechosVitales.get( i.nextElement() );
				if ( hechovital.getTraduccion(idioma) != null ) {

					txtexto += ( (TraduccionHechoVital) hechovital.getTraduccion(idioma) ).getNombre() + " ";
					txtexto += ( (TraduccionHechoVital) hechovital.getTraduccion(idioma) ).getDescripcion() + " ";
					txtexto += ( (TraduccionHechoVital) hechovital.getTraduccion(idioma) ).getPalabrasclave() + " ";

				}

			}

			filter.setMateria_id( ( txids.length() == 1 ) ? null : txids );
			trafilter.setMateria_text( ( txtexto.length() == 1 ) ? null : txtexto );

			filter.addTraduccion(idioma, trafilter);

		}

		return filter;

	}


	/**
	 * Añade los procedimientos al índice en todos los idiomas
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 */
	public void indexInsertaProcedimientoRemoto(ProcedimientoRemoto proc, ModelFilterObject filter)  {


		try { 

			if ( proc.getValidacion().equals(2) ) 
				return;

			if ( filter == null ) 
				filter = obtenerFilterObject(proc);  	

			String tipo = tipoProcedimiento(proc,false);
			Iterator iterator = proc.getLangs().iterator();
			while (  iterator.hasNext() ) {

				String idi = (String) iterator.next();
				IndexObject io = new IndexObject();

				io.setId( tipo + "." + proc.getId() );
				io.setClasificacion(tipo);

				io.setMicro( filter.getMicrosite_id() );
				io.setUo( filter.getUo_id() );
				io.setMateria( filter.getMateria_id() );
				io.setFamilia( filter.getFamilia_id() );
				io.setSeccion( filter.getSeccion_id() );

				io.setCaducidad("");	
				io.setPublicacion("");
				io.setDescripcion(""); 
				if ( proc.getFechaCaducidad() != null )		
					io.setCaducidad( new java.text.SimpleDateFormat("yyyyMMdd").format( proc.getFechaCaducidad() ) );

				if ( proc.getFechaPublicacion() != null )	
					io.setPublicacion( new java.text.SimpleDateFormat("yyyyMMdd").format( proc.getFechaPublicacion() ) );

				TraduccionProcedimientoLocal trad = ( (TraduccionProcedimientoLocal) proc.getTraduccion(idi) );
				if ( trad != null ) {

					io.setTituloserviciomain( trad.getNombre() );
					io.setUrl( "/govern/sac/visor_proc.do?codi=" + proc.getId() + "&lang=" + idi + "&coduo=" + proc.getUnidadAdministrativa().getId() );
					// Si es externo ponemos su propia URL
					if ( tipo.equals(Catalogo.SRVC_PROCEDIMIENTOS_EXTERNO) )	
						io.setUrl( proc.getUrl() );

					if ( trad.getNombre() != null ) {

						io.setTitulo( trad.getNombre() );
						io.addTextLine( trad.getNombre() );
						if ( trad.getResumen() != null ) {

							if ( trad.getResumen().length() > 200 ) {

								io.setDescripcion( trad.getResumen().substring(0,199) + "...");

							} else io.setDescripcion( trad.getResumen() );

						}

					}

					if ( trad.getDestinatarios() != null ) 	
						io.addTextLine( trad.getDestinatarios() );

					if ( trad.getLugar() != null )			
						io.addTextLine( trad.getLugar() );

					if ( trad.getObservaciones() != null )	
						io.addTextLine( trad.getObservaciones() );

					if ( trad.getPlazos() != null )			
						io.addTextLine( trad.getPlazos() );

					if ( trad.getResolucion() != null )		
						io.addTextLine( trad.getResolucion() );

					if ( trad.getNotificacion() != null )	
						io.addTextLine( trad.getNotificacion() );

					if ( trad.getRecursos() != null )		
						io.addTextLine( trad.getRecursos() ); // No está en el mantenimiento

					if ( trad.getRequisitos() != null )		
						io.addTextLine( trad.getRequisitos() );

					if ( trad.getSilencio() != null )		
						io.addTextLine( trad.getSilencio() );


				}

				io.addTextopcionalLine( filter.getTraduccion(idi).getMateria_text() );
				io.addTextopcionalLine( filter.getTraduccion(idi).getSeccion_text() );
				io.addTextopcionalLine( filter.getTraduccion(idi).getUo_text() );
				io.addTextopcionalLine( filter.getTraduccion(idi).getFamilia_text() );
				if ( proc.getMaterias() != null ) {

					Iterator iterador = proc.getMaterias().iterator();
					while ( iterador.hasNext() ) {

						Materia mat = (Materia) iterador.next();
						if ( mat.getTraduccion(idi) != null )
							io.addTextopcionalLine( ( (TraduccionMateria) mat.getTraduccion(idi) ).getNombre() );

					}

				}

				if ( io.getText().length() > 0 )
					org.ibit.rol.sac.persistence.delegate.DelegateUtil.getIndexerDelegate().insertaObjeto(io, idi);

			}

		} catch (Exception ex) {

			log.warn("[indexInsertaProcedimiento:" + proc.getId() + "] No se ha podido indexar el procedimiento. " + ex.getMessage());

		}

	}


	private String tipoProcedimiento (ProcedimientoLocal proc, boolean doc) {

		String tipo = "";

		if ( !doc ) {

			if ( proc.getUrl() != null  &&  proc.getUrl().length() > 0 ) {

				tipo = Catalogo.SRVC_PROCEDIMIENTOS_EXTERNO;

			} else if ( ( proc.getVersion() == null  &&  proc.getTramite() == null ) || ( proc.getVersion() == null && proc.getTramite() != null && proc.getTramite().length() == 0 ) ) {

				tipo = Catalogo.SRVC_PROCEDIMIENTOS_NOTELEMATICO;

			} else {

				tipo = Catalogo.SRVC_PROCEDIMIENTOS_SISTRA;
			}

		} else {

			if ( proc.getUrl() != null && proc.getUrl().length() > 0 )  {

				tipo = Catalogo.SRVC_PROCEDIMIENTOS_EXTERNO_DOCUMENTOS;

			} else if ( proc.getVersion() == null && proc.getTramite() == null ) 	{

				tipo = Catalogo.SRVC_PROCEDIMIENTOS_NOTELEMATICO_DOCUMENTOS;

			} else {

				tipo = Catalogo.SRVC_PROCEDIMIENTOS_SISTRA_DOCUMENTOS;

			}

		}

		return tipo;

	}


	/**
	 * Obtiene un Procedimiento Remoto segun su idExterno y el usuario
	 * actualmente logueado
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param idRemoto	Identificador de un procedimiento remoto.
	 * 
	 * @param	idExterno	Identificador externo del procedimiento
	 * 
	 * @return Devuelve el procedimiento remoto solicitado.
	 */
	public ProcedimientoRemoto obtenerProcedimientoRemoto( final String idRemoto, final Long idExterno) {

		final Session session = getSession();
		try {

			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);

			if ( admin == null )
				throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");

			return RemotoUtils.recogerProcedimiento( session, idExterno, admin.getId() );

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}


	/**
	 * Obtiene un Procedimiento Remoto segun su idExterno y una
	 * AdministracionRemota
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param idExterno	Identificador externo
	 * 
	 * @param	idAdministracion	Identificador de la administración remota.
	 * 
	 * @return	Devuelve el procedimiento remoto solicitado.
	 */
	public ProcedimientoRemoto obtenerProcedimientoRemoto(final Long idExterno, final Long idAdministracion) {

		final Session session = getSession();
		try {

			return RemotoUtils.recogerProcedimiento(session, idExterno, idAdministracion);

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}
	
	
	/**
	 * Borra un Procedimiento Remoto según su idExterno
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param	idRemoto	Identificador del procedimiento remoto.
	 * 
	 * @param	idExterno	Identificador externo del procedimiento.
	 */
	public void borrarProcedimientoRemoto(final String idRemoto ,final Long idExterno) {

		final Session session = getSession();
		try {

			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);

			if ( admin == null )
				throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");


			final ProcedimientoRemoto procedimiento = RemotoUtils.recogerProcedimiento( session, idExterno, admin.getId() );

			if ( procedimiento != null ) {

				admin.removeProcedimientoRemoto(procedimiento);
				procedimiento.getNormativas().clear();
				procedimiento.getUnidadAdministrativa().removeProcedimientoLocal(procedimiento);
				Historico historico = getHistorico(session, procedimiento);
				( (HistoricoProcedimiento) historico ).setProcedimiento(null);

				for ( final Materia mat : procedimiento.getMaterias() )
					mat.getProcedimientosLocales().remove(procedimiento);

				for (final HechoVitalProcedimiento hecho : procedimiento.getHechosVitalesProcedimientos() )
					session.delete(hecho);

				session.delete(procedimiento);
				session.flush();

			}

		} catch (final HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}
	
	
	/**
	 * Obtiene la iniciacion de un procedimiento dado su código estándar
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 */
	public Iniciacion obtenerIniciacion(final String codEstandarInciacion) {
		
		final Session session = getSession();
		try {	
			
			return RemotoUtils.recogerIniciacionCE(session, codEstandarInciacion);
			
		} catch (final HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}


	//Para no hacer un facade de documento con dos metodos, se han decidido poner aqui.
	/**
	 * Obtiene un Documento Remoto apartir de su id externo y su id de la UARemota
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 */
	public DocumentoRemoto obtenerDocumentoRemoto(Long idExterno, Long idAdmin) {
		
		Session session = getSession();
		try {

			StringBuilder consulta = new StringBuilder(" from DocumentoRemoto as dr where dr.idExterno = :idExterno ");
			consulta.append(" and dr.administracionRemota.id = :idAdmin");
			
			Query query = session.createQuery( consulta.toString() );
			query.setParameter("idExterno", idExterno);
			query.setParameter("idAdmin", idAdmin);
			
			DocumentoRemoto documento = (DocumentoRemoto) query.uniqueResult();
			if ( documento != null )
				Hibernate.initialize( documento.getProcedimiento() );

			return documento;
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}

	
	/**
	 * Obtiene un Documento Remoto segun su idExterno y el IdRemoto actualmente
	 * de la AdministracionRemota
	 *
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 * 
	 * @param 	idRemoto	Identificador remoto de un documento.
	 * 
	 * @param	idExtDoc	Identificador remoto de la administración.
	 */
	public DocumentoRemoto obtenerDocumentoRemoto(final String idRemoto, final Long idExtDoc) {
		
		final Session session = getSession();
		try {
			
			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);

			if ( admin == null )
				throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");

			return RemotoUtils.recogerDocumento( session, idExtDoc, admin.getId() );
			
		} catch (final HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}


	/**
	 * Crea o actualiza un Documento Remoto
	 * 
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 */
	public Long grabarDocumentoRemoto(DocumentoRemoto documento, Long idProcedimiento, Long idFicha) {
		
		Session session = getSession();
		try {
			
			FichaRemota ficha = null;
			ProcedimientoRemoto procedimiento = null;
			if ( documento.getId() == null ) {
				
				if ( idFicha != null ) {
					
					ficha = (FichaRemota) session.load(FichaRemota.class, idFicha);
					ficha.addDocumento(documento);
					
				}
				
				if ( idProcedimiento != null ) {
					
					procedimiento = (ProcedimientoRemoto) session.load(ProcedimientoRemoto.class, idProcedimiento);
					procedimiento.addDocumento(documento);
					
				}
				
				session.save(documento);
				
			} else {
				
				session.update(documento);
				
			}
			
			session.flush();
			
			return documento.getId();
			
		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}


	/**
	 * Borra un documento Remoto segun su Identificador externo
	 *
	 * @ejb.interface-method
	 * 
	 * @ejb.permission unchecked="true"
	 */
	public void borrarDocumentoRemoto(final String idRemoto ,final Long idExterno) {

		final Session session = getSession();
		try {
			
			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);
			if ( admin == null )
				throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");

			StringBuilder consulta = new StringBuilder(" from DocumentoRemoto as dr where dr.idExterno = :idExterno ");
			consulta.append(" and dr.administracionRemota.id = :idAdmin");
			
			Query query = session.createQuery("from DocumentoRemoto as dr where dr.idExterno="+idExterno+" and dr.administracionRemota.id="+ admin.getId());
			query.setParameter("idExterno", idExterno);
			query.setParameter("idAdmin", admin.getId());
			
			DocumentoRemoto documentoRemoto = (DocumentoRemoto) query.uniqueResult();

			if ( documentoRemoto != null ) {
				
				Hibernate.initialize( documentoRemoto.getProcedimiento() );
				ProcedimientoRemoto procRemoto = (ProcedimientoRemoto) session.load( ProcedimientoRemoto.class, documentoRemoto.getProcedimiento().getId() );
				procRemoto.removeDocumento(documentoRemoto);
				admin.removeDocumentoRemoto(documentoRemoto);

				session.delete(documentoRemoto);
				session.flush();
				
			}
			
		} catch (final HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}

}
