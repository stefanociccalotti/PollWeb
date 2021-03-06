<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<div>
    <jsp:include page="selectQuestion.html"/>
    <div class="form-group selected-question" questionType="type-number-question" questionId="${requestScope.question.id}">
        <div class="form-group">
            <div class="col-sm-4">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input mandatoryCheckbox"  id="${requestScope.question.number}-n-q-m" <c:if test="${requestScope.question.mandatory == 1}">checked</c:if> >
                    <label class="custom-control-label" for="${requestScope.question.number}-n-q-m">Obbligatoria</label>
                    <br/>
                    <span class="help-block"><small>Seleziona questa casella se la domanda è obbligatoria.</small></span>
                </div>
            </div>
        </div>
        <input type="text" class="form-control questionText" placeholder="Testo della domanda ..." value="${requestScope.question.question}" number="${requestScope.question.number}">
        <span class="help-block"><small>Non è presente alcun tool per le risposte perché stai creando una domanda con un numero come risposta.</small></span>
        <input type="text" class="form-control questionNote" placeholder="Scrivi una nota se lo desideri ..." value="${requestScope.question.note}">
    </div>
</div>