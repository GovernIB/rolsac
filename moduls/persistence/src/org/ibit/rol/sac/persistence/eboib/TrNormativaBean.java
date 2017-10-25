package org.ibit.rol.sac.persistence.eboib;

import java.util.Date;

public class TrNormativaBean 
{
      protected String numeroboib;
      protected String validacion;
      protected String idTipo;
      protected String nombreTipo;
      protected String idBoletin;
      protected String nombreBoletin;
      protected String idUA;
      protected String nombreUA;
      protected String valorRegistro;
      protected String tipoNormativa;
      protected String idTipoNormativa;
      protected Date fechaBoletin;
      
      //las del detalle de cada idioma
      protected String tra_titulo_c;
      protected String tra_enlace_c;
      protected String tra_apartado_c;
      protected String tra_paginaInicial_c;
      protected String tra_paginaFinal_c;
      protected String tra_observaciones_c;
      protected String tipoPublicacion_c;
      protected String tra_titulo_v;
      protected String tra_enlace_v;
      protected String tra_apartado_v;
      protected String tra_paginaInicial_v;
      protected String tra_paginaFinal_v;
      protected String tra_observaciones_v;  
      protected String tipoPublicacion_v;
      
  public TrNormativaBean()
  {
  }


  public void setNumeroboib(String numeroboib)
  {
    this.numeroboib = numeroboib;
  }


  public String getNumeroboib()
  {
    return numeroboib;
  }


  public void setValidacion(String validacion)
  {
    this.validacion = validacion;
  }


  public String getValidacion()
  {
    return validacion;
  }


  public void setIdTipo(String idTipo)
  {
    this.idTipo = idTipo;
  }


  public String getIdTipo()
  {
    return idTipo;
  }


  public void setNombreTipo(String nombreTipo)
  {
    this.nombreTipo = nombreTipo;
  }


  public String getNombreTipo()
  {
    return nombreTipo;
  }


  public void setIdBoletin(String idBoletin)
  {
    this.idBoletin = idBoletin;
  }


  public String getIdBoletin()
  {
    return idBoletin;
  }


public void setNombreBoletin(String nombreBoletin)
  {
    this.nombreBoletin = nombreBoletin;
  }


  public String getNombreBoletin()
  {
    return nombreBoletin;
  }


  public void setIdUA(String idUA)
  {
    this.idUA = idUA;
  }


  public String getIdUA()
  {
    return idUA;
  }


  public void setNombreUA(String nombreUA)
  {
    this.nombreUA = nombreUA;
  }


  public String getNombreUA()
  {
    return nombreUA;
  }


  public void setValorRegistro(String valorRegistro)
  {
    this.valorRegistro = valorRegistro;
  }


  public String getValorRegistro()
  {
    return valorRegistro;
  }


  public void setTra_titulo_c(String tra_titulo_c)
  {
    this.tra_titulo_c = tra_titulo_c;
  }


  public String getTra_titulo_c()
  {
    return tra_titulo_c;
  }


  public void setTra_enlace_c(String tra_enlace_c)
  {
    this.tra_enlace_c = tra_enlace_c;
  }


  public String getTra_enlace_c()
  {
    return tra_enlace_c;
  }


  public void setTra_apartado_c(String tra_apartado_c)
  {
    this.tra_apartado_c = tra_apartado_c;
  }


  public String getTra_apartado_c()
  {
    return tra_apartado_c;
  }


  public void setTra_paginaInicial_c(String tra_paginaInicial_c)
  {
    this.tra_paginaInicial_c = tra_paginaInicial_c;
  }


  public String getTra_paginaInicial_c()
  {
    return tra_paginaInicial_c;
  }


  public void setTra_paginaFinal_c(String tra_paginaFinal_c)
  {
    this.tra_paginaFinal_c = tra_paginaFinal_c;
  }


  public String getTra_paginaFinal_c()
  {
    return tra_paginaFinal_c;
  }


  public void setTra_observaciones_c(String tra_observaciones_c)
  {
    this.tra_observaciones_c = tra_observaciones_c;
  }


  public String getTra_observaciones_c()
  {
    return tra_observaciones_c;
  }


  public void setTra_titulo_v(String tra_titulo_v)
  {
    this.tra_titulo_v = tra_titulo_v;
  }


  public String getTra_titulo_v()
  {
    return tra_titulo_v;
  }


  public void setTra_enlace_v(String tra_enlace_v)
  {
    this.tra_enlace_v = tra_enlace_v;
  }


  public String getTra_enlace_v()
  {
    return tra_enlace_v;
  }


  public void setTra_apartado_v(String tra_apartado_v)
  {
    this.tra_apartado_v = tra_apartado_v;
  }


  public String getTra_apartado_v()
  {
    return tra_apartado_v;
  }


  public void setTra_paginaInicial_v(String tra_paginaInicial_v)
  {
    this.tra_paginaInicial_v = tra_paginaInicial_v;
  }


  public String getTra_paginaInicial_v()
  {
    return tra_paginaInicial_v;
  }


  public void setTra_paginaFinal_v(String tra_paginaFinal_v)
  {
    this.tra_paginaFinal_v = tra_paginaFinal_v;
  }


  public String getTra_paginaFinal_v()
  {
    return tra_paginaFinal_v;
  }


  public void setTra_observaciones_v(String tra_observaciones_v)
  {
    this.tra_observaciones_v = tra_observaciones_v;
  }


  public String getTra_observaciones_v()
  {
    return tra_observaciones_v;
  }


public Date getFechaBoletin() {
	return fechaBoletin;
}


public void setFechaBoletin(Date fechaBoletin) {
	this.fechaBoletin = fechaBoletin;
}


/**
 * @return the tipoPublicacion_c
 */
public String getTipoPublicacion_c() {
	return tipoPublicacion_c;
}


/**
 * @param tipoPublicacion_c the tipoPublicacion_c to set
 */
public void setTipoPublicacion_c(String tipoPublicacion_c) {
	this.tipoPublicacion_c = tipoPublicacion_c;
}


/**
 * @return the tipoPublicacion_v
 */
public String getTipoPublicacion_v() {
	return tipoPublicacion_v;
}


/**
 * @param tipoPublicacion_v the tipoPublicacion_v to set
 */
public void setTipoPublicacion_v(String tipoPublicacion_v) {
	this.tipoPublicacion_v = tipoPublicacion_v;
}


/**
 * @return the tipoNormativa
 */
public String getTipoNormativa() {
	return tipoNormativa;
}


/**
 * @param tipoNormativa the tipoNormativa to set
 */
public void setTipoNormativa(String tipoNormativa) {
	this.tipoNormativa = tipoNormativa;
}


/**
 * @return the idTipoNormativa
 */
public String getIdTipoNormativa() {
	return idTipoNormativa;
}


/**
 * @param idTipoNormativa the idTipoNormativa to set
 */
public void setIdTipoNormativa(String idTipoNormativa) {
	this.idTipoNormativa = idTipoNormativa;
}


}