<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="rol" uri="/WEB-INF/rol.tld" %>
<c:set var="rolAdmin"><rol:userIsAdmin/></c:set>
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
    
    <c:if test="${idUA != null}">
        <ul class="submenuUA">
            <li class="detalle activo"><span></span><a href="javascript:void(0)">Detall de la UA</a></li>
            <li class="hijas"><span></span><a href="javascript:void(0)">Unitats filles</a></li>
        </ul>
    </c:if>
    
    <!-- Escritorio unidades hijas -->
    <div id="escritorioUnidadesHijas">
        
        <!--<ul class="submenuUA">
            <li class="detalle inactivo"><a href="javascript:void(0)">Detall de la UA</a></li>
            <li class="hijas activo"><span></span><strong>Unitats filles</strong></li>
        </ul>-->
        
        <ul id="opcionesUnidadesHijas" class="tabOpciones">
            <li class="opcio L actiu">
                <a id="tabListado" href="javascript:void(0)"><spring:message code='tab.llistat'/></a>
            </li>
            <li class="opcio C">
                <a id="tabBuscador" href="javascript:;"><spring:message code='tab.cercador'/></a>
            </li>
            <li id="btnNuevaUAhija" class="opcions nuevo"><a href="javascript:;" class="btn nou"><span><span>Crea una nova Unitat Administrativa</span></span></a></li>
        </ul>
        <div id="resultadosUnidadesHijas">
            <div class="resultats L actiu">
                <div class="dades">
                    <p class="executant">Carregant llistat de unitats administratives. Esperi un moment, si us plau.</p>
                </div>
                <input type="hidden" value="0" class="pagPagina" />
                <input type="hidden" value="DESC" class="ordreTipus" />
                <input type="hidden" value="id" class="ordreCamp" />
            </div>
            <div class="resultats C">
                <!-- cercador -->
                <div id="cercadorUnitatsFilles">
                    <div id="cercador_contingut_unitats_filles" class="cercador cercador_contingut">
                        <div class="opcionesBusqueda">
                            <h2><spring:message code='txt.OPCIONS_CERCA'/></h2>                    
                            <div class="fila">
                                <div class="element checkbox">                                
                                    <label for="cerca_uaFilles"><spring:message code='camp.inclouUAFilles'/></label>                                                                
                                    <input id="cerca_uaFilles" type="checkbox" name="cerca_uaFilles" value="1" />
                                </div>
                            </div>
                            <div class="fila">
                                <div class="element checkbox">                                
                                    <label for="cerca_uaMeves"><spring:message code='camp.cerca_totes_unitats'/></label>                                
                                    <input id="cerca_uaMeves" name="cerca_uaMeves" type="checkbox" value="1"/>
                                </div>
                            </div>
                            <div class="element">                        
                                <label for="cerca_estat"><spring:message code='txt.visibilitat'/></label>                            
                                <select id="cerca_estat" name="cerca_estat" class="t8">
                                    <option value="" selected="selected"><spring:message code='camp.tria.opcio'/></option>
                                    <option value="1"><spring:message code='txt.validacio.publica'/></option>
                                    <option value="2"><spring:message code='txt.validacio.interna'/></option>
                                    <option value="3"><spring:message code='txt.validacio.reserva'/></option>
                                </select>
                            </div>
                        </div>                      
                        <div class="busquedaBasica">
                            <h2><spring:message code='fitxes.llistat.cercador'/></h2>
                            <div class="fila">
                                <div class="element t25">
                                    <div class="etiqueta">
                                        <label for="cerca_codi"><spring:message code='fitxes.llistat.codi'/></label>
                                    </div>
                                    <div class="control">
                                        <input id="cerca_codi" name="cerca_codi" type="text" />
                                    </div>
                                </div>
                                <div class="element t75">
                                    <div class="etiqueta">
                                        <label for="cerca_textes"><spring:message code='fitxes.llistat.textes'/></label>
                                    </div>
                                    <div class="control">
                                        <input id="cerca_textes" name="cerca_textes" type="text"/>
                                    </div>
                                </div>      
                            </div>
                            <div class="fila">
                                <div class="element t25">
                                    <div class="etiqueta">
                                        <label for="uahija_espacioTerritorial"><spring:message code='unitatadm.formulari.espai_territorial'/></label>
                                    </div>
                                    <div class="control select">
                                        <select id="uahija_espacioTerritorial" name="espacio_territorial">
                                            <option value=""><spring:message code='camp.tria.opcio'/></option>
                                            <c:forEach items="${llistaEspaiTerritorial}" var="espaiTerritorial">
                                                <option value='<c:out value="${espaiTerritorial.id}" />'><c:out value="${espaiTerritorial.nom}" /></option>
                                            </c:forEach>
                                        </select>               
                                    </div>
                                </div>         
                                <div class="element t35">
                                    <div class="etiqueta">
                                        <label for="cerca_materia"><spring:message code='fitxes.llistat.materia'/></label>
                                    </div>
                                    <div class="control">
                                        <select id="cerca_materia" name="cerca_materia" class="t8">
                                            <option value="" selected="selected"><spring:message code='camp.tria.opcio'/></option>                                        
                                            <c:forEach items="${llistaMateries}" var="materia">
                                                <option value="<c:out value="${materia.id}"/>"><c:out value="${materia.nom}"/></option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="element t35">
                                    <div class="etiqueta">
                                        <label for="uahija_tratamiento"><spring:message code='unitatadm.formulari.responsable.tractament'/></label>
                                    </div>
                                    <div class="control select">
                                        <select id="uahija_tratamiento" name="tratamiento">
                                            <option value=""><spring:message code='camp.tria.opcio'/></option>                                          
                                            <c:forEach items="${llistaTractaments}" var="tractament">
                                                <option value='<c:out value="${tractament.id}" />'><c:out value="${tractament.nom}" /></option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                                                    
                            <div class="fila">                            
                                <div class="botonera noClear">
                                    <div class="boton btnGenerico">
                                      <a id="btnLimpiarUnidadesHijasForm" href="javascript:void(0)" class="btn borrar"><span><span><spring:message code='boto.borrar'/></span></span></a>
                                    </div>
                                    <div class="boton btnGenerico">
                                     <a id="btnBuscarUnidadesHijasForm" href="javascript:;" class="btn consulta"><span><span><spring:message code='boto.cercar'/></span></span></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /cercador -->
                <div class="dades"></div>
                <input type="hidden" value="0" class="pagPagina" /> 
                <input type="hidden" value="DESC" class="ordreTipus" /> 
                <input type="hidden" value="id" class="ordreCamp" />
            </div>
        </div>
    </div>
    <!-- /Escritorio unidades hijas --> 
    
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
                    <legend><spring:message code='txt.llegenda_dades'/></legend>
                    <div class="modul_continguts mostrat">
                        <!-- fila -->
                        <div class="fila">
                            <p class="introIdiomas"><spring:message code='txt.idioma.idioma'/></p>
                            <ul class="idiomes">
                                <c:forEach items="${idiomes_aplicacio}" var="lang">
                                <li class="idioma">
                                    <a href="javascript:;" class="<c:out value='${lang}'/>">
                                    <c:choose>
                                        <c:when test="${lang eq 'ca'}">
                                            <spring:message code='txt.idioma.ca'/>          
                                        </c:when>
                                        <c:when test="${lang eq 'es'}">
                                            <spring:message code='txt.idioma.es'/>          
                                        </c:when>
                                        <c:when test="${lang eq 'en'}">
                                            <spring:message code='txt.idioma.en'/>          
                                        </c:when>
                                        <c:when test="${lang eq 'fr'}">
                                            <spring:message code='txt.idioma.fr'/>          
                                        </c:when>
                                        <c:when test="${lang eq 'de'}">
                                            <spring:message code='txt.idioma.de'/>          
                                        </c:when>                                       
                                    </c:choose>
                                    </a>
                                </li>                               
                                </c:forEach>
                            </ul>
                            <div class="idiomes">                                                           
                                <c:forEach items="${idiomes_aplicacio}" var="lang">
                                <div class="idioma <c:out value='${lang}'/>"> 
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
                                                <label for="item_nom_<c:out value='${lang}'/>"><spring:message code='unitatadm.formulari.nom'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_nom_<c:out value='${lang}'/>" name="item_nom_<c:out value='${lang}'/>" type="text" />
                                            </div>
                                        </div>
                                        <div id="caja_item_clave_primaria" class="element t25p">
                                            <div class="etiqueta">
                                                <label for="item_clave_primaria"><spring:message code='camp.clau_primaria'/></label>                                                
                                            </div>
                                            <div class="control">
                                                <input id="item_clave_primaria" name="item_clave_primaria" type="text" readonly="readonly"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta"><label for="item_presentacio_<c:out value='${lang}'/>"><spring:message code='unitatadm.formulari.presentacio'/></label></div>
                                            <div class="control">
                                                <textarea id="item_presentacio_<c:out value='${lang}'/>" name="item_presentacio_<c:out value='${lang}'/>" style="width:100%" rows="6" class="rich complexe nou"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="fila">
                                        <div class="element t50p">
                                            <div class="etiqueta"><label for="item_abreviatura_<c:out value='${lang}'/>"><spring:message code='unitatadm.formulari.abreviatura'/></label></div>
                                            <div class="control">
                                                <input id="item_abreviatura_<c:out value='${lang}'/>" name="item_abreviatura_<c:out value='${lang}'/>" type="text" />
                                            </div>
                                        </div>
                                        <div class="element t50p">                                      
                                            <div class="etiqueta"><label for="item_url_<c:out value='${lang}'/>"><spring:message code='unitatadm.formulari.url'/></label></div>
                                            <div class="control">
                                                <input id="item_url_<c:out value='${lang}'/>" name="item_url_<c:out value='${lang}'/>" type="text" />
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
                                </div>
                                </c:forEach>
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
                            <p class="introIdiomas"><spring:message code='txt.idioma.idioma'/></p>
                            <ul class="idiomes">
                                <c:forEach items="${idiomes_aplicacio}" var="lang">
                                <li class="idioma">
                                    <a href="javascript:;" class="<c:out value='${lang}'/>">
                                    <c:choose>
                                        <c:when test="${lang eq 'ca'}">
                                            <spring:message code='txt.idioma.ca'/>          
                                        </c:when>
                                        <c:when test="${lang eq 'es'}">
                                            <spring:message code='txt.idioma.es'/>          
                                        </c:when>
                                        <c:when test="${lang eq 'en'}">
                                            <spring:message code='txt.idioma.en'/>          
                                        </c:when>
                                        <c:when test="${lang eq 'fr'}">
                                            <spring:message code='txt.idioma.fr'/>          
                                        </c:when>
                                        <c:when test="${lang eq 'de'}">
                                            <spring:message code='txt.idioma.de'/>          
                                        </c:when>                                       
                                    </c:choose>
                                    </a>
                                </li>                               
                                </c:forEach>
                            </ul>
                            <div class="idiomes">                                                           
                                <c:forEach items="${idiomes_aplicacio}" var="lang">
	                                <div class="idioma <c:out value='${lang}'/>">
	                                	<div class="fila">
	                                        <div class="element t99p">
	                                            <div class="etiqueta"><label for="item_cvResponsable_<c:out value='${lang}'/>"><spring:message code='unitatadm.formulari.cvresponsable'/></label></div>
	                                            <div class="control">
	                                                <textarea id="item_cvResponsable_<c:out value='${lang}'/>" name="item_cvResponsable_<c:out value='${lang}'/>" style="width:100%" rows="6" class="rich complexe nou"></textarea>
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
                                </c:forEach>
							</div>
                        </div>
						<!-- /fila -->
                        <!-- fila -->
                        <div class="fila">
                            <div class="element t50p campoImagen">                                       
                                <div class="thumbnail"></div>
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
                                <div class="thumbnail"></div>
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
                    <legend><spring:message code='txt.llegenda_contacte'/></legend>                              
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
                                <div class="thumbnail"></div>                            
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
                                <div class="thumbnail"></div>
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
                                <div class="thumbnail"></div>
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
                                <div class="thumbnail"></div>
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

            <!-- modul -->
            <div id="modulEstadistiques" class="modul">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='txt.ESTADISTIQUES'/></legend> 
                    <div class="modul_continguts mostrat">
                    <%-- 
                        <div class="fila">
                            <img src="/sacback2/quadreControl/grafica.do?tipoOperacion=1&id=1" width="728px" />
                        </div>
                    --%>
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->

              <c:if test="${rolAdmin}">
                <!-- modul -->
                <div id="modulAuditories" class="modul auditorias">                
                    <fieldset>
                        <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                        <legend><spring:message code='txt.AUDITORIES'/></legend>
                        <div class="modul_continguts amagat">
                           <p class="executant"><spring:message code='txt.carregant'/></p>
                         <%--
                            <table>
                                <thead>
                                    <th class="usuario"><div>USUARI</div></th>
                                    <th class="fecha"><div>DATA</div></th>
                                    <th class="operacion"><div>OPERACI�</div></th>
                                </thead>                    
                                <tbody>
                                    <tr>
                                        <td class="usuario"><div>rsanz</div></td>
                                        <td class="fecha"><div>16/01/2012</div></td>
                                        <td class="operacion"><div>Modificaci�</div></td>
                                    </tr>
                                    <tr>
                                        <td class="usuario"><div>jfernandez</div></td>
                                        <td class="fecha"><div>16/01/2012</div></td>
                                        <td class="operacion"><div>Modificaci�</div></td>
                                    </tr>
                                    <tr>
                                        <td class="usuario"><div>flopez</div></td>
                                        <td class="fecha"><div>16/01/2012</div></td>
                                        <td class="operacion"><div>Alta</div></td>
                                    </tr>
                                </tbody>
                            </table>
                            --%>
                        </div>
                    </fieldset>
                </div>
                <!-- /modul -->
          </c:if>
            
            
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
            <div class="modul modulRelacioOrganica" id="modulRelacioOrganica">
                <fieldset>
                    <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                    <legend><spring:message code='txt.RELACIO_ORGANICA'/></legend>
                    <div class="modul_continguts mostrat">
                    
                        <div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta"><label for="item_pare"><spring:message code='unitatadm.formulari.pare'/></label></div>
                                <div class="control">
                                    <input id="item_pare" class="item_pare" name="item_pare" type="text" readonly="readonly" />
                                    <input id="item_pare_id" name="item_pare_id" type="hidden" />                                                
                                </div>                                          
                            </div>            
                        </div>
                        <!-- Botonera -->                        
                        <div class="botonera">
                            <div class="boton btnGenerico" style="margin-left: 0px;">
                                <a href="javascript:carregarArbreUAExpand('<c:url value="/pantalles/popArbreUAExpandir.do"/>','popUA','item_pare_id', 'item_pare');" class="btn consulta">                                
                                    <span><span><spring:message code='boto.canviarUAPare'/></span></span>
                                </a>
                            </div>
                            <div class="boton btnGenerico borrar">
                                <a href="javascript:EliminaArbreUA('item_pare','item_pare_id');" class="btn borrar">
                                    <span><span><spring:message code='boto.borrar'/></span></span>
                                </a>
                            </div>
                        </div>                        
                        <!-- /Botonera -->
                        
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
                        
                            <input name="modulo_materias_modificado" type="hidden" value="0" />
                        
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
                            <input name="modulo_secciones_modificado" type="hidden" value="0" />
                            <div class="seleccionats">                                          
                                <p class="info"><spring:message code='unitatadm.formulari.seccions.noInfo'/></p>
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
                            
                                <input name="modulo_edificios_modificado" type="hidden" value="0" />
                            
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
                    <div id="cercador_contingut_edificis" class="cercador_contingut">
                        
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
		                        <div class="fila">
									<div class="element t75">    
		           	               		<div class="etiqueta"><label for="item_organ"><spring:message code='camp.unitat_administrativa'/></label>    </div>
		                           		<div class="control">
		                               		<input id="cerca_edificis_organ_id" name="cerca_edificis_organ_id" value='<c:out value="${idUA}" />'type="hidden" />
		                               	    <input id="cerca_edificis_organ" name="cerca_edificis_organ" value='<c:out value="${nomUA}" />'type="text" class="nou" readonly="readonly" />
		                               	</div>
		                            </div>                                                                                
				                    <div class="btnCambiar boton btnGenerico" style="margin-top:15px">
		        		                <a href="javascript:carregarArbreUA('<c:url value="/pantalles/popArbreUA.do"/>','popUA','cerca_edificis_organ_id','cerca_edificis_organ');" class="btn consulta">
		                		           	<span><span><spring:message code='boto.canviarUA'/></span></span>
		                        		</a>
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
    </div>
    <!-- /escriptori_edificis -->
    
    <!-- Escritorio nueva unidad hija -->
    <div id="escritorioNuevaUA" class="escriptori_detall">
        <form id="nuevaUA_formGuardar" action="" method="post">
            <input id="nuevaUA_item_id" name="item_id" type="hidden" value='' />       
            <p><spring:message code='txt.recordi_dades_asterisc'/> (<span class="obligatori">*</span>) <spring:message code='txt.son_obligatories'/></p>                    
            <!-- modulPrincipal -->     
            <div class="grupoModulosFormulario modulPrincipal">            
                <!-- modul -->
                <div class="modul">     
                    <fieldset>
                        <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                        <legend><spring:message code='txt.llegenda_dades'/></legend>
                        <div class="modul_continguts mostrat">
                            <!-- fila -->
                            <div class="fila">
                                <p class="introIdiomas"><spring:message code='txt.idioma.idioma'/></p>
                                <ul class="idiomes">
                                    <c:forEach items="${idiomes_aplicacio}" var="lang">
                                    <li class="idioma">
                                        <a href="javascript:;" class="<c:out value='${lang}'/>">
                                        <c:choose>
                                            <c:when test="${lang eq 'ca'}">
                                                <spring:message code='txt.idioma.ca'/>          
                                            </c:when>
                                            <c:when test="${lang eq 'es'}">
                                                <spring:message code='txt.idioma.es'/>          
                                            </c:when>
                                            <c:when test="${lang eq 'en'}">
                                                <spring:message code='txt.idioma.en'/>          
                                            </c:when>
                                            <c:when test="${lang eq 'fr'}">
                                                <spring:message code='txt.idioma.fr'/>          
                                            </c:when>
                                            <c:when test="${lang eq 'de'}">
                                                <spring:message code='txt.idioma.de'/>          
                                            </c:when>                                       
                                        </c:choose>
                                        </a>
                                    </li>                               
                                    </c:forEach>
                                </ul>
                                <div class="idiomes">                                                           
                                    <c:forEach items="${idiomes_aplicacio}" var="lang">
                                    <div class="idioma <c:out value='${lang}'/>"> 
                                        <div class="fila">
                                            <div class="element t50p">                                      
                                                <div class="etiqueta"><label for="nuevaUA_item_codi_estandar"><spring:message code='unitatadm.formulari.codi_estandar'/></label></div>
                                                <div class="control">
                                                    <input id="nuevaUA_item_codi_estandar" name="item_codi_estandar" type="text" />
                                                </div>                                          
                                            </div>                                      
                                        </div>
                                        <div class="fila">
                                            <div class="element t75p">
                                                <div class="etiqueta">
                                                    <label for="nuevaUA_item_nom_<c:out value='${lang}'/>"><spring:message code='unitatadm.formulari.nom'/></label>
                                                </div>
                                                <div class="control">
                                                    <input id="nuevaUA_item_nom_<c:out value='${lang}'/>" name="item_nom_<c:out value='${lang}'/>" type="text" />
                                                </div>
                                            </div>
                                            <%--<div id="nuevaUA_caja_item_clave_primaria" class="element t25p">
                                                <div class="etiqueta">
                                                    <label for="nuevaUA_item_clave_primaria"><spring:message code='camp.clau_primaria'/></label>                                                
                                                </div>
                                                <div class="control">
                                                    <input id="nuevaUA_item_clave_primaria" name="item_clave_primaria" type="text" readonly="readonly"/>
                                                </div>
                                            </div>--%>
                                        </div>
                                        <div class="fila">
                                            <div class="element t99p">
                                                <div class="etiqueta"><label for="nuevaUA_item_presentacio_<c:out value='${lang}'/>"><spring:message code='unitatadm.formulari.presentacio'/></label></div>
                                                <div class="control">
                                                    <textarea id="nuevaUA_item_presentacio_<c:out value='${lang}'/>" name="item_presentacio_<c:out value='${lang}'/>" style="width:100%" rows="6" class="rich complexe nou"></textarea>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="fila">
                                            <div class="element t50p">
                                                <div class="etiqueta"><label for="nuevaUA_item_abreviatura_<c:out value='${lang}'/>"><spring:message code='unitatadm.formulari.abreviatura'/></label></div>
                                                <div class="control">
                                                    <input id="nuevaUA_item_abreviatura_<c:out value='${lang}'/>" name="item_abreviatura_<c:out value='${lang}'/>" type="text" />
                                                </div>
                                            </div>
                                            <div class="element t50p">                                      
                                                <div class="etiqueta"><label for="nuevaUA_item_url_<c:out value='${lang}'/>"><spring:message code='unitatadm.formulari.url'/></label></div>
                                                <div class="control">
                                                    <input id="nuevaUA_item_url_<c:out value='${lang}'/>" name="item_url_<c:out value='${lang}'/>" type="text" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="fila">
                                            <div class="element t50p">                                      
                                                <div class="etiqueta"><label for="nuevaUA_item_espai_territorial"><spring:message code='unitatadm.formulari.espai_territorial'/></label></div>
                                                <div class="control select">                                                
                                                    <select id="nuevaUA_item_espai_territorial" name="item_espai_territorial">
                                                        <option value=""><spring:message code='txt.escolliu_opcio'/></option>
                                                        <c:forEach items="${llistaEspaiTerritorial}" var="espaiTerritorial">
                                                            <option value='<c:out value="${espaiTerritorial.id}" />'><c:out value="${espaiTerritorial.nom}" /></option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>                                          
                                        </div>      
                                    </div>
                                    </c:forEach>
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
                                    <div class="etiqueta"><label for="nuevaUA_item_responsable"><spring:message code='unitatadm.formulari.responsable.nom'/></label></div>
                                    <div class="control">
                                        <input id="nuevaUA_item_responsable" name="item_responsable" type="text" />
                                    </div>  
                                </div>
                                <div class="element t50p">
                                    <div class="etiqueta">
                                        <label for="nuevaUA_item_responsable_sexe"><spring:message code='unitatadm.formulari.responsable.sexe'/></label>                                        
                                    </div>
                                    <div class="control select">
                                        <select id="nuevaUA_item_responsable_sexe" name="item_responsable_sexe">
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
	                            <p class="introIdiomas"><spring:message code='txt.idioma.idioma'/></p>
	                            <ul class="idiomes">
	                                <c:forEach items="${idiomes_aplicacio}" var="lang">
	                                <li class="idioma">
	                                    <a href="javascript:;" class="<c:out value='${lang}'/>">
	                                    <c:choose>
	                                        <c:when test="${lang eq 'ca'}">
	                                            <spring:message code='txt.idioma.ca'/>          
	                                        </c:when>
	                                        <c:when test="${lang eq 'es'}">
	                                            <spring:message code='txt.idioma.es'/>          
	                                        </c:when>
	                                        <c:when test="${lang eq 'en'}">
	                                            <spring:message code='txt.idioma.en'/>          
	                                        </c:when>
	                                        <c:when test="${lang eq 'fr'}">
	                                            <spring:message code='txt.idioma.fr'/>          
	                                        </c:when>
	                                        <c:when test="${lang eq 'de'}">
	                                            <spring:message code='txt.idioma.de'/>          
	                                        </c:when>                                       
	                                    </c:choose>
	                                    </a>
	                                </li>                               
	                                </c:forEach>
	                            </ul>
	                            <div class="idiomes">                                                           
	                                <c:forEach items="${idiomes_aplicacio}" var="lang">
		                                <div class="idioma <c:out value='${lang}'/>">
		                                	<div class="fila">
		                                        <div class="element t99p">
		                                            <div class="etiqueta"><label for="nuevaUA_item_cvResponsable_<c:out value='${lang}'/>"><spring:message code='unitatadm.formulari.cvresponsable'/></label></div>
		                                            <div class="control">
		                                                <textarea id="nuevaUA_item_cvResponsable_<c:out value='${lang}'/>" name="item_cvResponsable_<c:out value='${lang}'/>" style="width:100%" rows="6" class="rich complexe nou"></textarea>
		                                            </div>
		                                        </div>
		                                    </div>
		                                </div>
	                                </c:forEach>
								</div>
	                        </div>
							<!-- /fila -->
                            <!-- fila -->
                            <div class="fila">
                                <div class="element t50p campoImagen">                                       
                                    <div class="thumbnail"></div>
                                    <div class="etiqueta"><label for="nuevaUA_item_responsable_foto_petita"><spring:message code='unitatadm.formulari.responsable.foto.petita'/></label></div>
                                    <div class="control archivo">                                                                           
                                        <div id="nuevaUA_grup_item_responsable_foto_petita" class="file">                                        
                                            <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                            <a href="#" target="_blank"></a>
                                            <input type="checkbox" name="item_responsable_foto_petita_delete" id="nuevaUA_item_responsable_foto_petita_delete" value="1"/>
                                            <label for="nuevaUA_item_responsable_foto_petita_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                        </div>
                                    </div>                                
                                </div>                              
                                <div class="element t50p">
                                    <div class="etiqueta"><label for="nuevaUA_item_responsable_foto_petita"><spring:message code='unitatadm.formulari.responsable.foto.petita'/></label></div>
                                    <div class="control">                                           
                                        <input id="nuevaUA_item_responsable_foto_petita" name="item_responsable_foto_petita" type="file" class="nou" />
                                    </div>
                                </div>                                                                                      
                            </div>
                            <!-- /fila -->
                            <!-- fila -->
                            <div class="fila">
                                <div class="element t50p campoImagen">
                                    <div class="thumbnail"></div>
                                    <div class="etiqueta"><label for="nuevaUA_item_responsable_foto_gran"><spring:message code='unitatadm.formulari.responsable.foto.gran'/></label></div>
                                    <div class="control archivo">   
                                        <div id="nuevaUA_grup_item_responsable_foto_gran" class="file">
                                            <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                            <a href="#" target="_blank"></a>
                                            <input type="checkbox" name="item_responsable_foto_gran_delete" id="nuevaUA_item_responsable_foto_gran_delete" value="1"/>
                                            <label for="nuevaUA_item_responsable_foto_gran_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                        </div>
                                    </div>
                                </div>    
                                
                                <div class="element t50p">
                                    <div class="etiqueta"><label for="nuevaUA_item_responsable_foto_gran"><spring:message code='unitatadm.formulari.responsable.foto.gran'/></label></div>
                                    <div class="control">                                           
                                        <input id="nuevaUA_item_responsable_foto_gran" name="item_responsable_foto_gran" type="file" class="nou" />
                                    </div>
                                </div>                                                                                      
                            </div>
                            <!-- /fila -->
                            <!-- fila -->
                            <div class="fila">
                                <div class="element t50p">
                                    <div class="etiqueta">
                                        <label for="nuevaUA_item_tractament"><spring:message code='unitatadm.formulari.responsable.tractament'/></label>                                    
                                    </div>
                                    <div class="control select">
                                        <select id="nuevaUA_item_tractament" name="item_tractament">
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
                        <legend><spring:message code='txt.llegenda_contacte'/></legend>                              
                        <div class="modul_continguts" style="display:block;">                               
                        
                            <!-- fila -->
                            <div class="fila">                              
                                <div class="element t50p">                                  
                                    <div class="etiqueta"><label for="nuevaUA_item_telefon"><spring:message code='unitatadm.formulari.telefon'/></label></div>
                                    <div class="control">
                                        <input id="nuevaUA_item_telefon" name="item_telefon" type="text" />
                                    </div>                                      
                                </div>  
                                <div class="element t50p">                                      
                                    <div class="etiqueta"><label for="nuevaUA_item_fax"><spring:message code='unitatadm.formulari.fax'/></label></div>
                                    <div class="control">
                                        <input id="nuevaUA_item_fax" name="item_fax" type="text" />
                                    </div>                                          
                                </div>                                      
                            </div>
                            <!-- /fila -->     
                            
                            <!-- fila -->
                            <div class="fila">
                                <div class="element t50p">                                      
                                    <div class="etiqueta"><label for="nuevaUA_item_email"><spring:message code='unitatadm.formulari.email'/></label></div>
                                    <div class="control">
                                        <input id="nuevaUA_item_email" name="item_email" type="text" />
                                    </div>                                          
                                </div>
                                <div class="element t50p">                                      
                                    <div class="etiqueta"><label for="item_domini"><spring:message code='unitatadm.formulari.domini'/></label></div>
                                    <div class="control">
                                        <input id="nuevaUA_item_domini" name="item_domini" type="text" />
                                    </div>                                          
                                </div>
                            </div>
                            <!-- /fila -->
                        
                            <!-- fila -->
                            <%--<div class="fila">                                  
                                <div class="element t50p">                                      
                                    <div class="etiqueta"><label for="nuevaUA_item_clau_hita"><spring:message code='unitatadm.formulari.clau_hita'/></label></div>
                                    <div class="control">
                                        <input id="nuevaUA_item_clau_hita" name="item_clau_hita" type="text" />
                                    </div>                                          
                                </div>
                                <div class="element t50p">                                      
                                    <div class="etiqueta"><label for="nuevaUA_item_codi_estandar"><spring:message code='unitatadm.formulari.codi_estandar'/></label></div>
                                    <div class="control">
                                        <input id="nuevaUA_item_codi_estandar" name="item_codi_estandar" type="text" />
                                    </div>                                          
                                </div>
                            </div>--%>
                            <!-- /fila -->                                  
                            <!-- fila -->
                            <%--<div class="fila">                                      
                                <div class="element t50p">                                      
                                    <div class="etiqueta"><label for="nuevaUA_item_domini"><spring:message code='unitatadm.formulari.domini'/></label></div>
                                    <div class="control">
                                        <input id="nuevaUA_item_domini" name="item_domini" type="text" />
                                    </div>                                          
                                </div>
                                <div class="element t50p">
                                    <div class="etiqueta">
                                        <label for="nuevaUA_item_validacio"><spring:message code='unitatadm.formulari.validacio'/></label>
                                        
                                    </div>
                                    <div class="control select">
                                        <select id="nuevaUA_item_validacio" name="item_validacio">
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
                                    <div class="etiqueta"><label for="nuevaUA_item_email"><spring:message code='unitatadm.formulari.email'/></label></div>
                                    <div class="control">
                                        <input id="nuevaUA_item_email" name="item_email" type="text" />
                                    </div>                                          
                                </div>                            
                                <div class="element t50p">                                      
                                    <div class="etiqueta"><label for="nuevaUA_item_espai_territorial"><spring:message code='unitatadm.formulari.espai_territorial'/></label></div>
                                    <div class="control select">                                    
                                        <select id="nuevaUA_item_espai_territorial" name="item_espai_territorial">
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
                                    <div class="thumbnail"></div>                            
                                    <div class="etiqueta"><label for="nuevaUA_item_logo_horizontal"><spring:message code='unitatadm.formulari.logotipus.horitzontal'/></label></div>
                                    <div class="control archivo">   
                                        <div id="nuevaUA_grup_item_logo_horizontal" class="file">
                                            <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                            <a href="#" target="_blank"></a>
                                            <input type="checkbox" name="item_logo_horizontal_delete" id="nuevaUA_item_logo_horizontal_delete" value="1"/>
                                            <label for="nuevaUA_item_logo_horizontal_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                        </div>
                                    </div>
                                </div>                                
                                
                                <div class="element t50p">
                                    <div class="etiqueta"><label for="nuevaUA_item_logo_horizontal"><spring:message code='unitatadm.formulari.logotipus.horitzontal'/></label></div>
                                    <div class="control">
                                        <input id="nuevaUA_item_logo_horizontal" name="item_logo_horizontal" type="file" class="nou" />
                                    </div>
                                </div>                                                                                      
                            </div>
                            <!-- /fila -->
                            <!-- fila -->
                            <div class="fila">
                                <div class="element t50p campoImagen">                                       
                                    <div class="thumbnail"></div>
                                    <div class="etiqueta"><label for="nuevaUA_item_logo_vertical"><spring:message code='unitatadm.formulari.logotipus.vertical'/></label></div>
                                    <div class="control archivo">   
                                        <div id="nuevaUA_grup_item_logo_vertical" class="file">
                                            <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                            <a href="#" target="_blank"></a>
                                            <input type="checkbox" name="item_logo_vertical_delete" id="nuevaUA_item_logo_vertical_delete" value="1"/>
                                            <label for="nuevaUA_item_logo_vertical_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                        </div>
                                    </div>
                                </div>    
                                
                                <div class="element t50p">
                                    <div class="etiqueta"><label for="nuevaUA_item_logo_vertical"><spring:message code='unitatadm.formulari.logotipus.vertical'/></label></div>
                                    <div class="control">
                                        <input id="nuevaUA_item_logo_vertical" name="item_logo_vertical" type="file" class="nou" />
                                    </div>
                                </div>                                                                                      
                            </div>
                            <!-- /fila -->
                            <!-- fila -->
                            <div class="fila">
                                <div class="element t50p campoImagen">                                       
                                    <div class="thumbnail"></div>
                                    <div class="etiqueta"><label for="nuevaUA_item_logo_salutacio_horizontal"><spring:message code='unitatadm.formulari.logotipus.horitzontal.salutacio'/></label></div>
                                    <div class="control archivo">   
                                        <div id="nuevaUA_grup_item_logo_salutacio_horizontal" class="file">
                                            <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                            <a href="#" target="_blank"></a>
                                            <input type="checkbox" name="item_logo_salutacio_horizontal_delete" id="nuevaUA_item_logo_salutacio_horizontal_delete" value="1"/>
                                            <label for="nuevaUA_item_logo_salutacio_horizontal_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                        </div>
                                    </div>
                                </div>    
                                                            
                                <div class="element t50p">
                                    <div class="etiqueta"><label for="nuevaUA_item_logo_salutacio_horizontal"><spring:message code='unitatadm.formulari.logotipus.horitzontal.salutacio'/></label></div>
                                    <div class="control">
                                        <input id="nuevaUA_item_logo_salutacio_horizontal" name="item_logo_salutacio_horizontal" type="file" class="nou" />
                                    </div>
                                </div>                                                                                      
                            </div>
                            <!-- /fila -->
                            <!-- fila -->
                            <div class="fila">
                                <div class="element t50p campoImagen">                                       
                                    <div class="thumbnail"></div>
                                    <div class="etiqueta"><label for="nuevaUA_item_logo_salutacio_vertical"><spring:message code='unitatadm.formulari.logotipus.vertical.salutacio'/></label></div>
                                    <div class="control archivo">   
                                        <div id="nuevaUA_grup_item_logo_salutacio_vertical" class="file">
                                            <span><spring:message code='txt.no_arxiu_assignat'/></span>
                                            <a href="#" target="_blank"></a>
                                            <input type="checkbox" name="item_logo_salutacio_vertical_delete" id="nuevaUA_item_logo_salutacio_vertical_delete" value="1"/>
                                            <label for="nuevaUA_item_logo_salutacio_vertical_delete" class="eliminar"><spring:message code='boto.elimina'/></label>
                                        </div>
                                    </div>
                                </div>    
                                <div class="element t50p">
                                    <div class="etiqueta"><label for="nuevaUA_item_logo_salutacio_vertical"><spring:message code='unitatadm.formulari.logotipus.vertical.salutacio'/></label></div>
                                    <div class="control">
                                        <input id="nuevaUA_item_logo_salutacio_vertical" name="item_logo_salutacio_vertical" type="file" class="nou" />
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
                        <legend><spring:message code='unitatadm.formulari.fitxes'/></legend>
                        <div class="modul_continguts" style="display:block;">
                            <div class="fila">
                                <div class="element t25p">
                                    <div class="etiqueta"><label for="item_nivell_1"><abbr title="Primer"><spring:message code='unitatadm.formulari.fitxes.nivell.1'/></abbr><spring:message code='unitatadm.formulari.fitxes.nivell'/></label></div>
                                    <div class="control">
                                        <input id="nuevaUA_item_nivell_1" name="item_nivell_1" type="text" class="t6 nou" />
                                    </div>
                                </div>
                                <div class="element t25p">
                                    <div class="etiqueta"><label for="item_nivell_2"><abbr title="Segon"><spring:message code='unitatadm.formulari.fitxes.nivell.2'/></abbr><spring:message code='unitatadm.formulari.fitxes.nivell'/></label></div>
                                    <div class="control">
                                        <input id="nuevaUA_item_nivell_2" name="item_nivell_2" type="text" class="t6 nou" />
                                    </div>
                                </div>
                                <div class="element t25p">
                                    <div class="etiqueta"><label for="item_nivell_3"><abbr title="Tercer"><spring:message code='unitatadm.formulari.fitxes.nivell.3'/></abbr><spring:message code='unitatadm.formulari.fitxes.nivell'/></label></div>
                                    <div class="control">
                                        <input id="nuevaUA_item_nivell_3" name="item_nivell_3" type="text" class="t6 nou" />
                                    </div>
                                </div>
                                <div class="element t25p">
                                    <div class="etiqueta"><label for="item_nivell_4"><spring:message code='unitatadm.formulari.fitxes.nivell.4'/></label></div>
                                    <div class="control">
                                        <input id="nuevaUA_item_nivell_4" name="item_nivell_4" type="text" class="t6 nou" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                </div>
                <!-- /modul -->
    
                <!-- modul -->
                <div id="modulEstadistiques" class="modul">
                    <fieldset>
                        <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                        <legend><spring:message code='txt.ESTADISTIQUES'/></legend> 
                        <div class="modul_continguts mostrat"></div>
                    </fieldset>
                </div>
                <!-- /modul -->
    
                <c:if test="${rolAdmin}">
                    <!-- modul -->
                    <div id="modulAuditories" class="modul auditorias">                
                        <fieldset>
                            <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                            <legend><spring:message code='txt.AUDITORIES'/></legend>
                            <div class="modul_continguts amagat">
                               <p class="executant"><spring:message code='txt.carregant'/></p>
                            </div>
                        </fieldset>
                    </div>
                    <!-- /modul -->
                </c:if>
                
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
                                  <label for="nuevaUA_item_validacio"><spring:message code='camp.validacio'/></label>
                              </div>
                              <div class="control">
                                  <select id="nuevaUA_item_validacio" name="item_validacio">
                                  
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
                                      <a id="nuevaUA_btnGuardar" href="javascript:;" class="btn guarda important"><span><span><spring:message code='boto.guarda_exclamacio'/></span></span></a>
                                  </li>
                                  <li class="btnVolver par">
                                      <a id="nuevaUA_btnVolver" href="javascript:;" class="btn volver"><span><span><spring:message code='boto.torna'/></span></span></a>
                                  </li>
                                  <!--<li class="btnPrevisualizar par">
                                      <a id="nuevaUA_btnPrevisualizar" href="javascript:;" class="btn previsualitza"><span><span><spring:message code='boto.previsualitza'/></span></span></a>
                                  </li>
                                  <li class="e btnEliminar impar">
                                      <a id="nuevaUA_btnEliminar" href="javascript:;" class="btn elimina"><span><span><spring:message code='boto.elimina'/></span></span></a>
                                  </li>-->
                              </ul>
                            </div>
                            <!-- /botonera dalt -->
                        </div>
                    </fieldset>
                </div>
                <!-- /modul -->
                
                <!-- modul -->
                <div id="nuevaUA_modulRelacioOrganica" class="modul modulRelacioOrganica">
                    <fieldset>
                        <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                        <legend><spring:message code='txt.RELACIO_ORGANICA'/></legend>
                        <div class="modul_continguts mostrat">
                        
                            <div class="fila">
                                <div class="element t50p">
                                    <div class="etiqueta"><label for="nuevaUA_item_pare"><spring:message code='unitatadm.formulari.pare'/></label></div>
                                    <div class="control">
                                        <input id="nuevaUA_item_pare" class="item_pare" name="item_pare" type="text" readonly="readonly" />
                                        <input id="nuevaUA_item_pare_id" name="item_pare_id" type="hidden" />                                                
                                    </div>                                          
                                </div>            
                            </div>
                            <!-- Botonera -->                        
                            <div class="botonera">
                                <div class="boton btnGenerico" style="margin-left: 0px;">
                                    <a href="javascript:carregarArbreUAExpand('<c:url value="/pantalles/popArbreUAExpandir.do"/>','popUA','nuevaUA_item_pare_id', 'nuevaUA_item_pare');" class="btn consulta">                                
                                        <span><span><spring:message code='boto.canviarUAPare'/></span></span>
                                    </a>
                                </div>
                                <div class="boton btnGenerico borrar">
                                    <a href="javascript:EliminaArbreUA('nuevaUA_item_pare','nuevaUA_item_pare_id');" class="btn borrar">
                                        <span><span><spring:message code='boto.borrar'/></span></span>
                                    </a>
                                </div>
                            </div>                        
                            <!-- /Botonera -->
                            
                        </div>
                    </fieldset>
                </div>
                <!-- /modul -->
                
                <!-- modul -->
                <%--
                <div class="modul" id="nuevaUA_modul_materies">
                    <fieldset>
                        <a class="modul mostrat"><spring:message code='txt.amaga'/></a>
                        <legend><spring:message code='unitatadm.formulari.materies'/></legend>
                        <div class="modul_continguts mostrat">
                                                                            
                            <!-- modulMateries -->
                            <div class="modulMateries selectorChecks">
                            
                                <input name="modulo_materias_modificado" type="hidden" value="0" />
                            
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
                <div class="modul" id="nuevaUA_modul_seccions">                 
                    <fieldset>                                  
                        <a class="modul mostrat"><spring:message code='txt.amaga'/></a>                              
                        <legend><spring:message code='unitatadm.formulari.seccions'/></legend>                               
                        <div class="modul_continguts mostrat">                                  
                            <!-- modulSeccions -->
                            <div class="modulSeccions">                                     
                                <input name="modulo_secciones_modificado" type="hidden" value="0" />
                                <div class="seleccionats">                                          
                                    <p class="info"><spring:message code='unitatadm.formulari.seccions.noInfo'/></p>
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
                <div class="modul" id="nuevaUA_modul_edificis">                     
                    <fieldset>                                  
                        <a class="modul mostrat"><spring:message code='txt.amaga'/></a>                              
                        <legend><spring:message code='unitatadm.formulari.edificis'/></legend>                               
                        <div class="modul_continguts mostrat">                                  
                            <!-- modulEdificis -->
                            <div class="modulEdificis">
                                <div class="seleccionats">
                                    <input name="modulo_edificios_modificado" type="hidden" value="0" />
                                    <div class="seleccionat">
                                        <p class="info"><spring:message code='unitatadm.formulari.edificis.noInfo'/></p>
                                        <div class="listaOrdenable"></div>
                                    </div>
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
                --%>
                                    
            </div>
            <!-- /modulLateral -->      
        </div>
        </form>
    </div>
    <!-- /Escritorio nueva unidad hija -->
    
    <script type="text/javascript" src="<c:url value='/js/tiny_mce/jquery.tinymce.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/pxem.jQuery.js'/>"></script>  
    <script type="text/javascript" src="<c:url value='/js/jquery-ui.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/jquery.ui.datepicker-ca.js'/>"></script>  
    <script type="text/javascript" src="<c:url value='/js/lista_ordenable.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/unidades_hijas.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/modul_materies.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/modul_seccions.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/modul_edificis.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/formulari.js'/>"></script>    
    <script type="text/javascript" src="<c:url value='/js/unitat.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/nueva_ua.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/jquery.form.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/modul_auditories.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/modul_estadistiques.js'/>"></script>
    
    <script type="text/javascript">
        var noUnitat="<spring:message code='unitatadm.noUnitat'/>";
        var pagLlistat = '<c:url value="/unitatadm/unitatadm.do" />';
        var pagLlistat2 = '<c:url value="/unidadAdministrativa/cambiarUA.do" />';
        
        var pagDetall = '<c:url value="/unitatadm/pagDetall.do" />';
        var pagEdificis = '<c:url value="/edificis/llistat.do" />';        
        var pagSeccions = '<c:url value="/unitatadm/llistatSeccions.do" />';
        var pagSeccionsFitxes = '<c:url value="/unitatadm/llistatFitxesUA.do" />'; 
        var pagGuardar = '<c:url value="/unitatadm/guardar.do" />';
        var pagEsborrar = '<c:url value="/unitatadm/esborrar.do" />';
        var pagAuditories = '<c:url value="/auditories/llistat.do" />';
        var pagEstadistiques = '<c:url value="/estadistiques/grafica.do" />';
        var pagArrel = '<c:url value="/" />';    
        var txtEsborrarCorrecte = "<spring:message code='unitatadm.esborrat.correcte'/>";        
        var pagFitxes = '<c:url value="/fitxainf/llistat.do" />';
        
        // dsanchez: URL para ordenar el listado de UA hijas. 
        var pagOrdenarUAHijas = '<c:url value="/unitatadm/reordenarUAs.do" />';
        
        var urlPrevisualizarUA = '<c:out value="${urlPrevisualitzacio}"/>';
        var urlListaUAs = '<c:url value="/unitatadm/llistat.do" />';
        
        // texts
        var txtEspere = "<spring:message code='txt.esperi'/>";
        var txtCarregant = "<spring:message code='txt.carregant'/>";
        var txtSi = "<spring:message code='txt.si'/>";
        var txtNo = "<spring:message code='txt.no'/>";
        var txtTrobats = "<spring:message code='txt.trobats'/>";
        var txtTrobades = "<spring:message code='txt.trobades'/>";
        var txtUnitat = "<spring:message code='txt.unitat'/>";
        var txtUnitats = "<spring:message code='txt.unitats'/>";
        var txtAdministrativa = "<spring:message code='txt.administrativa'/>";
        var txtAdministratives = "<spring:message code='txt.administratives'/>";
        var txtMostrem = "<spring:message code='txt.mostrem'/> <spring:message code='txt.del'/> ";
        var txtMostremAl = " <spring:message code='txt.al'/> ";
        var txtNoHiHa = "<spring:message code='txt.no_hi_ha'/>";
        var txtNoHiHaUnitats = txtNoHiHa + " " + txtUnitats.toLowerCase();
        var txtNoHiHaFitxes = "<spring:message code='txt.seccio_sense_fitxes'/>";
        var txtCarregantUnitats = txtCarregant + " " + txtUnitats + " " + txtAdministratives + ". " + txtEspere;
        var txtOrdenats = "<spring:message code='txt.ordenats'/>";
        var txtAscendentment = "<spring:message code='txt.ascendent'/>";
        var txtDescendentment = "<spring:message code='txt.descendentment'/>";
        var txtPer = "<spring:message code='txt.per'/>";
        // taula
        var txtNom = "<spring:message code='txt.nom'/>";
        var txtPare = "<spring:message code='txt.pare'/>";
        var txtCercant = "<spring:message code='txt.cercant'/>";
        var txtCercantUnitats = txtCercant + " " + txtUnitats + " " + txtAdministratives + ". " + txtEspere;
        // paginacio
        var txtTrobat = "<spring:message code='txt.sha_trobat'/>";
        var txtSeguents = "<spring:message code='txt.seguents'/>";
        var txtAnteriors = "<spring:message code='txt.anteriors'/>";
        var txtInici = "<spring:message code='txt.inici'/>";
        var txtFinal = "<spring:message code='txt.final'/>";
        var txtPagines = "<spring:message code='txt.pagines'/>";
        var txtCercant = "<spring:message code='txt.cercant'/>";    
        var txtCercantUnitatsAnteriors = txtCercant + " " + txtUnitats.toLowerCase() + " " + txtAnteriors.toLowerCase() + ". " + txtEspere;
        var txtCercantUnitatsSeguents = txtCercant + " " + txtUnitats.toLowerCase() + " " + txtSeguents.toLowerCase() + ". " + txtEspere;
        var txtCercantAnteriors = txtCercantUnitatsAnteriors;
        var txtCercantSeguents = txtCercantUnitatsSeguents;
               
        var txtCercantElements = txtCercant + " <spring:message code='txt.elements'/>" + ". " + txtEspere;
    
        // arbre
        var txtArrel = "<spring:message code='txt.arrel'/>";
        var txtNodesFills = "<spring:message code='txt.nodes_fills.titol'/>";
        var txtCarregantNodes = txtCarregant + " <spring:message code='txt.nodes_fills_dot'/> " + txtEspere;
        
        
        // detall
        var txtCarregantDetall = txtCarregant + " detall de l "+ txtUnitat.toLowerCase() + ". " + txtEspere;
        var txtEdificiEliminar = "<spring:message code='txt.segur_eliminar_aquesta'/> " + txtUnitat.toLowerCase() + "?";
        var txtEnviantDades = "<spring:message code='txt.enviant_dades_servidor'/> " + txtEspere;
        var txtMostra = "<spring:message code='txt.mostra'/>";
        var txtAmaga = "<spring:message code='txt.amaga'/>";
        var txtCaducat = "<spring:message code='txt.caducat'/>";
        // idioma
        var txtDesplega = "<spring:message code='txt.desplega'/>";
        var txtPlega = "<spring:message code='txt.plega'/>";
        // moduls
        var txtElimina = "<spring:message code='txt.elimina'/>";
        var txtHiHa = "<spring:message code='txt.hi_ha'/>";
        var txtNoHiHa = "<spring:message code='txt.no_hi_ha'/>";
        var txtItems = "<spring:message code='txt.items'/>";
        var txtCarregantItems = "<spring:message code='txt.carregant'/> " + txtItems.toLowerCase();
        var txtCercantItems = "<spring:message code='txt.cercant'/> " + txtItems.toLowerCase();
        var txtCercantItemsAnteriors = "<spring:message code='txt.cercant'/> " + txtItems.toLowerCase() + " " + txtAnteriors.toLowerCase() + ". " + txtEspere;
        var txtCercantItemsSeguents = "<spring:message code='txt.cercant'/> " + txtItems.toLowerCase() + " " + txtSeguents.toLowerCase() + ". " + txtEspere;
        // modul materies
        var txtMateria = "<spring:message code='txt.materia'/>";
        var txtMateries = "<spring:message code='txt.materies'/>";
        var txtNoHiHaMateries = txtNoHiHa + " " + txtMateries;
        var txtNoHiHaMateriesSeleccionades = txtNoHiHa + " " + txtMateries + " " + txtSeleccionades.toLowerCase();
        
        // modul seccions
        var txtSeccio = "<spring:message code='txt.seccio_lower'/>";
        var txtSeccions = "<spring:message code='txt.seccions_lower'/>";
        var txtNoHiHaSeccions = txtNoHiHa + " " + txtSeccions;
        var txtFitxa = "<spring:message code='txt.fitxa_lower'/>";
        var txtFitxes = "<spring:message code='txt.fitxes_lower'/>";
        var txtNoHiHaFitxes = txtNoHiHa + " " + txtFitxes;
        var txtPublicacio = "<spring:message code='txt.publicacio_lower'/>";
        var txtCaducitat = "<spring:message code='txt.caducitat_lower'/>";
        var txtOrdre = "<spring:message code='camp.ordre_lower'/>";
        var txtGestioFitxes = "<spring:message code='unitatadm.formulari.fitxes.gestiona'/>";
        var txtNoHiHaSeccionsSeleccionades = txtNoHiHa + " " + txtSeccions + " " + txtSeleccionades.toLowerCase();
        var txtNoHiHaFitxesSeleccionades = txtNoHiHa + " " + txtFitxes + " " + txtSeleccionades.toLowerCase();
        var txtErrorSeccionsBuides = "<spring:message code='unitatadm.formulari.error.seccions_buides'/>";
        
        // modul edificis
        var txtEdifici = "<spring:message code='txt.edifici'/>";
        var txtEdificis = "<spring:message code='txt.edificis'/>";
        var txtAdresa = "<spring:message code='unitatadm.formulari.edificis.adreca'/>";
        var txtCodiPostal = "<spring:message code='unitatadm.formulari.edificis.cp'/>";
        var txtPoblacio = "<spring:message code='unitatadm.formulari.edificis.poblacio'/>";
        var txtNoHiHaEdificis = txtNoHiHa + " " + txtEdificis.toLowerCase();
        var txtNoHiHaEdificisSeleccionats = txtNoHiHa + " " + txtEdificis.toLowerCase() + " " + txtSeleccionats.toLowerCase();
        
        // error conexio
        var txtCalcularTotal = "<spring:message code='error.conexio.calcular_total'/>";
        var txtAjaxError = "<spring:message code='error.conexio.ajax'/>";
        var txtIntenteho = "<spring:message code='error.conexio.intentar_de_nou'/>";
        var txtConexionIntentar = "<spring:message code='error.conexio.intentar_en'/>";
        var txtSegon = "<spring:message code='error.conexio.segon'/>";
        var txtSegons = "<spring:message code='error.conexio.segons'/>";
        var txtConectar = "<spring:message code='error.conexio.conectar_ara'/>";
        var txtFuncions = "<spring:message code='error.conexio.funcions'/>";
        var txtFuncionsFins = "<spring:message code='error.conexio.fins'/>";

        var txtCampObligatori = "<spring:message code='txt.es_un_camp_obligatori'/>";

        var txtMaxim = "<spring:message code='txt.maxim'/>";
        var txtMax = "<spring:message code='txt.max'/>";
        var txtCaracters = "<spring:message code='txt.caracters'/>";
        var txtCampObligatori = "<spring:message code='txt.camp_obligatori'/>";
        var txtAnyMal = "<spring:message code='txt.any_mal'/>";
        var txtMesMal = "<spring:message code='txt.mes_mal'/>";
        var txtDiaMal = "<spring:message code='txt.dia_mal'/>";
        var txtNoEsCorrecte = "<spring:message code='txt.data_no_correcte'/>";

        var txtItemEliminar= "<spring:message code='txt.eliminar'/> <spring:message code='txt.item'/>";
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
    
    <script type="text/javascript">
    <!--
       // Formulario de nueva unidad administrativa.
        var NuevaUAFormulariDades = [ // OBLIGATORIS
            { // Nom en catala
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "nuevaUA_item_nom_ca",
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
                "etiquetaValor": "nuevaUA_item_validacio",
                "obligatori": "si",
                "tipus": "numeric",         
                "error":
                    {
                        "obligatori": "<spring:message code='unitatadm.formulari.validacio.obligatori'/>"                   
                    }
            },{ // Sexe Responsable
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "nuevaUA_item_responsable_sexe",
                "obligatori": "si",
                "tipus": "numeric",
                "error":
                    {
                        "obligatori": "<spring:message code='unitatadm.formulari.sexeResponsable.obligatori'/>",
                    }
            },{ // Tractament
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "nuevaUA_item_tractament",
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
                "etiquetaValor": "nuevaUA_item_clau_hita",
                "obligatori": "no",
                "tipus": "alfanumeric",
                "error":
                    {
                        "tipus": "<spring:message code='unitatadm.formulari.tipus.clauHita'/>",
                    }
            },{ // Codi estandar
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "nuevaUA_item_codi_estandar",
                "obligatori": "no",
                "tipus": "alfanumeric",
                "error":
                    {
                        "tipus": "<spring:message code='unitatadm.formulari.tipus.codiEstandar'/>",
                    }
            },{ // Domini
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "nuevaUA_item_domini",
                "obligatori": "no",
                "tipus": "alfanumeric",
                "error":
                    {
                        "tipus": "<spring:message code='unitatadm.formulari.tipus.domini'/>",
                    }
            },{ // Telefon
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "nuevaUA_item_telefon",
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
                "etiquetaValor": "nuevaUA_item_fax",
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
                "etiquetaValor": "nuevaUA_item_email",
                "obligatori": "no",
                "tipus": "email",
                "error":
                    {
                        "tipus": "<spring:message code='unitatadm.formulari.tipus.email'/>",
                    }
            },{ // Responsable
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "nuevaUA_item_responsable",
                "obligatori": "no",
                "tipus": "alfanumeric",
                "error":
                    {
                        "tipus": "<spring:message code='unitatadm.formulari.tipus.responsable'/>",
                    }
            },{ // nivell 1
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "nuevaUA_item_nivell_1",
                "obligatori": "no",
                "tipus": "numeric",
                "error":
                    {
                        "tipus": "<spring:message code='unitatadm.formulari.tipus.nivell1'/>",
                    }
            },{ // nivell 2
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "nuevaUA_item_nivell_2",
                "obligatori": "no",
                "tipus": "numeric",
                "error":
                    {
                        "tipus": "<spring:message code='unitatadm.formulari.tipus.nivell2'/>",
                    }
            },{ // nivell 1
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "nuevaUA_item_nivell_3",
                "obligatori": "no",
                "tipus": "numeric",
                "error":
                    {
                        "tipus": "<spring:message code='unitatadm.formulari.tipus.nivell3'/>",
                    }
            },{ // nivell 1
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "nuevaUA_item_nivell_4",
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