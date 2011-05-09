package es.caib.traspasa.util;


import es.caib.boib.article.model.ApartatModel;
import es.caib.boib.article.model.ArticleModel;
import es.caib.boib.article.model.RegistreModel;
import es.caib.boib.article.model.SubapartatModel;
import es.caib.boib.bocaib.ejb.BoibSession;
import es.caib.boib.bocaib.ejb.BoibSessionHome;
import es.caib.boib.article.ejb.Article;
import es.caib.boib.article.ejb.ArticleHome;

import es.caib.boib.tipus_norma.ejb.TipusNorma;
import es.caib.boib.tipus_norma.ejb.TipusNormaHome;
import es.caib.traspasa.actionsforms.SearchnormativaActionForm;
import es.caib.traspasa.bean.TrNormativaLocalBean;
import es.caib.traspasa.bean.TrMensaAvisoBean;
import es.caib.traspasa.bean.TrListadoNormativaLocalBean;

import java.util.ArrayList;
import java.sql.Date;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.NormativaDelegate;



public class BdSearchnormativa 
{

/**
 * @author vrs
 * 
 * Esta clase tiene los métodos necesarios para obtener listados de normativas,
 * articulos, etc...
 */
 
 private int numeroregistros=-1;
 private SearchnormativaActionForm fsearch;
 
 
 
 private Object boibs[] = null;
 private BoibSession boibBean = null;
 private Article boibArticleBean = null;
 private TipusNorma tipusNorma =null;
 
 
 private static Hashtable hashtableregistros_v = new Hashtable(); //hash temporal en catalan
 private static Hashtable hashtableregistros_c = new Hashtable(); //hash temporal en castellano
 

 private ArrayList listadonormativas = new ArrayList(); //contiene el listado de normativas en caso de más de una encontrada
 
 //beans
 private TrNormativaLocalBean normativabean = new TrNormativaLocalBean();
 private TrMensaAvisoBean mensajeavisobean = new TrMensaAvisoBean();

  


  /** Constructor. Se le pasa el formulario de busqueda */
  public BdSearchnormativa(SearchnormativaActionForm f) {
  
  //recoger el ejb-boib
        try {
         Context ic = new InitialContext();
         Object object = ic.lookup("es.caib.boib.BocaibSessionBean");
         BoibSessionHome home = (BoibSessionHome) PortableRemoteObject.narrow(object, BoibSessionHome.class);
         boibBean = home.create();
         } catch (Exception ex) {
          ex.printStackTrace();
         }

  //recoger el ejb-boib-article  
  	   try {
         Context ic = new InitialContext();
         Object object = ic.lookup("es.caib.boib.ArticleBean");
         ArticleHome home = (ArticleHome) PortableRemoteObject.narrow(object, ArticleHome.class);
         boibArticleBean = home.create();
       } catch (Exception ex) {
         ex.printStackTrace();
       }
       
  //recoger el ejb-boib-tipusnorma  
  	   try {
         Context ic = new InitialContext();
         Object object = ic.lookup("es.caib.boib.TipusNormaBean");
         TipusNormaHome home = (TipusNormaHome) PortableRemoteObject.narrow(object, TipusNormaHome.class);
         tipusNorma = home.create();
       } catch (Exception ex) {
         ex.printStackTrace();
       }       

    fsearch = f;
    
    traza("LONGITUD DEL HASH TEMPORAL " + hashtableregistros_v.size()); 
    
//    makeSearch();
  }
  
