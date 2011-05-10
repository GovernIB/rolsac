package test.unitario.back.mock;

import java.util.Arrays;
import java.util.List;

import org.ibit.rol.sac.model.Idioma;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;

public class MockIdiomaDelegate extends IdiomaDelegate {

	protected MockIdiomaDelegate() throws DelegateException {
		super();
	}

	
	public List listarLenguajes() {
		String[] langs={"ca","es","en","de"};
		return  Arrays.asList(langs);
	}
	
	public String lenguajePorDefecto() throws DelegateException {
		return "ca";
	}
	
	public List<Idioma> listarIdiomas() throws DelegateException {
		// TODO Auto-generated method stub
		return null;
	}
}
