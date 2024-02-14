package com.example.course.repository;

import com.example.course.entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface catRepo extends JpaRepository<Cat, Integer> {

}
