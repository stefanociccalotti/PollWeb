<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div>
    <jsp:include page="selectQuestion.html"/>
    <div class="form-group selected-question" questionType="type-single-answer" questionId="${requestScope.question.id}">
        <div class="form-group" >
            <div class="col-sm-4">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox"  id="${requestScope.question.id}-s-a-m" class="custom-control-input mandatoryCheckbox" value="${requestScope.question.question}"  <c:if test="${requestScope.question.mandatory == 1}">checked</c:if> >
                    <label class="custom-control-label" for="${requestScope.question.id}-s-a-m">Obbligatoria</label>
                    <br/>
                    <span class="help-block"><small>Seleziona questa casella se la domanda è obbligatoria.</small></span>
                </div>
            </div>
        </div>
        <input type="text" class="form-control questionText" placeholder="Testo della domanda ..." value="${requestScope.question.question}" number="${requestScope.question.number}">
        <div class="col-sm-4 single-answers">
            <c:set var="answers" value="${requestScope.question.answers}"/>
            <c:forEach begin="0" end="${fn:length(answers)-1}" var="index">
                <div class="custom-control custom-radio single-answer-text-radio">
                    <input type="radio" id="s-a-${requestScope.question.number}-${index}" name="customRadio" class="custom-control-input">
                    <label class="custom-control-label" for="s-a-${requestScope.question.number}-${index}"><input type="text"class="answerText" placeholder=" Testo della risposta ..." value="${answers[index]}"></label>
                </div>
            </c:forEach>
            <div class="singAnsMarker"></div>
            <div class="m-icon addSingAns"><i class="m-r-10 mdi mdi-plus-circle-outline"></i><span>Aggiungi Risposta</span></div>
        </div>
        <input type="text" class="form-control questionNote" placeholder="Scrivi una nota se lo desideri ..." value="${requestScope.question.note}">
    </div>
</div>