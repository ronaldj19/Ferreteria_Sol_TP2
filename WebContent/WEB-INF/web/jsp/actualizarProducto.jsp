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

	function sendForm(formulario, action, parametro)
	{
		if (validarCampos(formulario,parametro)){
			formulario.action = "<%=request.getContextPath()%>" + action;
			formulario.submit();
		}
	}

	function sendForm1(formulario, action)
	{
		if (formulario.nombreMarca.value.length == 0)
		{
			alert('(!) Ingresar una marca. ');	
		}else
		{
			formulario.action = "<%=request.getContextPath()%>" + action;
			formulario.submit();
		}		
	}
	
	function validarCampos(formulario,parametro)
	{
		if (parametro != 'INIT')
		{
			if (parametro == 'NEW' || parametro == 'EDIT')
			{
				if (parametro == 'NEW')
				{
					if (formulario.codigo.value.length == 0)
					{
						alert('(!) Ingresar Código Producto.');return false;
					}
				}
				if (formulario.nombre.value.length == 0)
				{
					alert('(!) Ingresar Nombre Producto.');return false;
				}
				if (formulario.precioVenta.value.length == 0)
				{
					alert('(!) Ingresar Precio Venta.');return false;
				}
				if (parseFloat(formulario.precioVenta.value) == 0)
				{
					alert('(!) Ingresar Precio Venta.');return false;
				}	
				if (formulario.descripcion.value.length > 200)
				{
					alert('(!) El campo descripción ha superado el  numero minimo (200) de caracteres permitidos.');return false;
				}	
			}	
		}
		
		return true;
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

	//CONTROLLING EVENTS IN jQuery
	$(document).ready(function(){

		var h = 250;
		var w = 400;
		var popup = "popupContact";
		
		disablePopup();
		//LOADING POPUP
		//Click the button event!
		$("a[rel='pop-up']").click(function(){
			//centering with css
			centerPopup(popup,h,w);
			//load popup
			loadPopup(popup);
		});
					
		//CLOSING POPUP
		//Click the x event!
		$("#popupContactClose").click(function(){
			disablePopup();
		});
		//Click out event!
		$("#backgroundPopup").click(function(){
			disablePopup();
		});
		//Press Escape event!
		$(document).keypress(function(e){
			if(e.keyCode==27 && popupStatus==1){
				disablePopup();
			}
		});

	});
	
	
</script>

</head>
<body>
<form:form commandName="productoForm" id="productoForm" enctype="multipart/form-data">
<input type="hidden" name="parametro" id="parametro" value="${update_producto}"/>
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
			<td>
				<table>
					<tr>
						<td colspan="4"><strong> <c:out value="${titulo}"></c:out> </strong></td>
					</tr>
					<tr>
						<td colspan="4" height="15"></td>
					</tr>
					<c:if test="${update_producto == 'EDIT'}">
					<tr>
						<td>Código:</td>
						<td colspan="3"><c:out value="${productoForm.codigo}"></c:out></td>
						
					</tr>	
					</c:if>
					<c:if test="${update_producto != 'EDIT'}">
					<tr>
						<td>Código(*):</td>
 						<td colspan="3"><form:input path="codigo" cssClass="textbox2" maxlength="20"/></td>
 							</tr>	
					</c:if>
					<tr>
						<td>Nombre(*):</td>
						<td colspan="3"><form:input path="nombre" cssClass="textbox3" maxlength="50"/></td>
					</tr>
					<tr>
						<td>Marca:</td>
						<td colspan="2">
							<form:select path="idMarca" cssClass="textbox1">
								<form:option value="0" label="--Seleccionar--"></form:option>
								<form:options items="${lstMarca}" itemValue="id" itemLabel="nombre" />
							</form:select>
						</td>
						<td><a href="#" rel="pop-up">Agregar una Marca...</a></td>
					</tr>
					<tr>
						<td>Stock:</td>
						<td colspan="3"><form:input path="stock" cssClass="textbox2" onblur="validarNumero(this,'',0);" onkeypress="return isAlfaNumeric(event)" maxlength="6"/></td>
					</tr>
					<tr>
						<td>Precio Venta(*):</td>
						<td colspan="3"><form:input path="precioVenta" cssClass="textbox2" onblur="validarNumero(this,'Precio de Venta ingresado',2);" maxlength="8"/>
						</td>
					</tr>
					<tr>
						<td>Precio Compra:</td>
						<td><form:input path="precioCompra" cssClass="textbox2" onblur="validarNumero(this,'Precio de Compra ingresado',2);" maxlength="8"/>
						</td>
					</tr>
					<tr>
						<td>Foto:</td>
						<td colspan="3">
							<input type="file" name="foto" id="foto" class="textbox3"/>
						</td>
					</tr>
					<tr>
						<td>Descripción:</td>
						<td colspan="3"><form:textarea path="descripcion" rows="3" cols="10" cssClass="textbox3"/></td>
					</tr>
					<tr>
						<td colspan="4" height="30"></td>
					</tr>
					<tr>
						<td>
							<input type="button" class="boton" onclick="sendForm(this.form, '/producto/saveProducto.htm','${update_producto}');" value="Guardar">
						</td>
						<td>
							<input type="button" class="boton" onclick="sendForm(this.form, '/producto/iniciar.htm', 'INIT');" value="Cancelar">
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
		<tr>
			<td colspan="4">´
				<div id=popupContact>
					<a href="#" id="popupContactClose">x</a> 
				   <table width="100%">
				   		<tr>
							<td colspan="5" height="15"></td>
						</tr>
						<tr>
							<td width="20"></td>
							<td colspan="4" align="left"><strong> AGREGAR UNA MARCA DE PRODUCTO </strong></td>
						</tr>
						<tr>
							<td></td>
							<td colspan="4" height="1px" bgcolor="#08088A"></td>
						</tr>
						<tr>
							<td colspan="5" height="15"></td>
						</tr>
						<tr>
							<td></td>
							<td>Marca: </td>
							<td colspan="2"><form:input path="nombreMarca" cssClass="textbox1"/></td>
						</tr>
						<tr>
							<td></td>
							<td>Procedencia: </td>
							<td colspan="2">
							<form:select path="procedencia" cssClass="textbox1">
								<form:options items="${lstPais}" itemValue="valor" itemLabel="valor" />
							</form:select>
							</td>
						</tr>
						<tr>
							<td></td>
							<td>Descripción: </td>
							<td colspan="2"><form:textarea path="descripcionMarca" rows="3" cols="10" cssClass="textbox3"/></td>
						</tr>
						<tr>
							<td colspan="5" height="30"></td>
						</tr>
						<tr>
							<td></td>
							<td>
								<input type="button" class="boton" onclick="sendForm1(this.form, '/producto/saveMarcaProducto.htm');" value="Guardar">
							</td>
							<td>
								<input type="button" class="boton" onclick="disablePopup();" value="Cancelar">
							</td>
							<td colspan="2"></td>
						</tr>
					</table>
				</div>
				<div id="backgroundPopup"></div>
			</td>		
		</tr>
	</table>
</form:form>

</body>
</html>