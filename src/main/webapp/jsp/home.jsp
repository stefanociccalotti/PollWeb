<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="it.univaq.disim.controller.HomeController" %>
<%@ page import="it.univaq.disim.model.SurveyModel" %>
<%@ page import="java.util.ArrayList" %>

<jsp:include page="frame.jsp" />

<div class="page-wrapper">

    <div class="page-breadcrumb">
        <div class="row">
            <div class="col-5 align-self-center">
                <h4 class="page-title">Benvenuto!</h4>
            </div>
        </div>
    </div>

    <div class="container-fluid">
        <div class="row">
            <!-- column -->
            <div class="col-12">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title">I tuoi sondaggi:</h4>
                    </div>
                    <div class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th class="border-top-0">NOME</th>
                                <th class="border-top-0">STATO</th>
                                <th class="border-top-0">TIPO</th>
                                <th class="border-top-0">URL</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${list}" var="item">
                                <tr>
                                    <td class="txt-oflo"><c:out value="${item.title}" /></td>
                                    <td><span class="label label-${item.statusCss} label-rounded"><c:out value="${item.status}" /></span> </td>
                                    <td><span class="font-medium"><c:out value="${item.privacy}" /></span></td>
                                    <td class="txt-oflo"><c:out value="${item.url}" /></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ============================================================== -->
    <!-- End Container fluid  -->
    <!-- ============================================================== -->
</div>

<!-- ============================================================== -->
<!-- ============================================================== -->
<!-- End Page wrapper  -->
<!-- ============================================================== -->

<jsp:include page="footer.jsp" />