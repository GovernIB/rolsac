package org.ibit.rol.sac.back.negocio;

import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;

public class GrabadorFicha extends GrabadorBase {

	public <T extends org.ibit.rol.sac.model.Classificable> T grabar(org.ibit.rol.sac.back.form.TraDynaValidatorForm dForm, T t) throws DelegateException {
		Ficha ficha =  (Ficha) t;
		long id=getFichaDelegate().grabarFicha(ficha);
		ficha.setId(id);
		return t;
		
	}
	

	private FichaDelegate fichaDelegate;

	public void setFichaDelegate(FichaDelegate fichaDelegate) {
		this.fichaDelegate = fichaDelegate;
	}

	public FichaDelegate getFichaDelegate() {
    	if(null==fichaDelegate) 
    		fichaDelegate=DelegateUtil.getFichaDelegate();
		return fichaDelegate;
	}	
}
