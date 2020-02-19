document.onreadystatechange = () => {
    if (document.readyState === 'complete') {


        let selections;
        let singAnswers;
        let multAnswers;
        let  idGenerator = 0;

        setAddQuestion();
        setQuestionsToSelect();
        setAddSingAns();
        setAddMultAns();

        document.getElementById('submit').onclick = submitSurvey;

        //TODO - NOTA: QUI' IMPOSTO LA SELECT PER AGGIUNGERE UNA DOMANDA, POI RICHIAMO setQuestionsToSelect()

        function setAddQuestion() {

            let addQuestion = document.getElementById('addQuestion');

            addQuestion.onclick = function() {
                addQuestion.outerHTML = getChooseQuestion();
                setQuestionsToSelect();
                setAddQuestion();
            }

        }

        //TODO - NOTA: QUI' IMPOSTO LA SELECT PER OGNI TIPO DI DOMANDA

        function setQuestionsToSelect() {

            selections = document.getElementsByClassName('custom-select');

            for(let i = 0; i < selections.length; i++) {
                selections[i].onchange = function() {

                    let newQuestionType = this.options[this.selectedIndex].getAttribute('newQuestionType');
                    let newElement = document.createElement('div');
                    let div1 = selections[i].parentNode.parentNode.parentNode.parentNode;
                    let div2 = div1.children[1];
                    let toggleButton = selections[i].parentNode.parentNode.nextElementSibling;
                    let newID = idGenerator;

                    idGenerator++;
                    newElement.className = 'form-group selected-question';
                    newElement.setAttribute('questionType',getQuestionType(newQuestionType));
                    newElement.setAttribute('questionId','new');
                    newElement.innerHTML = getQuestionFrame(newQuestionType,newID);

                    div1.replaceChild(newElement,div2);
                    toggleButton.value = 'Elimina domanda';
                    toggleButton.style.backgroundColor = 'rgba(255, 0, 0, 0.65)';

                    if(newQuestionType === 'singAns') {
                        setAddSingAns();
                    } else if(newQuestionType === 'multAns') {
                        setAddMultAns();
                    }
                }
            }
            toggleQuestions();
        }

        function setAddSingAns() {
            singAnswers = document.getElementsByClassName('addSingAns');
            for (let i = 0; i < singAnswers.length; i++) {
                singAnswers[i].onclick = () => {
                    let nodeParent = singAnswers[i].parentNode;
                    let newID = idGenerator;
                    idGenerator++;
                    let newElements = getSingAnsBox(newID);
                    nodeParent.getElementsByClassName('singAnsMarker')[0].outerHTML = newElements;
                }
            }
        }

        function setAddMultAns() {

            multAnswers = document.getElementsByClassName('addMultAns');

            for (let i = 0; i < multAnswers.length; i++) {
                multAnswers[i].onclick = () => {
                    let nodeParent = multAnswers[i].parentNode;
                    let newID = idGenerator;
                    idGenerator++;
                    let newElements = getMultAnsBox(newID);
                    nodeParent.getElementsByClassName('multAnsMarker')[0].outerHTML = newElements;
                }
            }
        }

        function toggleQuestions() {

            let toggleQuestions = document.getElementsByClassName('toggleQuestion');

            for (let i = 0; i < toggleQuestions.length; i++) {
                toggleQuestions[i].onclick = () => {
                    let nodeToHide = toggleQuestions[i].parentNode.nextElementSibling;
                    if(nodeToHide.style.display === 'none') {
                        nodeToHide.style.display = '';
                        toggleQuestions[i].value = 'Elimina domanda';
                        toggleQuestions[i].style.backgroundColor = 'rgba(255, 0, 0, 0.65)';
                    } else {
                        nodeToHide.style.display = 'none';
                        toggleQuestions[i].value = 'Ripristina domanda';
                        toggleQuestions[i].style.backgroundColor = 'green';
                    }
                }
            }
        }

        function submitSurvey() {

            let surveyObj = createSurveyObj();

            let data = JSON.stringify(surveyObj);
            let http_request;

            if (window.XMLHttpRequest) { // Mozilla, Safari,...
                http_request = new XMLHttpRequest();
                if (http_request.overrideMimeType) {
                    http_request.overrideMimeType('text/xml');
                }
            } else if (window.ActiveXObject) { // IE
                try {
                    http_request = new ActiveXObject("Msxml2.XMLHTTP");
                } catch (e) {
                    try {
                        http_request = new ActiveXObject("Microsoft.XMLHTTP");
                    } catch (e) {}
                }
            }

            if (!http_request) {
                alert('Errore: non è stato possibile creare una istanza XMLHTTP');
                return false;
            }

            http_request.open('POST', 'http://localhost:8080/web-engineering-pollweb/surveyEditor', true);
            http_request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            http_request.onreadystatechange = function() { alertContents(http_request); };
            http_request.send('data='+encodeURIComponent(data));
        }

        function alertContents(http_request) {

            if (http_request.readyState == 4) {
                if (http_request.status == 200) {
                    alert(http_request.responseText);
                    if(surveyIsCreated()) {
                        alert('Stai per essere reindirizzato.');
                        window.location.href = 'http://localhost:8080/web-engineering-pollweb/viewSurveys';
                    }
                } else {
                    alert('Si è verificato un problema con la richiesta');
                }
            }

        }

        function createSurveyObj() {

            let questionsCollection = document.getElementsByClassName('selected-question');
            let survey = new Object();

            survey.id = getSurveyId();
            survey.privacy = checkSurveyPrivacy();
            survey.status = 'salvato';
            survey.title = document.getElementById('surveyTitle').value;
            survey.opening = document.getElementById('surveyOpening').value;
            survey.closing = document.getElementById('surveyClosing').value;

            let questions = [];
            let typesOfQuestion = ['type-single-answer','type-multiple-answer','type-open-question','type-number-question','type-date-question'];

            for(let i = 0; i < questionsCollection.length; i++) {

                let questionsItem = questionsCollection.item(i);
                let questionType = questionsItem.getAttribute('questionType');
                let toggle = questionsItem.style.display;

                if(toggle != 'none' && typesOfQuestion.includes(questionType)) {
                    questions[i] = new Question(questionsItem);
                }
                //TODO: si può comunicare all'utente che alcune domande non sono selezionate.
            }

            survey.questions = questions;

            return survey;

        }

        function Question(questionsItem) {
            this.questionID = questionsItem.getAttribute('questionId');
            this.mandatory = checkMandatoryQuestion(questionsItem);
            this.number = parseInt(questionsItem.getElementsByClassName('questionText').item(0).getAttribute('number'));
            this.text = getJsonStr(questionsItem);
            this.note = questionsItem.getElementsByClassName('questionNote').item(0).value;
        }

//====================================================FUNZIONI PER PULIRE IL CODICE====================================================//


        function getQuestionType(newQuestionType) {
            switch(newQuestionType) {
                case 'singAns':
                    return 'type-single-answer';
                case 'multAns':
                    return 'type-multiple-answer';
                case 'openQuest':
                    return 'type-open-question';
                case 'number':
                    return 'type-number-question';
                case 'date':
                    return 'type-date-question';
                default:
                    return 'none';
            }
        }

        function checkMandatoryQuestion(questionsItem) {
            if(questionsItem.getElementsByClassName('mandatoryCheckbox').item(0).checked) return 1;
            else return 0;
        }

        function checkSurveyPrivacy() {
            if(document.getElementById('surveyPrivacy').checked) return 'riservato';
            else return 'pubblico';
        }

        function getSurveyId() {
            let url_search = new URL(window.location.href).search;
            if(url_search) return parseInt(url_search.split('=')[1]);
            else return null;
        }

        function getJsonStr(questionsItem) {
            switch(questionsItem.getAttribute('questionType')) {
                case 'type-single-answer':
                    return {type: "singAns", answers: getQuestionAnswers(questionsItem), question: questionsItem.getElementsByClassName('questionText').item(0).value};
                case 'type-multiple-answer':
                    return {type: "multAns", answers: getQuestionAnswers(questionsItem), question: questionsItem.getElementsByClassName('questionText').item(0).value};
                case 'type-number-question':
                    return {type: "number", question: questionsItem.getElementsByClassName('questionText').item(0).value};
                case 'type-open-question':
                    return {type: "openQuest", question: questionsItem.getElementsByClassName('questionText').item(0).value};
                case 'type-date-question':
                    return {type: "date", question: questionsItem.getElementsByClassName('questionText').item(0).value};
                case 'none':
                    return {type: "No question type.", question: "Question unselected."};
                default:
                    alert('Errore riscontrato nel tipo di domanda');
                    return;
            }
        }

        function getQuestionAnswers(questionsItem) {
            let answers = questionsItem.getElementsByClassName('answerText');
            let answersArray = [answers.item(0).value];

            for(let i = 1; i < answers.length; i++) {
                answersArray.push(answers.item(i).value);
            }
            return answersArray;
        }

        function surveyIsCreated() {
            if(document.getElementById('submit').value === 'Crea Sondaggio') return true
            else return false;
        }

        function getQuestionFrame(newQuestionType,newID) {

            let res;

            switch(newQuestionType) {
                case 'singAns':
                    res = getSingAns(newID);
                    return res;
                case 'multAns':
                    res = getMultAns(newID);
                    return res;
                case 'openQuest':
                    res = getOpenQuest(newID);
                    return res;
                case 'number':
                    res = getNumber(newID);
                    return res;
                case 'date':
                    res = getDate(newID);
                    return res;
                default:
                    return '';
            }
        }

        function getSingAns(newID) {
            return `<div class="form-group">
                        <div class="col-sm-4">
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" id="${newID}-new-question" class="custom-control-input mandatoryCheckbox">
                                <label class="custom-control-label" for="${newID}-new-question">Obbligatoria</label>
                                <br/>
                                <span class="help-block"><small>Seleziona questa casella se la domanda è obbligatoria.</small></span>
                            </div>
                        </div>
                    </div>
                    <input type="text" class="form-control questionText" placeholder="Testo della domanda ..." value="" number="${newID}">
                    <div class="col-sm-4 single-answers">
                        <div class="custom-control custom-radio single-answer-text-radio">
                            <input type="radio" id="s-a-new-${newID}" name="customRadio" class="custom-control-input">
                            <label class="custom-control-label" for="s-a-new-${newID}">
                                <input type="text" class="answerText" placeholder=" Testo della risposta ...">
                            </label>
                        </div>
                        <div class="singAnsMarker"></div>
                        <div class="m-icon addSingAns"><i class="m-r-10 mdi mdi-plus-circle-outline"></i><span>Aggiungi Risposta</span></div>
                    </div>
                    <input type="text" class="form-control questionNote" placeholder="Scrivi una nota se lo desideri ..." value="">`;
        }

        function getMultAns(newID) {
            return `<div class="form-group">
                        <div class="col-sm-4">
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input mandatoryCheckbox" id="${newID}-new-question">
                                <label class="custom-control-label" for="${newID}-new-question">Obbligatoria</label>
                                <br/>
                                <span class="help-block"><small>Seleziona questa casella se la domanda è obbligatoria.</small></span>
                            </div>
                        </div>
                    </div>
                    <input type="text" class="form-control questionText" placeholder="Testo della domanda ..." value="" number="${newID}">
                    <div class="col-sm-4 multiple-answers">
                        <div class="custom-control custom-checkbox multiple-answer-text-checkbox">
                            <input type="checkbox" class="custom-control-input" id="m-a-new-${newID}">
                            <label class="custom-control-label" for="m-a-new-${newID}"><input type="text" class="answerText" placeholder=" Testo della risposta ..."></label>
                        </div>
                        <div class="multAnsMarker"></div>
                        <div class="m-icon addMultAns"><i class="m-r-10 mdi mdi-plus-circle-outline"></i><span>Aggiungi Risposta</span></div>
                    </div>
                    <input type="text" class="form-control questionNote" placeholder="Scrivi una nota se lo desideri ..." value="">`;
        }

        function getOpenQuest(newID) {
            return `<div class="form-group">
                        <div class="col-sm-4">
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input mandatoryCheckbox" id="${newID}-new-question">
                                <label class="custom-control-label" for="${newID}-new-question">Obbligatoria</label>
                                <br/>
                                <span class="help-block"><small>Seleziona questa casella se la domanda è obbligatoria.</small></span>
                            </div>
                        </div>
                    </div>
                    <input type="text" class="form-control questionText" placeholder="Testo della domanda ..." value="" number="${newID}">
                    <span class="help-block"><small>Non è presente alcun tool per le risposte perché stai creando una domanda aperta.</small></span>
                    <input type="text" class="form-control questionNote" placeholder="Scrivi una nota se lo desideri ..." value="" >`;
        }

        function getNumber(newID) {
            return `<div class="form-group">
                        <div class="col-sm-4">
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input mandatoryCheckbox" id="${newID}-new-question">
                                <label class="custom-control-label" for="${newID}-new-question">Obbligatoria</label>
                                <br/>
                                <span class="help-block"><small>Seleziona questa casella se la domanda è obbligatoria.</small></span>
                            </div>
                        </div>
                    </div>
                    <input type="text" class="form-control questionText" placeholder="Testo della domanda ..." value="" number="${newID}">
                    <span class="help-block"><small>Non è presente alcun tool per le risposte perché stai creando una domanda con un numero come risposta.</small></span>
                    <input type="text" class="form-control questionNote" placeholder="Scrivi una nota se lo desideri ..." value="">`;
        }

        function getDate(newID) {
            return `<div class="form-group">
                        <div class="col-sm-4">
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input mandatoryCheckbox" id="${newID}-new-question">
                                <label class="custom-control-label" for="${newID}-new-question">Obbligatoria</label>
                                <br/>
                                <span class="help-block"><small>Seleziona questa casella se la domanda è obbligatoria.</small></span>
                            </div>
                        </div>
                    </div>
                    <input type="text" class="form-control questionText" placeholder="Testo della domanda ..." value="" number="${newID}">
                    <span class="help-block"><small>Non è presente alcun tool per le risposte perché stai creando una domanda con una data come risposta.</small></span>
                    <input type="text" class="form-control questionNote" placeholder="Scrivi una nota se lo desideri ..." value="" >`;
        }

        function getChooseQuestion() {
            return `<div>
                        <div class="create-question-container">
                            <div class="choose-question-type">
                                <div class="form-group select-question-container">
                                    <label>Scegli il tipo di domanda</label>
                                    <select class="custom-select col-12">
                                        <option selected>Scegli...</option>
                                        <option value="1" newQuestionType="singAns">Risposta singola</option>
                                        <option value="2" newQuestionType="multAns">Risposta multipla</option>
                                        <option value="3" newQuestionType="openQuest">Domanda Aperta</option>
                                        <option value="4" newQuestionType="number">Numero</option>
                                        <option value="5" newQuestionType="date">Data</option>
                                    </select>
                                </div>
                            </div>
                            <input class="toggleQuestion" type="button" value="Elimina domanda" style="height:2em;margin-bottom:1.2rem;">
                        </div>
                        <div class="form-group selected-question" questionType="none" questionId="new"></div>
                    </div>                    
                    <hr class="hr-soft-separation">
                    <div class="m-icon addQuestion" id="addQuestion"><span>Aggiungi Domanda</span><i class="m-r-10 mdi mdi-plus-circle-outline"></i></div>`;
        }

        function getSingAnsBox(newID) {

            return `<div class="custom-control custom-radio single-answer-text-radio">
                        <input type="radio" id="s-a-new-${newID}" name="customRadio" class="custom-control-input">
                        <label class="custom-control-label" for="s-a-new-${newID}">
                            <input type="text" class="answerText" placeholder=" Testo della risposta ...">
                        </label>
                    </div>
                    <div class="singAnsMarker"></div>`;
        }

        function getMultAnsBox(newID) {

            return `<div class="custom-control custom-checkbox multiple-answer-text-checkbox">
                        <input type="checkbox" class="custom-control-input" id="m-a-new-${newID}">
                        <label class="custom-control-label" for="m-a-new-${newID}"><input type="text" class="answerText" placeholder=" Testo della risposta ..."></label>
                    </div>
                    <div class="multAnsMarker"></div>`;
        }
    }
}