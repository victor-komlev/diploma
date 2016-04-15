(function() {
	var app = angular.module('store', []);

	app.controller('StoreController', function() {
		this.products = gems;
	});

	app.controller("PanelController", function() {
		this.tab = 1;

		this.selectTab = function(selectTab) {
			this.tab = selectTab;
		};

		this.isSelected = function(selected) {
			return this.tab == selected;
		};

	});
	var gems = [ {
		name : 'Dodecahedron',
		price : 2.95,
		description : '..........',
		canPurchase : false,
		soldOut : false
	}, {
		name : 'Pendaik',
		price : 56.95,
		description : '..........',
		canPurchase : true
	}, {
		name : 'awdawdzdrgzdrg',
		price : 1112.95,
		description : '..zsefzsef.sefzsefzsef......',
		canPurchase : false,
		soldOut : true
	}, {
		name : 'adawdawd',
		price : 1112222.95,
		description : '..zseffzsrfzsrfzszsef.zsfsfzssefzsefzsef......',
		canPurchase : true,
		soldOut : false
	} ]

})();