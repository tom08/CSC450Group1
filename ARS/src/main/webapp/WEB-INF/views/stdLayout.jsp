 <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<html>

	<head>
		
		<!-- Bootstrap imports -->
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	
		<!-- Optional theme -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	
		<!-- JQuery Imports -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	
		<!-- Latest compiled and minified JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
	
		<!-- Project CSS -->
		<!-- <link rel="stylesheet" href="WEB-INF/resources/css/master.css" type="text/css"> -->
		
		<!-- Datatables imports -->
		<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
		<link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css">
		
		<style>
			.ars-table * {
				text-align: center;
			}
			fieldset.surround {
				margin-top: 10px;
				border: 2px solid grey;
			}
			legend {
				border: none;
				border-radius: 4px;
				width: 5.5%;
				background-color: #008add;
				color: white;
			}
			.rate_existing_format {
				text-align: center;
			}
			.rate_existing_format a {
				width: 20%;
				height: 5%;
			}
		</style>
		
		<title>ARS</title>
	</head>

	<body>
	
	<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/ars/">ARS</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="#"><span class="glyphicon glyphicon-refresh"></span> Update</a></li>
      <li><a href="/ars/">Dash Board</a></li>
      <li><a href="/ars/rate_existing">Rate Existing</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
    	<li><a href="/ars/settings"><span class="glyphicon glyphicon-cog"></span></a></li>
    </ul>
  </div>
</nav>