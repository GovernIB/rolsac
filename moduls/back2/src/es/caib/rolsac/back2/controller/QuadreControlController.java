package es.caib.rolsac.back2.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EstadisticaDelegate;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.NormativaDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value="/quadreControl/")  
public class QuadreControlController {
 
	private MessageSource messageSource = null;
	
	public final static Integer NUMERO_REGISTROS = 5;
    
    @Autowired
    public void setMessageSource(MessageSource messageSource){
        this.messageSource = messageSource;
    }
    
    @RequestMapping(value="/quadreControl.do")        
	public String quadreControl(HttpSession session, HttpServletRequest request, Map<String,Object> model) throws ServletException, IOException {
    	
    	GregorianCalendar dataActual = new GregorianCalendar();
    	model.put("dataActual", dataActual.getTime());
    	UnidadAdministrativa unitatAdministrativa = new UnidadAdministrativa();
    	
    	if (session.getAttribute("unidadAdministrativa")!=null){
    		unitatAdministrativa = (UnidadAdministrativa)session.getAttribute("unidadAdministrativa");
    		model.put("idUA",unitatAdministrativa.getId());
            model.put("nomUA",unitatAdministrativa.getNombreUnidadAdministrativa());
        } 
    	
        model.put("menu", 0);
        model.put("submenu", "layout/submenuOrganigrama.jsp");
        model.put("submenu_seleccionado", 0);
		model.put("titol_escriptori", messageSource.getMessage("submenu.quadre_control", null, request.getLocale()));
		model.put("escriptori", "pantalles/quadreControl.jsp");
		
		// Nombre de continguts
		try {
			// Procediment
			ProcedimientoDelegate procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
			int procedimentActiu = procedimientoDelegate.buscarProcedimientosActivos(unitatAdministrativa, dataActual.getTime());
			int procedimentCaducat = procedimientoDelegate.buscarProcedimientosCaducados(unitatAdministrativa, dataActual.getTime());
			model.put("procedimentActiu",procedimentActiu);
			model.put("procedimentCaducat",procedimentCaducat);
			model.put("procedimentTotal",procedimentActiu + procedimentCaducat);
			
			// Normativa
			NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
			int normativaActiva = normativaDelegate.buscarProcedimientosActivos(unitatAdministrativa);
			model.put("normativaActiva",normativaActiva);
			
			//Fitxa
			FichaDelegate fichaDelegate = DelegateUtil.getFichaDelegate();
			int fitxaActiva = fichaDelegate.buscarFichasActivas(unitatAdministrativa, dataActual.getTime());
			int fitxaCaducada = fichaDelegate.buscarFichasCaducadas(unitatAdministrativa, dataActual.getTime());
			
			model.put("fitxaActiva",fitxaActiva);
			model.put("fitxaCaducada",fitxaCaducada);
			model.put("fitxaTotal",fitxaActiva + fitxaCaducada);
			
			
		} catch (DelegateException e) {
			e.printStackTrace();
		}
		
		// Darreres Modificacions
		try {
			EstadisticaDelegate eDelegate = DelegateUtil.getEstadisticaDelegate();
			
			GregorianCalendar dataActualFi = dataActual;
			dataActualFi.add(Calendar.DATE, -7);
			
			Map<Timestamp, Object> llistaCanvis = eDelegate.listarUltimasModificaciones(dataActual.getTime(), dataActualFi.getTime(), NUMERO_REGISTROS, unitatAdministrativa);
			
			model.put("darreresModificacions", llistaCanvis);
			
			
		} catch (DelegateException dEx) {
			 if (dEx.getCause() instanceof SecurityException) {
	                String error = messageSource.getMessage("error.permisos", null, request.getLocale());
	            } else {
	                String error = messageSource.getMessage("error.altres", null, request.getLocale());
	                dEx.printStackTrace();
	            }
		}
		
		// Estadístiques
		
		return "index";
	}
}