  /** método que devuelve el numero de normativas encontrados */
  public String getNumeroNormativas() 
  {
    return "" + numeroregistros;
  }
  
 

  
  /** método que realiza la búsqueda en el boid.
   * utiliza las clases del boib para acceder al mismo
   */
  public void makeSearch() {

        //obtener boid y numero registro
        int s_numeroboib = obtenerNumeroBoib();
        int s_numeroregistro = obtenerNumberoRegistroBoib();
    
        traza("ENTRAMOS EN MAKESEARCH:  s_numeroboib = " + obtenerNumeroBoib() + " , s_numeroregistro = " + s_numeroregistro);
        
        //if ((s_numeroboib!=-1) && (s_numeroregistro!=-1)) 
        
        if (s_numeroboib!=-1) {
          //antes de hacer nada hay que comprobar que no esté ya insertado
            boolean estainsertado_en_sac = isInsertSAC(s_numeroboib, s_numeroregistro);
            
            if (!estainsertado_en_sac) {
                int s_id_articulo_c = obtenerIdRegistroBoib(s_numeroboib, s_numeroregistro, 2); //castellano
                int s_id_articulo_v = obtenerIdRegistroBoib(s_numeroboib, s_numeroregistro, 1); //catalan
                
                switch (s_id_articulo_v) {
                case 1:
                       numeroregistros=1;
                       mensajeavisobean.setCabecera("OK");
                       mensajeavisobean.setSubcabecera("Cerca finalitzada correctament.");
                       break;
            
                case 0:
                       numeroregistros=0;
                       mensajeavisobean.setCabecera("ERROR EN LA CERCA");
                       mensajeavisobean.setSubcabecera("S'han obtingut resultats inesperats.");
                       mensajeavisobean.setDescripcion("El nombre de registres obtinguts en català ha sigut: " + s_id_articulo_v + " i en castellà ha sigut: " + s_id_articulo_c);
                       break;
            
                case -1:
                       numeroregistros=-1;
                       mensajeavisobean.setCabecera("ERROR EN ELS PARÀMETRES");
                       mensajeavisobean.setSubcabecera("Eror raro");
                       break;

                default:
                  numeroregistros=s_id_articulo_v; //se asignan las ocurrencias encontradas en catalan (por ejemplo)
                  mensajeavisobean.setCabecera("ERROR EN LA CERCA");
                  mensajeavisobean.setSubcabecera("S'han obtingut resultats inesperats.");
                  mensajeavisobean.setDescripcion("El nombre de registres obtinguts en 'català' han sigut: " + s_id_articulo_v + " i en 'castellà' ha sigut: " + s_id_articulo_c);
              }

                
              /*  
             if ((s_id_articulo_c==s_id_articulo_v) && (s_id_articulo_c==1)) {
                  numeroregistros=1;
                  mensajeavisobean.setCabecera("OK");
                  mensajeavisobean.setSubcabecera("Búsqueda finalizada correctamente.");
                } else {
                  numeroregistros=s_id_articulo_v; //se asignan las ocurrencias encontradas en catalan (por ejemplo)
                  mensajeavisobean.setCabecera("ERROR EN LA BÚSQUEDA");
                  mensajeavisobean.setSubcabecera("Se han obtenido resultados inexperados.");
                  mensajeavisobean.setDescripcion("El número de registros obtenidos en 'catalán' han sido: " + s_id_articulo_v + " y en 'castellano' han sido: " + s_id_articulo_c);
                }
                */
            } else {
                  numeroregistros=-1;
                  mensajeavisobean.setCabecera("ERROR EN LOS PARÀMETRES");
                  mensajeavisobean.setSubcabecera("El boib i el registre JA ESTAN introduïts en el SAC");
            }
            
        } else {
          numeroregistros=-1;
          mensajeavisobean.setCabecera("ERROR EN ELS PARÀMETRES");
          mensajeavisobean.setSubcabecera("Els paràmetres de cerca tenen inconsistències. Son erronis.");
          mensajeavisobean.setDescripcion("Las causes probables d'aquest error són que o bé el número del boib introduït o la data són incorrectes. Es possible que el número de registre contengui un valor incorrecte.");
        }
   
   //para vaciar el hash de cuando en cuando.
   if (hashtableregistros_v.size()>6000) 
   {
     traza("VACIAR EL HASH");
     hashtableregistros_v = new Hashtable();
     hashtableregistros_c = new Hashtable();
   }
   
  }

