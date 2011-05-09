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
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public Long grabarProcedimientoRemoto(final String idRemoto ,final ProcedimientoRemoto procRemoto,
			final String[] ceMaterias, final String[] ceHechos) {
		final Session session = getSession();
		try {
			AdministracionRemota adminRemota = RemotoUtils.recogerAdministracionRemota(session, idRemoto);
			
			if(adminRemota==null)
	    		throw new EJBException("No existe ninguna Administracion Remota asociada");
			
			procRemoto.setAdministracionRemota(adminRemota);
			
			return grabarProcedimientoRemoto(session, procRemoto, ceMaterias, ceHechos);
		}catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	/**
	 * Crea o actualiza un ProcedimientoRemoto
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public Long grabarProcedimientoRemoto(final ProcedimientoRemoto procRemoto,
			final String[] ceMaterias, final String[] ceHechos) {
		final Session session = getSession();
		try {
			AdministracionRemota adminRemota=null;
			
			if (procRemoto.getAdministracionRemota() != null){
				adminRemota = (AdministracionRemota) session.load(
						AdministracionRemota.class, procRemoto
								.getAdministracionRemota().getId());
			}
			
			if(adminRemota==null)
	    		throw new EJBException("No existe ninguna Administracion Remota asociada");
			
			procRemoto.setAdministracionRemota(adminRemota);
			
			return grabarProcedimientoRemoto(session, procRemoto, ceMaterias, ceHechos);
			
		}catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Crea o actualiza un ProcedimientoRemoto
	 * @throws HibernateException 
	 */
	@SuppressWarnings("unchecked")
	private Long grabarProcedimientoRemoto(final Session session, final ProcedimientoRemoto procRemoto,
		final String[] ceMaterias, final String[] ceHechos) throws HibernateException {
		
		final Set<Materia> materias = RemotoUtils.recogerMateriasCE(
				session, ceMaterias);
		if (materias != null) {
			procRemoto.setMaterias(materias);
		} else {
			procRemoto.setMaterias(Collections.EMPTY_SET);
		}
		
		AdministracionRemota adminRemota= procRemoto.getAdministracionRemota();

		if (procRemoto.getId() != null) {
			session.update(procRemoto);
		} else {
			session.save(procRemoto);
			adminRemota.addProcedimientoRemoto(procRemoto);
			procRemoto.setAdministracionRemota(adminRemota); 
		}

		final Set<HechoVital> hechos = RemotoUtils.recogerHechosCE(session,
				ceHechos);
		if (hechos != null && !hechos.isEmpty()) {

			if (procRemoto.getHechosVitalesProcedimientos() == null) {
				procRemoto.setHechosVitalesProcedimientos(new HashSet<HechoVitalProcedimiento>());
			} else {
				for (final HechoVitalProcedimiento heProc : procRemoto
						.getHechosVitalesProcedimientos()) {
					boolean relacionado = false;
					for (final HechoVital hecho : hechos) {
						if (relacionado = hecho.getId().equals(
								heProc.getHechoVital().getId())) {
							hechos.remove(hecho);
							break;
						}
					}
					if (!relacionado) {
						heProc.getHechoVital()
								.removeHechoVitalProcedimiento(heProc);
						procRemoto.removeHechoVitalProcedimiento(heProc);
						session.delete(heProc);
					}
				}
			}

			for (final HechoVital hecho : hechos) {
				final HechoVitalProcedimiento hvProc = new HechoVitalProcedimiento();
				hvProc.setHechoVital(hecho);
				hvProc.setProcedimiento(procRemoto);
				procRemoto.addHechoVitalProcedimiento(hvProc);
				hecho.addHechoVitalProcedimiento(hvProc);
				session.save(hvProc);
			}
		} else {
			procRemoto.setHechosVitalesProcedimientos(Collections.EMPTY_SET);
		}

		session.flush();
		return procRemoto.getId();
	}
	

	private ModelFilterObject obtenerFilterObject(ProcedimientoRemoto proc) throws DelegateException {
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
   		Hashtable lista_materias = new Hashtable(), lista_hechos = new Hashtable();
   		UnidadAdministrativa ua= proc.getUnidadAdministrativa();
		List listapadres= org.ibit.rol.sac.persistence.delegate.DelegateUtil.getUADelegate().listarPadresUnidadAdministrativa(ua.getId());
   		
   		if (proc.getMaterias()!=null) {
   			Iterator itmat=proc.getMaterias().iterator();
   			while (itmat.hasNext()) {
   				mat=(Materia)itmat.next();
   				if (!lista_materias.containsKey(mat.getId()))
       					lista_materias.put(mat.getId(), mat);
   			}
   		}

   		if (proc.getHechosVitalesProcedimientos()!=null) {
   			Iterator itvital=proc.getHechosVitalesProcedimientos().iterator();
   			while (itvital.hasNext()) {
   				hvital=(HechoVitalProcedimiento)itvital.next();
   				if (!lista_hechos.containsKey(hvital.getHechoVital().getId()))
   					lista_hechos.put(hvital.getHechoVital().getId(), hvital.getHechoVital());
   			}
   		}
   		
   		Iterator langs= proc.getLangs().iterator();
   		while (langs.hasNext()) {
   			idioma = (String) langs.next();
			txids=Catalogo.KEY_SEPARADOR;
    		txtexto=" ";
	    		
			trafilter = new TraModelFilterObject();
			trafilter.setMaintitle(null); 
				
			// Obtenemos la UA con sus padres excepto el raiz
			if (ua!=null) {
				txids=Catalogo.KEY_SEPARADOR;
				txtexto=" ";

				UnidadAdministrativa ua_padre=null;
				for (int x = 1; x < listapadres.size(); x++) {
					ua_padre=(UnidadAdministrativa)listapadres.get(x);
					txids+=ua_padre.getId()+Catalogo.KEY_SEPARADOR;
					if (ua_padre.getTraduccion(idioma)!=null)
						txtexto+=((TraduccionUA)ua_padre.getTraduccion(idioma)).getNombre()+" ";
				}

				filter.setUo_id( (txids.length()==1) ? null: txids);
				trafilter.setUo_text( (txtexto.length()==1) ? null: txtexto);
			}
			
			// Obtenemos su Familia
			Familia fam= proc.getFamilia();
			if (fam!=null) {
				filter.setFamilia_id(fam.getId());
				if (fam.getTraduccion(idioma)!=null)	
					trafilter.setFamilia_text(((TraduccionFamilia)fam.getTraduccion(idioma)).getNombre());
			}
			
			// Obtenemos las materias y hechos vitales
	    	txids=Catalogo.KEY_SEPARADOR;
	    	txtexto=" ";
	    		
	    	Enumeration i=lista_materias.keys();
	    		
	    	while (i.hasMoreElements()) {
	    		Materia materia = (Materia)lista_materias.get(i.nextElement());
		       	txids+=materia.getId()+Catalogo.KEY_SEPARADOR; //anadir los ids (los de los hechos vitales no)
		       	if (materia.getTraduccion(idioma)!=null) {
		       		txtexto+=((TraduccionMateria)materia.getTraduccion(idioma)).getNombre() + " ";
		       		txtexto+=((TraduccionMateria)materia.getTraduccion(idioma)).getDescripcion() + " ";
		       		txtexto+=((TraduccionMateria)materia.getTraduccion(idioma)).getPalabrasclave() + " ";
		       	}
	    	}

	    	i=lista_hechos.keys();
	    	HechoVital hechovital=null;
	    	
	    	while (i.hasMoreElements()) {
	    		hechovital = (HechoVital)lista_hechos.get(i.nextElement());
		       	if (hechovital.getTraduccion(idioma)!=null) {
		       		txtexto+=((TraduccionHechoVital)hechovital.getTraduccion(idioma)).getNombre() + " ";
		       		txtexto+=((TraduccionHechoVital)hechovital.getTraduccion(idioma)).getDescripcion() + " ";
		       		txtexto+=((TraduccionHechoVital)hechovital.getTraduccion(idioma)).getPalabrasclave() + " ";
		       	}
	    	}
	    		
	    	filter.setMateria_id( (txids.length()==1) ? null: txids);
	    	trafilter.setMateria_text( (txtexto.length()==1) ? null: txtexto);
	    		
    		filter.addTraduccion(idioma, trafilter);
 
		}
   		
		return filter;
	}

	/**
     * Añade los procedimientos al indice en todos los idiomas
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public void indexInsertaProcedimientoRemoto(ProcedimientoRemoto proc, ModelFilterObject filter)  {
    	
    	
    	try {
    		if (proc.getValidacion().equals(2)) return;

	    	if (filter==null) filter = obtenerFilterObject(proc);  	
	    	
	    	String tipo = tipoProcedimiento (proc,false);
	    	String tipoDoc = tipoProcedimiento (proc,true); 	    	
			for (Iterator iterator = proc.getLangs().iterator(); iterator.hasNext();) {
				String idi = (String) iterator.next();
				IndexObject io= new IndexObject();
				
				io.setId(tipo + "." + proc.getId());
				io.setClasificacion(tipo);
	            
	            io.setMicro( filter.getMicrosite_id() );
	            io.setUo( filter.getUo_id() );
				io.setMateria( filter.getMateria_id() );
				io.setFamilia( filter.getFamilia_id() );
				io.setSeccion( filter.getSeccion_id() );
				
				io.setCaducidad("");	
				io.setPublicacion("");
				io.setDescripcion(""); 
				if (proc.getFechaCaducidad()!=null)		io.setCaducidad(new java.text.SimpleDateFormat("yyyyMMdd").format(proc.getFechaCaducidad()));
				if (proc.getFechaPublicacion()!=null)	io.setPublicacion(new java.text.SimpleDateFormat("yyyyMMdd").format(proc.getFechaPublicacion()));
				

	            TraduccionProcedimientoLocal trad=((TraduccionProcedimientoLocal)proc.getTraduccion(idi));
	            if (trad!=null) {

	            	io.setTituloserviciomain(trad.getNombre());

            		io.setUrl("/govern/sac/visor_proc.do?codi="+proc.getId()+"&lang=" + idi + "&coduo=" + proc.getUnidadAdministrativa().getId());
	            	// Si es externo ponemos su propia URL
            		if (tipo.equals(Catalogo.SRVC_PROCEDIMIENTOS_EXTERNO))	io.setUrl(proc.getUrl());
	            	if (trad.getNombre()!=null) {
	            		io.setTitulo(trad.getNombre());
	            		io.addTextLine(trad.getNombre());
	            		if (trad.getResumen()!=null) {
	            			if (trad.getResumen().length()>200) io.setDescripcion(trad.getResumen().substring(0,199)+"...");
	            			else io.setDescripcion(trad.getResumen());
	            		}
	            	}
	            	if (trad.getDestinatarios()!=null) 	io.addTextLine(trad.getDestinatarios());
	            	if (trad.getLugar()!=null)			io.addTextLine(trad.getLugar());
	            	if (trad.getObservaciones()!=null)	io.addTextLine(trad.getObservaciones());
	            	if (trad.getPlazos()!=null)			io.addTextLine(trad.getPlazos());
	            	if (trad.getResolucion()!=null)		io.addTextLine(trad.getResolucion());
	            	if (trad.getNotificacion()!=null)	io.addTextLine(trad.getNotificacion());
	            	if (trad.getRecursos()!=null)		io.addTextLine(trad.getRecursos()); // No está en el mantenimiento
	            	if (trad.getRequisitos()!=null)		io.addTextLine(trad.getRequisitos());
	            	if (trad.getSilencio()!=null)		io.addTextLine(trad.getSilencio());
					
	            }
				io.addTextopcionalLine(filter.getTraduccion(idi).getMateria_text());
				io.addTextopcionalLine(filter.getTraduccion(idi).getSeccion_text());
				io.addTextopcionalLine(filter.getTraduccion(idi).getUo_text());
				io.addTextopcionalLine(filter.getTraduccion(idi).getFamilia_text());
				if (proc.getMaterias()!=null) {
					Iterator iter3 = proc.getMaterias().iterator();
					while (iter3.hasNext()) {
						Materia mat = (Materia)iter3.next();
						if (mat.getTraduccion(idi)!=null) {
							io.addTextopcionalLine(((TraduccionMateria)mat.getTraduccion(idi)).getNombre());
						}
					}
				}

	            if (io.getText().length()>0)
	            	org.ibit.rol.sac.persistence.delegate.DelegateUtil.getIndexerDelegate().insertaObjeto(io, idi);
			}
			//log.warn("[indexInsertaProcedimiento:" + proc.getId() + "] INDEXADO");
		
		}
					
		catch (Exception ex) {
			log.warn("[indexInsertaProcedimiento:" + proc.getId() + "] No se ha podido indexar el procedimiento. " + ex.getMessage());
		}
        
	}
	
    
	private String tipoProcedimiento (ProcedimientoLocal proc, boolean doc) {

		String tipo="";
		
		if (!doc) {
			if (proc.getUrl()!=null && proc.getUrl().length()>0)			tipo=Catalogo.SRVC_PROCEDIMIENTOS_EXTERNO;
			else if ((proc.getVersion()==null && proc.getTramite()==null) || (proc.getVersion()==null && proc.getTramite()!=null && proc.getTramite().length()==0 )) 	tipo=Catalogo.SRVC_PROCEDIMIENTOS_NOTELEMATICO;
			else															tipo=Catalogo.SRVC_PROCEDIMIENTOS_SISTRA;
		} else {
			if (proc.getUrl()!=null && proc.getUrl().length()>0)			tipo=Catalogo.SRVC_PROCEDIMIENTOS_EXTERNO_DOCUMENTOS;
			else if (proc.getVersion()==null && proc.getTramite()==null) 	tipo=Catalogo.SRVC_PROCEDIMIENTOS_NOTELEMATICO_DOCUMENTOS;
			else{}															tipo=Catalogo.SRVC_PROCEDIMIENTOS_SISTRA_DOCUMENTOS;			
		}
    	return tipo;
	}
	
	/**
	 * Obtiene un Procedimiento Remoto segun su idExterno y el uausrio
	 * actualmente logueado
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public ProcedimientoRemoto obtenerProcedimientoRemoto(
			final String idRemoto ,final Long idExt) {
		final Session session = getSession();
		try {
			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);
        	
        	if(admin==null)
        		throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");
        				
			return RemotoUtils.recogerProcedimiento(session, idExt, admin.getId());
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
	 * @ejb.permission unchecked="true"
	 */
	public ProcedimientoRemoto obtenerProcedimientoRemoto(
			final Long idExt, final Long idAdmin) {
		final Session session = getSession();
		try {
			return RemotoUtils.recogerProcedimiento(session, idExt, idAdmin);
		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

	/**
	 * Lista los procedimientos remotos
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	@SuppressWarnings("unchecked")
	public Set<ProcedimientoRemoto> listarProcedimientosRemotos(final String idRemoto) {
		final Session session = getSession();
		try {
			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);
        	
        	if(admin==null)
        		throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");
        	
			return admin.getProcedimientosRemotos();
		} catch (final HibernateException e) {
			throw new EJBException(e);
		} finally {
			close(session);
		}
	}

	/**
	 * Borra un Procedimiento Remoto segun su idExterno
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public void borrarProcedimientoRemoto(final String idRemoto ,final Long idExt) {
		final Session session = getSession();
		try {
			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);
        	
        	if(admin==null)
        		throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");


			final ProcedimientoRemoto procedimiento = RemotoUtils.recogerProcedimiento(session, idExt, admin.getId());
			
			if(procedimiento!=null){
				admin.removeProcedimientoRemoto(procedimiento);
	
				procedimiento.getNormativas().clear();
				procedimiento.getUnidadAdministrativa().removeProcedimientoLocal(procedimiento);
	            Historico historico = getHistorico(session, procedimiento);
	            ((HistoricoProcedimiento) historico).setProcedimiento(null);
				
				for (final Materia mat : procedimiento.getMaterias()) {
					mat.getProcedimientosLocales().remove(procedimiento);
				}
				for (final HechoVitalProcedimiento hecho : procedimiento
						.getHechosVitalesProcedimientos()) {
					session.delete(hecho);
				}
	
				session.delete(procedimiento);
				session.flush();
			}
		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}

    //TODO: encara no està provat
    /**
	 * lista los procedimientos remotos asociados a una unidad administrativa y a una materia
     * que son publicos y no estan caducados
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
     public List<ProcedimientoRemoto> listarProcedimientosRemotosUAMateria(Long idUA, String codEstMateria){
        Session session = getSession();
        try{
            Query query = session.createQuery(" from ProcedimientoRemoto as pr where pr.unidadAdministrativa.id = :idUA" +
                                              " and :codEstMateria in (select mat.codigoEstandar from pr.materias as mat)" +
                                              " and pr.validacion = :validacion" +
                                              " and ( pr.fechaCaducidad is null or pr.fechaCaducidad >= :fecha)");
            query.setParameter("idUA", idUA);
            query.setParameter("codEstMateria", codEstMateria);
            query.setParameter("validacion", Validacion.PUBLICA);
            query.setParameter("fecha", DateUtils.today());
            query.setCacheable(true);

            return query.list();
        }catch(HibernateException he){
           throw new EJBException(he);
        }finally{
            close(session);
        }
     }

     //TODO: encara no està provat
     /**
	 * lista los procedimientos remotos asociados a una unidad administrativa y a un hecho vital
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
     public List<ProcedimientoRemoto> listarProcedimientosRemotosUAHechoVital(Long idUA, String codEstHV){
        Session session = getSession();
        try{
            Query query = session.createQuery(" from ProcedimientoRemoto as pr where pr.unidadAdministrativa.id = :idUA " +
                                              " and :codEstHV in (select hvp.hechoVital.codigoEstandar from pr.hechosVitalesProcedimientos as hvp) " +
                                              " and pr.validacion = :validacion" +
                                              " and ( pr.fechaCaducidad is null or pr.fechaCaducidad >= :fecha)");
            query.setParameter("idUA", idUA);
            query.setParameter("codEstHV", codEstHV);
            query.setParameter("validacion", Validacion.PUBLICA);
            query.setParameter("fecha", DateUtils.today());
            query.setCacheable(true);

            return query.list();
        }catch(HibernateException he){
           throw new EJBException(he);
        }finally{
            close(session);
        }
     }


    /**
     * Busca todos los {@link ProcedimientoRemoto} cuyo nombre contenga el String de entrada
     *
     * @param busqueda
     * @param idioma
     * @return lista de {@link ProcedimientoRemoto}
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	@SuppressWarnings("unchecked")
	public List<ProcedimientoRemoto> buscar(final String busqueda, final String idioma){
		List<ProcedimientoRemoto> resultado;
		if (busqueda != null && !"".equals(busqueda.trim())){
			Session session = getSession();
	        try {
	        	Query query = session.createQuery("from ProcedimientoRemoto as prc, prc.traducciones as trad where index(trad) = :idioma and upper(trad.nombre) like :busqueda");
	        	query.setString("idioma", idioma);
	        	query.setString("busqueda", "%"+busqueda.trim().toUpperCase()+"%");
	        	resultado = (List<ProcedimientoRemoto>)query.list();
	        } catch (HibernateException he) {
	            throw new EJBException(he);
	        } finally {
	            close(session);
	        }
		} else {
			resultado = Collections.emptyList();
		}

		return resultado;
	}

	  /**
     * Lista de procedimientos remotos mas recientes de un conjunto de materias
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     * TODO: comprovar caducitat?
     */
    public List<ProcedimientoRemoto> listarProcedimientosRecientesMaterias(int length, String[] codigos, boolean include,boolean caducados) {
        Session session = getSession();
        try {
            Query query = session.createQuery(
                    "select p from ProcedimientoRemoto as p " +
                    "where p.fechaActualizacion is not null and p.validacion = :validacion " +
                    (!caducados ? "AND ( p.fechaCaducidad is null or p.fechaCaducidad >= :fecha ) " : "") +
                    " and " + (include?"exists":"not exists") + " (select m from p.materias m where m.codigoEstandar in (:codigos)) " +
                    "order by p.fechaActualizacion desc");
            query.setParameter("validacion", Validacion.PUBLICA);
            query.setParameterList("codigos", codigos);
            if(!caducados){query.setParameter("fecha", DateUtils.today());}
            query.setMaxResults(length);
            query.setCacheable(true);
            return query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Lista de procedimientos remotos mas comentados
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     * TODO: comprovar caducitat?
     */
    public List<ProcedimientoRemoto> listarProcedimientosMasComentadosMaterias(int length, String[] codigos, boolean include,boolean caducados) {
        Session session = getSession();
        try {
            Query query = session.createQuery(
                    "select p from ProcedimientoRemoto p, ComentarioProcedimiento cp " +
                    "where cp.procedimiento = p and p.validacion = :validacion " +
                    (!caducados ? "AND ( p.fechaCaducidad is null or p.fechaCaducidad >= :fecha ) " : "") +
                    " and " + (include?"exists":"not exists") + " (select m from p.materias m where m.codigoEstandar in (:codigos)) " +
                    // "group by" ha de posar explicitament tots els camps del select, en aquest cas de procediment. Veure:
                    // http://opensource.atlassian.com/projects/hibernate/browse/HB-1003
                    "group by p.id, p.idExterno, p.urlRemota, p.administracionRemota, p.signatura, " +
                    " p.fechaCaducidad, p.fechaPublicacion, p.fechaActualizacion, p.validacion, p.tramite, " +
                    " p.version, p.info, p.unidadAdministrativa, p.familia , p.url, p.orden, p.orden2, p.orden3,p.iniciacion, p.indicador, p.ventanillaUnica, p.info, p.responsable, p.taxa, p.organResolutori" +
                    " order by count(cp) desc");
            query.setParameter("validacion", Validacion.PUBLICA);
            query.setParameterList("codigos", codigos);
            if(!caducados){query.setParameter("fecha", DateUtils.today());}
            query.setMaxResults(length);
            query.setCacheable(true);
            return query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Lista de procedimientos remotos mas visitados
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     * TODO: comprovar caducitat?
     */
    public List<ProcedimientoRemoto> listarProcedimientosMasVisitadosMaterias(int length, String[] codigos, boolean include,boolean caducados) {
        Session session = getSession();
        try {
            Query query = session.createQuery(
                    "select p from ProcedimientoRemoto p, HistoricoProcedimiento hp, Estadistica e " +
                    "where hp.procedimiento = p and e.historico = hp and p.validacion = :validacion " +
                    (!caducados ? "AND ( p.fechaCaducidad is null or p.fechaCaducidad >= :fecha ) " : "") +
                    " and " + (include?"exists":"not exists") + " (select m from p.materias m where m.codigoEstandar in (:codigos)) " +
                    // "group by" ha de posar explicitament tots els camps, veure:
                    // http://opensource.atlassian.com/projects/hibernate/browse/HB-1003
                    "group by p.id, p.idExterno, p.urlRemota, p.administracionRemota, p.signatura, " +
                    " p.fechaCaducidad, p.fechaPublicacion, p.fechaActualizacion, p.validacion, p.tramite, " +
                    " p.version, p.info, p.unidadAdministrativa, p.familia , p.url, p.orden, p.orden2, p.orden3,p.iniciacion, p.indicador, p.ventanillaUnica, p.info, p.responsable, p.taxa, p.organResolutori" +
                    " order by sum(e.contador) desc");
            query.setParameter("validacion", Validacion.PUBLICA);
            query.setParameterList("codigos", codigos);
            query.setMaxResults(length);
            if(!caducados){query.setParameter("fecha", DateUtils.today());}
            query.setCacheable(true);
            return query.list();
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
	/**
	 * Obtiene la iniciacion de un procedimiento dado su CE
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public Iniciacion obtenerIniciacion(final String ceIniciacion) {
		final Session session = getSession();
		try {	
			return RemotoUtils.recogerIniciacionCE(session, ceIniciacion);
		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}


    //Para no hacer un facade de documento con dos metodos, se han decidido poner aqui.


    /**
     * Obtiene un Documento Remoto apartir de su id externo y su id de la UARemota
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public DocumentoRemoto obtenerDocumentoRemoto(Long idExterno,Long idAdmin) {
        Session session = getSession();
        try {

			Query query = session.createQuery("from DocumentoRemoto as dr where dr.idExterno="+idExterno+" and dr.administracionRemota.id="+idAdmin);
			DocumentoRemoto documento = (DocumentoRemoto)query.uniqueResult();

			if (documento != null) {
				Hibernate.initialize(documento.getProcedimiento());
			}

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
	 * @ejb.permission unchecked="true"
	 */
	public DocumentoRemoto obtenerDocumentoRemoto(final String idRemoto, final Long idExtDoc) {
		final Session session = getSession();
		try {
			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);

			if(admin==null)
        		throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");

			return RemotoUtils.recogerDocumento(session, idExtDoc, admin.getId());
		} catch (final HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}


     /**
     * Crea o actualiza un Documento Remoto
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public Long grabarDocumentoRemoto(DocumentoRemoto documento, Long procedimiento_id, Long ficha_id) {
        Session session = getSession();
        try {
        	FichaRemota ficha = null;
        	ProcedimientoRemoto procedimiento = null;
            if (documento.getId() == null) {
                if (ficha_id != null) {
                    ficha = (FichaRemota) session.load(FichaRemota.class, ficha_id);
                    ficha.addDocumento(documento);
                }
                if (procedimiento_id != null) {
                    procedimiento = (ProcedimientoRemoto) session.load(ProcedimientoRemoto.class, procedimiento_id);
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
	 * Borra un documento Remoto segun su idExterno
	 *
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public void borrarDocumentoRemoto(final String idRemoto ,final Long idExt) {

		final Session session = getSession();
		try {
			AdministracionRemota admin = RemotoUtils.recogerAdministracionRemota(session, idRemoto);
        	if(admin==null)
        		throw new EJBException("No existe ninguna Administracion Remota asociada al idRemoto");

			Query query = session.createQuery("from DocumentoRemoto as dr where dr.idExterno="+idExt+" and dr.administracionRemota.id="+ admin.getId());
			DocumentoRemoto documentoRemoto = (DocumentoRemoto)query.uniqueResult();

			if(documentoRemoto!=null){
				Hibernate.initialize(documentoRemoto.getProcedimiento());
				ProcedimientoRemoto procRemoto = (ProcedimientoRemoto) session.load(ProcedimientoRemoto.class, documentoRemoto.getProcedimiento().getId());
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
