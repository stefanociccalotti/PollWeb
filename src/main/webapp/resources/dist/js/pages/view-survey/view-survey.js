document.onreadystatechange = function () {
    if (document.readyState === 'complete') {

        let backButton = document.getElementById('backbutton');
        let nextButton = document.getElementById('nextbutton');
        let submitButton = document.getElementById('submitbutton');
        let form = document.getElementById('form');
        let questionsNumber = parseInt(document.getElementById('surveyInfo').getAttribute('size'));
        let currentMargin = 0;

        backButton.onclick = () => {

            let margin = currentMargin + 100;

            switch(true) {
                case currentMargin === -100:
                    setBackbuttonNotAllowedStyle();
                case currentMargin <= -100 && currentMargin !== -questionsNumber * 100:
                    currentMargin = margin;
                    document.getElementById('slide').style.setProperty('margin-left',currentMargin+'%');
                    console.log(currentMargin+'%');
                    break;
                case currentMargin === -questionsNumber * 100:
                    currentMargin = margin;
                    document.getElementById('slide').style.setProperty('margin-left',currentMargin+'%');
                    hideSubmit();
                    console.log(currentMargin+'%');
                    break;
            }
        }

        nextButton.onclick = () => {

            let margin = currentMargin - 100;

            switch(true) {
                case currentMargin === 0:
                    setBackbuttonAllowedStyle();
                case currentMargin >= -(questionsNumber - 2) * 100:
                    currentMargin = margin;
                    document.getElementById('slide').style.setProperty('margin-left',currentMargin+'%');
                    console.log(currentMargin+'%');
                    break;
                case currentMargin === -(questionsNumber - 1) * 100:
                    currentMargin = margin;
                    document.getElementById('slide').style.setProperty('margin-left',currentMargin+'%');
                    showSubmit();
                    console.log(currentMargin+'%');
            }
        }

        submitButton.onclick = () => {

            let missingMandatoryQuestions = getMissingMandatoryQuestions();

            switch(missingMandatoryQuestions.length) {
                case 0:
                    form.submit();
                    break;
                case 1:
                    alert('La domanda ' + missingMandatoryQuestions[0] + ' è obbligatoria!');
                    break;
                default:
                    let msg = 'Le domande ' + missingMandatoryQuestions[0];

                    for(let i = 1; i < missingMandatoryQuestions.length-1; i++) {
                        msg += ', ' + missingMandatoryQuestions[i];
                    }

                    msg += ' e ' + missingMandatoryQuestions[missingMandatoryQuestions.length-1] + ' sono obbligatorie!';
                    alert(msg);
                    break;
            }
        }

        function showSubmit() {
            setNextbuttonNotAllowedStyle();
            document.getElementById('submitbutton').style.setProperty('display','unset');
        }

        function hideSubmit() {
            setNextbuttonAllowedStyle();
            document.getElementById('submitbutton').style.setProperty('display','none');
        }

        function setBackbuttonAllowedStyle() {
            backButton.style.setProperty('background-color','rgba(26, 46, 77, 0.747)');
            backButton.style.setProperty('cursor','pointer');
            backButton.onmouseenter = () => {
                backButton.style.backgroundColor = 'rgba(26, 46, 77, 0.911)';
            }
            backButton.onmouseleave = () => {
                backButton.style.backgroundColor = 'rgba(26, 46, 77, 0.747)';
            }
        }

        function setBackbuttonNotAllowedStyle() {
            backButton.onmouseenter = () => {}
            backButton.onmouseleave = () => {}
            backButton.style.setProperty('background-color','rgba(26, 46, 77, 0.51)');
            backButton.style.setProperty('cursor','default');
        }

        function setNextbuttonAllowedStyle() {
            nextButton.style.setProperty('background-color','rgba(26, 46, 77, 0.747)');
            nextButton.style.setProperty('cursor','pointer');
            nextButton.onmouseenter = () => {
                nextButton.style.backgroundColor = 'rgba(26, 46, 77, 0.911)';
            }
            nextButton.onmouseleave = () => {
                nextButton.style.backgroundColor = 'rgba(26, 46, 77, 0.747)';
            }
        }

        function setNextbuttonNotAllowedStyle() {
            nextButton.onmouseenter = () => {}
            nextButton.onmouseleave = () => {}
            nextButton.style.setProperty('background-color','rgba(26, 46, 77, 0.51)');
            nextButton.style.setProperty('cursor','default');
        }

        function getMissingMandatoryQuestions() {
            let questions = document.getElementsByClassName('question-info');
            let missingMandatoryQuestions = [];

            for(let i = 0; i < questions.length; i++) {
                if( questions[i].getAttribute('mandatory') === '1' && isNotAnswered(questions[i]) ) missingMandatoryQuestions.push(i+1);
            }
            console.log(missingMandatoryQuestions);
            return missingMandatoryQuestions;
        }

        function isNotAnswered(question) {
            switch(question.getAttribute('questionType')) {
                case 'date':
                    if(question.getElementsByTagName('input')[0].value === '') return true;
                    else return false;
                case 'openQuest':
                    if(question.getElementsByTagName('textarea')[0].value === '') return true;
                    else return false;
                case 'number':
                    if(question.getElementsByTagName('input')[0].value === '') return true;
                    else return false;
                case 'multAns':
                    let multAnswers = question.getElementsByTagName('input');
                    for(let i = 0; i < multAnswers.length; i++) {
                        if(multAnswers[i].checked) return false;
                    }
                    return true;
                case 'singAns':
                    let singAnswers = question.getElementsByTagName('input');
                    for(let i = 0; i < singAnswers.length; i++) {
                        if(singAnswers[i].checked) return false;
                    }
                    return true;
            }
        }

    }
}