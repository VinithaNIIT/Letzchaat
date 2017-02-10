app.service("ChatService" , function($q, $timeout,$rootScope) {
	
	var service = {} , listener = $q.defer(), socket = {          //var service is userdefined json object
		 client: null,
		 stomp: null
	}, messageIds = [];// we can send n number messages.Only ids are storing
	
	service.RECONNECT_TIMEOUT = 30000;
	service.SOCKET_URL = "/LetzchaatBackEnd/chat";
	service.CHAT_TOPIC = "/queue/message";      /*service.CHAT_TOPIC = "/friend_name/queue/message";*/
	service.CHAT_BROKER = "/app/chat";
	service.recieve = function() {
		console.log("recieve")
	return listener.promise; 
	};
	
	service.send = function(message) {
		console.log("send")
	var id = Math.floor(Math.random() * 1000000);
	socket.stomp.send(service.CHAT_BROKER, {
		priority: 9//0  9-> 9 is the  highest priority  
	}, JSON.stringify({     //If you send the json object,it will string  //send(destination,{},body);
		message: message,//ng-model
		name: $rootScope.currentUser.username,
		id: id
		/*friend_name:$rootScope.friend_name*/
	
	}));
	messageIds.push(id);
	};
	
	var reconnect = function() { //local method in the service
		console.log("reconnect")
	$timeout(function() {        //
		initialize();
	}, this.RECONNECT_TIMEOUT);	
	};
	
	var getMessage = function(data) {
		console.log("getMessage")
	var message = JSON.parse(data), out = {}; //out is JSON object
		out.message = message.message;
		out.name=message.name;
		out.time = new Date(message.time);
		/*out.friend_name=message.friend_name*/
		console.log('getMessage in service',out.message,out.name,out.time)
		return out;
	};
	
	var startListener = function() {
		console.log("recieve")
	    socket.stomp.subscribe(service.CHAT_TOPIC, function(data) {  //subscribe(destination,callback,{id:myid}
		listener.notify(getMessage(data.body)); //notify ->some message should popup. data.body(Actual message) 
	});	
	};
	
	var initialize = function() {
		console.log("initialize")
	    socket.client = new SockJS(service.SOCKET_URL);
		socket.stomp = Stomp.over(socket.client);  //over is the static method in the class.Stomp is builtin class
		socket.stomp.connect({}, startListener);//connect is predefined => connect(headers,connectCallback[success],errorCallback[error])
		socket.stomp.onclose = reconnect;
	};
	initialize();  //to call the initialize method first time explicitly
	return service;
});