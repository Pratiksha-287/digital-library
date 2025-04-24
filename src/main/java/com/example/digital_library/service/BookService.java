package com.example.digital_library.service;

import com.example.digital_library.entities.BookInputEntity;
import com.example.digital_library.model.BookModel;
import com.example.digital_library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookModel addBook(BookModel book){
        return this.bookRepository.save(book);
    }

    public BookModel updateBook(BookModel book){return this.bookRepository.update(book);}

    public BookModel getBookById(long id){
        return this.bookRepository.findById(id);
    }

    public List<BookModel> getAllBooks(){return this.bookRepository.findAllBooks();}

    public void deleteById(long id){this.bookRepository.deleteById(id);}

}
