<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div>
    <div class="create-question-container">
        <div class="choose-question-type">
            <div class="form-group select-question-container">
                <label>Scegli il tipo di domanda</label>
                <select class="custom-select col-12">
                    <option selected>Scegli...</option>
                    <option value="1" newQuestionType="singAns">Risposta singola</option>
                    <option value="2" newQuestionType="multAns">Risposta multipla</option>
                    <option value="3" newQuestionType="openQuest">Domanda Aperta</option>
                    <option value="4" newQuestionType="number">Numero</option>
                    <option value="5" newQuestionType="date">Data</option>
                </select>
            </div>
        </div>
        <input type="button" value="Elimina domanda" style="height: 2em;align-self: flex-end;margin-bottom: 1rem;">
    </div>
    <div class="form-group selected-question" questionType="type-multiple-answer" questionId="${requestScope.question.id}">
        <div class="form-group">
            <div class="col-sm-4">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input mandatoryCheckbox" id="${requestScope.question.number}-m-a-m" <c:if test="${requestScope.question.mandatory == 1}">checked</c:if> >
                    <label class="custom-control-label" for="${requestScope.question.number}-m-a-m">Obbligatoria</label>
                    <br/>
                    <span class="help-block"><small>Seleziona questa casella se la domanda è obbligatoria.</small></span>
                </div>
            </div>
        </div>
        <input type="text" class="form-control questionText" placeholder="Testo della domanda ..." value="${requestScope.question.question}" number="${requestScope.question.number}">
        <div class="col-sm-4 multiple-answers">
            <c:set var="answers" value="${requestScope.question.answers}"/>
            <c:forEach begin="0" end="${fn:length(answers)-1}" var="index">
                <div class="custom-control custom-checkbox multiple-answer-text-checkbox">
                    <input type="checkbox" class="custom-control-input" id="m-a-${requestScope.question.number}-${index}">
                    <label class="custom-control-label" for="m-a-${requestScope.question.number}-${index}"><input type="text" class="answerText" placeholder=" Testo della risposta ..." value="${answers[index]}"></label>
                </div>
            </c:forEach>
            <div class="multAnsMarker"></div>
            <div class="m-icon addMultAns"><i class="m-r-10 mdi mdi-plus-circle-outline"></i><span>Aggiungi Risposta</span></div>
        </div>
        <input type="text" class="form-control questionNote" placeholder="Scrivi una nota se lo desideri ..." value="${requestScope.question.note}">
    </div>
</div>