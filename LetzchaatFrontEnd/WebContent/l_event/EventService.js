'use strict'
app.factory('EventService', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
 
    var REST_SERVICE_URI = 'http://localhost:8081/LetzchaatBackEnd';
 return{
	 
	 fetchAllEvents:function(){
	 console.log('fetchAllEvents Method in EventServices')
	 return $http.get(REST_SERVICE_URI+'/events/')
	 .then(
	function(response){
		console.log('success in EventService',response.data)
		return response.data;
	},
	function(errResponse){
		console.error('Error while fetching the Events');
		return $q.reject(errResponse);
	}
	 );
	 
 },
 createEvent:function(event){
	 console.log('Event service')
	 return $http.post(REST_SERVICE_URI+'/createevent/',event)
	 .then(function(response){
		 return response.data;
		 
	 },
	 function(errResponse){
		 console.error('Error while creating the Event');
		 return $q.reject(errResponse);
		 
	 }
			 
	 );
 }
 }
}]);