  /** método que realiza la búsqueda en el boid.
   * pero pasándole el boib y el numero de registro con la certeza de que son correctos
   */
 public void makeSearchFromBoibRegistro(String trcodificacion) 
 {
   //en el parametro está codificado el boib y el numero de registro.
   traza("ENTRAMOS EN MAKESEARCHFROMBOIBREGISTRO: trcodificacion= " +  trcodificacion);
   int cadenaX= trcodificacion.indexOf("X");
   int s_numeroboib;
   int s_numeroregistro;
   if (cadenaX!=-1) {
     s_numeroboib=Integer.parseInt(trcodificacion.substring(0,cadenaX));
     s_numeroregistro=Integer.parseInt(trcodificacion.substring(cadenaX+1,trcodificacion.length()));
     //antes de hacer nada hay que comprobar que no esté ya insertado
            boolean estainsertado_en_sac = isInsertSAC(s_numeroboib, s_numeroregistro);
            
            if (!estainsertado_en_sac) {
                int s_id_articulo_c = obtenerIdRegistroBoib(s_numeroboib, s_numeroregistro, 2); //castellano
                int s_id_articulo_v = obtenerIdRegistroBoib(s_numeroboib, s_numeroregistro, 1); //catalan
                
                
                if ((s_id_articulo_c==s_id_articulo_v) && (s_id_articulo_c==1)) {
                  numeroregistros=1;
                  mensajeavisobean.setCabecera("OK");
                  mensajeavisobean.setSubcabecera("Cerca finalitzada correctament.");
                } else {
                  numeroregistros=-1;
                  mensajeavisobean.setCabecera("ERROR EN LA CERCA");
                  mensajeavisobean.setSubcabecera("S'han obtingut resultats inesperats.");
                  mensajeavisobean.setDescripcion("El número de registres obtinguts en 'català' ha sigut: " + s_id_articulo_v + " i en 'castellà' ha sigut: " + s_id_articulo_c);
                }
            } else {
                  numeroregistros=-1;
                  mensajeavisobean.setCabecera("ERROR EN ELS PARÀMETRES");
                  mensajeavisobean.setSubcabecera("El boib i el registre JA ESTAN introduïts en el SAC");
            }
   } else {
       numeroregistros=-1;
       mensajeavisobean.setCabecera("ERROR EN ELS PARÀMETRES");
       mensajeavisobean.setSubcabecera("Incoherència de paràmetres");
       mensajeavisobean.setDescripcion("El valor del paràmetre és incorrecte. ");
   }
   
   //para vaciar el hash de cuando en cuando.
   if (hashtableregistros_v.size()>6000) 
   {
     traza("VACIAR EL HASH");
     hashtableregistros_v = new Hashtable();
     hashtableregistros_c = new Hashtable();
   }   
 }

   /** método que obtiene el numero de boib sobre el que se hace la búsqueda.
   */
 private int obtenerNumeroBoib() 
 {
        int tmp_numeroboib = (!fsearch.getNumeroboletin().equals(""))?Integer.parseInt(fsearch.getNumeroboletin()):-1;

        if (tmp_numeroboib == -1)  {
              java.util.Date s_fecha_tmp = null;
              java.sql.Date s_fecha =  null;
    
              if (!fsearch.getFecha().equals("")) {
                s_fecha_tmp = getFecha(fsearch.getFecha());
                s_fecha =  new java.sql.Date( s_fecha_tmp.getTime() );
              }
              
              try {
                Collection colecionarticulos;
                colecionarticulos = boibArticleBean.findByCompletSearch(-1, -1, -1, -1, "", "", "",-1, -1, s_fecha, s_fecha);
                boibs = colecionarticulos.toArray();
                ArticleModel am = null;
                if (boibs.length==1) {
                  am = (ArticleModel) boibs[0];
                  tmp_numeroboib = am.getNumero();
                } else {
                  tmp_numeroboib=-1;
                }
              } catch(Exception e) {
                tmp_numeroboib=-1;
                traza("ERROR AL OBTENER EL BOIB A PARTIR DE LA FECHA " + e.getMessage());
              }
        

        }
        
        return tmp_numeroboib;
 }
 
