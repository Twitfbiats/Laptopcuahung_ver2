<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en"><head>
<meta charset="utf-8">
<title>Directory listing</title>
</head>
<body>
<h1>Directory listing</h1>
<hr>
<ul>
    <c:forEach var="file" items="${files}">
        <li><a href="${file}">${file}</a></li>
    </c:forEach>
</ul>
<hr>


</body></html>