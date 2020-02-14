<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="frame.jsp" />
<link href="./resources/css/popup.css" rel="stylesheet">
<link href="./resources/dist/css/viewSurveys.css" rel="stylesheet">
    <div class="page-wrapper">

            <div class="page-breadcrumb">
                <div class="row">
                    <div class="col-5 align-self-center">
                        <h4 class="page-title">Lista gestori:</h4>
                    </div>
                </div>
            </div>

            <div class="container-fluid">
                <div class="addSurvey-button">
                    <a href="#creategestore">
                        <img class="new-survey-img" src="./resources/images/icons/addUser.png"/>
                    </a>
                </div>
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                                <div class="card-body">
                                        <h4 class="card-title">Da qui puoi gestire tutti i gestori presenti sul sito.</h4>
                                        <h5 class="card-subtitle">Cliccando sul menu 'scegli', è possibile modificare o eliminare definitivamente il gestore.</h5>
                                    </div>
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th scope="col">ID</th>
                                            <th scope="col">Nome</th>
                                            <th scope="col">Cognome</th>
                                            <th scope="col">Username</th>
                                            <th scope="col">E-mail</th>
                                            <th scope="col">Modifica<i class="m-r-0 mdi mdi-lead-pencil"></i></th>
                                        </tr>
                                    </thead>
                                    <tbody id="tablegestore">
                                      <c:forEach items="${curatorslist}" var="item">
                                        <tr class="test1">
                                            <th scope="row" id="IDgestore"><c:out value="${item.id}" /></th>
                                            <td id="nomegestore"><c:out value="${item.name}" /></td>
                                            <td><c:out value="${item.surname}" /></td>
                                            <td><c:out value="${item.username}" /></td>
                                            <td><c:out value="${item.mail}" /></td>
                                            <td class="edit-td">
                                                <ul class="edit-nav">
                                                    <li class="nav-item dropdown">
                                                        <a class="nav-link dropdown-toggle text-muted waves-effect waves-dark edit-survey" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                            <span style="display:flex;align-self:center;">Scegli</span>
                                                            <i class="m-r-0 mdi mdi-menu-down"></i>
                                                        </a>
                                                        <div class="dropdown-menu dropdown-menu-right user-dd animated">
                                                            <a class="dropdown-item" href="#popup1" onclick="updateGestore(this)"><i class="ti-agenda m-r-5 m-l-5"></i> Modifica Gestore</a>
                                                            <form method="post" action="curators?id=${item.id}">
                                                                <a class="dropdown-item" href="javascript:;" onclick="parentNode.submit();"><i class="ti-close m-r-5 m-l-5"></i> Rimuovi Gestore </a>
                                                                <input type="hidden" name="userid" value="${item.id}"/>
                                                                <input type="hidden" name="action" value="delete"/>
                                                            </form>
                                                        </div>
                                                    </li>                            
                                                </ul>
                                            </td>   
                                        </tr>
                                     </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <div id="popup1" class="overlay" style="margin: auto;">
                    <div class="popup">
                        <h2 style="text-align: center">Modifica del gestore</h2>
                    </br>
                        <a class="close" href="#">&times;</a>
                        <div class="content">
                            <form class="form-horizontal form-material" action="curators" method="post">
                                <input type="hidden" name="action" value="update"/>
                                <input type="hidden" name="userid" id="gestoreid" value=""/>
                                <div class="form-group">
                                    <label class="col-md-12">Nome</label>
                                    <div class="col-md-12">
                                        <input type="text" name="nomeg" id="gestorenome" value="" class="form-control form-control-line">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Cognome</label>
                                    <div class="col-md-12">
                                        <input type="text" name="cognomeg" id="gestorecognome" value="" class="form-control form-control-line">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="example-email" class="col-md-12">Username</label>
                                    <div class="col-md-12">
                                        <input type="text" name="userg" id="gestoreusername"  value="" class="form-control form-control-line">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="example-email" class="col-md-12">Email</label>
                                    <div class="col-md-12">
                                        <input type="email" name="mailg" id="gestoremail"  value="" class="form-control form-control-line" name="example-email" id="example-email">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-12">Reset Password</label>
                                    <div class="col-md-12">
                                        <input type="password" name="passwordg" id="gestorepassword" value="" class="form-control form-control-line">
                                    </div>
                                </div>
                                <br/>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <button class="btn btn-success" style="display: inline !important">Update Profile</button>
                                    </div>
                                </div>
                                </form>
                        </div>
                    </div>
                </div>

                <div id="creategestore" class="overlay" style="margin: auto;">
                        <div class="popup">
                            <h2 style="text-align: center">Crea nuovo Gestore</h2>
                        </br>
                            <a class="close" href="#">&times;</a>
                            <div class="content">
                                <form class="form-horizontal form-material" action="curators" method="post">
                                    <input type="hidden" name="action" value="new"/>
                                    <div class="form-group">
                                        <label for="example-email" class="col-md-12">Username</label>
                                        <div class="col-md-12">
                                            <input type="text" name="usernamen" id="gestoreusername"  value="" class="form-control form-control-line" >
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="example-email" class="col-md-12">Mail</label>
                                        <div class="col-md-12">
                                            <input type="email" name="mailn" id="gestoremail"  value="" class="form-control form-control-line" name="example-email" id="example-email">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">Password</label>
                                        <div class="col-md-12">
                                            <input type="password" name="passwordn" id="gestorepassword" value="password" class="form-control form-control-line">
                                        </div>
                                    </div>
                                    <br/>
                                    <div class="form-group">
                                        <div class="col-sm-12">
                                            <button class="btn btn-success" style="display: inline !important">Crea</button>
                                        </div>
                                    </div>
                                    </form>
                            </div>
                        </div>
                    </div>
            </div>
        </div>
        <jsp:include page="footer.jsp" />