package com.example.blogapi.service;

import java.util.List;

import com.example.blogapi.payload.PostDto;
import com.example.blogapi.payload.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto postDto);
	
	List<PostDto> getAllPosts();
	
	List<PostDto> getAllPostsWithPagination(int pageNo, int pageSize);

	PostDto getPostById(long id);
	
	PostDto updatePost(PostDto postDto, long id);
	
	void deletePostById(long id);

	PostResponse getAllPostsWithPaginationPage(int pageNo, int pageSize);

	PostResponse getAllPostsWithPaginationPageSort(int pageNo, int pageSize, String sortBy);

	PostResponse getAllPostsWithPaginationPageSortAndDir(int pageNo, int pageSize, String sortBy, String sortDir);
	
	List<PostDto> getPostByCategory(Long categoryId);
}
