package com.officelibrary.library.junit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.officelibrary.library.exposure.model.Author;
import com.officelibrary.library.exposure.model.Book;
import com.officelibrary.library.exposure.model.Category;
import com.officelibrary.library.exposure.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class BookServiceTestJunit {

    private static final List<Book> library = Arrays.asList(
        new Book("Ulysses", "Ulysses chronicles", Arrays.asList(new Author("James", "Joyce")), new Category("Myths")),
        new Book("Don Quixote", "Retired country gentleman in his fifties", Arrays.asList(new Author("Miguel", "de Cervantes")),
            new Category("Legends")),
        new Book("One Hundred Years of Solitude", "Widely beloved and acclaimed novel", null, new Category("Magic Realism")),
        new Book("The Great Gatsby", "An era that Fitzgerald himself dubbed the.",
            Arrays.asList(new Author("Francis Scott", "Fitzgerald")), new Category("Thriller"))
    );

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void reset() {
        bookService = new BookService();
    }

    @Test
    void addAndGetASingleBookTest() {
        //given
        bookService.addBook(library.get(0));

        //when
        Optional<Book> bookOptional = bookService.getBookByTitle("Ulysses");
        assertTrue(bookOptional.isPresent());
        Book book = bookOptional.get();
        assertAll(
            "Assert Ulysses is present",
            () -> assertEquals("Ulysses", book.getTitle()),
            () -> assertEquals(library.get(0).getAuthors().get(0), book.getAuthors().get(0)),
            () -> assertEquals(library.get(0).getDescription(), book.getDescription(), "Descriptions must match")
        );
    }

}
