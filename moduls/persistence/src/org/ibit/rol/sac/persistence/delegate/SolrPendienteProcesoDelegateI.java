package org.ibit.rol.sac.persistence.delegate;

import java.util.List;

import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteJob;

import es.caib.rolsac.utils.ResultadoBusqueda;


public interface SolrPendienteProcesoDelegateI {

	

	public void indexarTodoFicha(SolrPendienteJob solrPendienteJob) throws DelegateException;

	public void indexarTodoProcedimiento(SolrPendienteJob solrPendienteJob) throws DelegateException;

	public void indexarTodoNormativa(SolrPendienteJob solrPendienteJob) throws DelegateException;

	public void indexarTodoTramite(SolrPendienteJob solrPendienteJob) throws DelegateException;

	public void indexarTodoUA(SolrPendienteJob solrPendienteJob) throws DelegateException;

	public Boolean indexarPendientes() throws DelegateException ;
	    
		
}
