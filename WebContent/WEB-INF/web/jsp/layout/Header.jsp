<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/menu.css" type="text/css">
<jsp:useBean id="now" class="java.util.Date" />
<script type="text/javascript">
	function sendLogout(formulario)
	{
		formulario.action = "<%=request.getContextPath()%>/security/logout.htm";
		formulario.submit();
	}	
</script>

<table cellpadding="0" cellspacing="0" border="0" width="100%" bgcolor=#86EB5F>
	<tr>
		<td width="10"><br></td>
		<td>
			<table>
				<tr>
					<td align="center"><img alt="sapn" src="<%=request.getContextPath()%>/images/sol.jpg" width="60%"> </td>
				</tr>
			</table>
		</td>
		<td>
			<table border="0" width="100%">
				<tr>
					<td height="20"></td>
					<td></td>
				</tr>
				<tr>
					<td class="texto"><strong>SISTEMA DE ADMINISTRACION</strong></td>
					<td align="right"><input class="boton" type="button" value="Logout" 
						onclick="sendLogout(this.form);"></td>
				</tr>
				<tr>
					<td colspan="2" class="texto">
						<strong>FERRETERIAS EL SOL</strong>
					</td>
				</tr>
				<tr>
					<td>Bienvenido(a),
					<c:out value='${sessionScope["scopedTarget.userSessionBean"].nombreCompleto}'/></td>
					<td align="right"><fmt:formatDate value="${now}" pattern="EEEE, dd 'de' MMMM 'de' yyyy "/></td>
				</tr>
				<tr>
					<td colspan="2" height="1px" bgcolor="">
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="3" height="1px" bgcolor="#08088A">
		</td>
	</tr>
</table>
