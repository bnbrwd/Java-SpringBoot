package com.example.blogapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.blogapi.payload.PostDto;
import com.example.blogapi.payload.PostResponse;
import com.example.blogapi.service.PostService;
import com.example.blogapi.utils.AppConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
		return new ResponseEntity<PostDto>(postService.createPost(postDto), HttpStatus.CREATED);
	}
	
	//Get All Post rest api
	/*@GetMapping
	public List<PostDto> getAllPosts(){
		return postService.getAllPosts();
	} */
	
	//Get All Post rest api
	/*@GetMapping
	public List<PostDto> getAllPostsWithPagination(@RequestParam(value = "pageNo",defaultValue = "0",required = false)int pageNo,@RequestParam(value = "pageSize",defaultValue = "10",required = false)int pageSize){
		return postService.getAllPostsWithPagination(pageNo,pageSize);
	} */
	
/*	//Get All Post rest api
	@GetMapping
	public PostResponse getAllPostsWithPaginationPage(@RequestParam(value = "pageNo",defaultValue = "0",required = false)int pageNo,@RequestParam(value = "pageSize",defaultValue = "10",required = false)int pageSize){
		return postService.getAllPostsWithPaginationPage(pageNo,pageSize);
	}*/
	
	/*//Get All Post rest api
		@GetMapping
		public PostResponse getAllPostsWithPaginationPageSort(@RequestParam(value = "pageNo",defaultValue = "0",required = false)int pageNo,@RequestParam(value = "pageSize",defaultValue = "10",required = false)int pageSize,@RequestParam(value = "sortBy",defaultValue = "id",required = false)String sortBy){
			return postService.getAllPostsWithPaginationPageSort(pageNo,pageSize,sortBy);
		} */
	
	//Get All Post rest api
			@GetMapping
			public PostResponse getAllPostsWithPaginationPageSortAndDir(@RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false)int pageNo,@RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false)int pageSize,@RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false)String sortBy,@RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false)String sortDir){
				return postService.getAllPostsWithPaginationPageSortAndDir(pageNo,pageSize,sortBy,sortDir);
			}
	
	//get pot by id
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id){
		return ResponseEntity.ok(postService.getPostById(id));
	}
	
	//update post
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") long id){
		PostDto postResponse = postService.updatePost(postDto, id);
		
		return new ResponseEntity<>(postResponse, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){
		postService.deletePostById(id);
		
		return new ResponseEntity<String>("Post entity deleted successfully", HttpStatus.OK);
	}
	
	//get post by category
	@GetMapping("/category/{id}")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable(name = "id") long categoryId){
		List<PostDto> postList = postService.getPostByCategory(categoryId);
		return ResponseEntity.ok(postList);
	}
}
