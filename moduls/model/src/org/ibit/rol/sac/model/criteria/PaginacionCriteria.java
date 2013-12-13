package org.ibit.rol.sac.model.criteria;

import java.io.Serializable;


public class PaginacionCriteria implements Serializable {
	
	private static final long serialVersionUID = 4139076365395485088L;
	
	private int pagPag;
	private int pagRes;
	private String criterioOrdenacion;
	private String propiedadDeOrdenacion;
	
	public PaginacionCriteria(int pagPag, int pagRes, String criterioOrdenacion, String propiedadDeOrdenacion) {
		
		this.pagPag = pagPag;
		this.pagRes = pagRes;
		this.criterioOrdenacion = criterioOrdenacion;
		this.propiedadDeOrdenacion = propiedadDeOrdenacion;
		
	}
	
	public PaginacionCriteria() {
		
	}
	
	public int getPagPag() {
		return pagPag;
	}
	
	public void setPagPag(int pagPag) {
		this.pagPag = pagPag;
	}
	
	public int getPagRes() {
		return pagRes;
	}
	
	public void setPagRes(int pagRes) {
		this.pagRes = pagRes;
	}

	public String getCriterioOrdenacion() {
		return criterioOrdenacion;
	}

	public void setCriterioOrdenacion(String criterioOrdenacion) {
		this.criterioOrdenacion = criterioOrdenacion;
	}

	public String getPropiedadDeOrdenacion() {
		return propiedadDeOrdenacion;
	}

	public void setPropiedadDeOrdenacion(String propiedadDeOrdenacion) {
		this.propiedadDeOrdenacion = propiedadDeOrdenacion;
	}

}