    /** método que obtiene el numero de registro dentro del boib sobre el que se hace la búsqueda.
   */
 private int obtenerNumberoRegistroBoib() 
 {
   int tmp_numeroregistro = (!fsearch.getNumeroregistro().equals(""))?Integer.parseInt(fsearch.getNumeroregistro()):-1;
   return tmp_numeroregistro;
 }


/** método principal que dado un boib, un numero registro y el idioma rellena todos las hash y arraylist y beans, 
 * y devuelve el numero de articulos encontrado.
 * 
 * Devuelve el numero de coincidencias encontradas.
 * Si numregboib es -1 entonces busca todas las de numboib: devolverá n.
 * Si numregboib es un numero busca el par boib/registro: devolverá -1, 1, o 0
 * */
private int obtenerIdRegistroBoib(int numboib, int numregboib, int idioma)
{
  int retorno=0;
  int retornodevarios=0;
  
  
  //primero hay que obtener el bean de los temporales.
  //si no están se busca en los metodos del boib.
  if (hashTieneNormativa(numboib,numregboib,idioma)) {
    retorno=1;
  } else {
  
      try {
        
        // Hay que recorrer todas las secciones, apartados, subapartados y registros
        // y meterlo en una hash
        
        Collection colecionseciones = boibArticleBean.getSeccions(numboib,idioma);
        Object vsecciones[] = null;
        vsecciones = colecionseciones.toArray();
        
       
        for (int i=0;i<vsecciones.length;i++) {
              traza("Seccion " + (String)vsecciones[i]);
       
              Collection colecionapartados = boibArticleBean.getSeccio(numboib, (String)vsecciones[i] );
              Object vapartados[] = null;
              vapartados = colecionapartados.toArray();

              for (int j=0;j<vapartados.length;j++) {
                      traza("Apartado " + (String)vapartados[j].toString());
                              
                      ApartatModel apartado = (ApartatModel)vapartados[j];
                      Collection colecionsubapartados = apartado.getList();
                      Object vsubapartados[] = null;
                      
                      vsubapartados = colecionsubapartados.toArray();
                     
                      for (int k=0;k<vsubapartados.length;k++) {
                            traza("Subapartado " + (String)vsubapartados[k].toString());
                          
                                          
                            
                            SubapartatModel subapartado = (SubapartatModel)vsubapartados[k];
                            Collection colecionregistrosmodel = subapartado.getList();
                            Object vcolecionregistrosmodel[] = null;
                            vcolecionregistrosmodel = colecionregistrosmodel.toArray();
                          
                            for (int x=0;x<vcolecionregistrosmodel.length;x++) {                           
                                  RegistreModel registre = (RegistreModel)vcolecionregistrosmodel[x];
                                  
                                  TrNormativaLocalBean tmpbean = new TrNormativaLocalBean();
                                  tmpbean=rellenaBean(numboib,registre,idioma); 
                                  String clavehash=numboib + "X" + registre.getRegistre();
                                    
                                  
                                  //meter en hash
                                  if (idioma==1) 
                                    hashtableregistros_c.put(clavehash, tmpbean);
                                  else 
                                    hashtableregistros_v.put(clavehash, tmpbean);
                                    
                                  //meter en el arraylist
                                  meterListaNormativa(tmpbean);
                                  retornodevarios++;
                                  
                                  traza("ADD al hash " + clavehash);
                                  
                                  if (registre.getRegistre()==numregboib) {
                                      traza("ENCONTRADO REGISTRO EN BOIB. REGISTRO: " + numregboib);
                                      //normativabean = tmpbean;
                                      normativabean = traspasobean(normativabean, tmpbean,idioma);
                                      retorno++;
                                  }
                            } // fin registros
                      } // fin subapartados
              } // fin apartados          
        } //fin secciones


      
    } catch (Exception e) 
    {
      retorno=-1;
      retornodevarios=-1;
      traza("ERRROR AL BUSCAR EN EL BOIB UN REGISTRO. MÉTODOD: obtenerIdRegistroBoib ." + e.getMessage());
    }
  
  } //else hashTieneNormativa
  
  traza("OJO, MUY IMPORTANTE, PUESTO QUE NO FUNCIONA EL MÉTODO DEL EJB VOY A SIMULAR QUE PONGO EL NUMÉRO 2 EN EL TIPO");

  
  if (numregboib!=-1) 
    return retorno;
  else 
    return retornodevarios;
}



//métodos para manejo del bean de la normativa

