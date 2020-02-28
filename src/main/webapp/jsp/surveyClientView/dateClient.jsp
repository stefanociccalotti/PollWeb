<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<h4 class="card-title" style="font-weight: 500"> ${requestScope.question.question}</h4>
<div class="form-group date-answer">
    <input type="date" name="${requestScope.question.id}" class="form-control" placeholder="...">
</div>
<h5 class="card-subtitle" style="margin:1em 0 0 0">${requestScope.question.note} </h5>
