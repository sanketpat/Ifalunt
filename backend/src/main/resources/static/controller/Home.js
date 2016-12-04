


app.controller('MainCtrl', function ($scope, $http, sessionService, $window) {
	$scope.myVar=false;
	$scope.hideflag=false;
	// Set of Photos
	if(sessionService.get('user')==null){
		$window.location.href = './index.html';
        
	}else{
		$scope.user=sessionService.get('user')
		$scope.Email=$scope.user;
		
		
		var requserDetails = {
	             method : 'POST',
	             url : 'http://localhost:8080/user/getUserdetails',
	             headers : {
	                 'Content-Type' : 'application/json'
	             },
	             data : {
	                 username :sessionService.get('user')
	                }
	         };
		
		 $http(requserDetails)
	     .then(function(response) {
	    	 if (response.status == 200 && response.data!="") {
                //alert("Photo Uploaded Successfully");
                
                
                $scope.firstname=response.data.firstName;
                $scope.lastname=response.data.lastName;
                
                $scope.myDate= response.data.birthDate
                $scope.Bio= response.data.bio
               
           
            }
	    	 
	    	
	    	 
	     });
		
		
		
		
		
		
		var req = {
	             method : 'GET',
	             url : 'http://localhost:8080/user/getPosts/'+$scope.user,
	             headers : {
	                 'Content-Type' : 'application/json'
	             },
	           
	         };
		
		
		 $http(req)
	        .then(function(response) {
	                    // success callback
	                    if (response.status == 200 && response.data!="") {
	                       
	                        $scope.displayDetails=response.data;
	                        
	                    	$scope.myVar=true;
	                    	$scope.hideflag=true;
	                    

	                    }
	                    else if (response.data=="") {
	                        alert("Start Following your Friends to See their Posts ");
	                       
	                    }else {
	                        alert("Error: "
	                            + response.data.statusText);
	                    }
	                });
		
	$scope.photos = [
	{src: 'http://farm9.staticflickr.com/8042/7918423710_e6dd168d7c_b.jpg', desc: 'Image 01'},
	{src: 'http://farm9.staticflickr.com/8449/7918424278_4835c85e7a_b.jpg', desc: 'Image 02'},
	{src: 'http://farm9.staticflickr.com/8457/7918424412_bb641455c7_b.jpg', desc: 'Image 03'},
	{src: 'http://farm9.staticflickr.com/8179/7918424842_c79f7e345c_b.jpg', desc: 'Image 04'},
	{src: 'http://farm9.staticflickr.com/8315/7918425138_b739f0df53_b.jpg', desc: 'Image 05'},
	{src: 'http://farm9.staticflickr.com/8461/7918425364_fe6753aa75_b.jpg', desc: 'Image 06'}
	];
	
	$scope.Wrangler = [
	             	{src: 'https://rukminim1.flixcart.com/image/832/832/shirt/g/z/7/w15484945485frost-grey-wrangler-m-original-imaezhgagjzeyc4b.jpeg?q=70', desc: 'https://www.flipkart.com/wrangler-men-s-solid-casual-grey-shirt/p/itmeznj6zpjvvgpx?pid=SHTEHPZ95VHFUGZ7&srno=s_1_5&otracker=search&lid=LSTSHTEHPZ95VHFUGZ7ZTTYX2&qH=26a29adce1d7965d',Name: 'Wrangler Mens Solid Casual Grey Shirt'},
	             	{src: 'https://rukminim1.flixcart.com/image/832/832/shirt/z/t/w/w14848945114navy-wrangler-m-original-imaezh77x39nmcxs.jpeg?q=70', desc: 'https://www.flipkart.com/wrangler-men-s-checkered-casual-white-dark-blue-shirt/p/itmehpzacyajexhh?pid=SHTEHPZARWPKGZTW&srno=s_1_2&otracker=search&lid=LSTSHTEHPZARWPKGZTWISU0HT&qH=26a29adce1d7965d',Name: 'Wrangler Mens Solid Casual Blue Shirt'},
	             	{src: 'https://rukminim1.flixcart.com/image/832/832/shirt/z/d/8/w1490557112zwhite-wrangler-m-original-imaezmjjmwarhsjt.jpeg?q=70', desc: 'https://www.flipkart.com/wrangler-men-s-solid-casual-white-shirt/p/itmezpthyr4afaz6?pid=SHTEHPZ9AQMV4KGJ&srno=s_1_8&otracker=search&lid=LSTSHTEHPZ9AQMV4KGJHWK0JQ&qH=26a29adce1d7965d',Name: 'Wrangler Mens Solid Casual White Shirt'},
	             	{src: 'https://rukminim1.flixcart.com/image/832/832/shirt/z/c/u/w1495794554bsand-wrangler-m-original-imaezh77zsfmjcgz.jpeg?q=70', desc: 'https://www.flipkart.com/wrangler-men-s-printed-casual-brown-shirt/p/itmehpz9nfxsxxsz?pid=SHTEHPZ9CYHMYEUZ&srno=s_1_14&otracker=search&lid=LSTSHTEHPZ9CYHMYEUZCJSCRU&qH=26a29adce1d7965d',Name: 'Wrangler Mens Solid Casual Brown Shirt'},
	             	
	             	
	             	
	             	
	             	
	             	];
	
	$scope.Levis = [
		             	{src: 'https://rukminim1.flixcart.com/image/832/832/jean/v/f/u/23677-0009blues-levi-s-32-original-imaez96ftf6kqraz.jpeg?q=70', desc: 'https://www.flipkart.com/levi-s-regular-men-s-dark-blue-jeans/p/itmezeghbvnzbdfy?pid=JEAEMCBZXTNGGVKM&srno=s_1_1&otracker=search&lid=LSTJEAEMCBZXTNGGVKM4GRIWM&qH=36e28ffbc38804f5',Name: 'Levis Regular Mens Dark Blue Jeans'},
		             	{src: 'https://rukminim1.flixcart.com/image/832/832/jean/z/b/j/21788-0004indigo-levi-s-32-original-imaefn5mhsvkxw2x.jpeg?q=70', desc: 'https://www.flipkart.com/levi-s-skinny-men-s-blue-jeans/p/itmeehwdcbyzwhfe?pid=JEAEEHWDMVHZTRFZ&srno=s_1_4&otracker=search&lid=LSTJEAEEHWDMVHZTRFZVGQKDW&qH=36e28ffbc38804f5',Name: 'Levis Skinny Mens Blue Jeans'},
		             	{src: 'https://rukminim1.flixcart.com/image/832/832/shirt/p/u/g/28697-0000reds-levi-s-m-original-imaezgfzkwytag87.jpeg?q=70', desc: 'https://www.flipkart.com/levi-s-men-s-casual-shirt/p/itmemcbrd33g7vzn?pid=SHTEMCBRJT8JEFWJ&srno=s_1_1&otracker=search&lid=LSTSHTEMCBRJT8JEFWJXRYGYX&qH=db200580ff57f7f9',Name: 'Levis Mens Casual Shirt'},
		             	{src: 'https://rukminim1.flixcart.com/image/832/832/shirt/u/9/3/24577-0033yelllow-levi-s-m-original-imaez962gutn87fw.jpeg?q=70', desc: 'https://www.flipkart.com/levi-s-men-s-solid-casual-yellow-shirt/p/itmezdnyqphfn6dt?pid=SHTEMCBR24GEDYRF&srno=s_1_11&otracker=search&lid=LSTSHTEMCBR24GEDYRFDDW2E1&qH=db200580ff57f7f9',Name: 'Levis Mens Solid Casual Yellow Shirt'},
		             	
		             	
		             	
		             	
		             	
		             	];
	
	

	$scope.PepeJeans = [
		             	{src: 'https://rukminim1.flixcart.com/image/832/832/jean/h/a/r/tyler-ht-bl-rinse-pepe-36-original-imaehj2sryzk54jh.jpeg?q=70', desc: 'https://www.flipkart.com/levi-s-regular-men-s-dark-blue-jeans/p/itmezeghbvnzbdfy?pid=JEAEMCBZXTNGGVKM&srno=s_1_1&otracker=search&lid=LSTJEAEMCBZXTNGGVKM4GRIWM&qH=36e28ffbc38804f5',Name: 'Levis Regular Men Dark Blue Jeans'},
		             	{src: 'https://rukminim1.flixcart.com/image/832/832/jean/h/p/k/bickford-h-mid-tint-pepe-34-original-imaehj2zdj7sjkde.jpeg?q=70', desc: 'https://www.flipkart.com/levi-s-skinny-men-s-blue-jeans/p/itmeehwdcbyzwhfe?pid=JEAEEHWDMVHZTRFZ&srno=s_1_4&otracker=search&lid=LSTJEAEEHWDMVHZTRFZVGQKDW&qH=36e28ffbc38804f5',Name: 'Levis Skinny Men Blue Jeans'},
		             	{src: 'https://rukminim1.flixcart.com/image/832/832/shirt/m/u/5/bronco-lsindigo-pepe-jeans-m-original-imaektxsmwjgkyz9.jpeg?q=70', desc: 'https://www.flipkart.com/pepe-jeans-men-s-solid-casual-blue-shirt/p/itmemxhcs6rzgbnh?pid=SHTEJMDAKHWTGMU5&srno=s_1_7&otracker=search&lid=LSTSHTEJMDAKHWTGMU5BCKZPD&qH=15e8f2ea4a8bfff7',Name: 'Pepe Jeans Men Solid Casual Blue Shirt'},
		             	{src: 'https://rukminim1.flixcart.com/image/832/832/shirt/y/8/b/jerry-lsnavy-pepe-jeans-l-original-imaemv7x6swe2q2g.jpeg?q=70', desc: 'https://www.flipkart.com/pepe-jeans-men-s-floral-print-casual-dark-blue-shirt/p/itmemwhy8augcuqe?pid=SHTEJMDACSJHVKP2&srno=s_1_6&otracker=search&lid=LSTSHTEJMDACSJHVKP2AMALWH&qH=15e8f2ea4a8bfff7',Name: 'Pepe Jeans Mens Floral Print Casual Dark Blue Shirt'},
		        ];
	

	$scope.PepeJeansWomen = [
		             	{src: 'https://rukminim1.flixcart.com/image/832/832/t-shirt/b/q/6/e-ladies-knits-ls-non-cottonchar-pepe-jeans-s-original-imaezft2reauu6ta.jpeg?q=70', desc: 'https://www.flipkart.com/pepe-jeans-printed-women-s-round-neck-grey-t-shirt/p/itmeznkx2bstmnjk?pid=TSHEHHENBTEQ62KH&sattr=color&st=color&otracker=search',Name: 'Pepe Jeans Printed Women Round Neck Grey T-Shirt'},
		             	{src: 'https://rukminim1.flixcart.com/image/832/832/t-shirt/e/b/u/e-ladies-knits-ss-non-cottonecru-pepe-jeans-s-original-imaemxcnajy5u2vm.jpeg?q=70', desc: 'https://www.flipkart.com/pepe-jeans-printed-women-s-round-neck-black-t-shirt/p/itmez6srxxhzhgng?pid=TSHEHHENVDNXXEBU&srno=s_1_2&otracker=search&lid=LSTTSHEHHENVDNXXEBUSLHD7D&qH=169f63504f7e411c',Name: 'Pepe Jeans Printed Women Round Neck Black T-Shirt'},
		             	{src: 'https://rukminim1.flixcart.com/image/832/832/jean/k/j/5/popy-blue-pepe-jeans-28-original-imaehxxpbgsf8m6r.jpeg?q=70', desc: 'https://www.flipkart.com/pepe-jeans-slim-women-s-blue/p/itmehvgyxyw6zgdf?pid=JEAEHVG5U5R6PQBG&srno=s_1_1&otracker=search&lid=LSTJEAEHVG5U5R6PQBGUY3SCD&qH=4b4887b09895fb63',Name: 'Pepe Jeans Slim Women Blue Jeans'},
		             	{src: 'https://rukminim1.flixcart.com/image/832/832/jean/f/f/b/juliya-3-pepe-jeans-30-original-imaenmnhsa4fwv67.jpeg?q=70', desc: 'https://www.flipkart.com/pepe-jeans-skinny-women-s-blue/p/itmenn4vxdypybk9?pid=JEAENN4VTGHKXVR8&srno=s_1_2&otracker=search&lid=LSTJEAENN4VTGHKXVR8Q62VQO&qH=4b4887b09895fb63',Name: 'Pepe Jeans Skinny Women Blue Jeans'},
		        ];
		
	
	$scope.LevisWomen = [
	 		             	{src: 'https://rukminim1.flixcart.com/image/832/832/shirt/z/f/n/18668-0003-levi-s-s-original-imaemrp5fd9qzy8y.jpeg?q=70', desc: 'https://www.flipkart.com/levi-s-women-s-solid-casual-blue-shirt/p/itmemwhz7qvgzyzy?pid=SHTEK48GNZ2F7VRU&srno=s_1_2&otracker=search&lid=LSTSHTEK48GNZ2F7VRURYVCIA&qH=f47d97392dbcfd15',Name: 'Levi Women Solid Casual Blue Shirt'},
	 		             	{src: 'https://rukminim1.flixcart.com/image/832/832/shirt/4/q/g/18668-0010-levi-s-s-original-imaemrp5q3zxg8dt.jpeg?q=70', desc: 'https://www.flipkart.com/levi-s-women-s-solid-casual-blue-shirt/p/itmemwhz8te9fdcg?pid=SHTEK48G9UEVQ4QG&srno=s_1_4&otracker=search&lid=LSTSHTEK48G9UEVQ4QGHVHT9W&qH=f47d97392dbcfd15',Name: 'Levi Women Solid Casual Blue Shirt'},
	 		             	{src: 'https://rukminim1.flixcart.com/image/832/832/jean/p/q/3/21361-0009-levi-s-28-original-imaeguh3a9h5rm7s.jpeg?q=70', desc: 'https://www.flipkart.com/levi-s-skinny-women-s-blue-jeans/p/itmejyf68z3fzygb?pid=JEAEGQFWHSCNAFAA&srno=s_1_1&otracker=search&lid=LSTJEAEGQFWHSCNAFAATMMWFO&qH=deea7c8ecfd00b1a',Name: 'Levi Skinny Women Blue Jeans'},
	 		             	{src: 'https://rukminim1.flixcart.com/image/832/832/jean/v/d/9/21322-0000-levi-s-28-original-imaegsctqftfubuh.jpeg?q=70', desc: 'https://www.flipkart.com/levi-s-skinny-women-s-blue-jeans/p/itmegqfwhwm7syns?pid=JEAEGQFWQYHVQF8F&srno=s_1_2&otracker=search&lid=LSTJEAEGQFWQYHVQF8FCACXZR&qH=deea7c8ecfd00b1a',Name: 'Levi Skinny Women Blue Jeans'},
	 		        ];
	 		
	 	

	$scope.WranglerWomen = [
	 		             	{src: 'https://rukminim1.flixcart.com/image/832/832/shirt/6/a/t/wrsh5392raspberry-wrangler-s-original-imaege475yxt3xye.jpeg?q=70', desc: 'https://www.flipkart.com/wrangler-women-s-printed-formal-pink-purple-shirt/p/itmegftk3cfgq4zk?pid=SHTEGFTKZPHUUNXU&srno=s_1_3&otracker=search&lid=LSTSHTEGFTKZPHUUNXUXGI6VS&qH=3b9f34b3de684fc8',Name: 'Wrangler Women Printed Formal Pink, Purple Shirt'},
	 		             	{src: 'https://rukminim1.flixcart.com/image/832/832/t-shirt/f/m/5/w14546j03n1doff-white-wrangler-s-original-imaezw7qnxgfmhec.jpeg?q=70', desc: 'https://www.flipkart.com/wrangler-women-s-t-shirt/p/itmehgyqvvahpu9f?pid=TSHEHGYTGSRZRFM5&srno=s_1_11&otracker=search&lid=LSTTSHEHGYTGSRZRFM5AKGZTH&qH=3b9f34b3de684fc8',Name: 'Wrangler Women T-Shirt'},
	 		             	{src: 'https://rukminim1.flixcart.com/image/832/832/jean/n/w/k/wrjn5203mid-night-wrangler-28-original-imaegz3tex3jbegz.jpeg?q=70', desc: 'https://www.flipkart.com/wrangler-slim-women-s-black-jeans/p/itmedkk484qejdzc?pid=JEAEDKK4YKC2BNWK&srno=s_1_10&otracker=search&lid=LSTJEAEDKK4YKC2BNWKPETZAM&qH=3b9f34b3de684fc8',Name: 'Wrangler Slim Women Black Jeans'},
	 		             	{src: 'https://rukminim1.flixcart.com/image/832/832/jean/m/d/r/wrjn5843true-blue-wrangler-28-original-imaemv2zxzpzb3vb.jpeg?q=70', desc: 'https://www.flipkart.com/wrangler-slim-women-s-blue-jeans/p/itmemwm4bpxwrggg?pid=JEAEHFJQ2ESZT3F6&srno=s_1_14&otracker=search&lid=LSTJEAEHFJQ2ESZT3F63GAZIU&qH=3b9f34b3de684fc8',Name: 'Wrangler Slim Women Blue Jeans'},
	 		        ];
	
	
	
	
	
	// initial image index
	$scope._Index = 0;
	
	
	// if a current image is the same as requested image
	$scope.isActive = function (index) {
	return $scope._Index === index;
	
	};
	$scope.teamProfile = $scope.photos[$scope._Index];
	if($scope.teamProfile.desc=="Image 03"){
		
		
		$scope.Suggphotos=$scope.Wrangler;
	}
	
	else if($scope.teamProfile.desc=="Image 02"){
		$scope.Suggphotos=$scope.WranglerWomen;
	}	
	else if($scope.teamProfile.desc=="Image 01"){
		$scope.Suggphotos=$scope.PepeJeansWomen;
	}
	else if($scope.teamProfile.desc=="Image 04"){
		$scope.Suggphotos=$scope.PepeJeansWomen;
	}
	else if($scope.teamProfile.desc=="Image 05"){
		$scope.Suggphotos=$scope.LevisWomen;
	}
	else if($scope.teamProfile.desc=="Image 06"){
		$scope.Suggphotos=$scope.Wrangler;
	}

	
	
	
	// show prev image
	$scope.showPrev = function () {
	$scope._Index = ($scope._Index > 0) ? --$scope._Index : $scope.displayDetails.length - 1;
	};
	// show next image
	$scope.showNext = function () {
			$scope._Index = ($scope._Index < $scope.displayDetails.length - 1) ? ++$scope._Index : 0;
			$scope.teamProfile = $scope.displayDetails[$scope._Index];
			if($scope.teamProfile.brand=="Wrangler" && $scope.teamProfile.gender=="M" ){
				
		
				$scope.Suggphotos=$scope.Wrangler;
			}
			
			else if($scope.teamProfile.brand=="Wrangler" && $scope.teamProfile.gender=="F" ){
				$scope.Suggphotos=$scope.WranglerWomen;
			}	
			else if($scope.teamProfile.brand=="PepeJeans" && $scope.teamProfile.gender=="M"){
				$scope.Suggphotos=$scope.PepeJeans;
			}
			else if($scope.teamProfile.brand=="PepeJeans" && $scope.teamProfile.gender=="F"){
				$scope.Suggphotos=$scope.PepeJeansWomen;
			}
			else if($scope.teamProfile.brand=="Levis" && $scope.teamProfile.gender=="F"){
				$scope.Suggphotos=$scope.LevisWomen;
			}
			else if($scope.teamProfile.brand=="Levis" && $scope.teamProfile.gender=="M"){
				$scope.Suggphotos=$scope.Levis;
			}
			
			
	};
	// show a certain image
	$scope.showPhoto = function (index) {
	$scope._Index = index;
	};
	}
	
	  $scope.Logout = function () {
		  sessionService.destroy('user');
		  $window.location.href = './index.html';
	        	  
	  }
	
	});

