package com.ltfullstack.booksevice.command.controller;

import com.ltfullstack.booksevice.command.command.CreateBookCommand;
import com.ltfullstack.booksevice.command.model.BookRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
public class BookCommandController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping String addBook(@RequestBody BookRequest modal){
        CreateBookCommand command = new CreateBookCommand(UUID.randomUUID().toString(), modal.getName(), modal.getAuthor(), true);
        return commandGateway.sendAndWait(command);
    }

}
