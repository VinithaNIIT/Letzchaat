'use strict';
 
app.controller('UserController', ['$scope', 'UserService','$rootScope', function($scope, UserService,$rootScope) {
    var self = this;
    self.user={username:'',
    		password:'',
    		name:'',
    		email:'',
    		mobile:'',
    		address:'',
    		status:'',
    		role:'',
    		reason:'',
    		isonline:'',
    		errorcode:'',
    		errormessage:''
    		
    
    
    };
    self.users=[];
    
    
    self.login=function(){
    	
    	console.log("Inside login controller");
    	self.authenticate(self.user);
    	
    }
    
    self.authenticate=function(user){
    	
    	UserService.authenticate(user).then(
    		function(d)	{
    			
    			self.user=d;
    			if(self.user.errorcode=="404"){
    				
    				alert("Invalid Credentials...Please try again!")
    			}
    			
    			else{
    				console.log($rootScope.currentUser);
    				
    				$rootScope.currentUser={
    						
    						username:self.user.username,
    						name:self.user.name,
    						role:self.user.role
    						
    						
    				};
    				
    				 $http.defaults.headers.common['Authorization']='Basic'+$rootScope.currentUser;
    				 $cookieStore.put('currentUser',$rootScope.currentUser);
    				console.log($rootScope.currentUser)
    			}
    			
    		}
    	
    	)}
    
    self.submit=function(){
    	console.log('Saving new Registration ',self.user);
    	self.createUser(self.user);
    }
    self.createUser=function(user){
    	console.log('Create User....');
    	UserService.createUser(user)
    	.then(
    			function(d){
    				alert('Thank you for Registration')
    			},
    			function(errResponse){
					console.error('Error while creating user.....');
    			}
    	);
    	
    };
    
    
    
}]);