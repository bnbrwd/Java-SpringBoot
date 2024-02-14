package com.example.blogapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.blogapi.entity.Category;
import com.example.blogapi.entity.Post;
import com.example.blogapi.exception.ResourceNotFoundException;
import com.example.blogapi.payload.PostDto;
import com.example.blogapi.payload.PostResponse;
import com.example.blogapi.repository.CategoryRepository;
import com.example.blogapi.repository.PostRepository;
import com.example.blogapi.service.PostService;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepository categoryRepository;
	

	@Override
	public PostDto createPost(PostDto postDto) {
		Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));

		Post post = mapToEntity(postDto);
		post.setCategory(category);
		
		Post newPost =postRepository.save(post);
		
		//convert entity to DTO
		PostDto postResponse = mapToDto(newPost);
		
		return postResponse;
	}


	@Override
	public List<PostDto> getAllPosts() {
		
		List<Post> posts = postRepository.findAll();
		return posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
	}
	
	//convert Entity into Dto
	private PostDto mapToDto(Post post) {
//		PostDto postDto = new PostDto();
//		postDto.setId(post.getId());
//		postDto.setTitle(post.getTitle());
//		postDto.setDescription(post.getDescription());
//		postDto.setContent(post.getContent());
		PostDto postDto = modelMapper.map(post, PostDto.class);
		return postDto;
	}
	
	//converted Dto into Entity
	private Post mapToEntity(PostDto postDto) {
//		Post post = new Post();
//		post.setTitle(postDto.getTitle());
//		post.setDescription(postDto.getDescription());
//		post.setContent(postDto.getContent());
		Post post = modelMapper.map(postDto, Post.class);
		return post;
	}


	@Override
	public PostDto getPostById(long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		return mapToDto(post);
		
	}

	//update post by id
	@Override
	public PostDto updatePost(PostDto postDto, long id) {
		// get post by id from database
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));

		post .setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		post.setCategory(category);
		
		Post updatedPost = postRepository.save(post);
		return mapToDto(updatedPost);
		
	}


	@Override
	public void deletePostById(long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		postRepository.delete(post);
		
	}


	@Override
	public List<PostDto> getAllPostsWithPagination(int pageNo, int pageSize) {
		//create pageble instance
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Post> post = postRepository.findAll(pageable);
		//get content for page method
		List<Post> postRes = post.getContent();
		return postRes.stream().map(postItem -> mapToDto(postItem)).collect(Collectors.toList());
	}


	@Override
	public PostResponse getAllPostsWithPaginationPage(int pageNo, int pageSize) {
		//create pageble instance
				Pageable pageable = PageRequest.of(pageNo, pageSize);
				Page<Post> post = postRepository.findAll(pageable);
				//get content for page method
				List<Post> postRes = post.getContent();
				List<PostDto> content = postRes.stream().map(postItem -> mapToDto(postItem)).collect(Collectors.toList());
				PostResponse postResponse = new PostResponse();
				postResponse.setContent(content);
				postResponse.setLast(post.isLast());
				postResponse.setPageNo(post.getNumber());
				postResponse.setPageSize(post.getSize());
				postResponse.setTotalElement(post.getTotalElements());
				postResponse.setTotalPage(post.getTotalPages());
				return postResponse;
	}


	@Override
	public PostResponse getAllPostsWithPaginationPageSort(int pageNo, int pageSize, String sortBy) {
		//create pageble instance
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Post> post = postRepository.findAll(pageable);
		//get content for page method
		List<Post> postRes = post.getContent();
		List<PostDto> content = postRes.stream().map(postItem -> mapToDto(postItem)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(content);
		postResponse.setLast(post.isLast());
		postResponse.setPageNo(post.getNumber());
		postResponse.setPageSize(post.getSize());
		postResponse.setTotalElement(post.getTotalElements());
		postResponse.setTotalPage(post.getTotalPages());
		return postResponse;
	}


	@Override
	public PostResponse getAllPostsWithPaginationPageSortAndDir(int pageNo, int pageSize, String sortBy,String sortDir) {
		//create pageble instance
				Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
				Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
				Page<Post> post = postRepository.findAll(pageable);
				//get content for page method
				List<Post> postRes = post.getContent();
				List<PostDto> content = postRes.stream().map(postItem -> mapToDto(postItem)).collect(Collectors.toList());
				PostResponse postResponse = new PostResponse();
				postResponse.setContent(content);
				postResponse.setLast(post.isLast());
				postResponse.setPageNo(post.getNumber());
				postResponse.setPageSize(post.getSize());
				postResponse.setTotalElement(post.getTotalElements());
				postResponse.setTotalPage(post.getTotalPages());
				return postResponse;
	}


	@Override
	public List<PostDto> getPostByCategory(Long categoryId) {
		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
		
		List<Post> posts = postRepository.findByCategoryId(categoryId);
		return posts.stream().map((post) -> mapToDto(post)).collect(Collectors.toList());
		
	}

}
