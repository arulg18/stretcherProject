function exportTableToCSV(filename) {
    var csv = [];
    var rows = document.querySelectorAll("table tr");

    for (var i = 0; i < rows.length; i++) {
        var row = [], cols = rows[i].querySelectorAll("td, th");

        for (var j = 0; j < cols.length; j++)
            row.push(cols[j].innerText);

        csv.push(row.join(","));
    }

    // Download CSV file
    downloadCSV(csv.join("\n"), filename);
}

function downloadCSV(csv, filename) {
    var csvFile;
    var downloadLink;

    // CSV file
    csvFile = new Blob([csv], {type: "text/csv"});

    // Download link
    downloadLink = document.createElement("a");

    // File name
    downloadLink.download = filename;

    // Create a link to the file
    downloadLink.href = window.URL.createObjectURL(csvFile);

    // Hide download link
    downloadLink.style.display = "none";

    // Add the link to DOM
    document.body.appendChild(downloadLink);

    // Click download link
    downloadLink.click();
}

function addToSchedule()
{
  var table = document.getElementById('maintable');

  var row = document.getElementById('textFieldArray');

  var newRow = table.insertRow(table.rows.length - 1);





  for (var i = 0; i < row.cells.length; i++) {
    newRow.insertCell(i)
    newRow.cells[i].innerHTML = row.cells[i].children[0].value;
    row.cells[i].children[0].value = ""
  }

  exportTableToCSV("appointments.csv")




}
