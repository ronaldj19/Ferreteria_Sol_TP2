<%@page import="pe.com.sol.portal.session.UserSessionBean"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/menu.css" type="text/css">

<table cellpadding="0" cellspacing="0" border="0" bordercolor="green" bgcolor="#86EB5F">
	<%	HttpSession sesion = request.getSession(false);
		UserSessionBean userSessionBean=(UserSessionBean)sesion.getAttribute("scopedTarget.userSessionBean"); %>
	<tr>
		<td width="10"></td>
		<td>
			<div id="menucontainer" class="verticalMenu">
				<ul id="tabmenu">
					<div>
						<strong><p> ADMINISTRACI&oacute;N</p></strong>
					</div>
					<li><a class="" ><strong>MANTENIMIENTO</strong></a>
						<ul id="Mantenimiento" class="verticalSubmenu" style="display: block;">
							<li><a class="" href="<%=request.getContextPath()%>/producto/iniciar.htm">Producto</a></li>
							<li><a class="" href="<%=request.getContextPath()%>/cliente/iniciar.htm">Cliente</a></li>
						</ul>
					</li>
				</ul>
			</div>		
		</td>
	</tr>
	
	<tr>
		<td></td>
		<td height="300"></td>
	</tr>
</table>
