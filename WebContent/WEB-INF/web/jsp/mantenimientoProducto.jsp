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
				if (formulario.codigo.value.length == 0)
				{
					alert('(!) Ingresar código producto.');
					return false;
				}
			}
			if (formulario.tipoBusqueda[1].checked)
			{
				if (formulario.nombre.value.length == 0)
				{
					alert('(!) Ingresar nombre producto.');
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
			document.forms[0].action = "<%=request.getContextPath()%>/producto/actualizarProducto.htm";
			document.forms[0].submit();
		}else
		{
			return false;
		}
	}

	function habilitarCampo(formulario,valor)
	{
		if (valor == "C")
		{
			formulario.nombre.readOnly=true;
			formulario.nombre.value = "";
			formulario.nombre.style.backgroundColor='#C0C0C0';

			formulario.codigo.readOnly=false;
			formulario.codigo.style.backgroundColor='#FFFFFF';
			
		}else
		{
			formulario.nombre.readOnly=false;
			formulario.nombre.style.backgroundColor='#FFFFFF';
			
			formulario.codigo.readOnly=true;
			formulario.codigo.value = "";
			formulario.codigo.style.backgroundColor='#C0C0C0';
			
		}
	}

	function consultar(formulario,valor)
	{
		formulario.num_pag.value = valor;
		formulario.action = "<%=request.getContextPath()%>/producto/buscarProductoPag.htm";
		formulario.submit();
	}
	
	function selectBusqueda(tipo)
	{
		if (tipo == "C")
		{
			document.forms[0].nombre.readOnly=true;
			document.forms[0].nombre.value = "";
			document.forms[0].nombre.style.backgroundColor='#C0C0C0';

			document.forms[0].codigo.readOnly=false;
			document.forms[0].codigo.style.backgroundColor='#FFFFFF';
			
		}else
		{
			document.forms[0].nombre.readOnly=false;
			document.forms[0].nombre.style.backgroundColor='#FFFFFF';
			
			document.forms[0].codigo.readOnly=true;
			document.forms[0].codigo.value = "";
			document.forms[0].codigo.style.backgroundColor='#C0C0C0';
			
		}
		
	}
	
</script>

</head>
<body onload="selectBusqueda('${busqueda}');">

<form:form method="POST" commandName="productoForm" id="productoForm" >
<input type="hidden" name="parametro" id="parametro" />
<input type="hidden" name="id" id="id" />
<input type="hidden" name="reg_total" id="reg_total" value="${registros_prod}">
<input type="hidden" name="num_pag" id="num_pag" >
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
						<td colspan="4" align="center"><strong>MANTENIMIENTO DE PRODUCTOS</strong></td>
					</tr>
					<tr>
						<td colspan="4" height="10"></td>
					</tr>
					<tr>
						<td colspan="4">Seleccione el tipo de Busqueda:</td>
					</tr>
					<tr>
						<td colspan="2">
							<form:radiobutton path="tipoBusqueda" value="C" label="Por Codigo" onclick="habilitarCampo(this.form,'C');"/>
						</td>
						<td colspan="2">
							<form:radiobutton path="tipoBusqueda" value="N" label="Por Nombre" onclick="habilitarCampo(this.form,'N');"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">Código:
							<form:input path="codigo" id="codigo" cssClass="textbox1" maxlength="20" />
						</td>
					
						<td colspan="2">Nombre:
							<form:input path="nombre" id="nombre" cssClass="textbox1" maxlength="50"/>
							<input type="button" class="boton" 
								onclick="sendForm(this.form, '/producto/actualizarProducto.htm','SEARCH');" value="Buscar...">
						</td>
					</tr>
					<tr>
						<td colspan="4" height="10"></td>
					</tr>
					<tr>
						<td colspan="3"><strong>Lista de Productos: </strong></td>
						<td align="center"><strong>Pagina <c:out value="${pagina_actual}" /> de <c:out value="${total_paginas}"/></strong></td>
					</tr>
					<tr>
						<td colspan="4">
							<table class="TablaGeneral">
								<thead class="CabeceraGeneral">
									<tr>
										<th align="center">#</th>
										<th>Código</th>
										<th width="250">Nombre</th>
<!-- 										<th width="50">Marca</th>									
 -->										<th width="50">Precio</th>
										<th width="50">Stock</th>
										<th>Edit</th>
										<th>Delete</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${lstProducto}" var="producto" varStatus="ploop" >
										<c:choose>
				                          <c:when test="${ploop.count mod 2 eq 0}">
				                            <c:set var="rowStyle" value="FilaPar"/>
				                          </c:when>
				                          <c:otherwise>
				                            <c:set var="rowStyle" value="FilaImpar"/>
				                          </c:otherwise>
				                        </c:choose>
										<tr class="${rowStyle}">
											<td align="center"><c:out value="${ploop.count}" /></td>
											<td><c:out value="${producto.codigo}" /></td>
											<td><c:out value="${producto.nombre}" /></td>
<%-- 											<td><c:out value="${producto.marca.nombre}" /></td>
 --%>											<td align="right"><c:out value="${producto.precioVenta}" /></td>
											<td align="right"><c:out value="${producto.stock}" /></td>
											<td align="center">
												<a href="<%=request.getContextPath()%>/producto/actualizarProducto.htm?parametro=EDIT&id=${producto.id}" class="imglink">
													<img id='ri_14'
													 border="0" 
													 src="<%=request.getContextPath()%>/images/edit1.gif"
													 alt="Editar"
													 title="Editar Producto"/>
												</a>
											</td>
											<td align="center"><a href="#" class="imglink" >
												<img onclick="eliminar('${producto.id}');" id='ri_14'
													 border="0" 
													 src="<%=request.getContextPath()%>/images/delete1.gif"
													 alt="Eliminar"
													 title="Eliminar Producto" /></a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</td>
					</tr>
				<tr>
						<td colspan="4">
						<c:forEach items="${lstParametro}" var="parametro" varStatus="ploop" >
						<input type="button" class="boton" onclick="consultar(this.form,'${parametro.valor}');" value="${parametro.valor}">
						</c:forEach>
						</td>
					</tr>  
					<tr>
						<td colspan="4" height="15"></td>
					</tr>
					<tr>
						<td colspan="4" align="right">
							<input type="button" class="boton" onclick="sendForm(this.form, '/producto/actualizarProducto.htm','NEW');" value="Ingresar Nuevo Producto">
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