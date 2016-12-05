app.controller('Trends', function($scope, $http, sessionService, $window) {
	if (sessionService.get('user') == null) {
		$window.location.href = './index.html';

	} else {
		$scope.user = sessionService.get('user')
		$scope.Email = $scope.user;

		var requserDetails = {
			method : 'POST',
			url : 'http://localhost:8080/user/getUserdetails',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : {
				username : sessionService.get('user')
			}
		};

		$http(requserDetails).then(function(response) {
			if (response.status == 200 && response.data != "") {
				//alert("Photo Uploaded Successfully");

				$scope.firstname = response.data.firstName;
				$scope.lastname = response.data.lastName;

				$scope.myDate = response.data.birthDate
				$scope.Bio = response.data.bio

			}

		});

	}

	var urls = 'http://localhost:8080/photo/getBrands/' + $scope.Email;
	var req = {
		method : 'POST',
		url : urls,

	};

	$http(req).then(function(response) {
		if (response.status == 200 && response.data != "") {
			//alert("Photo Uploaded Successfully");

			$scope.trends = response.data;

		}

	});

	$scope.Logout = function() {
		sessionService.destroy('user');
		$window.location.href = './index.html';

	}

});
