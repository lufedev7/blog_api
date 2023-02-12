package com.blog.blog_api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blog_api.models.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
    public Optional<Rol> findByName(String name);
}
