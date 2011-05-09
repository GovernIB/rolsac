package test.unitario.back.mock;

import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;

public class MockFichaDelegate extends FichaDelegate {

	public static Ficha ficha;
	
	 @Override
	public Ficha obtenerFicha(Long id) throws DelegateException {
		return ficha;
	}
	 
	 @Override
	public boolean autorizaCrearFicha(Integer validacionNormativa)
			throws DelegateException {
	return true;
	}

	 @Override
	 public Long grabarFicha(Ficha ficha) throws DelegateException {

		 long id=123L;
		 ficha.setId(id);
		 this.ficha=ficha;
		 return id;
	 }
	 
	 @Override
		public void anyadirMateria(Long materiaId, Long procId) throws DelegateException {
			
			Materia materia=new Materia();
			materia.setId(materiaId);
			if(0==materiaId)  
				materia.setCodigoEstandar(Materia.CE_SENSECLASSIFICAR);
			if(1==materiaId)  
				materia.setCodigoEstandar("Agricultura");

			ficha.addMateria(materia);

		}

		@Override
		public void eliminarMateria(Long materiaId, Long procId) throws DelegateException {
			ficha.removeMateria(materiaId);
		}
}
