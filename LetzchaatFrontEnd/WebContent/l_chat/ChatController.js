app.controller("ChatController" , function($scope, $rootScope,ChatService) {
	$scope.messages = [];
    $scope.message = "";
    $scope.max = 140;
    
   $scope.friend_name="";
    
   /* var self = this;
    self.friend={
    		friend_name:''
    		
    };
    */
    
    
    console.log('Outside friendName()')
    
    
   /* self.friendName= 
    function(friend_name){
    	console.log('FriendName in chatcontroller',friend_name)
    	$rootScope.friend_name=friend_name;
    	console.log('Friend_name',friend_name)
    	$scope.addMessage()
    	
    }*/
    
    
   $scope.addMessage = function() {
    	
    	console.log("addMessage")
    	console.log('Friend name',$rootScope.friend_name)
    	console.log('Message is',$scope.message)
    ChatService.send($scope.message);
    	 /* ChatService.send($scope.message);*/
    	$scope.message = "";
    };
    
    ChatService.recieve().then(null , null, function(message) {   // null -> successhandler and null->errorhandler
         console.log("recieve in ChatController") 
              $scope.messages.push(message);  
         console.log('Recieved the message',$scope.messages);   //this message we have to display in html text area.
    });
}); 