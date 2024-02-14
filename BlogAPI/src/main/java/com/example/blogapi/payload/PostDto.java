package com.example.blogapi.payload;

import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "PostDto", description = "Description of postDto")
public class PostDto {
	
	private long id;
	@NotEmpty
	@Size(min = 2, message = "Post title should have at least 2 characters")
	@Schema(description = "The Title of the blog", example = "Facebook blog")
	private String title;
	@NotEmpty
	@Size(min = 10, message = "Post description should have at least 10 characters")
	private String description;
	private String content;
	private Set<CommentDto> comments;
	
	private Long categoryId;

}
