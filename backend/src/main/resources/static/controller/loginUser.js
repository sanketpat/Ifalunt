
var iflaunt=angular.module('iflaunt', []);

iflaunt.controller('LoginController',
    ['$window', '$scope', '$http', function($window, $scope, $http) {
            $scope.email;
            $scope.password;
            $scope.FlagEmailLogin=false;
           $scope.passwordFlagLogin=false;
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
                
                
    if($scope.email==""||$scope.email==null){
                	$scope.FlagEmailLogin=true;
                	
                }
                else if( $scope.password==""|| $scope.password==null){
                	$scope.FlagEmailLogin=false;
                	$scope.passwordFlagLogin=true;
                	
                }else{
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
                        });}

            }
        }
    ]
);




iflaunt.controller('RegisterController',
    ['$window','$scope', '$http', function($window,$scope, $http) {
            $scope.signUpSuccess = true;
            $scope.FlagEmail=false;
            $scope.passwordFlag=false; 
            $scope.ConfirmpasswordFlag=false;
            $scope.email;
            $scope.password;
            $scope.UseralreadyExist=false;
        	
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
                
                
                
                if($scope.email==null || $scope.password==null||$scope.password==""||$scope.email==""||$scope.confirmPassword==""||$scope.confirmPassword==null){
                	
                	if($scope.email=null||$scope.email==""){
                	$scope.FlagEmail=true; 
                	}
                	else if($scope.password==null||$scope.password==""){
                		$scope.passwordFlag=true; 
                		$scope.email=email;
                			}
                	else if($scope.confirmPassword==null||$scope.confirmPassword==""){
                		$scope.ConfirmpasswordFlag=true; 
                		$scope.passwordFlag=false;
                		$scope.email=email;
                		 $scope.password=""
                			 $scope.confirmPassword=""
                	                
                    		
                	}
                	
                	
                }else if($scope.password != $scope.confirmPassword){
                	$scope.ConfirmpasswordFlag=true; 
            		$scope.passwordFlag=false;
            		 $scope.password=""
            			 $scope.confirmPassword=""
                }else
                
                if(password == $scope.confirmPassword){
                    $http(req).then(
                        function(response) {
                        	$scope.response=response;
                            // success callback
                            if (response.status == 200 && response.data!="") {
                                $scope.signUpSuccess = false;
                               var json= JSON.parse(JSON.stringify(response.data));


                                alert("Sign Up Successful!"+ json.userName);
                                $window.location.href = './partials/profile-update.html';

                            }
                            
                            
                            else if(response.data==""){
                            	$scope.UseralreadyExist=true; 
                            	$scope.ConfirmpasswordFlag=false; 
                        		$scope.passwordFlag=false;	
                            }                           
                            else {userName
                                alert("Error: "
                                    + response.data.statusText);
                            }
                        });
                }
            }
            
            
            
        }
    ]

);

iflaunt.service('sharedProperties', function () {
    var property = $scope.response.data;

  this.getdata=function(){
	  var property = $scope.response.data;
	  return property;
  }
});



