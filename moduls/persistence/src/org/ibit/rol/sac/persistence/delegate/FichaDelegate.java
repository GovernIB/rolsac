package org.ibit.rol.sac.persistence.delegate;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Enlace;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteResultado;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;

import es.caib.rolsac.utils.ResultadoBusqueda;
import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.model.types.EnumCategoria;
import net.sf.hibernate.Session;

/**
 * Business delegate para manipular fichas. ejaen@dgtic - u92770 Classe
 * desacoplada del standard EJB2.0 per permetre testing. S'ha definit una classe
 * enlloc d'una interficie per evitar incompabilitats binaria amb les altres
 * aplicacions corporatives que esperen una classe (produeix un
 * IncompatibleClassChangeError).
 *
 */
public class FichaDelegate implements FichaDelegateI {

	FichaDelegateI impl;

	public FichaDelegateI getImpl() {
		return impl;
	}

	public void setImpl(final FichaDelegateI impl) {
		this.impl = impl;
	}

	@Override
	public boolean autorizaCrearFicha(final Integer validacionNormativa) throws DelegateException {
		return impl.autorizaCrearFicha(validacionNormativa);
	}

	@Override
	public boolean autorizaModificarFicha(final Long idFicha) throws DelegateException {
		return impl.autorizaModificarFicha(idFicha);
	}

	@Override
	public Long grabarFicha(final Ficha ficha) throws DelegateException {
		return impl.grabarFicha(ficha);
	}

	@Override
	public ResultadoBusqueda buscarFichas(final Map parametros, final Map traduccion, final UnidadAdministrativa ua,
			final Long idFetVital, final Long idMateria, final Long idPublic, final boolean uaFilles,
			final boolean uaMeves, final String campoOrdenacion, final String orden, final String pagina,
			final String resultats, final int campoVisible) throws DelegateException {
		return impl.buscarFichas(parametros, traduccion, ua, idFetVital, idMateria, idPublic, uaFilles, uaMeves,
				campoOrdenacion, orden, pagina, resultats, campoVisible);
	}

	@Override
	public Ficha obtenerFicha(final Long id) throws DelegateException {
		return impl.obtenerFicha(id);
	}

	@Override
	public Archivo obtenerIconoFicha(final Long id, final String idioma) throws DelegateException {
		return impl.obtenerIconoFicha(id, idioma);
	}

	@Override
	public Archivo obtenerImagenFicha(final Long id, final String idioma) throws DelegateException {
		return impl.obtenerImagenFicha(id, idioma);
	}

	@Override
	public Archivo obtenerBanerFicha(final Long id, final String idioma) throws DelegateException {
		return impl.obtenerBanerFicha(id, idioma);
	}

	@Override
	public void borrarFicha(final Long id) throws DelegateException {
		impl.borrarFicha(id);
	}

	@Override
	public List<Ficha> listarFichasSeccionUA(final Long ua_id, final String codEstSecc, final String[] codEstHV,
			final String[] codEstMat) throws DelegateException {
		return impl.listarFichasSeccionUA(ua_id, codEstSecc, codEstHV, codEstMat);
	}

	@Override
	public List listarFichasSeccionTodas(final Long id) throws DelegateException {
		return impl.listarFichasSeccionTodas(id);
	}

	@Override
	public Long crearFichaUA2(final Long unidad_id, final Long seccion_id, final Long ficha_id)
			throws DelegateException {
		return impl.crearFichaUA2(unidad_id, seccion_id, ficha_id);
	}

	@Override
	public void borrarFichaUA(final Long id) throws DelegateException {
		impl.borrarFichaUA(id);
	}

	@Override
	public Hashtable getContenidos_web() throws DelegateException {
		return impl.getContenidos_web();
	}

	@Override
	public void setContenidos_web(final Hashtable contenidos_web) throws DelegateException {
		impl.setContenidos_web(contenidos_web);
	}

	@Override
	public List<FichaUA> listFichasUA(final Long idFicha) throws DelegateException {
		return impl.listFichasUA(idFicha);
	}

	@Override
	public Ficha obtenerFichaPMA(final Long id) throws DelegateException {
		return impl.obtenerFichaPMA(id);
	}

	@Override
	public int buscarFichasActivas(final List<Long> listaUnidadAdministrativaId, final Date fechaCaducidad)
			throws DelegateException {
		return impl.buscarFichasActivas(listaUnidadAdministrativaId, fechaCaducidad);
	}

	@Override
	public int buscarFichasCaducadas(final List<Long> listaUnidadAdministrativaId, final Date fechaCaducidad)
			throws DelegateException {
		return impl.buscarFichasCaducadas(listaUnidadAdministrativaId, fechaCaducidad);
	}

	@Override
	public void borrarFichasUAdeFicha(final List<FichaUA> fichasUA) throws DelegateException {
		impl.borrarFichasUAdeFicha(fichasUA);
	}

	@Override
	public Ficha obtenerFichaDeFichaUA(final Long idFichaUA) throws DelegateException {
		return impl.obtenerFichaDeFichaUA(idFichaUA);
	}

	@Override
	public void actualizaEnlacesFicha(final Long id, final List<Enlace> enlacesNuevos,
			final List<Enlace> enlacesAEliminar) throws DelegateException {
		impl.actualizaEnlacesFicha(id, enlacesNuevos, enlacesAEliminar);
	}

	@Override
	public SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente)
			throws DelegateException {
		return impl.indexarSolr(solrIndexer, solrPendiente);
	}

	@Override
	public SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, final Long idElemento,
			final EnumCategoria categoria) throws DelegateException {
		return impl.indexarSolr(solrIndexer, idElemento, categoria);
	}

	@Override
	public Integer comprobarRelacionFicha(final Long idFitxa) throws DelegateException {
		return impl.comprobarRelacionFicha(idFitxa);
	}

	@Override
	public SolrPendienteResultado desindexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente)
			throws DelegateException {
		return impl.desindexarSolr(solrIndexer, solrPendiente);
	}

	@Override
	public List<Long> buscarIdsFichas() throws DelegateException {
		return impl.buscarIdsFichas();
	}

	@Override
	public Ficha obtenerFichaParaSolr(final Long id, final Session iSession) throws DelegateException {
		return impl.obtenerFichaParaSolr(id, iSession);
	}

	@Override
	public void reordenarDocumentos(final Long idFicha, final List<Long> idDocumentos) throws DelegateException {
		impl.reordenarDocumentos(idFicha, idDocumentos);
	}

	@Override
	public ResultadoBusqueda consultaFichas(final FiltroGenerico filtro) throws DelegateException {
		return impl.consultaFichas(filtro);
	}

	@Override
	public ResultadoBusqueda consultaFichasUA(final FiltroGenerico filtro) throws DelegateException {
		return impl.consultaFichasUA(filtro);
	}
}
