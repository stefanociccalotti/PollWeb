<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="frame.jsp" />


    <div class="page-wrapper" style="display:flex;flex-direction:column">
        <!-- ============================================================== -->
        <!-- Bread crumb and right sidebar toggle -->
        <!-- ============================================================== -->
        <div class="page-breadcrumb">
            <div class="row">
                <div class="col-5 align-self-center">
                    <h4 class="page-title">Profile</h4>
                </div>
                <div class="col-7 align-self-center">
                    <div class="d-flex align-items-center justify-content-end">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item">
                                    <a href="#">Home</a>
                                </li>
                                <li class="breadcrumb-item active" aria-current="page">Profile</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
        <!-- ============================================================== -->
        <!-- End Bread crumb and right sidebar toggle -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Container fluid  -->
        <!-- ============================================================== -->
        <div class="container-fluid">
            <!-- ============================================================== -->
            <!-- Start Page Content -->
            <!-- ============================================================== -->
            <!-- Row -->
            <div class="" style="display:flex;flex-direction:row;justify-content: space-between;">
                <!-- Column -->
                <div class="col-lg-2 col-xlg-3 col-md-5" style="padding:0">
                    <div class="card">
                        <div class="card-body">
                            <div style="display:flex;flex-direction:column;align-items:flex-start">
                                <img src="./resources/images/icons/userIcon.jpg" class="" width="150" />
                                <div>
                                    <h4 class="card-title m-t-10"><c:out value="${userinfo.username}" /></h4>
                                    <h6 class="card-subtitle"><c:out value="${userinfo.type}" /></h6>
                                    <div class="row text-center justify-content-md-center"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Column -->
                <!-- Column -->
                <div class="col-lg-10 col-xlg-9 col-md-7">
                    <div class="card">
                        <div class="card-body">
                            <form class="form-horizontal form-material" method="post" action="${pageContext.request.contextPath}/profile">
                                <div class="form-group">
                                    <label class="col-md-12">Nome</label>
                                    <div class="col-md-12">
                                        <input name="nome" type="text" value="<c:out value="${userinfo.name} " />" class="form-control form-control-line">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Cognome</label>
                                    <div class="col-md-12">
                                        <input name="cognome" type="text" value="<c:out value="${userinfo.surname} " />" class="form-control form-control-line">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="example-email" class="col-md-12">Email</label>
                                    <div class="col-md-12">
                                        <input name="email" type="email" value="<c:out value="${userinfo.mail}" />" class="form-control form-control-line" name="example-email" id="example-email">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Password</label>
                                    <div class="col-md-12">
                                        <input name="password" type="password" value="<c:out value="${userinfo.password}" />" class="form-control form-control-line">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Data Di Nascita</label>
                                    <div class="col-md-12">
                                        <input name="datanascita" type="date" value="<c:out value="${userinfo.birthday}" />" class="form-control form-control-line">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <button class="btn btn-success">Update Profile</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- Column -->
            </div>
            <!-- Row -->
            <!-- ============================================================== -->
            <!-- End PAge Content -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Right sidebar -->
            <!-- ============================================================== -->
            <!-- .right-sidebar -->
            <!-- ============================================================== -->
            <!-- End Right sidebar -->
            <!-- ============================================================== -->
        </div>
        <!-- ============================================================== -->
        <!-- End Container fluid  -->
        <!-- ============================================================== -->
    </div>
    <!-- ============================================================== -->
    <!-- End Page wrapper  -->
    <!-- ============================================================== -->
<jsp:include page="footer.jsp" />