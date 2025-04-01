package com.inventory.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.service.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}

