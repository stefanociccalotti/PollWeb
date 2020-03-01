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

        nextbutton.onclick = () => {

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

        function showSubmit() {
            setNextbuttonNotAllowedStyle();
            document.getElementById('submitbutton').style.setProperty('display','unset');
        }

        function hideSubmit() {
            setNextbuttonAllowedStyle();
            document.getElementById('submitbutton').style.setProperty('display','none');
        }

        function setBackbuttonAllowedStyle() {
            backbutton.style.setProperty('background-color','rgba(26, 46, 77, 0.747)');
            backbutton.style.setProperty('cursor','pointer');
            backbutton.onmouseenter = () => {
                backbutton.style.backgroundColor = 'rgba(26, 46, 77, 0.911)';
            }
            backbutton.onmouseleave = () => {
                backbutton.style.backgroundColor = 'rgba(26, 46, 77, 0.747)';
            }
        }

        function setBackbuttonNotAllowedStyle() {
            backbutton.onmouseenter = () => {}
            backbutton.onmouseleave = () => {}
            backbutton.style.setProperty('background-color','rgba(26, 46, 77, 0.51)');
            backbutton.style.setProperty('cursor','default');
        }

        function setNextbuttonAllowedStyle() {
            nextbutton.style.setProperty('background-color','rgba(26, 46, 77, 0.747)');
            nextbutton.style.setProperty('cursor','pointer');
            nextbutton.onmouseenter = () => {
                nextbutton.style.backgroundColor = 'rgba(26, 46, 77, 0.911)';
            }
            nextbutton.onmouseleave = () => {
                nextbutton.style.backgroundColor = 'rgba(26, 46, 77, 0.747)';
            }
        }

        function setNextbuttonNotAllowedStyle() {
            nextbutton.onmouseenter = () => {}
            nextbutton.onmouseleave = () => {}
            nextbutton.style.setProperty('background-color','rgba(26, 46, 77, 0.51)');
            nextbutton.style.setProperty('cursor','default');
        }

    }
}