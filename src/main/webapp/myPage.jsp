<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.yash.OnlineVegetableSelling.domain.Menu" %>
<%@ page import="com.yash.OnlineVegetableSelling.dao.MenuDAO" %>
<%@ page import="com.yash.OnlineVegetableSelling.daoImpl.MenuDAOImpl" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Menu Management</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/table.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script>
    $(document).ready(function(){
        $('[data-toggle="tooltip"]').tooltip();
        loadMenuData(); // Load menu data on page load

        $('#addMenuForm').submit(function(e) {
            e.preventDefault();
            $.ajax({
                url: 'MenuController?action=add',
                type: 'POST',
                data: $(this).serialize(),
                success: function(result) {
                    $('#addMenuModal').modal('hide');
                    loadMenuData();
                },
                error: function(xhr, status, error) {
                    alert('Error adding menu');
                }
            });
        });

        $('#editMenuForm').submit(function(e) {
            e.preventDefault();
            $.ajax({
                url: 'MenuController?action=update',
                type: 'POST',
                data: $(this).serialize(),
                success: function(result) {
                    $('#editMenuModal').modal('hide');
                    loadMenuData();
                },
                error: function(xhr, status, error) {
                    alert('Error updating menu');
                }
            });
        });

        // Handle the confirmation button click
        $('#confirmDeleteButton').click(function() {
            console.log('Deleting menu with ID:', menuIdToDelete);
            $.ajax({
                url: 'MenuController?action=delete',
                type: 'POST',
                data: { menuId: menuIdToDelete },
                success: function(result) {
                    console.log('Deletion result:', result);
                    if(result === 'success') {
                        loadMenuData(); // Reload menu data
                        $('#deleteConfirmationModal').modal('hide'); // Hide the modal
                    } else {
                        alert('Error deleting menu');
                    }
                },
                error: function(xhr, status, error) {
                    console.log('Error deleting menu:', error);
                    alert('Error deleting menu');
                }
            });
        });
    });

    function loadMenuData() {
        $.ajax({
            url: 'MenuController?action=list',
            type: 'GET',
            success: function(data) {
                // Split the response into lines
                var lines = data.split('\n');
                var menuData = [];
                for (var i = 0; i < lines.length; i++) {
                    if (lines[i].trim() !== '') { // Avoid empty lines
                        var parts = lines[i].split(',');
                        if (parts.length === 2) { // Assuming each line has menuId and menuName
                            menuData.push({
                                menuId: parts[0].trim(),
                                menuName: parts[1].trim()
                            });
                        }
                    }
                }
                renderMenuData(menuData);
            },
            error: function() {
                alert('Error loading menu data');
            }
        });
    }

    function renderMenuData(data) {
        var dataHtml = '';
        for (var i = 0; i < data.length; i++) {
            dataHtml += '<tr>';
           
            dataHtml += '<td>' + data[i].menuName + '</td>';
            dataHtml += '<td>';
            dataHtml += '<a href="#editMenuModal" class="edit" data-toggle="modal" onclick="prepareEdit(' + data[i].menuId + ', \'' +data[i].menuName + '\')"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>';
            dataHtml += '<a href="#" class="delete" onclick="deleteMenu(' + data[i].menuId + ')"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>';
            dataHtml += '</td>';
            dataHtml += '</tr>';
        }
        $('#menu-table-body').html(dataHtml);
    }

    function prepareEdit(menuId, menuName) {
        $('#editMenuId').val(menuId );
        $('#editMenuName').val(menuName);
    }

    var menuIdToDelete; // Global variable to hold the menu ID to delete

    function deleteMenu(menuId) {
        menuIdToDelete = menuId; // Store the menu ID to delete
        $('#deleteConfirmationModal').modal('show'); // Show the confirmation modal
    }
    </script>
</head>
<body>
    <div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
                        <h2>Menu Management</h2>
                    </div>
                    <div class="col-sm-6">
                        <button type="button" class="btn btn-success" data-toggle="modal" data-target="#addMenuModal">
                            <i class="material-icons">&#xE147;</i> <span>Add New Menu</span>
                        </button>
                    </div>
                </div>
            </div>
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        
                        <th>Menu Name</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody id="menu-table-body">
                </tbody>
            </table>
        </div>
    </div>

    <!-- Add Menu Modal -->
    <div class="modal fade" id="addMenuModal" tabindex="-1" role="dialog" aria-labelledby="addMenuModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addMenuModalLabel">Add New Menu</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="addMenuForm">
                        <div class="form-group">
                            <label for="menuName">Menu Name:</label>
                            <input type="text" class="form-control" id="menuName" name="menuName" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Add Menu</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Edit Menu Modal -->
    <div class="modal fade" id="editMenuModal" tabindex="-1" role="dialog" aria-labelledby="editMenuModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editMenuModalLabel">Edit Menu</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="editMenuForm">
                        <div class="form-group">
                            <label for="editMenuId">Menu ID:</label>
                            <input type="text" class="form-control" id="editMenuId" name="menuId" readonly>
                        </div>
                        <div class="form-group">
                            <label for="editMenuName">Menu Name:</label>
                            <input type="text" class="form-control" id="editMenuName" name="menuName" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Update Menu</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Delete Confirmation Modal -->
    <div class="modal fade" id="deleteConfirmationModal" tabindex="-1" role="dialog" aria-labelledby="deleteConfirmationModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteConfirmationModalLabel"> Confirm Deletion</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button >
                </div>
                <div class="modal-body">
                    Are you sure you want to delete this menu?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-danger" id="confirmDeleteButton">Delete</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>