'use strict';
app.controller('BlogController', ['$scope', 'BlogService', function($scope, BlogService){
	var self=this;
	self.blog={
			blogid:'',
			blogtitle:'',
			description:'',
			date_of_creation:'',
			username:'',
			errorCode:'',
			errorMessage:''
		};
	self.blogs=[];
	
	
	self.submit=function(){
		
		console.log('Saving new blog',self.blog);
		self.createBlog(self.blog);
	}
	self.createBlog=function(blog){
		
		console.log('create blogs...');
		BlogService.createBlog(blog)
		.then(
				self.fetchAllBlogs,
				function(errResponse){
					console.error('Error while creating blog.....');
				}
				
		);
		
	};
	
	
	self.fetchAllBlogs=function(){
		
		BlogService.fetchAllBlogs()
		.then(function(d){
			self.blogs=d;
			
		},
		
		function(errResponse){
			console.error('Error while fetching the data');
		}
		
		);
	};
	
	 self.reset=function(){
      	  console.log('resetting the form',self.blog);
      	  self.blog={
      			blogid : '',
      			blogtitle :'',
     			description : '',
     			date_of_creation : '',
     			username : '',
     			status : '',
     			errorCode:'',
     			errorMessage:''
      			  
      	  };
      	  $scope.myForm.$setPristine();//reset form
        };
        
        self.updateBlog=function(blogid){
        	BlogService.updateBlog(blogid)
        	.then(
        		self.fetchAllBlogs,
        		function(errResponse){
        			console.error('Error while updating Blog');
        		}
        	);
        	 };
        	 self.edit=function edit(blogid){
        		 console.log('blogid to be edited',blogid);
        		 for(var i = 0; i < self.blogs.length; i++){
       	            if(self.blogs[i].blogid === blogid) {
       	                self.blog = angular.copy(self.blogs[i]);
       	                break;
       	            }
       	            }
        	 }
        	 self.deleteBlog = function(blogid){
       			BlogService.deleteBlog(blogid)
       			.then(
       					self.fetchAllBlogs,
       					function(errResponse){
       						console.error("Error while deleting Blog");
       					});
       		};
       		
       		self.getSelectedBlog =function(id){
          		console.log("--.getting blog:"+id)
          		BlogService.getBlog(id)
          		.then(function(d){
          			self.blog=d;
          			$location.path('/view_blog');
          			
          		},
          		function(errResponse){
          			console.error("Error while fetching Blogs");
          		}
          	);
          		};
	
}]);