<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<c:set var="opening" scope="request" value="${survey.opening}"/>
<c:set var="numberOfQuestions" scope="request" value="${numberOfQuestions}"/>
<jsp:include page="frame.jsp" />

<form method="post" action="surveyClient">
    <div class="page-wrapper">
        <div class="page-breadcrumb">
            <div class="row">
                <div class="col-5 align-self-center">
                    <h4 class="page-title" style="font-weight:400;font-size:1.7em">${survey.title}</h4>
                </div>
            </div>
        </div>
        <div class="container-fluid" style="padding:66px;">
            <div class="survey-container">
                <input type="hidden" name="idans" value="${surveyid}"/>
                <c:if test="${questions != null}">
                    <c:forEach items="${questions}" var="item">
                        <div id="slide" class="col-12">
                            <div class="col-6">
                                <div class="card">
                                    <div class="card-body">
                                        <c:set var="question" scope="request" value="${item}"/>
                                        <jsp:include page="surveyClientView/${item.type}Client.jsp"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="col-12">
                        <div class="col-6">
                            <div class="card">
                                <div class="card-body">
                                    <div class="form-group survey-closing" style="margin:0;">
                                        <p>${survey.closing}</p>
                                        <h5>Grazie per aver compilato il sondaggio. Se sei sicuro delle tue risposte clicca su "Invia"!</h5>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
            <div class="button-container">
                <div class="backNextButtons">
                    <input type="button" id="backbutton" value="Precedente"/>
                    <input type="button" id="nextbutton" value="Successiva"/>
                </div>
                    <input type="submit" id="submitbutton" value="Invia"/>
             </div>
        </div>

    </div>
</form>
<script type="text/javascript">
    const array = "${printAll}".split("_&_");
    array.forEach(element => console.log(element));
</script>
<jsp:include page="footer.jsp" />
