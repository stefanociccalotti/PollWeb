<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<h4 class="card-title" style="font-weight: bolder"> ${requestScope.question.question}</h4>
<div class="form-group date-answer">
    <input type="date" name="${requestScope.question.id}" class="form-control" placeholder="...">
</div>
