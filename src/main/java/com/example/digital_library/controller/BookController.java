package com.example.digital_library.controller;

import com.example.digital_library.adapter.BookAdapter;
import com.example.digital_library.commons.CommonAdapter;
import com.example.digital_library.entities.BookInputEntity;
import com.example.digital_library.exceptions.ResourceNotFoundException;
import com.example.digital_library.model.BookModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;

@RestController
@RequestMapping("/book")
public class BookController {
    private final CommonAdapter<BookInputEntity,BookModel> adapter;
    @Autowired
    public BookController(CommonAdapter<BookInputEntity,BookModel> adapter) {this.adapter = adapter;}

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@Valid @RequestBody BookInputEntity book) {
        return new ResponseEntity<>(
                this.adapter.save(book),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable long id) {
        BookModel book = this.adapter.getById(id);
        if (book == null) return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateBook(@Valid @RequestBody BookInputEntity book) {
        try {
            return new ResponseEntity<>(this.adapter.update(book), HttpStatus.OK);
        }catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllBooks() {
        return new ResponseEntity<>(this.adapter.getAll(),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id) {
        this.adapter.delete(id);
//        return new ResponseEntity<>( HttpStatus.OK);
        return ResponseEntity.ok("book deleted successfully");
    }
}
