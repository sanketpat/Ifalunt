
'use strict';
app.controller('LoginController',
    ['$window', '$scope', '$http','sessionService', function($window, $scope, $http, sessionService) {
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
                            if (response.status == 200 && response.data!="") {
                                alert("Login Successful!");
                                sessionService.set('user',$scope.email);
                                //alert("Session Created"+ sessionService.get($scope.email));
                                $window.location.href = './Home.html';
                                

                            }
                            else if (response.data=="") {
                                alert("Incorrect Credentials ");
                               
                            }else {
                                alert("Error: "
                                    + response.data.statusText);
                            }
                        });}

            }
        }
    ]
);




app.controller('RegisterController',
    ['$window','$scope', '$http','sessionService', function($window,$scope, $http, sessionService) {
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
                                
                                sessionService.set('user',$scope.email);
                                alert("Session Created"+ sessionService.get('user'));
                                
                                $window.location.href = './profile-update.html';

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




