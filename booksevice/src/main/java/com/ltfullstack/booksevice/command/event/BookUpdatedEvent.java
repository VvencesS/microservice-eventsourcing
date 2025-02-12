package com.ltfullstack.booksevice.command.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookUpdatedEvent {
    private String id;
    private String name;
    private String author;
    private Boolean isReady;
}
