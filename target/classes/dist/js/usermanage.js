function removeGestore(e){
    var name =  $(e).closest('tr').find('td')[0].innerText;
    var id =  $(e).closest('tr').find('th').text();
    var index = $(e).closest('tr')[0].sectionRowIndex;
    if (confirm("Sei sicuro di voler rimuovere il gestore:" + name + "?")) {
        txt = "Press OK!";
        document.getElementById("tablegestore").deleteRow(index);

    } else {
        txt = name;
    }
    console.log(txt);

}

function updateGestore(ex){
    var nomeg = $(ex).closest('tr').find('td')[0].innerText;
    var cognomeg = $(ex).closest('tr').find('td')[1].innerText;
    var usernameg = $(ex).closest('tr').find('td')[2].innerText;
    var passwordg = $(ex).closest('tr').find('td')[3].innerText;
    document.getElementById("gestorenome").value = nomeg;
    document.getElementById("gestoreusername").value = usernameg;
    document.getElementById("gestorepassword").value = passwordg;
}