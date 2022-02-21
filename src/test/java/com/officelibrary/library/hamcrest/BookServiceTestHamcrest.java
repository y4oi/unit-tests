package com.officelibrary.library.hamcrest;

import java.util.Arrays;
import java.util.List;

import com.officelibrary.library.exposure.model.Author;
import com.officelibrary.library.exposure.model.Book;
import com.officelibrary.library.exposure.model.Category;
import com.officelibrary.library.exposure.service.BookService;
import org.junit.jupiter.api.BeforeEach;
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

}
