<%@ page import="ua.epam.electives.commands.LoginLecturerCommand"%>
<%@ page import="ua.epam.electives.entities.AuthorizedUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="ua.epam.electives.localization.text" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="login.label.title" /></title>
</head>
<body>
	<fmt:message key="login.label.language" />
	<form>
	    <select id="language" name="language" onchange="submit()">
	        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
	        <option value="uk" ${language == 'uk' ? 'selected' : ''}>Україна</option>
	    </select>
    </form>
        
    
	<h1><fmt:message key="login.label.enterToSystem" /></h1>   
<%-- 	<c:if test="${requestScope.errorLogIn eq true}">
		<h1>
			<font color="red"><fmt:message key="login.label.enterError" /></font>
		</h1>
	</c:if> --%>
	<form name="loginForm" action="controller" method="post">
		<input type="hidden" name="command" value="login" />
		<table cellpadding="3pt">
			<tr>
				<td><fmt:message key="login.label.username" /> :</td>
				<td><input type="text" name="userName" size="30" /></td>
			</tr>
			<tr>
				<td><fmt:message key="login.label.password" /> :</td>
				<td><input type="password" name="password" size="30" /></td>
			</tr>
		</table>
		<p />
		<fmt:message key="login.button.submit" var="buttonValue" />
		<input type="submit" value="${buttonValue}" />
	</form>
</body>
</html>