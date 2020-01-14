stylize();

document.onreadystatechange = function () {
    if (document.readyState === 'complete') {

        let backbutton = document.getElementById('backbutton');
        let nextbutton = document.getElementById('nextbutton');
        let currentMargin = 0;
        let questionNumber =  5 - 2; //prendi il numero di domande e sottrai 2 per avere il limite alla possibilità di andare avanti con le domande.

        backbutton.onclick = () => {
            if(currentMargin <= -100) {
                let margin = currentMargin + 100;
                currentMargin = margin;
                document.getElementById('slide').style.setProperty('margin-left',currentMargin+'%');
                console.log(currentMargin+'%');
            }
        }

        nextbutton.onclick = () => {
            if(currentMargin >= -questionNumber * 100) {
                let margin = currentMargin - 100;
                currentMargin = margin;
                document.getElementById('slide').style.setProperty('margin-left',currentMargin+'%');
                console.log(currentMargin+'%');
            }
        }

    }
}

function stylize() {
    let surveyContainer = document.getElementById('survey-container').style;
    let buttonContainer = document.getElementById('button-container').style;

    surveyContainer.setProperty('display','flex');
    surveyContainer.setProperty('flex-direction','row');
    buttonContainer.setProperty('display','flex');
    buttonContainer.setProperty('flex-direction','row');
    buttonContainer.setProperty('justify-content','center');
    
}