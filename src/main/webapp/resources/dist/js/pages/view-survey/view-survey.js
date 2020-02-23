stylize();

document.onreadystatechange = function () {
    if (document.readyState === 'complete') {

        let backbutton = document.getElementById('backbutton');
        let nextbutton = document.getElementById('nextbutton');
        let sizesurvey = document.getElementById('sizesurvey').textContent;
        console.log(sizesurvey);

        console.log(-sizesurvey *100 -100);
        let currentMargin = 0;
        //let questionNumber =  6; //prendi il numero di domande e sottrai 2 per avere il limite alla possibilità di andare avanti con le domande.(!!!!!)

        backbutton.onclick = () => {
            var btninvia = document.getElementById('nextbutton');
            if(btninvia.value == "Invia"){
                btninvia.value = 'Successiva';
                btninvia.type = 'button';
            }
            if(currentMargin <= -100) {
                let margin = currentMargin + 100;
                currentMargin = margin;
                document.getElementById('slide').style.setProperty('margin-left',currentMargin+'%');
                console.log(currentMargin+'%');
            }
        }

        nextbutton.onclick = () => {
            if(currentMargin >= -sizesurvey * 100) {
                let margin = currentMargin - 100;
                currentMargin = margin;
                document.getElementById('slide').style.setProperty('margin-left',currentMargin+'%');
                console.log(currentMargin+'%');
            }else{
                let btninvia = document.getElementById('nextbutton');
                btninvia.type = 'submit';
                btninvia.size = 40;
            }
            if(currentMargin == -sizesurvey *100 -100){
                let btninvia = document.getElementById('nextbutton');
                btninvia.value = 'Invia';

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