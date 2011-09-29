// MODUL TRÀMITS

$(document).ready(function() {
	
	// elements
	modul_tramits_elm = $("div.modulTramits:first");
	
	if (modul_tramits_elm.size() == 1) {
		
		// INICIEM
		ModulTramit.iniciar();
		
	}
	
});

var ModulTramit = {
	iniciar: function() {
		
		// one al botó de gestionar
		modul_tramits_elm.find("a.gestiona").one("click", ModulTramit.gestiona);
		
	},
	gestiona: function() {
		
		procediment_ID = $("#item_id").val();
		
		if (procediment_ID != "") {
			a_enllas = true;
			document.location = pagTramit + "?id=" + procediment_ID;
		}
		
	}
};
