'use strict';
 
app.factory('UserService', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
 
    var REST_SERVICE_URI = 'http://localhost:8081/LetzchaatBackEnd';
 return{
        authenticate: function(user){
        	console.log('calling authenticate method');
    	return $http.post(REST_SERVICE_URI+'/login/',user)
    	.then(
    		function(response){
    			
    			if(response.data.errorcode==""){
    				
$rootScope.currentUser={
    						
    						username:self.user.username,
    						name:self.user.name,
    						role:sef.user.role
    				};
    			}
    			
    			return response.data;
    		},
    		function(errResponse){
    			
    			console.error('Error while fetching UserDetails');
    			return $q.reject(errResponse);
    		});
    	},
 
 createUser:function(user){
	 console.log('createUser Method in UserService')
	 return $http.post(REST_SERVICE_URI+'/register/',user)
	 .then(
			 function(response){
				 return response.data;
			 },
			 function(errResponse){
				 console.error('Error while creating the User');
				 return $q.reject(errResponse);
				 
			 });
	 }
 
 
 }}]);