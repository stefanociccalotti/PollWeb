<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<h4 class="card-title" style="font-weight: 500"> ${requestScope.question.question}<c:if test="${requestScope.question.mandatory == 1}"><span style="color:#ffa869;">*</span></c:if></h4>
<div class="form-group number-answer question-info" mandatory="${requestScope.question.mandatory}" questionType="number">
    <input type="number" pattern="[0-9]*" name="${requestScope.question.id}" class="form-control number-answer">
</div>
<h5 class="card-subtitle" style="margin:1em 0 0 0">${requestScope.question.note} </h5>