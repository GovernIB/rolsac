package es.caib.traspasa.util;

import es.caib.traspasa.bean.TrListadoNormativaLocalBean;
import es.caib.traspasa.bean.TrNormativaLocalBean;
import es.caib.traspasa.bean.TrMensaAvisoBean;

import java.util.List;

public interface SearchNormativa {
  /** m�todo que devuelve el numero de normativas encontrados */
  public String getNumeroNormativas();
  
  /** m�todo que realiza la b�squeda en el boib.
   */
  public void makeSearch();

  /** m�todo que realiza la b�squeda en el boib.
   * pero pas�ndole el boib y el numero de registro con la certeza de que son correctos
   */
  public void makeSearchFromBoibRegistro(String trcodificacion); 

  //seters y geters
  public void setNormativabean(TrNormativaLocalBean normativabean);
  public TrNormativaLocalBean getNormativabean();
  public void setMensajeavisobean(TrMensaAvisoBean mensajeavisobean);
  public TrMensaAvisoBean getMensajeavisobean();
  public void setListadonormativas(List<TrListadoNormativaLocalBean> listadonormativas);
  public List<TrListadoNormativaLocalBean> getListadonormativas();

}