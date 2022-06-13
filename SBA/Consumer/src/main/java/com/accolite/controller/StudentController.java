package com.accolite.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.Proxy.BookProxy;
import com.accolite.model.BookDTO;

@RestController
@RequestMapping("/studentService")
public class StudentController {

	@Autowired
	private BookProxy proxy;

	private static final Logger log = LoggerFactory.getLogger(StudentController.class);

	@GetMapping("/findCost/bookname/{bookname}/quantity/{quantity}")
	public ResponseEntity<BookDTO> findCostByBook(@PathVariable String bookname,
			@PathVariable Integer quantity) {
 
	
		if (bookname.isEmpty() ) {
			
			log.error(" book name is empty, pass the book name");
			return new ResponseEntity("book name is empty",HttpStatus.NOT_FOUND);
		}
		
		BookDTO book = proxy.getBookByName(bookname);

		BookDTO b = new BookDTO(book.getBookId(), book.getBookName(), book.getBookCost(), quantity,
				(book.getBookCost() * quantity));

		log.info( " book found having details");

		return new ResponseEntity<BookDTO>(b, HttpStatus.FOUND);

	}
}
