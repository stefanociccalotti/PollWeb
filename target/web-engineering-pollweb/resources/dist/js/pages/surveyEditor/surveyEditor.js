document.onreadystatechange = () => {
    if (document.readyState === 'complete') {

        setAddQuestion();
        setAddSingAns();
        setAddMultAns();

        document.getElementById('submit').onclick = updateSurvey;

        function setAddQuestion() {

            document.getElementById('addQuestion').onclick = () => {
                document.getElementById('addQuestion').outerHTML =
                    '<div class="create-question-container">\n' +
                    '    <div class="choose-question-type">\n' +
                    '        <div class="form-group select-question-container">\n' +
                    '            <label>Scegli il tipo di domanda</label>\n' +
                    '            <select class="custom-select col-12">\n' +
                    '                <option selected>Scegli...</option>\n' +
                    '                <option value="1">Risposta singola</option>\n' +
                    '                <option value="2">Risposta multipla</option>\n' +
                    '                <option value="3">Domanda Aperta</option>\n' +
                    '                <option value="4">Numero</option>\n' +
                    '                <option value="5">Data</option>\n' +
                    '            </select>\n' +
                    '        </div>\n' +
                    '        <input type="button" value="Reset"/>\n' +
                    '    </div>\n' +
                    '</div>\n' +
                    '\n' +
                    '<hr class="hr-soft-separation">\n' +
                    '<div class="m-icon addQuestion" id="addQuestion"><span>Aggiungi Domanda</span><i class="m-r-10 mdi mdi-plus-circle-outline"></i></div>\n'
                ;
                setAddQuestion();
            }

        }

        function setAddSingAns() {//TODO: sistemare id in singAns e multAns, quando aggiungo una risposta genero degli id non univoci

            let singAnswers = document.getElementsByClassName('addSingAns');

            for (let i = 0; i < singAnswers.length; i++) {
                singAnswers[i].onclick = () => {
                    singAnswers[i].outerHTML =
                        '<div class="custom-control custom-radio single-answer-text-radio">\n' +
                        '   <input type="radio" id="s-a-new-sa" name="customRadio" class="custom-control-input">\n' +
                        '   <label class="custom-control-label" for="s-a-new-sa"><input type="text" class="answerText" placeholder=" Testo della risposta ..."></label>\n' +
                        '</div>\n' +
                        '<div class="m-icon addSingAns"><i class="m-r-10 mdi mdi-plus-circle-outline"></i><span>Aggiungi Risposta</span></div>'
                    ;
                    setAddSingAns();
                }
            }
        }

        function setAddMultAns() {

            let multAnswers = document.getElementsByClassName('addMultAns');

            for (let j = 0; j < multAnswers.length; j++) {
                multAnswers[j].onclick = () => {
                    multAnswers[j].outerHTML =
                        '<div class="custom-control custom-checkbox multiple-answer-text-checkbox">\n' +
                        '   <input type="checkbox" class="custom-control-input" id="m-a-new-ma">\n' +
                        '   <label class="custom-control-label" for="m-a-new-ma"><input type="text" class="answerText" placeholder=" Testo della risposta ..."></label>\n' +
                        '</div>\n' +
                        '<div class="m-icon addMultAns"><i class="m-r-10 mdi mdi-plus-circle-outline"></i><span>Aggiungi Risposta</span></div>'
                    ;
                    setAddMultAns();
                }
            }
        }

        function updateSurvey() {
            let questionsCollection = document.getElementsByClassName('selected-question');
            let questions = [];

            for(let i = 0; i < questionsCollection.length; i++) {

                let questionsItem = questionsCollection.item(i);
                let question = [];

                question[0] = questionsItem.getAttribute('questionId');
                question[1] = checkMandatoryQuestion(questionsItem);
                question[2] = questionsItem.getElementsByClassName('questionText').item(0).getAttribute('number');
                question[3] = getJsonStr(questionsItem);
                question[4] = questionsItem.getElementsByClassName('questionNote').item(0).value;

                questions[i] = question;
            }

            console.log(questions);

        }

        //FUNZIONI PER PULIRE IL CODICE

        function checkMandatoryQuestion(questionsItem) {
            if(questionsItem.getElementsByClassName('mandatoryCheckbox').item(0).checked) return 1;
            else return 0;
        }

        function getJsonStr(questionsItem) {
            switch(questionsItem.classList.item(1)) {//TODO: cambiare questionsItem.classList.item(1), troppo "preciso"
                case 'type-single-answer':
                    return '{"type": "singAns", ' + getQuestionAnswers(questionsItem) + '"question": "' + questionsItem.getElementsByClassName('questionText').item(0).value + '"}';
                case 'type-multiple-answer':
                    return '{"type": "multAns", ' + getQuestionAnswers(questionsItem) + '"question": "' + questionsItem.getElementsByClassName('questionText').item(0).value + '"}';
                case 'type-number-question':
                    return '{"type": "number", ' + '"question": "' + questionsItem.getElementsByClassName('questionText').item(0).value + '"}';
                case 'type-open-question':
                    return '{"type": "openQuest", ' + '"question": "' + questionsItem.getElementsByClassName('questionText').item(0).value + '"}';
                case 'type-date-question':
                    return '{"type": "date", ' + '"question": "' + questionsItem.getElementsByClassName('questionText').item(0).value + '"}';
                default:
                    alert('Errore riscontrato nel tipo di domanda');
                    return;
            }
        }

        function getQuestionAnswers(questionsItem) {
            let answers = questionsItem.getElementsByClassName('answerText');
            let answersString = '"answers":["'+ answers.item(0).value + '"';

            for(let i = 1; i < answers.length; i++) {
                answersString += ',"' + answers.item(i).value +'"';
            }

            answersString += '], '

            return answersString;
        }
    }
}