package repository.book;

import model.Book;
import repository.Cache;

import java.util.List;
import java.util.Optional;

public class BookRepositoryCacheDecorator extends BookRepositoryDecorator{
    private Cache<Book> cache;

    public BookRepositoryCacheDecorator(BookRepository bookRepository, Cache<Book> cache){
        super(bookRepository);
        this.cache = cache;
    }

    @Override
    public List<Book> findAll() {
        if(cache.hasResult()){
            return cache.load();
        }

        List<Book> allBooks = decoratedBookRepository.findAll();
        cache.save(allBooks);

        return allBooks;
    }

    @Override
    public Optional<Book> findById(Long id) {
        if(cache.hasResult()) {
            return cache.load().stream()
                    .filter(it -> it.getId().equals(id))
                    .findFirst();
        }

        final Optional<Book> result = decoratedBookRepository.findById(id);

        if (result.isPresent()){
            cache.add(result.get());
            return result;
        }

        return Optional.empty();
    }

    @Override
    public boolean save(Book book) {
        return decoratedBookRepository.save(book);
    }

    @Override
    public void removeAll() {
        cache.invalidateCache();
        decoratedBookRepository.removeAll();
    }
}
