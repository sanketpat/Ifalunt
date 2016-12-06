
app.directive('appFilereader', function($q) {
	var slice = Array.prototype.slice;

	return {
		restrict : 'A',
		require : '?ngModel',
		link : function(scope, element, attrs, ngModel) {
			if (!ngModel)
				return;

			ngModel.$render = function() {
			};

			element.bind('change', function(e) {
				var element = e.target;

				$q.all(slice.call(element.files, 0).map(readFile)).then(
						function(values) {
							if (element.multiple)
								ngModel.$setViewValue(values);
							else
								ngModel.$setViewValue(values.length ? values[0]
										: null);
						});

				function readFile(file) {
					var deferred = $q.defer();

					var reader = new FileReader();
					reader.onload = function(e) {
						deferred.resolve(e.target.result);
					};
					reader.onerror = function(e) {
						deferred.reject(e);
					};
					reader.readAsDataURL(file);

					return deferred.promise;
				}

			}); //change

		} //link
	}; //return
});

app.directive('ngFiles', [ '$parse', function($parse) {

	function fn_link(scope, element, attrs) {
		var onChange = $parse(attrs.ngFiles);
		element.on('change', function(event) {
			onChange(scope, {
				$files : event.target.files
			});
		});
	}
	;

	return {
		link : fn_link
	}
} ]);

app.controller('ImageUploadDetails', function($scope, $http, sessionService,
		$window) {
	$scope.loading = true;
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

				if(  $scope.firstname=="" ||   $scope.firstname==null){
                	$window.location.href = 'profile-update.html';
                }
				$scope.myDate = response.data.birthDate
				$scope.Bio = response.data.bio

			}

		});

	}

	var request2 = {
		method : 'POST',
		url : '/photo/userPhotos',
		headers : {
			'Content-Type' : 'application/json'
		},
		data : {
			userName : sessionService.get('user')
		}
	};
	var request3 = {
		method : 'POST',
		url : '/photo/getPhotos',
		headers : {
			'Content-Type' : 'application/json'
		},
		data : {
			userName : sessionService.get('user')
		}
	};

	$http(request2).then(function(response) {
		// success callback
		if (response.status == 200 && response.data != "") {
			//alert("Photo Uploaded Successfully");
			$scope.loading = true;

			$scope.img = response.data;
			$scope.loading = false;
			//    alert("Session Name"+sessionService.get('user'));

			//  $scope.photoname=response.data.photoName;
			// ./images/muraligowthamsb@gmail.com.jpg
			//  alert($scope.photoname);
			//$window.location.href = './profile-update.html';

		} else if (response.data == "") {
			//alert("No photos in your album");
			$scope.loading = false;

		} else {
			// alert("Error: "
			//+ response.data.statusText);
		}
	});

	$http(request3).then(function(response) {
		// success callback
		if (response.status == 200 && response.data != "") {
			//alert("Photo Uploaded Successfully");
			// alert("Response sucess");
			$scope.img1 = response.data;

			//    alert("Session Name"+sessionService.get('user'));

			//  $scope.photoname=response.data.photoName;
			// ./images/muraligowthamsb@gmail.com.jpg
			//  alert($scope.photoname);
			//$window.location.href = './profile-update.html';

		} else if (response.data == "") {
			// alert("No photos in your album");

		} else {
			alert("Error: " + response.data.statusText);
		}
	});

	$scope.Logout = function() {
		sessionService.destroy('user');
		$window.location.href = './index.html';

	}

	var formdata = new FormData();
	$scope.getTheFiles = function($files) {
		angular.forEach($files, function(value, key) {
			formdata.append(key, value);
		});
		formdata.append("username", sessionService.get('user'));

	};

	$scope.uploadFiles = function() {

		if ($scope.Accessory == "" || $scope.Accessory == null) {

			alert("invalid Details");
		} else if ($scope.Accessory == "" || $scope.Accessory == null) {

			alert("invalid Details");
		} else {

			var request = {
				method : 'POST',
				url : '/photo/upload',
				responseType : 'text',
				data : formdata,
				headers : {
					'Content-Type' : undefined
				}

			};

			$http(request).success(function() {

				$scope.addData();

				// data should be text string here (only if the server response is text/plain)
			}).error(function(data, status) {
				console.error('Repos error', status, data);
				alert("Invalid Photo");
			});

			// SEND THE FILES.
		}
	}

	$scope.addData = function() {

		var request1 = {
			method : 'POST',
			url : '/photo/add',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : {
				description : '',
				accessoryType : $scope.Accessory,
				brand : $scope.BrandName1,
				user : {
					userName : sessionService.get('user')
				}
			}

		};
		// alert("Acc"+$scope.Accessory);

		$http(request1).then(function(response) {
			// success callback
			if (response.status == 200 && response.data != "") {
				alert("Photo Uploaded Successfully");
				//    alert("Session Name"+sessionService.get('user'));
				$window.location.href = './ImageUpload.html'
				// $scope.photoname=response.data.photoName;
				// ./images/muraligowthamsb@gmail.com.jpg
				//  alert($scope.photoname);
				//$window.location.href = './profile-update.html';

			} else if (response.data == "") {
				alert("Incorrect Credentials ");

			} else {
				alert("Error: " + response.data.statusText);
			}
		});
	}

});
