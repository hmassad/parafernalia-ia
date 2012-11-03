<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nuevo Producto</title>
</head>
<body>
	<h1>Nuevo Producto</h1>
	<p>
		<label for="codigoTextBox">Código</label><input type="text"
			id="codigoTextBox" name="codigoTextBox"></input>
	</p>
	<p>
		<label for="caracteristicaTextBox">Característica</label><input
			type="text" id="caracteristicaTextBox" name="caracteristicaTextBox"></input>
	</p>
	<p>
		<label for="marcaTextBox">Marca</label><input type="text"
			id="marcaTextBox" name="marcaTextBox"></input>
	</p>
	<p>
		<label for="origenTextBox">Origen</label><input type="text"
			id="origenTextBox" name="origenTextBox"></input>
	</p>
	<p>
		<label for="tipoTextBox">Tipo</label><input type="text"
			id="tipoTextBox" name="tipoTextBox"></input>
	</p>
	<p>
		<label for="precioTextBox">Precio</label><input type="text"
			id="precioTextBox" name="precioTextBox"></input>
	</p>
	<label for="materiasPrimasTable">Materias Primas</label>
	<table id="materiasPrimasTable">
		<tr>
			<td></td>
		</tr>
	</table>
	<p>
		<input type="submit"></input>
	</p>
</body>
</html>