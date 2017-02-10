'use strict';
app.controller('EventController', ['$scope','$location', 'EventService', function($scope,$location, EventService){
	var self=this;
	self.event={
			eventid:'',
			event_name:'',
			date_of_event:'',
			venue:'',
			description:'',
			errorCode:'',
			errorMessage:'',
			imageurl:''
		};
	self.events=[];
	
	console.log('INSIDE event CONTROLLER')
	self.submit=function(){
		
		console.log('Saving new event',self.event);
		self.createEvent(self.event);
	}
	self.createEvent=function(event){
		
		console.log('create events...',self.event);
		EventService.createEvent(event)
		.then(
				function(d){
    				alert('Successfully created the event')
    				$location.path("/");
    			},
				function(errResponse){
					console.error('Error while creating event.....');
				}
				
		);
		
	};
	
	console.log('fetchallevents')
	self.fetchAllEvents=function(){
		console.log(' Inside FetchAllevents method in event Controller ')
		EventService.fetchAllEvents()
		.then(function(d){
			self.events=d;
			
			console.log('value in events',self.events)
			
		},
		
		function(errResponse){
			console.error('Error while fetching the data');
		}
		
		);
	};
	
	 self.reset=function(){
      	  console.log('resetting the form',self.event);
      	  self.event={
      			eventid:'',
    			event_name:'',
    			date_of_event:'',
    			venue:'',
    			description:'',
    			errorCode:'',
    			errorMessage:''
      			  
      	  };
      	  $scope.myForm.$setPristine();//reset form
        };
        
        self.updateEvent=function(eventid){
        	EventService.updateEvent(eventid)
        	.then(
        		self.fetchAllEvents,
        		function(errResponse){
        			console.error('Error while updating event');
        		}
        	);
        	 };
        	 self.edit=function edit(eventid){
        		 console.log('eventid to be edited',eventid);
        		 for(var i = 0; i < self.events.length; i++){
       	            if(self.events[i].eventid === eventid) {
       	                self.event = angular.copy(self.events[i]);
       	                break;
       	            }
       	            }
        	 }
        	 self.deleteEvent = function(eventid){
       			EventService.deleteEvent(eventid)
       			.then(
       					self.fetchAllEvents,
       					function(errResponse){
       						console.error("Error while deleting event");
       					});
       		};
       		console.log('events value',self.events)
       		self.getSelectedEvent = function(id){
          		console.log("--.getting event:"+id)
          		EventService.getEvent(id)
          		.then(function(d){
          			self.events=d;
          			console.log('getSelectedevent in eventController',self.events)
          			$location.path('/view_event');
          			
          		},
          		function(errResponse){
          			console.error("Error while fetching events");
          		}
          	);
          		};
	
}]);