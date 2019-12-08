<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="it.univaq.disim.model.SurveyModel" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="it.univaq.disim.controller.ContentLoaderController" %>

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

        <a href="create-survey.html">
            <img class="new-survey-img" src="../resources/images/add-icon.png"/>
        </a>

        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title">Aiuto:</h4>
                        <h5 class="card-subtitle">Verde: pubblico-aperto | Viola: privato-aperto | Rosso: chiuso | Grigio chiaro: salvato | Blu: ?</h5>
                    </div>
                    <div class="table-responsive table-hover">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">Titolo</th>
                                <th scope="col">Stato</th>
                                <th scope="col">Descrizione</th>
                                <th scope="col">Modifica<i class="m-r-0 mdi mdi-lead-pencil"></i></th>
                            </tr>
                            </thead>
                            <%

                                ArrayList<SurveyModel> list = new ContentLoaderController().loadViewSurvey((Integer) session.getAttribute("userID"));
                                request.setAttribute("list",list);

                            %>
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
                                                <a class="dropdown-item" href="javascript:void(0)"><i class="ti-agenda m-r-5 m-l-5"></i> Modifica Partecipanti</a>
                                                <a class="dropdown-item" href="javascript:void(0)"><i class="ti-write m-r-5 m-l-5"></i> Modifica Contenuto</a>
                                                <a class="dropdown-item" href="javascript:void(0)"><i class="ti-eye m-r-5 m-l-5"></i> Preview Sondaggio</a>
                                                <a class="dropdown-item" href="javascript:void(0)"><i class="ti-search m-r-5 m-l-5"></i> Vedi Risultato</a>
                                                <a class="dropdown-item" href="javascript:void(0)"><i class="ti-close m-r-5 m-l-5"></i> Chiudi Sondaggio</a>
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