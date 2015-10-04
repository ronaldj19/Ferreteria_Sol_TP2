<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SOL Admin</title>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script> 
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/popup.js"></script> 
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/popup.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/CalendarCSS.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
 
<script type="text/javascript">
	
	function sendForm(formulario, action)
	{
		if (confirm("Estas seguro de realizar los cambios?"))
		{
			formulario.action = "<%=request.getContextPath()%>" + action;
			formulario.submit();
		}
		
	}

	function regresar(formulario, action)
	{
		formulario.action = "<%=request.getContextPath()%>" + action;
		formulario.submit();		
	}
	
	String.prototype.trim = function() { return this.replace(/^\s+|\s+$/, ''); };
	function validarNumero(obj,msj,d){
		
		var num = obj.value.trim();
		var re = /^\d+(\.\d+)?$/;
		if (num.length==0)
			num = 0;
		if (!re.test(num)) {
			alert('(!) ' + msj + ' no valido.');
			obj.value = "0.00";    
		}else
		{
			obj.value=(parseFloat(num)).toFixed(d); 
		}
	}

	function isAlfaNumeric(e)
	{
	    tecla = (document.all) ? e.keyCode : e.which;
	    if (tecla == 8) return true; 
	    patron = /[0-9]/;
	    te = String.fromCharCode(tecla); 
		
		if (patron.test(te))
		{
			return true;
		} 
		else
		{
			if (window.event) // IE
			{
			    window.event.keyCode = 0;
			    window.event.returnValue = false;
			}
			return false;
		}
	}

</script>

</head>
<body>

<form:form >
	<table width="810px" border="0" align="center">
		<tr>
			<td colspan="3">
				<jsp:include page="layout/Header.jsp"></jsp:include>
			</td>
		</tr>
		<tr>
			<td valign="top" width="160">
				<jsp:include page="layout/Menu.jsp"></jsp:include>
			</td>
			<td></td>
			<td valign="top">
				<table>
					<tr>
						<td colspan="4"><br><strong> <c:out value="${titulo}"></c:out> </strong></td>
					</tr>
					<tr>
						<td colspan="4" height="10"></td>
					</tr>
					<tr>
						<td colspan="4">Ingresar los nuevos valores a los parametros a modificar.</td>
					</tr>
					<tr>
						<td colspan="4" height="30"></td>
					</tr>
					<tr>
						<td>IGV %:</td>
						<td colspan="3"><input type="text" name="pigv" id="pigv" value="${igv}" class="textbox2" onkeypress="return isAlfaNumeric(event)" /> </td>
					</tr>
					<tr>
						<td>Tipo Cambio:</td>
						<td colspan="3"><input type="text" name="ptipocambio" id="ptipocambio" value="${tipoCambio}" class="textbox2" onblur="validarNumero(this,'Tipo de Cambio',2);" /></td>
					</tr>
					<tr>
						<td colspan="4" height="30"></td>
					</tr>
					<tr>
						<td colspan="4">
							<input type="button" class="boton" onclick="sendForm(this.form, '/parametro/saveParametro.htm');" value="Guardar">
							<input type="button" class="boton" onclick="regresar(this.form, '/security/paginaInicio.htm');" value="Regresar">
						</td>
					</tr>
					<tr>
						<td colspan="4" height="60"></td>
					</tr>
					<tr>
						<td colspan="4" class="textoError"><c:out value="${mensaje}" /></td>
					</tr>
					<tr>
						<td colspan="4" class="textoError"><c:out value="${mensaje1}" /></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="center"><br>
				<jsp:include page="layout/Footer.jsp"></jsp:include>
			</td>
		</tr>
		
	</table>
</form:form>

</body>
</html>