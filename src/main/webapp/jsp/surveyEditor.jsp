<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<jsp:include page="frame.jsp" />

<div class="page-wrapper">

    <div class="page-breadcrumb">
        <div class="row">
            <div class="col-5 align-self-center">
                <h4 class="page-title">Crea Sondaggio</h4>
            </div>
        </div>
    </div>
    <div class="container-fluid">
        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-body">
                        <form class="form-horizontal m-t-30">
                            <h4 class="card-title">Informazioni sul sondaggio:</h4>
                            <h5 class="card-subtitle"> I campi contrassegnati con "*" sono obbligatori </h5>
                            <!--Riservato-->
                            <div class="form-group">
                                <label>Seleziona questo checkbox se desideri che il sondaggio sia riservato (puoi farlo anche in seguito):</label>
                                <div class="col-sm-4">
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="custom-control-input" id="surveyPrivacy">
                                        <label class="custom-control-label" for="surveyPrivacy">Riservato</label>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label>Titolo *</label>
                                <input type="text" class="form-control" placeholder="titolo del sondaggio ..." value="${survey.title}">
                            </div>
                            <div class="form-group">
                                <label>Testo di apertura</label>
                                <textarea class="form-control" rows="5" placeholder="visualizzato dall'utente prima di compilare il sondaggio ...">${survey.opening}</textarea>
                            </div>
                            <div class="form-group survey-closing">
                                <label>Testo di chiusura</label>
                                <textarea class="form-control" rows="5" placeholder="visualizzato dopo la compilazione ...">${survey.closing}</textarea>
                            </div>

                            <hr class="hr-heavy-separation">

                            <h4 class="card-title h4-question">Creazione domande:</h4>
                            <h5 class="card-subtitle">Nota: per creare un sondaggio sono necessarie almeno 5 domande.</h5>

                            <hr class="hr-soft-separation">

                            <c:choose>
                                <c:when test="${questions != null}">
                                    <c:forEach items="${questions}" var="item">
                                        <c:set var="question" scope="request" value="${item}"/>
                                        <jsp:include page="surveyEditorQuestions/${item.type}.jsp"/>
                                        <hr class="hr-soft-separation">
                                    </c:forEach>
                                    <c:if test="${numberOfQuestions < 5}">
                                        <c:set var="index" value="${5 - numberOfQuestions}"></c:set>
                                        <c:forEach begin="0" end="${index-1}" varStatus="loop">
                                            <jsp:include page="surveyEditorQuestions/chooseQuestion.html"/>
                                        </c:forEach>
                                    </c:if>
                                    <div class="m-icon addQuestion" id="addQuestion"><span>Aggiungi Domanda</span><i class="m-r-10 mdi mdi-plus-circle-outline"></i></div>

                                    <div class="form-group">
                                        <div class="col-sm-12" style="display:flex;justify-content: center;">
                                            <input type="button" id="submit" class="btn btn-success submit" style="font-size:1.5em;" value="Aggiorna Sondaggio"/>
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach begin="0" end="4" varStatus="loop">
                                        <jsp:include page="surveyEditorQuestions/chooseQuestion.html"/>
                                    </c:forEach>
                                    <div class="m-icon addQuestion" id="addQuestion"><span>Aggiungi Domanda</span><i class="m-r-10 mdi mdi-plus-circle-outline"></i></div>

                                    <div class="form-group">
                                        <div class="col-sm-12" style="display:flex;justify-content: center;">
                                            <input type="button" id="submit" class="btn btn-success submit" style="font-size:1.5em;" value="Crea Sondaggio"/>
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    window.numberOfQuestions = "${numberOfQuestions}";
    const array = "${printAll}".split("_&_");
    array.forEach(element => console.log(element));
</script>
<jsp:include page="footer.jsp" />