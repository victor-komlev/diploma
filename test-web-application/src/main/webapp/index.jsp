<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html ng-app="store">
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
</head>
<body ng-controller="StoreController as store">
	<div ng-repeat="product in store.products">
		<h1>
			{{product.name}} <em class="pull-right"> {{product.price |
				currency}} </em>
		</h1>
		<section ng-controller="PanelController as panel">
			<ul class="nav nav-pills">
				<li ng-class="{active:panel.isSelected(1)}"><a href
					ng-click="panel.selectTab(1)">Description</a></li>
				<li ng-class="{active:panel.isSelected(2)}"><a href
					ng-click="panel.selectTab(2)">Specification</a></li>
				<li ng-class="{active:panel.isSelected(3)}"><a href
					ng-click="panel.selectTab(3)">REviews</a></li>
			</ul>
			<p ng-show="panel.isSelected(1)">{{product.description}}</p>
		</section>

		<form name="reviewForm">
			<select>
				<option value="1">1 star</option>
				<option value="2">2 starts</option>
			</select>
			<textarea></textarea>
			<label>by:</label>
			<input type="email" /> <input type="submit" value="Submit" />
		</form>
		<button ng-show="product.canPurchase">Add to Cart</button>
	</div>
	<script type="text/javascript" src="lib/angular.js"></script>
	<script type="text/javascript" src="app/app.js"></script>
	<h2>Hello Ebit Tvoyu Mat'!!!!!</h2>
</body>
</html>
