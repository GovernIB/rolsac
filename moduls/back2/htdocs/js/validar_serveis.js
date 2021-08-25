// valida los campos obligatorios del servicio
function validarServeis(){

	//comprobamos que sea servicio
	if (typeof servIncomplet == 'undefined') {
		return true;
	}
	//comprobamos que sea publico
	   if ($("#item_estat").val() != 1) {
		   return true;
	   }


		//comprobacion fecha
		var fechaServ = $("#item_data_publicacion").val();
		if(fechaServ==""){

			Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: servIncomplet+" "+servPublFecha, text: ""});
			return false;
		}

		//comprobacion datos
		var nomServ = $("#item_nom_ca").val();
		if(nomServ==""){

			Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: servIncomplet+" "+servNom, text: ""});
			return false;
		}

		var objecteServ = $("#item_objeto_ca").val();
		if(objecteServ==""){

			Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: servIncomplet+" "+servObjecte, text: ""});
			return false;
		}

		var destServ = $("#item_destinatarios_ca").val();
		if(destServ==""){

			Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: servIncomplet+" "+servDest, text: ""});
			return false;
		}
		var organRespServ = $("#item_organ_instructor").val();
		if(organRespServ==""){

			Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: servIncomplet+" "+servOrganRespon, text: ""});
			return false;
		}



		// comprobacion datos de contacto
		var itemRespServ = $("#item_servei_responsable").val();
		if(itemRespServ==""){

			Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: servIncomplet+" "+servResponItem, text: ""});
			return false;
		}
		var respServ = $("#item_responsable_nombre").val();
		if(respServ==""){

			Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: servIncomplet+" "+servRespon, text: ""});
			return false;
		}


        // se comprueba el apartado canal

		// comprobamos que tenga un canal
		if ($("#item_check_tramit_presencial").is(":checked") == false && $("#item_check_tramit_telematico").is(":checked") == false && $("#item_check_tramit_telefonico").is(":checked") == false) {
			Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: servIncomplet+" "+servCanal, text: ""});
			return false;
		}

		if ($("#item_check_tramit_telematico").prop('checked')) {

		var urlCanal=$("#item_tramite_url_ca").val();
		var tramiteIdCanal= $("#item_tramite_id").val();
		var  versionCanal= $("#item_tramite_version").val();
		var plataforma= $("#item_plataforma").val();
		if(urlCanal=="" && (tramiteIdCanal=="" || versionCanal=="" || plataforma=="")){

			Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: servIncomplet+" "+servCanalTele, text: ""});
				return false;
			}
		if(urlCanal!="" && (tramiteIdCanal!="" || versionCanal!="" || plataforma!="")){
			Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: servIncomplet+" "+servCanalTele, text: ""});
		return false;
	   }
		}


         // se comprueba el apartado LOPD

			if ($("#item_lopd_activo").prop('checked')) {

				var finlopdServ = $("#item_lopd_finalidad_ca").val();
				if(finlopdServ==""){
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: servIncomplet+" "+servLopdFin, text: ""});
					return false;
				}
				var legilopdServ = $("#item_lopd_legitimacion").val();
				if(legilopdServ==""){
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: servIncomplet+" "+servLopdLegiObligatori, text: ""});
					return false;
				}
				var destlopdServ = $("#item_lopd_destinatario_ca").val();
				if(destlopdServ==""){
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: servIncomplet+" "+servLopdDes, text: ""});
					return false;
				}
				var dretlopdServ = $("#item_lopd_derechos_ca").val();
				if(dretlopdServ==""){
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: servIncomplet+" "+servLopdDret, text: ""});
					return false;
				}


			}

   return true;
}


//valida los campos obligatorios del procedimiento
function validarProcediment(){

	//comprobamos que sea procedimiento
	if (typeof formIncomplet == 'undefined') {
		return true;
	}
	//comprobamos que sea publico
	   if ($("#item_estat").val() != 1) {
		   return true;
	   }


		//comprobacion fecha
		var fechaPro = $("#item_data_publicacio").val();
		if(fechaPro==""){

			Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: formIncomplet+" "+proPublFecha, text: ""});
			return false;
		}

		//comprobacion datos
		var nomPro = $("#item_nom_ca").val();
		if(nomPro==""){

			Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: formIncomplet+" "+proNomObligatori, text: ""});
			return false;
		}

		var objectePro = $("#item_objecte_ca").val();
		if(objectePro==""){
			Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: formIncomplet+" "+proObjecteObligatori, text: ""});
			return false;
		}

		var destinatarioPro = $("#item_destinataris_ca").val();
		if(destinatarioPro==""){
			Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: formIncomplet+" "+proDestObligatori, text: ""});
			return false;
		}
		var iniciPro = $("#item_iniciacio").val();
		if(iniciPro==""){
			Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: formIncomplet+" "+proIniObligatori, text: ""});
			return false;
		}
		var resolPro = $("#item_resolucio_ca").val();
		if(resolPro==""){
			Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: formIncomplet+" "+proResolObligatori, text: ""});
			return false;
		}
		var silenciPro = $("#item_silenci_combo").val();
		if(silenciPro==""){
			Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: formIncomplet+" "+proSilenciObligatori, text: ""});
			return false;
		}
		var admPro = $("#item_fi_vida_administrativa").val();
		if(admPro==""){
			Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: formIncomplet+" "+profiObligatori, text: ""});
			return false;
		}
		var organPro = $("#item_organ").val();
		if(organPro==""){
			Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: formIncomplet+" "+proOrganObligatori, text: ""});
			return false;
		}
		var responsablePro = $("#item_organ_responsable").val();
		if(responsablePro==""){
			Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: formIncomplet+" "+proOrganRespObligatori, text: ""});
			return false;
		}



		// comprobacion datos de contacto
		var serveiPro = $("#item_servei_responsable").val();
		if(serveiPro==""){
			Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: formIncomplet+" "+proServeiRespObligatori, text: ""});
			return false;
		}
		var respPro = $("#item_responsable").val();
		if(respPro==""){
			Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: formIncomplet+" "+proRespObligatori, text: ""});
			return false;
		}

         // se comprueba el apartado LOPD



				var finlopdPro = $("#item_lopd_finalidad_ca").val();
				if(finlopdPro==""){
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: servIncomplet+" "+proLopdFin, text: ""});
					return false;
				}
				var legilopdPro = $("#item_lopd_legitimacion").val();
				if(legilopdPro==""){
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: servIncomplet+" "+proLopdLegiObligatori, text: ""});
					return false;
				}
				var destlopdPro = $("#item_lopd_destinatario_ca").val();
				if(destlopdPro==""){
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: servIncomplet+" "+proLopdDes, text: ""});
					return false;
				}
				var dretlopdPro = $("#item_lopd_derechos_ca").val();
				if(dretlopdPro==""){
					Missatge.llansar({tipus: "alerta", modo: "error", fundit: "si", titol: servIncomplet+" "+proLopdDret, text: ""});
					return false;
				}




   return true;
}