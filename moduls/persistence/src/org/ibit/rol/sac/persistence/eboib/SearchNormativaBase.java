package org.ibit.rol.sac.persistence.eboib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.NormativaDelegate;

public abstract class SearchNormativaBase implements SearchNormativa {

	  private List<TrListadoNormativaBean> listadonormativas = new ArrayList<TrListadoNormativaBean>(); //contiene el listado de normativas en caso de más de una encontrada

	  public SearchNormativaBase() {
	  }


	public void setListadonormativas(List<TrListadoNormativaBean> listadonormativas)
	  {
	    this.listadonormativas = listadonormativas;
	  }


	  public List<TrListadoNormativaBean> getListadonormativas()
	  {
	    return listadonormativas;
	  }

	  //seters y geters

	  //beans
	  protected TrNormativaBean normativabean = new TrNormativaBean();

	  public void setNormativabean(TrNormativaBean normativabean)
	  {
	    this.normativabean = normativabean;
	  }

	  /**
	   * Get normativaBean.
	   */
	  public TrNormativaBean getNormativabean()   {
	    return normativabean;
	  }

	  /** Mensaje aviso. **/
	  protected TrMensaAvisoBean mensajeavisobean = new TrMensaAvisoBean();
	  /**
	   * Set mensaje aviso.
	   * @param mensajeavisobean
	   */
	  public void setMensajeavisobean(TrMensaAvisoBean mensajeavisobean)
	  {
	    this.mensajeavisobean = mensajeavisobean;
	  }

	  /**
	   * Get mensaje aviso. 
	   */
	  public TrMensaAvisoBean getMensajeavisobean()  {
	    return mensajeavisobean;
	  }

	  
	  /**
	   * metodos para manejar la lista de normativas
	   * @param normativabean
	   */
	  protected void meterListaNormativa(TrNormativaBean normativabean)  {
	    TrListadoNormativaBean tmpbean = new TrListadoNormativaBean();
	    //normativa
	    tmpbean.setBoib(normativabean.getNumeroboib());
	    tmpbean.setRegistro(normativabean.getValorRegistro());
	    tmpbean.setTitulo(normativabean.getTra_titulo_v());
	    tmpbean.setFechaBoletin(normativabean.getFechaBoletin());
	    tmpbean.setTrcodificacion(tmpbean.getBoib()+"X"+tmpbean.getRegistro());
	    tmpbean.setIdTipoNormativa(normativabean.getIdTipoNormativa());
	    this.getListadonormativas().add(tmpbean);
	  }


	 /**
	  * funciones para manejar informacion del SAC
	  * @param s_numeroboib
	  * @param s_numeroregistro
	  * @return
	  */
	  protected boolean isInsertSAC(int s_numeroboib, int s_numeroregistro)  {
	    boolean retorno=false;
	    
	    Map<String, String> paramMap = new HashMap<String, String>();
	    Map<String, String> tradMap = new HashMap<String, String>();
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
