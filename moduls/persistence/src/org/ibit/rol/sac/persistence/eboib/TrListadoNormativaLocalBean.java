package org.ibit.rol.sac.persistence.eboib;

import java.util.Date;

public class TrListadoNormativaLocalBean 
{

  private String boib="";
  private String registro="";
  private String titulo="";
  private String trcodificacion="";
  private Date fechaBoletin;
  

public TrListadoNormativaLocalBean()
  {
  }


  public void setBoib(String boib)
  {
    this.boib = boib;
  }


  public String getBoib()
  {
    return boib;
  }


  public void setRegistro(String registro)
  {
    this.registro = registro;
  }


  public String getRegistro()
  {
    return registro;
  }


  public void setTitulo(String titulo)
  {
    this.titulo = titulo;
  }


  public String getTitulo()
  {
    return titulo;
  }


  public void setTrcodificacion(String trcodificacion)
  {
    this.trcodificacion = trcodificacion;
  }


  public String getTrcodificacion()
  {
    return trcodificacion;
  }


  public void setFechaBoletin(Date date) {
	this.fechaBoletin = date;
	
  }

  public Date getFechaBoletin() {
		return fechaBoletin;
	}


}