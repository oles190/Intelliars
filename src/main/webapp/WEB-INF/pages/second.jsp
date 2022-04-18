<%@ page import="java.util.List" %>
<%@ page import="com.intelliarts.test.dto.CategoryDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>




<%List<CategoryDTO> categoryDTOS = (List<CategoryDTO>) session.getAttribute("list");%>
<% for (CategoryDTO one : categoryDTOS) {%>
<%=one%>
<br>
<%}%>

<body>

</body>


</html>
