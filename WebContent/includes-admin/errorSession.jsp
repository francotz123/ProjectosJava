<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "controllers.ErrorSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<title>No logeado</title>
</head>
<body>
<div class="container mt-5 pt-5">
    <div class="row">
        <div class="col-md-12">
            <div class="error-template">
                <h1>
                    Oops!</h1>
                <h2>
                   No estas logeado :(</h2>
                <div class="error-details">
                    Inicio sesion y disfruta de nuesta web!
                </div>
                <div class="error-actions">
                    <a href="../errorSession" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-home"></span>
                        Login</a>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>