package com.example.blogapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.blogapi.entity.Comment;
import com.example.blogapi.entity.Post;
import com.example.blogapi.exception.BlogAPIException;
import com.example.blogapi.exception.ResourceNotFoundException;
import com.example.blogapi.payload.CommentDto;
import com.example.blogapi.repository.CommentRepository;
import com.example.blogapi.repository.PostRepository;
import com.example.blogapi.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(long postId, CommentDto commentDto) {
		Comment comment = mapToEntity(commentDto);
		Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		//set post to comment entity
		comment.setPost(post);
		//save comment entity to DB
		Comment newComment = commentRepository.save(comment);
		
		return mapToDto(newComment);
	}
	
	private CommentDto mapToDto(Comment comment) {
//		CommentDto commentDto = new CommentDto();
//		commentDto.setId(comment.getId());
//		commentDto.setName(comment.getName());
//		commentDto.setEmail(comment.getEmail());
//		commentDto.setBody(comment.getBody());
		CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
		return commentDto;
	}
	
	private Comment mapToEntity(CommentDto commentDto) {
//		Comment comment = new Comment();
//		comment.setId(commentDto.getId());
//		comment.setName(commentDto.getName());
//		comment.setEmail(commentDto.getEmail());
//		comment.setBody(commentDto.getBody());
		Comment comment = modelMapper.map(commentDto, Comment.class);
		return comment;
	}

	@Override
	public List<CommentDto> getCommentsByPostId(long postId) {
		
		
		List<Comment> comments = commentRepository.findByPostId(postId);
		
		return comments.stream().map(commnet -> mapToDto(commnet)).collect(Collectors.toList());
	}

	@Override
	public CommentDto getCommentById(long postId, long commentId) {
		Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "comment doesn't belong to post");
		}
		
		return mapToDto(comment);
		
	}

	@Override
	public CommentDto updateComment(long postId, long commentId, CommentDto commentDto) {
		Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "comment doesn't belong to post");
		}
		
		comment.setName(commentDto.getName());
		comment.setEmail(commentDto.getEmail());
		comment.setBody(commentDto.getBody());
		
		Comment updatedComment = commentRepository.save(comment);
		return mapToDto(updatedComment);
		
	}

	@Override
	public void deleteComment(long postId, long commentId) {
		Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "comment doesn't belong to post");
		}
		
		commentRepository.delete(comment);
		
	}

}
