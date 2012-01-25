<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="rol" uri="/WEB-INF/rol.tld" %>

    <link href="<c:url value='/css/unitat.css'/>" rel="stylesheet" type="text/css" media="screen" />
    <link href="<c:url value='/css/modul_seccions.css'/>" rel="stylesheet" type="text/css" media="screen" />
	<link href="<c:url value='/css/modul_seccions_fitxes.css'/>" rel="stylesheet" type="text/css" media="screen" />        
    <link href="<c:url value='/css/modul_edificis.css'/>" rel="stylesheet" type="text/css" media="screen" />    

	<input type="hidden" id="rolusuario" value="<rol:printRol/>"/>	
	    	    
    <div id="escriptori_contingut">
    </div>
    
    <div id="carregantDetall">
        <p class="executant"><spring:message code='unitatadm.carregant_unitatadm'/></p>
    </div>          
    <!-- escriptori_detall -->
    <div id="escriptori_detall" class="escriptori_detall">
        <form id="formGuardar" action="" method="post">
        <input id="item_id" name="item_id" type="hidden" value='<c:out value="${idUA}" />' />       
        <p><spring:message code='txt.recordi_dades_asterisc'/> (<span class="obligatori">*</span>) <spring:message code='txt.son_obligatories'/></p>                    
        <!-- modulPrincipal -->     
        <div id="modulPrincipal" class="grupoModulosFormulario modulPrincipal">            
            <!-- modul -->
            <div class="modul">     
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend>Dades</legend>
                    <div class="modul_continguts mostrat">
                        <!-- fila -->
                        <div class="fila">
                            <p class="introIdiomas"><spring:message code='txt.idioma.idioma'/></p>
                            <ul class="idiomes">
                                <li class="idioma">
                                    <a href="javascript:;" class="ca"><spring:message code='txt.idioma.ca'/></a>
                                </li>
                                <li class="idioma">
                                    <a href="javascript:;" class="es"><spring:message code='txt.idioma.es'/></a>
                                </li>
                                <li class="idioma">
                                    <a href="javascript:;" class="en"><spring:message code='txt.idioma.en'/></a>
                                </li>
                                <li class="idioma">
                                    <a href="javascript:;" class="de"><spring:message code='txt.idioma.de'/></a>
                                </li>
                                <li class="idioma">
                                    <a href="javascript:;" class="fr"><spring:message code='txt.idioma.fr'/></a>
                                </li>
                                <li class="traduix btnGenerico">
                                    <a href="javascript:;" class="btn traduix"><span><span><spring:message code='txt.idioma.tradueix'/></span></span></a>
                                </li>
                            </ul>
                            <div class="idiomes">
                            <!-- ca -->
                                <div class="idioma ca"> 
                                    <div class="fila">
                                        <div class="element t50p">                                      
                                            <div class="etiqueta"><label for="item_codi_estandar"><spring:message code='unitatadm.formulari.codi_estandar'/></label></div>
                                            <div class="control">
                                                <input id="item_codi_estandar" name="item_codi_estandar" type="text" />
                                            </div>                                          
                                        </div>                                      
                                    </div>
                                    <div class="fila">
                                        <div class="element t75p">
                                            <div class="etiqueta">
                                                <label for="item_nom_ca"><spring:message code='unitatadm.formulari.nom'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_nom_ca" name="item_nom_ca" type="text" />
                                            </div>
                                        </div>
                                        <div class="element t25p">
                                            <div class="etiqueta">
                                                <label for="item_clave_primaria">Clave primaria</label>                                                
                                            </div>
                                            <div class="control">
                                                <input id="item_clave_primaria" name="item_clave_primaria" type="text" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta"><label for="item_presentacio_ca"><spring:message code='unitatadm.formulari.presentacio'/></label></div>
                                            <div class="control">
                                                <textarea id="item_presentacio_ca" name="item_presentacio_ca" style="width:100%" rows="6" class="rich complexe nou"></textarea>
                                            </div>
                                        </div>
                                    </div>  
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_abreviatura_ca"><spring:message code='unitatadm.formulari.abreviatura'/></label></div>
                                            <div class="control">
                                                <input id="item_abreviatura_ca" name="item_abreviatura_ca" type="text" />
                                            </div>
                                        </div>
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_url_ca"><spring:message code='unitatadm.formulari.url'/></label></div>
                                            <div class="control">
                                                <input id="item_url_ca" name="item_url_ca" type="text" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">                                      
                                            <div class="etiqueta"><label for="item_espai_territorial"><spring:message code='unitatadm.formulari.espai_territorial'/></label></div>
                                            <div class="control select">                                                
                                                <select id="item_espai_territorial" name="item_espai_territorial">
                                                    <option value=""><spring:message code='txt.escolliu_opcio'/></option>
                                                    <c:forEach items="${llistaEspaiTerritorial}" var="espaiTerritorial">
                                                        <option value='<c:out value="${espaiTerritorial.id}" />'><c:out value="${espaiTerritorial.nom}" /></option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>                                          
                                    </div>      
                                    <div class="fila">
                                        <div class="element t50p">                                          
                                            <div class="etiqueta"><label for="item_pare"><spring:message code='unitatadm.formulari.pare'/></label></div>
                                            <div class="control">
                                                <input id="item_pare" name="item_pare" type="text" readonly="readonly" />
                                                <input id="item_pare_id" name="item_pare_id" type="hidden" />
                                            </div>                                          
                                        </div>            
                                    </div>
                                    <!-- Botonera -->
                                    <div id="cercador">
                                        <div class="botonera" style="margin-top: 0px; float:left;">
                                            <div class="boton btnGenerico" style="margin-left: 0px;">
                                                <a href="javascript:carregarArbreUA('<c:url value="/pantalles/popArbreUA.do"/>','popUA','item_pare_id','item_pare');" class="btn consulta">
                                                    <span><span><spring:message code='boto.canviarUAPare'/></span></span>
                                                </a>
                                            </div>
                                            <div class="boton btnGenerico">
                                                <a href="javascript:EliminaArbreUA('item_pare','item_pare_id');" class="btn borrar">
                                                    <span><span><spring:message code='boto.borrar'/></span></span>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /Botonera -->
                                    
                                </div>
                                <!-- /ca -->
                                <!-- es -->
                                <div class="idioma es">
                                    <div class="fila">
                                        <div class="element t50p">                                      
                                            <div class="etiqueta"><label for="item_codi_estandar_es"><spring:message code='unitatadm.formulari.codi_estandar'/></label></div>
                                            <div class="control">
                                                <input id="item_codi_estandar_es" name="item_codi_estandar_es" type="text" />
                                            </div>                                          
                                        </div>                                      
                                    </div>
                                    <div class="fila">
                                        <div class="element t75p">
                                            <div class="etiqueta"><label for="item_nom_es"><spring:message code='unitatadm.formulari.nom'/></label></div>
                                            <div class="control">
                                                <input id="item_nom_es" name="item_nom_es" type="text" />
                                            </div>
                                        </div>
                                        <div class="element t25p">
                                            <div class="etiqueta">
                                                <label for="item_clave_primaria_es">Clave primaria</label>                                                
                                            </div>
                                            <div class="control">
                                                <input id="item_clave_primaria_es" name="item_clave_primaria_es" type="text" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta"><label for="item_presentacio_es"><spring:message code='unitatadm.formulari.presentacio'/></label></div>
                                            <div class="control">
                                                <textarea id="item_presentacio_es" name="item_presentacio_es" style="width:100%" rows="6" class="rich complexe nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_abreviatura_es"><spring:message code='unitatadm.formulari.abreviatura'/></label></div>
                                            <div class="control">
                                                <input id="item_abreviatura_es" name="item_abreviatura_es" type="text" />
                                            </div>
                                        </div>
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_url_es"><spring:message code='unitatadm.formulari.url'/></label></div>
                                            <div class="control">
                                                <input id="item_url_es" name="item_url_es" type="text" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">                                      
                                            <div class="etiqueta"><label for="item_espai_territorial_es"><spring:message code='unitatadm.formulari.espai_territorial'/></label></div>
                                            <div class="control select">                                                
                                                <select id="item_espai_territorial_es" name="item_espai_territorial_es">
                                                    <option value=""><spring:message code='txt.escolliu_opcio'/></option>
                                                    <c:forEach items="${llistaEspaiTerritorial}" var="espaiTerritorial">
                                                        <option value='<c:out value="${espaiTerritorial.id}" />'><c:out value="${espaiTerritorial.nom}" /></option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>  
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">                                          
                                            <div class="etiqueta"><label for="item_pare_es"><spring:message code='unitatadm.formulari.pare'/></label></div>
                                            <div class="control">
                                                <input id="item_pare_es" name="item_pare_es" type="text" readonly="readonly" />                                                
                                            </div>                                          
                                        </div>            
                                    </div>
                                    <!-- Botonera -->
                                    <div id="cercador">
                                        <div class="botonera" style="margin-top: 0px; float:left;">
                                            <div class="boton btnGenerico" style="margin-left: 0px;">
                                                <a href="javascript:carregarArbreUA('<c:url value="/pantalles/popArbreUA.do"/>','popUA','item_pare_id','item_pare_es');" class="btn consulta">
                                                    <span><span><spring:message code='boto.canviarUAPare'/></span></span>
                                                </a>
                                            </div>
                                            <div class="boton btnGenerico">
                                                <a href="javascript:EliminaArbreUA('item_pare_es','item_pare_id');" class="btn borrar">
                                                    <span><span><spring:message code='boto.borrar'/></span></span>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /Botonera -->
                                </div>
                                <!-- /es -->
                                <!-- en -->
                                <div class="idioma en">
                                    <div class="fila">
                                        <div class="element t50p">                                      
                                            <div class="etiqueta"><label for="item_codi_estandar_en"><spring:message code='unitatadm.formulari.codi_estandar'/></label></div>
                                            <div class="control">
                                                <input id="item_codi_estandar_en" name="item_codi_estandar_en" type="text" />
                                            </div>                                          
                                        </div>                                      
                                    </div>
                                    <div class="fila">
                                        <div class="element t75p">
                                            <div class="etiqueta">
                                                <label for="item_nom_en"><spring:message code='unitatadm.formulari.nom'/></label>
                                                
                                            </div>
                                            <div class="control">
                                                <input id="item_nom_en" name="item_nom_en" type="text" />
                                            </div>                                            
                                        </div>
                                        <div class="element t25p">
                                            <div class="etiqueta">
                                                <label for="item_clave_primaria_en">Clave primaria</label>                                                
                                            </div>
                                            <div class="control">
                                                <input id="item_clave_primaria_en" name="item_clave_primaria_en" type="text" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta"><label for="item_presentacio_en"><spring:message code='unitatadm.formulari.presentacio'/></label></div>
                                            <div class="control">
                                                <textarea id="item_presentacio_en" name="item_presentacio_en" style="width:100%" rows="6" class="rich complexe nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_abreviatura_en"><spring:message code='unitatadm.formulari.abreviatura'/></label></div>
                                            <div class="control">
                                                <input id="item_abreviatura_en" name="item_abreviatura_en" type="text" />
                                            </div>
                                        </div>
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_url_en"><spring:message code='unitatadm.formulari.url'/></label></div>
                                            <div class="control">
                                                <input id="item_url_en" name="item_url_en" type="text" />
                                            </div>  
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">                                      
                                            <div class="etiqueta"><label for="item_espai_territorial_en"><spring:message code='unitatadm.formulari.espai_territorial'/></label></div>
                                            <div class="control select">                                                
                                                <select id="item_espai_territorial_en" name="item_espai_territorial_en">
                                                    <option value=""><spring:message code='txt.escolliu_opcio'/></option>
                                                    <c:forEach items="${llistaEspaiTerritorial}" var="espaiTerritorial">
                                                        <option value='<c:out value="${espaiTerritorial.id}" />'><c:out value="${espaiTerritorial.nom}" /></option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>  
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">                                          
                                            <div class="etiqueta"><label for="item_pare_en"><spring:message code='unitatadm.formulari.pare'/></label></div>
                                            <div class="control">
                                                <input id="item_pare_en" name="item_pare_en" type="text" readonly="readonly" />                                                
                                            </div>                                          
                                        </div>            
                                    </div>
                                    <!-- Botonera -->
                                    <div id="cercador">
                                        <div class="botonera" style="margin-top: 0px; float:left;">
                                            <div class="boton btnGenerico" style="margin-left: 0px;">
                                                <a href="javascript:carregarArbreUA('<c:url value="/pantalles/popArbreUA.do"/>','popUA','item_pare_id','item_pare_en');" class="btn consulta">
                                                    <span><span><spring:message code='boto.canviarUAPare'/></span></span>
                                                </a>
                                            </div>
                                            <div class="boton btnGenerico">
                                                <a href="javascript:EliminaArbreUA('item_pare_en','item_pare_id');" class="btn borrar">
                                                    <span><span><spring:message code='boto.borrar'/></span></span>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /Botonera -->
                                </div>
                                <!-- /en -->
                                <!-- de -->
                                <div class="idioma de">
                                    <div class="fila">
                                        <div class="element t50p">                                      
                                            <div class="etiqueta"><label for="item_codi_estandar_de"><spring:message code='unitatadm.formulari.codi_estandar'/></label></div>
                                            <div class="control">
                                                <input id="item_codi_estandar_de" name="item_codi_estandar_de" type="text" />
                                            </div>                                          
                                        </div>                                      
                                    </div>
                                    <div class="fila">
                                        <div class="element t75p">
                                            <div class="etiqueta"><label for="item_nom_de"><spring:message code='unitatadm.formulari.nom'/></label></div>
                                            <div class="control">
                                                <input id="item_nom_de" name="item_nom_de" type="text" />
                                            </div>
                                        </div>
                                        <div class="element t25p">
                                            <div class="etiqueta">
                                                <label for="item_clave_primaria_de">Clave primaria</label>                                                
                                            </div>
                                            <div class="control">
                                                <input id="item_clave_primaria_de" name="item_clave_primaria_de" type="text" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta"><label for="item_presentacio_de"><spring:message code='unitatadm.formulari.presentacio'/></label></div>
                                            <div class="control">
                                                <textarea id="item_presentacio_de" name="item_presentacio_de" style="width:100%" rows="6" class="rich complexe nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_abreviatura_de"><spring:message code='unitatadm.formulari.abreviatura'/></label></div>
                                            <div class="control">
                                                <input id="item_abreviatura_de" name="item_abreviatura_de" type="text" />
                                            </div>
                                        </div>
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_url_de"><spring:message code='unitatadm.formulari.url'/></label></div>
                                            <div class="control">
                                                <input id="item_url_de" name="item_url_de" type="text" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">                                      
                                            <div class="etiqueta"><label for="item_espai_territorial_de"><spring:message code='unitatadm.formulari.espai_territorial'/></label></div>
                                            <div class="control select">                                                
                                                <select id="item_espai_territorial_de" name="item_espai_territorial_de">
                                                    <option value=""><spring:message code='txt.escolliu_opcio'/></option>
                                                    <c:forEach items="${llistaEspaiTerritorial}" var="espaiTerritorial">
                                                        <option value='<c:out value="${espaiTerritorial.id}" />'><c:out value="${espaiTerritorial.nom}" /></option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>  
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">                                          
                                            <div class="etiqueta"><label for="item_pare_de"><spring:message code='unitatadm.formulari.pare'/></label></div>
                                            <div class="control">
                                                <input id="item_pare_de" name="item_pare_de" type="text" readonly="readonly" />                                                
                                            </div>                                          
                                        </div>            
                                    </div>
                                    <!-- Botonera -->
                                    <div id="cercador">
                                        <div class="botonera" style="margin-top: 0px; float:left;">
                                            <div class="boton btnGenerico" style="margin-left: 0px;">
                                                <a href="javascript:carregarArbreUA('<c:url value="/pantalles/popArbreUA.do"/>','popUA','item_pare_id','item_pare_de');" class="btn consulta">
                                                    <span><span><spring:message code='boto.canviarUAPare'/></span></span>
                                                </a>
                                            </div>
                                            <div class="boton btnGenerico">
                                                <a href="javascript:EliminaArbreUA('item_pare_de','item_pare_id');" class="btn borrar">
                                                    <span><span><spring:message code='boto.borrar'/></span></span>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /Botonera -->
                                </div>
                                <!-- /de -->
                                <!-- fr -->
                                <div class="idioma fr">
                                    <div class="fila">
                                        <div class="element t50p">                                      
                                            <div class="etiqueta"><label for="item_codi_estandar_fr"><spring:message code='unitatadm.formulari.codi_estandar'/></label></div>
                                            <div class="control">
                                                <input id="item_codi_estandar_fr" name="item_codi_estandar_fr" type="text" />
                                            </div>                                          
                                        </div>                                      
                                    </div>
                                    <div class="fila">
                                        <div class="element t75p">
                                            <div class="etiqueta"><label for="item_nom_fr"><spring:message code='unitatadm.formulari.nom'/></label></div>
                                            <div class="control">
                                                <input id="item_nom_fr" name="item_nom_fr" type="text" />
                                            </div>
                                        </div>
                                        <div class="element t25p">
                                            <div class="etiqueta">
                                                <label for="item_clave_primaria_fr">Clave primaria</label>                                                
                                            </div>
                                            <div class="control">
                                                <input id="item_clave_primaria_fr" name="item_clave_primaria_fr" type="text" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta"><label for="item_presentacio_fr"><spring:message code='unitatadm.formulari.presentacio'/></label></div>
                                            <div class="control">
                                                <textarea id="item_presentacio_fr" name="item_presentacio_fr" style="width:100%" rows="6" class="rich complexe nou"></textarea>
                                            </div>
                                        </div>
                                    </div>  
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_abreviatura_fr"><spring:message code='unitatadm.formulari.abreviatura'/></label></div>
                                            <div class="control">
                                                <input id="item_abreviatura_fr" name="item_abreviatura_fr" type="text" />
                                            </div>
                                        </div>
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_url_fr"><spring:message code='unitatadm.formulari.url'/></label></div>
                                            <div class="control">
                                                <input id="item_url_fr" name="item_url_fr" type="text" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">                                      
                                            <div class="etiqueta"><label for="item_espai_territorial_fr"><spring:message code='unitatadm.formulari.espai_territorial'/></label></div>
                                            <div class="control select">                                                
                                                <select id="item_espai_territorial_fr" name="item_espai_territorial_fr">
                                                    <option value=""><spring:message code='txt.escolliu_opcio'/></option>
                                                    <c:forEach items="${llistaEspaiTerritorial}" var="espaiTerritorial">
                                                        <option value='<c:out value="${espaiTerritorial.id}" />'><c:out value="${espaiTerritorial.nom}" /></option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>  
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">                                          
                                            <div class="etiqueta"><label for="item_pare_fr"><spring:message code='unitatadm.formulari.pare'/></label></div>
                                            <div class="control">
                                                <input id="item_pare_fr" name="item_pare_fr" type="text" readonly="readonly" />                                                
                                            </div>                                          
                                        </div>            
                                    </div>
                                    <!-- Botonera -->
                                    <div id="cercador">
                                        <div class="botonera" style="margin-top: 0px; float:left;">
                                            <div class="boton btnGenerico" style="margin-left: 0px;">
                                                <a href="javascript:carregarArbreUA('<c:url value="/pantalles/popArbreUA.do"/>','popUA','item_pare_id','item_pare_fr');" class="btn consulta">
                                                    <span><span><spring:message code='boto.canviarUAPare'/></span></span>
                                                </a>
                                            </div>
                                            <div class="boton btnGenerico">
                                                <a href="javascript:EliminaArbreUA('item_pare_fr','item_pare_id');" class="btn borrar">
                                                    <span><span><spring:message code='boto.borrar'/></span></span>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /Botonera -->
                                </div>
                                <!-- /fr -->        
                            </div>
                        </div>
                        <!-- /fila -->
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->
            
            <!-- modul -->
            <div class="modul">
                <fieldset>  
                    <a class="modul amagat"><spring:message code='txt.mostra'/></a>
                    <legend><spring:message code='unitatadm.formulari.responsable'/></legend>
                    <div class="modul_continguts"><%-- style="display:block;" hasta que funcione el jquery --%>
                        <!-- fila -->
                        <div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta"><label for="item_responsable"><spring:message code='unitatadm.formulari.responsable.nom'/></label></div>
                                <div class="control">
                                    <input id="item_responsable" name="item_responsable" type="text" />
                                </div>  
                            </div>
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_responsable_sexe"><spring:message code='unitatadm.formulari.responsable.sexe'/></label>
                                    
                                </div>
                                <div class="control select">
                                    <select id="item_responsable_sexe" name="item_responsable_sexe">
                                        <option value=""><spring:message code='txt.escolliu_opcio'/></option>
                                        <option value="1"><spring:message code='txt.sexe.masculi'/></option>
                                        <option value="2"><spring:message code='txt.sexe.femeni'/></option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <!-- /fila -->
                        <!-- fila -->
                        <div class="fila">
	                        <div class="element t50p campoImagen">	                                     
                                <div class="thumbnail"><img src="" width="50" height="50"/></div>
                                <div class="etiqueta"><label for="item_responsable_foto_petita"><spring:message code='unitatadm.formulari.responsable.foto.petita'/></label></div>
                                <div class="control archivo">                                                                           
                                    <div id="grup_item_responsable_foto_petita" class="file">                                        
                                        <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                        <a href="#" target="_blank"></a>
                                        <input type="checkbox" name="item_responsable_foto_petita_delete" id="item_responsable_foto_petita_delete" value="1"/>
                                        <label for="item_responsable_foto_petita_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                    </div>
                                </div>                                
	                        </div>    	                        
	                        <div class="element t50p">
	                            <div class="etiqueta"><label for="item_responsable_foto_petita"><spring:message code='unitatadm.formulari.responsable.foto.petita'/></label></div>
	                            <div class="control">                                      		
	                                <input id="item_responsable_foto_petita" name="item_responsable_foto_petita" type="file" class="nou" />
	                            </div>
	                        </div>                                                                                      
                        </div>
                        <!-- /fila -->
                        <!-- fila -->
                        <div class="fila">
	                        <div class="element t50p campoImagen">
                                <div class="thumbnail"><img src="" width="50" height="50"/></div>
	                            <div class="etiqueta"><label for="item_responsable_foto_gran"><spring:message code='unitatadm.formulari.responsable.foto.gran'/></label></div>
	                            <div class="control archivo">   
	                            	<div id="grup_item_responsable_foto_gran" class="file">
	                            		<span><spring:message code='txt.no_arxiu_assignat'/></span>
	                            		<a href="#" target="_blank"></a>
	                            		<input type="checkbox" name="item_responsable_foto_gran_delete" id="item_responsable_foto_gran_delete" value="1"/>
	                            		<label for="item_responsable_foto_gran_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
	                            	</div>
	                            </div>
	                        </div>    
	                        
	                        <div class="element t50p">
	                            <div class="etiqueta"><label for="item_responsable_foto_gran"><spring:message code='unitatadm.formulari.responsable.foto.gran'/></label></div>
	                            <div class="control">                                      		
	                                <input id="item_responsable_foto_gran" name="item_responsable_foto_gran" type="file" class="nou" />
	                            </div>
	                        </div>                                                                                      
                        </div>
                        <!-- /fila -->
                        <%--
                        <!-- fila -->
                        <div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta"><label for="item_responsable_foto_petita"><spring:message code='unitatadm.formulari.responsable.foto.petita'/></label></div>
                                <div class="control">
                                    <input id="item_responsable_foto_petita" name="item_responsable_foto_petita" type="file" />
                                </div>
                            </div>
                            <div class="element t50p">
                                <div class="etiqueta"><label for="item_responsable_foto_gran"><spring:message code='unitatadm.formulari.responsable.foto.gran'/></label></div>
                                <div class="control">
                                    <input id="item_responsable_foto_gran" name="item_responsable_foto_gran" type="file" />
                                </div>
                            </div>
                        </div>
                        <!-- /fila -->
                        --%>
                        <!-- fila -->
                        <div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_tractament"><spring:message code='unitatadm.formulari.responsable.tractament'/></label>
                                    
                                </div>
                                <div class="control select">
                                    <!-- input id="item_tractament" name="item_tractament" type="text" /-->
                                    <select id="item_tractament" name="item_tractament">
                                        <option value=""><spring:message code='txt.escolliu_opcio'/></option>
                                        <c:forEach items="${llistaTractaments}" var="tractament">
                                            <option value='<c:out value="${tractament.id}" />'><c:out value="${tractament.nom}" /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <!-- /fila -->
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->
            
            <!-- modul -->
            <div class="modul">                         
                <fieldset>                                  
                    <a class="modul amagat"><spring:message code='txt.mostra'/></a>                             
                    <legend>CONTACTO</legend>                              
                    <div class="modul_continguts" style="display:block;">                               
                    
                        <!-- fila -->
                        <div class="fila">                              
                            <div class="element t50p">                                  
                                <div class="etiqueta"><label for="item_telefon"><spring:message code='unitatadm.formulari.telefon'/></label></div>
                                <div class="control">
                                    <input id="item_telefon" name="item_telefon" type="text" />
                                </div>                                      
                            </div>  
                            <div class="element t50p">                                      
                                <div class="etiqueta"><label for="item_fax"><spring:message code='unitatadm.formulari.fax'/></label></div>
                                <div class="control">
                                    <input id="item_fax" name="item_fax" type="text" />
                                </div>                                          
                            </div>                                      
                        </div>
                        <!-- /fila -->     
                        
                        <!-- fila -->
                        <div class="fila">
                            <div class="element t50p">                                      
                                <div class="etiqueta"><label for="item_email"><spring:message code='unitatadm.formulari.email'/></label></div>
                                <div class="control">
                                    <input id="item_email" name="item_email" type="text" />
                                </div>                                          
                            </div>
                            <div class="element t50p">                                      
                                <div class="etiqueta"><label for="item_domini"><spring:message code='unitatadm.formulari.domini'/></label></div>
                                <div class="control">
                                    <input id="item_domini" name="item_domini" type="text" />
                                </div>                                          
                            </div>
                        </div>
                        <!-- /fila -->
                    
                        <!-- fila -->
                        <%--<div class="fila">                                  
                            <div class="element t50p">                                      
                                <div class="etiqueta"><label for="item_clau_hita"><spring:message code='unitatadm.formulari.clau_hita'/></label></div>
                                <div class="control">
                                    <input id="item_clau_hita" name="item_clau_hita" type="text" />
                                </div>                                          
                            </div>
                            <div class="element t50p">                                      
                                <div class="etiqueta"><label for="item_codi_estandar"><spring:message code='unitatadm.formulari.codi_estandar'/></label></div>
                                <div class="control">
                                    <input id="item_codi_estandar" name="item_codi_estandar" type="text" />
                                </div>                                          
                            </div>
                        </div>--%>
                        <!-- /fila -->                                  
                        <!-- fila -->
                        <%--<div class="fila">                                      
                            <div class="element t50p">                                      
                                <div class="etiqueta"><label for="item_domini"><spring:message code='unitatadm.formulari.domini'/></label></div>
                                <div class="control">
                                    <input id="item_domini" name="item_domini" type="text" />
                                </div>                                          
                            </div>
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_validacio"><spring:message code='unitatadm.formulari.validacio'/></label>
                                    
                                </div>
                                <div class="control select">
                                    <select id="item_validacio" name="item_validacio">
                                        <option value="" selected="selected"><spring:message code='txt.escolliu_opcio'/></option>
                                        <option value="1"><spring:message code='unitatadm.formulari.validacio.publica'/></option>
                                        <option value="2"><spring:message code='unitatadm.formulari.validacio.interna'/></option>
                                    </select>
                                </div>
                            </div>                                      
                        </div>--%>
                        <!-- /fila -->                                  
                         
                        <!-- fila -->
                        <%--<div class="fila">                          
                            <div class="element t50p">                                      
                                <div class="etiqueta"><label for="item_email"><spring:message code='unitatadm.formulari.email'/></label></div>
                                <div class="control">
                                    <input id="item_email" name="item_email" type="text" />
                                </div>                                          
                            </div>                            
                            <div class="element t50p">                                      
                                <div class="etiqueta"><label for="item_espai_territorial"><spring:message code='unitatadm.formulari.espai_territorial'/></label></div>
                                <div class="control select">                                    
                                    <select id="item_espai_territorial" name="item_espai_territorial">
                                        <option value=""><spring:message code='txt.escolliu_opcio'/></option>
                                        <c:forEach items="${llistaEspaiTerritorial}" var="espaiTerritorial">
                                            <option value='<c:out value="${espaiTerritorial.id}" />'><c:out value="${espaiTerritorial.nom}" /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>                              
                        </div>--%>
                        <!-- /fila -->
                        
                    </div>                              
                </fieldset>                         
            </div>
            <!-- /modul -->                 
            
            <!-- modul -->
            <div class="modul">
                <fieldset>  
                    <a class="modul amagat"><spring:message code='txt.mostra'/></a>
                    <legend><spring:message code='unitatadm.formulari.logotipus'/></legend>
                    <div class="modul_continguts" style="display:block;">
                    	<!-- fila -->
                    	<div class="fila">
                            <div class="element t50p campoImagen">	                                     
                                <div class="thumbnail"><img src="" width="50" height="50"/></div>                            
                                <div class="etiqueta"><label for="item_logo_horizontal"><spring:message code='unitatadm.formulari.logotipus.horitzontal'/></label></div>
                                <div class="control archivo">   
                                	<div id="grup_item_logo_horizontal" class="file">
                                		<span><spring:message code='txt.no_arxiu_assignat'/></span>
                                		<a href="#" target="_blank"></a>
                                		<input type="checkbox" name="item_logo_horizontal_delete" id="item_logo_horizontal_delete" value="1"/>
                                		<label for="item_logo_horizontal_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                	</div>
                                </div>
                            </div>                                
                            
                            <div class="element t50p">
                                <div class="etiqueta"><label for="item_logo_horizontal"><spring:message code='unitatadm.formulari.logotipus.horitzontal'/></label></div>
                                <div class="control">
                                    <input id="item_logo_horizontal" name="item_logo_horizontal" type="file" class="nou" />
                                </div>
                            </div>                                                                                      
                     	</div>
                     	<!-- /fila -->
                     	<!-- fila -->
                     	<div class="fila">
                            <div class="element t50p campoImagen">	                                     
                                <div class="thumbnail"><img src="" width="50" height="50"/></div>
                                <div class="etiqueta"><label for="item_logo_vertical"><spring:message code='unitatadm.formulari.logotipus.vertical'/></label></div>
                                <div class="control archivo">   
                                	<div id="grup_item_logo_vertical" class="file">
                                		<span><spring:message code='txt.no_arxiu_assignat'/></span>
                                		<a href="#" target="_blank"></a>
                                		<input type="checkbox" name="item_logo_vertical_delete" id="item_logo_vertical_delete" value="1"/>
                                		<label for="item_logo_vertical_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                	</div>
                                </div>
                            </div>    
                            
                            <div class="element t50p">
                                <div class="etiqueta"><label for="item_logo_vertical"><spring:message code='unitatadm.formulari.logotipus.vertical'/></label></div>
                                <div class="control">
                                    <input id="item_logo_vertical" name="item_logo_vertical" type="file" class="nou" />
                                </div>
                            </div>                                                                                      
                     	</div>
                        <!-- /fila -->
                        <!-- fila -->
                     	<div class="fila">
                            <div class="element t50p campoImagen">	                                     
                                <div class="thumbnail"><img src="" width="50" height="50"/></div>
                                <div class="etiqueta"><label for="item_logo_salutacio_horizontal"><spring:message code='unitatadm.formulari.logotipus.horitzontal.salutacio'/></label></div>
                                <div class="control archivo">   
                                	<div id="grup_item_logo_salutacio_horizontal" class="file">
                                		<span><spring:message code='txt.no_arxiu_assignat'/></span>
                                		<a href="#" target="_blank"></a>
                                		<input type="checkbox" name="item_logo_salutacio_horizontal_delete" id="item_logo_salutacio_horizontal_delete" value="1"/>
                                		<label for="item_logo_salutacio_horizontal_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                	</div>
                                </div>
                            </div>    
                                                        
                            <div class="element t50p">
                                <div class="etiqueta"><label for="item_logo_salutacio_horizontal"><spring:message code='unitatadm.formulari.logotipus.horitzontal.salutacio'/></label></div>
                                <div class="control">
                                    <input id="item_logo_salutacio_horizontal" name="item_logo_salutacio_horizontal" type="file" class="nou" />
                                </div>
                            </div>                                                                                      
                     	</div>
                        <!-- /fila -->
                        <!-- fila -->
                     	<div class="fila">
                            <div class="element t50p campoImagen">	                                     
                                <div class="thumbnail"><img src="" width="50" height="50"/></div>
                                <div class="etiqueta"><label for="item_logo_salutacio_vertical"><spring:message code='unitatadm.formulari.logotipus.vertical.salutacio'/></label></div>
                                <div class="control archivo">   
                                	<div id="grup_item_logo_salutacio_vertical" class="file">
                                		<span><spring:message code='txt.no_arxiu_assignat'/></span>
                                		<a href="#" target="_blank"></a>
                                		<input type="checkbox" name="item_logo_salutacio_vertical_delete" id="item_logo_salutacio_vertical_delete" value="1"/>
                                		<label for="item_logo_salutacio_vertical_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                	</div>
                                </div>
                            </div>    
                            
                            
                            <div class="element t50p">
                                <div class="etiqueta"><label for="item_logo_salutacio_vertical"><spring:message code='unitatadm.formulari.logotipus.vertical.salutacio'/></label></div>
                                <div class="control">
                                    <input id="item_logo_salutacio_vertical" name="item_logo_salutacio_vertical" type="file" class="nou" />
                                </div>
                            </div>                                                                                      
                     	</div>
                        <!-- /fila -->             	
                        <!-- fila -->
                        <%-- 
                        <div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta"><label for="item_logo_horizontal"><spring:message code='unitatadm.formulari.logotipus.horitzontal'/></label></div>
                                <div class="control">
                                    <input id="item_logo_horizontal" name="item_logo_horizontal" type="file" />
                                </div>
                            </div>
                            <div class="element t50p">
                                <div class="etiqueta"><label for="item_logo_vertical"><spring:message code='unitatadm.formulari.logotipus.vertical'/></label></div>
                                <div class="control">
                                    <input id="item_logo_vertical" name="item_logo_vertical" type="file" />
                                </div>
                                <div class="etiqueta"></div>
                            </div>
                        </div>
                        
                        <!-- /fila -->
                        <!-- fila -->
                        <div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta"><label for="item_logo_salutacio_horizontal"><spring:message code='unitatadm.formulari.logotipus.horitzontal.salutacio'/></label></div>
                                <div class="control">
                                    <input id="item_logo_salutacio_horizontal" name="item_logo_salutacio_horizontal" type="file" />
                                </div>
                            </div>
                            <div class="element t50p">
                                <div class="etiqueta"><label for="item_logo_salutacio_vertical"><spring:message code='unitatadm.formulari.logotipus.vertical.salutacio'/></label></div>
                                <div class="control">
                                    <input id="item_logo_salutacio_vertical" name="item_logo_salutacio_vertical" type="file" />
                                </div>
                            </div>
                        </div>
                        <!-- /fila -->
                        --%>
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->
            <!-- modul -->
            <div class="modul">
                <fieldset>
                    <a class="modul amagat"><spring:message code='txt.mostra'/></a>
                    <legend><spring:message code='unitatadm.formulari.fitxes'/></legend>
                    <div class="modul_continguts" style="display:block;">
                        <div class="fila">
                            <div class="element t25p">
                                <div class="etiqueta"><label for="item_nivell_1"><abbr title="Primer"><spring:message code='unitatadm.formulari.fitxes.nivell.1'/></abbr><spring:message code='unitatadm.formulari.fitxes.nivell'/></label></div>
                                <div class="control">
                                    <input id="item_nivell_1" name="item_nivell_1" type="text" class="t6 nou" />
                                </div>
                            </div>
                            <div class="element t25p">
                                <div class="etiqueta"><label for="item_nivell_2"><abbr title="Segon"><spring:message code='unitatadm.formulari.fitxes.nivell.2'/></abbr><spring:message code='unitatadm.formulari.fitxes.nivell'/></label></div>
                                <div class="control">
                                    <input id="item_nivell_2" name="item_nivell_2" type="text" class="t6 nou" />
                                </div>
                            </div>
                            <div class="element t25p">
                                <div class="etiqueta"><label for="item_nivell_3"><abbr title="Tercer"><spring:message code='unitatadm.formulari.fitxes.nivell.3'/></abbr><spring:message code='unitatadm.formulari.fitxes.nivell'/></label></div>
                                <div class="control">
                                    <input id="item_nivell_3" name="item_nivell_3" type="text" class="t6 nou" />
                                </div>
                            </div>
                            <div class="element t25p">
                                <div class="etiqueta"><label for="item_nivell_4"><spring:message code='unitatadm.formulari.fitxes.nivell.4'/></label></div>
                                <div class="control">
                                    <input id="item_nivell_4" name="item_nivell_4" type="text" class="t6 nou" />
                                </div>
                            </div>
                        </div>
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->
        </div>
        <!-- /modulPrincipal -->
        
        <!-- modulLateral -->
        <div class="modulLateral">
            <!-- modul -->
            <div class="modul publicacio">
                <fieldset>
                    <legend><spring:message code='boto.publicacio'/></legend>
                    
                      <div class="element right">
                          <div class="etiqueta">
                              <label for="item_validacio"><spring:message code='camp.validacio'/></label>
                          </div>
                          <div class="control">
                              <select id="item_validacio" name="item_validacio">
                              
                                  <c:set var="rolSuper"><rol:userIsSuper/></c:set>
                                  <c:choose>
                                      <c:when test="${rolSuper}" >
                                          <option value="1" selected="selected"><spring:message code='txt.validacio.publica'/></option>
                                          <option value="2"><spring:message code='txt.validacio.interna'/></option>
                                          <option value="3"><spring:message code='txt.validacio.reserva'/></option>                                                                                   
                                      </c:when>
                                      <c:otherwise>
                                          <option value="2" selected="selected"><spring:message code='txt.validacio.interna'/></option>
                                      </c:otherwise>
                                  </c:choose>                                                               
                              </select>
                          </div>
                    </div>
                    
                    <div class="modul_continguts mostrat">
                        <!-- botonera dalt -->
                        <div class="botonera dalt">
                          <ul>
                              <li class="btnGuardar impar">
                                  <a id="btnGuardar" href="javascript:;" class="btn guarda important"><span><span><spring:message code='boto.guarda_exclamacio'/></span></span></a>
                              </li>
                              <li class="btnPrevisualizar par">
                                  <a id="btnPrevisualizar" href="javascript:;" class="btn previsualitza"><span><span><spring:message code='boto.previsualitza'/></span></span></a>
                              </li>
                              <li class="e btnEliminar impar">
                                  <a id="btnEliminar" href="javascript:;" class="btn elimina"><span><span><spring:message code='boto.elimina'/></span></span></a>
                              </li>
                          </ul>
                        </div>
                                                
                        <!-- /botonera dalt -->
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->
            <!-- modul -->
            <div class="modul" id="modul_materies">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='unitatadm.formulari.materies'/></legend>
                    <div class="modul_continguts mostrat">
                        
                        <!-- modulMateries -->
                        <div class="modulMateries selectorChecks">
                            <div class="seleccionats">
                                <p class="info"><spring:message code='unitatadm.formulari.materies.noInfo'/></p>
                                <div class="listaOrdenable"></div>
                                <div class="btnGenerico">
                                    <a class="btn gestiona" href="javascript:;"><span><span><spring:message code='unitatadm.formulari.materies.gestiona'/></span></span></a>                 
                                </div>                          
                            </div>                          
                            <div class="llistat">                                       
                                <ul>                                                        
                                    <c:forEach items="${llistaMateries}" var="materia" varStatus="i">
                                        <c:choose>
                                            <c:when test="${(i.count) % 2 == 0}">
                                                <li class="par">
                                            </c:when>
                                            <c:otherwise>
                                               <li class="impar">
                                            </c:otherwise>
                                        </c:choose>                                     
                                          <label><span><c:out value="${materia.nom}" /></span><input type="checkbox" value="<c:out value='${materia.id}' />" /></label>
                                        </li>                                                                                                               
                                    </c:forEach>                                
                                </ul>                                           
                                <div class="botonera">
                                    <div class="btnGenerico">
                                       <a class="btn finalitza" href="javascript:;"><span><span><spring:message code='boto.finalitza'/></span></span></a>
                                    </div>
                                    <div class="btnGenerico">
                                       <a href="javascript:;" class="cancela"><span><span><spring:message code='boto.cancela'/></span></span></a>
                                    </div>
                                </div>                                      
                            </div>                                  
                        </div>
                        <!-- /modulMateries -->
                        
                    </div>                              
                </fieldset>                     
            </div>
            <!-- /modul -->                     
            <!-- modul -->
            <div class="modul" id="modul_seccions">                 
                <fieldset>                                  
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>                              
                    <legend><spring:message code='unitatadm.formulari.seccions'/></legend>                               
                    <div class="modul_continguts mostrat">                                  
                        <!-- modulSeccions -->
                        <div class="modulSeccions">                                     
                            <div class="seleccionats">                                          
                                <p class="info"><spring:message code='unitatadm.formulari.seccions.noInfo'/></p>
                                <!--   <div class="listaOrdenable"></div> -->
                                <div class="listaOrdenable"></div>
                                <div class="btnGenerico">
                                    <a class="btn gestionaSeccions" href="javascript:;"><span><span><spring:message code='unitatadm.formulari.seccions.gestiona'/></span></span></a>                 
                                </div>                                                                           
                            </div>                                  
                        </div>
                        <!-- /modulSeccions -->                                 
                    </div>                              
                </fieldset>                     
            </div>
            <!-- /modul -->                     
            <!-- modul -->
            <div class="modul" id="modul_edificis">                     
                <fieldset>                                  
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>                              
                    <legend><spring:message code='unitatadm.formulari.edificis'/></legend>                               
                    <div class="modul_continguts mostrat">                                  
                        <!-- modulEdificis -->
                        <%-- dsanchez: Clase "multilang" para listas multi-idioma --%>
                        <!--<div class="modulEdificis multilang">-->
                        <div class="modulEdificis">
                            
                            <!--
                            <ul class="idiomes">
                                <li class="introIdiomas">Idioma:</li>
                                <li class="ca seleccionat">ca</li>
                                <li class="es">es</li>
                                <li class="en">en</li>
                                <li class="de">de</li>
                                <li class="fr">fr</li>
                            </ul>-->
                            
                            <div class="seleccionats">
                                <%-- dsanchez: un solo idioma --%>
                                <div class="seleccionat">
                                    <p class="info"><spring:message code='unitatadm.formulari.edificis.noInfo'/></p>
                                    <div class="listaOrdenable"></div>
                                </div>
                                <%-- dsanchez: multiidioma --%>
                                <!--
                                <div class="seleccionat cajaIdioma ca">
                                    <p class="info"><spring:message code='unitatadm.formulari.edificis.noInfo'/></p>
                                    <div class="listaOrdenable"></div>
                                </div>
                                <div class="es cajaIdioma">
                                    <p class="info">No hay edificios.</p>
                                    <div class="listaOrdenable"></div>
                                </div>
                                <div class="en cajaIdioma">
                                    <p class="info"><spring:message code='unitatadm.formulari.edificis.noInfo'/></p>
                                    <div class="listaOrdenable"></div>
                                </div>                                
                                <div class="de cajaIdioma">
                                    <p class="info"><spring:message code='unitatadm.formulari.edificis.noInfo'/></p>
                                    <div class="listaOrdenable"></div>
                                </div>                                
                                <div class="fr cajaIdioma">
                                    <p class="info"><spring:message code='unitatadm.formulari.edificis.noInfo'/></p>
                                    <div class="listaOrdenable"></div>
                                </div>                                
                                -->
                                
                                <div class="btnGenerico">
                                    <a class="btn gestiona" href="javascript:;"><span><span><spring:message code='unitatadm.formulari.edificis.gestiona'/></span></span></a>
                                </div>
                            </div>                                  
                        </div>
                        <!-- /modulEdificis -->                                 
                    </div>                              
                </fieldset>                     
            </div>
            <!-- /modul -->                     
        </div>
        <!-- /modulLateral -->      
    </div>
    </form>
    <!-- /escriptori_detall -->
    
    <!-- escriptori_previsualitza -->
    <div id="escriptori_previsualitza">
        <h2><spring:message code='unitatadm.formulari.previsualitzaFitxa'/></h2>
        <div class="boton btnGenerico clear">
            <a href="javascript:;" class="btn torna dePrevisualitzar"><span><span><spring:message code='boto.torna'/></span></span></a>
        </div>                    
        <div class="previsualitzacio">                      
            <iframe frameborder="0" scrolling="auto"></iframe>              
        </div>              
    </div>
    <!-- /escriptori_previsualitza -->
                    
    <!-- escriptori_seccions -->
    <div id="escriptori_seccions">    
       <ul id="opcions">
            <li class="opcio C actiu"><spring:message code='unitatadm.formulari.seccions.gestio'/></li>                                 
        </ul>
        
        <div id="resultatsSeccions" class="escriptori_items_llistat">            
            <div class="resultats C actiu" style="display: block;">
                <div id="cercador" class="escriptori_items_cercador"> 
                    <div id="cercador_contingut">
                        
                        <div class="fila">                                  
                            <div class="element t18">                               
                                <div class="etiqueta"><label for="cerca_seccions_nom"><spring:message code='unitatadm.formulari.seccions.nom'/></label></div>
                                <div class="control">
                                    <input id="cerca_seccions_nom" name="cerca_seccions_nom" type="text" class="nom" />
                                </div>                                  
                            </div>                              
                        </div>
                        
                        <div class="botonera">
                            <div class="boton btnGenerico"><a id="btnLimpiarSeccionesForm" class="btn borrar" href="javascript:;"><span><span><spring:message code='boto.borrar'/></span></span></a></div>
                            <div class="boton btnGenerico"><a id="btnBuscarSeccionesForm" class="btn consulta" href="javascript:;"><span><span><spring:message code='boto.cercar'/></span></span></a></div>
                            <div class="boton btnGenerico btnVolverDetalleSecciones"><a class="btn torna" href="javascript:;"><span><span><spring:message code='boto.torna_detall'/></span></span></a></div>
                        </div>
                        
                    </div>
                    
                </div>
                   
                <div class="dades"></div>
                                       
                <input type="hidden" value="0" class="pagPagina" />
                <input type="hidden" value="DESC" class="ordreTipus" />
                <input type="hidden" value="nom" class="ordreCamp" />
                             
            </div>
        </div>        
        
        <div class="modulLateral escriptori_items_seleccionats">
        
            <div class="modul">
                
                <!--<div class="interior multilang">-->
                <div class="interior">
                    <div class="seleccionats">
                        <div class="seleccionat">
                            <p class="info"><spring:message code='unitatadm.formulari.seccions.noInfo'/></p>
                            <div class="listaOrdenable"></div>
                        </div>
                    
                        <p class="botonera btnGenerico">
                            <a id="btnFinalizarSecciones" href="javascript:;" class="btn finalitza important"><span><span><spring:message code='boto.finalitza'/></span></span></a>
                        </p>  
                    </div>    
                </div>
                
            </div>
            
        </div>
        <!-- seleccionats -->                  
    </div>
    <!-- /escriptori_seccions -->   
    
    <!-- escriptori_fitxes -->
    <div id="escriptori_fitxes">    
       <ul id="opcions">
            <li class="opcio C actiu"><spring:message code='unitatadm.formulari.seccions.fitxes.gestio'/></li>                                 
        </ul>
        
        <div id="resultatsFitxes" class="escriptori_items_llistat">            
            <div class="resultats C actiu" style="display: block;">
                <div id="cercador" class="escriptori_items_cercador"> 
                    <div id="cercador_contingut">
                        
                        <div class="fila">                                  
                            <div class="element t18">                               
                                <div class="etiqueta"><label for="cerca_fitxes_nom"><spring:message code='unitatadm.formulari.seccions.fitxes.nom'/></label></div>
                                <div class="control">
                                    <input id="cerca_fitxes_nom" name="cerca_fitxes_nom" type="text" class="nom" />
                                </div>
                            </div>
                            
                            <div class="element t21">                               
                                <div class="etiqueta"><label for="cerca_fitxes_codi"><spring:message code='unitatadm.formulari.seccions.fitxes.codi'/></label></div>
                                <div class="control">
                                    <input id="cerca_fitxes_codi" name="cerca_fitxes_codi" type="text" class="codi" />
                                </div>
                            </div>
                        </div>                        
                        
                        <div class="botonera">
                            <div class="boton btnGenerico"><a id="btnLimpiarFichasForm" class="btn borrar" href="javascript:;"><span><span><spring:message code='boto.borrar'/></span></span></a></div>
                            <div class="boton btnGenerico"><a id="btnBuscarFichasForm" class="btn consulta" href="javascript:;"><span><span><spring:message code='boto.cercar'/></span></span></a></div>
                            <div class="boton btnGenerico btnVolverDetalleFichas"><a class="btn torna" href="javascript:;"><span><span><spring:message code='boto.torna_detall'/></span></span></a></div>
                        </div>
                        
                    </div>
                    
                </div>
                   
                <div class="dades"></div>
                                       
                <input type="hidden" value="0" class="pagPagina" />
                <input type="hidden" value="DESC" class="ordreTipus" />
                <input type="hidden" value="nom" class="ordreCamp" />
                             
            </div>
        </div>        
        
        <div class="modulLateral escriptori_items_seleccionats">
        
            <div class="modul">
                
                <!--<div class="interior multilang">-->
                <div class="interior">
                    <div class="seleccionats">
                        <div class="seleccionat">
                            <p class="info"><spring:message code='unitatadm.formulari.seccions.fitxes.noInfo'/></p>
                            <div class="listaOrdenable"></div>
                        </div>
                    
                        <p class="botonera btnGenerico">
                            <a id="btnFinalizarFichas" href="javascript:;" class="btn finalitza important"><span><span><spring:message code='boto.finalitza'/></span></span></a>
                        </p>  
                    </div>    
                </div>
                
            </div>
            
        </div>
        <!-- seleccionats -->                  
    </div>
    <!-- /escriptori_fitxes -->   
                    
    <!-- escriptori_edificis -->
    <div id="escriptori_edificis">
       <ul id="opcions">
            <li class="opcio C actiu"><spring:message code='unitatadm.formulari.edificis.gestio'/></li>                                 
        </ul>
        
        <div id="resultats" class="escriptori_items_llistat">            
            <div class="resultats C actiu" style="display: block;">
                <div id="cercador" class="escriptori_items_cercador"> 
                    <div id="cercador_contingut">
                        
                        <div class="fila">                                  
                            <div class="element t18">                               
                                <div class="etiqueta"><label for="cerca_edificis_adresa"><spring:message code='unitatadm.formulari.edificis.adreca'/></label></div>
                                <div class="control">
                                    <input id="cerca_edificis_adresa" name="cerca_edificis_adresa" type="text" class="adresa" />
                                </div>                                  
                            </div>                              
                            <div class="element t7">                                
                                <div class="etiqueta"><label for="cerca_edificis_cp"><spring:message code='unitatadm.formulari.edificis.cp'/></label></div>
                                <div class="control">
                                    <input id="cerca_edificis_cp" name="cerca_edificis_cp" type="text" class="cp" />
                                </div>                                  
                            </div>                              
                            <div class="element t12">                               
                                <div class="etiqueta"><label for="cerca_edificis_poblacio"><spring:message code='unitatadm.formulari.edificis.poblacio'/></label></div>
                                <div class="control">
                                    <input id="cerca_edificis_poblacio" name="cerca_edificis_poblacio" type="text" class="poblacio" />
                                </div>                                  
                            </div>                              
                        </div>
                        
                        <div class="botonera">
                            <div class="boton btnGenerico"><a id="btnLimpiarForm" class="btn borrar" href="javascript:;"><span><span><spring:message code='boto.borrar'/></span></span></a></div>
                            <div class="boton btnGenerico"><a id="btnBuscarForm" class="btn consulta" href="javascript:;"><span><span><spring:message code='boto.cercar'/></span></span></a></div>
                            <div class="boton btnGenerico btnVolverDetalle"><a class="btn torna" href="javascript:;"><span><span><spring:message code='boto.torna_detall'/></span></span></a></div>
                        </div>
                        
                    </div>
                </div>   
                <div class="dades"></div>                       
                <input type="hidden" value="0" class="pagPagina" />
                <input type="hidden" value="DESC" class="ordreTipus" />
                <input type="hidden" value="adresa" class="ordreCamp" />             
            </div>
        </div>        
        
        <!-- seleccionats -->
        <!--<div class="escriptori_items_seleccionats">
            <div class="interior">
                <h3><spring:message code='unitatadm.formulari.edificis.selecionats'/></h3>                      
                <div class="dades">                     
                    <p class="info"><spring:message code='unitatadm.formulari.edificis.noInfo'/></p>
                </div>               
                <div class="listaOrdenable"></div>                                                
                <p class="botonera btnGenerico">
                    <a id="btnFinalizar" href="javascript:;" class="btn finalitza important"><span><span><spring:message code='boto.finalitza'/></span></span></a>
                </p>                  
            </div>
        </div>-->
        
        <div class="modulLateral escriptori_items_seleccionats">
            <div class="modul">
                
                <!--<div class="interior multilang">-->
                <div class="interior">
                
                    <!--<ul class="idiomes">
                        <li class="introIdiomas">Idioma:</li>
                        <li class="ca seleccionat">ca</li>
                        <li class="es">es</li>
                        <li class="en">en</li>
                        <li class="de">de</li>
                        <li class="fr">fr</li>
                    </ul>-->
                    
                    <div class="seleccionats">
                    
                        <div class="seleccionat">
                            <p class="info"><spring:message code='unitatadm.formulari.edificis.noInfo'/></p>
                            <div class="listaOrdenable"></div>
                        </div>
                    
                        <!--<div class="seleccionat cajaIdioma ca">
                            <p class="info"><spring:message code='unitatadm.formulari.edificis.noInfo'/></p>
                            <div class="listaOrdenable"></div>
                        </div>
                        <div class="es cajaIdioma">
                            <p class="info"><spring:message code='unitatadm.formulari.edificis.noInfo'/></p>
                            <div class="listaOrdenable"></div>
                        </div>
                        <div class="en cajaIdioma">
                            <p class="info"><spring:message code='unitatadm.formulari.edificis.noInfo'/></p>
                            <div class="listaOrdenable"></div>
                        </div>                                
                        <div class="de cajaIdioma">
                            <p class="info"><spring:message code='unitatadm.formulari.edificis.noInfo'/></p>
                            <div class="listaOrdenable"></div>
                        </div>                                
                        <div class="fr cajaIdioma">
                            <p class="info"><spring:message code='unitatadm.formulari.edificis.noInfo'/></p>
                            <div class="listaOrdenable"></div>                      
                        </div>-->
                        
                        <p class="botonera btnGenerico">
                            <a id="btnFinalizar" href="javascript:;" class="btn finalitza important"><span><span><spring:message code='boto.finalitza'/></span></span></a>
                        </p>                                    
                    </div>                                  
                </div>
            </div>
        </div>
        <!-- seleccionats -->
    
    
    <%-- 
    <div id="escriptori_edificis">              
        <h2>Gestió dels edificis relacionats</h2>                   
        <div class="botonera dalt">
            <ul>
                <li>
                    <a href="javascript:;" class="btn torna"><span><span>Torna al detall</span></span></a>
                </li>
            </ul>
        </div>                  
        <!-- llistat -->
        <div class="escriptori_items_llistat">                      
            <!-- cercador -->
            <div class="escriptori_items_cercador">                     
                <h3>Cercador</h3>                               
                <div class="fila">                                  
                    <div class="element t18">                               
                        <div class="etiqueta"><label for="cerca_edificis_adresa">Adreça</label></div>
                        <div class="control">
                            <input id="cerca_edificis_adresa" name="cerca_edificis_adresa" type="text" class="adresa" />
                        </div>                                  
                    </div>                              
                    <div class="element t7">                                
                        <div class="etiqueta"><label for="cerca_edificis_cp">Codi postal</label></div>
                        <div class="control">
                            <input id="cerca_edificis_cp" name="cerca_edificis_cp" type="text" class="cp" />
                        </div>                                  
                    </div>                              
                    <div class="element t12">                               
                        <div class="etiqueta"><label for="cerca_edificis_poblacio">Població</label></div>
                        <div class="control">
                            <input id="cerca_edificis_poblacio" name="cerca_edificis_poblacio" type="text" class="poblacio" />
                        </div>                                  
                    </div>                              
                </div>                          
                <div class="botonera">
                    <a href="javascript:;" class="btn consulta"><span><span>Cerca!</span></span></a>
                </div>                      
            </div>
            <!-- /cercador -->                      
            <div class="dades"></div>                       
            <input type="hidden" value="0" class="pagPagina" />
            <input type="hidden" value="DESC" class="ordreTipus" />
            <input type="hidden" value="adresa" class="ordreCamp" />                    
        </div>
        <!-- /llistat -->
        
        <!-- seleccionats -->
        <div class="escriptori_items_seleccionats">
            <h3>Edificis seleccionats</h3>                      
            <p class="botonera">
                <a href="javascript:;" class="btn finalitza important"><span><span>Finalitza</span></span></a>
            </p>                        
            <div class="dades">                     
                <p class="info">No hi ha cap edifici.</p>                           
            </div>                  
        </div>
        <!-- seleccionats -->
        --%>                                
    
    </div>
    <!-- /escriptori_edificis -->
    
    <script type="text/javascript" src="<c:url value='/js/tiny_mce/jquery.tinymce.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/pxem.jQuery.js'/>"></script>  
    <script type="text/javascript" src="<c:url value='/js/jquery-ui.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/jquery.ui.datepicker-ca.js'/>"></script>  
    <script type="text/javascript" src="<c:url value='/js/lista_ordenable.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/modul_materies.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/modul_seccions.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/modul_edificis.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/formulari.js'/>"></script>    
    <script type="text/javascript" src="<c:url value='/js/unitat.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/jquery.form.js'/>"></script>
    
    <script type="text/javascript">
        var noUnitat="<spring:message code='unitatadm.noUnitat'/>";
        var pagLlistat = '<c:url value="/unitatadm/unitatadm.do" />';
        
        var pagDetall = '<c:url value="/unitatadm/pagDetall.do" />';
        var pagEdificis = '<c:url value="/edificis/llistat.do" />';        
        var pagSeccions = '<c:url value="/unitatadm/llistatSeccions.do" />';
        var pagSeccionsFitxes = '<c:url value="/unitatadm/llistatFitxesUA.do" />'; 
        var pagGuardar = '<c:url value="/unitatadm/guardar.do" />';
        var pagEsborrar = '<c:url value="/unitatadm/esborrar.do" />';
        var pagArrel = '<c:url value="/" />';    
        var txtEsborrarCorrecte = "<spring:message code='unitatadm.esborrat.correcte'/>";        
		var pagFitxes = '<c:url value="/fitxainf/llistat.do" />';
        
        // texts
        var txtEspere = "<spring:message code='txt.esperi'/>";
        var txtCarregant = "<spring:message code='txt.carregant'/>";
        var txtSi = "<spring:message code='txt.si'/>";
        var txtNo = "<spring:message code='txt.no'/>";
        var txtTrobats = "<spring:message code='txt.trobats'/>";
        var txtTrobades = "<spring:message code='txt.trobades'/>";
        var txtUnitat = "Unitat";
        var txtUnitats = "Unitats";
        var txtAdministrativa = "Administrativa";
        var txtAdministratives = "Administratives";
        var txtMostrem = "Mostrem del ";
        var txtMostremAl = " al ";
        var txtNoHiHa = "<spring:message code='txt.no_hi_ha'/>";
        var txtNoHiHaUnitats = txtNoHiHa + " " + txtUnitats.toLowerCase();
        var txtNoHiHaFitxes = "Aquesta secció no té fitxes";
        var txtCarregantUnitats = txtCarregant + " " + txtUnitats + " " + txtAdministratives + ". " + txtEspere;
        var txtOrdenats = "<spring:message code='txt.ordenats'/>";
        var txtAscendentment = "<spring:message code='txt.ascendent'/>";
        var txtDescendentment = "<spring:message code='txt.descendentment'/>";
        var txtPer = "<spring:message code='txt.per'/>";
        // taula
        var txtNom = "Nom";
        var txtPare = "Pare";
        var txtCercant = "Cercant";
        var txtCercantUnitats = txtCercant + " " + txtUnitats + " " + txtAdministratives + ". " + txtEspere;
        // paginacio
        var txtTrobat = "S'ha trobat";
        var txtSeguents = "Següents";
        var txtAnteriors = "Anteriors";
        var txtInici = "Inici";
        var txtFinal = "Final";
        var txtPagines = "Pàgines";
        var txtCercant = "<spring:message code='txt.cercant'/>";    
        var txtCercantUnitatsAnteriors = txtCercant + " " + txtUnitats.toLowerCase() + " " + txtAnteriors.toLowerCase() + ". " + txtEspere;
        var txtCercantUnitatsSeguents = txtCercant + " " + txtUnitats.toLowerCase() + " " + txtSeguents.toLowerCase() + ". " + txtEspere;       
        var txtCercantElements = txtCercant + " <spring:message code='txt.elements'/>" + ". " + txtEspere;
    
        // arbre
        var txtArrel = "Arrel";
        var txtNodesFills = "Nodes fills";
        var txtCarregantNodes = txtCarregant + " nodes fills. " + txtEspere;
        
        
        // detall
        var txtCarregantDetall = txtCarregant + " detall de l "+ txtUnitat.toLowerCase() + ". " + txtEspere;
        var txtEdificiEliminar = "Està segur de voler eliminar aquesta " + txtUnitat.toLowerCase() + "?";
        var txtEnviantDades = "Enviant dades al servidor. " + txtEspere;        
        var txtMostra = "Mostra";
        var txtAmaga = "<spring:message code='txt.amaga'/>";
        var txtCaducat = "Caducat";
        // idioma
        var txtDesplega = "Desplega";
        var txtPlega = "Plega";
        // moduls
        var txtElimina = "Elimina";
        var txtHiHa = "Hi ha";
        var txtNoHiHa = "No hi ha";
        var txtItems = "Ítems";
        var txtCarregantItems = "Carregant " + txtItems.toLowerCase();
        var txtCercantItems = "Cercant " + txtItems.toLowerCase();
        var txtCercantItemsAnteriors = "Cercant " + txtItems.toLowerCase() + " " + txtAnteriors.toLowerCase() + ". " + txtEspere;
        var txtCercantItemsSeguents = "Cercant " + txtItems.toLowerCase() + " " + txtSeguents.toLowerCase() + ". " + txtEspere;
        // modul materies
        var txtMateria = "matèria";
        var txtMateries = "matèries";
        var txtNoHiHaMateries = txtNoHiHa + " " + txtMateries;
        var txtNoHiHaMateriesSeleccionades = txtNoHiHa + " " + txtMateries + " " + txtSeleccionades.toLowerCase();
        
        // modul seccions
        var txtSeccio = "secció";
        var txtSeccions = "seccions";
        var txtNoHiHaSeccions = txtNoHiHa + " " + txtSeccions;
        var txtFitxa = "fitxa";
        var txtFitxes = "fitxes";        
        var txtNoHiHaFitxes = txtNoHiHa + " " + txtFitxes;
        var txtPublicacio = "publicació";
        var txtCaducitat = "caducitat";
        var txtOrdre = "ordre";
        var txtGestioFitxes = "<spring:message code='unitatadm.formulari.fitxes.gestiona' />";
        var txtNoHiHaSeccionsSeleccionades = "No hi ha " + txtSeccions + " " + txtSeleccionades.toLowerCase();
        var txtNoHiHaFitxesSeleccionades = "No hi ha " + txtFitxes + " " + txtSeleccionades.toLowerCase();
        var txtErrorSeccionsBuides = "Les seccions a guardar han de contenir calque fitxa. Esborri les seccions sense fitxes o assigni-les al menys una";
        
        // modul edificis
        var txtEdifici = "Edifici";
        var txtEdificis = "Edificis";
        var txtAdresa = "Adreça";
        var txtCodiPostal = "Codi Postal";
        var txtPoblacio = "Població";
        var txtNoHiHaEdificis = txtNoHiHa + " " + txtEdificis.toLowerCase();
        var txtNoHiHaEdificisSeleccionats = "No hi ha " + txtEdificis.toLowerCase() + " " + txtSeleccionats.toLowerCase();
        
        // error conexio
        var txtCalcularTotal = "Calcular el total";
        var txtAjaxError = "S'ha produït un error de conexió.";
        var txtIntenteho = "Intente-ho de nou en uns minuts.";
        var txtConexionIntentar = "Intentar conexió en";
        var txtSegon = "segon";
        var txtSegons = "segons";
        var txtConectar = "Conectar ara";
        var txtFuncions = "Les funcions de l'aplicació s'han deshabilitat";
        var txtFuncionsFins = "fins que no es restableixi la comunicació";

        var txtCampObligatori = "Es un camp obligatori";

        var txtMaxim = "<spring:message code='txt.maxim'/>";
        var txtMax = "<spring:message code='txt.max'/>";
        var txtCaracters = "<spring:message code='txt.caracters'/>";
        var txtCampObligatori = "<spring:message code='txt.camp_obligatori'/>";
        var txtAnyMal = "<spring:message code='txt.any_mal'/>";
        var txtMesMal = "<spring:message code='txt.mes_mal'/>";
        var txtDiaMal = "<spring:message code='txt.dia_mal'/>";
        var txtNoEsCorrecte = "<spring:message code='txt.data_no_correcte'/>";

        var txtItemEliminar="Eliminar item";
        
    </script>
    
    <script type="text/javascript">
    <!--
       // dades formularios
        var FormulariDades = [ // OBLIGATORIS
            { // Nom en catala
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_nom_ca",
                "obligatori": "si",
                "tipus": "alfanumeric",
                "caracters":
                    {
                        "maxim": 150,
                        "mostrar": "no",
                        "abreviat": "no"
                    },
                "error":
                    {
                        "obligatori": "<spring:message code='unitatadm.formulari.nom.obligatori'/>",
                        "tipus": "<spring:message code='unitatadm.formulari.nom.no_nomes_numeros'/>"
                    }
            }, // Validacio
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_validacio",
                "obligatori": "si",
                "tipus": "numeric",         
                "error":
                    {
                        "obligatori": "<spring:message code='unitatadm.formulari.validacio.obligatori'/>"                   
                    }
            },{ // Sexe Responsable
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_responsable_sexe",
                "obligatori": "si",
                "tipus": "numeric",
                "error":
                    {
                        "obligatori": "<spring:message code='unitatadm.formulari.sexeResponsable.obligatori'/>",
                    }
            },{ // Tractament
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_tractament",
                "obligatori": "si",
                "tipus": "numeric",
                "error":
                    {
                        "obligatori": "<spring:message code='unitatadm.formulari.tractament.obligatori'/>",
                    }
            } // NO OBLIGATORIS 
            ,{ // Clau hita
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_clau_hita",
                "obligatori": "no",
                "tipus": "alfanumeric",
                "error":
                    {
                        "tipus": "<spring:message code='unitatadm.formulari.tipus.clauHita'/>",
                    }
            },{ // Codi estandar
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_codi_estandar",
                "obligatori": "no",
                "tipus": "alfanumeric",
                "error":
                    {
                        "tipus": "<spring:message code='unitatadm.formulari.tipus.codiEstandar'/>",
                    }
            },{ // Domini
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_domini",
                "obligatori": "no",
                "tipus": "alfanumeric",
                "error":
                    {
                        "tipus": "<spring:message code='unitatadm.formulari.tipus.domini'/>",
                    }
            },{ // Telefon
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_telefon",
                "obligatori": "no",
                "tipus": "numeric",
                "caracters":
                {
                    "maxim": 9,
                    "mostrar": "no",
                    "abreviat": "no"
                },
                "error":
                    {
                        "tipus": "<spring:message code='unitatadm.formulari.tipus.telefon'/>",
                    }
            },{ // Fax
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_fax",
                "obligatori": "no",
                "tipus": "numeric",
                "caracters":
                {
                    "maxim": 9,
                    "mostrar": "no",
                    "abreviat": "no"
                },
                "error":
                    {
                        "tipus": "<spring:message code='unitatadm.formulari.tipus.fax'/>",
                    }
            },{ // Email
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_email",
                "obligatori": "no",
                "tipus": "email",
                "error":
                    {
                        "tipus": "<spring:message code='unitatadm.formulari.tipus.email'/>",
                    }
            },{ // Responsable
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_responsable",
                "obligatori": "no",
                "tipus": "alfanumeric",
                "error":
                    {
                        "tipus": "<spring:message code='unitatadm.formulari.tipus.responsable'/>",
                    }
            },{ // nivell 1
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_nivell_1",
                "obligatori": "no",
                "tipus": "numeric",
                "error":
                    {
                        "tipus": "<spring:message code='unitatadm.formulari.tipus.nivell1'/>",
                    }
            },{ // nivell 2
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_nivell_2",
                "obligatori": "no",
                "tipus": "numeric",
                "error":
                    {
                        "tipus": "<spring:message code='unitatadm.formulari.tipus.nivell2'/>",
                    }
            },{ // nivell 1
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_nivell_3",
                "obligatori": "no",
                "tipus": "numeric",
                "error":
                    {
                        "tipus": "<spring:message code='unitatadm.formulari.tipus.nivell3'/>",
                    }
            },{ // nivell 1
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_nivell_4",
                "obligatori": "no",
                "tipus": "numeric",
                "error":
                    {
                        "tipus": "<spring:message code='unitatadm.formulari.tipus.nivell4'/>",
                    }
            }
        ];
    -->
    </script>    