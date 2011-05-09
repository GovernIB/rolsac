package es.caib.traspasa.util;

import es.caib.traspasa.actionsforms.NormativaForm;


public class BdEditar 
{
 private NormativaForm fdatos;

  public BdEditar(NormativaForm f) {
    fdatos = f;
  }
  
  public boolean insertarRegistro() 
  {
    boolean retorno=false;
    
    //VRS: aquí se realiza la insercion de la normativa o articulos
    // de momento es el código tal cual lo tienen en estos momentos
    // action que hace esto en el sac: org.ibit.rol.sac.back.action.contenido.normativa.EditarLocalAction
    
    /*
       
        log.info("Entramos en editar");
        //NormativaForm dForm = (NormativaForm) form;
        NormativaDelegate normativaDelegate = DelegateUtil.getNormativaDelegate();
        TipoNormativaDelegate tipoNormativaDelegate = DelegateUtil.getTipoNormativaDelegate();
        BoletinDelegate boletinDelegate = DelegateUtil.getBoletinDelegate();
        UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
        IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();

        NormativaLocal normativa = new NormativaLocal();
        VOUtils.populate(normativa, fdatos);

        NormativaLocal normativaOld = null;
        boolean modificant = fdatos.get("id") != null;
        if (modificant){
            //Recuperamos los datos de la normativa local
            normativaOld = (NormativaLocal)normativaDelegate.obtenerNormativa((Long)dForm.get("id"));
            normativa.setAfectadas(normativaOld.getAfectadas());
            normativa.setProcedimientos(normativaOld.getProcedimientos());
        }

        Iterator aux =  Arrays.asList((FormFile[]) dForm.get("ficheros")).iterator();
        Iterator lang = idiomaDelegate.listarLenguajes().iterator();

        while (aux.hasNext()){
            log.info("Entro en el while de ficheros");
            FormFile fichero = (FormFile) aux.next();
            String idioma = (String) lang.next();
            TraduccionNormativa traduccion = (TraduccionNormativa) normativa.getTraduccion(idioma);
            if (archivoValido(fichero)){
                traduccion.setArchivo(populateArchivo(traduccion.getArchivo(), fichero));
                normativa.setTraduccion(idioma, traduccion);
            } else if (request.getParameter("borrarfichero_" + idioma) != null){
                traduccion.setArchivo(null);
                normativa.setTraduccion(idioma, traduccion);
            } else if (modificant){
                TraduccionNormativa traduccionOld = (TraduccionNormativa) normativaOld.getTraduccion(idioma);
                if (traduccionOld != null && traduccionOld.getArchivo() != null)
                    traduccion.setArchivo(traduccionOld.getArchivo());
            }
        }

        normativa.setFecha(dForm.getFecha());
        normativa.setFechaBoletin(dForm.getFechaBoletin());
        if (dForm.get("valorNumero") != null)
            normativa.setNumero(((Long) dForm.get("valorNumero")).longValue());
        if (dForm.get("valorRegistro") != null)
            normativa.setRegistro(((Long) dForm.get("valorRegistro")).longValue());
        if (dForm.get("idTipo") != null)
            normativa.setTipo(tipoNormativaDelegate.obtenerTipoNormativa((Long) dForm.get("idTipo")));
        if (dForm.get("idBoletin") != null)
            normativa.setBoletin(boletinDelegate.obtenerBoletin((Long) dForm.get("idBoletin")));

        Long idUA = (Long) dForm.get("idUA");
        normativaDelegate.grabarNormativaLocal(normativa, idUA);

        if(modificant){
            log.info("Entro en el if de confirmacion de modificacion");
            request.setAttribute("alert", "confirmacion.modificacion");
            request.setAttribute("afectacionOptions", normativa.getAfectadas());
            request.setAttribute("procedimientoOptions", normativa.getProcedimientos());
        } else {
            request.setAttribute("alert", "confirmacion.alta");
        }

        dForm.set("id", normativa.getId());
        log.info("Creat/Actualitzat " + normativa.getId());
    
    */
   
    
    //VRS: fin de realizar la insercion
    
    return retorno;
  }
}