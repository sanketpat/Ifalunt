 
           
app.directive('appFilereader', function($q) {
    var slice = Array.prototype.slice;

    return {
        restrict: 'A',
        require: '?ngModel',
        link: function(scope, element, attrs, ngModel) {
                if (!ngModel) return;

                ngModel.$render = function() {};

                element.bind('change', function(e) {
                    var element = e.target;

                    $q.all(slice.call(element.files, 0).map(readFile))
                        .then(function(values) {
                            if (element.multiple) ngModel.$setViewValue(values);
                            else ngModel.$setViewValue(values.length ? values[0] : null);
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


app.directive('ngFiles', ['$parse', function ($parse) {

    function fn_link(scope, element, attrs) {
        var onChange = $parse(attrs.ngFiles);
        element.on('change', function (event) {
            onChange(scope, { $files: event.target.files });
        });
    };

    return {
        link: fn_link
    }
} ]);


app.controller('fupController', function ($scope, $http, sessionService, $window) {

	
		  var req = {
		             method : 'POST',
		             url : 'http://localhost:8080/user/getUserdetails',
		             headers : {
		                 'Content-Type' : 'application/json'
		             },
		             data : {
		                 username :sessionService.get('user')
		                }
		         };
		     
		     $http(req)
		     .then(function(response) {
		    	 if (response.status == 200 && response.data!="") {
                     //alert("Photo Uploaded Successfully");
                     
                     
                     $scope.firstname=response.data.firstName;
                     $scope.lastname=response.data.lastName;
                     
                     $scope.myDate= response.data.birthDate
                     $scope.Bio= response.data.bio
                     $scope.city= response.data.city
                  if( response.data.gender=="M")
                	  { $scope.male=response.data.gender;
                	  
                	  }
                  else{$scope.male=response.data.gender;
                	  
                  }
                 //    alert("Session Name"+sessionService.get('user'));
                     
                    // $scope.photoname=response.data.photoName;
                    // ./images/muraligowthamsb@gmail.com.jpg
                   //  alert($scope.photoname);
                     //$window.location.href = './profile-update.html';

                 }
		    	 
		    	 
		    	 
		     });
		     
		     

	  
	
		//alert("Session Created"+ sessionService.get('user'));

	if(sessionService.get('user')==null){
		$window.location.href = './index.html';
        
	}else{
		$scope.user=sessionService.get('user')
		$scope.Email=$scope.user;
	}
	
    var formdata = new FormData();
    $scope.getTheFiles = function ($files) {
        angular.forEach($files, function (value, key) {
            formdata.append(key, value);
        });
        formdata.append("username",sessionService.get('user'));
        
        
    };
    $scope.setGender = function (value) {
        //If DIV is visible it will be hidden and vice versa.
        $scope.gender = value;
    }

    $scope.uploadFiles = function () {

        var request = {
            method: 'POST',
            url: 'user/upload',
            data: formdata,
            headers: {
                'Content-Type': undefined
            }
        };
        
        
       
        
        
        

        // SEND THE FILES.
        $http(request)
        .then(function(response) {
                    // success callback
                    if (response.status == 200 && response.data!="") {
                        alert("Photo Uploaded Successfully");
                    //    alert("Session Name"+sessionService.get('user'));
                        
                        $scope.photoname=response.data.photoName;
                       // ./images/muraligowthamsb@gmail.com.jpg
                      //  alert($scope.photoname);
                        //$window.location.href = './profile-update.html';

                    }
                    else if (response.data=="") {
                        alert("Incorrect Credentials ");
                       
                    }else {
                        alert("Error: "
                            + response.data.statusText);
                    }
                });
    }
    
	$scope. NavigetetoHome= function () {
    	$window.location.href = './Home.html';
    }
    

    $scope.uploadDetails = function () {
    	
    //	alert("You are herte");
    	

    	 var req = {
                 method : 'POST',
                 url : 'http://localhost:8080/user/update',
                 headers : {
                     'Content-Type' : 'application/json'
                 },
                 data : {
                     userName : $scope.user,
                     firstName:$scope.firstname,
                    	 lastName:$scope.lastname,
                    		 photoName:$scope.Email,
                    			 birthDate:$scope.myDate,
                    				 gender:$scope.male,
                    					 bio:$scope.Bio,
                    					 city:$scope.city
                 }
             }
    	
    	
if($scope.firstname==""||$scope.myDate==""||$scope.male==""){
	alert("Enter Valid Details")
}  
else	 if($scope.firstname==null||$scope.myDate==null||$scope.male==null){
    			alert("Enter Valid Details")
    		}  


else{ 	
    	  $http(req).then(
                  function(response) {
                  	$scope.response=response;
                      // success callback
                      if (response.status == 200 && response.data!="") {
                          $scope.signUpSuccess = false;
                         var json= JSON.parse(JSON.stringify(response.data));
                        $window.location.href = './Home.html';

                      }
                                               
                      else {userName
                          alert("Error: "
                              + response.data.statusText);
                      }
                  });

    	 }
    }
    
    function dateController ($scope) {
        $scope.myDate = new Date();
        $scope.minDate = new Date(
           $scope.myDate.getFullYear(),
           $scope.myDate.getMonth() - 2,
           $scope.myDate.getDate());
        $scope.maxDate = new Date(
           $scope.myDate.getFullYear(),
           $scope.myDate.getMonth() + 2,
           $scope.myDate.getDate());
        $scope.onlyWeekendsPredicate = function(date) {
           var day = date.getDay();
           return day === 0 || day === 6;
        }
     } 
    
});
app.directive(
        'dateInput',
        function(dateFilter) {
            return {
                require: 'ngModel',
                template: '<input type="date"></input>',
                replace: true,
                link: function(scope, elm, attrs, ngModelCtrl) {
                    ngModelCtrl.$formatters.unshift(function (modelValue) {
                        return dateFilter(modelValue, 'yyyy-MM-dd');
                    });
                    
                    ngModelCtrl.$parsers.unshift(function(viewValue) {
                        return new Date(viewValue);
                    });
                },
            };
    });