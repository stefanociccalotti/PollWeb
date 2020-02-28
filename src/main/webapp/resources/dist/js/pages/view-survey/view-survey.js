document.onreadystatechange = function () {
    if (document.readyState === 'complete') {

        let backbutton = document.getElementById('backbutton');
        let nextbutton = document.getElementById('nextbutton');
        let questionsNumber = parseInt(document.getElementById('surveyInfo').getAttribute('size'));
        let currentMargin = 0;

        backbutton.onclick = () => {

            let margin = currentMargin + 100;

            switch(true) {
                case currentMargin === -100:
                    backNotAllowed();
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

        nextbutton.onclick = () => {

            let margin = currentMargin - 100;

            switch(true) {
                case currentMargin === 0:
                    backAllowed();
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

        function showSubmit() {
            nextbutton.style.setProperty('background-color','rgba(26, 46, 77, 0.51)');
            nextbutton.style.setProperty('cursor','default');
            document.getElementById('submitbutton').style.setProperty('display','unset');
        }

        function hideSubmit() {
            nextbutton.style.setProperty('background-color','rgba(26, 46, 77, 0.747)');
            nextbutton.style.setProperty('cursor','pointer');
            document.getElementById('submitbutton').style.setProperty('display','none');
        }

        function backAllowed() {
            backbutton.style.setProperty('background-color','rgba(26, 46, 77, 0.747)');
            backbutton.style.setProperty('cursor','pointer');
        }

        function backNotAllowed() {
            backbutton.style.setProperty('background-color','rgba(26, 46, 77, 0.51)');
            backbutton.style.setProperty('cursor','default');
        }

    }
}