 private TrNormativaLocalBean traspasobean(TrNormativaLocalBean tmpbeanlocal, TrNormativaLocalBean tmpbean,int idi) 
 {

             tmpbeanlocal.setNumeroboib(tmpbean.getNumeroboib());
             tmpbeanlocal.setIdBoletin(tmpbean.getIdBoletin());
             tmpbeanlocal.setNombreBoletin(tmpbean.getNombreBoletin());
             tmpbeanlocal.setIdTipo(tmpbean.getIdTipo());
             tmpbeanlocal.setNombreTipo(tmpbean.getNombreTipo());
             tmpbeanlocal.setValorRegistro(tmpbean.getValorRegistro());
             tmpbeanlocal.setTextoFechaBoletin(tmpbean.getTextoFechaBoletin());
             if (idi==1) {
                 //castellano
                 tmpbeanlocal.setTra_titulo_c(tmpbean.getTra_titulo_c());
                 tmpbeanlocal.setTra_apartado_c(tmpbean.getTra_apartado_c());
                 tmpbeanlocal.setTra_paginaInicial_c(tmpbean.getTra_paginaInicial_c());
                 tmpbeanlocal.setTra_paginaFinal_c(tmpbean.getTra_paginaFinal_c());
             } else {
                  tmpbeanlocal.setTra_titulo_v(tmpbean.getTra_titulo_v());
                  tmpbeanlocal.setTra_apartado_v(tmpbean.getTra_apartado_v());
                  tmpbeanlocal.setTra_paginaInicial_v(tmpbean.getTra_paginaInicial_v());
                  tmpbeanlocal.setTra_paginaFinal_v(tmpbean.getTra_paginaFinal_v());
             }
    return  tmpbeanlocal;
 }


  private TrNormativaLocalBean rellenaBean(int boib, RegistreModel registre, int idi) 
  {
    TrNormativaLocalBean normbean = new TrNormativaLocalBean();
    try {
             
             ArticleModel articulo = boibArticleBean.getDetails(registre.getId());
            
             normbean.setNumeroboib("" + boib);
             normbean.setIdBoletin("1");
             normbean.setNombreBoletin("BOIB");
             normbean.setValorRegistro("" + registre.getRegistre());


             //valores del tipo
             //OJO, MUY IMPORTANTE, PUESTO QUE NO FUNCIONA EL MÉTODO DEL EJB,
             // VOY A SIMULAR QUE PONGO EL NUMÉRO 2
             
             String tipo_sac = "" + tipusNorma.getTipusNormesArticle(registre.getRegistre());
             traza("Registre "+ registre.getRegistre()+ " Tipus norma " +tipo_sac);
             //String tipo_sac = "2";
             normbean.setIdTipo(tipo_sac);
             
             if (!tipo_sac.equals("0")){
             //el tipo está codificado en el web.xml con un punto (.)
             String txnombretipo=Configuracion.getPropiedad("norma_"+tipo_sac);
             txnombretipo = txnombretipo.substring(txnombretipo.indexOf(".")+1,txnombretipo.length());
             
             normbean.setNombreTipo(txnombretipo);
             }else{
             	normbean.setNombreTipo("Varis");	
             	normbean.setIdTipo("73508");
             }
                                      
             java.text.SimpleDateFormat diamesanyo = new java.text.SimpleDateFormat("dd/MM/yyyy");
             normbean.setTextoFechaBoletin(diamesanyo.format(articulo.getData()));
             if (idi==1) {
                 //castellano
                 normbean.setTra_titulo_c(registre.getSumari());
                 normbean.setTra_apartado_c(articulo.getSeccio() + " | " + articulo.getApartat() + " | " + articulo.getSubapartat());
                 normbean.setTra_paginaInicial_c("" + registre.getPagini());
                 normbean.setTra_paginaFinal_c("" + registre.getPagfin());
             } else {
                  normbean.setTra_titulo_v(registre.getSumari());
                  normbean.setTra_apartado_v(articulo.getSeccio() + " | " + articulo.getApartat() + " | " + articulo.getSubapartat());
                  normbean.setTra_paginaInicial_v("" + registre.getPagini());
                  normbean.setTra_paginaFinal_v("" + registre.getPagfin());
             }
    } catch(Exception e) {
    	 
    }
    return normbean;
  }

