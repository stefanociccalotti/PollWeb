<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="frame.jsp" />

    <div class="page-wrapper">

        <div class="page-breadcrumb">
            <div class="row">
                <div class="col-5 align-self-center" >
                    <h4 class="page-title" >Modifica Partecipanti:</h4>
                </div>
            </div>
        </div>

        <div class="container-fluid">
            <div class="row" >
                <div class="col-lg-12 ">
                    <div class="card">
                        <div class="card-body">
                        <c:forEach items="${lisparticipants}" var="item">
                            <form class="form-horizontal form-material" method="post" action="participantsEditor">
                                <input name="action" type="hidden" value="updateparticipants" placeholder="" class="form-control form-control-line">
                                <input name="idparticipants" type="hidden" value="${item.id}" placeholder="" class="form-control form-control-line">
                                <div class="form-group">
                                    <label for="example-email" class="col-md-12">Email</label>
                                    <div class="col-md-12">
                                        <input name="mail" type="email" value="${item.mail}" placeholder="" class="form-control form-control-line" name="example-email" id="emailpart">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Password</label>
                                    <div class="col-md-12">
                                        <input name="password" id="passwordpart" type="text" value="${item.password}" class="form-control form-control-line">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <button class="btn btn-success">Modifica</button>
                                    </div>
                                </div>
                            </form>
                            <br/>
                        </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<jsp:include page="footer.jsp" />
