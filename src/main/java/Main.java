import database.DatabaseConnectionFactory;
import repository.*;
import repository.book.BookRepository;
import repository.book.BookRepositoryCacheDecorator;
import repository.book.BookRepositoryMySQL;
import service.book.BookService;
import service.book.BookServiceImpl;
import java.sql.Connection;

public class Main {
    public static void main(String[] args){
        System.out.println("Hello world!");

        Connection connection = DatabaseConnectionFactory.getConnectionWrapper(true).getConnection();

        BookRepository bookRepository = new BookRepositoryMySQL(connection);
        BookRepository decoratedBookRepository = new BookRepositoryCacheDecorator(bookRepository, new Cache<>());
        BookService bookService = new BookServiceImpl(decoratedBookRepository);

//        bookService.save(new BookBuilder()
//                .setId(0L)
//                .setAuthor("Alex")
//                .setTitle("Awesome")
//                .setPublishedDate(LocalDate.of(2008, 4, 1))
//                .build());
//
//        bookService.save(new BookBuilder()
//                .setId(1L)
//                .setAuthor("Alex1")
//                .setTitle("Awesome1")
//                .setPublishedDate(LocalDate.of(2009, 5, 1))
//                .build());
//        bookService.save(new BookBuilder()
//                .setId(2L)
//                .setAuthor("Alex2")
//                .setTitle("Awesome2")
//                .setPublishedDate(LocalDate.of(2010, 6, 1))
//                .build());
//        bookService.save(new BookBuilder()
//                .setId(3L)
//                .setAuthor("Alex3")
//                .setTitle("Awesome3")
//                .setPublishedDate(LocalDate.of(2011, 7, 1))
//                .build());
//        bookService.save(new BookBuilder()
//                .setId(4L)
//                .setAuthor("Alex4")
//                .setTitle("Awesome4")
//                .setPublishedDate(LocalDate.of(2012, 8, 1))
//                .build());


        System.out.println(bookService.findAll());
        System.out.println(bookService.findAll());

        System.out.println(bookService.getAgeOfBook(2L));
    }
}
