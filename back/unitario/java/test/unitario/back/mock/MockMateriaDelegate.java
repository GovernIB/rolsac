package test.unitario.back.mock;

import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.TraduccionMateria;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;

public class MockMateriaDelegate extends MateriaDelegate {

	public MockMateriaDelegate() throws DelegateException {
		super();
	}

	@Override
	public Materia obtenerMateriaCE(String codigosEstandarMateria)
			throws DelegateException {

		Materia materia = new Materia();
		TraduccionMateria tradmat = new TraduccionMateria();
		
		if(Materia.CE_SENSECLASSIFICAR.equals(codigosEstandarMateria)) {
			tradmat.setDescripcion("Sense Classificar");
			materia.setTraduccion("ca", tradmat);
			materia.setCodigoEstandar(Materia.CE_SENSECLASSIFICAR);
			materia.setId(0L);
		}
		
		if("Agricultura".equals(codigosEstandarMateria)) {
			tradmat.setDescripcion("Agricultura");
			materia.setTraduccion("ca", tradmat);
			materia.setCodigoEstandar("Agricultura");
			materia.setId(1L);
		}

		if("Salut".equals(codigosEstandarMateria)) {
			tradmat.setDescripcion("Salut");
			materia.setTraduccion("ca", tradmat);
			materia.setCodigoEstandar("Salut");
			materia.setId(2L);
		}

		
		
		return materia;
	}
	
}
