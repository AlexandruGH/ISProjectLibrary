package repositories;

import model.Book;
import model.builder.BookBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.BookRepository;
import repository.BookRepositoryCacheDecorator;
import repository.BookRepositoryMock;
import repository.Cache;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookRepositoryMockTest {

    private static BookRepository repository;

    @BeforeAll
    public static void setup(){
        repository = new BookRepositoryCacheDecorator(new BookRepositoryMock(), new Cache<>());
        System.out.println("@Before");
    }

    @Test
    public void findAll()
    {
        System.out.println("findAll");
        List<Book> allBooks = repository.findAll();

        assertTrue(allBooks.isEmpty());

        int nrInsertedBooks = 5;
        for(int i=0; i<nrInsertedBooks; i++){
            repository.save(new BookBuilder().setTitle("Title").build());
        }

        List<Book> newBooks = repository.findAll();

        assertEquals(nrInsertedBooks, newBooks.size());

    }

}
