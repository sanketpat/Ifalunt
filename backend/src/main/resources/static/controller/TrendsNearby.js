app.controller('Trends', function ($scope, $http, sessionService, $window) {
	if(sessionService.get('user')==null){
		$window.location.href = './index.html';
        
	}else{
		$scope.user=sessionService.get('user')
		$scope.Email=$scope.user;
	}
	
	var urls='http://localhost:8080/photo/getBrands/'+$scope.Email;
		var req = {
	             method : 'POST',
	             url :urls,
	            
	            
	         };
		
		$http(req)
	     .then(function(response) {
	    	 if (response.status == 200 && response.data!="") {
                //alert("Photo Uploaded Successfully");
                
                
                $scope.trends=response.data;
              
            
            }
	    	 
	    	 
	    	 
	     });

		
		
	   	  $scope.Logout = function () {
		  sessionService.destroy('user');
		  $window.location.href = './index.html';
	        	  
	  }
	
});
