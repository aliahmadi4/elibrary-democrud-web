package edu.mum.cs.cs425.demos.elibrarydemocrudweb.service.impl;

import edu.mum.cs.cs425.demos.elibrarydemocrudweb.model.Book;
import edu.mum.cs.cs425.demos.elibrarydemocrudweb.repository.BookRepository;
import edu.mum.cs.cs425.demos.elibrarydemocrudweb.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;
	


//    @Override
//    public Iterable<Book> getAllBooks() {
//        return repository.findAll();
//    }
    
    @Override
    public Iterable<Book> getAllBooks() {
    	//sort using crudrepository and streams
//        return ((List<Book>)repository.findAll())
//        		.stream()
//        		.sorted(Comparator.comparing(Book::getTitle))
//        		.collect(Collectors.toList());
    	
    	//using jparepository instead of crudrepository
    	return repository.findAll(Sort.by("title"));
    }

    @Override
    public Book saveBook(Book book) {
        return repository.save(book);
    }

    @Override
    public Book getBookById(Integer bookId) {
        return repository.findById(bookId).orElse(null);
    }

    @Override
    public void deleteBookById(Integer bookId) {
        repository.deleteById(bookId);
    }

    @Override
    public Page<Book> getAllBooksPaged(int pageNo){
    	return repository.findAll(PageRequest.of(pageNo, 2, Sort.by("title")));
    }

	@Override
	public Optional<Book> findByISBN(String isbn) {
		return repository.findBookByIsbn(isbn);
	}
}
