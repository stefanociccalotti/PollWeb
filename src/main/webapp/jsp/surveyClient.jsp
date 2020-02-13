<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="frame.jsp" />

<style>
    .card{
        position: relative;
        display: flex;
        flex-direction: column;
        min-width: 0;
        word-wrap: break-word;
        background-color: #fff;
        background-clip: border-box;
        border: 0 solid transparent;
        border-radius: 0;
        !important;
    }

</style>
<form method="post" action="surveyClient">

<div class="page-wrapper">

    <label id="sizesurvey" hidden>${numberOfQuestions}</label>


    <div class="container-fluid">

        <div class="survey-container" id="survey-container">

            <div class="col-12" id="slide">
                <div class="col-6">
                    <div class="card">
                        <div class="card-body">
                            <div class="form-group">
                                <label>Titolo *</label>
                                <input type="text" class="form-control" placeholder="titolo del sondaggio ..." value="${survey.title}">
                            </div>
                            <div class="form-group">
                                <label>Testo di apertura</label>
                                <textarea class="form-control" rows="5" placeholder="visualizzato dall'utente prima di compilare il sondaggio ...">${survey.opening}</textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <input type="hidden" name="idans" value="${surveyid}">

            <c:if test="${questions != null}">
                                <c:forEach items="${questions}" var="item">
                                    <div class="col-12">
                                    <div class="col-6">
                                    <div class="card">
                                    <div class="card-body">
                                    <c:set var="question" scope="request" value="${item}"/>
                                    <jsp:include page="surveyEditorQuestions/${item.type}.jsp"/>
                                    <hr class="hr-soft-separation">
                                    </div>
                                    </div>
                                    </div>
                                    </div>
                                </c:forEach>

                                <div class="col-12">
                                    <div class="col-6">
                                        <div class="card">
                                            <div class="card-body">
                                                <div class="form-group survey-closing">
                                                    <label>Testo di chiusura</label>
                                                    <textarea class="form-control" rows="5" placeholder="visualizzato dopo la compilazione ...">${survey.closing}</textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </c:if>

        </div>
                    <div class="button-container" id="button-container">
                        <input type="button" id="backbutton" value="Precedente"/>
                        <input type="button" id="nextbutton" value="Successiva"/>
                     </div>

    </div>

</div>
</form>
<script type="text/javascript">
    const array = "${printAll}".split("_&_");
    array.forEach(element => console.log(element));
</script>
<jsp:include page="footer.jsp" />
