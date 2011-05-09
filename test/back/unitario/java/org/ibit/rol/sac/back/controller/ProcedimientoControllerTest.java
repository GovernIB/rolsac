package org.ibit.rol.sac.back.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.ibit.rol.sac.back.negocio.BuscadorMaterias;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.springframework.mock.web.MockHttpServletRequest;

import test.unitario.back.mock.MockMateriaDelegate;

import junit.framework.TestCase;

public class ProcedimientoControllerTest extends TestCase {
  ProcedimientoController controller;

  public void test01MateriasNoContieneTodasMateriaMenosSinClasificar() throws ServletException, IOException, DelegateException {

	  resolverDependencias();
	  MockHttpServletRequest request = crearMockRequest();
	  request.addParameter("opcion", "materia");
	  controller.perform(null, request, null, null);
	  List<Materia> materias = listarMateriasDevueltas(request);
	  verificarNoExisteMateriaSinClasificar(materias);
  }

  private void resolverDependencias() throws DelegateException {
	  controller = new ProcedimientoController();
	  
	  BuscadorMaterias buscadorMaterias = new BuscadorMaterias();
	  controller.setBuscadorMaterias(buscadorMaterias);

	  buscadorMaterias.setMateriaDelegate(crearMockMateriaDelegate01());
	  
  }

  private MockMateriaDelegate crearMockMateriaDelegate01()
  throws DelegateException {
	  MockMateriaDelegate materiaDelegate = new MockMateriaDelegate() { 
		  public List listarMaterias() throws org.ibit.rol.sac.persistence.delegate.DelegateException {
			  List<Materia> materias=new ArrayList<Materia>();
			  materias=añadirMateria(materias,Materia.CE_SENSECLASSIFICAR);
			  materias=añadirMateria(materias,"Salut");
			  return materias;
		  }

		  private List<Materia> añadirMateria(List<Materia> materias, String cemateria) {
			  Materia materia;
			  materia = new Materia();
			  materia.setCodigoEstandar(cemateria);
			  materias.add(materia);
			  return materias;
		  }

	  };
	  return materiaDelegate;
  }

  private List<Materia> listarMateriasDevueltas(MockHttpServletRequest request) {
	  List<Materia> materias = (List<Materia>)request.getAttribute("elementOptions");
	  return materias;
  }

  private MockHttpServletRequest crearMockRequest() {
	  MockHttpServletRequest request = new MockHttpServletRequest();
	  return request;
  }

  private void verificarNoExisteMateriaSinClasificar(List<Materia> materias) {
	  assertEquals(1, materias.size());
	  for(Materia materia: materias) {
		  if(Materia.CE_SENSECLASSIFICAR.equals(materia.getCodigoEstandar())) 
			  fail();
	  }
  }


  
}
