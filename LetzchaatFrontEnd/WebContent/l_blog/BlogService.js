'use strict'
app.factory('BlogService', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
 
    var REST_SERVICE_URI = 'http://localhost:8081/LetzchaatBackEnd';
 return{
	 
	 fetchAllBlogs:function(){
	 
	 return $http.get(REST_SERVICE_URI+'/blogs/')
	 .then(
	function(response){
		return response.data;
	},
	function(errResponse){
		console.error('Error while fetching the Blogs');
		return $q.reject(errResponse);
	}
	 );
	 
 },
 createBlog:function(blog){
	 
	 return $http.post(REST_SERVICE_URI+'/createblog/',blog)
	 .then(function(response){
		 return response.data;
		 
	 },
	 function(errResponse){
		 console.error('Error while creating the blog');
		 return $q.reject(errResponse);
		 
	 }
			 
	 );
 },
 getBlog:function(id){
	 return $http.get(REST_SERVICE_URI+'/getblog/'+id)
	 .then(function(response){
		 
		 $rootScope.selectedBlog=response.data;
		 return response.data;
	 },
	 function(errResponse){
		 console.error('Error while getting blog');
		 return $q.reject(errResponse);
	 }
	 
	 );
 },
 deleteBlog:function(id){
	 return $http.del(REST_SERVICE_URI+'/deleteblog/'+id)
	 .then(function(response){
		return response.data; 
	 },
	 function(errResponse){
		 console.error('Error while deleting the blog');
		 return $q.reject(errResponse);
	 }
	 
	 );
 },
 updateBlog:function(blogid){
	 return $http.put(REST_SERVICE_URI+'/updateblog/'+blogid)
	 .then(function(response){
		 return response.data;
	 },
	 function(errResponse){
		 console.error('Error while updating the blog');
		 return $q.reject(errResponse);
	 }
			 
	 );
 }
 
 
 
 }
 }]);