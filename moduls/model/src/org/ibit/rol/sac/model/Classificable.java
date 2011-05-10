package org.ibit.rol.sac.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class Classificable extends Traducible {

	private Set<Materia> materias;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.model.Classificable#getMaterias()
	 */
	public Set<Materia> getMaterias() {
	    return materias;
	}

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.model.Classificable#setMaterias(java.util.Set)
	 */
	public void setMaterias(Set<Materia> materias) {
	    this.materias = materias;
	}

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.model.Classificable#addMateria(org.ibit.rol.sac.model.Materia)
	 */
	public void addMateria(Materia materia) {
		materias.add(materia);
		
	}
	
	
	public void removeMateria(long id) {
		Materia materia=new Materia();
		materia.setId(id);
		materias.remove(materia);
	}


	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.model.Classificable#tieneMaterias()
	 */
	public boolean tieneMaterias() {
		return 0<materias.size();
	}
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.model.Classificable#estaClasificado()
	 */
	public boolean estaClasificado() {
		if(0==materias.size()) 
			return false;
		if(tieneMateriaSinClasificar()) 
			return false;
		return true;
	}

	private boolean tieneMateriaSinClasificar() {
		Iterator<Materia> it = materias.iterator();
		return Materia.CE_SENSECLASSIFICAR.equals(it.next().getCodigoEstandar());
	}

	@Override
	public String toString() {
		return "[materias=" + Arrays.toString(materias.toArray()) + "]";
	}

	
}