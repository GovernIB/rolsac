<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

    <link href="<c:url value='/css/unitat.css'/>" rel="stylesheet" type="text/css" media="screen" />
    <link href="<c:url value='/css/modul_seccions.css'/>" rel="stylesheet" type="text/css" media="screen" />
    <link href="<c:url value='/css/modul_edificis.css'/>" rel="stylesheet" type="text/css" media="screen" />    
    
    <div id="carregantDetall">
        <p class="executant"><spring:message code='unitatadm.carregant_unitatadm'/></p>
    </div>          
    <!-- escriptori_detall -->
    <div id="escriptori_detall">
        <form id="formGuardar" action="false">
        <input id="item_id" name="item_id" type="hidden" value='<c:out value="${idUA}" />' />       
        <p><spring:message code='txt.recordi_dades_asterisc'/> (<span class="obligatori">*</span>) <spring:message code='txt.son_obligatories'/></p>                    
        <!-- modulPrincipal -->     
        <div id="modulPrincipal" class="grupoModulosFormulario">            
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
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_nom_ca"><spring:message code='unitatadm.formulari.nom'/></label>
                                            </div>
                                            <div class="control">
                                                <input id="item_nom_ca" name="item_nom_ca" type="text" />
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
                                </div>
                                <!-- /ca -->
                                <!-- es -->
                                <div class="idioma es">
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta"><label for="item_nom_es"><spring:message code='unitatadm.formulari.nom'/></label></div>
                                            <div class="control">
                                                <input id="item_nom_es" name="item_nom_es" type="text" />
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
                                </div>
                                <!-- /es -->
                                <!-- en -->
                                <div class="idioma en">
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta">
                                                <label for="item_nom_en"><spring:message code='unitatadm.formulari.nom'/></label>
                                                <span class="obligatori" title="Camp obligatori">*</span>
                                            </div>
                                            <div class="control">
                                                <input id="item_nom_en" name="item_nom_en" type="text" />
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
                                </div>
                                <!-- /en -->
                                <!-- de -->
                                <div class="idioma de">
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta"><label for="item_nom_de"><spring:message code='unitatadm.formulari.nom'/></label></div>
                                            <div class="control">
                                                <input id="item_nom_de" name="item_nom_de" type="text" />
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
                                </div>
                                <!-- /de -->
                                <!-- fr -->
                                <div class="idioma fr">
                                    <div class="fila">
                                        <div class="element t99p">
                                            <div class="etiqueta"><label for="item_nom_fr"><spring:message code='unitatadm.formulari.nom'/></label></div>
                                            <div class="control">
                                                <input id="item_nom_fr" name="item_nom_fr" type="text" />
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
                    <legend><spring:message code='unitatadm.formulari.configuracio_gestio'/>Configuració / Gestió</legend>                              
                    <div class="modul_continguts" style="display:block;">                               
                        <!-- fila -->
                        <div class="fila">                                  
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
                        </div>
                        <!-- /fila -->                                  
                        <!-- fila -->
                        <div class="fila">                                      
                            <div class="element t50p">                                      
                                <div class="etiqueta"><label for="item_domini"><spring:message code='unitatadm.formulari.domini'/></label></div>
                                <div class="control">
                                    <input id="item_domini" name="item_domini" type="text" />
                                </div>                                          
                            </div>
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_validacio"><spring:message code='unitatadm.formulari.validacio'/></label>
                                    <span class="obligatori" title="Camp obligatori">*</span>
                                </div>
                                <div class="control select">
                                    <select id="item_validacio" name="item_validacio">
                                        <option value="" selected="selected"><spring:message code='txt.escolliu_opcio'/></option>
                                        <option value="1"><spring:message code='unitatadm.formulari.validacio.publica'/></option>
                                        <option value="2"><spring:message code='unitatadm.formulari.validacio.interna'/></option>
                                    </select>
                                </div>
                            </div>                                      
                        </div>
                        <!-- /fila -->                                  
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
                                <div class="etiqueta"><label for="item_espai_territorial"><spring:message code='unitatadm.formulari.espai_territorial'/></label></div>
                                <div class="control select">
                                    <!-- <input id="item_espai_territorial" name="item_espai_territorial" type="text" /-->
                                    <select id="item_espai_territorial" name="item_espai_territorial">
                                        <option value=""><spring:message code='txt.escolliu_opcio'/></option>
                                        <c:forEach items="${llistaEspaiTerritorial}" var="espaiTerritorial">
                                            <option value='<c:out value="${espaiTerritorial.id}" />'><c:out value="${espaiTerritorial.nom}" /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>  
                        </div>
                        <!-- /fila -->
                        <!-- fila -->
                        <div class="fila">                                  
                            <div class="element t50p">                                          
                                <input id="item_pare_id" name="item_pare_id" type="hidden" />                                           
                                <div class="etiqueta"><label for="item_pare"><spring:message code='unitatadm.formulari.pare'/></label></div>
                                <div class="control">
                                <%-- ? if ($_SESSION['rolsac_rol'] == "RSC_SUPER") { ? --%>
                                    <input id="item_pare" name="item_pare" type="text" readonly="readonly" />
                                    <input id="id_item_pare" name="id_item_pare" type="hidden" readonly="readonly" />
                                    <%-- <input type="button" onclick="ArbreUA()" value="Canviar UA pare"/> --%>
                                <%-- ? } else { ?>
                                    <input id="item_pare" name="item_pare" type="text" />
                                <? } ? --%>
                                </div>                                          
                            </div>                                                                          
                        </div>
                        <!-- /fila -->
                        <!-- Botonera -->
                        <div id="cercador">
                            <div class="botonera" style="margin-top: 0px; float:left;">
                                <div class="boton btnGenerico" style="margin-left: 0px;">
                                    <a href="javascript:ArbreUA();" class="btn consulta">
                                    <span><span><spring:message code='boto.canviarUAPare'/></span></span>
                                    </a>
                                </div>
                                <div class="boton btnGenerico">
                                    <a href="javascript:EliminaArbreUA();" class="btn borrar">
                                    <span><span><spring:message code='boto.borrar'/></span></span>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <!-- /Botonera -->                              
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
                                    <span class="obligatori" title="Camp obligatori">*</span>
                                </div>
                                <div class="control select">
                                    <select id="item_responsable_sexe" name="item_responsable_sexe">
                                        <option value=""><spring:message code='unitatadm.formulari.responsable.sexe'/><spring:message code='txt.escolliu_opcio'/></option>
                                        <option value="1"><spring:message code='txt.sexe.masculi'/></option>
                                        <option value="2"><spring:message code='txt.sexe.femeni'/></option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <!-- /fila -->
                        <!-- fila -->
                        <div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta"><label for="item_responsable_foto_petita"><spring:message code='unitatadm.formulari.responsable.foto.petita'/></label></div>
                                <div class="control">
                                    <input id="item_responsable_foto_petita" name="item_responsable_foto_petita" type="file" />
                                </div>
                                <div class="etiqueta"></div>
                                <div class="control">
                                    <input id="item_responsable_foto_petita_nom" name="item_responsable_foto_petita_nom" type="text" readonly="readonly" />
                                </div>
                            </div>
                            <div class="element t50p">
                                <div class="etiqueta"><label for="item_responsable_foto_gran"><spring:message code='unitatadm.formulari.responsable.foto.gran'/></label></div>
                                <div class="control">
                                    <input id="item_responsable_foto_gran" name="item_responsable_foto_gran" type="file" />
                                </div>
                                <div class="etiqueta"></div>
                                <div class="control">
                                    <input id="item_responsable_foto_gran_nom" name="item_responsable_foto_gran_nom" type="text" readonly="readonly" />
                                </div>
                            </div>
                        </div>
                        <!-- /fila -->
                        <!-- fila -->
                        <div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta">
                                    <label for="item_tractament"><spring:message code='unitatadm.formulari.responsable.tractament'/></label>
                                    <span class="obligatori" title="Camp obligatori">*</span>
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
                    <legend>Logotipus</legend>
                    <div class="modul_continguts" style="display:block;">
                        <!-- fila -->
                        <div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta"><label for="item_logo_horizontal">Logo horizontal</label></div>
                                <div class="control">
                                    <input id="item_logo_horizontal" name="item_logo_horizontal" type="file" />
                                </div>
                                <div class="etiqueta"></div>
                                <div class="control">
                                    <input id="item_logo_horizontal_nom" name="item_logo_horizontal_nom" type="text" readonly="readonly" />
                                </div>
                            </div>
                            <div class="element t50p">
                                <div class="etiqueta"><label for="item_logo_vertical">Logo vertical</label></div>
                                <div class="control">
                                    <input id="item_logo_vertical" name="item_logo_vertical" type="file" />
                                </div>
                                <div class="etiqueta"></div>
                                <div class="control">
                                    <input id="item_logo_vertical_nom" name="item_logo_vertical_nom" type="text" />
                                </div>
                            </div>
                        </div>
                        <!-- /fila -->
                        <!-- fila -->
                        <div class="fila">
                            <div class="element t50p">
                                <div class="etiqueta"><label for="item_logo_salutacio_horizontal">Logo salutacio horizontal</label></div>
                                <div class="control">
                                    <input id="item_logo_salutacio_horizontal" name="item_logo_salutacio_horizontal" type="file" />
                                </div>
                                <div class="etiqueta"></div>
                                <div class="control">
                                    <input id="item_logo_salutacio_horizontal_nom" name="item_logo_salutacio_horizontal_nom" type="text" />
                                </div>
                            </div>
                            <div class="element t50p">
                                <div class="etiqueta"><label for="item_logo_salutacio_vertical">Logo salutacio vertical</label></div>
                                <div class="control">
                                    <input id="item_logo_salutacio_vertical" name="item_logo_salutacio_vertical" type="file" />
                                </div>
                                <div class="etiqueta"></div>
                                <div class="control">
                                    <input id="item_logo_salutacio_vertical_nom" name="item_logo_salutacio_vertical_nom" type="text" />
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
                    <legend>Fitxes de la portada web</legend>
                    <div class="modul_continguts" style="display:block;">
                        <div class="fila">
                            <div class="element t25p">
                                <div class="etiqueta"><label for="item_nivell_1"><abbr title="Primer">1er</abbr> nivell</label></div>
                                <div class="control">
                                    <input id="item_nivell_1" name="item_nivell_1" type="text" class="t6 nou" />
                                </div>
                            </div>
                            <div class="element t25p">
                                <div class="etiqueta"><label for="item_nivell_2"><abbr title="Segon">2on</abbr> nivell</label></div>
                                <div class="control">
                                    <input id="item_nivell_2" name="item_nivell_2" type="text" class="t6 nou" />
                                </div>
                            </div>
                            <div class="element t25p">
                                <div class="etiqueta"><label for="item_nivell_3"><abbr title="Tercer">3er</abbr> nivell</label></div>
                                <div class="control">
                                    <input id="item_nivell_3" name="item_nivell_3" type="text" class="t6 nou" />
                                </div>
                            </div>
                            <div class="element t25p">
                                <div class="etiqueta"><label for="item_nivell_4">Nivell llistat</label></div>
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
        <div id="modulLateral">
            <!-- modul -->
            <div class="modul publicacio">
                <fieldset>
                    <legend>Publicació</legend>
                    <div class="modul_continguts mostrat">
                        <!-- botonera dalt -->
                        <div class="botonera dalt">
                          <ul>
                              <li class="btnGuardar impar">
                                  <a id="btnGuardar" href="javascript:;" class="btn guarda important"><span><span>Guarda!</span></span></a>
                              </li>
                              <li class="btnPrevisualizar par">
                                  <a id="btnPrevisualizar" href="javascript:;" class="btn previsualitza"><span><span>Previsualitza</span></span></a>
                              </li>
                          </ul>
                        </div>
                                                
                        <!-- /botonera dalt -->
                    </div>
                </fieldset>
            </div>
            <!-- /modul -->
            <!-- modul -->
            <div class="modul">
                <fieldset>
                    <a class="modul mostrat">Amaga</a>
                    <legend>Matèries relacionades</legend>
                    <div class="modul_continguts mostrat">
                        
                        <!-- modulMateries -->
                        <div class="modulMateries selectorChecks">
                            <div class="seleccionats">
                                <p class="info">No hi ha matèries.</p>
                                <div class="listaOrdenable"></div>
                                <div class="btnGenerico">
                                    <a class="btn gestiona" href="javascript:;"><span><span>Gestiona matèries</span></span></a>                 
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
                                       <a class="btn finalitza" href="javascript:;"><span><span>Finalitza</span></span></a>
                                    </div>
                                    <div class="btnGenerico">
                                       <a href="javascript:;" class="cancela"><span><span>Cancel·la</span></span></a>
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
            <div class="modul">                 
                <fieldset>                                  
                    <a class="modul mostrat">Amaga</a>                              
                    <legend>Seccions</legend>                               
                    <div class="modul_continguts mostrat">                                  
                        <!-- modulEdificis -->
                        <div class="modulSeccions">                                     
                            <div class="seleccionats">                                          
                                <p class="info">No hi ha seccions.</p>                              
                            </div>                                  
                        </div>
                        <!-- /modulEdificis -->                                 
                    </div>                              
                </fieldset>                     
            </div>
            <!-- /modul -->                     
            <!-- modul -->
            <div class="modul">                     
                <fieldset>                                  
                    <a class="modul mostrat">Amaga</a>                              
                    <legend>Edificis relacionats</legend>                               
                    <div class="modul_continguts mostrat">                                  
                        <!-- modulEdificis -->
                        <div class="modulEdificis">                                 
                            <div class="seleccionats">                                          
                                <p class="info">No hi ha edificis.</p>
                                <div class="listaOrdenable"></div>
                                <!--
                                <p class="info">Hi ha <strong>3 edificis</strong> relacionats.</p>                                          
                                <div class="listaOrdenable">
                                    <ul>
                                        <li><input type="hidden" value="1" />Edifici 1</li>
                                        <li><input type="hidden" value="2" />Edifici 2</li>
                                        <li><input type="hidden" value="3" />Edifici 3</li>
                                    </ul>
                                </div>
                                -->                             
                                <div class="btnGenerico">
                                    <a class="btn gestiona" href="javascript:;"><span><span>Gestiona edificis</span></span></a>
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
        <h2>Previsualitzant la fitxa</h2>
        <p>
            <a href="javascript:;" class="btn torna dePrevisualitzar"><span><span>Torna</span></span></a>
        </p>                    
        <div class="previsualitzacio">                      
            <iframe frameborder="0" scrolling="auto"></iframe>              
        </div>              
    </div>
    <!-- /escriptori_previsualitza -->
                    
    <!-- escriptori_seccions -->
    <div id="escriptori_seccions">              
        <h2>Seccions de la unitat</h2>                  
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
                        <div class="etiqueta"><label for="cerca_fixta_titol">Títol</label></div>
                        <div class="control">
                            <input id="cerca_fixta_titol" name="cerca_fixta_titol" type="text" class="adresa" />
                        </div>                                  
                    </div>                              
                    <div class="element t7">                                
                        <div class="etiqueta"><label for="cerca_fixta_codi">Codi</label></div>
                        <div class="control">
                            <input id="cerca_fixta_codi" name="cerca_fixta_codi" type="text" class="cp" />
                        </div>                                  
                    </div>                              
                    <div class="element t23">                               
                        <div class="etiqueta"><label class="cerca_fixta_seccio">Secció</label></div>
                        <div class="control">
                            <!--<input id="cerca_fixta_seccion" name="cerca_fixta_seccion" type="text" class="poblacio" />-->
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
            <input type="hidden" value="publicacio" class="ordreCamp" />                    
        </div>
        <!-- /llistat -->                   
        <div class="botonera baix">
            <ul>
                <li>
                    <a href="javascript:;" class="btn torna"><span><span>Torna al detall</span></span></a>
                </li>
            </ul>
        </div>              
    </div>
    <!-- /escriptori_seccions -->   
                
    <!-- escriptori_edificis -->
    <div id="escriptori_edificis">
       <ul id="opcions">
            <li class="opcio C actiu">Gestió dels edificis relacionats</li>                                 
        </ul>
        
        <div id="resultats" class="escriptori_items_llistat">            
            <div class="resultats C actiu" style="display: block;">
                <div id="cercador" class="escriptori_items_cercador"> 
                    <div id="cercador_contingut">
                        
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
                            <div class="boton btnGenerico"><a id="btnLimpiarForm" class="btn borrar" href="javascript:;"><span><span>Borrar</span></span></a></div>
                            <div class="boton btnGenerico"><a id="btnBuscarForm" class="btn consulta" href="javascript:;"><span><span>Cercar</span></span></a></div>
                            <div class="boton btnGenerico btnVolverDetalle"><a class="btn torna" href="javascript:;"><span><span>Torna al detall</span></span></a></div>
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
        <div class="escriptori_items_seleccionats">
            <div class="interior">
                <h3>Edificis seleccionats</h3>                      
                <div class="dades">                     
                    <p class="info">No hi ha cap edifici.</p>                           
                </div>
                
                <div class="listaOrdenable"></div>
                
                <!--  dsanchez: La lista debería crearse aquí, pero parece que se crea como hija directa de "escriptori_items_seleccionats" -->
                
                <p class="botonera btnGenerico">
                    <a id="btnFinalizar" href="javascript:;" class="btn finalitza important"><span><span>Finalitza</span></span></a>
                </p>                  
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
    <script type="text/javascript" src="<c:url value='/js/modul_materies.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/modul_seccions.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/modul_edificis.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/formulari.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/comuns.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/js/unitat.js'/>"></script>
    
    <script type="text/javascript">

        var pagDetall = '<c:url value="/unitatadm/pagDetall.htm" />';
        var pagEdificis = '<c:url value="/edificis/llistat.htm" />';
        var pagGuardar = '<c:url value="/unitatadm/guardar.htm" />';
        var pagEsborrar = '<c:url value="/unitatadm/esborrar.htm" />';
    
        // texts
        var txtEspere = "Espere un moment, si us plau.";
        var txtCarregant = "Carregant";
        var txtSi = "Sí";
        var txtNo = "No";
        var txtTrobats = "Trobats";
        var txtUnitat = "Unitat";
        var txtUnitats = "Unitats";
        var txtAdministrativa = "Administrativa";
        var txtAdministratives = "Administratives";
        var txtMostrem = "Mostrem del ";
        var txtMostremAl = " al ";
        var txtNoHiHa = "No hi ha";
        var txtNoHiHaUnitats = txtNoHiHa + " " + txtUnitats.toLowerCase();
        var txtCarregantUnitats = txtCarregant + " " + txtUnitats + " " + txtAdministratives + ". " + txtEspere;
        var txtOrdenats = "ordenats";
        var txtAscendentment = "ascendentment";
        var txtDescendentment = "descendentment";
        var txtPer = "per";
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
        var txtAmaga = "Amaga";
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
        // modul seccions
        var txtSeccio = "secció";
        var txtSeccions = "seccions";
        var txtNoHiHaSeccions = txtNoHiHa + " " + txtSeccions;
        var txtFitxa = "fitxa";
        var txtFitxes = "fitxes";
        var txtPublicacio = "publicació";
        var txtCaducitat = "caducitat";
        var txtOrdre = "ordre";
        // modul edificis
        var txtEdifici = "Edifici";
        var txtEdificis = "Edificis";
        var txtAdresa = "Adreça";
        var txtCodiPostal = "Codi Postal";
        var txtPoblacio = "Població";
        var txtNoHiHaEdificis = txtNoHiHa + " " + txtEdificis.toLowerCase();
        var txtSeleccionat = "Seleccionat";
        var txtSeleccionats = "Seleccionats";
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
        
    </script>
    
    <script type="text/javascript">
    <!--
       // dades formularios
        var FormulariDades = [
            {
                "modo": "individual",
                "etiqueta": "id",
                "etiquetaValor": "item_nom_ca",
                "obligatori": "si",
                "tipus": "alfanumeric",
                "caracters":
                    {
                        "maxim": 150,
                        "mostrar": "si",
                        "abreviat": "no"
                    },
                "error":
                    {
                        "obligatori": "<spring:message code='unitatadm.formulari.nom.obligatori'/>",
                        "tipus": "<spring:message code='unitatadm.formulari.nom.no_nomes_numeros'/>"
                    }
            }            
        ];
    -->
    </script>