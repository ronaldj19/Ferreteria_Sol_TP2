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
	function sendForm(formulario, action, tipo)
	{
		if (tipo == 'SEARCH')
		{
			if (formulario.tipoBusqueda[0].checked)
			{
				if (formulario.numeroDocumentoId.value.length == 0)
				{
					alert('(!) Ingresar numero de documento del cliente.');
					return false;
				}
			}
			if (formulario.tipoBusqueda[1].checked)
			{
				if (formulario.razonSocial.value.length == 0)
				{
					alert('(!) Ingresar nombre cliente.');
					return false;
				}
			}
		} 
		
		formulario.parametro.value = tipo;
		formulario.action = "<%=request.getContextPath()%>" + action;
		formulario.submit();
	}

	function eliminar(id)
	{
		if (confirm("Estas seguro de eliminar el registro?"))
		{
			document.forms[0].parametro.value = 'DELETE';
			document.forms[0].id.value = id;
			document.forms[0].action = "<%=request.getContextPath()%>/cliente/actualizarCliente.htm";
			document.forms[0].submit();
		}else
		{
			return false;
		}
	}

	function habilitarCampo(formulario,valor)
	{
		if (valor == "D")
		{
			formulario.razonSocial.readOnly=true;
			formulario.razonSocial.value = "";
			formulario.razonSocial.style.backgroundColor='#C0C0C0';

			formulario.numeroDocumentoId.readOnly=false;
			formulario.numeroDocumentoId.style.backgroundColor='#FFFFFF';
			
		}else
		{
			formulario.razonSocial.readOnly=false;
			formulario.razonSocial.style.backgroundColor='#FFFFFF';
			
			formulario.numeroDocumentoId.readOnly=true;
			formulario.numeroDocumentoId.value = "";
			formulario.numeroDocumentoId.style.backgroundColor='#C0C0C0';
			
		}
	}
	
	function selectBusqueda()
	{
		document.forms[0].razonSocial.readOnly=true;
		document.forms[0].razonSocial.style.backgroundColor='#C0C0C0';
	}
	
</script>

</head>
<body onload="selectBusqueda();">

<form:form method="POST" commandName="clienteForm" id="clienteForm" >
<input type="hidden" name="parametro" id="parametro" />
<input type="hidden" name="id" id="id" />
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
			<td valign="top"><br>
				<table>
					<tr>
						<td colspan="4" align="center"><strong>MANTENIMIENTO DE CLIENTES</strong></td>
					</tr>
					<tr>
						<td colspan="4" height="10"></td>
					</tr>
					<tr>
						<td colspan="4">Seleccione el tipo de Busqueda:</td>
					</tr>
					<tr>
						<td colspan="2">
							<form:radiobutton path="tipoBusqueda" value="D" label="Por Número Documento" onclick="habilitarCampo(this.form,'D');"/>
						</td>
						<td colspan="2">
							<form:radiobutton path="tipoBusqueda" value="S" label="Por Razón Social" onclick="habilitarCampo(this.form,'S');"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">DNI/RUC:
							<form:input path="numeroDocumentoId" id="numeroDocumentoId" cssClass="textbox1"/>
						</td>
						<td colspan="2">Razón Social:
							<form:input path="razonSocial" id="razonSocial" cssClass="textbox1"/>
							<input type="button" class="boton" 
								onclick="sendForm(this.form, '/cliente/actualizarCliente.htm','SEARCH');" value="Buscar...">
						</td>
					</tr>
					<tr>
						<td colspan="4" height="10"></td>
					</tr>
					<tr>
						<td colspan="3"><strong>Lista de Clientes: </strong></td>
						<td align="center"><strong>Pagina <c:out value="${pagina_actual}" /> de <c:out value="${total_paginas}"/></strong></td>
					</tr>
					<tr>
						<td colspan="4">
							<table class="TablaGeneral">
								<thead class="CabeceraGeneral">
									<tr>
										<th align="center">#</th>
										<th width="80">Nro. Doc.</th>
										<th>Tipo Doc.</th>
										<th width="250">Razón Social</th>
										<th width="50">Telefono</th>
										<th>Edit</th>
										<th>Delete</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${lstCliente}" var="cliente" varStatus="cloop" >
										<c:choose>
				                          <c:when test="${cloop.count mod 2 eq 0}">
				                            <c:set var="rowStyle" value="FilaPar"/>
				                          </c:when>
				                          <c:otherwise>
				                            <c:set var="rowStyle" value="FilaImpar"/>
				                          </c:otherwise>
				                        </c:choose>
										<tr class="${rowStyle}">
											<td align="center"><c:out value="${cloop.count}" /></td>
											<td><c:out value="${cliente.numeroDocumentoId}" /></td>
											<c:if test="${cliente.tipoDocumento == '1'}">
												<td align="center">DNI</td>
											</c:if>
											<c:if test="${cliente.tipoDocumento == '2'}">
												<td align="center">RUC</td>
											</c:if>
											<td><c:out value="${cliente.razonSocial}" /></td>
											<td><c:out value="${cliente.fonoMovil}" /></td>									
											<td align="center">
												<a href="<%=request.getContextPath()%>/cliente/actualizarCliente.htm?parametro=EDIT&id=${cliente.id}" class="imglink">
													<img id='ri_14'
													 border="0" 
													 src="<%=request.getContextPath()%>/images/edit1.gif"
													 alt="Editar"
													 title="Editar Cliente"/>
												</a>
											</td>
											<td align="center"><a href="#" class="imglink" >
												<img onclick="eliminar('${cliente.id}');" id='ri_14'
													 border="0" 
													 src="<%=request.getContextPath()%>/images/delete1.gif"
													 alt="Eliminar"
													 title="Eliminar Cliente" /></a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="4" height="15"></td>
					</tr>
					<tr>
						<td colspan="4" align="right">
							<input  type="button" class="boton" onclick="sendForm(this.form, '/cliente/actualizarCliente.htm','NEW');" value="Ingresar Nuevo Cliente">
						</td>
					</tr>
					<tr>
						<td colspan="4" height="25"></td>
					</tr>
					<tr>
						<td class="textoError" colspan="4"><c:out value="${mensaje}" /></td>
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