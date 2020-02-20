function removeGestore(e){
    var name =  $(e).closest('tr').find('td')[0].innerText;
    var id =  $(e).closest('tr').find('th').text();
    var index = $(e).closest('tr')[0].sectionRowIndex;
    if (confirm("Sei sicuro di voler rimuovere il gestore:" + name + "?")) {
        txt = "Press OK!";
    } else {
        txt = name;
    }
    console.log(txt);

}

function updateGestore(ex){
    var userid = $(ex).closest('tr').find('th').text();
    var nomeg = $(ex).closest('tr').find('td')[0].innerText;
    var cognomeg = $(ex).closest('tr').find('td')[1].innerText;
    var usernameg = $(ex).closest('tr').find('td')[2].innerText;
    var mailg = $(ex).closest('tr').find('td')[3].innerText;

    document.getElementById("gestoreid").value = userid;
    document.getElementById("gestorenome").value = nomeg;
    document.getElementById("gestorecognome").value = cognomeg;
    document.getElementById("gestoremail").value = mailg;
    document.getElementById("gestoreusername").value = usernameg;
}

