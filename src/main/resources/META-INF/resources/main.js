var columnDefs = [
    {headerName: "ID", field: "id", sort: 'desc'},
    {headerName: "Name", field: "name",sort: 'desc'},
    {headerName: "Salary", field: "salary",sort: 'desc'},
];

var gridOptions = {
    defaultColDef: {
        sortable: true,
        pagination: true
    },

    columnDefs: columnDefs,
    rowData: null
};

// setup the grid after the page has finished loading
document.addEventListener('DOMContentLoaded', function() {
    var gridDiv = document.querySelector('#myGrid');
    new agGrid.Grid(gridDiv, gridOptions);

    // do http request to get our sample data - not using any framework to keep the example self contained.
    // you will probably use a framework like JQuery, Angular or something else to do your HTTP calls.

    fetch('http://localhost:8080/employees')
        .then(response => response.json())
        .then(data => gridOptions.api.setRowData(data))
        .catch(error => console.error(error));
});
