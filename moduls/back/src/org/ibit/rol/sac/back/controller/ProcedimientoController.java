/**
 * User: jhorrach
 * Date: Mar 3, 2004
 * Time: 1:48:27 PM
 */
package org.ibit.rol.sac.back.controller;

import org.apache.struts.tiles.Controller;
import org.apache.struts.tiles.ComponentContext;
import org.ibit.rol.sac.back.negocio.BuscadorMaterias;
import org.ibit.rol.sac.back.utils.MateriasUtils;
import org.ibit.rol.sac.persistence.delegate.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;


public class ProcedimientoController implements Controller {



	public void perform(ComponentContext tilesContext,
			HttpServletRequest request,
			HttpServletResponse response,
			ServletContext servletContext)
	throws ServletException, IOException {


		try {
			if ("Familia".equals(request.getParameter("opcion"))){
				establecerAtributosFamiliaEnRequest(request);
			}
			if ("Iniciacion".equals(request.getParameter("opcion"))){
				establecerAtributosIniciacionEnRequest(request);
			}
			if ("materia".equals(request.getParameter("opcion"))){
				establecerAtributosMateriaEnRequest(request);
			}
			if ("normativa".equals(request.getParameter("opcion"))){
				establecerAtributosNormativasEnRequest(request);
			}

		} catch (DelegateException e) {
			throw new ServletException(e);
		}

	}

	private void establecerAtributosNormativasEnRequest(HttpServletRequest request) throws DelegateException {
		NormativaDelegate delegate = DelegateUtil.getNormativaDelegate();

		request.setAttribute("elementOptions", delegate.listarNormativas());
		request.setAttribute("opcion", "normativa");
	}

	private void establecerAtributosIniciacionEnRequest(HttpServletRequest request) throws DelegateException {
		IniciacionDelegate delegate = DelegateUtil.getIniciacionDelegate();

		request.setAttribute("elementOptions",delegate.listarIniciacion());
		request.setAttribute("opcion", "Iniciacion");
	}

	private void establecerAtributosFamiliaEnRequest(HttpServletRequest request) throws DelegateException {
		FamiliaDelegate delegate = DelegateUtil.getFamiliaDelegate();

		request.setAttribute("elementOptions", delegate.listarFamilias());
		request.setAttribute("opcion", "Familia");
	}

	private void establecerAtributosMateriaEnRequest(HttpServletRequest request) throws DelegateException {

		request.setAttribute("elementOptions", getBuscadorMaterias().listarTodasMateriasFiltrandoSinClasificar());
		request.setAttribute("opcion", "materia");
	}


	BuscadorMaterias buscadorMaterias;
	
	public BuscadorMaterias getBuscadorMaterias() {
		if(null==buscadorMaterias) buscadorMaterias = new BuscadorMaterias();
		return buscadorMaterias;
	}

	public void setBuscadorMaterias(BuscadorMaterias listadorMaterias) {
		this.buscadorMaterias = listadorMaterias;
	}


}
