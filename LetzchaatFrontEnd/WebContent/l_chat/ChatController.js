app.controller("ChatController" , function($scope, ChatService) {
	$scope.messages = [];
    $scope.message = "";
    $scope.max = 140;
    
    
    $scope.addMessage = function() {
    	console.log("addMessage")
    ChatService.send($scope.message);
    	$scope.message = "";
    };
    
    ChatService.recieve().then(null , null, function(message) {
         console.log("recieve in ChatController") 
              $scope.messages.push(message);  
         console.log('Recieved the message',$scope.messages);
    });
}); 