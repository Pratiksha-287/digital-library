package com.example.digital_library.repository.jpa;

import com.example.digital_library.entities.BookOutputEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookJPARepository extends JpaRepository<BookOutputEntity, Long> {
}
