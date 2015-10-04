<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SOL Security</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/CalendarCSS.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">


<script type="text/javascript">
	function sendForm(formulario, action)
	{
		if (validaCampos(formulario))
		{
			formulario.action = "<%=request.getContextPath()%>" + action;
			formulario.submit();
		}
	}

	function validaCampos(formulario)
	{
		if (formulario.nombre.value.length == 0)
		{
			alert("(!) Ingresar Nombre");
			return false;
		}
		if (formulario.apellido.value.length == 0)
		{
			alert("(!) Ingresar Apellido");
			return false;
		}
		if (formulario.codigoProducto.value.length == 0)
		{
			alert("(!) Ingresar Código del Producto");
			return false;
		}

		return true;
	}
	
</script>

</head>

<body>

<form:form method="POST" commandName="loginForm" id="loginForm">
<input type="hidden" name="parametro" id="parametro" value="${validaPagina}" />
	<table width="810px" align="center">
		<tr>
			<td width="20"></td>
			<td align="center" colspan="3" ><br><br><h4>SEGURIDAD</h4></td>
			<td></td>
		</tr>
		<tr>
			<td colspan="5" height="20"></td>
		</tr>
		<tr>
			<td></td>
			<td colspan="4">
				<table width="100%">
					<tr>
						<td width="100"></td>
						<td></td>
						<td></td>
						<td align="right" class="textob">Nombres:</td>
						<td><form:input path="nombre" id="nombre" cssClass="textbox" /></td>
						<td width="20"></td>
					</tr>
					<tr>
						<td width="100"></td>
						<td></td>
						<td></td>
						<td align="right" class="textob">Apellidos:</td>
						<td><form:input path="apellido" id="apellido" cssClass="textbox" /></td>
						<td width="20"></td>
					</tr>
					<tr>
						<td width="100"></td>
						<td></td>
						<td></td>
						<td align="right" class="textob">Codigo Producto:</td>
						<td><form:input path="codigoProducto" id="codigoProducto" cssClass="textbox" /></td>
						<td width="20"></td>
					</tr>
					<tr>
						<td width="20"></td>
						<td colspan="4" height="35"></td>
						<td></td>
					</tr>
					<tr>
						<td width="20"></td>
						<td></td>
						<td></td>
						<td></td>
						<td align="center">
							<input class="boton" type="button" onclick="sendForm(this.form, '/security/preLogin.htm');" value="Validar Producto">
						</td>
						<td></td>
					</tr>
					<tr>
						<td width="20" height="15"></td>
						<td></td>
						<td></td>
						<td colspan="3"></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<table width="100%">
		<tr>
			<td width="300"></td>
			<td class="textoError"><c:out value="${mensaje}" /></td>
		</tr>
	</table>
</form:form>

</body>
</html>