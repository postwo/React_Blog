package com.example.react_blog.repository;

import com.example.react_blog.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity,Integer> {
}