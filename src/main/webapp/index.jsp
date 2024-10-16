<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Bootstrap User Management Data Table</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel ="stylesheet" href = "css/table.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

<script>
$(document).ready(function(){
	$('[data-toggle="tooltip"]').tooltip();
});
</script>
</head>
<body>
<div class="container-xl">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    
                    
                </div>
            </div>
           <<table class="table table-striped table-hover">
    <thead>
        <tr id="table-header">
            <!-- header titles will be rendered here -->
        </tr>
    </thead>
    <tbody id="table-body">
        <!-- table data will be rendered here -->
    </tbody>
</table>

<div class="clearfix">
    <div class="hint-text" id="pagination-info">Showing <b>0</b> entries in this page out of <b>0</b> total entries</div>
    <ul class="pagination" id="pagination">
        <!-- pagination links will be generated here -->
    </ul>
</div>

<script>
    // Function to render table headers
    function renderTableHeader(headers) {
        var headerHtml = '';
        for (var i = 0; i < headers.length; i++) {
            headerHtml += '<th>' + headers[i] + '</th>';
        }
        $('#table-header').html(headerHtml);
    }

    // Function to render table data and pagination
    function renderTableData(data) {
        var dataHtml = '';
        for (var i = 0; i < data.length; i++) {
            dataHtml += '<tr>';
            for (var j = 0; j < data[i].length; j++) {
                dataHtml += '<td>' + data[i][j] + '</td>';
            }
            dataHtml += '</tr>';
        }
        $('#table-body').html(dataHtml);

        renderPagination(data);
    }

    // Function to render pagination
    function renderPagination(data) {
        var totalEntries = data.length;
        var entriesPerPage = 5; // Number of entries per page
        var totalPages = Math.ceil(totalEntries / entriesPerPage);
        var currentPage = 1; // Default to first page

        // Update pagination info
        var paginationInfoHtml = 'Showing <b>' + Math.min(entriesPerPage, totalEntries) + '</b> entries in this page out of <b>' + totalEntries + '</b> total entries';
        $('#pagination-info').html(paginationInfoHtml);

        // Generate pagination links
        var paginationHtml = '';
        for (var i = 1; i <= totalPages; i++) {
            if (i === currentPage) {
                paginationHtml += '<li class="page-item active"><a href="#" class="page-link">' + i + '</a></li>';
            } else {
                paginationHtml += '<li class="page-item"><a href="#" class="page-link">' + i + '</a></li>';
            }
        }

        // Add previous and next links
        paginationHtml = '<li class="page-item"><a href="#" class="page-link">Previous</a></li>' + paginationHtml + '<li class="page-item"><a href="#" class="page-link">Next</a></li>';

        $('#pagination').html(paginationHtml);
    }
</script>
        </div>
    </div>
</div>     
</body>
</html>