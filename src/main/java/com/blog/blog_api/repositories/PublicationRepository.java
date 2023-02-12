package com.blog.blog_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blog_api.models.Publications;

public interface PublicationRepository extends JpaRepository<Publications, Long> {

}
