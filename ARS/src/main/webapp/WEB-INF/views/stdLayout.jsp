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
		
		<title>Home</title>
	</head>

	<body>
	
	<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/">ARS</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="#"><span class="glyphicon glyphicon-refresh"></span> Update</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
    	<li><a href="#"><span class="glyphicon glyphicon-cog"></span></a></li>
      <li class="dropdown">
      	<a class="dropdown-toggle" data-toggle="dropdown" href="#">Test Data<span class="caret"></span></a>
     		<ul class="dropdown-menu">
	          <li><a href="test-page">Add Page</a></li>
	          <li><a href="test-keyword">Add Keyword</a></li>
	          <li><a href="test-ad-location-visit">Add Page Location Visit</a></li> 
	        </ul>
      	</li>
    </ul>
  </div>
</nav>