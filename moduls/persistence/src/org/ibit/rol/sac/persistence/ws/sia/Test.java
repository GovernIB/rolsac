package org.ibit.rol.sac.persistence.ws.sia;

import org.ibit.rol.sac.persistence.ws.sia.actualizar.WsSIAActualizarActuaciones_PortType;
import org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIA;
import org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACION;
import org.ibit.rol.sac.persistence.ws.sia.actualizar.v3_1.paramSIA.ParamSIAACTUACIONESACTUACIONACTIVO;




public class Test {

	public static void main(String[] args) throws Exception {
		
		String usuario ="WSBALEARES";
		String password = "primera";
		
		WsSIAActualizarActuaciones_PortType client = SiaClient.createClient("http://pre-sia2.redsara.es/axis2/services/wsSIAActualizarActuaciones");
		
		ParamSIAACTUACIONESACTUACION actuacion = new ParamSIAACTUACIONESACTUACION();
		
		ParamSIAACTUACIONESACTUACIONACTIVO activo = new ParamSIAACTUACIONESACTUACIONACTIVO("S");
		actuacion.setACTIVO(activo );
		
		ParamSIAACTUACIONESACTUACION[] actuaciones = new ParamSIAACTUACIONESACTUACION[1];
		actuaciones[0] = actuacion;
		
		ParamSIA parameters = new ParamSIA(usuario, password, null, actuaciones );
		client.actualizarSIA_v3_1(parameters );

	}

}
