angular.module('app', [])
.controller('form-controller', function(){
	this.click = function click(){
		window.alert("Hello");
	};
});

