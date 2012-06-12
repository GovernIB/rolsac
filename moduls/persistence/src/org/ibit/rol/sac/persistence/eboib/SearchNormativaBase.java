package org.ibit.rol.sac.persistence.eboib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.NormativaDelegate;

public abstract class SearchNormativaBase implements SearchNormativa {

	  private List<TrListadoNormativaLocalBean> listadonormativas = new ArrayList<TrListadoNormativaLocalBean>(); //contiene el listado de normativas en caso de más de una encontrada

	  public SearchNormativaBase() {
	  }


	public void setListadonormativas(List<TrListadoNormativaLocalBean> listadonormativas)
	  {
	    this.listadonormativas = listadonormativas;
	  }


	  public List<TrListadoNormativaLocalBean> getListadonormativas()
	  {
	    return listadonormativas;
	  }

	  //seters y geters

	  //beans
	  protected TrNormativaLocalBean normativabean = new TrNormativaLocalBean();

	  public void setNormativabean(TrNormativaLocalBean normativabean)
	  {
	    this.normativabean = normativabean;
	  }


	  public TrNormativaLocalBean getNormativabean()
	  {
	    return normativabean;
	  }


	  protected TrMensaAvisoBean mensajeavisobean = new TrMensaAvisoBean();
	  public void setMensajeavisobean(TrMensaAvisoBean mensajeavisobean)
	  {
	    this.mensajeavisobean = mensajeavisobean;
	  }


	  public TrMensaAvisoBean getMensajeavisobean()
	  {
	    return mensajeavisobean;
	  }

	  
	//metodos para manejar la lista de normativas
	  protected void meterListaNormativa(TrNormativaLocalBean normativabean) 
	  {
	    TrListadoNormativaLocalBean tmpbean = new TrListadoNormativaLocalBean();
	    //normativa
	    tmpbean.setBoib(normativabean.getNumeroboib());
	    tmpbean.setRegistro(normativabean.getValorRegistro());
	    tmpbean.setTitulo(normativabean.getTra_titulo_v());
	    tmpbean.setFechaBoletin(normativabean.getFechaBoletin());
	    tmpbean.setTrcodificacion(tmpbean.getBoib()+"X"+tmpbean.getRegistro());
	    this.getListadonormativas().add(tmpbean);
	  }


	//funciones para manejar informacion del SAC
	  protected boolean isInsertSAC(int s_numeroboib, int s_numeroregistro) 
	  {
	    boolean retorno=false;
	    
	    Map paramMap = new HashMap();
	    Map tradMap = new HashMap();
	    String TIPO_NORM = "local";
	    
	    // Parámetros generales
	    paramMap.put("numero", "" + s_numeroboib);
	    paramMap.put("registro", "" + s_numeroregistro);
	    
	    try {
	      NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
	      int numnormativas = normativaDelegate.buscarNormativas(paramMap, tradMap, TIPO_NORM).size();
	      retorno = (numnormativas>=1);     
	      
	    } catch(Exception e) {
	      retorno=false;
	    }
	    traza("EL RESULTADO DE LA BUSQUEDA EN SAC HA SIDO " + retorno);
	    return retorno;
	  }
	  

	//trazaaaaaaaaaaa ----------------------------------------------------
	 protected void traza(String txt) 
	 {
	  // System.out.println("VRS: " + txt);
	 }
	  
	
}
