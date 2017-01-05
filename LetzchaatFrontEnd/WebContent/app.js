var app=angular.module('myApp',['ngRoute','ui.bootstrap','ngCookies']);

app.config(function($routeProvider){
	console.log("APP.js ");
	
$routeProvider

.when('/',{
	templateUrl : 'l_common/body.html',
	
	
})

.when('/login',{
templateUrl : 'l_user/login.html',
controller : 'UserController'
})
.when('/registration',{
templateUrl : 'l_user/registration.html',
controller : 'UserController'
})

.when('/create_blog',{
templateUrl : 'l_blog/create_blog.html',
controller : 'BlogController'
})
.otherwise({redirectTo: '/'});
});

app.controller('CarouselDemoCtrl', ['$scope',function($scope) {
	 
	  $scope.myInterval = 50000;
	  $scope.slides = [
	     { image1: 'lib/image/banner1.jpg' },
	    { image1: 'lib/image/banner2.jpg'},
	    { image1: 'lib/image/banner3.jpg' },
	    { image1: 'lib/image/banner4.jpg' },
	    
	  ];
}]);
	  
	  app.run(function($rootScope, $location, $cookieStore, $http){
			 $rootScope.$on('$locationChangeStart', function (event, next, current) {
				 console.log("$locationChangeStart")
				 var restrictedPage=$.inArray($location.path(),['//','/','/manage_friends','/search_job','/manage_jobs','/view_blog','/register','/manage_users','/list_blog','/create_blog','/chat','/list_chat_forum','/adminhome'])=== -1;
				// -1 ----> non-restricted pages are more and for restricted pages ----> 1 ;
				 console.log("restrictedPage:" +restrictedPage)
			     var loggedIn = $rootScope.currentUser.username;
			      console.log("loggedIn:"+loggedIn)
		     if(!loggedIn) 
		     {
		    	 if(restrictedPage) {
		    		 console.log("Navigating to login page:")
		    		 $location.path('/');
		    	 }
		    	  
		     }
		     else //logged in
		    	 {
		    	 var role = $rootScope.currentuser.role;
		    	 var userRestrictedPage = $.inArray($location.path(), ['/post_job']) === 0;
		    	 
		    	 if (userRestrictedPage && role!='admin')
		    		 {
		    		 alert("You cannot do this operation as you are not logged in as:"+role)
		    		 $location.path('/login');
		    		 }
		    	 }
		  //keep user logged in after page refresh
		   $rootScope.currentUser = $cookieStore.get('currentUser') ||{};
			 if($rootScope.currentUser){
				 $http.defaults.headers.common['Authorization']='Basic'+$rootScope.currentUser;
				 }
		});
				
			  
	  });

