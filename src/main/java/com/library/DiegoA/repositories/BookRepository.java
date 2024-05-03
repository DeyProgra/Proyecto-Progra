package com.library.DiegoA.repositories;

import com.library.DiegoA.models.BookModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<BookModel, Long> {

    Optional<BookModel> findByCode(String code);

    void deleteByCode(String code);

}
