<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<jsp:include page="frame.jsp" />
<div class="main-wrapper">

    <div class="preloader">
        <div class="lds-ripple">
            <div class="lds-pos"></div>
            <div class="lds-pos"></div>
        </div>
    </div>

    <div class="error-box">
        <div class="error-body text-center">
            <h3 class="text-uppercase error-subtitle">GRAZIE PER AVER RISPOSTO CORRETTAMENTE AL QUESTIONARIO</h3>
            <p class="text-muted m-t-30 m-b-30">LE TUE RISPOSTE SONO STATE RICEVUTE CORRETTAMENTE!</p>
    </div>

</div>

<jsp:include page="footer.jsp" />