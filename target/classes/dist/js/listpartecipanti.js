var selectedRows = null;

function inputChanged(event) {
  event.target.parentElement.parentElement.parentElement.parentElement.parentElement.className =
    event.target.checked ? 'selected' : '';
}

function printSelected() {
  var textArea = document.getElementsByTagName('textarea')[0];
  textArea.value = '';

  selectedRows = document.getElementsByClassName('selected');
  for (var i = 0; i < selectedRows.length; ++i) {
    textArea.value += selectedRows[i].textContent.trim() + '\n';
  }
}

function removePartecipante(e){
  selectedRows = document.getElementsByClassName('selected');
  var table = document.getElementById('example');

  for (var i = 0; i < selectedRows.length; ++i) {
    var temp = selectedRows[i];
    console.log(temp);
    var indxe = $(temp).closest('tr')[0].sectionRowIndex;
    document.getElementById("tablepartecipanti").deleteRow(indxe);
    i--;
  }

  //console.log(selectedRows.sectionRowIndex);
}