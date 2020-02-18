<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<h4 class="card-title" style="font-weight: bolder"> ${requestScope.question.question}</h4>
<div class="form-group">
    <label>Scrivi una risposta con un numero di caratteri massimo di 100</label>
    <textarea class="form-control" name="${requestScope.question.id}" maxlength='100' rows="5" placeholder="La mia risposta ..."></textarea>
</div>

