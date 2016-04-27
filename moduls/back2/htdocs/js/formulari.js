// formulario - comprobaciones
/*
DATOS DEL FORMULARIO

"modo": individual, llistat // si és grup hi ha que ficar l'etiqueta, dada, obligatori, tipus i error de cadascun, ademés del nombre mínim i màxim del llistat
"etiqueta": id o name
"etiquetaValor": id_input
"minim": int, // opcional, si és un llistat normalment generat per name
"maxim": int, // opcional, si és un llistat normalment generat per name
"conjunt": int // quan el llistat va per id segurament serà un conjunt d'elements limitat
"obligatori": "si"
"tipus": numeric, alfanumeric, textual, email, cp, telefon
"caractersMax": int // opcional, sol anar a un textarea
"error":
		"minim": text // text si supera a la baixa el mínim
		"maxim": text // text si supera a l'alta el màxim
		"obligatori": "text,text", // si hi ha diversos elements (ex: per name) estaran separats per comes (,)
		"tipus": "text,text", // si hi ha diversos elements (ex: por name) estaran separats per comes (,)
		"suggerencia": text // text afegit a modo de consell, només una línia
*/

function FormulariComprovar(reglesValidacio) {
    var that = this;

    var reglesValidacio = reglesValidacio;
    var camp;
    
	this.formComprovacio = true;
    
    this.iniciar = function() {
    	
		$(reglesValidacio).each(function() {
		
			dada = this;
			
			// text màxim
			if (typeof dada.caracters != "undefined") {
				$("#" + dada.etiquetaValor).tamanyoMaximo(parseInt(dada.caracters.maxim,10), {mostrar: dada.caracters.mostrar, abreviat: dada.caracters.abreviat});
			}
			
			// obligatori
			codi_obligatori = " <span class=\"obligatori\" title=\"" + txtCampObligatori + "\">*</span>";
			if (dada.obligatori == "si") {
				$("#" + dada.etiquetaValor).parents("div.element:first").find("label").append(codi_obligatori);
			}
			
			// máscara data
			if (dada.tipus == "data") {
				$("#" + dada.etiquetaValor).mask("99/99/9999").validar("data");
			}
			
			// máscara cp
			if (dada.tipus == "cp") {
				$("#" + dada.etiquetaValor).mask("99999");
			}
			
			// máscara telèfon
			if (dada.tipus == "telefon") {
				$("#" + dada.etiquetaValor).mask("999999999");
			}
			
		});
		
	}
    
	this.llansar = function() {
		
		$(reglesValidacio).each(function() {
			dada = this;
			
			//Se va a añadir un campo condicional
			//Este campo sirve para cuando un dato sólo es obligatorio 
			// si otro campo está relleno.
			if (dada.condicional != null) {
				//Comprobamos si el elemento condicional esta relleno.
				if ($("#"+dada.condicional_elemento).val() == null || $("#"+dada.condicional_elemento).val() == '') {
					return;
				}
			}
			
			if (dada.modo == "individual" && dada.etiqueta == "id") {
				dada_elm = $("#" + dada.etiquetaValor);
				dada_val = dada_elm.val();
				
				if(dada.obligatori == "si" && dada_val == "") {
					that.error({error: dada.error.obligatori, camp: dada_elm});
					return false;
				}
				
				if (dada.tipus == "numeric" && dada_val != "" && isNaN(dada_val)) {
					that.error({error: dada.error.tipus, camp: dada_elm});
					return false;
				}
				
				if (dada.tipus == "email" && dada_val != "") {
					
					dada_val_email = that.email(dada_val);
					
					if (dada_val_email == "error") {
						that.error({error: dada.error.tipus, camp: dada_elm});
						return false;
					}
					
				}
				
				if (dada.tipus == "checkbox") {
					if ($("#"+dada.etiquetaValor).is(":checked") == false) {
						that.error({error: dada.error.obligatori, camp: dada_elm});
						return false;
					} 
				}
				
			} else if (dada.modo == "llistat") {
				
				et_valor = dada.etiquetaValor.split(",");
				
				if(dada.minim) {
					if ($("#"+dada.etiquetaValor) == null || $("#"+dada.etiquetaValor).find(".listaOrdenable ul li") == null || $("#"+dada.etiquetaValor).find(".listaOrdenable ul li").length < dada.minim) {
						that.error({error: dada.error.tipus, camp: dada.etiquetaValor});
						return false;
					}
					/**
					 * SE HA COMENTADO EL SIGUIENTE CÓDIGO PORQUE NO QUEDA MUY CLARO EL OBJETIVO NI TAMPOCO PARA QUE ALGO TAN COMPLEJO.
					et_num = $("form input[name=" + et_valor[0] + "]").size();
					
					if (et_num < dada.minim) {
						that.error({error: dada.error.minim});
						return false;
					}
				
					if (et_valor.length > 0) {
						
						obligatori_valor = dada.obligatori.split(",");
						tipus_valor = dada.tipus.split(",");
						
						obligatori_error_valor = dada.error.obligatori.split(",");
						tipus_error_valor = dada.error.tipus.split(",");
						
						err = false;
						
						
					
						$(et_valor).each(function(i) {
						
							et_valor_name = $.trim(this);
							
							$("form input[name=" + et_valor_name + "]").each(function() {
								
								et_valor_name_val = $(this);
								
								if ($.trim(obligatori_valor[i]) == "si" && et_valor_name_val.val() == "") {
									if (dada.error.suggerencia) {
										that.error({error: $.trim(obligatori_error_valor[i]), camp: et_valor_name_val, suggerencia: dada.error.suggerencia});
									} else {
										that.error({error: $.trim(obligatori_error_valor[i]), camp: et_valor_name_val});
									}
									err = true;
									return err;
								}
								
								if ($.trim(tipus_valor[i]) == "numeric" && et_valor_name_val.val() != "" && !isNaN(et_valor_name_val.val())) {
									that.error({error: $.trim(tipus_error_valor[i]), camp: et_valor_name_val});
									err = true;
									return err;
								}
								
							});
							
							if (err) {
								return false;
							}
						
						});
						
						if (err) {
							return false;
						
						
					}
					**/
				} else if(dada.conjunt) {
					
					dependiente_valor = dada.dependiente.split(",");
					obligatori_valor = dada.obligatori.split(",");
					tipus_valor = dada.tipus.split(",");
					
					obligatori_error_valor = dada.error.obligatori.split(",");
					tipus_error_valor = dada.error.tipus.split(",");
					
					for (i=0; i<dada.conjunt; i++) {
						
						valores = new Array(et_valor.length);
						
						for (j=0; j<et_valor.length; j++) {
							
							valor_elm = $("#" + $.trim(et_valor[j])+(i+1));
							valor_val = valor_elm.val();
							
							if ($.trim(obligatori_valor[j]) == "si" && valor_val == "" && $("#" + $.trim(dependiente_valor[j])+(i+1)).val() != "") {
								if (dada.error.suggerencia) {
									that.error({error: $.trim(obligatori_error_valor[j]), camp: valor_elm, suggerencia: dada.error.suggerencia});
								} else {
									that.error({error: $.trim(obligatori_error_valor[j]), camp: valor_elm});
								}
								err = true;
								return err;
							}
							
							if ($.trim(tipus_valor[j]) != "numeric" && valor_val != "" && !isNaN(valor_val)) {
								that.error({error: $.trim(tipus_error_valor[j]), camp: valor_elm});
								err = true;
								return err;
							}
						
						}
						
						if (err) {
							return false;
						}
						
					}
					
					if (err) {
						return false;
					}
					
				}
				
			}
		
		});
		
	}
    
    
	this.email = function(valor) {
		if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/.test(valor)){
			return "ok";
		} else {
			return "error";
		}
	}
    
    
	this.error = function(opcions) {
		// missatge
		if (opcions.camp) {
			camp = opcions.camp;
		}
		if (opcions.suggerencia) {
			Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: opcions.error, text: "<p>" + opcions.suggerencia + "</p>", funcio: that.tancar});
		} else {
			Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: opcions.error, funcio: that.tancar});
		}
		that.formComprovacio = false;
	}
    
    
	this.tancar = function() {
		Missatge.cancelar();
		that.formComprovacio = true;
		camp.focus();
	}
};


