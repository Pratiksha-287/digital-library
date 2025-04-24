package com.example.digital_library.adapter;


import com.example.digital_library.commons.CommonAdapter;
import com.example.digital_library.entities.BookInputEntity;
import com.example.digital_library.mappers.input.BookInputMapper;
import com.example.digital_library.model.BookModel;
import com.example.digital_library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookAdapter implements CommonAdapter<BookInputEntity, BookModel> {
    private final BookInputMapper bookInputMapper;
    private final BookService bookService;

    @Autowired
    public BookAdapter(BookInputMapper bookInputMapper, BookService bookService) {
        this.bookInputMapper=bookInputMapper;
        this.bookService=bookService;
    }

    @Override
    public BookModel save(BookInputEntity inputEntity){
        return this.bookService.addBook(
                this.bookInputMapper.mapToModel(inputEntity)
                );
    }

    @Override
    public BookModel update(BookInputEntity inputEntity){
        return this.bookService.updateBook(
                this.bookInputMapper.mapToModel(inputEntity)
        );
    }

    @Override
    public List<BookModel> getAll(){
        return this.bookService.getAllBooks();
    }

    @Override
    public BookModel getById(long id){
        return this.bookService.getBookById(id);
    }


    @Override
    public void delete(long id){
        this.bookService.deleteById(id);
    }



}
