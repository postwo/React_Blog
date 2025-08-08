package com.example.react_blog.repository;

import com.example.react_blog.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<ImageEntity,Integer> {

    List<ImageEntity> findByBoardNumber(Integer boardNumber);
}