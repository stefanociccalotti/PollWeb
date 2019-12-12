<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="frame.jsp" />

<div class="page-wrapper">

    <div class="page-breadcrumb">
        <div class="row">
            <div class="col-5 align-self-center">
                <h4 class="page-title">Crea Sondaggio</h4>
            </div>
        </div>
    </div>

    <div class="container-fluid">
        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-body">
                        <form class="form-horizontal m-t-30">
                            <h4 class="card-title">Informazioni sul sondaggio:</h4>
                            <h5 class="card-subtitle"> I campi contrassegnati con "*" sono obbligatori </h5>
                            <!--Riservato-->
                            <div class="form-group">
                                <label>Seleziona questo checkbox se desideri che il sondaggio sia riservato (puoi farlo anche in seguito):</label>
                                <div class="col-sm-4">
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="custom-control-input" id="customCheck1">
                                        <label class="custom-control-label" for="customCheck1">Riservato</label>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label>Titolo *</label>
                                <input type="text" class="form-control" placeholder="titolo del sondaggio ...">
                            </div>
                            <div class="form-group">
                                <label>Testo di apertura</label>
                                <textarea class="form-control" rows="5" placeholder="visualizzato dall'utente prima di compilare il sondaggio ..."></textarea>
                            </div>
                            <div class="form-group survey-closing">
                                <label>Testo di chiusura</label>
                                <textarea class="form-control" rows="5" placeholder="visualizzato dopo la compilazione ..."></textarea>
                            </div>

                            <hr class="hr-heavy-separation">

                            <h4 class="card-title h4-question">Creazione domande:</h4>
                            <h5 class="card-subtitle">Nota: per creare un sondaggio sono necessarie almeno 5 domande.</h5>

                            <hr class="hr-soft-separation">

                            <div class="create-question-container">

                                <div class="choose-question-type">
                                    <div class="form-group select-question-container">
                                        <label>Scegli il tipo di domanda</label>
                                        <select class="custom-select col-12">
                                            <option selected>Scegli...</option>
                                            <option value="1">Risposta singola</option>
                                            <option value="2">Risposta multipla</option>
                                            <option value="3">Domanda Aperta</option>
                                            <option value="4">Numero</option>
                                            <option value="5">Data</option>
                                        </select>
                                    </div>
                                    <input type="button" value="Reset"/>
                                </div>
                                <!--DOMANDA 1-->
                                <div class="form-group type-single-answer selected-question">
                                    <div class="form-group">
                                        <div class="col-sm-4">
                                            <div class="custom-control custom-checkbox">
                                                <input type="checkbox" class="custom-control-input" id="1-s-a-m">
                                                <label class="custom-control-label" for="1-s-a-m">Obbligatoria</label>
                                                <br/>
                                                <span class="help-block"><small>Seleziona questa casella se la domanda è obbligatoria.</small></span>
                                            </div>
                                        </div>
                                    </div>
                                    <input type="text" class="form-control" placeholder="Testo della domanda ...">
                                    <div class="col-sm-4 single-answers">
                                        <div class="custom-control custom-radio single-answer-text-radio">
                                            <input type="radio" id="s-a-1-1" name="customRadio" class="custom-control-input">
                                            <label class="custom-control-label" for="s-a-1-1"><input type="text" placeholder=" Testo della risposta ..."></label>
                                        </div>
                                        <div class="custom-control custom-radio single-answer-text-radio">
                                            <input type="radio" id="s-a-1-2" name="customRadio" class="custom-control-input">
                                            <label class="custom-control-label" for="s-a-1-2"><input type="text" placeholder=" Testo della risposta ..."></label>
                                        </div>
                                        <div class="m-icon"><i class="m-r-10 mdi mdi-plus-circle-outline"></i><span>Aggiungi Risposta</span></div>
                                    </div>
                                    <input type="text" class="form-control" placeholder="Scrivi una nota se lo desideri ...">
                                </div>

                            </div>

                            <hr class="hr-soft-separation">

                            <div class="create-question-container">

                                <div class="choose-question-type">
                                    <div class="form-group select-question-container">
                                        <label>Scegli il tipo di domanda</label>
                                        <select class="custom-select col-12">
                                            <option selected>Scegli...</option>
                                            <option value="1">Risposta singola</option>
                                            <option value="2">Risposta multipla</option>
                                            <option value="3">Domanda Aperta</option>
                                            <option value="4">Numero</option>
                                            <option value="5">Data</option>
                                        </select>
                                    </div>
                                    <input type="button" value="Reset"/>
                                </div>
                                <!--DOMANDA 2-->
                                <div class="form-group type-multiple-answer selected-question">
                                    <div class="form-group">
                                        <div class="col-sm-4">
                                            <div class="custom-control custom-checkbox">
                                                <input type="checkbox" class="custom-control-input" id="2-m-a-m">
                                                <label class="custom-control-label" for="2-m-a-m">Obbligatoria</label>
                                                <br/>
                                                <span class="help-block"><small>Seleziona questa casella se la domanda è obbligatoria.</small></span>
                                            </div>
                                        </div>
                                    </div>
                                    <input type="text" class="form-control" placeholder="Testo della domanda ...">
                                    <div class="form-group">
                                        <div class="col-sm-4 multiple-answer">
                                            <div class="custom-control custom-checkbox multiple-answer-text-checkbox">
                                                <input type="checkbox" class="custom-control-input" id="m-a-2-1">
                                                <label class="custom-control-label" for="m-a-2-1"><input type="text" placeholder=" Testo della risposta ..."></label>
                                            </div>
                                            <div class="custom-control custom-checkbox">
                                                <input type="checkbox" class="custom-control-input" id="m-a-2-2">
                                                <label class="custom-control-label" for="m-a-2-2"><input type="text" placeholder=" Testo della risposta ..."></label>
                                            </div>
                                        </div>
                                        <div class="m-icon"><i class="m-r-10 mdi mdi-plus-circle-outline"></i><span>Aggiungi Risposta</span></div>
                                    </div>
                                    <input type="text" class="form-control" placeholder="Scrivi una nota se lo desideri ...">
                                </div>

                            </div>

                            <hr class="hr-soft-separation">

                            <div class="create-question-container">

                                <div class="choose-question-type">
                                    <div class="form-group select-question-container">
                                        <label>Scegli il tipo di domanda</label>
                                        <select class="custom-select col-12">
                                            <option selected>Scegli...</option>
                                            <option value="1">Risposta singola</option>
                                            <option value="2">Risposta multipla</option>
                                            <option value="3">Domanda Aperta</option>
                                            <option value="4">Numero</option>
                                            <option value="5">Data</option>
                                        </select>
                                    </div>
                                    <input type="button" value="Reset"/>
                                </div>
                                <!--DOMANDA 3-->
                                <div class="form-group type-open-question selected-question">
                                    <div class="form-group">
                                        <div class="col-sm-4">
                                            <div class="custom-control custom-checkbox">
                                                <input type="checkbox" class="custom-control-input" id="3-o-q-m">
                                                <label class="custom-control-label" for="3-o-q-m">Obbligatoria</label>
                                                <br/>
                                                <span class="help-block"><small>Seleziona questa casella se la domanda è obbligatoria.</small></span>
                                            </div>
                                        </div>
                                    </div>
                                    <input type="text" class="form-control" placeholder="Testo della domanda ...">
                                    <span class="help-block"><small>Non è presente alcun tool per le risposte perché stai creando una domanda aperta.</small></span>
                                    <input type="text" class="form-control" placeholder="Scrivi una nota se lo desideri ...">
                                </div>

                            </div>

                            <hr class="hr-soft-separation">

                            <div class="create-question-container">

                                <div class="choose-question-type">
                                    <div class="form-group select-question-container">
                                        <label>Scegli il tipo di domanda</label>
                                        <select class="custom-select col-12">
                                            <option selected>Scegli...</option>
                                            <option value="1">Risposta singola</option>
                                            <option value="2">Risposta multipla</option>
                                            <option value="3">Domanda Aperta</option>
                                            <option value="4">Numero</option>
                                            <option value="5">Data</option>
                                        </select>
                                    </div>
                                    <input type="button" value="Reset"/>
                                </div>

                                <!--DOMANDA 4-->
                                <div class="form-group type-number-question selected-question">
                                    <div class="form-group">
                                        <div class="col-sm-4">
                                            <div class="custom-control custom-checkbox">
                                                <input type="checkbox" class="custom-control-input" id="4-n-q-m">
                                                <label class="custom-control-label" for="4-n-q-m">Obbligatoria</label>
                                                <br/>
                                                <span class="help-block"><small>Seleziona questa casella se la domanda è obbligatoria.</small></span>
                                            </div>
                                        </div>
                                    </div>
                                    <input type="text" class="form-control" placeholder="Testo della domanda ...">
                                    <span class="help-block"><small>Non è presente alcun tool per le risposte perché stai creando una domanda con un numero come risposta.</small></span>
                                    <input type="text" class="form-control" placeholder="Scrivi una nota se lo desideri ...">
                                </div>

                            </div>

                            <hr class="hr-soft-separation">

                            <div class="create-question-container">

                                <div class="choose-question-type">
                                    <div class="form-group select-question-container">
                                        <label>Scegli il tipo di domanda</label>
                                        <select class="custom-select col-12">
                                            <option selected>Scegli...</option>
                                            <option value="1">Risposta singola</option>
                                            <option value="2">Risposta multipla</option>
                                            <option value="3">Domanda Aperta</option>
                                            <option value="4">Numero</option>
                                            <option value="5">Data</option>
                                        </select>
                                    </div>
                                    <input type="button" value="Reset"/>
                                </div>

                                <!--DOMANDA 5-->
                                <div class="form-group type-date-question selected-question">
                                    <div class="form-group">
                                        <div class="col-sm-4">
                                            <div class="custom-control custom-checkbox">
                                                <input type="checkbox" class="custom-control-input" id="5-d-q-m">
                                                <label class="custom-control-label" for="5-d-q-m">Obbligatoria</label>
                                                <br/>
                                                <span class="help-block"><small>Seleziona questa casella se la domanda è obbligatoria.</small></span>
                                            </div>
                                        </div>
                                    </div>
                                    <input type="text" class="form-control" placeholder="Testo della domanda ...">
                                    <span class="help-block"><small>Non è presente alcun tool per le risposte perché stai creando una domanda con una data come risposta.</small></span>
                                    <input type="text" class="form-control" placeholder="Scrivi una nota se lo desideri ...">
                                </div>

                            </div>

                            <hr class="hr-soft-separation">

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />