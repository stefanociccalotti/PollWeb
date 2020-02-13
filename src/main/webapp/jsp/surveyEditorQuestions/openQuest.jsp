<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
    <div class="form-group selected-question" questionType="type-open-question" questionId="${requestScope.question.id}">
        <div class="form-group" >
            <div class="col-sm-4">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input mandatoryCheckbox" id="${requestScope.question.number}-o-q-m" <c:if test="${requestScope.question.mandatory == 1}">checked</c:if> >
                    <label class="custom-control-label" for="${requestScope.question.number}-o-q-m">Obbligatoria</label>
                    <br/>
                    <span class="help-block"><small>Seleziona questa casella se la domanda è obbligatoria.</small></span>
                </div>
            </div>
        </div>
        <input type="text" class="form-control questionText" placeholder="Testo della domanda ..." value="${requestScope.question.question}" number="${requestScope.question.number}">
        <span class="help-block"><small>Non è presente alcun tool per le risposte perché stai creando una domanda aperta.</small></span>
        <input type="text" class="form-control questionNote" placeholder="Scrivi una nota se lo desideri ..." value="${requestScope.question.note}" >
    </div>
</div>