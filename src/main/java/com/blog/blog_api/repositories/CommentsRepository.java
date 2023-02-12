package com.blog.blog_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blog_api.models.Comments;

public interface CommentsRepository extends JpaRepository<Comments, Long>{
public List<Comments> findByPublicationsId(long publicationId);
}
