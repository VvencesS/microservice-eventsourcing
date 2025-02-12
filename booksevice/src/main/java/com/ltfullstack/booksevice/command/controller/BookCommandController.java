package com.ltfullstack.booksevice.command.controller;

import com.ltfullstack.booksevice.command.command.CreateBookCommand;
import com.ltfullstack.booksevice.command.command.DeleteBookCommand;
import com.ltfullstack.booksevice.command.command.UpdateBookCommand;
import com.ltfullstack.booksevice.command.model.BookRequestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
public class BookCommandController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String addBook(@RequestBody BookRequestModel modal){
        CreateBookCommand command = new CreateBookCommand(UUID.randomUUID().toString(), modal.getName(), modal.getAuthor(),true);
        return commandGateway.sendAndWait(command);
    }
    @PutMapping("/{bookId}")
    public String updateBook(@RequestBody BookRequestModel modal, @PathVariable String bookId){
        UpdateBookCommand command = new UpdateBookCommand(bookId, modal.getName(), modal.getAuthor(), modal.getIsReady());
        return commandGateway.sendAndWait(command);
    }

    @DeleteMapping("/{bookId}")
    public String deleteBook(@PathVariable String bookId){
        DeleteBookCommand command = new DeleteBookCommand(bookId);
        return commandGateway.sendAndWait(command);
    }

}