  private boolean hashTieneNormativa(int numboib, int numregboib, int idioma) 
  {
    //si en el hash está la normativa la meteremos en el bean
    boolean retorno=false;
    String clavehash = numboib + "X" + numregboib;
    boolean tmpexiste;
    if (idioma==1) 
      tmpexiste=hashtableregistros_c.containsKey(clavehash);
    else 
      tmpexiste=hashtableregistros_v.containsKey(clavehash);
      
    retorno = tmpexiste;
    
    TrNormativaLocalBean tmpnormabean = new TrNormativaLocalBean();
    if (tmpexiste) {
        traza("BOIB " + numboib + " ENCONTRADO POR HASH");
        retorno=true;
        if (idioma==1) {
          tmpnormabean = (TrNormativaLocalBean)hashtableregistros_c.get(clavehash);
          normativabean = (TrNormativaLocalBean)hashtableregistros_v.get(clavehash);
          
          normativabean.setTra_titulo_c(tmpnormabean.getTra_titulo_c());
          normativabean.setTra_apartado_c(tmpnormabean.getTra_apartado_c());
          normativabean.setTra_paginaInicial_c(tmpnormabean.getTra_paginaInicial_c());
          normativabean.setTra_paginaFinal_c(tmpnormabean.getTra_paginaFinal_c());
        } else {
            tmpnormabean = (TrNormativaLocalBean)hashtableregistros_v.get(clavehash);
            normativabean = (TrNormativaLocalBean)hashtableregistros_c.get(clavehash);
            
            normativabean.setTra_titulo_v(tmpnormabean.getTra_titulo_v());
            normativabean.setTra_apartado_v(tmpnormabean.getTra_apartado_v());
            normativabean.setTra_paginaInicial_v(tmpnormabean.getTra_paginaInicial_v());
            normativabean.setTra_paginaFinal_v(tmpnormabean.getTra_paginaFinal_v());
        }
    }
    return retorno;
  }

//metodos para manejar la lista de normativas

  private void meterListaNormativa(TrNormativaLocalBean normativabean) 
  {
    TrListadoNormativaLocalBean tmpbean = new TrListadoNormativaLocalBean();
    tmpbean.setBoib(normativabean.getNumeroboib());
    tmpbean.setRegistro(normativabean.getValorRegistro());
    tmpbean.setTitulo(normativabean.getTra_titulo_v());
    tmpbean.setTrcodificacion(tmpbean.getBoib()+"X"+tmpbean.getRegistro());
    listadonormativas.add(tmpbean);
  }



//funciones para manejar informacion del SAC

  private boolean isInsertSAC(int s_numeroboib, int s_numeroregistro) 
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
  
  //seters y geters

  public void setNormativabean(TrNormativaLocalBean normativabean)
  {
    this.normativabean = normativabean;
  }


  public TrNormativaLocalBean getNormativabean()
  {
    return normativabean;
  }


  public void setMensajeavisobean(TrMensaAvisoBean mensajeavisobean)
  {
    this.mensajeavisobean = mensajeavisobean;
  }


  public TrMensaAvisoBean getMensajeavisobean()
  {
    return mensajeavisobean;
  }
  
    public void setListadonormativas(ArrayList listadonormativas)
  {
    this.listadonormativas = listadonormativas;
  }


  public ArrayList getListadonormativas()
  {
    return listadonormativas;
  }

  private java.util.Date getFecha(String txfecha) 
  {
    
    Calendar cal = Calendar.getInstance();
    String txanyo=txfecha.substring(6,10);
    String txmes=txfecha.substring(3,5);
    String txdia=txfecha.substring(0,2);
    cal.set( cal.YEAR, Integer.parseInt(txanyo) );
    cal.set( cal.MONTH, Integer.parseInt(txmes)-1 );
    cal.set( cal.DATE, Integer.parseInt(txdia) );
    
    return cal.getTime();
    
  }
  
  

//trazaaaaaaaaaaa ----------------------------------------------------
 private void traza(String txt) 
 {
  // System.out.println("VRS: " + txt);
 }






  
}