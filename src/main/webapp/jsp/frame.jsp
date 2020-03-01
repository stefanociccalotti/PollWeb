<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html dir="ltr" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="resources/assets/images/favicon.png">
    <title>PollWeb - Sondaggi</title>
    <!-- Custom CSS -->
    <link href="./resources/dist/css/style.min.css" rel="stylesheet">
    <link href="${pageCss}" rel="stylesheet">
</head>

<body>

<div id="main-wrapper" data-navbarbg="skin6" data-theme="light" data-layout="vertical" data-sidebartype="full" data-boxed-layout="full">

    <header class="topbar" data-navbarbg="skin6">
        <nav class="navbar top-navbar navbar-expand-md navbar-light">
            <div class="navbar-header" data-logobg="skin5">
                <!-- This is for the sidebar toggle which is visible on mobile only -->
                <a class="nav-toggler waves-effect waves-light d-block d-md-none" href="javascript:void(0)">
                    <i class="ti-menu ti-close"></i>
                </a>

                <div class="navbar-brand">
                    <a href="#" class="logo">
                        <!-- Logo icon -->
                        <b class="logo-icon">
                            <!-- Light Logo icon -->
                            <img src="./resources/assets/images/logo-light-icon.png" style="height:50px;" alt="homepage" class="light-logo" />
                        </b>
                        <!--End Logo icon -->
                        <!-- Logo text -->
                        <span class="logo-text">
                            <!-- Light Logo text -->
                                <img src="./resources/assets/images/logo-light-text.png" style="margin:.3em 0 0 .9em;" class="light-logo" alt="homepage" />
                            </span>
                    </a>
                </div>

                <a class="topbartoggler d-block d-md-none waves-effect waves-light" href="javascript:void(0)" data-toggle="collapse" data-target="#navbarSupportedContent"
                   aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <i class="ti-more"></i>
                </a>
            </div>

            <div class="navbar-collapse collapse" id="navbarSupportedContent" data-navbarbg="skin6" style="justify-content:space-between">
                <p class="nav-item" style="margin:auto 0 auto 1em;font-size:1.5em;font-weight:100;">
                    <c:choose>
                        <c:when test="${sessionScope.user != null}">Crea, gestisci e monitora i tuoi sondaggi . . .</c:when>
                        <c:otherwise>Benvenuto! Puoi procedere a compilare il sondaggio . . .</c:otherwise>
                    </c:choose>
                </p>
                <div class="navbar-nav float-right">
                    <div class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle text-muted waves-effect waves-dark pro-pic" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <c:choose>
                                <c:when test="${sessionScope.user!=null}"><span><c:out value="${sessionScope.user}"/></span><i class="m-r-0 mdi mdi-menu-down"></i></c:when>
                                <c:otherwise><span><c:out value="Guest User"/></span></c:otherwise>
                            </c:choose>
                        </a>
                        <c:if test="${sessionScope.user != null}">
                        <div class="dropdown-menu dropdown-menu-right user-dd animated" style="position: absolute;will-change: transform;top: 0px;left: 0px;transform: translate3d(0px, 62px, 0px)!important;">
                            <a class="dropdown-item" href="profile"><i class="ti-user m-r-5 m-l-5"></i> My Profile</a>
                            <a class="dropdown-item" href="logout"><i class="ti-wallet m-r-5 m-l-5"></i> Logout</a>
                        </div>
                        </c:if>
                    </div>
                    <div style="margin:auto">
                        <img src="https://image.flaticon.com/icons/svg/21/21104.svg" alt="user" class="rounded-circle" width="31">
                    </div>
                </div>

            </div>
        </nav>
    </header>

    <aside class="left-sidebar" data-sidebarbg="skin5">
        <!-- Sidebar scroll-->
        <c:choose>
            <c:when test="${sessionScope.user != null}">
                <div class="scroll-sidebar">
                    <!-- Sidebar navigation-->
                    <nav class="sidebar-nav">
                        <ul id="sidebarnav">
                            <li class="sidebar-item">
                                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="home" aria-expanded="false">
                                    <i class="mdi mdi-av-timer"></i>
                                    <span class="hide-menu">Dashboard</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="profile" aria-expanded="false">
                                    <i class="mdi mdi-account-network"></i>
                                    <span class="hide-menu">Profilo</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="viewSurveys" aria-expanded="false">
                                    <i class="mdi mdi-newspaper"></i>
                                    <span class="hide-menu">Sondaggi</span>
                                </a>
                            </li>
                            <c:if test="${sessionScope.type.equals('administrator')}">
                            <li class="sidebar-item">
                                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="curators" aria-expanded="false">
                                    <i class="mdi mdi-account-key"></i>
                                    <span class="hide-menu">Modifica Gestori</span>
                                </a>
                            </li>
                            </c:if>
                        </ul>
                        <c:if test="${requestScope.opening != null}">
                            <div id="surveyInfo" size="${requestScope.numberOfQuestions}" style="color:#fff;padding:0em 1em 0 1em;">
                                <hr style="background-color:#fff;">
                                <p style="font-size:1.2em;font-weight:100;padding-left:.4em;">
                                        ${requestScope.opening}
                                </p>
                                <hr style="background-color:#fff;">
                            </div>
                        </c:if>
                    </nav>
                    <!-- End Sidebar navigation -->
                </div>
            </c:when>
            <c:otherwise>
                <div id="surveyInfo" class="scroll-sidebar" size="${requestScope.numberOfQuestions}" style="color:#fff;padding:0em 1em 0 1em;">
                    <hr style="background-color:#fff;">
                    <p style="font-size:1.2em;font-weight:100;padding-left:.4em;">
                        ${requestScope.opening}
                    </p>
                    <hr style="background-color:#fff;">
                </div>
            </c:otherwise>
        </c:choose>
        <!-- End Sidebar scroll-->
    </aside>

<!-- TAG DIV, BODY E HTML PRESENTE NEL FOOTER-->