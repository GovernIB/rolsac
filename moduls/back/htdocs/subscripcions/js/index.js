// Document JavaScript

function ajustarEscritori() {
	dEscritori = document.getElementById('escritori');
	wY = document.documentElement.clientHeight;
	wX = document.documentElement.clientWidth;
	dEscritori_posY = dEscritori.offsetTop;
	dEscritori.style.height = wY - dEscritori_posY + 'px';
	dEscritori.style.width = wX + 'px';
	return wY, wX, dEscritori;
}

window.onload = ajustarEscritori;
window.onresize = ajustarEscritori;

// menu

var ie = document.all;
var dom = document.getElementById && !document.all;

function posicionOffset(obj,tipo) {
	var totalOffset = (tipo == "left") ? obj.offsetLeft : obj.offsetTop;
	var parentEl = obj.offsetParent;
	while (parentEl != null){
		totalOffset = (tipo == "left") ? totalOffset+parentEl.offsetLeft : totalOffset+parentEl.offsetTop;
		parentEl = parentEl.offsetParent;
	}
	return totalOffset;
}

function voreMenu(obj, e, menuID) {
	subMenu = document.getElementById(menuID);
	if (typeof subMenu != "undefined") subMenu.style.display = 'none';
	clearhidemenu();
	// evitamos la expansión del evento
	if (window.event) event.cancelBubble = true;
	else if (e.stopPropagation) e.stopPropagation();
	// buscamos posición de la opcion
	subMenu.x = posicionOffset(obj.parentNode, "left");
	subMenu.y = posicionOffset(obj.parentNode, "top");
	subMenu.w = obj.parentNode.offsetWidth;
	// mostramos el submenu y posicionamos
	subMenu.style.display = 'block';
	subMenu.style.left = subMenu.x + "px";
	subMenu.style.top = subMenu.y + "px";
	subMenu.style.width = subMenu.w - 2 + "px";
	subMenu.onmouseover = clearhidemenu;
	subMenu.onmouseout = ie ? function() { dynamichide(event); } : function(event) { dynamichide(event); }
	subMenu.onclick = function() { subMenu.style.display = 'none'; };
}

function dynamichide(e) {
	if (ie && !subMenu.contains(e.toElement)) delayhidemenu();
	else if (dom && e.currentTarget != e.relatedTarget) delayhidemenu();
}

function delayhidemenu() {
	delayhide = setTimeout("subMenu.style.display = 'none'",100);
}

function clearhidemenu() {
	if (typeof delayhide != "undefined") clearTimeout(delayhide);
}