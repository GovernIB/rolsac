package es.caib.rolsac.back2.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.HistoricoFicha;
import org.ibit.rol.sac.model.HistoricoNormativa;
import org.ibit.rol.sac.model.HistoricoProcedimiento;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionFicha;
import org.ibit.rol.sac.model.TraduccionNormativa;
import org.ibit.rol.sac.model.TraduccionProcedimiento;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EstadisticaDelegate;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.NormativaDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.persistence.delegate.UsuarioDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.caib.rolsac.back2.util.Parametros;
import es.caib.rolsac.back2.util.RolUtil;

@Controller
@RequestMapping(value = "/quadreControl/")
public class QuadreControlController extends PantallaBaseController {

	private static Log log = LogFactory.getLog(QuadreControlController.class);	
	
	@RequestMapping(value = "/quadreControl.do")
	public String quadreControl(HttpSession session,
			HttpServletRequest request, Map<String, Object> model)
			throws ServletException, IOException {

		GregorianCalendar dataActual = new GregorianCalendar();
		
		//Afegim un dia mes per no haver de mirar les hores i minuts al between
		dataActual.add(Calendar.DATE, +1);
		UnidadAdministrativa unitatAdministrativa = new UnidadAdministrativa();

		// Comprobamos si tenemos que recorrer todos los nodos
		List<Long> llistaUnitatAdministrativaId = new ArrayList<Long>();
		
		try {
			
	        UsuarioDelegate usuariDelegate = DelegateUtil.getUsuarioDelegate();
	        Usuario usuari = usuariDelegate.obtenerUsuariobyUsername(request.getRemoteUser());
	        
	        RolUtil rolUtil = new RolUtil(request);
	        
			boolean usuariSuper = rolUtil.userIsSuper(); 
	        
			if (session.getAttribute("unidadAdministrativa") != null) {
				
				unitatAdministrativa = (UnidadAdministrativa) session.getAttribute("unidadAdministrativa");
				model.put("idUA", unitatAdministrativa.getId());
				model.put("nomUA",unitatAdministrativa.getNombreUnidadAdministrativa());
			
				String allNodos = request.getParameter("allUA");
				UnidadAdministrativaDelegate unitatAdministrativaDelegate = DelegateUtil.getUADelegate();
				
				if (allNodos != null && !"".equals(allNodos) && unitatAdministrativa != null && unitatAdministrativa.getId() != null) {
					
					if ( usuari.getUnidadesAdministrativas().contains(unitatAdministrativa)  || usuariSuper )
						llistaUnitatAdministrativaId.addAll( unitatAdministrativaDelegate.cargarArbolUnidadId(unitatAdministrativa.getId()) );
					
					model.put("allUA", "S");
					
				} else {
					
					// Sólo se mostrarán datos si el usuario tiene acceso a la UA o si es administrador
					if (usuari.getUnidadesAdministrativas().contains(unitatAdministrativa)  || usuariSuper ) { 
						llistaUnitatAdministrativaId.add(unitatAdministrativa.getId());
					} 
				}
							
			} else {
		        
		        UnidadAdministrativaDelegate unitatAdministrativaDelegate = DelegateUtil.getUADelegate();
		        
		     // Filtrar sólo por las UAs del usuario
	            for (UnidadAdministrativa unitat: (Set<UnidadAdministrativa>)usuari.getUnidadesAdministrativas()) 
	                llistaUnitatAdministrativaId.addAll(unitatAdministrativaDelegate.cargarArbolUnidadId(unitat.getId()));
	            
            }
			
			model.put("menu", 0);
			model.put("submenu", "layout/submenu/submenuOrganigrama.jsp");
			model.put("submenu_seleccionado", 0);
			model.put("titol_escriptori", messageSource.getMessage("submenu.quadre_control", null, request.getLocale()));
			model.put("escriptori", "pantalles/quadreControl.jsp");
				
			// Darreres Modificacions
			EstadisticaDelegate eDelegate = DelegateUtil.getEstadisticaDelegate();
			
			GregorianCalendar dataActualFi = new GregorianCalendar();
			dataActualFi.add(Calendar.DATE, -7);

			Map<Timestamp, Object> llistaCanvis = eDelegate.listarUltimasModificaciones(dataActualFi.getTime(), dataActual.getTime(), Parametros.NUMERO_REGISTROS, llistaUnitatAdministrativaId);
			
			String idioma = request.getLocale().getLanguage();
			
			if (idioma != null && !"".equals(idioma)) {
				
    			for ( Object element: llistaCanvis.values() ) {
    				
    			    if (element instanceof HistoricoFicha) {
    			        Ficha fitxa = ((HistoricoFicha) element).getFicha();
    			        if (fitxa != null) {
    			            ((HistoricoFicha) element).setNombre(((TraduccionFicha) fitxa.getTraduccion(idioma)).getTitulo());
    			        }
    			    } else if (element instanceof HistoricoProcedimiento) {
    			        ProcedimientoLocal procediment = ((HistoricoProcedimiento) element).getProcedimiento();
    			        if (procediment != null) {
    			            ((HistoricoProcedimiento) element).setNombre(((TraduccionProcedimiento) procediment.getTraduccion(idioma)).getNombre());
    			        }
    			    } else {
    			        Normativa normativa = ((HistoricoNormativa) element).getNormativa();
    			        if (normativa != null) {
    			            ((HistoricoNormativa) element).setNombre(((TraduccionNormativa) normativa.getTraduccion(idioma)).getTitulo());
    			        }
    			    }    			       			    
    			}
			}

			model.put("darreresModificacions", llistaCanvis);

			// Nombre de continguts

			// Procediment
			ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
			int procedimentActiu = procedimientoDelegate.buscarProcedimientosActivos(llistaUnitatAdministrativaId,dataActual.getTime());
			int procedimentCaducat = procedimientoDelegate.buscarProcedimientosCaducados(llistaUnitatAdministrativaId,dataActual.getTime());
			model.put("procedimentActiu", procedimentActiu);
			model.put("procedimentCaducat", procedimentCaducat);
			model.put("procedimentTotal", procedimentActiu + procedimentCaducat);

			// Normativa
			NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
			int normativaActiva = normativaDelegate.buscarNormativasActivas(llistaUnitatAdministrativaId);
			model.put("normativaActiva", normativaActiva);

			// Fitxa
			FichaDelegate fichaDelegate = DelegateUtil.getFichaDelegate();
			int fitxaActiva = fichaDelegate.buscarFichasActivas(llistaUnitatAdministrativaId, dataActual.getTime());
			int fitxaCaducada = fichaDelegate.buscarFichasCaducadas(llistaUnitatAdministrativaId, dataActual.getTime());

			model.put("fitxaActiva", fitxaActiva);
			model.put("fitxaCaducada", fitxaCaducada);
			model.put("fitxaTotal", fitxaActiva + fitxaCaducada);

		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				String error = messageSource.getMessage("error.permisos", null,request.getLocale());
				log.error(error);
			} else {
				//String error = messageSource.getMessage("error.altres", null,request.getLocale());
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
		}
				
		loadIndexModel (model, request);
		
		return "index";		
	}
	
}
