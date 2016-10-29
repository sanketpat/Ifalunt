angular.module('iflaunt',[]).controller(
	'RegisterController', 
	[
		'$scope', '$http',
		function($scope, $http) {
			$scope.signUpSuccess = true;
			$scope.email;
			$scope.password;
			$scope.confirmPassword;
			$scope.signUp= function(email, password, confirmPassword)
			{
				var req = {
							method : 'POST',
							url : 'http://localhost:8080/user/register',
							headers : {
								'Content-Type' : 'application/json'
							},
							data : {
								userName : email,
								password : password
							}
				}
				if(password == confirmPassword){
							$http(req).then(
									function(response) {
										// success callback
										if (response.status == 200) {
											$scope.signUpSuccess = false;
											alert("Sign Up Successful!");
											
										} else {
											alert("Error: "
													+ response.data.statusText);
										}
									});
				}
			}
		}
	]
);