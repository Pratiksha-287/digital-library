package com.example.digital_library.repository;

import com.example.digital_library.entities.BookOutputEntity;
import com.example.digital_library.exceptions.ResourceNotFoundException;
import com.example.digital_library.mappers.output.BookOutputMapper;
import com.example.digital_library.model.BookModel;
import com.example.digital_library.repository.jpa.BookJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BookRepository {
    private final BookJPARepository bookJPARepository;
    private final BookOutputMapper bookOutputMapper;

    @Autowired
    public BookRepository( BookJPARepository bookJPARepository,BookOutputMapper bookOutputMapper) {
        this.bookJPARepository = bookJPARepository;
        this.bookOutputMapper = bookOutputMapper;
    }

    public BookModel save(BookModel book){
        //convert model to output-entity
        BookOutputEntity outputEntity = this.bookOutputMapper.mapFromModel(book);
        //save in db
        BookOutputEntity savedOutputEntity = this.bookJPARepository.save(outputEntity);
        //convert the saved output entity into model
        //return the model
        return this.bookOutputMapper.mapToModel(savedOutputEntity);
    }

    public BookModel update(BookModel book){
        BookModel existingModel=this.findById(book.getId());
        book.setUpdatedAt(Instant.now());
        book.setCreatedAt(existingModel.getCreatedAt());
        return this.save(book);
    }

    public BookModel findById(long id){
        Optional<BookOutputEntity> optionalBookOutputEntity = this.bookJPARepository.findById(id);
        return optionalBookOutputEntity.map(this.bookOutputMapper::mapToModel).orElse(null);
    }

    public List<BookModel> findAllBooks(){
        return this.bookJPARepository.findAll().stream().map(this.bookOutputMapper::mapToModel).toList();
    }

    public void deleteById(long id){
        this.bookJPARepository.deleteById(id);
    }


}
