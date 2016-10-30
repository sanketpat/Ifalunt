
var iflaunt=angular.module('iflaunt', []);

iflaunt.controller('LoginController',
    ['$window', '$scope', '$http', function($window, $scope, $http) {
            $scope.email;
            $scope.password;
            $scope.signIn= function(eamil, password)
            {
                var req = {
                    method : 'POST',
                    url : 'http://localhost:8080/user/login',
                    headers : {
                        'Content-Type' : 'application/json'
                    },
                    data : {
                        username : eamil,
                        password : password
                    }
                }
                $http(req).then(
                        function(response) {
                            // success callback
                            if (response.status == 200) {
                                alert("Login Successful!");
                                $window.location.href = './partials/profile-update.html';

                            } else {
                                alert("Error: "
                                    + response.data.statusText);
                            }
                        });

            }
        }
    ]
);

iflaunt.controller('RegisterController',
    ['$window','$scope', '$http', function($window,$scope, $http) {
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
                               var json= JSON.parse(JSON.stringify(response.data));


                                alert("Sign Up Successful!"+ json.userName);
                                $window.location.href = './partials/profile-update.html';

                            } else {userName
                                alert("Error: "
                                    + response.data.statusText);
                            }
                        });
                }
            }
        }
    ]
);



