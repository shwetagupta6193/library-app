package com.example.library.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.library.persistence.entity.Books;

public interface BookRepository extends JpaRepository<Books, Long> {

}