// textarea, tamany màxim
jQuery.fn.tamanyoMaximo = function(max, opcions) {
	this.each(function() {
		div_padre = $(this).parents("div.element:first");
		
		if (opcions.mostrar == "si") {
			txtMaxPlus = (opcions.abreviat == "si") ? " (<abbr title=\"" + txtMaxim + " " + max + " " + txtCaracters + "\">" + txtMax + " " + max + "</abbr>)" : " (" + txtMaxim + " " + max + " " + txtCaracters + ")";
			div_padre.find("label:first").append(txtMaxPlus);
		}
		
		
		var type = this.tagName.toLowerCase();
		var inputType = this.type? this.type.toLowerCase() : null;
		if(type == "input" && inputType == "text" || inputType == "password") {
			this.maxLength = max;
		} else if(type == "textarea") {
			this.onkeypress = function(e) {
				var ob = e || event;
				var keyCode = ob.keyCode;
				var hasSelection = document.selection? document.selection.createRange().text.length > 0 : this.selectionStart != this.selectionEnd;
				return !(this.value.length >= max && (keyCode > 50 || keyCode == 32 || keyCode == 0 || keyCode == 13) && !ob.ctrlKey && !ob.altKey && !hasSelection);
			};
			this.onkeyup = function() {
				if(this.value.length > max) {
					this.value = this.value.substring(0,max);
				}
			};
		}
	});
};


var validar_alerta = false;

jQuery.fn.validar = function(tipus) {
	this.each(function() {
		if (tipus == "data") {
			this.onblur = function(e) {
				if (!validar_alerta) {
					elm = $(this);
					valor = elm.val();
					data = valor.split("/");
					dataDia = parseInt(data[0], 10);
					dataMes = parseInt(data[1], 10);
					dataAnyo = parseInt(data[2], 10);
					txt_validar = "";
					if (dataAnyo == 0) {
						txt_validar = txtAnyMal;
					}
					if (dataMes > 12 || dataMes == 0) {
						txt_validar = txtMesMal;
					}
					if (dataDia == 0 || (dataMes == 4 || dataMes == 6 || dataMes == 9 || dataMes == 11) && dataDia > 30) {
						txt_validar = txtDiaMal;
					} else if ((dataMes == 1 || dataMes == 3 || dataMes == 5 || dataMes == 7 || dataMes == 8 || dataMes == 10 || dataMes == 12) && dataDia > 31) {
						txt_validar = txtDiaMal;
					} else if (dataMes == 2) {
						febreroDias = (dataAnyo % 4 != 0) ? 28 : 29;
						if (dataDia > febreroDias) {
							txt_validar = txtDiaMal;
						}
					}
					if (txt_validar != "") {
						validar_alerta = true; 
						txt_etiqueta = elm.parents("div.element:first").find("label:first").text();
						Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: txt_validar + " " + txt_etiqueta + " " + txtNoEsCorrecte, funcio: function() { elm.val("").focus();Missatge.cancelar();validar_alerta = false; }});
					}
				}
			}
		}
	});
};