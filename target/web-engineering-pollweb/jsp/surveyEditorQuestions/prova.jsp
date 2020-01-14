<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:out value="${question.question}"/>
<c:set var="answers" value="${requestScope.question.answers}"/>
<c:forEach begin="0" end="${fn:length(answers)}" var="index">
    ${answers[index]}
</c:forEach>