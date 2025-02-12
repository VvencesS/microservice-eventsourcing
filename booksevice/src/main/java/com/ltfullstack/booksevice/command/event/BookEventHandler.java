package com.ltfullstack.booksevice.command.event;

import com.ltfullstack.booksevice.command.data.Book;
import com.ltfullstack.booksevice.command.data.BookRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookEventHandler {
    @Autowired
    BookRepository bookRepository;

    @EventHandler
    public void on(BookCreatedEvent event){
        Book book = new Book();
        BeanUtils.copyProperties(event, book);
        bookRepository.save(book);
    }

    @EventHandler
    public void on(BookUpdatedEvent event){
        Optional<Book> oBook = bookRepository.findById(event.getId());

        oBook.ifPresent(book -> {
            book.setName(event.getName());
            book.setAuthor(event.getAuthor());
            book.setIsReady(event.getIsReady());

            bookRepository.save(book);
        });
    }

    @EventHandler
    public void on(BookDeletedEvent event){
        Optional<Book> oBook = bookRepository.findById(event.getId());
        oBook.ifPresent(book -> bookRepository.delete(book));
    }
}
