<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h4 class="card-title"> ${requestScope.question.question}<c:if test="${requestScope.question.mandatory == 1}"><span style="color:#ffa869;">*</span></c:if></h4>
<div class="col-sm-4 multiple-answers question-info" mandatory="${requestScope.question.mandatory}" questionType="multAns">
    <c:set var="answers" value="${requestScope.question.answers}"/>
    <c:forEach begin="0" end="${fn:length(answers)-1}" var="index">
        <div class="custom-control custom-checkbox multiple-answer-text-checkbox">
            <input type="checkbox" class="custom-control-input" id="m-a-${requestScope.question.number}-${index}" name="${requestScope.question.id}" value="${answers[index]}">
            <label class="custom-control-label" for="m-a-${requestScope.question.number}-${index}">${answers[index]}</label>
        </div>
    </c:forEach>
</div>
<h5 class="card-subtitle" style="margin:1em 0 0 0">${requestScope.question.note} </h5>