package com.officelibrary.library.exposure.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.officelibrary.library.exposure.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

@Service
@ApplicationScope
public class BookService {

    private List<Book> books;

    public BookService() {
        this.books = new ArrayList<>();
    }

    public List<Book> addBook(Book book) {
        this.books.add(book);
        return this.books;
    }

    public List<Book> getBooks() {
        return this.books;
    }

    public Optional<Book> getBookByTitle(String title) {
        return this.books.stream().filter(book -> book.getTitle().equals(title)).findAny();
    }

    public Optional<Book> getBookById(int id) {
        return this.books.stream().filter(book -> book.getUniqueID() == id).findAny();
    }

    public Optional<List<Book>> getBookByCategory(String categoryId) {
        return Optional.of(this.books.stream()
            .filter(book -> {
                if (book.getCategory() != null) {
                    return book.getCategory().getName().equals(categoryId);
                }
                return false;
            }).collect(Collectors.toList()));
    }

    public List<Book> deleteBook(Book book) {
        this.books.remove(book);
        return this.books;
    }

    public List<Book> deleteBookById(int id) {
        this.books.removeIf(book -> book.getUniqueID() == id);
        return this.books;
    }

    public void updateBook(int id, Book newBook) {
        this.books.set(books.indexOf(getBookById(id).orElseThrow(() -> new RuntimeException("Book with id: " + id + " has not been found"))),
            newBook);
    }
}
