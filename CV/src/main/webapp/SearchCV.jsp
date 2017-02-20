<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.lang.String" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search a CV</title>
</head>
<body>
<s:form action="SearchCV">
    <s:textfield name="keywords" label="Name"/>
    <s:submit/>
</s:form>
<s:set var="resultado" value="resultado" />
Resultado:
<jsp:useBean id="resultado" class="java.lang.String" scope="page" />
<br />
<%=resultado%>

<br />
<br />
<a href="<s:url action='index' />">Index</a></li>
</body>
</html>