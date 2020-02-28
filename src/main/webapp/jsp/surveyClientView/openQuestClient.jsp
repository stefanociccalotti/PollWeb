<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<h4 class="card-title" style="font-weight: 500"> ${requestScope.question.question}</h4>
<div class="form-group">
    <label>Scrivi una risposta con un numero di caratteri massimo di 100</label>
    <textarea class="form-control" name="${requestScope.question.id}" maxlength='100' rows="5" placeholder="La mia risposta ..."></textarea>
</div>
<h5 class="card-subtitle" style="margin:1em 0 0 0">${requestScope.question.note} </h5>
