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


</head>

<body>

<form:form method="POST">

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
			<td width="20"></td>
			<td valign="top">
				<table>
					<tr>
						<td>
							<br><br><br><br>
							Bienvenido(a) al Sistema de Administración de ferreterias el SOL, use el menú vertical para empezar <br>
							a navegar por los modulos.<br><br>
							Cabe señalar que este proyecto ha sido creado para el curso de Taller de Proyectos 2 por<br>
							estudiantes de la Facultad de Ingeniería de Sistemas e Informática.
							 
						</td>
					</tr>
					<tr>
						<td height="70"></td>
					</tr>
					<tr>
						<td class="textoError"><c:out value="${mensaje}"></c:out> </td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3"><br><br><br><br>
				<table width="100%">
					
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