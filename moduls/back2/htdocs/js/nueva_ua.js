// TIPUS UNITATS ADMINISTRATIVES

$(document).ready(function() {
	
	// elements
	/*opcions_elm = $("#opcions");
	escriptori_elm = $("#escriptori");
	escriptori_contingut_elm = $("#escriptori_contingut");
	
	resultats_elm = $("#resultats");
	resultats_llistat_elm = resultats_elm.find("div.L");
	*/
				
	nuevaUA_escriptori_detall_elm = $("#escritorioNuevaUA");
	nuevaUA_escriptori_previsualitza_elm = $("#escriptori_previsualitza");
			
	// INICIEM
	//Error = new CError();
	NuevaUADetall = new CNuevaUADetall(false,NuevaUAFormulariDades,{
		btnGuardar: "nuevaUA_btnGuardar",
		btnVolver: "nuevaUA_btnVolver",
		form: "nuevaUA_formGuardar"
	});
	/*Auditoria = new ModulAuditories();
	Estadistica = new ModulEstadistiques();*/
	
	NuevaUADetall.iniciar();
	
});

// detall
function CNuevaUADetall(soloFicha,reglasFormulario,ids){	
		
	this.extend = DetallBase;
	this.extend(soloFicha,reglasFormulario,ids);

	var that = this;
	this.tipusAuditoria = 'unitat';
	this.tipusEstadistica = 'unitat';

	var materias = "";
	
	this.iniciar = function() {
		
		// Evento del bot�n de volver.		
		/*jQuery("#nuevaUA_btnVolver").unbind("click").bind("click",function(){
			jQuery("#escritorioNuevaUA").hide();
			jQuery("#escritorioUnidadesHijas").show();			
		});*/
		
		//redigirimos el mÃ©todo que guarda porque en este caso tambiÃ©n hacemos un upload de archivos				
		this.guarda = this.guarda_upload;
				
		// idioma
		if (nuevaUA_escriptori_detall_elm.find("div.idiomes").size() != 0) {
			
			// Esconder todos menos el primero
			jQuery("#escritorioNuevaUA div.idioma:first-child").show();
			jQuery('#escritorioNuevaUA div.idioma:gt(0)').hide();
			
			ul_idiomes_elm = nuevaUA_escriptori_detall_elm.find("ul.idiomes:first");
									
			a_primer_elm = ul_idiomes_elm.find("a:first");
			a_primer_elm.parent().addClass("seleccionat");
			
			a_primer_elm_class = a_primer_elm.attr("class");
			a_primer_elm_text = a_primer_elm.text();
			
			a_primer_elm.parent().html("<span class=\"" + a_primer_elm_class + "\">" + a_primer_elm_text + "</span>");
			
			div_idiomes_elm = nuevaUA_escriptori_detall_elm.find("div.idiomes:first");			
			div_idiomes_elm.find("div." + a_primer_elm.attr("class")).addClass("seleccionat");
			ul_idiomes_elm.bind("click", NuevaUADetall.idioma);
			
			// Mostramos DIV con el primer idioma en la sección de idiomas del responsable de la UA.
			$('#escritorioNuevaUA div.idioma:eq(5)').show();
						
		}
		
		// moduls
		moduls_elm = escriptori_detall_elm.find("div.modul");		
        
        // Sincronizar campos sin idioma en zona multi-idioma.   
        jQuery("#nuevaUA_item_codi_estandar,#nuevaUA_item_codi_estandar_es,#nuevaUA_item_codi_estandar_en,#nuevaUA_item_codi_estandar_de,#nuevaUA_item_codi_estandar_fr").change(function(){
            jQuery("#nuevaUA_item_codi_estandar,#nuevaUA_item_codi_estandar_es,#nuevaUA_item_codi_estandar_en,#nuevaUA_item_codi_estandar_de,#nuevaUA_item_codi_estandar_fr").val( jQuery(this).val() );
        });
        
        jQuery("#nuevaUA_item_clave_primaria,#nuevaUA_item_clave_primaria_es,#nuevaUA_item_clave_primaria_en,#nuevaUA_item_clave_primaria_de,#nuevaUA_item_clave_primaria_fr").change(function(){
            jQuery("#nuevaUA_item_clave_primaria,#nuevaUA_item_clave_primaria_es,#nuevaUA_item_clave_primaria_en,#nuevaUA_item_clave_primaria_de,#nuevaUA_item_clave_primaria_fr").val( jQuery(this).val() );
        });
        
        jQuery("#nuevaUA_item_espai_territorial,#nuevaUA_item_espai_territorial_es,#nuevaUA_item_espai_territorial_en,#nuevaUA_item_espai_territorial_de,#nuevaUA_item_espai_territorial_fr").change(function(){
            jQuery("#nuevaUA_item_espai_territorial,#nuevaUA_item_espai_territorial_es,#nuevaUA_item_espai_territorial_en,#nuevaUA_item_espai_territorial_de,#nuevaUA_item_espai_territorial_fr").val( jQuery(this).val() );
        });
	}
	
	this.DetallBase_vuelve = this.vuelve;
	
	/**
     * Vuelve de la ficha al listado.
     */
    this.vuelve = function() {
        
        if( this.cambiosSinGuardar() ){
            Missatge.llansar({tipus: "confirmacio", modo: "atencio", fundit: "si", titol: txtAvisoCambiosSinGuardar, funcio: function() {
                                
                Missatge.cancelar();
                
                jQuery("#escritorioNuevaUA").hide();
                jQuery("#escritorioUnidadesHijas").show();
                
                
            }});
        }else{
            
            jQuery("#escritorioNuevaUA").hide();
            jQuery("#escritorioUnidadesHijas").show();
            
        }
    }
	
	// Sobreescribe el método guarda de detall_base, en este caso necesitamos hacer algo especial dado que hay que subir archivos
	this.guarda_upload = function(e) {    
		
		// Validamos el formulario			
		if(!that.formulariValid()){
			return false;
		}
		
		// Enviamos el formulario mediante el metodo ajaxSubmit del plugin jquery.form
		$("#nuevaUA_formGuardar").ajaxSubmit({	
			url: pagGuardar,
			dataType: 'json',
			beforeSubmit: function() {
				Missatge.llansar({tipus: "missatge", modo: "executant", fundit: "si", titol: txtEnviantDades});
			},
			success: function(data) {
							
				Llistat.cacheDatosListado = null;
				LlistatSeccioFitxes.cacheDatosListado = null;
				
				if (data.id < 0) {
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txtGenericError, text: "<p>" + data.nom + "</p>"});
				} else {
					// recarregam per actualitzar la molla de pa
                    Missatge.llansar({tipus: "alerta", modo: "correcte", fundit: "si", titol: data.nom, funcio: Detall.carregarUA } );
				}										

			}

		});

		return false;	
		
	}
	
	// M�todo para iniciar el formulario para crear una nueva UA hija.
	this.nuevaUAHija = function(){
		var nomUAPadre = "";
		var idUAPadre = null;
		
		// Obtenemos los datos de la UA padre del objeto Detalle.
		if( Detall.uaGeneral && Detall.uaGeneral.id != 0 ){
			idUAPadre = Detall.uaGeneral.id;
			
			// @todo De momento sacamos el nombre de la UA padre del texto en catal�n, pero lo suyo es que al cargar una UA, venga 
			// un campo con el nombre del idioma actual.
			nomUAPadre = Detall.uaGeneral.ca.nombre;
		}
		
		// Rellenamos el m�dulo de Relaci�n Org�nica.
		jQuery("#nuevaUA_item_pare_id").val(idUAPadre);
		jQuery("#nuevaUA_item_pare").val(nomUAPadre);
		
		this.modificado(false);
	}
	
	// M�todo sobreescrito.
	this.busca = function(){}
			
	// M�todo sobreescrito.
	this.carregar = function(itemID) {}
	this.recarregar = function() {}
	this.pintar = function(dades) {}
	this.elimina = function() {}
	this.previsualitza = function() {}
	this.carregarInici = function() { window.location.replace(pagLlistat); }
	this.carregarUA = function() { this.modificado(false); window.location.replace(pagLlistat); }
}

function posarValorsInput(idInput, valor)
{
	$(idInput).val(valor);
}