package com.library.DiegoA.controllers;

import com.library.DiegoA.models.BookModel;
import com.library.DiegoA.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping ("/books")
public class BookController {
    @Autowired
    BookService bookService;
    @GetMapping()
    public ArrayList<BookModel> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/id/{id}")
    public Optional<BookModel> getBookById(@PathVariable Long id){
        return bookService.findById(id);
    }

    @GetMapping("/code/{code}")
    public Optional<BookModel> getBookByCode(@PathVariable String code){
        return bookService.findByCode(code);
    }

    @PostMapping()
    public BookModel saveBook(@RequestBody BookModel book){
        return bookService.saveBook(book);
    }

    @PutMapping("/edit/{id}")
    public BookModel editBook(@PathVariable Long id, @RequestBody BookModel book){
        return bookService.editBook(id, book);
    }

    @DeleteMapping("/delete/id")
    public String deleteBookById(@RequestParam("id") Long id){
        return bookService.deleteBookById(id);
    }

    @DeleteMapping("/delete/code")
    public String deleteBookByCode(@RequestParam("code") String code){ return bookService.deleteBookByCode(code); }

}
