<%@ page import="java.util.List" %>
<%@ page import="com.intelliarts.test.dto.CategoryDTO" %>
<%@ page import="org.springframework.ui.Model" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/styles/second.css" type="text/css"/>

</head>



<body>

<%List<CategoryDTO> categoryDTOS = (List<CategoryDTO>) session.getAttribute("list");%>
<% for (CategoryDTO one : categoryDTOS) {%>
<span><%=one%></span>
<br>
<br>
<%}%>

</body>


</html>
