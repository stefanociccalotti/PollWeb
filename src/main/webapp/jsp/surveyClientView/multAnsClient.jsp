<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h4 class="card-title" style="font-weight: bolder"> ${requestScope.question.question}</h4>
<div class="col-sm-4 multiple-answers">
    <c:set var="answers" value="${requestScope.question.answers}"/>
    <c:forEach begin="0" end="${fn:length(answers)-1}" var="index">
        <div class="custom-control custom-checkbox multiple-answer-text-checkbox">
            <input type="checkbox" class="custom-control-input" id="m-a-${requestScope.question.number}-${index}" name="${requestScope.question.question}" value="${answers[index]}">
            <label class="custom-control-label" for="m-a-${requestScope.question.number}-${index}">${answers[index]}</label>
        </div>
    </c:forEach>
</div>

