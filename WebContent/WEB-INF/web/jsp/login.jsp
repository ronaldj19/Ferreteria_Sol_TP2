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
		if (formulario.usuario.value.length == 0)
		{
			alert("(!) Ingresar Usuario");
			return false;
		}
		if (formulario.password.value.length == 0)
		{
			alert("(!) Ingresar Password");
			return false;
		}

		return true;
	}
	
</script>

</head>

<body>

<form:form method="POST" commandName="loginForm" id="loginForm" >
	<table width="810px" align="center" height="110px" >
	</table>
	<table width="810px" align="center" bgcolor="#86EB5F">
		<tr>
			<td width="20"></td>
			<td align="center" colspan="3" ><br><br><h4>SISTEMA DE ADMINISTACION </h4></td>
			<td></td>
		</tr>
		<tr>
			<td width="20"></td>
			<td align="center" colspan="3" ><h4>FERRETERIAS EL SOL</h4></td>
			<td></td>
		</tr>
		<tr>
			<td colspan="5" height="20"></td>
		</tr>
		<tr>
			<td><br></td>
			<td colspan="4">
					<table width="100%">
					<tr>
						<td width="100"></td>
						<td></td>
						<td></td>
						<td align="right" class="textob">Usuario: 
						</td>
						<td><form:input path="usuario" id="usuario" cssClass="textbox" /></td>
						<td width="20"></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td ></td>
						<td align="right" class="textob">Password:</td>
						<td><form:password path="password" id="password" cssClass="textbox" 
											onkeypress="if (event.keyCode == '13'){JavaScript:sendForm(this.form, '/security/login.htm')}"/></td>
						<td></td>
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
							<input class="boton" type="button" onclick="sendForm(this.form, '/security/login.htm');" value="Login">
						</td>
						<td></td>
					</tr>
					<tr>
						<td width="20" height="15" colspan="6"></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="6" align="center">
				<table width="100%">
					<tr>
						<td width="300"></td>
						<td class="textoError"><c:out value="${mensaje}" /><br><c:out value="${mensaje1}" />
						<c:out value="${mensaje3}" /></td>
					</tr>
					<tr style=" height : 32px;">
						<td align="right"></td><br>
						<td class="textoError"><c:out value="${mensaje2}" /></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	
</form:form>

</body>
</html>