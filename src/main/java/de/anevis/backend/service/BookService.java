package de.anevis.backend.service;

import de.anevis.backend.repository.BookRepository;
import de.anevis.backend.domain.Book;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class BookService {

	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	public Page<Book> findAll(Integer pageNo, Integer pageSize, String filterValue) {
		Page<Book> pagedResult;
		if(pageNo != null && pageSize != null){
			if (filterValue != null && !filterValue.isEmpty()) {
				pagedResult = bookRepository.findByFilterValue(filterValue, PageRequest.of(pageNo - 1, pageSize));
				if(!pagedResult.hasContent() && pagedResult.getTotalPages() > 0){
					pagedResult = bookRepository.findByFilterValue(filterValue, PageRequest.of(pagedResult.getTotalPages() - 1, pageSize));
				}
			} else {
				pagedResult = bookRepository.findAll(PageRequest.of(pageNo - 1, pageSize));
			}
		}
		else{
			pagedResult = bookRepository.findAll(Pageable.unpaged());
		}
		return pagedResult;
	}

	public Book findById(Long id){
		Optional<Book> bookOptional = bookRepository.findById(id);
		return bookOptional.orElse(null);
	}

	public Book save(Book book){
		return bookRepository.save(book);
	}

	public Book update(Long id, Book updateBook){
		Optional<Book> bookOptional = bookRepository.findById(id);
		if(bookOptional.isPresent()){
			Book existingBook = bookOptional.get();
			BeanUtils.copyProperties(updateBook, existingBook, "id");
			return bookRepository.save(existingBook);
		}
		return null;
	}

	public boolean delete(Long id){
		Optional<Book> bookOptional = bookRepository.findById(id);
		if(bookOptional.isPresent()){
			bookRepository.delete(bookOptional.get());
			return true;
		}
		return false;
	}

}
