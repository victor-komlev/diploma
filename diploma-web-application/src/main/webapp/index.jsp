
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>AngularJS - SignalR - ServiceDashboard</title>
<link rel="stylesheet" href="Content/bootstrap.min.css" />
<link rel="stylesheet" href="Content/angular-chart.css" />
<link rel="stylesheet" href="Content/app.css" />

<script type="text/javascript" src="Scripts/angular.js"></script>
<script type="text/javascript" src="Scripts/Chart.js"></script>
<script type="text/javascript" src="Scripts/angular-chart.js"></script>
<script type="text/javascript" src="Scripts/sockjs.js"></script>

<script type="text/javascript"
	src="app/Notifications/NotificationModule.js"></script>
<script type="text/javascript"
	src="app/Notifications/NotificationServices.js"></script>

<script src="app/app.js"></script>
<!-- <script src="app/services.js"></script> -->
<!-- <script src="app/directives.js"></script> -->
<!-- <script src="app/controllers.js"></script> -->

</head>
<body ng-app="Stats">
	<div class="col-lg-6 col-sm-12" id="bar-chart"
		ng-controller="TaskStatsCntrl">
		<div class="panel panel-default">
			<div class="panel-heading">Task Stats</div>
			<div class="panel-body">
				<canvas id="bar1" class="chart chart-bar" chart-data="data"
					chart-labels="labels" chart-series="series" chart-options="options" chart-y-axes="multiAxis"></canvas>
			</div>
		</div>
	</div>
	<div class="col-lg-6 col-sm-12" id="bar-chart-action"
		ng-controller="ActionStatsCntrl">
		<div class="panel panel-default">
			<div class="panel-heading">Action Stats</div>
			<div class="panel-body">
				<canvas id="bar2" class="chart chart-bar" chart-data="dataAction"
					chart-labels="labelsAction" chart-series="seriesAction" chart-options="optionsAction" chart-y-axes="multiAxis"></canvas>
			</div>
		</div>
	</div>
</body>
</html>