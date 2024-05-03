package com.library.DiegoA.services;

import com.library.DiegoA.ExceptionHandler.DuplicateUserCodeException;
import com.library.DiegoA.models.BookModel;
import com.library.DiegoA.repositories.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository BookRepository;

    public ArrayList<BookModel> getAllBooks(){
        return (ArrayList<BookModel>) BookRepository.findAll();
    }

    public BookModel saveBook(BookModel book){
        String searchCode = book.getCode();
        if(BookRepository.findByCode(searchCode).isEmpty()){
            return BookRepository.save(book);
        } else{
            throw new DuplicateUserCodeException("El libro con el código " + searchCode + " ya existe.");

        }
    }

    public Optional<BookModel> findById(Long id){
        return BookRepository.findById(id);
    }

    public Optional<BookModel> findByCode(String code){
        return BookRepository.findByCode(code);
    }

    //editar
    public BookModel editBook(Long id, BookModel book){

        BookModel existingBook = BookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Book not found with id: " + id));

        existingBook.setCode(book.getCode());
        existingBook.setName(book.getName());
        existingBook.setGenre(book.getGenre());
        existingBook.setDate(book.getDate());
        existingBook.setAuthor(book.getAuthor());

        return BookRepository.save(existingBook);
    }

    public String deleteBookById(Long id){

        if(findById(id).isPresent()){
            BookRepository.deleteById(id);
            return "Book deleted successfully";
        } else{
            return "Book with id "+ id +" not found";
        }

    }

    @Transactional
    public String deleteBookByCode(String code){

        if(findByCode(code).isPresent()){
            BookRepository.deleteByCode(code);
            return "Book deleted successfully";
        } else{
            return "Book with code "+ code +" not found";
        }

    }

}
