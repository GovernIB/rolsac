package org.ibit.rol.sac.persistence.delegate;

import java.util.List;

import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteJob;

import es.caib.rolsac.utils.ResultadoBusqueda;


public interface SolrPendienteDelegateI {

	
	public List<SolrPendiente> getPendientes()
			throws DelegateException;
		
	public Boolean indexarPendientes()
			throws DelegateException;
	
	public Boolean borrarCaducadas()
			throws DelegateException;
	
	
	public Boolean grabarSolrPendiente(String tipo, Long idElemento, Long accion)
			 throws DelegateException;

	public ResultadoBusqueda getPendientes(int pagina, int resultados) throws DelegateException;

	public SolrPendienteJob crearSorlPendienteJob() throws DelegateException ;
	    
	public void cerrarSorlPendienteJob(SolrPendienteJob solrpendienteJob) throws DelegateException;
	    
	public void crearJob(String tipoIndexacion)  throws DelegateException;

	public void indexarTodoFicha(SolrPendienteJob solrPendienteJob) throws DelegateException;

	public void indexarTodoProcedimiento(SolrPendienteJob solrPendienteJob) throws DelegateException;

	public void indexarTodoNormativa(SolrPendienteJob solrPendienteJob) throws DelegateException;

	public void indexarTodoTramite(SolrPendienteJob solrPendienteJob) throws DelegateException;

	public void indexarTodoUA(SolrPendienteJob solrPendienteJob) throws DelegateException;

	public List<SolrPendienteJob> getListJobs(int cuantos) throws DelegateException;

	
		
}