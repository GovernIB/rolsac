package org.ibit.rol.sac.back.subscripcions.job;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.back.subscripcions.Suscripcionback;
import org.ibit.rol.sac.back.subscripcions.utils.StringUtil;
import org.ibit.rol.sac.model.HistoricoEnvio;
import org.ibit.rol.sac.model.RegistroTemaEnvio;
import org.ibit.rol.sac.model.RegistroTemaSuscriptor;
import org.ibit.rol.sac.model.Suscriptor;
import org.ibit.rol.sac.model.TipoSuscripcion;
import org.ibit.rol.sac.persistence.delegate.ActualizarSuscriptorDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.HistoricoEnvioDelegate;
import org.ibit.rol.sac.persistence.delegate.SuscriptorDelegate;
import org.ibit.rol.sac.persistence.delegate.TipoSuscripcionDelegate;




public class SubscripcionsPortalJob extends JobAutomatico{

	private Log log = LogFactory.getLog( SubscripcionsPortalJob.class  );
	

	@Override
	public void doProceso() throws Exception {

		String servidor = obtenerServidor();
		
		Set tos = new HashSet();
		try{
			String value = System.getProperty("es.caib.webcaib.principal");
			if ((value != null) && value.equals("S")) {
						
					
					TipoSuscripcionDelegate tsD = DelegateUtil.getTipoSuscripcionDelegate();
					List tsLista = tsD.listarTiposSuscripcion();
					TipoSuscripcion ts = null;
					for(Iterator it=tsLista.iterator(); it.hasNext();)
					{
						ts = (TipoSuscripcion) it.next();
						if(ts.getIdentificador().equals(Suscripcionback.SUSCRIPCION_PORTAL)) break;
						else ts = null;
					}
					if(ts == null)
					{
						log.error("No se ha encontrado Tipo de Suscripcion: " + Suscripcionback.SUSCRIPCION_PORTAL);
						return;
					}
					if(ts.getEstado().compareTo(TipoSuscripcion.INACTIVO) == 0) return;
					
					// Comprobamos las horas de envio
					if(!esHoraDeEnviar(ts)) return;
					
		
					HistoricoEnvioDelegate heD = DelegateUtil.getHistoricoEnvioDelegate();
					
					HistoricoEnvio ultEnvio = heD.obtenerUltimoHistoricoEnvio();
					if(ultEnvio != null)
					{
						Date now = new Date();
			            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			            String nowStr = sdf.format(now);
			            String fechaEnvio = sdf.format(ultEnvio.getFechaEnvio());
			            if(nowStr.equals(fechaEnvio))
						{
							log.debug("Hoy ya se ha realizado un envío");
							return;
						}
					}
					SuscriptorDelegate susDe = DelegateUtil.getSuscriptorDelegate();
					log.debug("Antes que nada");
					
					
					List<Suscriptor> listSuscriptores = susDe.getSuscriptoresByTipo(ts.getId());
					Set<String>temas = actualizaResumenTemas(listSuscriptores);
					
					log.debug("Despues de acutalizaResumentTemas");
					
					HistoricoEnvio he = new HistoricoEnvio();
					he.setTipoSuscripcion(ts);
					Long id = heD.grabarHistoricoEnvio(he);
					for(Iterator it=temas.iterator(); it.hasNext(); )
					{
						String codMat = (String) it.next();
						RegistroTemaEnvio rte = new RegistroTemaEnvio();
						rte.setIdMateria(Long.parseLong(codMat));
						log.debug("Servidor: " + servidor);
						String contenido = getContenido(servidor + "/govern/materia.do?codmat=" + codMat);
						log.debug("Registro Tema suscriptor: " + codMat + " genera: " + contenido);
						if(contenido.equals("")) continue;
						int idx = contenido.indexOf("NOT_DATA");
						if(idx == -1)
						{
							rte.setHtml(contenido);
							he.addTema(rte);
						}
					}
					log.debug("Despues de Generar Contenidos Materias");
					he.setFechaEnvio(new Date());
					heD.grabarHistoricoEnvio(he);
					
					Set combinaciones = susDe.recuperaCombinaciones(ts.getId());
					for(Iterator it=combinaciones.iterator(); it.hasNext(); )
					{
						String combinacion = (String) it.next();
						if((combinacion == null) || (combinacion.equals(""))) continue;
						log.debug("Combinacion: " + combinacion);
						String contenido = generaCombinacion(he,combinacion);
						log.debug("Combinacion: " + combinacion + " genera contenido: " + contenido);
						if(contenido.equals(""))
						{
							continue;
						}
						String contenidoHtml = getContenido(servidor + "/govern/boletinPortal.do");
						// En el caso de que no existan noticias nuevas no enviamos correos
			       		contenidoHtml = StringUtil.replace(contenidoHtml,"[#CONTENIDO#]",contenido);
						log.debug("Enviamos: " + contenidoHtml);
			       		List suscriptores = susDe.listarPorEstadoCombinacion(Suscriptor.TIPO_ACTIVO,combinacion);
			    		tos = new HashSet();
						addSuscriptores(tos,suscriptores);
					 	enviar(tos,"Butlletí diari",contenidoHtml,ts);
					}
					log.debug("Despues de Generar Contenidos Combinaciones");
					
			} else {
				log.debug("El jboss en donde está la aplicación no es el principal.");
			}
		}catch (Exception ex){
			log.error("Error al realizar el enviar Correos: " + ex.getMessage(),ex);			
		}		
		
	}
	/**
	 * Descripción: Actualizar el campo resumen temas de una lista de suscriptores. 
	 * @param suscriptores
	 * @return contiene todos los temas a los cuales los suscriptores estan suscritos
	 * @throws Exception
	 */
	private Set<String> actualizaResumenTemas(List<Suscriptor> suscriptores) throws Exception
	{
		try{
			HashSet<String> temasSet = new HashSet<String> ();
			ActualizarSuscriptorDelegate actuaSus = DelegateUtil.getActualizarSuscriptorDelegate();
	        for(Iterator<Suscriptor> it = suscriptores.iterator(); it.hasNext();)
	        {
	        	Suscriptor sus = (Suscriptor) it.next();
	        	Set<?> temas = sus.getTemas();
	        	List<Long> temasList = new ArrayList<Long>();
	        	String resumen = "";
	        	for(Iterator<?> itTemas = temas.iterator(); itTemas.hasNext();)
	        	{
	        		RegistroTemaSuscriptor reg = (RegistroTemaSuscriptor) itTemas.next();
	        		temasSet.add(reg.getIdMateria().toString());
	        		temasList.add(reg.getIdMateria());
	        	}
			  	RegistroTemaComparator comp = new RegistroTemaComparator();
	        	Collections.sort(temasList,comp);
	        	for(Iterator<Long> itTemasList = temasList.iterator(); itTemasList.hasNext();)
	        	{
	        		Long reg = (Long) itTemasList.next();
	           		resumen += reg.toString() + "#";
	        	}
        	
            actuaSus.actualizaSuscriptor(sus);
	        }
	    	return temasSet;
	    	
		}catch(Exception ex){
        	log.error("Actualizando resumen temas del suscriptor: " + ex.getMessage(),ex);
        	throw new Exception(ex);
       	}
	}

    private static class RegistroTemaComparator implements Comparator {
    	public int compare(Object element1, Object element2) {
	    	Long lower2 = (Long)element2;
	    	Long lower1 = (Long)element1;
	      return lower1.compareTo(lower2);
    	}
    }
	
	private String generaCombinacion(HistoricoEnvio he, String combinacion)
	{
		String[] codMaterias = combinacion.split("#");
		String resultado = "";
		for(int i=0; i<codMaterias.length; i++)
		{
			String codMat = codMaterias[i];
			if((codMat != null) && (!codMat.equals("")))
			{
				resultado += getTextoMateria(he,codMat);
			}
		}
		return resultado;
	}
	
	private String getTextoMateria(HistoricoEnvio he, String codigo)
	{
		Set temas = he.getTemas();
		for(Iterator it = temas.iterator(); it.hasNext();)
		{
			RegistroTemaEnvio re = (RegistroTemaEnvio) it.next();
			if(re.getIdMateria().toString().equals(codigo))
			{
				return re.getHtml();
			}
		}
		return "";
	}
 
	
}
