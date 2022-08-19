package com.officelibrary.library.hamcrest;

import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.officelibrary.library.exposure.model.Author;
import com.officelibrary.library.exposure.model.Book;
import com.officelibrary.library.exposure.model.Category;
import com.officelibrary.library.exposure.service.BookService;
import org.assertj.core.api.Assertions;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class BookServiceTestHamcrest {

    private static final List<Book> library = Arrays.asList(
        new Book("Ulysses", "Ulysses chronicles", List.of(new Author("James", "Joyce")), new Category("Myths")),
        new Book("Don Quixote", "Retired country gentleman in his fifties", List.of(new Author("Miguel", "de Cervantes")),
            new Category("Legends")),
        new Book("One Hundred Years of Solitude", "Widely beloved and acclaimed novel", null, new Category("Magic Realism")),
        new Book("The Great Gatsby", "An era that Fitzgerald himself dubbed the.",
            List.of(new Author("Francis Scott", "Fitzgerald")), new Category("Thriller"))
    );

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void reset() {
        bookService = new BookService();
    }

    @Test
    void retrieveBookTest() {
        bookService.addBook(library.get(0));

        Optional<Book> bookOptional = bookService.getBookByTitle("Ulysses");

        MatcherAssert.assertThat(bookOptional.isPresent(), equalTo(true));
    }


    @Test
    void addMultipleBooksTest() {
        library.forEach(bookService::addBook);

        // assertEquals(4, bookService.getBooks().size());
        Assertions.assertThat(bookService.getBooks().size()).isEqualTo(4);
    }

    @Test
    void addBooksWithDuplicateTest() {
        library.forEach(bookService::addBook);
        library.forEach(bookService::addBook);
        Assertions.assertThat(bookService.getBooks().size()).isEqualTo(8);
    }

}
