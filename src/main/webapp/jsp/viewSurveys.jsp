<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<jsp:include page="frame.jsp" />

<div class="page-wrapper">

    <div class="page-breadcrumb">
        <div class="row">
            <div class="col-5 align-self-center">
                <h4 class="page-title">I tuoi sondaggi.</h4>
            </div>
        </div>
    </div>

    <div class="container-fluid">

        <div class="addSurvey-button">
            <a href="surveyEditor">
                <img class="new-survey-img" src="./resources/images/icons/addSurvey.png"/>
            </a>
        </div>

        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title">Aiuto:</h4>
                        <h5 class="card-subtitle">Verde: pubblico-aperto | Viola: privato-aperto | Rosso: chiuso | Grigio chiaro: salvato | Blu: ?</h5>
                    </div>
                    <div class="table-responsive table-hover" style="overflow: visible;">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">Titolo</th>
                                <th scope="col">Stato</th>
                                <th scope="col">Descrizione</th>
                                <th scope="col">Modifica<i class="m-r-0 mdi mdi-lead-pencil"></i></th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach items="${list}" var="item">
                            <tr class="table-${item.statusCss}">
                                <th scope="row">${item.title}</th>
                                <td>${item.status} - ${item.privacy}</td>
                                <td>${item.opening}</td>
                                <td class="edit-td">
                                    <ul class="edit-nav">
                                        <li class="nav-item dropdown">
                                            <a class="nav-link dropdown-toggle text-muted waves-effect waves-dark edit-survey" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                <span style="display:flex;align-self:center;">Scegli</span>
                                                <i class="m-r-0 mdi mdi-menu-down"></i>
                                            </a>
                                            <div class="dropdown-menu dropdown-menu-right user-dd animated">
                                                <c:if test="${!item.privacy.equals('pubblico')}"><a class="dropdown-item" href="participantsEditor?surveyid=${item.id}&a=add"><i class="ti-plus m-r-5 m-l-5"></i> Aggiungi Partecipante</a></c:if>
                                                <c:if test="${!item.privacy.equals('pubblico')}"><a class="dropdown-item" href="participantsEditor?surveyid=${item.id}"><i class="ti-agenda m-r-5 m-l-5"></i> Modifica Partecipanti</a></c:if>
                                                <c:if test="${!item.status.equals('pubblicato')}"><a class="dropdown-item" href="surveyEditor?survey=${item.id}"><i class="ti-write m-r-5 m-l-5"></i> Modifica Contenuto</a></c:if>
                                                <form action="viewSurveys" method="POST"><input type="hidden" name="surveyid" value="${item.id}" /><input type="hidden" name="action" value="viewresult" /><a class="dropdown-item" href="javascript:void(0)" onclick="this.parentNode.submit()"><i class="ti-search m-r-5 m-l-5"></i> Vedi Risultato</a></form>
                                                <c:if test="${!item.status.equals('chiuso')}"><form action="viewSurveys" method="POST"><input type="hidden" name="surveyid" value="${item.id}" /><input type="hidden" name="action" value="closesurvey" /><a class="dropdown-item" href="javascript:void(0)" onclick="this.parentNode.submit()"><i class="ti-close m-r-5 m-l-5"></i> Chiudi Sondaggio</a></form></c:if>
                                            </div>
                                        </li>
                                    </ul>
                                </td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div><!--END CARD-->
            </div>
        </div>
    </div>

</div>

<jsp:include page="footer.jsp" />