package com.lesnoy.application.controller;

import com.lesnoy.application.service.BookService;
import org.summer.context.annotation.IntensiveAutowired;
import org.summer.context.annotation.IntensiveComponent;

@IntensiveComponent
public class BookController {

    @IntensiveAutowired
    public BookService bookService;

    public BookController() {
    }

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public BookService getBookService() {
        return bookService;
    }
}
