package com.ltfullstack.booksevice.query.projection;

import com.ltfullstack.booksevice.command.data.Book;
import com.ltfullstack.booksevice.command.data.BookRepository;
import com.ltfullstack.booksevice.query.model.BookResponseModel;
import com.ltfullstack.booksevice.query.queries.GetAllBooksQuery;
import com.ltfullstack.booksevice.query.queries.GetBookDetailQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BookProjection {
    @Autowired
    private BookRepository bookRepository;

    @QueryHandler
    public List<BookResponseModel> handle(GetAllBooksQuery query){
        List<Book> list = bookRepository.findAll();
        List<BookResponseModel> response = new ArrayList<>();
        list.forEach(book -> {
            BookResponseModel model = new BookResponseModel();
            BeanUtils.copyProperties(book, model);
            response.add(model);
        });

        return response;
    }

    @QueryHandler
    public BookResponseModel handle(GetBookDetailQuery query){
        BookResponseModel response = new BookResponseModel();
        bookRepository.findById(query.getId()).ifPresent(book -> BeanUtils.copyProperties(book, response));
        return response;
    }
}
