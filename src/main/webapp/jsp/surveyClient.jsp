<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="frame.jsp" />

<div class="page-wrapper">

    <div class="page-breadcrumb">
        <div class="row">
            <div class="col-5 align-self-center">
                <h4 class="page-title">Titolo del sondaggio che ho creato: cosa sto chiedendo?</h4>
            </div>
        </div>
    </div>

    <div class="container-fluid">

        <div class="survey-container" id="survey-container">

            <div class="col-12" id="slide">
                <div class="col-6">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">Questa è la mia domanda a risposta singola?</h4>
                            <div class="form-group">
                                <div class="">
                                    <div class="custom-control custom-radio">
                                        <input type="radio" id="customRadio1" name="customRadio" class="custom-control-input">
                                        <label class="custom-control-label" for="customRadio1">Questa è la prima opzione alla domanda a risposta singola</label>
                                    </div>
                                    <div class="custom-control custom-radio">
                                        <input type="radio" id="customRadio2" name="customRadio" class="custom-control-input">
                                        <label class="custom-control-label" for="customRadio2">Seconda opzione</label>
                                    </div>
                                </div>
                            </div>
                            <h5 class="card-subtitle"> Questa è la nota della domanda </h5>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-12">
                <div class="col-6">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">Questa è la mia domanda a risposta multipla?</h4>
                            <div class="form-group row p-t-20">
                                <div class="">
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="custom-control-input" id="customCheck1">
                                        <label class="custom-control-label" for="customCheck1">Questa è una possibile risposta, lunga e con molti caratteri (numero uno)</label>
                                    </div>
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="custom-control-input" id="customCheck2">
                                        <label class="custom-control-label" for="customCheck2">Risposta 2</label>
                                    </div>
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="custom-control-input" id="customCheck3">
                                        <label class="custom-control-label" for="customCheck3">Risposta 3</label>
                                    </div>
                                </div>
                            </div>
                            <h5 class="card-subtitle"> Questa è la nota della domanda </h5>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-12">
                <div class="col-6">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">Questa è la mia domanda con risposta aperta?</h4>
                            <div class="form-group">
                                <label>Scrivi una risposta con un numero di caratteri compreso tra x e y</label>
                                <textarea class="form-control" rows="5" placeholder="La mia risposta ..."></textarea>
                            </div>
                            <h5 class="card-subtitle"> Questa è la nota della domanda </h5>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-12">
                <div class="col-6">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">Questa è la mia domanda con risposta a numero?</h4>
                            <div class="form-group number-answer">
                                <input type="number" class="form-control number-answer" placeholder="...">
                            </div>
                            <h5 class="card-subtitle"> Questa è la nota della domanda </h5>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-12">
                <div class="col-6">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">Questa è la mia domanda con risposta con data?</h4>
                            <div class="form-group date-answer">
                                <input type="date" class="form-control" placeholder="...">
                            </div>
                            <h5 class="card-subtitle"> Questa è la nota della domanda </h5>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <div class="button-container" id="button-container">
            <input type="button" id="backbutton" value="Precedente"/>
            <input type="button" id="nextbutton" value="Successiva"/>
        </div>

    </div>
</div>
<jsp:include page="footer.jsp" />
