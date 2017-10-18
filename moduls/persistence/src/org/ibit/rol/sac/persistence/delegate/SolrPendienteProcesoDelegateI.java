package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.model.SolrPendienteJob;


public interface SolrPendienteProcesoDelegateI {

	public void indexarTodoFicha(SolrPendienteJob solrPendienteJob) throws DelegateException;

	public void indexarTodoProcedimiento(SolrPendienteJob solrPendienteJob) throws DelegateException;

	public void indexarTodoServicio(SolrPendienteJob solrPendienteJob) throws DelegateException;

	public void indexarTodoNormativa(SolrPendienteJob solrPendienteJob) throws DelegateException;

	public void indexarTodoTramite(SolrPendienteJob solrPendienteJob) throws DelegateException;

	public void indexarTodoUA(SolrPendienteJob solrPendienteJob) throws DelegateException;

	public Boolean indexarPendientes() throws DelegateException ;
	    
		
}
