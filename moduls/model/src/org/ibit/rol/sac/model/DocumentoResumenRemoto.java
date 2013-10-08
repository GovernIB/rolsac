package org.ibit.rol.sac.model;

import java.util.HashMap;

import org.ibit.rol.sac.model.ws.DocumentoTransferible;
import org.ibit.rol.sac.model.ws.TraduccionDocumentoTransferible;

/**
 * Created by IntelliJ IDEA.
 * User: mgonzalez
 * Date: 17-ene-2011
 * Time: 12:33:59
 * To change this template use File | Settings | File Templates.
 */
public class DocumentoResumenRemoto extends DocumentoResumen implements Remoto{

    private AdministracionRemota administracionRemota;
	private Long idExterno;
    private String urlRemota;

    public AdministracionRemota getAdministracionRemota() {
        return administracionRemota;
    }

    public void setAdministracionRemota(AdministracionRemota administracionRemota) {
        this.administracionRemota = administracionRemota;
    }

    public Long getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(Long idExterno) {
        this.idExterno = idExterno;
    }

    public String getUrlRemota() {
        return urlRemota;
    }

    public void setUrlRemota(String urlRemota) {
        this.urlRemota = urlRemota;
    }

    public void rellenar(final DocumentoTransferible documentoTransferible){
		this.setIdExterno(documentoTransferible.getId());
		this.setOrden(documentoTransferible.getOrden());



		 //Relleno las traducciones
		final HashMap<String, Traduccion> traducciones = new HashMap<String, Traduccion>();
        if (documentoTransferible.getTraducciones() != null) {
            for (final TraduccionDocumentoTransferible traduccion : documentoTransferible.getTraducciones()){
                if (traduccion != null) {
                    final TraduccionDocumentoResumen temp =  new TraduccionDocumentoResumen();
                    temp.setTitulo(traduccion.getTitulo());
                    temp.setDescripcion(traduccion.getDescripcion());
                    traducciones.put(traduccion.getCodigoEstandarIdioma().toLowerCase(), temp);
                    temp.setArchivoResumen(ArchivoResumen.generar(traduccion.getArchivoTransferible()));
                }
            }
        }
        this.setTraduccionMap(traducciones);
	}
}
