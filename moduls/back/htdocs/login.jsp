<%
// Workaround per evitar:
//    "Error 400 - invalid direct reference to form login page"
if (session.isNew()) {
    response.sendRedirect("index.jsp");
    return;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="es" xml:lang="es">
<head>
   <title>ROL - SAC</title>
   <meta http-equiv="Content-type" content='text/html; charset="UTF-8"' />
   <link rel="stylesheet" href="css/styleA.css" type="text/css" />
</head>
<body>
<div id="centrardefora">
	<div id="centrardedins">
		<div class="bloc">
			<h1>ROL - SAC</h1>
			<h2>Introduzca el nombre de usuario y la clave de acceso</h2>
		</div>
		<br />
		<form method="post" action="j_security_check">
		<div class="bloc">
			<div class="component">
				<div class="etiqueta">Usuario</div>
				<input type="text" name="j_username" maxlength="256" tabindex="1" value="" class="btext" />
			</div>
			<div class="component">
				<div class="etiqueta">Clave</div>
				<input type="password" name="j_password" maxlength="256" tabindex="2" value="" class="btext" />
			</div>
		</div>
		<div class="botonera">
			<input type="submit" name="submit" value="Entrar" class="esquerra" />
			<input type="reset" value="Reiniciar" class="dreta" />
		</div>
		</form>
	</div>
</div>
</body>
</html>
