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
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/CalendarCSS.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">

<script type="text/javascript">

	function sendForm(formulario, action, parametro)
	{
		if (validarCampos(formulario,parametro)){
			formulario.action = "<%=request.getContextPath()%>" + action;
			formulario.submit();
		}
	}
	
	function validarCampos(formulario,parametro)
	{
		if (parametro != 'INIT')
		{
			if (formulario.tipoDocumento.value == 0)
			{
				alert('(!) Seleccionar Tipo de Documento.');return false;
			}
			if (formulario.numeroDocumentoId.value == 0)
			{
				alert('(!) Ingresar Nro Documento.');return false;
			}
			if (formulario.razonSocial.value == 0)
			{
				alert('(!) Ingresar Razón Social.');return false;
			}
			if (formulario.tipoDocumento.value == 1 )
			{
				if(formulario.numeroDocumentoId.value.length != 8){
					alert('(!) El DNI debe tener 8 digitos.');return false;				
				}
			}
			if (formulario.tipoDocumento.value == 2 )
			{
				if(formulario.numeroDocumentoId.value.length != 11){
					alert('(!) El RUC debe tener 11 digitos.');return false;				
				}
			}
			if (formulario.fonoMovil.value != 0 )
			{
				if(formulario.fonoMovil.value.length != 9){
					alert('(!) Elnumero de telefono movil debe tener 9 digitos');return false;				
				}
			}
			if (formulario.fonoFijo.value != 0 )
			{
				if(formulario.fonoFijo.value.length != 9){
					alert('(!) Elnumero de telefono fijo debe tener 9 digitos debido al codigo de ciudad');return false;				
				}
			}
			if (formulario.email.value.length != 0)
			{
				return validarEmail(formulario.email.value);
			}
			
			if (formulario.descripcion.value.length > 200)
			{
				alert('(!) El campo descripción ha superado el  numero minimo (200) de caracteres permitidos.');return false;
			}
			 
		}
		
		return true;
	}

	function validarEmail(valor) {
		if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(valor)){
			return true;
		} 
		alert('(!) El E-mail ingresado no es correcto.');
		return false;
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

<form:form commandName="clienteForm" id="clienteForm">
<input type="hidden" name="parametro" id="parametro" value="${update_cliente}"/>
<form:hidden path="id"/>
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
						<td colspan="4" height="15"></td>
					</tr>
					<c:if test="${update_cliente == 'EDIT'}">
					<tr>
						<td>Código:</td>
						<td colspan="3"><c:out value="${clienteForm.id}"></c:out></td>
						
					</tr>	
					</c:if>
					<tr>
						<td>Tipo Documento:</td>
						<td colspan="3">
						<form:select path="tipoDocumento" cssClass="textbox1">
							<form:option value="0" label="--Select--"></form:option>
							<form:option value="1" label="DNI"></form:option>
							<form:option value="2" label="RUC"></form:option>
						</form:select>
						</td>
						
					</tr>
					<tr>
						<td>Nro. Documento:</td>
						<td colspan="3"><form:input path="numeroDocumentoId" cssClass="textbox1" onkeypress="return isAlfaNumeric(event)" maxlength="11"/>
						</td>
					</tr>
					<tr>
						<td>Nombre/Razón Social :</td>
						<td colspan="3"><form:input path="razonSocial" cssClass="textbox3" maxlength="100"/></td>
					</tr>
					<tr>
						<td>Fono Movil :</td>
						<td colspan="3"><form:input path="fonoMovil" cssClass="textbox3" onkeypress="return isAlfaNumeric(event)"  maxlength="9" onclick=""/></td>
					</tr>
					<tr>
						<td>Fono Fijo :</td>
						<td colspan="3"><form:input path="fonoFijo" cssClass="textbox3" onkeypress="return isAlfaNumeric(event)" maxlength="9"/></td>
					</tr>
					<tr>
						<td>E-mail :</td>
						<td colspan="3"><form:input path="email" cssClass="textbox3" maxlength="50"/></td>
					</tr>
					<tr>
						<td>Dirección:</td>
						<td colspan="3"><form:input path="direccion" cssClass="textbox3" maxlength="200"/></td>
					</tr>
					<tr>
						<td>Descripción:</td>
						<td colspan="3"><form:textarea path="descripcion" rows="3" cols="3" cssClass="textbox3"/></td>
					</tr>
					<tr>
						<td colspan="4" height="30"></td>
					</tr>
					<tr>
						<td>
							<input type="button" class="boton" onclick="sendForm(this.form, '/cliente/saveCliente.htm','${update_cliente}');" value="Guardar">
						</td>
						<td>
							<input type="button" class="boton" onclick="sendForm(this.form, '/cliente/iniciar.htm','INIT');" value="Cancelar">
						</td>
						<td colspan="2"></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<table width="100%">
					<tr>
						<td width="150"></td>
						<td class="textoError"><c:out value="${mensaje}" /></td>
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