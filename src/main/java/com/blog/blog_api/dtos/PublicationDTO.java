package com.blog.blog_api.dtos;

import java.util.Set;

import com.blog.blog_api.models.Comments;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PublicationDTO {
    private long id;
    @NotEmpty
    @Size(min = 2,message = "Title must have at least  two characters.")
    private String tittle;
    @NotEmpty
    @Size(min = 10,message = "Descriptions must have at least  ten characters.")
    private String description;
    @NotEmpty
    private String content;
    private Set<Comments> comments;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public Set<Comments> getComments() {
        return comments;
    }

    public void setComments(Set<Comments> comments) {
        this.comments = comments;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PublicationDTO() {
        super();
    }

}
